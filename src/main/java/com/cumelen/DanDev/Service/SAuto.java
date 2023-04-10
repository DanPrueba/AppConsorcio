
package com.cumelen.DanDev.Service;

import com.cumelen.DanDev.Entity.Auto;
import com.cumelen.DanDev.Repository.IAutoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SAuto {
    
    @Autowired
    IAutoRepository iAutoRepository;
    
    public List<Auto> listaAutos(){
        return iAutoRepository.findAll();
    }
    
    public Auto traerAuto(int id){
        return iAutoRepository.findById(id).orElse(null);
    }
    
    
    public void guardarAuto(Auto auto){
        iAutoRepository.save(auto);
    }
    
    public void eliminarAuto(int id){
        iAutoRepository.deleteById(id);
    }
    
    public boolean existById(int id ){
        return iAutoRepository.existsById(id);
    }
    
    public boolean existByPatente(String patente ){
       return iAutoRepository.existsByPatente(patente);
    }
    
    public Optional<Auto> getByPatente(String patente){
        return iAutoRepository.findByPatente(patente);
    }
}
