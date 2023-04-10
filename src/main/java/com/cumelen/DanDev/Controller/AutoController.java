
package com.cumelen.DanDev.Controller;

import com.cumelen.DanDev.Dto.dtoAuto;
import com.cumelen.DanDev.Entity.Auto;
import com.cumelen.DanDev.Mensaje.Mensaje;
import com.cumelen.DanDev.Service.SAuto;
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
@RequestMapping("autos")
//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin(origins = "https://cumelen-vla.web.app/")

public class AutoController {
    
    @Autowired
    SAuto sAuto;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Auto>> listaAutos(){
        return new ResponseEntity(sAuto.listaAutos(), HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Auto> traerAuto( @PathVariable("id") int id ){
        if(!sAuto.existById(id))
            return new ResponseEntity(new Mensaje("El auto no existe."), HttpStatus.NOT_FOUND);
        Auto auto = sAuto.traerAuto(id);
        return new ResponseEntity(auto, HttpStatus.OK);
    } 
    
    
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarAuto(@RequestBody dtoAuto dto) {
        if( StringUtils.isBlank(dto.getModelo()))
            return new ResponseEntity( new Mensaje("Falta agregar el modelo del auto."), HttpStatus.BAD_REQUEST);
        if( StringUtils.isBlank(dto.getPatente()))
            return new ResponseEntity( new Mensaje("Falta agregar la Patente del auto."), HttpStatus.BAD_REQUEST);
        if( sAuto.existByPatente(dto.getPatente()))
            return new ResponseEntity( new Mensaje("La Patente ya existe en la base de datos."), HttpStatus.BAD_REQUEST);
        Auto auto = new Auto(dto.getModelo(), dto.getPatente());
        sAuto.guardarAuto(auto);
        return new ResponseEntity(new Mensaje("Auto creado correctamente."), HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update( @PathVariable("id") int id, @RequestBody dtoAuto dto ){
    if(!sAuto.existById(id))
            return new ResponseEntity( new Mensaje("El auto con ese ID no existe."), HttpStatus.BAD_REQUEST);
    if(sAuto.existByPatente(dto.getPatente()) && sAuto.getByPatente(dto.getPatente()).get().getId() != id)
            return new ResponseEntity( new Mensaje("La Patente ya existe en la base de datos."), HttpStatus.BAD_REQUEST);  
    if(StringUtils.isBlank(dto.getPatente()))
            return new ResponseEntity( new Mensaje("Falta agregar la Patente."), HttpStatus.BAD_REQUEST);

    Auto auto = sAuto.traerAuto(id);
    auto.setModelo(dto.getModelo());
    auto.setPatente(dto.getPatente());
    sAuto.guardarAuto(auto);

    return new ResponseEntity( new Mensaje("Auto actualizado correctamente.") , HttpStatus.OK);

    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarAuto(@PathVariable("id") int id){
        sAuto.eliminarAuto(id);
        return new ResponseEntity(new Mensaje("Auto eliminado."), HttpStatus.OK );
    }
    

    
}
