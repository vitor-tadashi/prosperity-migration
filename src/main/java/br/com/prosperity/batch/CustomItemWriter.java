package br.com.prosperity.batch;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.batch.item.ItemWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.prosperity.batch.bean.WordpressBean;

public class CustomItemWriter implements ItemWriter<WordpressBean> {

	private final String URL_SERVICO_PROSPERITY = "http://localhost:8080/servico";

	@Override
	public void write(List<? extends WordpressBean> listaWordpress) throws Exception {
		generateXML(listaWordpress.get(0));
	}

	@Path(value = URL_SERVICO_PROSPERITY)
	@POST
	@Produces("application/xml")
	public String generateXML(WordpressBean w) throws Exception {
		XStream stream = new XStream(new DomDriver());

		String xml = stream.toXML(w);

		String xmlConsertada = removerCaracteres(xml);
		
		//System.out.println(xmlConsertada);

		return xmlConsertada;
	}

	public String removerCaracteres(String xml) {

		String xmlConsertada = xml;

		xmlConsertada = xml.replaceAll("&#xd;", " ");

		return xmlConsertada;
	}
}