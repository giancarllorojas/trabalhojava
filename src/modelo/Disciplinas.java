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

public class Disciplinas {
	/*
	 * Inicializando as variaveis que irão guardar os dados dos csv's na memória durante a execução da aplicação
	 */
	ArrayList<formDisciplina> disciplinas = new ArrayList<formDisciplina>();
	HttpServletRequest request;
	HttpServletResponse response;
	File arquivo = new File("/home/defense/workspace/trabp2/data/disciplinas.csv");
	
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
            String disc_codigo = dados[0];
            String disc_nome = dados[1];
            String disc_ementa = dados[2];
            formDisciplina form = new formDisciplina();
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
			dados += d.codigo.toString() + "," + d.nome + "," + d.ementa + "\n";
		}
		FileWriter fw = new FileWriter(arquivo.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(dados);
		bw.close();
	}
	

	
	//pega disciplina pelo codigo
	public formDisciplina getDisciplina(String disc_cod){
		for(formDisciplina d: disciplinas){
			if(d.codigo.equals(disc_cod)){
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
		this.disciplinas.add(f);
		this.SalvaDados();
		
		response.sendRedirect("/trabp2/do/?control=Disciplinas&action=Exibir");
		return;
	}
	
	public void Deleta(formDisciplina f) throws IOException, ServletException{
		formDisciplina d = this.getDisciplina(f.codigo);
		if(d != null){
			this.disciplinas.remove(disciplinas.indexOf(d));
			this.SalvaDados();
		}
		
		response.sendRedirect("/trabp2/do/?control=Disciplinas&action=Exibir");
		return;
	}
	
	public void Altera(formDisciplina f) throws IOException, ServletException{
		formDisciplina d = this.getDisciplina(f.getCodAntigo());
		if(d != null){
			disciplinas.set(disciplinas.indexOf(d), f);
			this.SalvaDados();
		}
		
		response.sendRedirect("/trabp2/do/?control=Disciplinas&action=Exibir");
		return;
	}
	
	/*
	 * Classes de exibição das págians
	 * */
	public void Exibir() throws ServletException, IOException{
		this.request.setAttribute("discs", this.disciplinas);
		this.request.getRequestDispatcher( "/disciplinas/exibir.jsp" ).forward( request, response );
	}
	
	public void Alterar(formDisciplina f) throws ServletException, IOException{
		formDisciplina d = this.getDisciplina(f.getCodAntigo());
		this.request.setAttribute("disciplina", d);
		this.request.getRequestDispatcher( "/disciplinas/alterar.jsp" ).forward( request, response );
	}
	
	public void Inserir() throws ServletException, IOException{
		this.request.getRequestDispatcher( "/disciplinas/inserir.jsp" ).forward( request, response );
	}
}