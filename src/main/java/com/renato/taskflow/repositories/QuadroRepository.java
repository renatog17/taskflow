package com.renato.taskflow.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.renato.taskflow.controller.quadro.LerQuadro;
import com.renato.taskflow.domain.Quadro;

public interface QuadroRepository extends JpaRepository<Quadro, Long>{

	Page<Quadro> findAllByAtivoTrue(Pageable pageable);

}
