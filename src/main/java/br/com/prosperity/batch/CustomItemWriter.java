package br.com.prosperity.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import br.com.prosperity.batch.bean.WordpressBean;

public class CustomItemWriter implements ItemWriter<WordpressBean>{
	
	
	public void writeTo(WordpressBean w) throws Exception {

//		System.out.println("ESCREVER LEGAL");
//
//		try {
//			JAXBContext jaxbContext = JAXBContext.newInstance(WordpressBean.class);
//			
//			  // Create the Document
//	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//	        DocumentBuilder db = dbf.newDocumentBuilder();
//	        Document document = db.newDocument();
//	        
//			// serialize the entity myBean to the entity output stream
//			jaxbContext.createMarshaller().marshal(w, document);
//			
//			TransformerFactory tf = TransformerFactory.newInstance();
//	        Transformer t = tf.newTransformer();
//	        DOMSource source = new DOMSource(document);
//	        StreamResult result = new StreamResult(System.out);
//	        t.transform(source, result);
//	        
//	    	final String url = "localhost:8080/servico/candidato-servico";
//
//			// Get data from the server
//			Client client = Client.create();
//			WebResource resource = client.resource(url);
//
//			resource.post(result);
//			
//		} catch (JAXBException jaxbException) {
//			throw new ProcessingException("Error serializing a MyBean to the output stream", jaxbException);
//		}
	}

	@Override
	public void write(List<? extends WordpressBean> arg0) throws Exception {

		writeTo(arg0.get(0));
		
	}
}
