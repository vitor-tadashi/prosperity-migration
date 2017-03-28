package br.com.prosperity.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import br.com.prosperity.batch.bean.WordpressBean;
import br.com.prosperity.batch.business.CandidatoBusiness;

public class CustomItemReader implements ItemReader<WordpressBean> {

	private CandidatoBusiness candidatoBusiness;
	
	public void setCandidatoBusiness(CandidatoBusiness candidatoBusiness) {
		this.candidatoBusiness = candidatoBusiness;
	}
	
	@Override
	public WordpressBean read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		WordpressBean wordpressBean = new WordpressBean();

		wordpressBean.setCandidatosWordPress(candidatoBusiness.listar());
		
		return wordpressBean;
	}
}