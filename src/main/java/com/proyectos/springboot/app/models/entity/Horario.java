package com.proyectos.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "horarios")
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;

 private String nombre;

    @Column(name = "hora_inicio")
    @Temporal(TemporalType.DATE)
    private Date horaInicio;


    @Column(name = "hora_termino")
    @Temporal(TemporalType.DATE)
    private Date horaTermino;

    @OneToMany(mappedBy = "horario",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Bloque> bloques;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Date horaTermino) {
        this.horaTermino = horaTermino;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Bloque> getBloques() {
        return bloques;
    }

    public void setBloques(List<Bloque> bloques) {
        this.bloques = bloques;
    }

    private static final long serialVersionUID = 1L;
}
