package br.com.prosperity.batch;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.batch.item.ItemWriter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import br.com.properity.batch.bean.WordpressBean;

public class CustomItemWriter implements ItemWriter<WordpressBean> {

	public void write(WordpressBean w) throws Exception {

		final String url = "localhost:8080/can/servico";

		w.getCandidatos();

		// Get data from the server
		Client client = Client.create();
		WebResource resource = client.resource(url);
		
		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("name1", "val1");
		formData.add("name2", "val2");
		ClientResponse response = resource.type("application/x-www-form-urlencoded").post(ClientResponse.class,
				formData);

	}

	@Override
	public void write(List<? extends WordpressBean> arg0) throws Exception {
	}

}
