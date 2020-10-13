/*
Nome: Gabriel Arthur Ferreira Fiusa
Matricula: UC19106718
*/
import java.util.ArrayList;

public class Executora {
	public static void main(String[] args) {
		ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
		Cliente fulano = null;
		String opcoes = "";
		//-------------------------------------------------------//Cadastro 
		//fulano = new Cliente("Gabriel Arthur","Qs5"); //Habilite para testes e comente as linhas abaixo 
		do {
			try {
				fulano = Utils.cadastrarCliente();
			} catch (Exception e) {
				View.exibirError("ERROR", e.getMessage());
			}
		} while(fulano==null);
		//-------------------------------------------------------//MenuPrincipal
		do {
			try {
				opcoes = View.miniMenu("Conta", "Restaurantes", "Carrinho", "Sair", "Opcoes:", "[MENU PRINCIPAL]"); //Mini Menu para o usuario escolher o que ele quer fazer
			} catch (Exception e) {
				View.exibirError("ERROR", e.getMessage());
				opcoes = "Sair";  //Tratando a exception, aonde se ele clickar em "cancelar" tenha a mesma funcao que Sair
			}
			switch (opcoes) {
				case "Conta":
					subMenuConta(fulano); //Sub Menu de Contas
					break;
				case "Restaurantes":
					subMenuCardapio(restaurantes); //Sub Menu de Cardapio
					break;
				case "Carrinho":
					try {
						subMenuCarrinho(fulano,restaurantes); //Sub Menu de Carrinho
					} catch (Exception e) {						//TryCatch por que ela precisa que o usuario abra primeiro o cardapio para depois fazer as compras
						View.exibirError("ERROR", e.getMessage()); 
					}
					break; 
			}
		} while (opcoes != "Sair");
	}

	static void subMenuConta(Cliente fulano){
		String opcoes = "";
		do {
			try {
				opcoes = View.miniMenu("Dados da conta","Saldo","Voltar","","Opcoes:","[CONTA]"); //Mini menu
			} catch (Exception e) {
				View.exibirError("ERROR", e.getMessage());
				opcoes = "Voltar";//Tratando a exception, aonde se ele clickar em "cancelar" tenha a mesma funcao que Voltar
			}
			switch (opcoes) {
				case "Dados da conta":
					String opcoesDados = "";
					do {
						try {
							opcoesDados = View.miniMenu("Alterar nome", "Alterar endereco","Voltar","",fulano.getNome()+"\nSaldo:"+fulano.getSaldo(),"[CONTA - DADOS DA CONTA]");//Mini menu
						} catch (Exception e) {
							View.exibirError("ERROR", e.getMessage());
							opcoes = "Voltar";//Tratando a exception
						}
						switch (opcoesDados) {
							case "Alterar nome":
								try {
									fulano.setNome(View.inserirString("[CONTA - ALTERACAO DE NOME]", "Novo nome:")); //Validacao de nome e sobrenome(UTILS) possui throw
								} catch (Exception e) {
									View.exibirError("ERROR", e.getMessage());
								}
							break;
							case "Alterar endereco":
								try {
									fulano.setLocalizacao(View.inserirString("[CONTA - ALTERACAO DE ENDERECO]", "Novo endereco:"));//Validacao de endereco(Cliente) possui throw
								} catch (Exception e) {
									View.exibirError("ERROR", e.getMessage());
								}
							break;
						}
					} while (opcoesDados!="Voltar");
				break;
				case "Saldo":
					String opcoesSaldo="";
					do {
						try {
							opcoesSaldo = View.miniMenu("Inserir saldo", "Retirar saldo","Voltar","",fulano.getSaldo()+"R$","[CONTA - SALDO]");//Mini menu
						} catch (Exception e) {
							View.exibirError("ERROR", e.getMessage());
							opcoesSaldo = "Voltar";//Tratando a exception
						}
						switch (opcoesSaldo) {
							case "Inserir saldo":
								try {
									fulano.setSaldo(View.inserirInt("[CONTA - Depositar]", "Valor:"));//Validacao de depositar(Cliente) possui throw
								} catch (Exception e) {
									View.exibirError("ERROR", e.getMessage());
								}
							break;
							case "Retirar saldo":
								try {
									fulano.retirarSaldo(View.inserirInt("[CONTA - Retirar]", "Valor:"));//Validacao de Retirar(Cliente) possui throw
								} catch (Exception e) {
									View.exibirError("ERROR", e.getMessage());
								}
							break;
						}
					} while (opcoesSaldo!="Voltar");
				break;
			}
		} while (opcoes != "Voltar");
	}

