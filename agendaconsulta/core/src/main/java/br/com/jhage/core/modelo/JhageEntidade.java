package br.com.jhage.core.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.jhage.core.exception.ConverterToStringException;

/***
 * 
 * @author Alexsander Melo
 * @since 03/11/2018
 * 
 *
 * @param <E> Entidade de domínio
 */

public interface JhageEntidade<E> extends Serializable{

	public Long getId();
	public String converterToString() throws ConverterToStringException;
	public String getJsonValue() throws JsonProcessingException;
}
