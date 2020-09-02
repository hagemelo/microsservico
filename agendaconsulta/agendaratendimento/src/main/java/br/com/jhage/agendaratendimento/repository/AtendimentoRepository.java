package br.com.jhage.agendaratendimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jhage.core.modelo.Atendimento;

/**
 * 
 * @author Alexsander Melo
 * @since 02/12/2018
 * 
 */

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {


}
