package br.com.properity.batch.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultadoSQL {
	public WordpressBean mapRow(ResultSet rs, int rowNum) throws SQLException {

		WordpressBean candidato = new WordpressBean();
		candidato.setIdWordpress(rs.getLong(0));
		candidato.setLead_id(rs.getLong(1));
		candidato.setForm_id(rs.getInt(2));
		candidato.setField_number(rs.getInt(3));
		candidato.setValorCampo(rs.getString(4));
		candidato.setTipoCampo();

		System.out.println(candidato.getIdWordpress());
		/*
		 * System.out.println(candidato.getId());
		 * System.out.println(candidato.getId());
		 * System.out.println(candidato.getId());
		 * System.out.println(candidato.getId());
		 * System.out.println(candidato.getId());
		 */

		return candidato;
	}

}
