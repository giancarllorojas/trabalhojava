package br.ufrj;

public class formProfessor {
	@Obrigatorio( false )
	String id;
	
	@Obrigatorio( true )
	String nome;
	
	@Obrigatorio( true )
	String email;
	
	public String getNome(){
		return this.nome;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setId(String id){
		this.id = id;
	}
}
