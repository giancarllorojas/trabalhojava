package modelo;

public class formDisciplina {
	//variavel de controle pra alteração
	@Obrigatorio( false )
	String cod_antigo;
	
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
	
	public String getCodAntigo(){
		return cod_antigo;
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
	
	public void setCodAntigo(String cod_antigo){
		this.cod_antigo = cod_antigo;
	}
}