package br.com.properity.batch.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import bean.CandidatoBean;

@XmlRootElement
public class WordpressBean {
	
	private List<CandidatoBean> candidatos;

	public WordpressBean(List<CandidatoBean> candidatos) {
		super();
		this.candidatos = candidatos;
	}
	
	// Construtor vazio necess�rio para a deserializa��o pelo JAXB
	public WordpressBean() {
		
	}

	public List<CandidatoBean> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<CandidatoBean> candidatos) {
		this.candidatos = candidatos;
	}
}
