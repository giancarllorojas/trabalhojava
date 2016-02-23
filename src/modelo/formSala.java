package modelo;

public class formSala {
	//controle para alteração
	String num_antigo;
	
	String numero;
	String capacidade;
	
	public String getNumero(){
		return this.numero;
	}
	
	public String getCapacidade(){
		return this.capacidade;
	}
	
	public String getNumeroAntigo(){
		return this.num_antigo;
	}
	
	public void setNumero(String num){
		this.numero = num;
	}
	
	public void setCapacidade(String cap){
		this.capacidade = cap;
	}
	
	public void setNumeroAntigo(String num){
		this.num_antigo = num;
	}
}
