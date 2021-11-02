package com.notas.registro.service;

import java.util.List;

import com.notas.registro.DTO.ApoderadoDTO;
import com.notas.registro.mapper.ApoderadoMapper;
import com.notas.registro.model.Apoderado;
import com.notas.registro.repository.ApoderadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApoderadoService {

    @Autowired
    private ApoderadoRepository apoderadoRepository;

    public List<ApoderadoDTO> findApoderados() {
        return ApoderadoMapper.INSTANCIA.apoderadoListToApoderadoDTOList(apoderadoRepository.findAll());
    }

    protected Apoderado searchApoderado(String rut) {
        try {
            return apoderadoRepository.getByRut(rut);

        } catch (Exception e) {
            return null;
        }
    }

    public ApoderadoDTO findApoderado(String rut){

        Apoderado apoderado = searchApoderado(rut);

        if(apoderado!=null){
            return ApoderadoMapper.INSTANCIA.apoderadoToApoderadoDTO(apoderado);
        }
        return null;
    }

    public boolean addApoderado(ApoderadoDTO apoderadoDTO) {

        Apoderado apoderado = searchApoderado(apoderadoDTO.getRut());

        if (apoderado == null) {

            Apoderado ap = ApoderadoMapper.INSTANCIA.apoderadoDTOToApoderado(apoderadoDTO);

            return apoderadoRepository.saveAndFlush(ap) != null;
        }
        return false;
    }

}
