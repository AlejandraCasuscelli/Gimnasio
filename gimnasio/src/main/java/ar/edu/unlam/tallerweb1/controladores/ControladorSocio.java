package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioSocio;
import helpers.Formulario;

@Controller
public class ControladorSocio {
	@Inject
	private ServicioSocio servicioSocio;
	
	@RequestMapping (path = "/inscribirpase", method = RequestMethod.POST)
	public ModelAndView agregarPaseASocio(@ModelAttribute ("formulario") Formulario formulario) {
		Formulario formularioVacio = new Formulario();
		ModelMap model = new ModelMap();
		model.put("formulario", formularioVacio);
		return new ModelAndView("inscribirpase", model);
	}
	
	@RequestMapping (path = "seleccionarpase", method = RequestMethod.POST)
	public ModelAndView insertarPaseEnSocio(@ModelAttribute ("formulario") Formulario formulario) {
		servicioSocio.agregarPaseASocio(formulario.getIdSocio(), formulario.getIdPase());
		return new ModelAndView("cargacompleta");
	}
}