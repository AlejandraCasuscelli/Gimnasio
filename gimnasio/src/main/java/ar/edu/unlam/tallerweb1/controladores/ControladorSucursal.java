package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;



import ar.edu.unlam.tallerweb1.modelo.Socio;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioSocio;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;
import helpers.Coordenadas;


@Controller

public class ControladorSucursal {
	
	@Inject
	private ServicioSocio servicioSocio;
	@Inject
	private ServicioSucursal servicioSucursal;
	
	@RequestMapping(value="/sucursales")
	public ModelAndView sucursales() {

		ModelMap modelo = new ModelMap();
		
		Usuario usuario = new Usuario();
		
		modelo.put("usuario", usuario);
		
		return new ModelAndView("sucursalMapa", modelo);
	}
	
	@RequestMapping(value="/getSucursales")
	@ResponseBody
	public String getSucursales() {

		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		
		List<Sucursal> listaSucursales = servicioSucursal.listarSucursales();
		ArrayList<Coordenadas> listaCoordenadas = new ArrayList();
		for (Sucursal sucursal : listaSucursales) {
			Coordenadas coordenadaSucursal = new Coordenadas(sucursal.getNombre(),sucursal.getLat(),sucursal.getLng());
			listaCoordenadas.add(coordenadaSucursal);
		}
				
		return Coordenadas.getJson(listaCoordenadas);
	}
	
	
	@RequestMapping("/sucursal/{id}/socios")
	public ModelAndView verListaSocios(@PathVariable Long id) {

		ModelMap modelo = new ModelMap();
		List<Socio> socios = servicioSocio.buscarSocios(id);
		modelo.put("listaSocios", socios);
		
		return new ModelAndView("listaSocios", modelo);
	}
	
	

}
