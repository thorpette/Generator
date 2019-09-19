package com.everis.mdeveris.generador;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import com.everis.mdeveris.generador.data.Generador;

public class GeneradorEngine {
	
	private ScriptEngineManager engineManager;
	private ScriptEngine engine ;
	private Invocable invocable ;
	private Object dustjs;
	private Map<String,String> templates;
	private Generador generador;
	
	
	
	public GeneradorEngine() throws FileNotFoundException, ScriptException {
		super();
		engineManager = new ScriptEngineManager();
		engine = engineManager.getEngineByName("nashorn");
		
		engine.eval(new FileReader("src/main/resources/dust-full.js"));
		
		invocable = (Invocable) engine;
		dustjs = engine.eval("dust");
		
		
		templates=new HashMap<String,String>();
	}
	
	
	public Object generateData(String dataFile) {
		String content;
		try {
			Writer writer = new StringWriter();
			content = new String(Files.readAllBytes(Paths.get(dataFile)), "UTF-8");
	
			Bindings bindings = new SimpleBindings();
			bindings.put("writer", writer);
			ScriptEngine engineLogic = engineManager.getEngineByName("nashorn");
			engineLogic.getContext().setBindings(bindings, ScriptContext.ENGINE_SCOPE);
			engineLogic.eval(content);
			
			return writer.toString();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	public void registerTemplate(String name, String templateFile) {
		String content;
		try {
			content = new String(Files.readAllBytes(Paths.get(templateFile)), "UTF-8");
			templates.put(name, content);
			this.loadTemplate(name);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void executeSimpleTemplate(String template) throws ScriptException {
		
		
		this.registerTemplate(template, "src/main/resources/".concat(template).concat(".js"));
        
		
		String generado = this.renderTemplate(template,this.generateData("src/main/resources/data".concat(template).concat(".js")));
		
		String filename = this.generateFileName("writer.write('Test')");
		
		System.out.println(generado);
		
		System.out.println(filename);
	
	}
	
	

	
	public String renderTemplate(String template, Object variable) throws ScriptException{
		
		Writer writer = new StringWriter();

		String renderScript = ("{dust.render(template, " +
		    "JSON.parse(data), "
		    + "function(err,data) { "
		    + "if(err) { "
		        + "throw new Error(err);"
		    + "} "
		    + "else { "
		        + "writer.write( data, 0, data.length );"
		    + "}  "
		    + "});}");
		
		
		
		// Bind
		
		Bindings bindings = new SimpleBindings();
		bindings.put("template", template);
		bindings.put("data", variable);
		bindings.put("writer", writer);
		
		engine.getContext().setBindings(bindings, ScriptContext.GLOBAL_SCOPE);

		engine.eval(renderScript, engine.getContext());
		
	    return writer.toString();
	    
	    
		
		
	}
	
	
	public String generateFileName(String filename) {
		
		String content;
		try {
			Writer writer = new StringWriter();
			content = filename;
	
			Bindings bindings = new SimpleBindings();
			bindings.put("writer", writer);
			ScriptEngine engineLogic = engineManager.getEngineByName("nashorn");
			engineLogic.getContext().setBindings(bindings, ScriptContext.ENGINE_SCOPE);
			engineLogic.eval(content);
			
			return writer.toString();
			
		}  catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	
	public void loadTemplate(String name) throws NoSuchMethodException, ScriptException {
		String template = templates.get(name);
		Object compileTemplate = invocable.invokeMethod(dustjs, "compile",
		        template, name);
		Object loadedSource = invocable.invokeMethod(dustjs, "loadSource",
		        compileTemplate);
	}


	public Generador getGenerador() {
		return generador;
	}


	public void setGenerador(Generador generador) {
		this.generador = generador;
	}
	
	
	
}
