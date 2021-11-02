package com.notas.registro.service;

import java.util.List;
import java.util.Optional;

import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.mapper.CursoMapper;
import com.notas.registro.model.Curso;
import com.notas.registro.model.Profesor;
import com.notas.registro.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorService profesorService;

    public List<CursoDTO> findCursos() {
        return CursoMapper.INSTANCIA.cursoListToCursoDTOList(cursoRepository.findAll());
    }

    protected Curso searchCurso(int id) {
        try {

            Optional<Curso> curso = cursoRepository.findById(id);
            if (curso.isPresent()) {
                return curso.get();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public CursoDTO findCurso(int id){
        Curso  curso = searchCurso(id);

        if( curso!=null){
            return  CursoMapper.INSTANCIA.cursoToCursoDTO(curso);
        }
        return null;
    }

    public int addCurso(CursoDTO cursoDTO) {

        Profesor profesor = profesorService.searchProfesor(cursoDTO.getProfesor().getRut());
        if (profesor != null && profesor.getCurso()==null) {

            Curso curso = CursoMapper.INSTANCIA.cursoDTOToCurso(cursoDTO);
            try{
                return cursoRepository.saveAndFlush(curso).getId();

            }catch(Exception e){
                //se lanzará una excepción cuando se cree un curso con el mismo nombre e id curso que otra asignatura creada previamente
                return -1;
            }
        }

        return -1;

    }
}
