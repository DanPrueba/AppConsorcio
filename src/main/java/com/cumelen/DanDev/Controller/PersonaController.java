
package com.cumelen.DanDev.Controller;

import com.cumelen.DanDev.Dto.dtoPersona;
import com.cumelen.DanDev.Entity.Auto;
import com.cumelen.DanDev.Entity.Persona;
import com.cumelen.DanDev.Mensaje.Mensaje;
import com.cumelen.DanDev.Service.SAuto;
import com.cumelen.DanDev.Service.SPersona;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("personas")
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin(origins = "https://cumelen-vla.web.app/")
public class PersonaController {
    
    @Autowired
    SPersona sPersona;
    
    @Autowired
    SAuto sAuto;
     
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> traerPersonas(){
        return new ResponseEntity(sPersona.listaPersonas(), HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Persona> traerPersona( @PathVariable("id") int id ){
        if(!sPersona.existById(id))
            return new ResponseEntity(new Mensaje("La persona no existe."), HttpStatus.NOT_FOUND);
        Persona persona = sPersona.traerPersona(id);
        return new ResponseEntity(persona, HttpStatus.OK);
    } 
    
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPersona(@RequestBody dtoPersona dto) {
        if( StringUtils.isBlank(dto.getNombre()))
            return new ResponseEntity( new Mensaje("Falta agregar el nombre de la persona."), HttpStatus.BAD_REQUEST);
        if( StringUtils.isBlank(dto.getApellido()))
            return new ResponseEntity( new Mensaje("Falta agregar el apellido de la persona."), HttpStatus.BAD_REQUEST);        
        if( StringUtils.isBlank(dto.getProfesion()))
            return new ResponseEntity( new Mensaje("Falta agregar la Profesion de la persona."), HttpStatus.BAD_REQUEST);        
        if( StringUtils.isBlank(dto.getPatron()))
            return new ResponseEntity( new Mensaje("Falta agregar el patron/casa donde trabaja la persona."), HttpStatus.BAD_REQUEST); 
        
        Persona perso = new Persona(dto.getNombre(), dto.getApellido(), dto.getProfesion(), dto.getPatron(), dto.getDescripcion());
        sPersona.guardarPersona(perso);
        return new ResponseEntity(new Mensaje("Persona creada correctamente."), HttpStatus.CREATED);
    }   
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update( @PathVariable("id") int id, @RequestBody dtoPersona dto ){
    if(!sPersona.existById(id))
            return new ResponseEntity( new Mensaje("La persona no existe."), HttpStatus.BAD_REQUEST);
    if(StringUtils.isBlank(dto.getNombre()))
            return new ResponseEntity( new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
    Persona nuevaPersona = sPersona.traerPersona(id);
    nuevaPersona.setNombre(dto.getNombre());
    nuevaPersona.setApellido(dto.getApellido());
    nuevaPersona.setProfesion(dto.getProfesion());
    nuevaPersona.setPatron(dto.getPatron());
    nuevaPersona.setDescripcion(dto.getDescripcion());
    sPersona.guardarPersona(nuevaPersona);
    
     return new ResponseEntity( new Mensaje("Persona actualizada correctamente.") , HttpStatus.OK);
    }
    
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable("id") int id){
        if(!sPersona.existById(id))
            return new ResponseEntity(new Mensaje("La persona no existe."), HttpStatus.NOT_FOUND);
        
        Persona personaSeleccionada = sPersona.traerPersona(id);
        personaSeleccionada.setAutos(null);
        sPersona.eliminarPersona(id);
        return new ResponseEntity(new Mensaje("Persona eliminada."), HttpStatus.OK );
    }
    
    @PutMapping("/{persoId}/autos/{autoId}")
    public ResponseEntity<Persona> asignarAutoAEmpleado(
            @PathVariable int persoId,
            @PathVariable int autoId
    ){
        sPersona.asignarAutoAPersona(persoId, autoId);
        return new ResponseEntity(new Mensaje("Auto asignado correctamente."), HttpStatus.OK);
    }
    
    //Borrar un auto de la lista de la persona
    @DeleteMapping("/{persoId}/eliminarauto/{autoId}")
    public ResponseEntity<?> eliminarAutodePersona(@PathVariable int persoId,
                                                   @PathVariable int autoId){
        
        if(!sPersona.existById(persoId))
            return new ResponseEntity(new Mensaje("La persona no existe."), HttpStatus.NOT_FOUND);
        if(!sAuto.existById(autoId))
            return new ResponseEntity( new Mensaje("El auto con ese ID no existe."), HttpStatus.BAD_REQUEST);
        Persona persona = sPersona.traerPersona(persoId);
        Auto autoSeleccionado = sAuto.traerAuto(autoId);   
        persona.getAutos().remove(autoSeleccionado);     
        sPersona.guardarPersona(persona);      
        return new ResponseEntity(new Mensaje("Auto eliminado de la lista."), HttpStatus.OK);
    }
    
}
    
    
