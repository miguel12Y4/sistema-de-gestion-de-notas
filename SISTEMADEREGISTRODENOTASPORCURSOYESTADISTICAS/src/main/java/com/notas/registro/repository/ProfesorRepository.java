package com.notas.registro.repository;

import com.notas.registro.model.Profesor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor , String>{
    
    public Profesor getByRut(String rut);
    
}
