//Importacoes
import java.util.Random;

public class Utils {
	//-------------------------------------------------------// Processo de cadastrar um Cliente
	static Cliente cadastrarCliente() {
		String nome = View.inserirString("[CADASTRO]", "Nome e sobrenome:");//Coleta dados do usuario
		String localizacao = View.inserirString("[CADASTRO]", "Endereco:"); //Coleta dados do usuario
		
		Cliente cliente = new Cliente (nome,localizacao);					//Cria um Cliente
		return cliente;														//Retorna o cliente cadastrado 
	}
	//-------------------------------------------------------//
	static String validarNomeCompleto(String nome) {						//Validação de nome e sobrenome
		if(nome.length() < 5 && nome.length() > 50) {						//O nome e sobrenome, não pode ser menor que 5 e maior que 50
			throw new IllegalArgumentException("Nome invalido");
		}
		if(nome.indexOf(" ") == -1) {										//Procura se no texto tem " " e mostra para começar a busca a partir do index -1 
			throw new IllegalArgumentException("O campo 'nome' deve conter o seu nome e sobrenome");  //resumindo: procura no nome se tem espaço entre o nome e sobrenome
		}
		if(nome.substring(0,nome.indexOf(" ")).length() < 2 && nome.substring(nome.lastIndexOf(" ") + 1).length() < 2) { // Basicamente o mesmo de cima mas ele valida se tem 2 caracteres no nome e sobrenome
			throw new IllegalArgumentException("O nome e o sobrenome deve contrer pelo menos 2 caracteres");
		}
		return nome;
	}
	//-------------------------------------------------------// Gerador de Entregadores ( uma funcao capaz de criar entregadores para cada pedido );
	static Entregador gerarEntregador() {
		String nomeFinal,veiculoFinal;
		String nome[] = {"Valentim","Daniel","Enrico","Igor","Lucas","Carlos","Luis","Bernardo","Leonardo","Arthur"};	//Supostos nomes de entregadores
		String cor[] = {"Vermelha","Branca","Preto","Cinza"};															//Cores da cor, caso ele tenha motocicleta 
		String letras[] = {"A","B","C","D","E","F","G","H","I","J","L","M","N","O","P","Q","R","S","T","U","V","X","Z"};//Letras para criar a placa caso ele tenha motocicleta

		if((Utils.randomInRangeInt(1, 4) / 2)==0) {				//50% de ter bicibleta e 50% de ter motocicleta
			veiculoFinal = "Bicicleta";							//Caso ele tenha bicileta
		}else {													//Caso ele tenha motocicleta
			String placa = " ,Placa:";
			for(int i = 0;i < 3 ;i++) {
				placa = placa + letras[Utils.randomInRangeInt(1, 23)];//Gerador de placas(letras)
			}
			placa = placa + "-";
			for(int i = 0;i < 4 ;i++) {
				placa = placa + Utils.randomInRangeInt(1, 9); //Gerador de placas(numero)
			}
			veiculoFinal = "Moto ,"+cor[Utils.randomInRangeInt(1, 4)]+placa;
		}

		nomeFinal = nome[Utils.randomInRangeInt(1, 10)];						//Gera um nome aleatorio para o entregador

		Entregador entregador = new Entregador(nomeFinal,veiculoFinal);			//Cria o entregador
		return entregador;														//Retorna o entregador
	}
	//-------------------------------------------------------//Gerador de Restaurantes ( uma funcao capaz de criar restaurante );
	static Restaurante gerarRestaurante(int i) {
		String nomeFinal = "";
		String nome[] = {"Carmelo","Bem Estar","Laap","Armazem","Mandaka","Mestre Kuka"}; //Nome dos suposto restaurante

		nomeFinal += nome[i]; 
		int distancia = i+5; 							//cada restaurante tem ~=5km de outro
		int pontuacao = Utils.randomInRangeInt(3, 5); 	//pontuação de cada restaurante
		Restaurante restaurante = new Restaurante(nomeFinal,distancia,pontuacao);
		return restaurante;
	}
	//-------------------------------------------------------//Gerador de Cardapio ( uma funcao capaz de criar pratos para um restaurante );
	static Comidas gerarCardapio() {
		String nomeFinal = "";
		String nome[] = {"Filezinho","Estrogonofe de Frango","Coxxao","Frango Grill","Picanha Nobre","Bife Acebolado","Bife de Chorizo","File de Tilapia","Biffao","Estrogonofe de Carne","Parmegiana de Steak de Frango","Frango ah Parmegiana","Bife ah Parmegiana"};
		//-------------------------------------------------------// Todos os pratos foram copiados do Giraffas (Pistao Sul)
		float preco = randomInRangeInt(20,50); 					 //Gera um preco entre 20 a 50 reais
		nomeFinal += nome[Utils.randomInRangeInt(1,13)];		 //Gera um nome
		Comidas comida = new Comidas(nomeFinal,preco);
		return comida;
	}
	//-------------------------------------------------------// Funcao para gerar um inteiro aleatorio a partir do seu menor ao seu maior numero
	static int randomInRangeInt(int menor, int maior) {
		Random r = new Random();
		int result = r.nextInt(maior-menor) + menor;
		return result;
	}
}
