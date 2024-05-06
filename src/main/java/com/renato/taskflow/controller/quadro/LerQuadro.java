package com.renato.taskflow.controller.quadro;

import com.renato.taskflow.domain.Quadro;

public record LerQuadro(
		String titulo) {
	public LerQuadro(Quadro quadro) {
		this(quadro.getTitulo());
	}
}
