package com.proyectos.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectos.springboot.app.models.dao.IEmpaqueDao;
import com.proyectos.springboot.app.models.entity.Empaque;

@Service
public class EmpaqueServiceImp implements IEmpaqueService {

	@Autowired
	private IEmpaqueDao empaqueDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Empaque> findAll() {
		// TODO Auto-generated method stub
		return (List<Empaque>) empaqueDao.findAll();
	}

	@Override
	@Transactional
	public void save(Empaque empaque) {
		// TODO Auto-generated method stub
		empaqueDao.save(empaque);
	}

	@Override
	@Transactional
	public void edit(Empaque empaque) {
		empaqueDao.save(empaque);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Empaque findOne(String rut) {
		// TODO Auto-generated method stub
		return empaqueDao.findById(rut).orElse(null);
	}

	@Override
	@Transactional
	public void delete(String rut) {
		// TODO Auto-generated method stub
		empaqueDao.deleteById(rut);
	}
	
	

}
