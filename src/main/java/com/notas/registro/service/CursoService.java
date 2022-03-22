package com.notas.registro.service;

import java.util.List;

import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.mapper.CursoMapper;
import com.notas.registro.model.Curso;
import com.notas.registro.model.Profesor;
import com.notas.registro.repository.CursoRepository;
import com.notas.registro.repository.ProfesorRepository;
import com.notas.registro.repository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    public static final int NULL = 0;
    public static final int OK = 1;
    public static final int TIENE_ALUMNOS = 2;
    public static final int TIENE_ASIGNATURAS = 3;
    public static final int PROFESOR_TIENE_CURSO = 4;
    

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<CursoDTO> findCursos() {
        return CursoMapper.INSTANCIA.cursoListToCursoDTOList(cursoRepository.findAll());
    }


    public CursoDTO findCurso(int id){
        Curso  curso = searchRepository.searchCurso(id);

        if( curso!=null){
            return  CursoMapper.INSTANCIA.cursoToCursoDTO(curso);
        }
        return null;
    }

    public int addCurso(CursoDTO cursoDTO) {

        Profesor profesor = searchRepository.searchProfesor(cursoDTO.getProfesor().getRut());
        if (profesor != null && profesor.getCurso()==null) {

            Curso curso = CursoMapper.INSTANCIA.cursoDTOToCurso(cursoDTO);
            try{
                System.out.println("EL id es .....:"+curso.getId());
                cursoRepository.saveAndFlush(curso).getId();
                return OK;

            }catch(Exception e){
                //se lanzará una excepción cuando se cree un curso con el mismo nombre e id curso que otra asignatura creada previamente
                return NULL;
            }

        }else if(profesor!=null && profesor.getCurso()!=null){
            return PROFESOR_TIENE_CURSO;
        }
        System.out.println("el profesor es"+profesor);

        return -1;

    }

    public int deleteCurso(int id){
        Curso curso = searchRepository.searchCurso(id);

        if(curso==null){
            return NULL;
        }
        // si tiene alumnos, no se puede eliminar
        if(!curso.getAlumnos().isEmpty()){
            return TIENE_ALUMNOS;
        }

        //si tiene asignaturas no se puede eliminar
        if(!curso.getAsignaturas().isEmpty()){
            return TIENE_ASIGNATURAS;
        }

        Profesor p = curso.getProfesor();
        if(p!=null){
            p.setCurso(null);
            profesorRepository.saveAndFlush(p);
        }

        cursoRepository.delete(curso);
        return OK;
    }

    public CursoDTO modifyCurso(CursoDTO cursoDTO){

        if(cursoDTO==null) return null;

        Curso curso = searchRepository.searchCurso(cursoDTO.getId());

        if(curso==null) return null;

        if(cursoDTO.getNombre()!=null){
            curso.setNombre(cursoDTO.getNombre());
        }

        if(cursoDTO.getGrado()!=null){
            curso.setNombre(cursoDTO.getGrado());
        }


        cursoRepository.saveAndFlush(curso);

        return CursoMapper.INSTANCIA.cursoToCursoDTO(curso);
    }
}
