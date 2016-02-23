package modelo;

public class formTurma {
	//para controle
	String disc_antigo;
	String prof_antigo;
	
	String disciplina;
	String professor;
	String sala;
	String horario;
	
	public String getProfessor(){
		return this.professor;
	}
	
	public String getDisciplina(){
		return this.disciplina;
	}
	
	public String getSala(){
		return this.sala;
	}
	
	public String getHorario(){
		return this.horario;
	}
	
	public void setProfessor(String prof){
		this.professor = prof;
	}
	
	public void setDisciplina(String disc){
		this.disciplina = disc;
	}
	
	public void setSala(String sala){
		this.sala = sala;
	}
	
	public void setHorario(String hor){
		this.horario = hor;
	}
	
	public void setDiscAntigo(String d_a){
		this.disc_antigo = d_a;
	}
	
	public void setProfessor(String prof){
		this.professor = prof;
	}
}
