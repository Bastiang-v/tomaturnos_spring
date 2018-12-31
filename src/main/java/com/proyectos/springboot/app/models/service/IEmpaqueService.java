package com.proyectos.springboot.app.models.service;

import java.util.List;

import com.proyectos.springboot.app.models.entity.Empaque;

public interface IEmpaqueService {

	
	public List<Empaque> findAll();
	
	public void save(Empaque empaque);
	
	public void edit(Empaque empaque);
	
	public Empaque findOne(String rut);
	
	public void delete(String rut);
	
}
