package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.dao.ActividadDao;
import ar.edu.unlam.tallerweb1.dao.PagoDao;
import ar.edu.unlam.tallerweb1.dao.SocioDao;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Pago;
import ar.edu.unlam.tallerweb1.modelo.Socio;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.SucursalActividad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioSocio;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Set;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.*;


public class TestDao extends SpringTest{
  
	@Inject
	private ActividadDao actividadDao;
	
	@Inject
	private SocioDao socioDao;
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private PagoDao pagoDao;
	
    @Test
    @Transactional @Rollback(true)
    public void testGuardarSocioActividadSucursal(){
    	Socio socio = new Socio();
    	socio.setIdSocio(2L);
    	SucursalActividad sucursalActividad = new SucursalActividad();
    	sucursalActividad.setIdSucursalActividad(5L);
    	getSession().save(socio);
    	getSession().save(sucursalActividad);

    	actividadDao.guardarSocioActividadSucursal(socio.getIdSocio(), sucursalActividad.getIdSucursalActividad());
    	assertThat(getSession().get(Socio.class, socio.getIdSocio()).getActividadesEnSucursal().contains(sucursalActividad.getIdSucursalActividad()));
    }
    
    @Test
    @Transactional @Rollback(true)
    public void testRegistrarSocio(){
    	Socio socio = new Socio();
    	
    	Socio socioReferente =new Socio();
    	socioReferente.setIdSocio(2L);
    	Sucursal sucursal = new Sucursal();
    	sucursal.setId(4L);
    	
    	socioDao.registrarSocio(socio, socioReferente, sucursal);
    	assertThat(getSession().get(Socio.class, socio.getIdSocio())).isNotNull();
    	
    }
    
    @Test
    @Transactional @Rollback(true)
    public void testGuardarUsuario(){
    	Usuario usuarioNuevo = new Usuario();
    	usuarioNuevo.setNick("pepe");
    	usuarioNuevo.setPassword("pepepass");
    	Usuario usuario = usuarioDao.guardarUsuario(usuarioNuevo);
    	assertThat(getSession().get(Usuario.class, usuario.getId())).isNotNull();
    	assertEquals("pepe",getSession().get(Usuario.class, usuario.getId()).getNick());
    	
    }
    
    
	@Test
    @Transactional @Rollback(true)
    public void testListaPagos() throws ParseException{
    	Date fechaDesde = new GregorianCalendar(2018, Calendar.JULY, 1).getTime();
    	Date fechaHasta = new GregorianCalendar(2018, Calendar.AUGUST, 1).getTime();
    	
		List<Socio> socios = new ArrayList<Socio>();
	    Socio socio1 = new Socio();
	    Socio socio2 = new Socio();
	    Socio socio3 = new Socio();
	    
	    Pago pago1 = new Pago();
	    pago1.setSocio(socio1);
	    pago1.setFecha(new GregorianCalendar(2018, Calendar.JULY, 3).getTime());
	    
	    Pago pago2 = new Pago();
	    pago2.setFecha(new GregorianCalendar(2018, Calendar.JULY, 13).getTime()); //sin socio 
	    
	    
	    Pago pago3 = new Pago();
	    pago3.setSocio(socio3);
	    pago3.setFecha(new GregorianCalendar(2018, Calendar.AUGUST, 10).getTime()); //fuera del rango de fechas
	    
	    getSession().save(pago1);
	    getSession().save(pago2);
	    getSession().save(pago3);
	    getSession().save(socio1);
	    getSession().save(socio2);
	    getSession().save(socio3);
	    
	    socios.add(socio1);
	    socios.add(socio2);
	    socios.add(socio3);
	    List<Pago> pagos = pagoDao.listaPagos(socios, fechaDesde, fechaHasta);
	    
	    assertEquals(1,pagos.size()); //hay 3 pagos pero 2 son en las fechas indicadas y 1 es en la fecha indicada para esa 
	    //lista de socios
	    
	    
	    
	    
    }
    
   
}