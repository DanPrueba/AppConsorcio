
package com.cumelen.DanDev.Controller;

import com.cumelen.DanDev.Dto.dtoAuto;
import com.cumelen.DanDev.Entity.Auto;
import com.cumelen.DanDev.Mensaje.Mensaje;
import com.cumelen.DanDev.Service.SAuto;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("autos")
@CrossOrigin(origins = "http://localhost:4200/")
public class AutoController {
    
    @Autowired
    SAuto sAuto;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Auto>> listaAutos(){
        return new ResponseEntity(sAuto.listaAutos(), HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Auto> traerAuto( @PathVariable("id") int id ){
        Auto auto = sAuto.traerAuto(id);
        return new ResponseEntity(auto, HttpStatus.OK);
    } 
    
    
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarAuto(@RequestBody dtoAuto dto) {
        Auto auto = new Auto(dto.getModelo(), dto.getPatente());
        sAuto.guardarAuto(auto);
        return new ResponseEntity(new Mensaje("Auto creado correctamente."), HttpStatus.CREATED);
    }
    
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarAuto(@PathVariable("id") int id){
        sAuto.eliminarAuto(id);
        return new ResponseEntity(new Mensaje("Auto eliminado."), HttpStatus.OK );
    }
    

    
}
