package br.com.properity.batch.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import bean.CandidatoBean;

@XmlRootElement(name = "meuXml")
public class WordpressBean {
	private List<CandidatoWordPressBean> candidatosWordPress;
	private List<CandidatoBean> candidatos;

	public WordpressBean(List<CandidatoBean> candidatos) {
		super();
		this.candidatos = candidatos;
	}

	public WordpressBean() {
	}

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