package br.gov.sp.fatec.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.model.Anotacao;
import br.gov.sp.fatec.service.AnotacaoService;
import br.gov.sp.fatec.view.View;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/anotacao")
@CrossOrigin
public class AnotacaoController {
	
	@Autowired
	private AnotacaoService anotacaoService;

	public void setAnotacaoService(AnotacaoService anotacaoService) {
		this.anotacaoService = anotacaoService;
	}
	
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	@JsonView(View.Anotacao.class)
	public ResponseEntity<Anotacao> pesquisarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<Anotacao>(anotacaoService.buscarPorId(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@JsonView(View.Anotacao.class)
	public ResponseEntity<Collection<Anotacao>> getAll() {
		return new ResponseEntity<Collection<Anotacao>>(anotacaoService.todos(), HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@JsonView(View.Anotacao.class)
	public ResponseEntity<Anotacao> salvar(@RequestBody Anotacao anotacao, UriComponentsBuilder uriComponentsBuilder) {
		anotacao = anotacaoService.salvar(anotacao);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + anotacao.getId()).build().toUri());
		return new ResponseEntity<Anotacao>(anotacao, responseHeaders, HttpStatus.CREATED);
	}
	
}
