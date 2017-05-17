package br.com.prosperity.batch.business;

import java.util.List;

import br.com.prosperity.batch.bean.CandidatoWordPressBean;
import br.com.prosperity.batch.dao.CandidatoDAO;

public class CandidatoBusiness {
	private CandidatoDAO candidatoDAO;
	
	// Isso é uma forma manual de fazer AutoWired:
	public void setCandidatoDAO(CandidatoDAO candidatoDAO) {
		this.candidatoDAO = candidatoDAO;
	}

	public List<CandidatoWordPressBean> listar() {
		return candidatoDAO.listar();
	}
}
