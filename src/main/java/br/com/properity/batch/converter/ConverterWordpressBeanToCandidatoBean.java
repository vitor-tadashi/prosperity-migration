package br.com.properity.batch.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.CandidatoBean;
import br.com.properity.batch.bean.WordpressBean;

public class ConverterWordpressBeanToCandidatoBean {
	
	public CandidatoBean transformaWordpressEmCandidato(WordpressBean w) {
		CandidatoBean candidato = new CandidatoBean();
		
		for (int i = 0; i < w.getTipoCampo().size(); i++) {
			if(w.getTipoCampo().get(i).equals("Cpf")) {
				candidato.setCpf(w.getValorCampo(i));
			}
			else if(w.getTipoCampo().get(i).equals("Nome")) {
				candidato.setNome(w.getValorCampo(i));
			}
			else if(w.getTipoCampo().get(i).equals("Data nascimento")) {
				
				DateFormat df = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
				Date result = null;
				try {
					result = df.parse(w.getValorCampo(i));
				} catch (ParseException e) {
					result = null;
					System.out.println("Erro na tentativa de conversão de data: " + e);
				}
				
				candidato.setDataNascimento(result);
			}
		}
		return candidato;
	}
	
	public List<CandidatoBean> transformaLista(List<WordpressBean> listaWordpress) {
		
		List<CandidatoBean> candidatos = new ArrayList<>();
		
		for (WordpressBean w : listaWordpress) {
			candidatos.add(this.transformaWordpressEmCandidato(w));
		}
		return candidatos;
	}
}
