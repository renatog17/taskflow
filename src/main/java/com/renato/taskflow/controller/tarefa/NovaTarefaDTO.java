package com.renato.taskflow.controller.tarefa;

import java.time.LocalDateTime;

public record NovaTarefaDTO(
		String descricao,
		LocalDateTime prazo,
		Long listaId
		) {

}
