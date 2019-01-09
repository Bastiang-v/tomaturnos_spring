package com.proyectos.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectos.springboot.app.models.entity.Empaque;
import com.proyectos.springboot.app.models.service.IEmpaqueService;
import com.proyectos.springboot.app.models.service.IUploadFileService;
import com.proyectos.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("empaque")
public class EmpaqueController {

	@Autowired
	private IEmpaqueService empaqueService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/ver/{rut}")
	public String ver(@PathVariable(value = "rut") String rut, Map<String, Object> model, RedirectAttributes flash) {
		Empaque empaque = empaqueService.findOne(rut);
		if (empaque == null) {
			flash.addFlashAttribute("error", "Empaque no Existe");
			return "redirect:/listar";

		}
		model.put("empaque", empaque);
		model.put("titulo", "Detalle Empaque " + empaque.getNombre() + " " + empaque.getApellido());
		return "ver";
	}

	@RequestMapping(value = { "/listar", "/" }, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Empaque> empaque = empaqueService.findAll(pageRequest);
		PageRender<Empaque> pageRender = new PageRender<>("/listar", empaque);
		model.addAttribute("titulo", "Listado Empaques");
		model.addAttribute("empaques", empaque);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Empaque empaque = new Empaque();
		model.put("empaque", empaque);
		model.put("clase", "/form");
		model.put("titulo", "Crear Empaque");
		return "form";
	}

	@RequestMapping(value = "/edit/{rut}")
	public String editar(@PathVariable(value = "rut") String rut, Map<String, Object> model, RedirectAttributes flash) {
		Empaque empaque = null;
		if (rut.length() > 0) {
			empaque = empaqueService.findOne(rut);
		} else {
			return "redirect:/listar";
		}
		if (empaque == null) {
			flash.addFlashAttribute("error", "Error al editar : Rut no se encuentra en la Base de datos");
			return "redirect:/listar";

		}
		model.put("empaque", empaque);
		model.put("clase", "/edit");
		model.put("titulo", "Editar Empaque");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Empaque empaque, BindingResult result, Model model,
			@RequestParam("file") MultipartFile certificado, @RequestParam("credencial") MultipartFile foto,
			RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("clase", "/form");
			model.addAttribute("titulo", "Crear Empaque");
			return "form";
		}
		if (empaqueService.findOne(empaque.getRut()) != null) {
			model.addAttribute("clase", "/form");
			model.addAttribute("titulo", "Crear Empaque");
			model.addAttribute("mensaje", "Rut Ya se encuentra Registrado");
			flash.addFlashAttribute("warning", "Error al crear  Rut Ya se encuentra Registrado");
			return "form";
		}
		if (!certificado.isEmpty()) {
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(certificado);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Se subido correctamente el certificado '" + uniqueFilename + "'");
			empaque.setCertificado(uniqueFilename);
		}
		if (!foto.isEmpty()) {
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("success2", "Se subido correctamente la Credencial '" + uniqueFilename + "'");
			empaque.setFoto(uniqueFilename);
		}
		empaqueService.save(empaque);
		status.setComplete();
		flash.addFlashAttribute("success",
				"Empaque " + empaque.getNombre() + " " + empaque.getApellido() + " Creado Con exito");
		return "redirect:listar";

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editar(@Valid Empaque empaque, @RequestParam("file") MultipartFile certificado,
			@RequestParam("credencial") MultipartFile foto, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes flash) {
//		certificado es certificado y foto es credencial
		if (result.hasErrors()) {
			model.addAttribute("clase", "form-row");
			model.addAttribute("titulo", "Editar Empaque");
			return "form";

		}
		if (!certificado.isEmpty()) {
			if (empaque.getRut() != null && empaque.getCertificado() != null) {
				uploadFileService.delete(empaque.getCertificado());
			}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(certificado);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("info", "Se subido correctamente el certificado '" + uniqueFilename + "'");
			empaque.setCertificado(uniqueFilename);
		}
		if (!foto.isEmpty()) {
			if (empaque.getRut() != null && empaque.getFoto() != null) {
				uploadFileService.delete(empaque.getFoto());
			}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flash.addFlashAttribute("success2", "Se subido correctamente la credencial '" + uniqueFilename + "'");
			empaque.setFoto(uniqueFilename);
		}
		empaqueService.edit(empaque);
		flash.addFlashAttribute("success",
				"Empaque " + empaque.getNombre() + " " + empaque.getApellido() + " Editado Con exito");
		status.setComplete();
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{rut}")
	public String eliminar(@PathVariable(value = "rut") String rut, RedirectAttributes flash) {
		Empaque empaque = empaqueService.findOne(rut);
		empaqueService.delete(rut);
		flash.addFlashAttribute("success", "Empaque rut : " + rut + " Eliminado Con exito");
		if (empaque.getFoto() != null) {
			if (uploadFileService.delete(empaque.getFoto())) {
				flash.addFlashAttribute("success2", "Credencial " + empaque.getFoto() + " Eliminada con exito");
			}

		}
		if (empaque.getCertificado() != null) {
			if (uploadFileService.delete(empaque.getCertificado())) {
				flash.addFlashAttribute("info", "Certificado " + empaque.getCertificado() + " Eliminado con exito");
			}
		}

		return "redirect:/listar";
	}

}
