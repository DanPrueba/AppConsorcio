
package com.cumelen.DanDev.Entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="personas")
public class Persona {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    private String profesion;
    private String patron;
    private String descripcion;
    
    @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "persona_auto",
            joinColumns = {@JoinColumn(name = "FK_persona", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "FK_auto", nullable = false)})
    private Set<Auto> autos = new HashSet<>();

    public Persona() {
    }

    public Persona(String nombre, String apellido, String profesion, String patron, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.profesion = profesion;
        this.patron = patron;
        this.descripcion = descripcion;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Auto> getAutos() {
        return autos;
    }

    public void setAutos(Set<Auto> autos) {
        this.autos = autos;
    }
    
    
    
}
