package com.renato.taskflow.controller.quadro;

import jakarta.validation.constraints.NotEmpty;

public record NovoQuadroDTO(
		@NotEmpty
		String titulo) {

}
