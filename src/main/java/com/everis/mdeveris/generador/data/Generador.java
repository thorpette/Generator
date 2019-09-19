package com.everis.mdeveris.generador.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Generador {
	
	int id;
	String init;
	List<Elemento> elementos;
	String end;
	
	public String getInit() {
		return init;
	}
	@XmlElement
	public void setInit(String init) {
		this.init = init;
	}
	
	public List<Elemento> getElementos() {
		return elementos;
	}
	
    @XmlElementWrapper
    @XmlElement(name="elemento")
	public void setElementos(List<Elemento> elementos) {
		this.elementos = elementos;
	}
	
	public String getEnd() {
		return end;
	}
	
	@XmlElement
	public void setEnd(String end) {
		this.end = end;
	}
	public int getId() {
		return id;
	}
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
   
}
