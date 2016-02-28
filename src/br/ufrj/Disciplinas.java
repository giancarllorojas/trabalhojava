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

public class Disciplinas {
	/*
	 * Inicializando as variaveis que irão guardar os dados dos csv's na memória durante a execução da aplicação
	 */
	ArrayList<formDisciplina> disciplinas = new ArrayList<formDisciplina>();
	HttpServletRequest request;
	HttpServletResponse response;
	File arquivo = new File("/home/defense/workspace/trabalhofinal/csvs/disciplinas.csv");
	
	public Disciplinas(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException{
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
            String disc_id = dados[0];
            String disc_codigo = dados[1];
            String disc_nome = dados[2];
            String disc_ementa = dados[3];
            formDisciplina form = new formDisciplina();
            form.setId(disc_id);
            form.setCodigo(disc_codigo);
            form.setNome(disc_nome);
            form.setEmenta(disc_ementa);
            disciplinas.add(form);
        }
        scan.close();
	}
	
	private void SalvaDados() throws IOException{
		String dados = "";
		for(formDisciplina d : disciplinas){
			dados += d.id + "," + d.codigo.toString() + "," + d.nome + "," + d.ementa + "\n";
		}
		FileWriter fw = new FileWriter(arquivo.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(dados);
		bw.close();
	}
	
	private String pegaUltimoId(){
		if (this.disciplinas != null && !this.disciplinas.isEmpty()) {
			return this.disciplinas.get(this.disciplinas.size() - 1).id;
		}else{
			return "0";
		}
	}
	
	//Pega disciplina pelo ID
	public formDisciplina getDisciplina(String string){
		for(formDisciplina d: disciplinas){
			if(d.id.equals(string)){
				return d;
			}
		}
		return null;
	}
	
	/*
	 * Métodos do CRUD - insere, deleta e altera
	 * todos retornam pra principal(exibir.jsp) após conclusão ou erro
	 */
	
	public void Insere(formDisciplina f) throws IOException{
		Integer id = Integer.valueOf(this.pegaUltimoId()) + 1;
		f.setId(id.toString());
		this.disciplinas.add(f);
		this.SalvaDados();
		
		response.sendRedirect(request.getRequestURL() + "?control=Disciplinas&action=Exibir");
		return;
	}
	
	public void Deleta(String disc_id) throws IOException, ServletException{
		formDisciplina d = this.getDisciplina(disc_id);
		if(d != null){
			this.disciplinas.remove(disciplinas.indexOf(d));
			this.SalvaDados();
		}
		response.sendRedirect(request.getRequestURL() + "?control=Disciplinas&action=Exibir");
		return;
	}
	
	public void Altera(formDisciplina f) throws IOException, ServletException{
		formDisciplina d = this.getDisciplina(f.getId());
		if(d != null){
			disciplinas.set(disciplinas.indexOf(d), f);
			this.SalvaDados();
		}
		
		
		response.sendRedirect(request.getRequestURL() + "?control=Disciplinas&action=Exibir");
		return;
	}
	
	/*
	 * Classes de exibição das págians
	 * */
	public void Exibir() throws ServletException, IOException{
		this.request.setAttribute("discs", this.disciplinas);
		
		this.request.getRequestDispatcher( "/disciplinas/exibir.jsp" ).forward( request, response );
	}
	
	public void Alterar(String disc_id) throws ServletException, IOException{
		formDisciplina d = this.getDisciplina(disc_id);
		this.request.setAttribute("disciplina", d);
		this.request.getRequestDispatcher( "/disciplinas/alterar.jsp" ).forward( request, response );
	}
	
	public void Inserir() throws ServletException, IOException{
		this.request.getRequestDispatcher( "/disciplinas/inserir.jsp" ).forward( request, response );
	}
}