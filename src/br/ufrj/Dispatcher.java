package br.ufrj;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher {
	Object instance;
	
	
	public Dispatcher(String nomeClasse, HttpServletRequest request, HttpServletResponse response ) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> c = Class.forName( nomeClasse );
        Constructor<?> construtor = c.getConstructor(HttpServletRequest.class, HttpServletResponse.class);
        this.instance = construtor.newInstance(request, response);
	}

    public Method buscaMetodo(String nomeMetodo ) {
    	Method[] metodos = this.instance.getClass().getDeclaredMethods();
    	for(Method met : metodos){
    		if(met.getName().equals(nomeMetodo)){
    			return met;
    		}
    	}
        return null;
    }

    public Object instanciaForm( Method m ) throws InstantiationException, IllegalAccessException {
    	Class<?>[] tipos = m.getParameterTypes();
        if(tipos.length > 0){
        	if(m.getParameterTypes()[0] == Integer.class){
        		return "";
        	}else{
        		return m.getParameterTypes()[0].newInstance();
        	}
        }
        return null;
    }
    
    private Map<String, Method> buscaSetters( Class<?> classe ) {
    	Method[] metodos = classe.getDeclaredMethods();
    	Map<String, Method> setters = new HashMap<String, Method>();
    	
    	for(Method met : metodos){
    		if(met.getName().startsWith("set")){
    			setters.put(met.getName().substring(3), met);
    		}
    	}
        return setters;
    }
    
    public void executaMetodo(Method met, Object form, Map<String, String> params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ObrigatorioException{
    	this.preencheForm(form, params);
    	if(this.validaForm(form)){
    		System.out.println("deu true");
        	met.invoke(this.instance, form);
    	}else{
    		throw new ObrigatorioException("Por favor, preencha todos os campos obrigatórios.");
    	}
    }
    
    public void executaMetodo(Method met) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	met.invoke(this.instance);
    }
    
    public void executaMetodo(Method met, String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	met.invoke(this.instance, id);
    }
    
    //valida baseado nos annotations
    public Boolean validaForm(Object form) throws IllegalArgumentException, IllegalAccessException{
    	for(Field field : form.getClass().getDeclaredFields()){
			  Obrigatorio obg = field.getAnnotation(Obrigatorio.class);
			  field.setAccessible(true);
			  if(obg.value() == true){
				  if(field.get(form) == "" || field.get(form) == null){
					  return false;
				  }
			  }
		}
    	return true;
    }
    
    public void preencheForm( Object form, Map<String, String> parametros ) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ObrigatorioException {
        Map<String, Method> setters = buscaSetters( form.getClass() );
        
        for (Map.Entry<String, String> par : parametros.entrySet()){
        	//pega o método através da chave contida no Map de parametros e invoka com o valor
        	try{
        		 Method m = setters.get(par.getKey());
                 m.invoke(form, par.getValue());
        	}catch(Exception e){}
        }
    }
}
