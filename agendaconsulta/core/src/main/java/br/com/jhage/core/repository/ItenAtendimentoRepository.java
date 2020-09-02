package br.com.jhage.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jhage.core.modelo.ItenAtendimento;

/**
 * 
 * @author Alexsander Melo
 * @since 03/11/2018
 * 
 */

@Repository
public interface ItenAtendimentoRepository extends CrudRepository<ItenAtendimento, Long> {

}
