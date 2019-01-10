package com.proyectos.springboot.app.models.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupo  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

private  String nombre;

@Column(name = "turnos_max")
private int turnosMax;

    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
private Date createAt;

@Column(name = "hora_toma_inicio")
private String horaTomaInicio;
@Column(name = "hora_toma_fin")
private String horaTomaFin;

private int estado;


    @OneToMany(mappedBy = "grupo",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
private List<Empaque> empaques;

    public Grupo() {
        empaques = new ArrayList<Empaque>();
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTurnosMax() {
        return turnosMax;
    }

    public void setTurnosMax(int turnosMax) {
        this.turnosMax = turnosMax;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getHoraTomaInicio() {
        return horaTomaInicio;
    }

    public void setHoraTomaInicio(String horaTomaInicio) {
        this.horaTomaInicio = horaTomaInicio;
    }

    public String getHoraTomaFin() {
        return horaTomaFin;
    }

    public void setHoraTomaFin(String horaTomaFin) {
        this.horaTomaFin = horaTomaFin;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<Empaque> getEmpaques() {
        return empaques;
    }

    public void setEmpaques(List<Empaque> empaques) {
        this.empaques = empaques;
    }

    private static final long serialVesionUID = 1L;
}
