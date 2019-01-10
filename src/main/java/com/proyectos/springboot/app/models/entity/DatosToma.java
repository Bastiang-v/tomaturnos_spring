package com.proyectos.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "datos_toma")
public class DatosToma implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaToma() {
        return fechaToma;
    }

    public void setFechaToma(Date fechaToma) {
        this.fechaToma = fechaToma;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_toma")
    private Date fechaToma;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    private int estado;

    public DatosToma() {
        createAt = new Date();
    }

    private static final long serialVersionUID = 1L;
}
