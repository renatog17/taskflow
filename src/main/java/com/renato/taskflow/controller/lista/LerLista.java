package com.renato.taskflow.controller.lista;

import com.renato.taskflow.domain.Lista;

public record LerLista(
		Long id,
		String titulo,
		String descricao,
		Long quadroId) {

	public LerLista(Lista lista) {
		this(lista.getId(), lista.getTitulo(), lista.getDescricao(), lista.getQuadro().getId());
	}
}
