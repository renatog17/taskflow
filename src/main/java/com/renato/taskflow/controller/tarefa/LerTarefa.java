package com.renato.taskflow.controller.tarefa;

import java.time.LocalDateTime;

import com.renato.taskflow.domain.Tarefa;

public record LerTarefa(
		String descricao,
		LocalDateTime prazo,
		Long listaId) {

	public LerTarefa(Tarefa tarefa) {
		this(tarefa.getDescricao(), tarefa.getPrazo().toLocalDateTime(), tarefa.getLista().getId());
	}
	
}
