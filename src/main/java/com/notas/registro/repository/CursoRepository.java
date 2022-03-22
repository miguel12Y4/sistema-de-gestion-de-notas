package com.notas.registro.repository;

import java.util.Optional;

import com.notas.registro.model.Curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
    public Optional<Curso> findById(int id);

    @Query(value = "select COUNT(*) from curso c join asignatura a on c.id = a.id_curso where a.cerrada = :idCurso",
    nativeQuery = true)
    int asignaturasCerradas (@Param("idCurso") int idCurso);
    
}
