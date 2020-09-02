package br.com.jhage.core.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import br.com.jhage.core.exception.ConverterToStringException;
import br.com.jhage.core.exception.FirstNameException;
import br.com.jhage.core.helper.FirstAndLastNameHelper;

/**
 * 
 * @author Alexsander Melo
 * @since 11/11/2014
 * 
 */
@Entity
@Table(name = "CLIENTE")
public class Cliente implements JhageEntidade<Cliente>{

	private static final long serialVersionUID = 1L;
	private static final int ZERO = 0;

	@Version
	Integer versao;
	
	@Id
	@Column(name = "CLIENTE_ID", nullable = false)
	@SequenceGenerator(name = "clienteid", sequenceName = "GEN_CLIENTE_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clienteid")
	private Long id;

	private String nome;
	private String apelido;
	private String endereco;
	private String contato;
	
	@Transient
	private FirstAndLastNameHelper firstAndLastNameHelper;
	
	public Cliente(String nome, String apelido, String endereco, String contato) {
		
		this.nome = nome;
		this.apelido = apelido;
		this.endereco = endereco;
		this.contato = contato;
		this.firstAndLastNameHelper = new FirstAndLastNameHelper();
	}
	
	public Cliente() {
		
		this.nome = "";
		this.apelido = "";
		this.endereco = "";
		this.contato = "";
		this.firstAndLastNameHelper = new FirstAndLastNameHelper();
	}

	@Override
	public Long getId() {

		return this.id;
	}

	@Override
	public String converterToString() throws ConverterToStringException {

		return this.nome;
	}
	
	public String getNome() {
		return nome;
	}

	public String getApelido() {
		return apelido;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getContato() {
		return contato;
	}
	
	@JsonIgnore
	public String getJsonValue() throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
	
	public String primeiroNome() {
		
		try {
			return this.firstAndLastNameHelper.getFirstName(this.nome);
		
		} catch (FirstNameException e) {
			
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? ZERO : id.hashCode());
		result = prime * result + ((this.nome == null) ? ZERO : this.nome.hashCode());
		result = prime * result + ((this.apelido == null) ? ZERO : this.apelido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cliente)) {
			return false;
		}
		Cliente other = (Cliente) obj;
		return super.equals(obj) && this.id.equals(other.id) 
				&& this.nome.equals(other.nome)
				&& this.apelido.equals(other.apelido);
	}
	

}
