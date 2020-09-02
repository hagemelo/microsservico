package br.com.jhage.pedido_cqrs_core.modelo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.jhage.pedido_cqrs_core.constante.StatusPedido;
import br.com.jhage.pedido_cqrs_core.constante.ValoresConstantes;
import br.com.jhage.pedido_cqrs_core.helper.Helper;
import br.com.jhage.pedido_cqrs_core.helper.NumberHelp;

/**
 * 
 * @author Alexnsander Melo
 * @since 15/01/2017
 *
 */
public class Pedido implements JhageEntidade{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String contato;
	
	private String entrega;
	
	private Double troco;
	
	@JsonFormat(pattern="dd/MM/YYYY")
	private Date cadastro;
	
	private StatusPedido status;
	
	
	Pedido(){
		
	}
	
	public Pedido(String contato , String entrega,  Double troco){
	
		this.contato = Helper.ENULO.enulo(contato)?ValoresConstantes.STRING_VAZIO:contato;
		this.entrega = Helper.ENULO.enulo(entrega)?ValoresConstantes.STRING_VAZIO:entrega;
		this.troco = Helper.ENULO.enulo(troco)?ValoresConstantes.DOUBLE_ZERO:troco;
		this.status = StatusPedido.REALIZADO;
		this.cadastro = new Date();
		this.id =new Long(0);
	}
	
	@Override
	public Long getId() {

		return this.id;
	}
	
	@JsonProperty
	public String trocoString() {
		
		return NumberHelp.parseDoubleToString(this.troco);
	}
	
	@JsonProperty
	public boolean isRealizado(){
		
		return StatusPedido.REALIZADO.equals(this.status);
	}
	
	@JsonProperty
	public boolean isPronto(){
		
		return StatusPedido.PRONTO.equals(this.status);
	}
	
	@JsonProperty
	public boolean isEntregue(){
		
		return StatusPedido.ENTREGUE.equals(this.status);
	}
	
	public void setStatus(StatusPedido status){
		
		this.status = status;
	}
	
	public String getContato() {
		
		return contato;
	}

	public String getEntrega() {
		
		return entrega;
	}

	public Double getTroco() {
		
		return troco;
	}

	public StatusPedido getStatus() {
		
		return status;
	}

	public Date getCadastro() {
		
		return cadastro;
	}
	
	public void tratarNull(){
		
		this.tratarNUllContato();
		this.tratarNUllEntrega();
		this.tratarNUllTroco();
		this.tratarNUllCadastro();
		this.tratarNUllStatus();
	}
	
	private void tratarNUllContato(){
	
		if (Helper.ENULO.enulo(this.contato)){
			
			this.contato = ValoresConstantes.STRING_VAZIO;
		}
	}
	
	private void tratarNUllEntrega(){
		
		if (Helper.ENULO.enulo(this.entrega)){
			
			this.entrega = ValoresConstantes.STRING_VAZIO;
		}
	}
	
	private void tratarNUllTroco(){

		if (Helper.ENULO.enulo(this.troco)){
			
			this.troco = ValoresConstantes.DOUBLE_ZERO;
		}
	}
	
	private void tratarNUllCadastro(){
		
		if (Helper.ENULO.enulo(this.cadastro)){
			
			this.cadastro = new Date();
		}
	}
	
	private void tratarNUllStatus(){
		
		if (Helper.ENULO.enulo(this.status)){
			
			this.status = StatusPedido.REALIZADO;
		}
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((this.id == null) ? ValoresConstantes.ZERO : id.hashCode());
		result = prime * result + ((this.contato == null) ? ValoresConstantes.ZERO : this.contato.hashCode());
		result = prime * result + ((this.troco == null) ? ValoresConstantes.ZERO : this.troco.hashCode());
		result = prime * result + ((this.entrega == null) ? ValoresConstantes.ZERO : this.entrega.hashCode());
		result = prime * result + ((this.cadastro == null) ? ValoresConstantes.ZERO : this.cadastro.hashCode());
		result = prime * result + ((this.status == null) ? ValoresConstantes.ZERO : this.status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pedido)) {
			return false;
		}
		Pedido other = (Pedido) obj;

		return super.equals(obj) 
				&&  this.id.equals(other.id)
				&&  this.contato.equals(other.contato)
				&&  this.entrega.equals(other.entrega)
				&&  this.status.equals(other.status)
				&&  this.cadastro.equals(other.cadastro);
	}
	
}
