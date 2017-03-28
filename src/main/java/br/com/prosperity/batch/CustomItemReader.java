package br.com.prosperity.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import br.com.properity.batch.bean.WordpressBean;
import br.com.properity.batch.business.CandidatoBusiness;

public class CustomItemReader implements ItemReader<WordpressBean> {

	@Override
	public WordpressBean read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		WordpressBean wordpressBean = new WordpressBean();

		wordpressBean.setCandidatosWordPress(new CandidatoBusiness().listar());
		
		return wordpressBean;
	}
}