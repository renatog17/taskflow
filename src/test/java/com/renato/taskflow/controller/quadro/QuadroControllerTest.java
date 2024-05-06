package com.renato.taskflow.controller.quadro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.renato.taskflow.domain.Quadro;
import com.renato.taskflow.repositories.QuadroRepository;

@SpringBootTest
public class QuadroControllerTest {

	@InjectMocks
	private QuadroController quadroController;

	@Mock
	private QuadroRepository quadroRepository;

	@BeforeEach
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testCriarQuadro() {
		NovoQuadroDTO novoQuadroDTO = new NovoQuadroDTO("Quadro Teste");
		Quadro quadro = new Quadro();
		quadro.setId(1L);
		System.out.println("a");
		when(quadroRepository.save(any(Quadro.class))).thenReturn(null);
		
		ResponseEntity<?> responseEntity = quadroController.criarQuadro(novoQuadroDTO, UriComponentsBuilder.newInstance());

		assertEquals(ResponseEntity.created(URI.create("/quadro/1")).build().getStatusCode(), responseEntity.getStatusCode());
		verify(quadroRepository, times(1)).save(any(Quadro.class));
	}

}
