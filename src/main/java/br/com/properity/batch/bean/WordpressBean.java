package br.com.properity.batch.bean;

import java.util.List;

import bean.CandidatoBean;

public class WordpressBean {
	
	private List<CandidatoBean> candidatos;
	private List<CandidatoWordPressBean> candidatosWordPress;

	public List<CandidatoBean> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<CandidatoBean> candidatos) {
		this.candidatos = candidatos;
	}

	public List<CandidatoWordPressBean> getCandidatosWordPress() {
		return candidatosWordPress;
	}

	public void setCandidatosWordPress(List<CandidatoWordPressBean> candidatosWordPress) {
		this.candidatosWordPress = candidatosWordPress;
	}
}
