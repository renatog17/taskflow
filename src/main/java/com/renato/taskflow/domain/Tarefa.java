package com.renato.taskflow.domain;

import java.sql.Timestamp;
import java.util.Objects;

import com.renato.taskflow.controller.tarefa.EditarTarefaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Timestamp prazo;
	@ManyToOne
	@JoinColumn(name = "lista_id")
	private Lista lista;
	private Boolean ativo = true;

	public Tarefa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tarefa(String descricao, Timestamp prazo, Lista lista) {
		super();
		this.descricao = descricao;
		this.prazo = prazo;
		this.lista = lista;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Timestamp getPrazo() {
		return prazo;
	}

	public Lista getLista() {
		return lista;
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
		Tarefa other = (Tarefa) obj;
		return Objects.equals(id, other.id);
	}

	public void atualizar(EditarTarefaDTO editarTarefaDTO) {
		this.descricao = editarTarefaDTO.descricao();
		this.prazo = Timestamp.valueOf(editarTarefaDTO.prazo());
		
	}

}
