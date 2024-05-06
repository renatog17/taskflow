package com.renato.taskflow.controller.quadro;

import jakarta.validation.constraints.NotEmpty;

public record EditarQuadroDTO(
		@NotEmpty
		String titulo) {

}
