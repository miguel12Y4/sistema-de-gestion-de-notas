package com.notas.registro.mapper;

import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.model.Profesor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-02T11:46:50-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 11.0.12 (Debian)"
)
public class ProfesorMapperImpl implements ProfesorMapper {

    @Override
    public List<ProfesorDTO> profesorListToProfesorDTOList(List<Profesor> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<ProfesorDTO> list = new ArrayList<ProfesorDTO>( entidadList.size() );
        for ( Profesor profesor : entidadList ) {
            list.add( profesorToProfesorDTO( profesor ) );
        }

        return list;
    }

    @Override
    public ProfesorDTO profesorToProfesorDTO(Profesor profesor) {
        if ( profesor == null ) {
            return null;
        }

        ProfesorDTO profesorDTO = new ProfesorDTO();

        profesorDTO.setRut( profesor.getRut() );
        profesorDTO.setNombre( profesor.getNombre() );
        profesorDTO.setCorreo( profesor.getCorreo() );

        return profesorDTO;
    }

    @Override
    public List<Profesor> profesorDTOListToProfesorList(List<ProfesorDTO> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<Profesor> list = new ArrayList<Profesor>( entidadList.size() );
        for ( ProfesorDTO profesorDTO : entidadList ) {
            list.add( profesorDTOToProfesor( profesorDTO ) );
        }

        return list;
    }

    @Override
    public Profesor profesorDTOToProfesor(ProfesorDTO profesorDTO) {
        if ( profesorDTO == null ) {
            return null;
        }

        Profesor profesor = new Profesor();

        profesor.setRut( profesorDTO.getRut() );
        profesor.setNombre( profesorDTO.getNombre() );
        profesor.setCorreo( profesorDTO.getCorreo() );

        return profesor;
    }
}
