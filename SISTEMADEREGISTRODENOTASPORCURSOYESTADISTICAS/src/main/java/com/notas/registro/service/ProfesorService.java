package com.notas.registro.service;

import java.util.List;

import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.mapper.ProfesorMapper;
import com.notas.registro.model.Profesor;
import com.notas.registro.repository.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<ProfesorDTO> findProfesores() {
        return ProfesorMapper.INSTANCIA.profesorListToProfesorDTOList(profesorRepository.findAll());
    }

    protected Profesor searchProfesor(String rut) {
        try {
            return profesorRepository.getByRut(rut);

        } catch (Exception e) {
            return null;
        }
    }

    public ProfesorDTO findProfesor(String rut) {
        Profesor profesor = searchProfesor(rut);

        if(profesor!=null){
            return ProfesorMapper.INSTANCIA.profesorToProfesorDTO(profesor);
        }
        return null;
    }

    public boolean addProfesor(ProfesorDTO profesorDTO) {

        Profesor profe = searchProfesor(profesorDTO.getRut());

        if (profe == null) {
            Profesor profesor = ProfesorMapper.INSTANCIA.profesorDTOToProfesor(profesorDTO);
            return profesorRepository.saveAndFlush(profesor) != null;
        }

        return false;

    }
}
