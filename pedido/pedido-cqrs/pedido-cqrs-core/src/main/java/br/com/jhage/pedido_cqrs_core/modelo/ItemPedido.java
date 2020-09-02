package br.com.jhage.pedido_cqrs_core.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.jhage.pedido_cqrs_core.constante.ValoresConstantes;
import br.com.jhage.pedido_cqrs_core.helper.Helper;
import br.com.jhage.pedido_cqrs_core.helper.NumberHelp;

/**
 * 
 * @author Alexnsander Melo
 * @since 15/01/2017
 *
 */
public class ItemPedido implements JhageEntidade{
	
private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Integer quantidade;
	
	private Double valor;
	
	private String descricao;
	
	ItemPedido(){
		
	}
	
	public ItemPedido(String descricao, Double valor, Integer quantidade){
		
		this.quantidade = Helper.ENULO.enulo(quantidade)?ValoresConstantes.UM:quantidade;
		this.valor 		= Helper.ENULO.enulo(valor)?ValoresConstantes.DOUBLE_ZERO:valor;
		this.descricao 	= Helper.ENULO.enulo(descricao)?ValoresConstantes.STRING_VAZIO:descricao;
	}
	
	@Override
	public Long getId() {

		return this.id;
	}
	
    @JsonProperty
	public String totalString(){
		
		return NumberHelp.parseDoubleToString(this.total());
	}
    
    @JsonProperty
	public String valorString(){
		
		return NumberHelp.parseDoubleToString(this.getValor());
	}
    
	public Double total() {
		
		Double result =ValoresConstantes.DOUBLE_ZERO;
		if (this.isOKQuantidadeValor()){
			result =  this.getValor() * this.getQuantidade();
		}
		return result;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	private boolean isOKQuantidadeValor(){
		
		return !Helper.ENULO.enulo(this.valor) 
				&& !Helper.ENULO.enulo(this.quantidade) 
				&& Double.doubleToRawLongBits(this.valor)!=ValoresConstantes.ZERO;
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((this.id == null) ? 0 : id.hashCode());
		result = prime * result + ((this.quantidade == null) ? 0 : this.quantidade.hashCode());
		result = prime * result + ((this.descricao == null) ? 0 : this.descricao.hashCode());
		result = prime * result + ((this.valor == null) ? 0 : this.valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ItemPedido)) {
			return false;
		}
		ItemPedido other = (ItemPedido) obj;

		return super.equals(obj) 
				&&  this.id.equals(other.id)
				&&  this.quantidade.equals(other.quantidade)
				&&  this.descricao.equals(other.descricao)
				&&  this.valor.equals(other.valor);
	}
}
