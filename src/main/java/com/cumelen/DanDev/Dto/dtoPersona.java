
package com.cumelen.DanDev.Dto;

import com.cumelen.DanDev.Entity.Auto;
import java.util.List;

public class dtoPersona {
    
    private String nombre;
    private String apellido;
    private String profesion;
    private String patron;
    private String descripcion;
    private List<Auto> autos;

    public dtoPersona() {
    }

    public dtoPersona(String nombre, String apellido, String profesion, String patron, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.profesion = profesion;
        this.patron = patron;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }
    
    

    
    
}
