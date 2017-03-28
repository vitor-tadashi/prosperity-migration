package br.com.properity.batch.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.properity.batch.bean.CandidatoWordPressBean;
import br.com.properity.batch.connection.ConnectionFactory;

public class CandidatoDAO {

	private final String sqlQuery = "SELECT" + "lead_id," + "MAX(IF(field_number = '1', value, NULL)) AS '1',"
			+ "MAX(IF(field_number = '3', value, NULL)) AS '3'," + "MAX(IF(field_number = '8', value, NULL)) AS '8',"
			+ "MAX(IF(field_number = '15', value, NULL)) AS '15',"
			+ "MAX(IF(field_number = '10', value, NULL)) AS '10',"
			+ "MAX(IF(field_number = '11', value, NULL)) AS '11',"
			+ "MAX(IF(field_number = '27', value, NULL)) AS '27',"
			+ "MAX(IF(field_number = '28', value, NULL)) AS '28',"
			+ "MAX(IF(field_number = '29', value, NULL)) AS '29',"
			+ "MAX(IF(field_number = '31', value, NULL)) AS '31',"
			+ "MAX(IF(field_number = '32', value, NULL)) AS '32',"
			+ "MAX(IF(field_number = '33', value, NULL)) AS '33',"
			+ "MAX(IF(field_number = '14', value, NULL)) AS '14',"
			+ "MAX(IF(field_number = '16', value, NULL)) AS '16',"
			+ "MAX(IF(field_number = '18', value, NULL)) AS '18',"
			+ "MAX(IF(field_number = '23', value, NULL)) AS '23',"
			+ "MAX(IF(field_number = '20', value, NULL)) AS '20',"
			+ "MAX(IF(field_number = '21', value, NULL)) AS '21'," + "MAX(IF(field_number = '9', value, NULL)) AS '9'"
			+ "FROM" + "wp_rg_lead_detail where form_id = 4" + "GROUP BY" + "lead_id";

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
			conexao = ConnectionFactory.pegaConexao();
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
		String dataMaisRecente = this.pegarDataUltimoCadastroDoArquivo();
		String buscarCadastrosMaisRecentes = this.sqlQuery + dataMaisRecente;

		try {
			conexao = ConnectionFactory.pegaConexao();
			this.stmt = conexao.createStatement();
			this.stmt.execute(buscarCadastrosMaisRecentes);
			this.stmt.execute(sqlQuery);
			this.rs = this.stmt.getResultSet();

			/*
			 * if (stmt.execute(
			 * "select * from wp_rg_lead_detail where form_id = 4 and lead_id = 5591"
			 * )) { rs = stmt.getResultSet(); }
			 */
			while (this.rs.next()) {
				// Instancio o candidato bean
				CandidatoWordPressBean candidato = new CandidatoWordPressBean();
				candidato.setForm_id(4);
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex);
			System.out.println("SQLState: " + ex);
			System.out.println("VendorError: " + ex);
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

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
			Scanner scanner = new Scanner(new File(textFile));
			data = scanner.useDelimiter("\\Z").next();
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao tentar ler do seguinte arquivo: " + textFile);
			System.out.println(e);
		}
		
		return data;
	}
}