package com.proyectos.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectos.springboot.app.models.entity.Empaque;
import com.proyectos.springboot.app.models.service.IEmpaqueService;
import com.proyectos.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("empaque")
public class EmpaqueController {
	
    @Autowired
	private IEmpaqueService empaqueService;
	
	
	@RequestMapping(value= {"/listar","/"},method=RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page,Model model) {
		Pageable pageRequest  = PageRequest.of(page,5); 
		Page<Empaque> empaque = empaqueService.findAll(pageRequest);
		PageRender<Empaque> pageRender = new PageRender<>("/listar", empaque);
		model.addAttribute("titulo","Listado Empaques");
		model.addAttribute("empaques",empaque);
		model.addAttribute("page", pageRender);
		return "listar";
	}
	
	
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		Empaque empaque = new Empaque();
		model.put("empaque", empaque);
		model.put("clase","form-row");
	 model.put("titulo","Crear Empaque");
	 return "form";
	}
	
	@RequestMapping(value="/edit/{rut}")
	public String editar(@PathVariable(value="rut") String rut,Map<String, Object> model,RedirectAttributes flash) {
	Empaque empaque = null;
	if (rut.length()>0) {
		empaque = empaqueService.findOne(rut);
	}else {
		return "redirect:/listar";
	} 
	if (empaque == null) {
		flash.addFlashAttribute("error","Error al editar : Rut no se encuentra en la Base de datos");
		return "redirect:/listar";
		
	}
	model.put("empaque", empaque);
	model.put("clase","invisible");
	model.put("titulo","Editar Empaque");
		return "forme";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Empaque empaque, BindingResult result,Model model,RedirectAttributes flash,SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("clase","form-row");
			model.addAttribute("titulo","Crear Empaque");
			return "form";
		}
		try {
			Empaque empq = null;
			empq = empaqueService.findOne(empaque.getRut());
			
			if (empaque.getRut().equals(empq.getRut())) {
				model.addAttribute("clase","form-row");
				model.addAttribute("titulo","Crear Empaque");
				model.addAttribute("mensaje","Rut Ya se encuentra Registrado");
				flash.addFlashAttribute("error","Error al crear : Rut Ya se encuentra Registrado");
				return "form";
			} else {
				empaqueService.save(empaque);
			return "redirect:listar";
			}
		} catch (Exception e) {
			empaqueService.save(empaque);
			status.setComplete();
			flash.addFlashAttribute("success","Empaque Creado Con exito");
			return "redirect:listar";
		}
		
		
	}
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editar(@Valid Empaque empaque, BindingResult result,Model model,SessionStatus status,RedirectAttributes flash) {
		
		if (result.hasErrors()) {
			model.addAttribute("clase","form-row");
			model.addAttribute("titulo","Editar Empaque");
			return "form";
			
		}
		empaqueService.edit(empaque);
		flash.addFlashAttribute("success","Empaque Editado Con exito");
		status.setComplete();
		return "redirect:listar";
	}
	@RequestMapping(value="/eliminar/{rut}")
	public String eliminar(@PathVariable(value="rut") String rut, RedirectAttributes flash) {
		empaqueService.delete(rut);
		flash.addFlashAttribute("success","Empaque Eliminado Con exito");
		return "redirect:/listar"; 
	}
	

}
