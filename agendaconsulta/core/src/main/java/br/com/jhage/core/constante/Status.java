package br.com.jhage.core.constante;

/**
 * 
 * @author Alexsander Melo
 * @since 03/11/2018
 *
 */

public enum Status {
	
	ATIVO("Ativo"), 
	INATIVO("Inativo");
	
	private final String descricao;
	
	Status(final String descricao) {

		this.descricao = descricao;
	}

}
