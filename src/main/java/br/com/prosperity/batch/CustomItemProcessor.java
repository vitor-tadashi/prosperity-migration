package br.com.prosperity.batch;

import org.springframework.batch.item.ItemProcessor;
import br.com.properity.batch.bean.*;

public class CustomItemProcessor implements ItemProcessor<WordpressBean, WordpressBean> {

	@Override
	public WordpressBean process(WordpressBean w) throws Exception {
		return w;
	}

}