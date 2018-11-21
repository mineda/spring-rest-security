package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Anotacao;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.AnotacaoRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("anotacaoService")
@Transactional
public class AnotacaoServiceImpl implements AnotacaoService {

	@Autowired
	private AnotacaoRepository anotacaoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public void setAnotacaoRepo(AnotacaoRepository anotacaoRepo) {
		this.anotacaoRepo = anotacaoRepo;
	}
	
	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Anotacao salvar(Anotacao anotacao) {
		if(anotacao.getUsuario() != null) {
			Usuario usuario = anotacao.getUsuario();
			if(usuario.getId() != null) {
				usuario = usuarioRepo.findById(usuario.getId()).get();
			}
			else {
				usuario = usuarioRepo.save(usuario);
			}
		}
		anotacao.setDataHora(new Date());
		return anotacaoRepo.save(anotacao);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void excluir(Long idAnotacao) {
		anotacaoRepo.deleteById(idAnotacao);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Anotacao> todos() {
		List<Anotacao> retorno = new ArrayList<Anotacao>();
		for(Anotacao anotacao: anotacaoRepo.findAll()) {
			retorno.add(anotacao);
		}
		return retorno;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Anotacao> buscarPorUsuario(String nome) {
		if(nome == null || nome.isEmpty()) {
			return todos();
		}
		return anotacaoRepo.findByUsuarioNome(nome);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Anotacao buscarPorId(Long idAnotacao) {
		Optional<Anotacao> anotacao = anotacaoRepo.findById(idAnotacao);
		if(anotacao.isPresent()) {
			return anotacao.get();
		}
		return null;
	}

}
