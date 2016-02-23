package modelo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher {

    public Object cria( String nomeClasse, HttpServletRequest request, HttpServletResponse response ) throws Exception {
        Class<?> c = Class.forName( nomeClasse );
        Constructor<?> cs = c.getConstructor(HttpServletRequest.class, HttpServletResponse.class);

        return cs.newInstance(request, response);
    }

    public Method buscaMetodo( Class<?> classe, String nomeMetodo ) {
        // TODO Auto-generated method stub
    	Method[] metodos = classe.getDeclaredMethods();
    	for(Method met : metodos){
    		if(met.getName().equals(nomeMetodo)){
    			return met;
    		}
    	}
        return null;
    }

    public Object instanciaForm( Method m ) throws InstantiationException, IllegalAccessException {
        // TODO Auto-generated method stub
    	Class<?>[] tipos = m.getParameterTypes();
        if(tipos.length > 0){
        	return m.getParameterTypes()[0].newInstance();
        }
        return null;
    }
    
    private Map<String, Method> buscaSetters( Class<?> classe ) {
    	Method[] metodos = classe.getDeclaredMethods();
    	Map<String, Method> setters = new HashMap<String, Method>();
    	
    	for(Method met : metodos){
    		if(met.getName().startsWith("set")){
    			//coloca o nome sem o "set"
    			setters.put(met.getName().substring(3), met);
    		}
    	}
        return setters;
    }
    
    public void preencheForm( Object form, Map<String, String> parametros ) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
