package br.com.jhage.command.response;

/**
 * 
 * @author Alexsander Melo
 * @since 01/12/2018
 *
 */
public class Response {

	private String mensagem;

	public Response(String mensagem) {

		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
}
