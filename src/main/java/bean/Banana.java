package bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Banana {
	private String bar;

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}
	
	
}
