package com.renato.taskflow.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.renato.taskflow.controller.tarefa.LerTarefa;
import com.renato.taskflow.domain.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	Tarefa getReferenceByIdAndAtivoTrue(Long id);

	Page<Tarefa> findAllByAtivoTrue(Pageable paginacao);

	Optional<Tarefa> findByIdAndAtivoTrue(Long id);
}
