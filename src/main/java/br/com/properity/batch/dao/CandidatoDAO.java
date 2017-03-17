package br.com.properity.batch.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.properity.batch.bean.CandidatoBean;
import br.com.properity.batch.connection.ConnectionFactory;

public class CandidatoDAO {

	private List<CandidatoBean> listaCandidatos = new ArrayList<>();
	private Connection conexao;

	public CandidatoBean getBean() {
		CandidatoBean candidato = new CandidatoBean();
		Statement stmt = null;
		ResultSet rs = null;

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

				candidato.setId(rs.getLong(1));
				candidato.setLead_id(rs.getLong(2));
				candidato.setForm_id(rs.getInt(3));
				candidato.setField_number(rs.getInt(4));
				
				// Método que seta o tipo do campo (isto é, "vaga", "nome completo", "email", etc.)
				// dependendo do número fornecido no arquivo json.candidato:
				candidato.setTipoCampo();

				candidato.setValorCampo(rs.getString(5));

				/*System.out.println(candidato.getField_number() + " : " + candidato.getTipoCampo() + " : "
						+ candidato.getValorCampo());*/
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
	
	public List<CandidatoBean> listar() {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conexao = ConnectionFactory.pegaConexao();
			stmt = conexao.createStatement();
			stmt.execute("select * from wp_rg_lead_detail where form_id = 4");
			rs = stmt.getResultSet();

			/*
			 * if (stmt.execute(
			 * "select * from wp_rg_lead_detail where form_id = 4 and lead_id = 5591"
			 * )) { rs = stmt.getResultSet(); }
			 */

			// Instancio o candidato bean
			CandidatoBean candidato = new CandidatoBean();
			
			while (rs.next()) {
				// Na primeira linha da busca, o field_number vale 1,
				// valor que corresponde ao tipoCampo nome completo do candidato
				if(rs.getInt(4)==1)
				{
					candidato.setId(rs.getLong(1));
					candidato.setLead_id(rs.getLong(2));
					candidato.setForm_id(rs.getInt(3));
					candidato.setField_number(rs.getInt(4));
					/*System.out.println(candidato.getField_number() + " : " + candidato.getTipoCampo() + " : "
							+ candidato.getValorCampo());*/
				}
				
				// Aqui fora finalmente seto os valores que permanecem a ser alterados:
				// TIPOCAMPO E VALORCAMPO (texto que está inserido dentro)
				
				
				// Método que seta o tipo do campo (isto é, "vaga", "nome completo", "email", etc.)
				// dependendo do número fornecido no arquivo json.candidato:
				candidato.setTipoCampo();
				candidato.setValorCampo(rs.getString(5));
				
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
		return this.listaCandidatos;
	}
}
