package br.com.properity.batch.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultadoSQL {
	public CandidatoBean mapRow(ResultSet rs, int rowNum) throws SQLException {

		CandidatoBean candidato = new CandidatoBean();
		candidato.setId(rs.getLong(0));
		candidato.setLead_id(rs.getLong(1));
		candidato.setForm_id(rs.getInt(2));
		candidato.setField_number(rs.getInt(3));
		candidato.setValorCampo(rs.getString(4));
		candidato.setTipoCampo();

		System.out.println(candidato.getId());
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
