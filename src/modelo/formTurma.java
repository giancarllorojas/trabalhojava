package modelo;

public class formTurma {
	//para controle
	@Obrigatorio( false )
	String disc_antigo;
	
	@Obrigatorio( false )
	String prof_antigo;
	
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
	
	public String getDiscAntigo(){
		return this.disc_antigo;
	}
	
	public String getProfAntigo(){
		return this.prof_antigo;
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
	
	public void setProfAntigo(String prof){
		this.prof_antigo = prof;
	}
}
