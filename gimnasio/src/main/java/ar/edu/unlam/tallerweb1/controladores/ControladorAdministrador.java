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
import ar.edu.unlam.tallerweb1.modelo.SucursalActividad;
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
	@Inject 
	private ServicioActividad servicioActividad;

	
	
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
	
	@RequestMapping(path="/actividadesAdmin")
	public ModelAndView seleccionarSucursal() {
		ModelMap modelo = new ModelMap();
		modelo.put("listaSucursales",servicioSucursal.listarSucursales());
		return new ModelAndView("actividadesAdministrador",modelo);
	}
	@RequestMapping(path="/{idSucursal}/listaActividadesSucursal")
	public ModelAndView abmActividades(@PathVariable ("idSucursal")Long idSucursal) {
		ModelMap modelo = new ModelMap();
		modelo.put("listaSucursalActividades", servicioActividad.listarActividadesEnSucursal(idSucursal));
		return new ModelAndView("listaActividadesAbm",modelo);
	}
	@RequestMapping(path="/{idSucursalActividad}/modificacionActividad")
	public ModelAndView modificacionActividad(@PathVariable Long idSucursalActividad) {
		ModelMap modelo = new ModelMap();
		SucursalActividad SucursalActividadVacia = new SucursalActividad();
		modelo.put("sucursalActividad",servicioActividad.traerActividadSucursal(idSucursalActividad));
		modelo.put("sucursalActividadVacia", SucursalActividadVacia);
		return new ModelAndView("modificacionActividad",modelo);
	}	
	@RequestMapping(path="/{idSucursalActividad}/modificacionactividad", method= RequestMethod.POST)
		public ModelAndView establecerModificacionActividad(@ModelAttribute ("sucursalActividadVacia") SucursalActividad sucursalActividadUpdate,@PathVariable Long idSucursalActividad,@RequestParam("nombreActividad") String nombre) {
		SucursalActividad sucursalActividadBdd= servicioActividad.traerActividadSucursal(idSucursalActividad);
		servicioActividad.modificarActividad(sucursalActividadUpdate, sucursalActividadBdd);
		return new ModelAndView("redirect:/{idSucursalActividad}/modificacionActividad"); 
	}
	@RequestMapping(path="/{idSucursal}/bajaActividad")
	public ModelAndView eliminarSucursalActividad(@PathVariable("idSucursal")Long idSucursal,@RequestParam("idSucursalActividad") Long idSucursalActividad){
		ModelMap modelo = new ModelMap();
		String exito = "la actividad fue eliminada con exito";
		SucursalActividad sucursalActividad = servicioActividad.traerActividadSucursal(idSucursalActividad);
		servicioActividad.eliminarSucursalActividad(sucursalActividad);
		modelo.put("exito", exito);
		return new ModelAndView("redirect:/{idSucursal}/listaActividadesSucursal");
	}
}
