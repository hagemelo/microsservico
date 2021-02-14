package br.com.jhage.dispag.core.modelo;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhage.dispag.core.constante.Status;
import br.com.jhage.dispag.core.exception.ConverterToStringException;

/**
 * 
 * @author Alexsander Melo
 * @since 04/02/2021
 * 
 *  
 */
@Entity
@Table(name = "DEBITOS")
public class Debitos implements JhageEntidade<Debitos>{

	private static final long serialVersionUID = 1L;
	private static final int ZERO = 0;

	@Version
	Integer versao;

	@Id
	@Column(name = "DEBITO_ID", nullable = false)
	@SequenceGenerator(name = "debid", sequenceName = "GEN_DEBITO_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debid")
	private Long id;

	@Column(name = "VALOR")
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
		
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "CREDOR_ID", referencedColumnName = "CREDOR_ID")
	private Credor credor;
	
	@JsonFormat(pattern="dd/MM/yyyy", timezone="Brazil/East")
	@Temporal(TemporalType.DATE)
	private Date vencimento;
	
	public Debitos() {
		
		this.valor = 0.;
		this.vencimento = new Date();
		this.status = Status.AVENCER;
	}
	
	public Debitos(Double valor, String status, String vencimento) {
		
		this.valor = 0.;
		this.vencimento = new Date();
		this.status = Status.AVENCER;
	}

	@Override
	public Long getId() {

		return this.id;
	}

	public Double getValor() {
		return valor;
	}

	public Status getStatus() {
		return status;
	}

	public Credor getCredor() {
		return credor;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public Debitos add(Credor credor) {
		
		this.credor = credor;
		return this;
	}
	
	@JsonIgnore
	public String getJsonValue() throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
	
	//TODO pendente de fazer
	@Override
	public String converterToString() throws ConverterToStringException {

		StringBuffer buffer = new StringBuffer().append("").append("|").append("");
		return buffer.toString();

	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ZERO : id.hashCode());
		result = prime * result + ((this.vencimento == null) ? ZERO : vencimento.hashCode());
		result = prime * result + ((this.valor == null) ? ZERO : this.valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Debitos)) {
			return false;
		}
		Debitos other = (Debitos) obj;
		return super.equals(obj) && this.id.equals(other.id) && this.valor.equals(other.valor);
	}
}
