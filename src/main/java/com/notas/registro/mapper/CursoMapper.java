package com.notas.registro.mapper;

import java.util.List;

import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.model.Curso;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CursoMapper {

  public static final CursoMapper INSTANCIA= Mappers.getMapper(CursoMapper.class);
  
  public List<CursoDTO> cursoListToCursoDTOList(List<Curso> entidadList);
  public CursoDTO cursoToCursoDTO(Curso curso);
  

  public List<Curso> cursoDTOListToCursoList(List<CursoDTO> entidadList);
  public Curso cursoDTOToCurso(CursoDTO cursoDTO);
}
