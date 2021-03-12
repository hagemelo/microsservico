package br.com.jhage.dispag.core.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "ORCAMENTO")
public class Orcamento implements JhageEntidade<Orcamento> {

	private static final long serialVersionUID = 1L;

	@Version
	Integer versao;

	@Id
	@Column(name = "ORC_ID", nullable = false)
	@SequenceGenerator(name = "orcid", sequenceName = "GEN_DORC_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcid")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Estado estado;

	@Column(name = "VALORRECEITA")
	private Double valorReceita;

	@Column(name = "ANOMES")
	private String anomes;

	@JsonBackReference
	@OneToMany(mappedBy = "orcamento", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<DetalheOrcamento> detalhes;

	public Orcamento() {

		this.valorReceita = Double.valueOf(ZERO);
		this.detalhes = new HashSet<DetalheOrcamento>();
		this.estado = Estado.PENDENTE;
	}

	public Orcamento(String anomes, Double valorReceita) {

		this.anomes = anomes;
		this.valorReceita = valorReceita;
		this.estado = Estado.PENDENTE;
	}

	@Override
	public Long getId() {

		return this.id;
	}

	public Double getValorReceita() {
		return valorReceita;
	}

	public String getAnomes() {
		return anomes;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public Set<DetalheOrcamento> getDetalhes() {

		if (this.detalhes == null)
			this.detalhes = new HashSet<DetalheOrcamento>();
		return this.detalhes;
	}

	public void aprovar() {
		
		this.estado = Estado.APROVADO;
	}
	
	public void rejeitar() {
		
		this.estado = Estado.REJEITADO;
	}
	
	
	@JsonProperty
	public String saldoDespesasString() throws NumberHelpException {

		return NumberHelp.parseDoubleToString(this.saldoDespesas());
	}
	
	@JsonProperty
	public String getValorReceitaString() throws NumberHelpException {

		return NumberHelp.parseDoubleToString(this.getValorReceita());
	}

	public Double saldoDespesas() {

		Double saldoDespesas = this.detalhes.stream().mapToDouble(DetalheOrcamento::getValor).sum();
		saldoDespesas = saldoDespesas != null ? saldoDespesas : Double.valueOf(ZERO);
		return saldoDespesas;
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
			StringBuffer buffer = new StringBuffer().append("Orcamento").append(SEPARADOR).append(this.anomes)
					.append(SEPARADOR).append(NumberHelp.parseDoubleToString(this.valorReceita));
			result = buffer.toString();

		} catch (NumberHelpException e) {
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
		result = prime * result + ((this.anomes == null) ? ZERO : anomes.hashCode());
		result = prime * result + ((this.valorReceita == null) ? ZERO : valorReceita.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Orcamento)) {
			return false;
		}
		Orcamento other = (Orcamento) obj;
		return super.equals(obj) && this.id.equals(other.id) && this.valorReceita.equals(other.valorReceita)
				&& this.anomes.equals(other.anomes);
	}

}
