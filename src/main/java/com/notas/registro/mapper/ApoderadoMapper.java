package com.notas.registro.mapper;

import java.util.List;

import com.notas.registro.DTO.ApoderadoDTO;
import com.notas.registro.model.Apoderado;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApoderadoMapper {

  public static final ApoderadoMapper INSTANCIA= Mappers.getMapper(ApoderadoMapper.class);
  
  public List<ApoderadoDTO> apoderadoListToApoderadoDTOList(List<Apoderado> entidadList);
  public ApoderadoDTO apoderadoToApoderadoDTO(Apoderado apoderado);

  public List<Apoderado> apoderadoDTOListToApoderadoList(List<ApoderadoDTO> entidadList);
  public Apoderado apoderadoDTOToApoderado(ApoderadoDTO apoderadoDTO);
}