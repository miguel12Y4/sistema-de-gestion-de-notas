package com.notas.registro.repository;


import com.notas.registro.model.Alumno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String>{
    
    public Alumno getByRut(String rut);

}
