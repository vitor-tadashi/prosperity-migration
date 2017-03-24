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
	private final String teste = "select * from wp_rg_lead_detail WHERE lead_id between 3337 and 3360 ";
	
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

	public CandidatoWordPressBean getBean() {
		CandidatoWordPressBean candidato = new CandidatoWordPressBean();
		this.stmt = null;
		this.rs = null;

		try {
			conexao = ConnectionFactory.pegaConexao();
			stmt = conexao.createStatement();
			stmt.execute("select * from wp_rg_lead_detail where form_id = 4 and lead_id = 5591;");
			rs = stmt.getResultSet();

			/*
			 * if (stmt.execute(
			 * "select * from wp_rg_lead_detail where form_id = 4 and lead_id = 5591"
			 * )) { rs = stmt.getResultSet(); }
			 */

			while (rs.next()) {

				candidato.setIdWordpress(rs.getLong(1));
				candidato.setLead_id(rs.getLong(2));
				candidato.setForm_id(rs.getInt(3));
				candidato.setField_number(rs.getInt(4));

				// Método que seta o tipo do campo (isto é, "vaga", "nome
				// completo", "email", etc.) dependendo do número fornecido no arquivo json.candidato:
				candidato.setTipoCampo();

				candidato.setValorCampo(rs.getString(5));

				/*
				 * System.out.println(candidato.getField_number() + " : " +
				 * candidato.getTipoCampo() + " : " +
				 * candidato.getValorCampo());
				 */
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

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}
		return candidato;
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

			// Instancio o candidato bean
			CandidatoWordPressBean candidato = new CandidatoWordPressBean();

			while (this.rs.next()) {
				// Na primeira linha da busca, o field_number vale 1,
				// valor que corresponde ao tipoCampo nome completo do candidato
				if (this.rs.getInt(4) == 1) {
					candidato.setIdWordpress(this.rs.getLong(1));
					candidato.setLead_id(this.rs.getLong(2));
					candidato.setForm_id(this.rs.getInt(3));
					candidato.setField_number(this.rs.getInt(4));
					/*
					 * System.out.println(candidato.getField_number() + " : " +
					 * candidato.getTipoCampo() + " : " +
					 * candidato.getValorCampo());
					 */
				}

				// Aqui fora finalmente seto os valores que permanecem a ser
				// alterados:
				// TIPOCAMPO E VALORCAMPO (texto que está inserido dentro)

				// Método que seta o tipo do campo (isto é, "vaga", "nome
				// completo", "email", etc.)
				// dependendo do número fornecido no arquivo json.candidato:
				candidato.setTipoCampo();
				candidato.setValorCampo(this.rs.getString(5));

				// Finalmente adiciono o bean na lista de beans:

				listaCandidatos.add(candidato);

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