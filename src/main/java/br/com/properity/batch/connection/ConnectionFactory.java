package br.com.properity.batch.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.properity.batch.bean.CandidatoBean;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class ConnectionFactory {

	public static Connection pegaConexao() {

		Connection con = null;

		try {
			// The newInstance() call is a work around for some
			// broken Java implementations

			con = DriverManager.getConnection("jdbc:mysql://mysql01.verity3.hospedagemdesites.ws/verity31", "verity31",
					"novosite2015");

			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("Erro: " + ex);
			con = null;
		}

		return con;
	}
}
