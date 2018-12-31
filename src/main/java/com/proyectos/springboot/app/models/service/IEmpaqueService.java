package com.proyectos.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyectos.springboot.app.models.entity.Empaque;

public interface IEmpaqueService {

	
	public List<Empaque> findAll();
	
	public Page<Empaque> findAll(Pageable pageable);
	
	public void save(Empaque empaque);
	
	public void edit(Empaque empaque);
	
	public Empaque findOne(String rut);
	
	public void delete(String rut);
	
}
