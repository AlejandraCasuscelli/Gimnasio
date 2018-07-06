package ar.edu.unlam.tallerweb1.controladores;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.assertj.core.util.DateUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Actividad;
import ar.edu.unlam.tallerweb1.modelo.Pago;
import ar.edu.unlam.tallerweb1.modelo.Socio;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioActividad;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPago;
import ar.edu.unlam.tallerweb1.servicios.ServicioSocio;
import ar.edu.unlam.tallerweb1.servicios.ServicioSucursal;

@RestController
public class ControladorAdministrador {

	
	@Inject
	private ServicioSocio servicioSocio;
	@Inject
	private ServicioSucursal servicioSucursal;
	@Inject
	private ServicioPago servicioPago;

	
	
	@RequestMapping(path = "/homeAdmin")
	public ModelAndView irAHomeAdministrador() {
		ModelMap modelo = new ModelMap();
		modelo.put("listaSucursales", servicioSucursal.listarSucursales());
		return new ModelAndView("homeAdministrador", modelo);
	}

	@RequestMapping(path = "socios/")
	public ModelAndView listarSocios(){
		ModelMap modelo = new ModelMap();
		modelo.put("listaSocios", servicioSocio.buscarSocios());
		return new ModelAndView("socios",modelo);
	}
	
	@RequestMapping(path = "socios")
	public ModelAndView listarSocios(@RequestParam(value="idSucursal") Long idSucursal){
		ModelMap modelo = new ModelMap();
		modelo.put("listaSocios", servicioSocio.buscarSocios(idSucursal));
		return new ModelAndView("socios",modelo);
	}
	
	@RequestMapping(path = "ganancias/")
	public ModelAndView irAGanancias(){
		ModelMap modelo = new ModelMap();
		
		modelo.put("listaSucursales", servicioSucursal.listarSucursales());
		return new ModelAndView("ganancias",modelo);
	}
	
	
	@RequestMapping(path="ganancias", method = RequestMethod.GET)
	public ModelAndView verGanancias(@RequestParam(value="sucursal") String id, @RequestParam(value="periodo") String periodo) throws ParseException{
		Long idSucursal = Long.parseLong(id);
		Date fechaDesde = new SimpleDateFormat("yyyy-MM-dd").parse(periodo);
	    
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(fechaDesde);
		cal.add(Calendar.MONTH, 1);
		Date fechaHasta = cal.getTime();
		
		List<Socio> socios = servicioSocio.buscarSocios(idSucursal);
	    List<Pago> pagos = servicioPago.listaPagos(socios, fechaDesde, fechaHasta);
		Double ganancia = servicioPago.getTotalRecaudado(pagos);
	    Sucursal sucursal = servicioSucursal.getSucursal(idSucursal);
		
		ModelMap modelo = new ModelMap();
		modelo.put("listaPagos", pagos);
		modelo.put("total", ganancia);
		modelo.put("sucursal", sucursal);
		
		return new ModelAndView("listaPagos", modelo);
	}

	@RequestMapping(path = "/sucursales", method = RequestMethod.GET)
	public ModelAndView irASucursales(){
		ModelMap modelo = new ModelMap();
		modelo.put("listaSucursales", servicioSucursal.listarSucursales());
		return new ModelAndView("listaSucursales",modelo);
	}
	
	@RequestMapping(path = "/sucursal/{id}/modificar", method = RequestMethod.GET)
	public ModelAndView irAModificarSucursal(@PathVariable Long id) {
		ModelMap modelo = new ModelMap();
		Sucursal sucursalVacia = new Sucursal();
		modelo.put("sucursal", servicioSucursal.getSucursal(id));
		modelo.put("sucursalVacia", sucursalVacia);
		return new ModelAndView("modificarSucursal", modelo);
	}
	
	@RequestMapping(path = "/sucursal/{id}/modificardatos", method = RequestMethod.POST)
	public ModelAndView modificarDatosSucursal(@ModelAttribute ("sucursalVacia") Sucursal sucursalUpdate, @PathVariable Long id) {
		Sucursal sucursalBdd = servicioSucursal.getSucursal(id);
		servicioSucursal.modificarSucursal(sucursalUpdate, sucursalBdd);
		return new ModelAndView("listaSucursales");
	}

}
