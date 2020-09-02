package br.com.jhage.pedido_cqrs_core.response;

/**
 * 
 * @author Alexsander Melo 
 * @since 25/03/2018
 *
 */

public class MensagemResposta {
	
	private String acao;
	private String mensagem;

	MensagemResposta() {}
	
	public MensagemResposta(String acao, String mensagem) {
		
		this.acao = acao;
		this.mensagem = mensagem;
	}

	public String getAcao() {
		return acao;
	}

	public String getMensagem() {
		return mensagem;
	}
	
}
