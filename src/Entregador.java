
public class Entregador {
	//Declarações
	private String nome;
	private String Veiculo;
	//------------------------------------------------------- 
	//Construtores
	Entregador(String nome, String veiculo){
		setNome(nome);
		setVeiculo(veiculo);
	}
	//-------------------------------------------------------Todos os tratamentos o usuario nao tem acesso
	//Getters && Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVeiculo() {
		return Veiculo;
	}

	public void setVeiculo(String veiculo) {
		Veiculo = veiculo;
	}
	//-------------------------------------------------------
	//Relatorio do entregador
	public String toString() {
		return "[INFORMACOES DO ENTREGADOR]\n" +
				"\nNome: "+this.getNome()+
				"\nEntregador: "+this.getVeiculo();
	}
}
