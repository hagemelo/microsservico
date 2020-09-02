package br.com.jhage.cadastrarcliente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jhage.core.modelo.Cliente;


/**
 * 
 * @author Alexsander Melo
 * @since 02/12/2018
 * 
 */

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {


}
