
package com.cumelen.DanDev.Service;

import com.cumelen.DanDev.Entity.Auto;
import com.cumelen.DanDev.Entity.Persona;
import com.cumelen.DanDev.Repository.IAutoRepository;
import com.cumelen.DanDev.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SPersona {
    
    @Autowired
    IPersonaRepository iPersonaRepository;
    
    @Autowired
    IAutoRepository iAutoRepository;
    
    public List<Persona> listaPersonas(){
        return iPersonaRepository.findAll();
    }
    
    public Persona traerPersona(int id){
        return iPersonaRepository.findById(id).orElse(null);
    }
            
    public void guardarPersona(Persona persona){
        iPersonaRepository.save(persona);
    }
    
    public void eliminarPersona(int id){
        iPersonaRepository.deleteById(id);
    }
    
    public boolean existById(int id ){
        return iPersonaRepository.existsById(id);
    }
    
    public Persona asignarAutoAPersona(int persona_id, int auto_id){
        Set<Auto> listaAutos = null; 
        Persona persona = iPersonaRepository.findById(persona_id).get();
        Auto auto = iAutoRepository.findById(auto_id).get();
        listaAutos = persona.getAutos();
        listaAutos.add(auto);
        persona.setAutos(listaAutos);
        return iPersonaRepository.save(persona);   
    }
    
}
