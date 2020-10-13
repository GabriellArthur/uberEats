import java.util.ArrayList;

public class Restaurante {
	//Declarações
	private String nome;
	ArrayList<Comidas> cardapio;
	private int distancia;
	private int pontuacao;
	//-------------------------------------------------------
	//Construtores
	Restaurante(String nome,int distancia,int pontuacao){
		setNome(nome);									//Nome
		setDistancia(distancia);						//Distancia
		setPontuacao(pontuacao);						//Pontuacao do restaurante
		cardapio = new ArrayList<Comidas>();			//Cria uma estancia de comidas(cardapio)
		gerarCardapio();								//Cria o cardapio
	}
	//------------------------------------------------------- // Todos os tratamentos o usuario nao tem acesso
	//Getters && Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	//------------------------------------------------------- // Sem validacoes porquê o sistema gera tudo que ele precisa, na Utils
	public void gerarCardapio() {
		int qtd = 0;
		while(qtd != 3){
			Comidas comida = Utils.gerarCardapio();
			this.cardapio.add(comida);
			for (Comidas comidas : cardapio) {
				if(comidas.getNome() != comida.getNome()) {
					qtd++;
				}
			}
		}
	}
	//-------------------------------------------------------
	//Relatorio do resturante
	public String toString() {
		return "[RESTAURANTE]\n" +
				"\nNome: "+this.getNome()+
				"\nDistancia: "+this.getDistancia()+"km"+
				"\nComidas no cardapio: "+this.cardapio.size()+
				"\n[CARDAPIO]: \n"+this.cardapio;
	}
}
