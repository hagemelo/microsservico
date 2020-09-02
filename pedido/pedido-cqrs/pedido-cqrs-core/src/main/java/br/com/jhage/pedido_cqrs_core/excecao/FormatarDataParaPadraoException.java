package br.com.jhage.pedido_cqrs_core.excecao;

public class FormatarDataParaPadraoException extends PedidoException{
	
	private static final long serialVersionUID = 1L;

	private final static String MENSAGEM = "Erro ao Formatar Data Para Padrao";

	public FormatarDataParaPadraoException(){
		
		super(MENSAGEM);
	}
}
