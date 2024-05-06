package com.renato.taskflow.controller.tarefa;

import java.net.URI;
import java.sql.Timestamp;
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
import com.renato.taskflow.domain.Tarefa;
import com.renato.taskflow.repositories.ListaRepository;
import com.renato.taskflow.repositories.TarefaRepository;

@RequestMapping("/tarefa")
@RestController
public class TarefaController {

	@Autowired
	TarefaRepository tarefaRepository;
	@Autowired
	ListaRepository listaRepository;
	
	@PostMapping
	public ResponseEntity<?> criarTarefa(@RequestBody NovaTarefaDTO novaTarefaDTO, UriComponentsBuilder uriComponentsBuilder){
		Optional<Lista> lista = listaRepository.findById(novaTarefaDTO.listaId());
		if(lista.isPresent()) {
			Tarefa tarefa = new Tarefa(novaTarefaDTO.descricao(), Timestamp.valueOf(novaTarefaDTO.prazo()), lista.get());
			tarefaRepository.save(tarefa);
			URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(tarefa.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		Map<String, String> mensagem = new HashMap<String, String>();
		mensagem.put("mensagem", "lista não encontrada");
		return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(mensagem);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> lerTarefa(@PathVariable Long id){
		return ResponseEntity.ok( new LerTarefa(tarefaRepository.getReferenceByIdAndAtivoTrue(id)));
	}
	
	@GetMapping
	public Page<LerTarefa> lerTarefas(@PageableDefault(size = 10) Pageable paginacao){
		return tarefaRepository.findAllByAtivoTrue(paginacao).map(LerTarefa::new);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> editarTarefa(@PathVariable Long id, @RequestBody EditarTarefaDTO editarTarefaDTO){
		Optional<Tarefa> tarefa= tarefaRepository.findById(id);
		if(tarefa.isPresent()) {
			tarefa.get().atualizar(editarTarefaDTO);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarTarefa(@PathVariable Long id){
		Optional<Tarefa> tarefa = tarefaRepository.findByIdAndAtivoTrue(id);
		if(tarefa.isPresent()) {
			tarefa.get().excluirLogicamente();
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
