package com.renato.taskflow.controller.tarefa;

import java.time.LocalDateTime;

public record EditarTarefaDTO(
		String descricao,
		LocalDateTime prazo) {

}
