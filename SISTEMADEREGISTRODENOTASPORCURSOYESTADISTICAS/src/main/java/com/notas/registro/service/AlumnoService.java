package com.notas.registro.service;

import java.util.List;

import com.notas.registro.DTO.AlumnoDTO;
import com.notas.registro.mapper.AlumnoMapper;
import com.notas.registro.model.Alumno;
import com.notas.registro.model.Apoderado;
import com.notas.registro.model.Curso;
import com.notas.registro.repository.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ApoderadoService apoderadoService;

    @Autowired
    private CursoService cursoService;

    public List<AlumnoDTO> findAlumnos() {
        return AlumnoMapper.INSTANCIA.alumnoListToAlumnoDTOList(alumnoRepository.findAll());
    }

    public Alumno searchAlumno(String rut) {
        try {

            return alumnoRepository.getByRut(rut);

        } catch (Exception e) {

            return null;

        }
    }

    public AlumnoDTO findAlumno(String rut) {
        Alumno alumno = searchAlumno(rut);

        if(alumno!=null){
            return AlumnoMapper.INSTANCIA.alumnoToAlumnoDTO(alumno);
        }
        return null;
    }

    public boolean addAlumno(AlumnoDTO alumnoDTO) {

        Curso curso = cursoService.searchCurso(alumnoDTO.getCurso().getId());
        Apoderado apoderado = apoderadoService.searchApoderado(alumnoDTO.getApoderado().getRut());
        Alumno alumn = searchAlumno(alumnoDTO.getRut());

        if (curso != null && apoderado != null && alumn == null) {
            Alumno alumno = AlumnoMapper.INSTANCIA.alumnoDTOToAlumno(alumnoDTO);
            alumno.setCurso(curso);
            alumno.setApoderado(apoderado);

            apoderado.addAlumno(alumno);
            curso.addAlumno(alumno);

            return alumnoRepository.saveAndFlush(alumno) != null;
        }

        return false;
    }
}
