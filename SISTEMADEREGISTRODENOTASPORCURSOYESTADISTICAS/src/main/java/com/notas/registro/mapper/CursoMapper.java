package com.notas.registro.mapper;

import java.util.List;

import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.model.Curso;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CursoMapper {

  CursoMapper INSTANCIA= Mappers.getMapper(CursoMapper.class);
  
  List<CursoDTO> cursoListToCursoDTOList(List<Curso> entidadList);
  CursoDTO cursoToCursoDTO(Curso curso);
  

  List<Curso> cursoDTOListToCursoList(List<CursoDTO> entidadList);
  Curso cursoDTOToCurso(CursoDTO cursoDTO);
}
