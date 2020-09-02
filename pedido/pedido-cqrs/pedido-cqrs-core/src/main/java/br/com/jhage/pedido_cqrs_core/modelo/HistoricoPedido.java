package br.com.jhage.pedido_cqrs_core.modelo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jhage.pedido_cqrs_core.constante.StatusPedido;

/**
 * 
 * @author Alexnsander Melo
 * @since 18/02/2017
 *
 */
public class HistoricoPedido implements JhageEntidade{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@JsonFormat(pattern="dd/MM/YYYY HH:mm:ss")
	private Date ocorrencia;
	
	private StatusPedido status;
	
	HistoricoPedido(){
		
	}
	
	public HistoricoPedido(Pedido pedido){
	
		this.ocorrencia = new Date();
		this.status = pedido.getStatus();
		this.id =new Long(0);
	}

	@Override
	public Long getId() {

		return this.id;
	}
	
	public Date getOcorrencia() {
		return ocorrencia;
	}

	public StatusPedido getStatus() {
		return status;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((this.id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HistoricoPedido)) {
			return false;
		}
		HistoricoPedido other = (HistoricoPedido) obj;

		return super.equals(obj) 
				&&  this.id.equals(other.id);
	}
}
