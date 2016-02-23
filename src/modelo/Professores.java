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

public class Professores {
	/*
	 * Inicializando as variaveis que irão guardar os dados dos csv's na memória durante a execução da aplicação
	 */
	ArrayList<formProfessor> professores = new ArrayList<formProfessor>();
	HttpServletRequest request;
	HttpServletResponse response;
	File arquivo = new File("/home/defense/workspace/trabp2/data/professores.csv");
	
	public Professores(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException{
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
            String prof_nome = dados[0];
            String prof_email = dados[1];
            formProfessor form = new formProfessor();
            form.setNome(prof_nome);
            form.setEmail(prof_email);
            this.professores.add(form);
        }
        scan.close();
	}
	
	private void SalvaDados() throws IOException{
		String dados = "";
		for(formProfessor p : this.professores){
			dados += p.getNome() + "," + p.getEmail() + "\n";
		}
		FileWriter fw = new FileWriter(this.arquivo.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(dados);
		bw.close();
	}
	
	//pega professor pelo nome
	public formProfessor getProfessor(String prof_nome){
		for(formProfessor f: this.professores){
			if(f.getNome().equals(prof_nome)){
				return f;
			}
		}
		return null;
	}
	
	/*
	 * Insere novo professor no ArrayList professores e salva os dados no CSV
	 */
	public void Insere(formProfessor f) throws IOException{
		this.professores.add(f);
		this.SalvaDados();
		
		response.sendRedirect("/trabp2/do/?control=Professores&action=Exibir");
		return;
	}
	
	/*
	 * Remove professor dado um nome
	 */
	public void Deleta(formProfessor f) throws IOException{
		formProfessor p = this.getProfessor(f.getNome());
		if(p != null){
			this.professores.remove(this.professores.indexOf(p));
			this.SalvaDados();
		}
		
		response.sendRedirect("/trabp2/do/?control=Professores&action=Exibir");
		return;
	}
	
	/*
	 * Altera professor
	 */
	public void Altera(formProfessor f) throws IOException{
		formProfessor p = this.getProfessor(f.getNome());
		if(p != null){
			this.professores.set(this.professores.indexOf(p), f);
			this.SalvaDados();
		}
		
		response.sendRedirect("/trabp2/do/?control=Professores&action=Exibir");
		return;
	}
	
	/*
	 * Classes de exibição das págians
	 * */
	public void Exibir() throws ServletException, IOException{
		this.request.setAttribute("profs", this.professores);
		this.request.getRequestDispatcher( "/professores/exibir.jsp" ).forward( request, response );
	}
	
	public void Alterar(formProfessor f) throws ServletException, IOException{
		formProfessor p = this.getProfessor(f.getNomeAntigo());
		this.request.setAttribute("professor", p);
		this.request.getRequestDispatcher( "/professores/alterar.jsp" ).forward( request, response );
	}
	
	public void Inserir() throws ServletException, IOException{
		this.request.getRequestDispatcher( "/professores/inserir.jsp" ).forward( request, response );
	}
}