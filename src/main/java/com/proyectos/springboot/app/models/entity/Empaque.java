package com.proyectos.springboot.app.models.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "empaques")
public class Empaque implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	private String  rut;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotEmpty
	@Email
	private String correo;
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@NotNull
	private int estado;
	@Column(name="id_grupo")
	@NotNull
	private int IdGrupo;
	
	@PrePersist
	public void prepersis() {
		createAt = new Date();
	}
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
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
	public int getIdGrupo() {
		return IdGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		IdGrupo = idGrupo;
	}
	
	
	

}
