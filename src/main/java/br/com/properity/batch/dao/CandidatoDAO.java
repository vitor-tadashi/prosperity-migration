package br.com.properity.batch.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.properity.batch.bean.CandidatoBean;
import br.com.properity.batch.connection.ConnectionFactory;

public class CandidatoDAO {

	private CandidatoBean candidato = new CandidatoBean();
	private Connection conexao;

	public CandidatoBean getBean() {

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
		return this.candidato;
	}
}
