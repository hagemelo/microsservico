package br.com.jhage.agendaratendimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jhage.core.modelo.Cliente;


/**
 * 
 * @author Alexsander Melo
 * @since 02/12/2018
 * 
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("select c " +
			   "from Cliente c "+
			   "where c.nome like :nome " +
			    	  "and  c.apelido like :apelido ")
	public Cliente carregarCliente(@Param("nome") String nome, @Param("apelido")String apelido);
}
