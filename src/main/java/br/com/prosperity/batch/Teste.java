package br.com.prosperity.batch;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.glassfish.jersey.client.ClientResponse;
import org.w3c.dom.Document;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import bean.CandidatoBean;
import br.com.properity.batch.bean.WordpressBean;
import br.com.properity.batch.business.CandidatoBusiness;

public class Teste {

	public static void main(String[] args) throws Exception {
		WordpressBean wordpressBean = new WordpressBean();
		CandidatoBusiness b = new CandidatoBusiness();

		wordpressBean.setCandidatos(b.retornaListaBean());

		for (CandidatoBean a : wordpressBean.getCandidatos()) {
			System.out.println(a.getNome());
		}
		System.out.println("ESCREVER LEGAL");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(WordpressBean.class);

			// Create the Document
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.newDocument();

			// serialize the entity myBean to the entity output stream
			jaxbContext.createMarshaller().marshal(wordpressBean, document);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(System.out);
			t.transform(source, result);

			final String url = "http://http://localhost:2222/servico/can";

			// Get data from the server
			Client client = Client.create();
			WebResource resource = client.resource(url);

			ClientResponse response = resource.type(MediaType.APPLICATION_XML).post(ClientResponse.class,
					wordpressBean);

			resource.post(response);

		} catch (JAXBException jaxbException) {
			throw new ProcessingException("Error serializing a MyBean to the output stream", jaxbException);
		}
	}
}