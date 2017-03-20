package br.com.properity.batch.converter;

import java.util.ArrayList;
import java.util.List;

import bean.CandidatoBean;
import br.com.properity.batch.bean.WordpressBean;

public class ConverterWordpressBeanToCandidatoBean {
	
	public CandidatoBean transformaWordpressEmCandidato(WordpressBean w) {
		CandidatoBean candidato = new CandidatoBean();
		
		
		return candidato;
	}
	
	public List<CandidatoBean> transformaLista(List<WordpressBean> listaWordpress) {
		
		List<CandidatoBean> candidatos = new ArrayList<>();
		
		for (WordpressBean w : listaWordpress) {
			
		}
		return candidatos;
	}
}
