package com.notas.registro.repository;

import java.util.Optional;

import com.notas.registro.model.Curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
    public Optional<Curso> findById(int id);
    
}
