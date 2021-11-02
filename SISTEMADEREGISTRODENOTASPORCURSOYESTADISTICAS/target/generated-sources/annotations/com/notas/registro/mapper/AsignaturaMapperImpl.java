package com.notas.registro.mapper;

import com.notas.registro.DTO.AsignaturaDTO;
import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.model.Asignatura;
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
public class AsignaturaMapperImpl extends AsignaturaMapper {

    @Override
    public List<ProfesorDTO> profesorListToProfesorDTOList(List<Profesor> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<ProfesorDTO> list = new ArrayList<ProfesorDTO>( entidadList.size() );
        for ( Profesor profesor : entidadList ) {
            list.add( profesorToProfesorDTO( profesor ) );
        }

        return list;
    }

    @Override
    public ProfesorDTO profesorToProfesorDTO(Profesor profesor) {
        if ( profesor == null ) {
            return null;
        }

        ProfesorDTO profesorDTO = new ProfesorDTO();

        profesorDTO.setRut( profesor.getRut() );
        profesorDTO.setNombre( profesor.getNombre() );
        profesorDTO.setCorreo( profesor.getCorreo() );

        return profesorDTO;
    }

    @Override
    public List<Profesor> profesorDTOListToProfesorList(List<ProfesorDTO> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<Profesor> list = new ArrayList<Profesor>( entidadList.size() );
        for ( ProfesorDTO profesorDTO : entidadList ) {
            list.add( profesorDTOToProfesor( profesorDTO ) );
        }

        return list;
    }

    @Override
    public Profesor profesorDTOToProfesor(ProfesorDTO profesorDTO) {
        if ( profesorDTO == null ) {
            return null;
        }

        Profesor profesor = new Profesor();

        profesor.setRut( profesorDTO.getRut() );
        profesor.setNombre( profesorDTO.getNombre() );
        profesor.setCorreo( profesorDTO.getCorreo() );

        return profesor;
    }

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

    @Override
    public List<AsignaturaDTO> asignaturaListToAsignaturaDTOList(List<Asignatura> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<AsignaturaDTO> list = new ArrayList<AsignaturaDTO>( entidadList.size() );
        for ( Asignatura asignatura : entidadList ) {
            list.add( asignaturaToAsignaturaDTO( asignatura ) );
        }

        return list;
    }

    @Override
    public List<Asignatura> asignaturaDTOListToAsignaturaList(List<AsignaturaDTO> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<Asignatura> list = new ArrayList<Asignatura>( entidadList.size() );
        for ( AsignaturaDTO asignaturaDTO : entidadList ) {
            list.add( asignaturaDTOToAsignatura( asignaturaDTO ) );
        }

        return list;
    }

    @Override
    public Asignatura asignaturaDTOToAsignatura(AsignaturaDTO asignaturaDTO) {
        if ( asignaturaDTO == null ) {
            return null;
        }

        Asignatura asignatura = new Asignatura();

        asignatura.setId( asignaturaDTO.getId() );
        asignatura.setNombre( asignaturaDTO.getNombre() );
        asignatura.setCurso( cursoDTOToCurso( asignaturaDTO.getCurso() ) );
        asignatura.setProfesor( profesorDTOToProfesor( asignaturaDTO.getProfesor() ) );

        return asignatura;
    }

    @Override
    public AsignaturaDTO asignaturaToAsignaturaDTO(Asignatura asignatura) {
        if ( asignatura == null ) {
            return null;
        }

        AsignaturaDTO asignaturaDTO = new AsignaturaDTO();

        asignaturaDTO.setNombre( asignatura.getNombre() );
        asignaturaDTO.setCurso( cursoToCursoDTO( asignatura.getCurso() ) );
        asignaturaDTO.setProfesor( profesorToProfesorDTO( asignatura.getProfesor() ) );
        asignaturaDTO.setId( asignatura.getId() );

        return asignaturaDTO;
    }
}
