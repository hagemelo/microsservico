package br.com.jhage.core.modelo;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhage.core.constante.Hora;
import br.com.jhage.core.constante.Plano;
import br.com.jhage.core.constante.StatusAtendimento;
import br.com.jhage.core.exception.ConverterToStringException;

/**
 * 
 * @author Alexsander Melo
 * @since 01/12/2018
 * 
 */
@Entity
@Table(name = "ATENDIMENTO")
public class Atendimento implements JhageEntidade<Atendimento> {

	private static final long serialVersionUID = 1L;
	private static final int ZERO = 0;

	@Version
	Integer versao;

	@Id
	@Column(name = "ATENDIMENTO_ID", nullable = false)
	@SequenceGenerator(name = "atdid", sequenceName = "GEN_ATENDIMENTO_ID", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atdid")
	private Long id;

	@Column(name = "PRONTUARIO")
	private String prontuario;

	@Enumerated(EnumType.STRING)
	@Column(name = "HORA")
	private Hora hora;

	@ManyToOne
	@JoinColumn(name = "cliente_id", referencedColumnName = "CLIENTE_ID")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "profissional_id", referencedColumnName = "PROFISSIONAL_ID")
	private Profissional profissional;

	@Enumerated(EnumType.STRING)
	@Column(name = "PLANO")
	private Plano plano;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private StatusAtendimento status;

	private String obs;

	public Atendimento(String prontuario, Hora hora, Cliente cliente, Profissional profissional, Plano plano,
			String obs) {

		this.prontuario = prontuario;
		this.hora = hora;
		this.cliente = cliente;
		this.profissional = profissional;
		this.plano = plano;
		this.obs = obs;
		this.status = StatusAtendimento.AGENDADO;
	}
	
	public Atendimento() {
		
		this.prontuario = "";
		this.hora = Hora.H0800;
		this.plano = Plano.PARTICULAR;
		this.obs = "";
		this.status = StatusAtendimento.AGENDADO;
	}

	@Override
	public Long getId() {

		return this.id;
	}

	@Override
	public String converterToString() throws ConverterToStringException {

		StringBuffer buffer = new StringBuffer().append("Sr(a) ").append(this.cliente.primeiroNome().toString())
				.append("|").append(this.hora.toString()).append("|").append(this.plano.toString());
		return buffer.toString();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProntuario() {
		return prontuario;
	}

	public Hora getHora() {
		return hora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public Plano getPlano() {
		return plano;
	}

	public StatusAtendimento getStatus() {
		return status;
	}

	public String getObs() {
		return obs;
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
		result = prime * result + ((this.prontuario == null) ? ZERO : this.prontuario.hashCode());
		result = prime * result + ((this.hora == null) ? ZERO : this.hora.hashCode());
		result = prime * result + ((this.plano == null) ? ZERO : this.plano.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Atendimento)) {
			return false;
		}
		Atendimento other = (Atendimento) obj;
		return super.equals(obj) && this.id.equals(other.id) && this.prontuario.equals(other.prontuario)
				&& this.hora.equals(other.hora) && this.plano.equals(other.plano);
	}

}
