
package com.cumelen.DanDev.Controller;

import com.cumelen.DanDev.Dto.dtoPersona;
import com.cumelen.DanDev.Entity.Persona;
import com.cumelen.DanDev.Mensaje.Mensaje;
import com.cumelen.DanDev.Service.SPersona;
import java.util.List;
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
public class PersonaController {
    
    @Autowired
    SPersona sPersona;
     
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
        Persona perso = new Persona(dto.getNombre(), dto.getApellido(), dto.getProfesion(), dto.getPatron(), dto.getDescripcion());
        sPersona.guardarPersona(perso);
        return new ResponseEntity(new Mensaje("Persona creada correctamente."), HttpStatus.CREATED);
    }
    
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable("id") int id){
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
    
    
    
}
