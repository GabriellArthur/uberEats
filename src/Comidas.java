
public class Comidas {
	//Declarações
	private String nome;
	private float preco;
	//-------------------------------------------------------
	//Construtores
	Comidas(String nome, float preco){
		setNome(nome);
		setPreco(preco);
	}
	//-------------------------------------------------------
	//Getters && Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	//-------------------------------------------------------
	//Relatorio da Comida
	public String toString() {
		return ""+this.getNome()+" "+this.getPreco()+"R$\n";
	}
}