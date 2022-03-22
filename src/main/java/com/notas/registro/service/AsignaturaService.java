package com.notas.registro.service;

import java.util.ArrayList;
import java.util.List;

import com.notas.registro.DTO.AsignaturaDTO;
import com.notas.registro.DTO.DetalleNotas;
import com.notas.registro.DTO.Libreta;
import com.notas.registro.DTO.Promedio;
import com.notas.registro.DTO.ResumenAsignatura;
import com.notas.registro.mapper.AsignaturaMapper;
import com.notas.registro.model.Alumno;
import com.notas.registro.model.Asignatura;
import com.notas.registro.model.Curso;
import com.notas.registro.model.Profesor;
import com.notas.registro.repository.AsignaturaRepository;
import com.notas.registro.repository.CursoRepository;
import com.notas.registro.repository.ProfesorRepository;
import com.notas.registro.repository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaService {

    public static final int NULL = 0;
    public static final int OK = 1;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    
    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AlumnoService alumnoService;

    public List<AsignaturaDTO> findAsignaturas() {
        return AsignaturaMapper.INSTANCIA.asignaturaListToAsignaturaDTOList(asignaturaRepository.findAll());
    }

    public AsignaturaDTO findAsignatura(int id){
        Asignatura  asignatura = searchRepository.searchAsignatura(id);

        if( asignatura!=null){
            return  AsignaturaMapper.INSTANCIA.asignaturaToAsignaturaDTO(asignatura);
        }
        return null;
    }
    
    public int addAsignatura(AsignaturaDTO asignaturaDTO) {

        Curso curso = searchRepository.searchCurso(asignaturaDTO.getCurso().getId());
        Profesor profesor = searchRepository.searchProfesor(asignaturaDTO.getProfesor().getRut());
        

        if (curso != null && profesor != null) {

            Asignatura asignatura = AsignaturaMapper.INSTANCIA.asignaturaDTOToAsignatura(asignaturaDTO);
            asignatura.setCurso(curso);
            asignatura.setProfesor(profesor);

            profesor.addAsignatura(asignatura);
            curso.addAsignatura(asignatura);
            try{
                return asignaturaRepository.saveAndFlush(asignatura).getId();
            }catch (Exception e) {
                //cuanto la misma asignatura tenga el nombre e id del curso, lanzará un error
                return -1;
            }
        }

        return -1;
    }

    public int deleteAsignatura(int id){
        Asignatura asignatura = searchRepository.searchAsignatura(id);

        if(asignatura==null){
            return NULL;
        }

        Curso c = asignatura.getCurso();
        if(c!=null){
            c.removeAsignatura(asignatura);
            cursoRepository.saveAndFlush(c);
        }

        Profesor p = asignatura.getProfesor();
        if(p!=null){
            p.removeAsignatura(asignatura);
            profesorRepository.saveAndFlush(p);
        }


        asignaturaRepository.delete(asignatura);
        return OK;
    }

    public AsignaturaDTO modifyAsignatura(AsignaturaDTO asignaturaDTO){

        if(asignaturaDTO==null) return null;

        Asignatura asignatura = searchRepository.searchAsignatura(asignaturaDTO.getId());

        if(asignatura==null) return null;

        if(asignaturaDTO.getNombre()!=null){
            asignatura.setNombre(asignaturaDTO.getNombre());
        }

        asignaturaRepository.saveAndFlush(asignatura);

        return AsignaturaMapper.INSTANCIA.asignaturaToAsignaturaDTO(asignatura);
    }

    public List<Promedio> notasYRanking(int idAsignatura){
        
        Asignatura asignatura = searchRepository.searchAsignatura(idAsignatura);

        if(asignatura==null || !asignatura.getEstaCerrada()){

            return new ArrayList<>();
        }

        Object[] alumnos = asignaturaRepository.getPromedioAlumnosAndRanking(idAsignatura);

        List<Promedio> promedios = new ArrayList<>();
        for (Object o : alumnos){
            
            Object[] alumno = (Object[]) o;
            Promedio p = new Promedio((String)alumno[0],(double)alumno[1], idAsignatura);
            promedios.add(p);
        }
        return promedios;
    }

    public int puestoRanking(String rutAlumno, int idAsignatura){

        List<Promedio> promedios = notasYRanking(idAsignatura);

        for(Promedio p : promedios){
            if(p.getRut().equals(rutAlumno)){
                return promedios.indexOf(p)+1;
            }
        }
        return -1;
    }

    public double getPromedioByAsignatura(int idAsignatura){

        double promedio = asignaturaRepository.getPromedioByAsignatura(idAsignatura);

        if(promedio>=10){
            return promedio;
        }

        return -1;
    }

    public List<Promedio> getAllPromedioByRut(String rutAlumno){

        Object[] promedios = asignaturaRepository.getAllPromediosByRut(rutAlumno);

        List<Promedio> promediosAsignaturas = new ArrayList<>();

        for(Object o : promedios){
            
            Object[] prom = (Object[])o;

            Promedio p = new Promedio((String)prom[0],(Double)prom[2], (int)prom[1]);

            promediosAsignaturas.add(p);

        }

        return promediosAsignaturas;
    }

    public double getPromedioFinal(String rutAlumno){

        double promedioFinal = asignaturaRepository.getPromedioFinalByRut(rutAlumno);

        if(promedioFinal>=10){
            return promedioFinal;
        }

        return -1;
    }

    public Libreta getLibretaCompleta(String rut){
        Alumno alumno = searchRepository.searchAlumno(rut);

        if(alumno==null) return null;
        if(alumno.getCurso()==null) return null;

        List<Asignatura> asignaturas = alumno.getCurso().getAsignaturas();

        List<ResumenAsignatura> detalles = new ArrayList<>();

        for(Asignatura as : asignaturas){
            if(!as.isCerrada()) return null;

            DetalleNotas d = alumnoService.misNotas(as.getId(), rut);
            Double promedio = alumnoService.getPromedioByAsignatura(as.getId(), rut);
            int ranking = puestoRanking(rut, as.getId());


            ResumenAsignatura r = new ResumenAsignatura(as.getId(),promedio, ranking, d);

            detalles.add(r);
        }

        Double promedioFinal = getPromedioFinal(rut);

        return new Libreta(rut, alumno.getNombre(), alumno.getCurso().getId(), promedioFinal, detalles);
        
    }

    
}