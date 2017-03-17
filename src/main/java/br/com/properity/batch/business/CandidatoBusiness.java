package br.com.properity.batch.business;

import java.util.ArrayList;
import java.util.List;

import br.com.properity.batch.bean.CandidatoBean;
import br.com.properity.batch.dao.CandidatoDAO;

public class CandidatoBusiness {
	private CandidatoDAO candidatoDao = new CandidatoDAO();
	private List<CandidatoBean> listaCandidatos = new ArrayList<>();
	
	public void mostrarLista() {
		listaCandidatos = candidatoDao.listar();
		
		for (CandidatoBean candidatoBean : listaCandidatos) {
			System.out.println("Id do cara" + candidatoBean.getLead_id() + " nome do cara: " + candidatoBean.getValorCampo(1));
		}
	}
}
