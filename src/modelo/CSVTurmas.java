package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Classe que age como uma espécie de "banco de dados", da load nas informações no CSV e tem métodos de CRUD para essas informações 
 */
public class CSVTurmas {
	/*
	 * Inicializando as variaveis que irão guardar os dados dos csv's na memória durante a execução da aplicação
	 */
	ArrayList<formTurma> turmas = new ArrayList<formTurma>();
	File arquivo = new File("/home/defense/workspace2/siga/data/turmas.csv");
	
	public CSVTurmas() throws FileNotFoundException{
		this.CarregaDados();
	}
	
	private void CarregaDados() throws FileNotFoundException{
		Scanner scan = new Scanner(arquivo);
		scan.useDelimiter(",");
		while(scan.hasNextLine()){
            String linha = scan.nextLine();
            String[] dados = linha.split(",");
            String turma_professor = dados[0];
            String turma_disciplina = dados[1];
            String turma_sala = dados[2];
            String turma_horario = dados[3];
            formTurma turma = new formTurma(turma_professor, turma_disciplina, turma_sala, turma_horario);
            this.turmas.add(turma);
        }
        scan.close();
	}
	
	private void SalvaDados() throws IOException{
		String dados = "";
		for(formTurma t : this.turmas){
			dados += t.getDisciplina() + ", " + t.getProfessor() + ", " + t.getSala() + ", " + t.getHorario() + "\n";
		}
		FileWriter fw = new FileWriter(this.arquivo.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(dados);
		bw.close();
	}
	
	/*
	 * Insere uma Turma no ArrayList turmas e salva os dados no CSV
	 */
	public void Insere(String disciplina, String professor, String sala, String horario) throws IOException{
		formTurma t = new formTurma(disciplina, professor, sala, horario);
		this.turmas.add(t);
		this.SalvaDados();
	}
	
	/*
	 * Remove a Turma dado o codigo da Disciplina e nome do Professor que a compõe. Retorna true para caso tenha dado certo, e false do contrário(por exemplo quando o numero da Sala não existe)
	 */
	public boolean Deleta(String cod_disciplina, String nome_professor) throws IOException{
		for(formTurma t: this.turmas){
			if(t.getDisciplina().equals(cod_disciplina) && t.getProfessor().equals(nome_professor)){
				this.turmas.remove(this.turmas.indexOf(t));
				this.SalvaDados();
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Recebe dois objetos tipo Sala e substitui um pelo outro no ArrayList salas e no arquivo CSV
	 */
	public void Altera(formTurma original, formTurma nova) throws IOException{
		this.turmas.set(this.turmas.indexOf(original), nova);
		this.SalvaDados();
	}
}