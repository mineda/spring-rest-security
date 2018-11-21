package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Anotacao;

public interface AnotacaoService {
	
	public Anotacao salvar(Anotacao autorizacao);
	
	public void excluir(Long idAnotacao);
	
	public List<Anotacao> todos();
	
	public List<Anotacao> buscarPorUsuario(String nome);
	
	public Anotacao buscarPorId(Long idAnotacao);

}
