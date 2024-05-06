package com.renato.taskflow.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.renato.taskflow.domain.Lista;

public interface ListaRepository extends JpaRepository<Lista, Long>{

	Page<Lista> findAllByAtivoTrue(Pageable paginacao);

	Optional<Lista> getReferenceByIdAndAtivoTrue(Long id);

}
