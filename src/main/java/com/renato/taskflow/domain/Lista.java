package com.renato.taskflow.domain;

import java.util.List;
import java.util.Objects;

import com.renato.taskflow.controller.lista.EditarListaDTO;
import com.renato.taskflow.controller.lista.NovaListaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "quadro_id")
	private Quadro quadro;
	@OneToMany(mappedBy = "lista", fetch = FetchType.LAZY)
	private List<Tarefa> tarefas;
	private Boolean ativo = true;
	
	public Lista(String titulo, String descricao, Quadro quadro, List<Tarefa> tarefas) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.quadro = quadro;
		this.tarefas = tarefas;
	}

	public Lista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lista(NovaListaDTO novaListaDTO) {
		this.titulo = novaListaDTO.titulo();
		this.descricao = novaListaDTO.descricao();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Quadro getQuadro() {
		return quadro;
	}

	public void setQuadro(Quadro quadro) {
		this.quadro = quadro;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
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
		Lista other = (Lista) obj;
		return Objects.equals(id, other.id);
	}

	public void atualizar(EditarListaDTO editarListaDTO) {
		this.titulo = editarListaDTO.titulo();
		this.descricao = editarListaDTO.descricao();
		
	}

	public void excluirLogicamente() {
		this.ativo = false;
		
	}

}