	static void subMenuCardapio(ArrayList<Restaurante> restaurantes){
		String lista,opcoes ="";
		if(restaurantes.size()<=0){ // Gerar os restaurantes
			for (int i = 1; i < 6; i++) {
				restaurantes.add(Utils.gerarRestaurante(i));	
			}
		}
		do {
			try {
				opcoes = View.miniMenu("Listar Restaurantes mais proximos","Listar os melhores Restaurantes","Listar o cardapio","Voltar","[RESTAURANTE]","Opcoes:");//Mini menu
			} catch (Exception e) {
				View.exibirError("ERROR", e.getMessage());
				opcoes = "Voltar";//Tratando a exception
			}
			switch (opcoes) {
				case "Listar Restaurantes mais proximos":
					lista = "";
					for (int i = 1; i < 20; i++) { //Sabendo que cada restaurante tem ~=5km de outro é mais facil de calcular
						for (Restaurante restaurante : restaurantes) {
							if(i == restaurante.getDistancia()){
								lista += "Nome: "+restaurante.getNome()+" Distancia: "+restaurante.getDistancia()+"km\n";
							}
						}
					}
					View.exibirMensagem(lista);
					break;
				case "Listar os melhores Restaurantes":
					lista = "";
					for (int i = 5; i > 0; i--) { //Sabendo que cada restaurante tem no max 5 estrelas é mais facil de calcular
						for (Restaurante restaurante : restaurantes) {
							if(i == restaurante.getPontuacao()){
								lista +="Nome: "+restaurante.getNome()+" Pontuacao: "+restaurante.getPontuacao()+"\n";
							}
						}
					}
					View.exibirMensagem(lista);
					break;
				case "Listar o cardapio":
					for (int i = 1; i < 20; i++) { // A mesma coisa de "Listar Restaurantes mais proximos" mas ele mostra todas as informacoes do restaurante e seu cardapio
						for (Restaurante restaurante : restaurantes) {
							if(i == restaurante.getDistancia()){
								View.exibirMensagem(""+restaurante);
							}
						}
					}
					break;
			}
		} while (opcoes!="Voltar");
		
	}

