package com.notas.registro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.model.Profesor;

@Mapper
public interface ProfesorMapper {

  ProfesorMapper INSTANCIA= Mappers.getMapper(ProfesorMapper.class);
  
  List<ProfesorDTO> profesorListToProfesorDTOList(List<Profesor> entidadList);
  ProfesorDTO profesorToProfesorDTO(Profesor profesor);

  List<Profesor> profesorDTOListToProfesorList(List<ProfesorDTO> entidadList);
  Profesor profesorDTOToProfesor(ProfesorDTO profesorDTO);
}