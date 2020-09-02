package br.com.jhage.pedido_cqrs_core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhage.pedido_cqrs_core.config.KafkaConfig;
import br.com.jhage.pedido_cqrs_core.excecao.PedidoException;
import br.com.jhage.pedido_cqrs_core.modelo.Pedido;
import br.com.jhage.pedido_cqrs_core.response.MensagemResposta;


/**
 * 
 * @author Alexsander Melo
 * @since 11/02/2018
 *
 */
@CrossOrigin(origins = { "http://localhost:8081" }, maxAge = 3000)
@RequestMapping("/atendimento")
@RestController
public class AtendimentoController extends DefaultController{
	
	
	@Autowired
	private KafkaConfig KafkaConfig;
	
	@CrossOrigin
	@PostMapping(path="/novopedido")
	public @ResponseBody MensagemResposta registrarPedido(@RequestBody Pedido pedido) throws PedidoException{
		
		KafkaConfig.send("test", "ALEXNSANDER");
		MensagemResposta result = new MensagemResposta("Pedido", "Mensagem Enviada");
		return result;
	}
	
}
