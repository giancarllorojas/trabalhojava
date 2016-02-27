package modelo;

public class formProfessor {
	//variavel de controle
	@Obrigatorio( false )
	String nome_antigo;
	
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
	
	public String getNomeAntigo(){
		return this.nome_antigo;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setNomeAntigo(String nome_antigo){
		this.nome_antigo = nome_antigo;
	}
}
