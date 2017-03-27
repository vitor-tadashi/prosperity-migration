package br.com.properity.batch.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import bean.CandidatoBean;

@XmlRootElement(name = "meuXml")
public class WordpressBean {

	private List<CandidatoBean> candidatos = new ArrayList<>();

	public WordpressBean(List<CandidatoBean> candidatos) {
		super();
		this.candidatos = candidatos;
	}

	// Construtor vazio necessário para a deserialização pelo JAXB:
	public WordpressBean() {
	}

	public List<CandidatoBean> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<CandidatoBean> candidatos) {
		this.candidatos = candidatos;
	}
}