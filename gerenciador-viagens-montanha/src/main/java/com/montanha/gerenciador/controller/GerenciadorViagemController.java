package com.montanha.gerenciador.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;


import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.montanha.gerenciador.dtos.ViagemDto;
import com.montanha.gerenciador.entities.Viagem;
import com.montanha.gerenciador.responses.Response;
import com.montanha.gerenciador.services.ViagemServiceException;
import com.montanha.gerenciador.services.ViagemServices;

@RestController
@RequestMapping("/api/viagens")
public class GerenciadorViagemController {
	
	@Autowired
	private ViagemServices viagemService;

	/*
	 * A anotação @PostMapping identifica que o método que estamos criando é do tipo
	 * POST , na frente da nossa anotação temos path = “/new”, isso significa que
	 * para acessar este método que iremos construir devemos adicionar este /new ao
	 * caminho que tínhamos quando acessamos este controlador, ficando da seguinte
	 * forma :http://localhost:8089/api/viagens/new.
	 */	
	/*
	 * @Valid: lembra daquelas validações que colocamos em nosso ViagemDto, em
	 * relação a tamanho e obrigatoriedade? Está anotação verifica se o objeto
	 * ViagemDto passado atende todas as validações. Então vincula o resultado a
	 * variável result, que será utilizada para verificar se temos erros e
	 * apresentá-los no retorno no método.
	 */
	/*
	 * @RequestBody: ele habilita a deserialização automática do nosso objeto
	 * ViagemDto. Então se passarmos para o método um JSON esta anotação consegue de
	 * forma simples transformar estes dados em um objeto ViagemDto.:
	 */		
		
	@PostMapping(path = "/new")
	public ResponseEntity<Response<Viagem>> cadastrar(@Valid @RequestBody ViagemDto viagemDto, BindingResult result) {
		Response<Viagem> response = new Response<Viagem>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Viagem viagemSalva = this.viagemService.salvar(viagemDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(viagemDto.getId())
				.toUri();
		response.setData(viagemSalva);
		return ResponseEntity.created(location).body(response);
	}

	//o método Listar da classe viagemService utiliza
	// viagemRepository.findAll();
	
	@GetMapping
	public ResponseEntity<List<Viagem>> listar() {
		List<Viagem> viagens = viagemService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(viagens);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Viagem>> buscar(@PathVariable("id") Long id) throws ViagemServiceException {
  
		Viagem viagem = viagemService.buscar(id);
		Response<Viagem> response = new Response<Viagem>();
		response.setData(viagem);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