	static void subMenuCarrinho(Cliente fulano,ArrayList<Restaurante> restaurantes){
		String listaDeCompras = "",listaAux = "",opcoes = "";
		float valorCarrinho = 0; //O valor do carrinho
		boolean finalizarPedido = false; //Toda vez que finalizar a conta, zerar o carrinho e o valor
		ArrayList<Comidas> carrinho = new ArrayList<Comidas>(); //Na teoria, varias comidas que o usuario selecionou se torna o seu carrinho

		if(restaurantes.size()<=0){ //Verifica se foi gerados os restaurantes
			throw new IllegalArgumentException("Nao possui restaurantes, visite os restaurantes primeiro");
		}
		do {
			listaDeCompras = ""; //Tem uma lista de compras sem atualizada
			if(finalizarPedido == true){ //Assim que finaliza o pedido
				carrinho.clear();		//Limpa o carrinho
				valorCarrinho = 0;		//Limpa o valor
			}
			if(!carrinho.isEmpty()){ //Pega todo o conteudo do carrinho e salva
				int i = 0;
				for (Comidas comidas : carrinho) {
					i++;
					listaDeCompras +="\n["+i+"]"+comidas.getNome()+", "+comidas.getPreco()+"\n";
				}
			}else
				listaDeCompras = "vazio"; //Se o carrinho estiver vazio, ele informa
			try {
				opcoes = View.miniMenu("Adicionar ao carrinho", "Remover do carrinho", "Comprar", "Voltar", "Valor do carrinho :"+valorCarrinho, "[CARRINHO]");//miniMenu mostrando o valor do carrinho
			} catch (Exception e) {
				View.exibirError("ERROR", e.getMessage());
				opcoes = "Voltar";//tratamento de exception
			}
			switch (opcoes) {
				case "Adicionar ao carrinho":
					int op = 0,y;
					do {
						listaAux = "";//esvazia o array para auxiliar
						y = 0;
						for (Restaurante restaurante : restaurantes) {//Entra nos restaurantes
							for (Comidas restaurante2 : restaurante.cardapio) {//Entra em cardapio dos restaurantes
								y++;//Contador para ajuda o usuario a escolher
								listaAux += "["+y+"] "+restaurante.getNome()+" |Nome:"+restaurante2.getNome()+"  Preco: "+restaurante2.getPreco()+"\n";//Coleta as informacoes dos cardapios e restaurantes
								if(op == y) { // se a escolha do usuario foi igual a do index
									valorCarrinho +=restaurante2.getPreco(); //adciona a conta
									carrinho.add(restaurante2); //adciona o prato ao carrinho
								}
							}
							listaAux += "-\n"; //Pula linha para ajudar na visualizacao
						}
						try {
							op = View.inserirInt("[CARRINHO - Adicionar]", listaAux+"\n0 - Sair\nEscolha o prato:"); //Mostra a informacoes da lista e pede para adicionar conforme o index
						} catch (Exception e) {
							View.exibirError("ERROR", e.getMessage());
						}
					}while(op!=0);
					break;
				case "Remover do carrinho":
					if(valorCarrinho <=0 ){ //Se n tem dinheiro no carrinho, n tem pedidos
						throw new IllegalArgumentException("Nao possui pedidos!");
					}
					int remover = 0;
					do {
						try {
							remover = View.inserirInt("[CARRINHO - Remover]", listaDeCompras+"\n0 - Sair\n\nEscolha o prato:"); //Com a lista de compras que foi pega no inicio do sub-menu, mostra o carrinho para remover
							if(remover!=0){ //se n for para sair
								valorCarrinho -=carrinho.get(remover-1).getPreco(); //tira o preco
								View.exibirMensagem(carrinho.get(remover-1).getNome()+" Excluido com sucesso"); // informa ao usuario
								carrinho.remove(carrinho.get(remover-1)); // remove 
								remover = 0;// Sai do loop
							}
						} catch (Exception e) {
							View.exibirError("ERROR", e.getMessage());
						}
					} while (remover!=0);
					break;
				case "Comprar":
					if(valorCarrinho <=0 ){//Se n tem dinheiro no carrinho, n tem pedidos
						throw new IllegalArgumentException("Nao possui pedidos!");
					}
					if(View.confirmar("[COMPRAR]", listaDeCompras+"\n\nCarrinho:"+valorCarrinho+"\nDESEJA CONFIMAR A COMPRA?") == 0){ // parece "sim" ou "nao" para o usario confimar, se for sim
						fulano.retirarSaldo(valorCarrinho); // ele paga
						Entregador entregador = Utils.gerarEntregador(); // gera um entregador 
						View.exibirMensagem("Encontre "+entregador+"\n\nna porta em: "+Utils.randomInRangeInt(5, 10)+"Minutos"); //ele informa o pedido e a distancia da sua casa(randomico)
						finalizarPedido = true; //finaliza o pedido e zera tudo
					}else{
						View.exibirError("", "COMPRA REJEITADA");// caso ele clickar em "nao", só mostra essa mensagem 
					}
					break;
			}
		} while (opcoes!="Voltar");
	}
}
