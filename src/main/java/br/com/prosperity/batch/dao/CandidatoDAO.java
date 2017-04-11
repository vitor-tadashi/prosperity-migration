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
			+ "MAX(IF(field_number = '1', value, 'n/c')) AS 'nome', "
			+ "MAX(IF(field_number = '3', value, 'n/c')) AS 'email', " 
			+ "MAX(IF(field_number = '8', value, '01/01/1900')) AS 'data nascimento', "
			+ "MAX(IF(field_number = '15', value, 'n/c')) AS 'vaga', "
			+ "MAX(IF(field_number = '10', value, 'n/c')) AS 'telefone', "
			+ "MAX(IF(field_number = '11', value, 'n/c')) AS 'cidade', "
			+ "MAX(IF(field_number = '31', value, 'n/c')) AS 'grau instrucao', "
			+ "MAX(IF(field_number = '28', value, 'n/c')) AS 'curso', "
			+ "MAX(IF(field_number = '29', value, 'n/c')) AS 'instituicao', "
			+ "MAX(IF(field_number = '32', value, 'n/c')) AS 'situacaoAtual', "
			+ "MAX(IF(field_number = '33', value, '01/01/1900')) AS 'dataFormacao', "
			+ "MAX(IF(field_number = '14', value, 'n/c')) AS 'como ficou sabendo', "
			+ "MAX(IF(field_number = '9', value, 'n/c')) AS 'cpf', "
			+ "MAX(IF(field_number = '46', value, 'n/c')) AS 'rg', "
			+ "MAX(IF(field_number = '47', value, 'n/c')) AS 'estado', "
			+ "MAX(IF(field_number = '48', value, 'n/c')) AS 'logradouro', "
			+ "MAX(IF(field_number = '49', value, 'n/c')) AS 'numeroResidencial', "
			+ "MAX(IF(field_number = '50', value, 'n/c')) AS 'complemento', "
			+ "MAX(IF(field_number = '52', value, 'n/c')) AS 'pretensao', "
			+ "MAX(IF(field_number = '53', value, 'n/c')) AS 'cep' "
			+ "FROM wp_rg_lead_detail where form_id = 4 and lead_id > ";

	private final String textFile = "target/generated-sources/UltimoLeadId.txt";
	private Connection conexao;
	private Statement stmt = null;
	private ResultSet rs = null;

	public String obterLeadIdUltimoProcessamento() {
		String lead = "1";
		this.stmt = null;
		this.rs = null;

		try {
			conexao = dataSource.getConnection();
			stmt = conexao.createStatement();
			// Comando do mySQL para pegar a última data da tabela wp_rg_lead
			stmt.execute("select max(lead_id) from wp_rg_lead_detail");
			rs = stmt.getResultSet();

			while (this.rs.next()) {
				lead = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}

		return lead;
	}

	// Listar:
	public List<CandidatoWordPressBean> listar() {
		List<CandidatoWordPressBean> listaCandidatos = new ArrayList<>();
		
		this.stmt = null;
		this.rs = null;

		// Para executar o batch a partir do mais recente cadastro:
		String leadIdMaisRecente = this.pegarLeadIUltimoCadastroDoArquivo();
		String buscarCadastrosMaisRecentes = this.sqlQuery + leadIdMaisRecente + " group by lead_id";

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
				candidato.setGrauInstrucao(this.rs.getString("grau instrucao"));
				candidato.setCurso(this.rs.getString("curso"));
				candidato.setInstituicao(this.rs.getString("instituicao"));
				candidato.setSituacaoAtual(this.rs.getString("situacaoAtual"));
				candidato.setDataFormacao(this.rs.getString("dataFormacao"));
				candidato.setComoFicouSabendo(this.rs.getString("como ficou sabendo"));
				candidato.setCPF(this.rs.getString("cpf"));
				candidato.setGrauInstrucao(this.rs.getString("grau instrucao"));
				candidato.setCidade(this.rs.getString("cidade"));
				candidato.setLogradouro(this.rs.getString("logradouro"));
				candidato.setNumeroResidencial("numeroResidencial");
				candidato.setComplemento(this.rs.getString("complemento"));
				candidato.setPretensao(this.rs.getString("pretensao"));
				candidato.setRG(this.rs.getString("rg"));
				candidato.setCEP(this.rs.getString("cep"));

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
		return listaCandidatos;
	}

	public String pegarLeadIUltimoCadastroDoArquivo() {
		String lead = "0";

		try {
			criarPasta();
			Scanner scanner = new Scanner(new File(textFile));
			lead = scanner.useDelimiter("\\Z").next();
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao tentar ler do seguinte arquivo: " + textFile);
			System.out.println(e);
		}

		return lead;
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