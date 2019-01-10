package com.proyectos.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name="turnos")
public class Turno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
private Bloque bloque;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;


@ManyToOne(fetch = FetchType.LAZY)
    private Empaque empaque;

@PrePersist
public void prePersis(){
    createAt = new Date();
}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Empaque getEmpaque() {
        return empaque;
    }

    public void setEmpaque(Empaque empaque) {
        this.empaque = empaque;
    }
    private static final long serialVesionUID = 1L;
}
