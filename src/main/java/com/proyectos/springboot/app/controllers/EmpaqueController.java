package com.proyectos.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.proyectos.springboot.app.models.entity.Empaque;
import com.proyectos.springboot.app.models.service.IEmpaqueService;

@Controller
@SessionAttributes("empaque")
public class EmpaqueController {
	
    @Autowired
	private IEmpaqueService empaqueService;
	
	
	@RequestMapping(value= {"/listar","/"},method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado Empaques");
		model.addAttribute("empaques",empaqueService.findAll());
		return "listar";
	}
	
	
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		Empaque empaque = new Empaque();
		model.put("empaque", empaque);
		model.put("clase","form-group row");
	 model.put("titulo","Crear Empaque");
	 return "form";
	}
	
	@RequestMapping(value="/edit/{rut}")
	public String editar(@PathVariable(value="rut") String rut,Map<String, Object> model) {
	Empaque empaque = null;
	if (rut.length()>0) {
		empaque = empaqueService.findOne(rut);
	}else {
		return "redirect:/listar";
	}
	model.put("empaque", empaque);
	model.put("clase","invisible");
	model.put("titulo","Editar Empaque");
		return "forme";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Empaque empaque, BindingResult result,Model model,SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("clase","form-group row");
			model.addAttribute("titulo","Crear Empaque");
			return "form";
		}
		try {
			Empaque empq = null;
			empq = empaqueService.findOne(empaque.getRut());
			
			if (empaque.getRut().equals(empq.getRut())) {
				model.addAttribute("clase","form-group row");
				model.addAttribute("titulo","Crear Empaque");
				model.addAttribute("mensaje","Rut Ya se encuentra Registrado");
				return "form";
			} else {
				empaqueService.save(empaque);
			return "redirect:listar";
			}
		} catch (Exception e) {
			empaqueService.save(empaque);
			status.setComplete();
			return "redirect:listar";
		}
		
		
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editar(@Valid Empaque empaque, BindingResult result,Model model,SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("clase","form-group row");
			model.addAttribute("titulo","Editar Empaque");
			return "form";
			
		}
		empaqueService.edit(empaque);
		status.setComplete();
		return "redirect:listar";
	}
	@RequestMapping(value="/eliminar/{rut}")
	public String eliminar(@PathVariable(value="rut") String rut) {
		empaqueService.delete(rut);
		return "redirect:/listar"; 
	}
	

}
