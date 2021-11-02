package com.notas.registro.service;

import java.util.List;
import java.util.Optional;

import com.notas.registro.DTO.AsignaturaDTO;
import com.notas.registro.mapper.AsignaturaMapper;
import com.notas.registro.model.Asignatura;
import com.notas.registro.model.Curso;
import com.notas.registro.model.Profesor;
import com.notas.registro.repository.AsignaturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private CursoService cursoService;
    
    @Autowired
    private ProfesorService profesorService;

    public List<AsignaturaDTO> findAsignaturas() {
        return AsignaturaMapper.INSTANCIA.asignaturaListToAsignaturaDTOList(asignaturaRepository.findAll());
    }

    protected Asignatura searchAsignatura(int id) {
        try {
            Optional<Asignatura> asignatura = asignaturaRepository.findById(id);
            if (asignatura.isPresent()) {
                return asignatura.get();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public AsignaturaDTO findAsignatura(int id){
        Asignatura  asignatura = searchAsignatura(id);

        if( asignatura!=null){
            return  AsignaturaMapper.INSTANCIA.asignaturaToAsignaturaDTO(asignatura);
        }
        return null;
    }
    
    public int addAsignatura(AsignaturaDTO asignaturaDTO) {

        Curso curso = cursoService.searchCurso(asignaturaDTO.getCurso().getId());
        Profesor profesor = profesorService.searchProfesor(asignaturaDTO.getProfesor().getRut());
        

        if (curso != null && profesor != null) {

            Asignatura asignatura = AsignaturaMapper.INSTANCIA.asignaturaDTOToAsignatura(asignaturaDTO);
            asignatura.setCurso(curso);
            asignatura.setProfesor(profesor);

            profesor.addAsignatura(asignatura);
            curso.addAsignatura(asignatura);
            try{
                return asignaturaRepository.saveAndFlush(asignatura).getId();
            }catch (Exception e) {
                //cuanto la misma asignatura tenga el nombre e id del curso, lanzará un error
                return -1;
            }
        }

        return -1;
    }

}