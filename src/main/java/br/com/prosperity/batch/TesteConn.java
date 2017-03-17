package br.com.prosperity.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.properity.batch.bean.CandidatoBean;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class TesteConn {
	public static void main(String[] args) {

		Connection con = null;

		try {
			// The newInstance() call is a work around for some
			// broken Java implementations

			con = DriverManager.getConnection("jdbc:mysql://mysql01.verity3.hospedagemdesites.ws/verity31", "verity31",
					"novosite2015");

			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("Erro: " + ex);
		}

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			stmt.execute("select * from wp_rg_lead_detail where form_id = 4 and lead_id = 5591;");
			rs = stmt.getResultSet();

			/*
			 * if (stmt.execute(
			 * "select * from wp_rg_lead_detail where form_id = 4 and lead_id = 5591"
			 * )) { rs = stmt.getResultSet(); }
			 */

			while (rs.next()) {
				CandidatoBean candidato = new CandidatoBean();

				candidato.setId(rs.getLong(1));
				candidato.setLead_id(rs.getLong(2));
				candidato.setForm_id(rs.getInt(3));
				candidato.setField_number(rs.getInt(4));
				candidato.setTipoCampo();

				candidato.setValorCampo(rs.getString(5));

				System.out.println(candidato.getField_number() + " : " + candidato.getTipoCampo() + " : "
						+ candidato.getValorCampo());
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
	}
}
