package com.notas.registro.mapper;

import java.util.List;

import com.notas.registro.DTO.AsignaturaDTO;
import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.model.Asignatura;
import com.notas.registro.model.Curso;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class AsignaturaMapper implements CursoMapper, ProfesorMapper {

	public static final AsignaturaMapper INSTANCIA = Mappers.getMapper(AsignaturaMapper.class);

	public abstract List<AsignaturaDTO> asignaturaListToAsignaturaDTOList(List<Asignatura> entidadList);

	public abstract List<Asignatura> asignaturaDTOListToAsignaturaList(List<AsignaturaDTO> entidadList);

	public abstract Asignatura asignaturaDTOToAsignatura(AsignaturaDTO asignaturaDTO);

	public abstract AsignaturaDTO asignaturaToAsignaturaDTO(Asignatura asignatura);

	//se implementa este metodo para que no se muestre el profesor jefe del curso al que pertenece la asignatura
	public CursoDTO cursoToCursoDTO(Curso curso) {
		if (curso == null) {
			return null;
		}

		CursoDTO cursoDTO = new CursoDTO();

		cursoDTO.setId(curso.getId());
		cursoDTO.setNombre(curso.getNombre());
		cursoDTO.setGrado(curso.getGrado());

		return cursoDTO;
	}

}