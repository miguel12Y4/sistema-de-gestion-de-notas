package com.notas.registro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.model.Profesor;

@Mapper
public interface ProfesorMapper {

  public static final ProfesorMapper INSTANCIA= Mappers.getMapper(ProfesorMapper.class);
  
  public List<ProfesorDTO> profesorListToProfesorDTOList(List<Profesor> entidadList);
  public ProfesorDTO profesorToProfesorDTO(Profesor profesor);

  public List<Profesor> profesorDTOListToProfesorList(List<ProfesorDTO> entidadList);
  public Profesor profesorDTOToProfesor(ProfesorDTO profesorDTO);
}