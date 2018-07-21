package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ActividadDao;
import ar.edu.unlam.tallerweb1.dao.SocioDao;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Socio;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.SucursalActividad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioActividad;
import ar.edu.unlam.tallerweb1.servicios.ServicioActividadImp;
import ar.edu.unlam.tallerweb1.servicios.ServicioSocioImpl;

import static org.mockito.Mockito.*;

public class TestServicio {

	
	@Test
	@Transactional @Rollback(true)
	public void testServicioGuardarSocioActividadSucursal() {
		ServicioActividadImp servicioActividad = new ServicioActividadImp();
		ActividadDao actividadDaoMock = mock(ActividadDao.class);
		servicioActividad.setActividadDao(actividadDaoMock);
		SocioDao socioDaoMock = mock(SocioDao.class);
		servicioActividad.setSocioDao(socioDaoMock);
		Socio socioMock = mock(Socio.class);
		when(socioDaoMock.buscarSocio(1L)).thenReturn(socioMock);
		
		
		SucursalActividad sucursalActividadMock = mock(SucursalActividad.class);
		when(actividadDaoMock.traerActividadDeSucursal(1L)).thenReturn(sucursalActividadMock);
		when(sucursalActividadMock.getCupoActual()).thenReturn(10);
		
		
		when(sucursalActividadMock.getCupo()).thenReturn(20);
		
		@SuppressWarnings("unchecked")
		Set<SucursalActividad> listaSucursalActividad = mock(Set.class);
		when(socioMock.getActividadesEnSucursal()).thenReturn(listaSucursalActividad);
		when(listaSucursalActividad.contains(sucursalActividadMock)).thenReturn(false);
		
		
		assertTrue(servicioActividad.guardarSocioActividadSucursal(1L, 1L));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@Transactional @Rollback(true)
	public void testServicioRegistrarSocio(){
		ServicioSocioImpl servicioSocio = new ServicioSocioImpl();
		UsuarioDao usuarioDaoMock = mock(UsuarioDao.class);
		servicioSocio.setUsuarioDao(usuarioDaoMock);
		Socio socioMock = mock(Socio.class);
		Usuario usuarioMock = mock(Usuario.class);
		when(socioMock.getUsuario()).thenReturn(usuarioMock);
		when(usuarioMock.getNick()).thenReturn("");
		List<Usuario> listaUsuarioMock = mock(List.class);
		when(usuarioDaoMock.verSiExisteUsuario(anyString())).thenReturn(listaUsuarioMock);
		when(listaUsuarioMock.size()).thenReturn(1);
		assertFalse(servicioSocio.registrarSocio(socioMock));
		verify(socioMock, times(0)).setUsuario(usuarioMock);
		verify(socioMock, times(0)).setRecomendadoPor(null);
		
		//
	}
	

}
