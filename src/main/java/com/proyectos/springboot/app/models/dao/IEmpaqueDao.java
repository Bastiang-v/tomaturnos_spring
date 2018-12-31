package com.proyectos.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.proyectos.springboot.app.models.entity.Empaque;
public interface IEmpaqueDao extends CrudRepository<Empaque,String>{
	
	

}
