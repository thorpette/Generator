package com.everis.mdeveris.generador.data;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

public class Config {

	
	final static Logger LOGGER = Logger.getLogger(Config.class);
	
	private Generador generador;
	
	JAXBContext jaxbContext;
	
	
	public Config() throws JAXBException {
		super();
		jaxbContext = JAXBContext.newInstance(Generador.class);
	}

	
	public void loadConfiguration(String route) throws JAXBException, MalformedURLException {
		
		LOGGER.info("Inicio load configuration url:" + route);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		generador = (Generador) unmarshaller.unmarshal(new URL(route));
		LOGGER.info("Saliendo loadConfiguration url:" + route);
	}
	
	public void saveConfiguration(String route) throws JAXBException {
		File file = new File(route);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(generador, file);
	}


	public Generador getGenerador() {
		return generador;
	}


	public void setGenerador(Generador generador) {
		this.generador = generador;
	}
}
