package br.com.jhage.core.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhage.core.exception.ConverterToStringException;

/**
 * 
 * @author Alexsander Melo
 * @since 01/12/2018
 * 
 */
@Entity
@Table(name = "PROFISSIONAL")
public class Profissional implements JhageEntidade<Profissional>{

	private static final long serialVersionUID = 1L;
	private static final int ZERO = 0;

	@Version
	Integer versao;

	@Id
	@Column(name = "PROFISSIONAL_ID", nullable = false)
	@SequenceGenerator(name = "profid", sequenceName = "GEN_PROFISSIONAL_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profid")
	private Long id;

	private String nome;
	
	@Column(name = "CRO", nullable = false, unique = true)
	private String cro;

	@Column(name = "ESPECIALIDADE")
	private String especialidade;
	
	public Profissional(String nome, String cro, String especialidade) {
		
		this.nome = nome;
		this.cro = cro;
		this.especialidade = especialidade;
	}
	
	public Profissional() {
		
		this.nome = "";
		this.cro = "";
		this.especialidade = "";
	}

	public String getNome() {
		return nome;
	}

	public String getCro() {
		return cro;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	@Override
	public Long getId() {

		return this.id;
	}

	@Override
	public String converterToString() throws ConverterToStringException {

		return this.nome;
	}
	
	@JsonIgnore
	public String getJsonValue() throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
	
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ZERO : id.hashCode());
		result = prime * result + ((this.nome == null) ? ZERO : this.nome.hashCode());
		result = prime * result + ((this.cro == null) ? ZERO : this.cro.hashCode());
		result = prime * result + ((this.especialidade == null) ? ZERO : this.especialidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Profissional)) {
			return false;
		}
		Profissional other = (Profissional) obj;
		return super.equals(obj) && this.id.equals(other.id) 
				&& this.nome.equals(other.nome)
				&& this.cro.equals(other.cro)
				&& this.especialidade.equals(other.especialidade);
	}
	
	
}
