package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ActividadDao;
import ar.edu.unlam.tallerweb1.dao.SocioDao;
import ar.edu.unlam.tallerweb1.dao.SucursalDao;
import ar.edu.unlam.tallerweb1.modelo.Actividad;
import ar.edu.unlam.tallerweb1.modelo.Profesor;
import ar.edu.unlam.tallerweb1.modelo.Socio;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.SucursalActividad;

@Service ("ServicioActividad")
@Transactional
public class ServicioActividadImp implements ServicioActividad {
	
	@Inject
	private ActividadDao servicioActividadDao;
	@Inject
	private SocioDao servicioSocioDao;
	@Inject
	private SucursalDao sucursalDao;
	
	public void setActividadDao(ActividadDao dao){
		this.servicioActividadDao= dao;
	}
	public void setSocioDao(SocioDao dao){
		this.servicioSocioDao= dao;
	}
	@Override
	public List<SucursalActividad> listarActividadesEnSucursal(Long id) {
		return servicioActividadDao.listarActividadesEnSucursal(id);
	}
	@Override
	public boolean guardarSocioActividadSucursal(Long idSocio, Long idSucursalActividad) {
		Socio socio = servicioSocioDao.buscarSocio(idSocio);
		SucursalActividad sucursalActividad = servicioActividadDao.traerActividadDeSucursal(idSucursalActividad);
		if(sucursalActividad.getCupoActual()==null) {
			sucursalActividad.setCupoActual(0);
		}
		if (sucursalActividad.getCupoActual()<sucursalActividad.getCupo() && socio.getActividadesEnSucursal().contains(sucursalActividad) == false) {
			Integer cupoActual = sucursalActividad.getCupoActual()+1;
			sucursalActividad.setCupoActual(cupoActual);
			servicioActividadDao.modificarCupoDeActividadEnSucursal(sucursalActividad);
			servicioActividadDao.guardarSocioActividadSucursal(idSocio, idSucursalActividad);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public List<Actividad> listaActividades() {
		return servicioActividadDao.listaActividades();
	}
	@Override
	public List<SucursalActividad> listaActividadesNoDisponibles(Long idSocio, List<SucursalActividad> actividadesEnSucursal) {
		List<SucursalActividad> actividadesDeSocio = servicioActividadDao.listaActividadesDeSocio(idSocio);
		List<SucursalActividad> actividadesNoDisponibles = new ArrayList<SucursalActividad>();
		for (SucursalActividad actividadSocio : actividadesDeSocio) {
			if(actividadesEnSucursal.contains(actividadSocio))
				actividadesNoDisponibles.add(actividadSocio);
		}
		return actividadesNoDisponibles;
	}
	
	@Override
	public Actividad buscarActividad(Long idActividad) {
		return servicioActividadDao.buscarActividad(idActividad);
	}
	@Override
	public void modificarActividad(SucursalActividad sucursalActividadUpdate, SucursalActividad sucursalActividadBdd) {
		servicioActividadDao.modificarActividad(sucursalActividadUpdate, sucursalActividadBdd);
	}
	@Override
	public SucursalActividad traerActividadSucursal(Long idSucursalActividad) {
		return servicioActividadDao.traerActividadDeSucursal(idSucursalActividad);
	}
	@Override
	public void eliminarSucursalActividad(SucursalActividad sucursalActividad) {
		servicioActividadDao.eliminarSucursalActividad(sucursalActividad);
	}
	@Override
	public void agregarSucursalActividad(SucursalActividad sucursalActividadVacia) {
		Sucursal sucursal = sucursalDao.getSucursal(sucursalActividadVacia.getSucursal().getId());
		Actividad actividad = servicioActividadDao.buscarActividad(sucursalActividadVacia.getActividad().getId());
		sucursalActividadVacia.setSucursal(sucursal);
		sucursalActividadVacia.setActividad(actividad);
		sucursalActividadVacia.setCupoActual(0);
		servicioActividadDao.agregarSucursalActividad(sucursalActividadVacia);
	}
	@Override
	public void agregarNuevaActividad(Actividad actividad) {
		servicioActividadDao.agregarNuevaActividad(actividad);
	}
	@Override
	public void modificarActividad(Actividad actividadUpdate, Actividad actividad) {
		servicioActividadDao.modificarActividad(actividadUpdate, actividad);
		
	}
	@Override
	public void eliminarActividad(Actividad actividad) {
		servicioActividadDao.eliminarActividad(actividad);
	}
	@Override
	public List<Profesor> listarProfesores() {
		return servicioActividadDao.listarProfesores();
	}
	
}
