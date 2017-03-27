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
import br.com.properity.batch.bean.WordpressBean;
import br.com.properity.batch.connection.ConnectionFactory;

public class CandidatoDAO {

	private final String sqlQuery = "select T1.*, T2.date_created from wp_rg_lead_detail AS T1, wp_rg_lead AS T2  where T1.form_id = 4 and DATE(T2.date_created) > ";
	private final String teste = "select * from wp_rg_lead_detail WHERE lead_id between 3337 and 3360 and form_id = 4 ORDER BY lead_id asc, field_number asc";
	
	private List<CandidatoWordPressBean> listaCandidatos = new ArrayList<>();
	private final String textFile = "target/generated-sources/DataUltimoCadastro.txt";
	private Connection conexao;
	private Statement stmt = null;
	private ResultSet rs = null;

	public String DataUltimoCadastro() {
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

	//Listar:
	public List<CandidatoWordPressBean> listar() {
		this.stmt = null;
		this.rs = null;
		
		// Para executar o batch a partir do mais recente cadastro:
		String dataMaisRecente = this.pegarDataUltimoCadastroDoArquivo();
		String buscarCadastrosMaisRecentes = this.sqlQuery + dataMaisRecente;

		try {
			conexao = ConnectionFactory.pegaConexao();
			this.stmt = conexao.createStatement();
			//this.stmt.execute(buscarCadastrosMaisRecentes);
			this.stmt.execute(teste);
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
		String data = "n foi";
		
		try {
			Scanner scanner = new Scanner(new File(textFile));
			data = scanner.useDelimiter("\\Z").next();
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao tentar ler do seguinte arquivo: " + textFile);
			System.out.println(e);
		}
		// System.out.println(data);
		return data;
	}
}