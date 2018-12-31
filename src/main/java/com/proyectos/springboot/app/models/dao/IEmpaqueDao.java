package com.proyectos.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.proyectos.springboot.app.models.entity.Empaque;
public interface IEmpaqueDao extends PagingAndSortingRepository<Empaque,String>{
	
	

}
