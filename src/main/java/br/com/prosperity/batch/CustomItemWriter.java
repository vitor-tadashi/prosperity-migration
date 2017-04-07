package br.com.prosperity.batch;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.glassfish.jersey.client.ClientResponse;
import org.springframework.batch.item.ItemWriter;
import org.w3c.dom.Document;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.prosperity.batch.bean.WordpressBean;

public class CustomItemWriter implements ItemWriter<WordpressBean> {

	//@Produces({ MediaType.APPLICATION_XML })
	public void writeTo(WordpressBean w) throws Exception {
		System.out.println("ESCREVER LEGAL");

		if(w.getCandidatos().isEmpty())
			return;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(WordpressBean.class);
			
			  // Create the Document
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document document = db.newDocument();
	        
			// serialize the entity myBean to the entity output stream
			jaxbContext.createMarshaller().marshal(w, document);
			
			TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer t = (Transformer) tf.newTransformer();
	        DOMSource source = new DOMSource(document);
	        StreamResult result = new StreamResult(System.out);
	        t.transform(source, result);
	        
	    	final String url = "http://localhost:8080/servico";

/*			// Get data from the server
			Client client = Client.create();
			WebResource resource = client.resource(url);
			resource.post(result);*/
	    	Client client = Client.create();
	    	WebResource webResource = client.resource(url);

	        ClientResponse response = webResource.accept("application/xml")
	                .type("application/xml").post(ClientResponse.class, source);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write(List<? extends WordpressBean> listaWordpress) throws Exception {
		writeTo(listaWordpress.get(0));
	}
}
