
public class Cliente {
	//Declarações	
	private String nome;
	private String localizacao;
	private float saldo;
	//-------------------------------------------------------
	//Construtores
	Cliente(String nome, String localizacao){
		setNome(nome);
		setLocalizacao(localizacao);
	}
	//------------------------------------------------------- Habilite para testes
	Cliente(){ 
		
	}
	//-------------------------------------------------------
	//Getters && Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = Utils.validarNomeCompleto(nome); //Validacao de nome completo
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) { //Validacao de localizacao
		if(!localizacao.isEmpty())
			this.localizacao = localizacao;
		else
			throw new IllegalArgumentException("A localizacao se encontra vazia");
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) { //Validacao de saldo
		if(saldo <= 0){
			throw new IllegalArgumentException("O valor inserido se encontra vazio ou negativo");
		}else
			this.saldo += saldo;
	}
	//-------------------------------------------------------
	public void retirarSaldo(float saldo){ //Retirar o dinheiro depositado na conta do uberEats
		if(saldo <= this.saldo){
			this.saldo -= saldo;
		}else{
			throw new IllegalArgumentException("O valor inserido ultrapassa a quantia que possui,"+this.saldo+"R$");
		}
	}
	//-------------------------------------------------------
	//Relatorio da conta
	public String toString() {
		return "[INFORMACOES DA CONTA]\n" +
				"\nNome: "+this.getNome()+
				"\nLocalizacao: "+this.getLocalizacao()+
				"\nSaldo: "+this.getSaldo()+"R$";
	}
}
