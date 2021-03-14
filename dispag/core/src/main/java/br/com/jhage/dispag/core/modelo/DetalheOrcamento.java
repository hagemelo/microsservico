package br.com.jhage.dispag.core.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhage.dispag.core.constante.Estado;
import br.com.jhage.dispag.core.exception.ConverterToStringException;
import br.com.jhage.dispag.core.exception.NumberHelpException;
import br.com.jhage.dispag.core.helper.NumberHelp;

/**
 * 
 * @author Alexsander Melo
 * @since 15/02/2021
 *
 */
@Entity
@Table(name = "DETALHE_ORCAMENTO")
public class DetalheOrcamento implements JhageEntidade<Orcamento> {

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "DEO_ID", nullable = false)
	@SequenceGenerator(name = "deoid", sequenceName = "GEN_DEO_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deoid")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "ORC_ID", referencedColumnName = "ORC_ID")
	private Orcamento orcamento;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "CREDOR_ID", referencedColumnName = "CREDOR_ID")
	private Credor credor;
	
	@Column(name = "VALOR")
	private Double valor;
	
	public DetalheOrcamento() {
		
		this.valor = Double.valueOf(ZERO);
		this.estado = Estado.PENDENTE;
	}
	
	public DetalheOrcamento(Orcamento orcamento, Credor credor,  Double valor) {
		
		this.credor = credor;
		this.orcamento = orcamento;
		this.valor = valor;	
		this.estado = Estado.PENDENTE;
	}
	
	@Override
	public Long getId() {

		return this.id;
	}
	
	public Orcamento getOrcamento() {
		return orcamento;
	}

	public Credor getCredor() {
		return credor;
	}

	public Double getValor() {
		return valor;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void aprovar() {
		
		this.estado = Estado.APROVADO;
	}
	public void rejeitar() {
		
		this.estado = Estado.REJEITADO;
	}
	
	
	@JsonProperty
	public String valorString() throws NumberHelpException {

		return NumberHelp.parseDoubleToString(this.getValor());
	}
	
	@JsonIgnore
	public String getJsonValue() throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
	
	@Override
	public String converterToString() throws ConverterToStringException {
		String result = "";
		try {
			StringBuffer buffer = new StringBuffer()
					.append(this.orcamento.converterToString())
					.append(SEPARADOR)
					.append(this.credor.converterToString())
					.append(SEPARADOR)
					.append(NumberHelp.parseDoubleToString(this.valor))
					;
			result =  buffer.toString();
		}catch (NumberHelpException e) {
			e.printStackTrace();
			throw new ConverterToStringException();
		}
		return result;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ZERO : id.hashCode());
		result = prime * result + ((this.valor == null) ? ZERO : this.valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DetalheOrcamento)) {
			return false;
		}
		DetalheOrcamento other = (DetalheOrcamento) obj;
		return super.equals(obj) && this.id.equals(other.id) && this.valor.equals(other.valor);
	}
	
}
