package com.everis.mdeveris.generador.data;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Elemento {

	int id;

	String logic;
	String template;
	String filename;

	public String getLogic() {
		return logic;
	}
	
	@XmlElement
	public void setLogic(String logic) {
		this.logic = logic;
	}
	public String getTemplate() {
		return template;
	}
	
	@XmlElement
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public String getFilename() {
		return filename;
	}
	
	@XmlElement
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public int getId() {
		return id;
	}
	
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	
}
