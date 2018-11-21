package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Anotacao;

public interface AnotacaoRepository extends CrudRepository<Anotacao, Long> {
	
	public List<Anotacao> findByUsuarioNome(String nome);

}
