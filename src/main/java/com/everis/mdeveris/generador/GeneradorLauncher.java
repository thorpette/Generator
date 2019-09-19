package com.everis.mdeveris.generador;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import javax.script.ScriptException;

public class GeneradorLauncher {

	public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException, MalformedURLException {
		
		GeneradorEngine engine = new GeneradorEngine();
		
		engine.registerTemplate("test", "src/main/resources/plantillaTest.js");
        
		
		String generado = engine.renderTemplate("test",engine.generateData("src/main/resources/dataTest.js"));
		
		String filename = engine.generateFileName("writer.write('Test')");
		
		System.out.println(generado);
		
		System.out.println(filename);
	/*
		Generador generador = new Generador();
		Elemento elemento = new Elemento();
		elemento.setLogic("src/main/resources/dataTest.js");
		elemento.setTemplate("src/main/resources/plantillaTest.js");
		elemento.setFilename("writer.write('Test')");
		elemento.setId(1);
		ArrayList<Elemento> elementos = new ArrayList<Elemento>();
		elementos.add(elemento);
		generador.setElementos(elementos);
		generador.setId(1);
		generador.setInit("test");
		generador.setEnd("testEnd");
	*/	
		
		/* try {
			 GeneradorEngine engine = new GeneradorEngine();
		      Config configuration = new Config();
		      configuration.loadConfiguration("file:///C:/desarrollo/workSpace-MDeveris/generador/src/main/resources/config.xml");
			  Generador generador = configuration.getGenerador();
              engine.setGenerador(generador);
              
			    System.out.println("Terminado");  
			        

			      } catch (JAXBException e) {
				e.printStackTrace();
			      }
*/
		
	}
	

}
