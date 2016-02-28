package br.ufrj;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Disciplinas
 */
@WebServlet({ "/go", "/go/" })
public class go extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public go() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String classe = "br.ufrj." + request.getParameter("control");
        String metodo = request.getParameter("action");
        String id = request.getParameter("Id");
        Map<String, String> params = this.getParametros(request);

        try {
            Dispatcher d = new Dispatcher(classe, request, response);
        	Method m = d.buscaMetodo(metodo);
        	Object form = d.instanciaForm( m );
            request.getRequestDispatcher("/include.jsp").include(request, response);	
        	if(form != null){
        		if(form.getClass().getName().contains("form")){
        			d.executaMetodo(m, form, params);
        		}else{
        			d.executaMetodo(m, id);
        		}
        	}else{
        		d.executaMetodo(m);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private Map<String, String> getParametros(HttpServletRequest request){
        Map<String, String> params = new HashMap<String, String>();
        Enumeration<String> param_names = request.getParameterNames();
        while(param_names.hasMoreElements()){
        	String paramName = param_names.nextElement();
        	if(!(paramName.equals("action") || paramName.equals("control"))){
        		params.put(paramName, request.getParameter(paramName));
        	}
        }
        return params;
	}

}
