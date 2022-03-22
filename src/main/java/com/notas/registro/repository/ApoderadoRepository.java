package com.notas.registro.repository;

import com.notas.registro.model.Apoderado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApoderadoRepository extends JpaRepository<Apoderado, String> {
    
    public Apoderado getByRut(String rut);
    
}
