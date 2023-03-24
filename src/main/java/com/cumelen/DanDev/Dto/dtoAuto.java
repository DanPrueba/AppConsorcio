package com.cumelen.DanDev.Dto;

import com.cumelen.DanDev.Entity.Persona;
import java.util.List;

public class dtoAuto {
    
    private String modelo;
    private String patente;
    private List<Persona> personas;

    public dtoAuto() {
    }

    public dtoAuto(String modelo, String patente) {
        this.modelo = modelo;
        this.patente = patente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    
    
    
}
