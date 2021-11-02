package com.notas.registro.mapper;

import java.util.List;

import com.notas.registro.DTO.ApoderadoDTO;
import com.notas.registro.model.Apoderado;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApoderadoMapper {

  ApoderadoMapper INSTANCIA= Mappers.getMapper(ApoderadoMapper.class);
  
  List<ApoderadoDTO> apoderadoListToApoderadoDTOList(List<Apoderado> entidadList);
  ApoderadoDTO apoderadoToApoderadoDTO(Apoderado apoderado);

  List<Apoderado> apoderadoDTOListToApoderadoList(List<ApoderadoDTO> entidadList);
  Apoderado apoderadoDTOToApoderado(ApoderadoDTO apoderadoDTO);
}