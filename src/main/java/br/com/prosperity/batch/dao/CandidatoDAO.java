package br.com.prosperity.batch.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import br.com.prosperity.batch.bean.CandidatoWordPressBean;

public class CandidatoDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private final String sqlQuery = "SELECT lead_id, " 
			+ "MAX(IF(field_number = '1', value, NULL)) AS 'nome', "
			+ "MAX(IF(field_number = '3', value, NULL)) AS 'email', " 
			+ "MAX(IF(field_number = '8', value, NULL)) AS 'data nascimento', "
			+ "MAX(IF(field_number = '15', value, NULL)) AS 'vaga', "
			+ "MAX(IF(field_number = '10', value, NULL)) AS 'telefone', "
			+ "MAX(IF(field_number = '11', value, NULL)) AS 'cidade', "
			+ "MAX(IF(field_number = '27', value, NULL)) AS 'grau instrucao', "
			+ "MAX(IF(field_number = '28', value, NULL)) AS 'curso', "
			+ "MAX(IF(field_number = '29', value, NULL)) AS 'instituicao', "
			+ "MAX(IF(field_number = '31', value, NULL)) AS 'tipoCurso', "
			+ "MAX(IF(field_number = '32', value, NULL)) AS 'situacaoAtual', "
			+ "MAX(IF(field_number = '33', value, NULL)) AS 'dataFormacao', "
			+ "MAX(IF(field_number = '14', value, NULL)) AS 'como ficou sabendo', "
			+ "MAX(IF(field_number = '16', value, NULL)) AS 'como ficou sabendo outros', "
			+ "MAX(IF(field_number = '18', value, NULL)) AS '18', "
			+ "MAX(IF(field_number = '23', value, NULL)) AS '23', "
			+ "MAX(IF(field_number = '20', value, NULL)) AS '20', "
			+ "MAX(IF(field_number = '21', value, NULL)) AS '21', " 
			+ "MAX(IF(field_number = '9', value, NULL)) AS 'cpf' "
			+ "FROM wp_rg_lead_detail where form_id = 4 GROUP BY lead_id";

	private List<CandidatoWordPressBean> listaCandidatos = new ArrayList<>();
	private final String textFile = "target/generated-sources/DataUltimoCadastro.txt";
	private Connection conexao;
	private Statement stmt = null;
	private ResultSet rs = null;

	public String obterDataUltimoProcessamento() {
		String date = "";
		this.stmt = null;
		this.rs = null;

		try {
			conexao = dataSource.getConnection();
			stmt = conexao.createStatement();
			// Comando do mySQL para pegar a última data da tabela wp_rg_lead
			stmt.execute("SELECT max(date_created) FROM wp_rg_lead");
			rs = stmt.getResultSet();

			while (this.rs.next()) {
				date = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}

		return date;
	}

	// Listar:
	public List<CandidatoWordPressBean> listar() {
		this.stmt = null;
		this.rs = null;

		// Para executar o batch a partir do mais recente cadastro:
		//String dataMaisRecente = this.pegarDataUltimoCadastroDoArquivo();
		String buscarCadastrosMaisRecentes = this.sqlQuery; //+ dataMaisRecente;

		try {
			conexao = dataSource.getConnection();
			this.stmt = conexao.createStatement();
			this.stmt.execute(buscarCadastrosMaisRecentes);
			this.rs = this.stmt.getResultSet();

			while (this.rs.next()) {
				CandidatoWordPressBean candidato = new CandidatoWordPressBean();

				candidato.setLead_id(this.rs.getLong("lead_id"));
				candidato.setNome(this.rs.getString("nome"));
				candidato.setEmail(this.rs.getString("email"));
				candidato.setDataNascimento(this.rs.getString("data nascimento"));
				candidato.setVaga(this.rs.getString("vaga"));
				candidato.setTelefone(this.rs.getString("telefone"));
				candidato.setGrauInstrucao(this.rs.getString("cidade"));
				candidato.setCurso(this.rs.getString("curso"));
				candidato.setInstituicao(this.rs.getString("instituicao"));
				candidato.setTipoCurso(this.rs.getString("tipoCurso"));
				candidato.setSituacaoAtual(this.rs.getString("situacaoAtual"));
				candidato.setDataFormacao(this.rs.getString("dataFormacao"));
				candidato.setComoFicouSabendo(this.rs.getString("como ficou sabendo"));
				candidato.setComoFicouSabendoOutros(this.rs.getString("como ficou sabendo outros"));
				// candidato.setRemuneracaoAtual(this.rs.getString(15));
				// candidato.setBeneficiosAtuais(this.rs.getString(16));
				candidato.setCPF(this.rs.getString("cpf"));
				candidato.setGrauInstrucao(this.rs.getString("grau instrucao"));

				listaCandidatos.add(candidato);
			}

		} catch (Exception ex) {
			// handle any errors
			System.out.println("Exception: " + ex);
		} finally {
			if (this.rs != null) {
				try {
					this.rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				this.rs = null;
			}

			if (this.stmt != null) {
				try {
					this.stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				this.stmt = null;
			}
		}
		return this.listaCandidatos;
	}

	public String pegarDataUltimoCadastroDoArquivo() {
		String data = "2000-01-01";

		try {
			criarPasta();
			Scanner scanner = new Scanner(new File(textFile));
			data = scanner.useDelimiter("\\Z").next();
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao tentar ler do seguinte arquivo: " + textFile);
			System.out.println(e);
		}

		return data;
	}

	private void criarPasta() {
		File file = new File(textFile);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
	}
}