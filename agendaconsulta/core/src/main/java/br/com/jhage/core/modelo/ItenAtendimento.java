package br.com.jhage.core.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhage.core.constante.Procedimento;
import br.com.jhage.core.exception.ConverterToStringException;
import br.com.jhage.core.exception.NumberHelpException;
import br.com.jhage.core.helper.NumberHelp;

/**
 * 
 * @author Alexsander Melo
 * @since 11/11/2014
 * 
 */
@Entity
@Table(name = "ITENATENDIMENTO")
public class ItenAtendimento implements JhageEntidade<ItenAtendimento>{

	private static final long serialVersionUID = 1L;
	private static final int ZERO = 0;

	@Version
	Integer versao;

	@Id
	@Column(name = "ITEN_ATENDIMENTO_ID", nullable = false)
	@SequenceGenerator(name = "itatdid", sequenceName = "GEN_ITATENDIMENTO_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itatdid")
	private Long id;

	@Column(name = "VALOR")
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	private Procedimento procedimento;
	
	@Transient
	private NumberHelp numberHelp;
	
	public ItenAtendimento() {
		
		this.valor = 0.;
		numberHelp  = new NumberHelp();
	}
	
	public ItenAtendimento(Procedimento proc, Double valor) {
		
		this.procedimento = proc;
		this.valor = valor;
	}
	
	public Double getValor() {
		return valor;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	@Override
	public Long getId() {

		return this.id;
	}

	@JsonIgnore
	public String getJsonValue() throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
	
	@Override
	public String converterToString() throws ConverterToStringException  {
		
		try {
			StringBuffer buffer = new StringBuffer()
					.append(this.getProcedimento().toString())
					.append("|")
					.append(numberHelp.parseDoubleToString(this.valor));
			return buffer.toString();
		}catch (NumberHelpException e) {
			
			throw new ConverterToStringException(e);
		}
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
		if (!(obj instanceof ItenAtendimento)) {
			return false;
		}
		ItenAtendimento other = (ItenAtendimento) obj;
		return super.equals(obj) && this.id.equals(other.id) && this.valor.equals(other.valor);
	}
}
