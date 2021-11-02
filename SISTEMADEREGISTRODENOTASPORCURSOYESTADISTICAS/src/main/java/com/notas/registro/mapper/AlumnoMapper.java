package com.notas.registro.mapper;

import java.util.List;

import com.notas.registro.DTO.AlumnoDTO;
import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.model.Alumno;
import com.notas.registro.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class AlumnoMapper implements CursoMapper{

  public static AlumnoMapper INSTANCIA= Mappers.getMapper(AlumnoMapper.class);
  
  public abstract List<AlumnoDTO> alumnoListToAlumnoDTOList(List<Alumno> entidadList);

  public abstract AlumnoDTO alumnoToAlumnoDTO(Alumno alumno);

  public abstract List<Alumno> alumnoDTOListToAlumnoList(List<AlumnoDTO> entidadList);
  
  public Alumno alumnoDTOToAlumno(AlumnoDTO alumnoDTO){

    Alumno alumno  = new Alumno();

    alumno.setNombre( alumnoDTO.getNombre() );
    alumno.setRut( alumnoDTO.getRut() );
    alumno.setCorreo( alumnoDTO.getCorreo() );
    
    return alumno;
  }

  //Se implementa este metodo para que no muestre el profesor jefe del curso cuando se muestre el curso del alumno
  public CursoDTO cursoToCursoDTO(Curso curso) {
    if ( curso == null ) {
        return null;
    }

    CursoDTO cursoDTO = new CursoDTO();

    cursoDTO.setGrado( curso.getGrado() );
    cursoDTO.setId( curso.getId() );
    cursoDTO.setNombre( curso.getNombre() );

    return cursoDTO;
}
}