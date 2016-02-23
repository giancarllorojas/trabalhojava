package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Turmas {
	/*
	 * Inicializando as variaveis que irão guardar os dados dos csv's na memória durante a execução da aplicação
	 */
	ArrayList<formTurma> turmas = new ArrayList<formTurma>();
	ArrayList<formDisciplina> disciplinas = new ArrayList<formDisciplina>();
	ArrayList<formProfessor> professores = new ArrayList<formProfessor>();
	ArrayList<formSala> salas = new ArrayList<formSala>();
	
	HttpServletRequest request;
	HttpServletResponse response;
	File arquivo = new File("/home/defense/workspace/trabp2/data/turmas.csv");
	
	public Turmas(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException{		
		this.CarregaDados();
		//pega dados das outras classes
		this.disciplinas = new Disciplinas(request, response).disciplinas;
		this.professores = new Professores(request, response).professores;
		this.salas = new Salas(request, response).salas;
		
		this.request = request;
		this.response = response;
	}
	
	private void CarregaDados() throws FileNotFoundException{
		Scanner scan = new Scanner(arquivo);
		scan.useDelimiter(",");
		while(scan.hasNextLine()){
			String linha = scan.nextLine();
            String[] dados = linha.split(",");
            String turma_disciplina = dados[0];
            String turma_professor = dados[1];
            String turma_sala = dados[2];
            String turma_horario = dados[3];
            formTurma form = new formTurma();
            form.setDisciplina(turma_disciplina);
            form.setProfessor(turma_professor);
            form.setSala(turma_sala);
            form.setHorario(turma_horario);
            this.turmas.add(form);
        }
        scan.close();
	}
	
	private void SalvaDados() throws IOException{
		String dados = "";
		for(formTurma t : this.turmas){
			dados += t.getDisciplina() + "," + t.getProfessor() + "," + t.getSala() + "," + t.getHorario() + "\n";
		}
		FileWriter fw = new FileWriter(this.arquivo.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(dados);
		bw.close();
	}
	
	//Pega turma pela disciplina e professor
	public formTurma getTurma(String turma_disciplina, String turma_professor){
		for(formTurma t: this.turmas){
			if(t.getDisciplina().equals(turma_disciplina) && t.getProfessor().equals(turma_professor)){
				return t;
			}
		}
		return null;
	}
	
	/*
	 * Insere nova Turma no ArrayList turmas e salva os dados no CSV
	 */
	public void Insere(formTurma f) throws IOException{
		this.turmas.add(f);
		this.SalvaDados();
		
		response.sendRedirect("/trabp2/do/?control=Turmas&action=Exibir");
		return;
	}
	
	/*
	 * Remove Turma dado um professor e uma disciplina
	 */
	public void Deleta(formTurma f) throws IOException{
		formTurma t = this.getTurma(f.getDisciplina(), f.getProfessor());
		if(t != null){
			this.turmas.remove(this.turmas.indexOf(t));
			this.SalvaDados();
		}
		
		response.sendRedirect("/trabp2/do/?control=Turmas&action=Exibir");
		return;
	}
	
	/*
	 * Altera turma
	 */
	public void Altera(formTurma f) throws IOException{
		formTurma t = this.getTurma(f.getDisciplina(), f.getProfessor());
		if(t != null){
			this.turmas.set(this.turmas.indexOf(t), f);
			this.SalvaDados();
		}
		
		response.sendRedirect("/trabp2/do/?control=Turmas&action=Exibir");
		return;
	}
	
	/*
	 * Classes de exibição das págians
	 * */
	public void Exibir() throws ServletException, IOException{
		this.request.setAttribute("turmas", this.turmas);
		this.request.getRequestDispatcher( "/turmas/exibir.jsp" ).forward( request, response );
	}
	
	public void Alterar(formTurma f) throws ServletException, IOException{
		formTurma t = this.getTurma(f.getDiscAntigo(), f.getProfAntigo());
		if(t != null){
			this.request.setAttribute("disciplinas", this.disciplinas);
			this.request.setAttribute("professores", this.professores);
			this.request.setAttribute("salas", this.salas);
			this.request.setAttribute("turma", t);
			this.request.getRequestDispatcher( "/turmas/alterar.jsp" ).forward( request, response );
		}else{
			
		}
	}
	
	public void Inserir() throws ServletException, IOException{
		this.request.setAttribute("disciplinas", this.disciplinas);
		this.request.setAttribute("professores", this.professores);
		this.request.setAttribute("salas", this.salas);
		this.request.getRequestDispatcher( "/turmas/inserir.jsp" ).forward( request, response );
	}
}