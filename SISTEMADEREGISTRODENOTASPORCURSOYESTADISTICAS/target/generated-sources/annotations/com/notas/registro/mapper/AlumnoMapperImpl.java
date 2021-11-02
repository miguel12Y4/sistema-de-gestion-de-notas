package com.notas.registro.mapper;

import com.notas.registro.DTO.AlumnoDTO;
import com.notas.registro.DTO.ApoderadoDTO;
import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.model.Alumno;
import com.notas.registro.model.Apoderado;
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
public class AlumnoMapperImpl extends AlumnoMapper {

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
    public List<AlumnoDTO> alumnoListToAlumnoDTOList(List<Alumno> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<AlumnoDTO> list = new ArrayList<AlumnoDTO>( entidadList.size() );
        for ( Alumno alumno : entidadList ) {
            list.add( alumnoToAlumnoDTO( alumno ) );
        }

        return list;
    }

    @Override
    public AlumnoDTO alumnoToAlumnoDTO(Alumno alumno) {
        if ( alumno == null ) {
            return null;
        }

        AlumnoDTO alumnoDTO = new AlumnoDTO();

        alumnoDTO.setRut( alumno.getRut() );
        alumnoDTO.setNombre( alumno.getNombre() );
        alumnoDTO.setCorreo( alumno.getCorreo() );
        alumnoDTO.setApoderado( apoderadoToApoderadoDTO( alumno.getApoderado() ) );
        alumnoDTO.setCurso( cursoToCursoDTO( alumno.getCurso() ) );

        return alumnoDTO;
    }

    @Override
    public List<Alumno> alumnoDTOListToAlumnoList(List<AlumnoDTO> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<Alumno> list = new ArrayList<Alumno>( entidadList.size() );
        for ( AlumnoDTO alumnoDTO : entidadList ) {
            list.add( alumnoDTOToAlumno( alumnoDTO ) );
        }

        return list;
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

    protected ApoderadoDTO apoderadoToApoderadoDTO(Apoderado apoderado) {
        if ( apoderado == null ) {
            return null;
        }

        String rut = null;
        String nombre = null;
        String correo = null;

        rut = apoderado.getRut();
        nombre = apoderado.getNombre();
        correo = apoderado.getCorreo();

        ApoderadoDTO apoderadoDTO = new ApoderadoDTO( rut, nombre, correo );

        return apoderadoDTO;
    }
}
