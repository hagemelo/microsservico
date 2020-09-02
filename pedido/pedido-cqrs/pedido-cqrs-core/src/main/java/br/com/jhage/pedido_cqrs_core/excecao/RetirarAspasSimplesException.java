package br.com.jhage.pedido_cqrs_core.excecao;

public class RetirarAspasSimplesException extends PedidoException{
	
	private static final long serialVersionUID = 1L;

	private final static String MENSAGEM = "Erro ao Retirar Aspas Simples de String";
	
	public RetirarAspasSimplesException(){
	
		super(MENSAGEM);
	}
	
	

}
