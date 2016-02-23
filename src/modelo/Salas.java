package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Classe que age como uma espécie de "banco de dados", da load nas informações no CSV e tem métodos de CRUD para essas informações 
 */

public class Salas {
	/*
	 * Inicializando as variaveis que irão guardar os dados dos csv's na memória durante a execução da aplicação
	 */
	ArrayList<formSala> salas = new ArrayList<formSala>();
	HttpServletRequest request;
	HttpServletResponse response;
	File arquivo = new File("/home/defense/workspace/trabp2/data/salas.csv");
	
	public Salas(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException{
		this.CarregaDados();
		this.request = request;
		this.response = response;
	}
	
	private void CarregaDados() throws FileNotFoundException{
		Scanner scan = new Scanner(arquivo);
		scan.useDelimiter(",");
		while(scan.hasNextLine()){
			String linha = scan.nextLine();
            String[] dados = linha.split(",");
            String sala_numero = dados[0];
            String sala_capacidade = dados[1];
            formSala form = new formSala();
            form.setNumero(sala_numero);
            form.setCapacidade(sala_capacidade);
            this.salas.add(form);
        }
        scan.close();
	}
	
	private void SalvaDados() throws IOException{
		String dados = "";
		for(formSala s : this.salas){
			dados += s.getNumero() + "," + s.getCapacidade() + "\n";
		}
		FileWriter fw = new FileWriter(this.arquivo.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(dados);
		bw.close();
	}
	
	//pega sala pelo numero
	public formSala getSala(String sala_numero){
		for(formSala f: this.salas){
			if(f.getNumero().equals(sala_numero)){
				return f;
			}
		}
		return null;
	}
	
	/*
	 * Insere nova Sala no ArrayList salase salva os dados no CSV
	 */
	public void Insere(formSala f) throws IOException{
		this.salas.add(f);
		this.SalvaDados();
		
		response.sendRedirect("/trabp2/do/?control=Salas&action=Exibir");
		return;
	}
	
	/*
	 * Remove sala dado um numero
	 */
	public void Deleta(formSala f) throws IOException{
		formSala s = this.getSala(f.getNumero());
		if(s != null){
			this.salas.remove(this.salas.indexOf(s));
			this.SalvaDados();
		}
		
		response.sendRedirect("/trabp2/do/?control=Salas&action=Exibir");
		return;
	}
	
	/*
	 * Altera sala
	 */
	public void Altera(formSala f) throws IOException{
		formSala s = this.getSala(f.getNumero());
		if(s != null){
			this.salas.set(this.salas.indexOf(s), f);
			this.SalvaDados();
		}
		
		response.sendRedirect("/trabp2/do/?control=Salas&action=Exibir");
		return;
	}
	
	/*
	 * Classes de exibição das págians
	 * */
	public void Exibir() throws ServletException, IOException{
		this.request.setAttribute("salas", this.salas);
		this.request.getRequestDispatcher( "/salas/exibir.jsp" ).forward( request, response );
	}
	
	public void Alterar(formSala f) throws ServletException, IOException{
		formSala s = this.getSala(f.getNumeroAntigo());
		this.request.setAttribute("sala", s);
		this.request.getRequestDispatcher( "/salas/alterar.jsp" ).forward( request, response );
	}
	
	public void Inserir() throws ServletException, IOException{
		this.request.getRequestDispatcher( "/salas/inserir.jsp" ).forward( request, response );
	}
}