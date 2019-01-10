package com.proyectos.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bloques")
public class Bloque implements Serializable {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    private String dia;
private int numPersona;
private int estado;

@Temporal(TemporalType.DATE)
@Column(name = "create_at")
private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "datostoma_id")
    private DatosToma datostoma;

    @ManyToOne(fetch = FetchType.LAZY)
    private Horario horario;

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @PrePersist
    public void prePersis(){
        createAt = new Date();
    }

    @OneToMany(mappedBy = "bloque",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Turno> turnos;

    public Bloque() {
        turnos = new ArrayList<Turno>();
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getNumPersona() {
        return numPersona;
    }

    public void setNumPersona(int numPersona) {
        this.numPersona = numPersona;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    private static final long serialVersionUID = 1L;

}
