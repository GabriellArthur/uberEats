import javax.swing.JOptionPane;

public class View {

	static void exibirMensagem(String msg) { //Mostra mensagem
		JOptionPane.showMessageDialog(null, msg);
	}
	static String inserirString(String titulo,String msg) {//Insere uma string
		return JOptionPane.showInputDialog(null,msg,titulo,JOptionPane.DEFAULT_OPTION);
	}
	static int inserirInt(String titulo,String msg) { //Insere uma string
		String retorno = JOptionPane.showInputDialog(null,msg,titulo,JOptionPane.INFORMATION_MESSAGE);
		return Integer.parseInt(retorno);
	}
	static double inserirDouble(String titulo,String msg) {//Insere uma Double
		String retorno = JOptionPane.showInputDialog(null,msg,titulo,JOptionPane.INFORMATION_MESSAGE);
		return Double.parseDouble(retorno);
	}
	static void exibirError(String titulo,String msg) {//Mostra erro
		JOptionPane.showMessageDialog(null,msg,titulo,JOptionPane.ERROR_MESSAGE);
	}
	static int confirmar(String titulo,String msg) {//escolha com "sim" ou "nao"
		return JOptionPane.showConfirmDialog(null,msg,titulo,JOptionPane.YES_NO_OPTION);
	}
	static String miniMenu(String op1,String op2,String op3,String op4,String titulo,String msg){ //mostra um mini-menu
		Object[] itens = { op1, op2, op3, op4 };// cria os objetos
		Object selectedValue = JOptionPane.showInputDialog(null,titulo, msg,JOptionPane.INFORMATION_MESSAGE, null,itens, itens [0]);//Mostra o vetor de objetos criado na linha 26
		if(selectedValue == null)																									
			throw new IllegalArgumentException("Nao cancele, saia/volte!");//caso ele cancele
		else
			return selectedValue.toString();//caso ele escolha uma opcao//Retorna o toString que e a escolha do usuario,no caso o objeto, no caso a String 
	}
}
/*
showConfirmDialog	|Solicita uma confirmação como(YES, NO, CANCEL)	 
showInputDialog	    |Solicita algum valor	 
showMessageDialog	|Informa ao usuário sobre algo	 
showOptionDialog	|Unificação dos tres acima
*/