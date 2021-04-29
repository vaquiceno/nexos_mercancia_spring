package com.nexos.mercancia.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="usuarios_nexos")
@SecondaryTable(name = "cargos_nexos", pkJoinColumns=@PrimaryKeyJoinColumn(name="id"))
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer edad;
    @Column(table="cargos_nexos", name="nombre")
    private String cargoname;
    private LocalDate fechaIngreso;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, Integer edad, String cargo, LocalDate fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.cargoname = cargo;
        this.fechaIngreso = fechaIngreso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCargoname() {
        return cargoname;
    }

    public void setCargoname(String cargoname) {
        this.cargoname = cargoname;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
