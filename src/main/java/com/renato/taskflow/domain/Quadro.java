package com.renato.taskflow.domain;

import java.util.List;
import java.util.Objects;

import com.renato.taskflow.controller.quadro.EditarQuadroDTO;
import com.renato.taskflow.controller.quadro.NovoQuadroDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Quadro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "quadro", fetch = FetchType.LAZY)
	private List<Lista> listas;
	private String titulo;
	private Boolean ativo = true;

	public Boolean getAtivo() {
		return ativo;
	}

	public Quadro() {

	}

	public Quadro(List<Lista> listas, String titulo) {
		super();
		this.listas = listas;
		this.titulo = titulo;
	}

	public Quadro(NovoQuadroDTO novoQuadroDTO) {
		this.titulo = novoQuadroDTO.titulo();
	}

	public Quadro(Long quadroId) {
		this.id = quadroId;
	}

	public Quadro atualizar(EditarQuadroDTO editarQuadroDTO) {
		this.titulo = editarQuadroDTO.titulo();
		return this;
	}

	public Long getId() {
		return id;
	}

	public List<Lista> getListas() {
		return listas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void excluirLogicamente() {
		this.ativo = false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quadro other = (Quadro) obj;
		return Objects.equals(id, other.id);
	}

	public void setId(Long id) {
		this.id = id;

	}

}
