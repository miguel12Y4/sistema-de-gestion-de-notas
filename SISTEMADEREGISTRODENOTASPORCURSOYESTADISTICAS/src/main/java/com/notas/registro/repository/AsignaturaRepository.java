package com.notas.registro.repository;

import java.util.Optional;

import com.notas.registro.model.Asignatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer>{
    
    public Optional<Asignatura> findById(int id);

}
