package com.renato.taskflow.controller.lista;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.renato.taskflow.domain.Lista;
import com.renato.taskflow.domain.Quadro;
import com.renato.taskflow.repositories.ListaRepository;
import com.renato.taskflow.repositories.QuadroRepository;

@RequestMapping("/lista")
@RestController
public class ListaController {

	@Autowired
	ListaRepository listaRepository;
	@Autowired
	QuadroRepository quadroRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criarLista(@RequestBody NovaListaDTO novaListaDTO, UriComponentsBuilder uriComponentsBuilder){
		Optional<Quadro> quadro = quadroRepository.findById(novaListaDTO.quadroId());
		if(quadro.isPresent()) {
			Lista lista = new Lista(novaListaDTO);			
			lista.setQuadro(quadro.get());
			URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(lista.getId()).toUri();			
			listaRepository.save(lista);
			return ResponseEntity.created(uri).build();
		}
		
		Map<String, String> mensagem = new HashMap<String, String>();
		mensagem.put("mensagem", "quadro informado não foi encontrado");
		
		return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(mensagem);
	}
	
	@GetMapping
	public Page<LerLista> listarListas(@PageableDefault(size = 10) Pageable paginacao){
		return listaRepository.findAllByAtivoTrue(paginacao).map(LerLista::new);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable Long id) {
		Optional<Lista> lista = listaRepository.getReferenceByIdAndAtivoTrue(id);
		if(lista.isPresent())
			return ResponseEntity.ok(new LerLista(lista.get()));
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> editarLista(@PathVariable Long id, @RequestBody EditarListaDTO editarListaDTO){
		Optional<Lista> lista = listaRepository.findById(id);
		if(lista.isPresent()) {
			lista.get().atualizar(editarListaDTO);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluirLista(@PathVariable Long id){
		Optional<Lista> lista = listaRepository.findById(id);
		if(lista.isPresent()) {
			lista.get().excluirLogicamente();
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
