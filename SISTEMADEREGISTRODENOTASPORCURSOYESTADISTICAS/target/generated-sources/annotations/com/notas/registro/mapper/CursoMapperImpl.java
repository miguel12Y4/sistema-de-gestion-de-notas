package com.notas.registro.mapper;

import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.model.Curso;
import com.notas.registro.model.Profesor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-02T11:46:50-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 11.0.12 (Debian)"
)
public class CursoMapperImpl implements CursoMapper {

    @Override
    public List<CursoDTO> cursoListToCursoDTOList(List<Curso> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<CursoDTO> list = new ArrayList<CursoDTO>( entidadList.size() );
        for ( Curso curso : entidadList ) {
            list.add( cursoToCursoDTO( curso ) );
        }

        return list;
    }

    @Override
    public CursoDTO cursoToCursoDTO(Curso curso) {
        if ( curso == null ) {
            return null;
        }

        CursoDTO cursoDTO = new CursoDTO();

        cursoDTO.setId( curso.getId() );
        cursoDTO.setNombre( curso.getNombre() );
        cursoDTO.setGrado( curso.getGrado() );
        cursoDTO.setProfesor( profesorToProfesorDTO( curso.getProfesor() ) );

        return cursoDTO;
    }

    @Override
    public List<Curso> cursoDTOListToCursoList(List<CursoDTO> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<Curso> list = new ArrayList<Curso>( entidadList.size() );
        for ( CursoDTO cursoDTO : entidadList ) {
            list.add( cursoDTOToCurso( cursoDTO ) );
        }

        return list;
    }

    @Override
    public Curso cursoDTOToCurso(CursoDTO cursoDTO) {
        if ( cursoDTO == null ) {
            return null;
        }

        Curso curso = new Curso();

        curso.setId( cursoDTO.getId() );
        curso.setNombre( cursoDTO.getNombre() );
        curso.setGrado( cursoDTO.getGrado() );
        curso.setProfesor( profesorDTOToProfesor( cursoDTO.getProfesor() ) );

        return curso;
    }

    protected ProfesorDTO profesorToProfesorDTO(Profesor profesor) {
        if ( profesor == null ) {
            return null;
        }

        ProfesorDTO profesorDTO = new ProfesorDTO();

        profesorDTO.setRut( profesor.getRut() );
        profesorDTO.setNombre( profesor.getNombre() );
        profesorDTO.setCorreo( profesor.getCorreo() );

        return profesorDTO;
    }

    protected Profesor profesorDTOToProfesor(ProfesorDTO profesorDTO) {
        if ( profesorDTO == null ) {
            return null;
        }

        Profesor profesor = new Profesor();

        profesor.setRut( profesorDTO.getRut() );
        profesor.setNombre( profesorDTO.getNombre() );
        profesor.setCorreo( profesorDTO.getCorreo() );

        return profesor;
    }
}
