package br.ufrj;

public class formDisciplina {
	@Obrigatorio( false )
	String id;
	
    @Obrigatorio( true )
	String codigo;
    
    @Obrigatorio( true )
	String nome;
    
    @Obrigatorio( true )
	String ementa;
	
	public String getCodigo(){
		return codigo;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getEmenta(){
		return ementa;
	}
	
	public String getId(){
		return id;
	}
	
	public void setCodigo(String codigo){
		this.codigo = codigo;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public void setEmenta(String ementa){
		this.ementa = ementa;
	}
	
	public void setId(String id){
		this.id = id;
	}
}