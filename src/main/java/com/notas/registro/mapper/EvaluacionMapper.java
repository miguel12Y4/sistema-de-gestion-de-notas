package com.notas.registro.mapper;

import java.util.List;

import com.notas.registro.DTO.EvaluacionDTO;
import com.notas.registro.model.Evaluacion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EvaluacionMapper {

  public static final EvaluacionMapper INSTANCIA= Mappers.getMapper(EvaluacionMapper.class);
  
  public List<EvaluacionDTO> evaluacionListToEvaluacionDTOList(List<Evaluacion> entidadList);
  public EvaluacionDTO evaluacionToEvaluacionDTO(Evaluacion evaluacion);

  public List<Evaluacion> evaluacionDTOListToEvaluacionList(List<EvaluacionDTO> entidadList);
  public Evaluacion evaluacionDTOToEvaluacion(EvaluacionDTO evaluacionDTO);
}