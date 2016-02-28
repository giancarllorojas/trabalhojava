package br.ufrj;

public class formTurma {
	@Obrigatorio( false )
	String id;
	
	@Obrigatorio( true )
	String disciplina;
	
	@Obrigatorio( true )
	String professor;
	
	@Obrigatorio( true )
	String sala;
	
	@Obrigatorio( true )
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
	
	public String getId(){
		return this.id;
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
	
	public void setId(String id){
		this.id = id;
	}
}
