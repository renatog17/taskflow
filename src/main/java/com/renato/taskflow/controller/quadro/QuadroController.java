package com.renato.taskflow.controller.quadro;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.renato.taskflow.domain.Quadro;
import com.renato.taskflow.repositories.QuadroRepository;

import jakarta.transaction.Transactional;

@RequestMapping("/quadro")
@RestController
public class QuadroController {

	@Autowired
	QuadroRepository quadroRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criarQuadro(@RequestBody NovoQuadroDTO novoQuadroDTO,
			UriComponentsBuilder uriComponentsBuilder) {
		Quadro quadro = new Quadro(novoQuadroDTO);
		quadroRepository.save(quadro);

		URI uri = uriComponentsBuilder.path("/quadro/{id}").buildAndExpand(quadro.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> lerQuadro(@PathVariable Long id){
		return ResponseEntity.ok(new LerQuadro(quadroRepository.getReferenceById(id)));
	}
	
	@GetMapping
	public Page<LerQuadro> listarQuadros(@PageableDefault(size = 10, sort = { "titulo" }) Pageable paginacao) {
		return quadroRepository.findAllByAtivoTrue(paginacao).map(LerQuadro::new);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizarQuadro(@PathVariable Long id, @RequestBody EditarQuadroDTO editarQuadroDTO) {
		Quadro quadro = quadroRepository.getReferenceById(id);
		if (quadro.getAtivo()) {
			quadro.atualizar(editarQuadroDTO);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarQuadro(@PathVariable Long id) {
		quadroRepository.getReferenceById(id).excluirLogicamente();
		return ResponseEntity.noContent().build();
	}
}
