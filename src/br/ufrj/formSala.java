package br.ufrj;

public class formSala {
	@Obrigatorio( false )
	String id;
	
	@Obrigatorio( true )
	String numero;
	
	@Obrigatorio( true )
	String capacidade;
	
	public String getNumero(){
		return this.numero;
	}
	
	public String getCapacidade(){
		return this.capacidade;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void setNumero(String num){
		this.numero = num;
	}
	
	public void setCapacidade(String cap){
		this.capacidade = cap;
	}
	
	public void setId(String id){
		this.id = id;
	}
}
