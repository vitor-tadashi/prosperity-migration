package br.com.prosperity.batch.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.prosperity.bean.CandidatoBean;

@XmlRootElement(name = "WordpressBean")
public class WordpressBean {
	
	private List<CandidatoBean> candidatos;
	
	@JsonIgnore
	private List<CandidatoWordPressBean> candidatosWordPress = new ArrayList<>();

	public WordpressBean(List<CandidatoBean> candidatos) {
		super();
		this.candidatos = candidatos;
	}

	public WordpressBean() {
	}

	@XmlElement(type=CandidatoBean.class)
	public List<CandidatoBean> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<CandidatoBean> candidatos) {
		this.candidatos = candidatos;
	}

    @XmlTransient
	public List<CandidatoWordPressBean> getCandidatosWordPress() {
		return candidatosWordPress;
	}

	public void setCandidatosWordPress(List<CandidatoWordPressBean> candidatosWordPress) {
		this.candidatosWordPress = candidatosWordPress;
	}
	
	
}