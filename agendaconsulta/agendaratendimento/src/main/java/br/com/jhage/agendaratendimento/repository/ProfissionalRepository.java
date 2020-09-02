package br.com.jhage.agendaratendimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jhage.core.modelo.Profissional;

/**
 * 
 * @author Alexsander Melo
 * @since 02/12/2018
 *
 */
@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{
	
	
	@Query("select p " +
			   "from Profissional p "+
			   "where p.nome like :nome " +
			    	  "and  p.cro like :cro "+
			    	  "and  p.especialidade like :especialidade ")
	public Profissional carregarProfissional(@Param("nome") String nome, @Param("cro") String cro, @Param("especialidade") String especialidade);

}
