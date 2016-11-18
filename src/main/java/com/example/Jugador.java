package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

/**
 * Created by Navy on 13/10/2016.
 */
@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String nombre;

    @JsonIgnore
    @Column
    private LocalDate fechaNacimiento;

    @Column
    @Min(0)
    private int canastas;

    @Column
    @Min(0)
    private int asistencias;

    @Column
    @Min(0)
    private int rebotes;

    @Enumerated(EnumType.STRING)
    private Posicion posicion;

    //Relación de jugador con equipo, un jugador sólo puede estar en un equipo
    @ManyToOne
    private Equipo equipo;

    //Objeto Java Bean de Jugador
    public Jugador(){

    }

    public Jugador(String nombre, LocalDate fechaNacimiento, int canastas, int asistencias, int rebotes, Posicion posicion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.canastas = canastas;
        this.asistencias = asistencias;
        this.rebotes = rebotes;
        this.posicion = posicion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCanastas() {
        return canastas;
    }

    public void setCanastas(int canastas) {
        this.canastas = canastas;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", canastas=" + canastas +
                ", asistencias=" + asistencias +
                ", rebotes=" + rebotes +
                ", posicion=" + posicion +
                '}';
    }
}
