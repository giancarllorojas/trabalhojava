package controladores;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ObrigatorioException;


/**
 * Servlet implementation class Disciplinas
 */
@WebServlet({ "/do", "/do/" })
public class Framew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Framew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		modelo.Dispatcher dis = new modelo.Dispatcher();
        String classe = "modelo." + request.getParameter("control");
        String metodo = request.getParameter("action");
        
        //pega os parametros que não são o "action" nem o "control"
        Map<String, String> parametros = new HashMap<String, String>();
        Enumeration<String> param_names = request.getParameterNames();
        while(param_names.hasMoreElements()){
        	String paramName = param_names.nextElement();
        	if(!(paramName.equals("action") || paramName.equals("control"))){
        		parametros.put(paramName, request.getParameter(paramName));
        	}
        }
        
        try {
        	Object o = dis.cria(classe, request, response);
        	Method m = dis.buscaMetodo(o.getClass(), metodo);
        	Object form = dis.instanciaForm( m );
        	
        	
            
        	if(form != null){
        		Map<String, Boolean> obrigatorios = dis.getObrigatorios(form);
        		dis.preencheForm(form, parametros);
        		for(Map.Entry<String, Boolean> par : obrigatorios.entrySet()){
        			Field f = form.getClass().getDeclaredField(par.getKey());
        			f.setAccessible(true);
                	if(f.get(form) == null){
                		throw new ObrigatorioException();
                	}
                }
            	m.invoke(o, form);
        	}else{
        		m.invoke(o);
        	}
        	
        	
        	//w.append(retorno.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
