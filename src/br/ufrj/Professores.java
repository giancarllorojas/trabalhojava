package br.ufrj;

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
	File arquivo = new File("/home/defense/workspace/trabalhofinal/csvs/professores.csv");
	
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
            String prof_id = dados[0];
            String prof_nome = dados[1];
            String prof_email = dados[2];
            formProfessor form = new formProfessor();
            form.setId(prof_id);
            form.setNome(prof_nome);
            form.setEmail(prof_email);
            this.professores.add(form);
        }
        scan.close();
	}
	
	private void SalvaDados() throws IOException{
		String dados = "";
		for(formProfessor p : this.professores){
			dados += p.getId() + "," + p.getNome() + "," + p.getEmail() + "\n";
		}
		FileWriter fw = new FileWriter(this.arquivo.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(dados);
		bw.close();
	}
	
	private String pegaUltimoId(){
		if (this.professores != null && !this.professores.isEmpty()) {
			return this.professores.get(this.professores.size() - 1).id;
		}else{
			return "0";
		}
	}
	
	//pega professor pelo id
	public formProfessor getProfessor(String prof_id){
		for(formProfessor f: this.professores){
			if(f.getId().equals(prof_id)){
				return f;
			}
		}
		return null;
	}
	
	/*
	 * Insere novo professor no ArrayList professores e salva os dados no CSV
	 */
	public void Insere(formProfessor f) throws IOException{
		Integer id = Integer.valueOf(this.pegaUltimoId()) + 1;
		f.setId(id.toString());
		this.professores.add(f);
		this.SalvaDados();
		
		response.sendRedirect(request.getRequestURL() + "?control=Professores&action=Exibir");
		return;
	}
	
	/*
	 * Remove professor dado um nome
	 */
	public void Deleta(String prof_id) throws IOException{
		formProfessor p = this.getProfessor(prof_id);
		if(p != null){
			this.professores.remove(this.professores.indexOf(p));
			this.SalvaDados();
		}
		System.out.println(prof_id);
		response.sendRedirect(request.getRequestURL() + "?control=Professores&action=Exibir");
		return;
	}
	
	/*
	 * Altera professor
	 */
	public void Altera(formProfessor f) throws IOException{
		formProfessor p = this.getProfessor(f.getId());
		if(p != null){
			this.professores.set(this.professores.indexOf(p), f);
			this.SalvaDados();
		}
		
		System.out.println(f.id + f.nome + f.email);
		response.sendRedirect(request.getRequestURL() + "?control=Professores&action=Exibir");
		return;
	}
	
	/*
	 * Classes de exibição das págians
	 * */
	public void Exibir() throws ServletException, IOException{
		this.request.setAttribute("profs", this.professores);
		this.request.getRequestDispatcher( "/professores/exibir.jsp" ).forward( request, response );
	}
	
	public void Alterar(String prof_id) throws ServletException, IOException{
		formProfessor p = this.getProfessor(prof_id);
		this.request.setAttribute("professor", p);
		this.request.getRequestDispatcher( "/professores/alterar.jsp" ).forward( request, response );
	}
	
	public void Inserir() throws ServletException, IOException{
		this.request.getRequestDispatcher( "/professores/inserir.jsp" ).forward( request, response );
	}
}