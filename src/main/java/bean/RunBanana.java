package bean;
import javax.xml.bind.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class RunBanana {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		  JAXBContext jc = JAXBContext.newInstance(Banana.class);

	        // Create the Object
	        Banana foo = new Banana();
	        // foo.setBar("Hello World");

	        // Create the Document
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document document = db.newDocument();

	        // Marshal the Object to a Document
	        Marshaller marshaller = (Marshaller) jc.createMarshaller();
	        marshaller.marshal(foo, document);

	        // Output the Document
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer t = tf.newTransformer();
	        DOMSource source = new DOMSource(document);
	        StreamResult result = new StreamResult(System.out);
	        t.transform(source, result);

	}

}
