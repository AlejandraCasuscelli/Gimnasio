package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Socio;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

// Clase que prueba la conexion a la base de datos. Hereda de SpringTest por lo que corre dentro del contexto
// de spring
public class ConexionBaseDeDatosTest extends SpringTest{

    @Test
    @Transactional @Rollback(true)
    public void pruebaConexion(){
        assertThat(getSession().isConnected()).isTrue();
    }
    
    @SuppressWarnings({ "unused", "unchecked" })
	@Test
    @Transactional @Rollback(true)
    public void testListaSocios(){
        Session sesion = getSession();
        
        Socio socio1 = new Socio();
        socio1.setNombre("martin");
        Socio socio2 = new Socio();
        socio2.setNombre("juan");
        
        Socio socio3 = new Socio();
        socio3.setNombre("ana");
        
        sesion.save(socio1);
        sesion.save(socio2);
        sesion.save(socio3);
        
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setId(1L);
        
        Sucursal sucursal2 = new Sucursal();
        sucursal1.setId(2L);
        
        sesion.save(sucursal1);
        sesion.save(sucursal2);
        
        socio1.setSucursal(sucursal1);
        socio2.setSucursal(sucursal1);
        socio3.setSucursal(sucursal2);
        
        
		List<Socio> listaDeSocios = sesion.createCriteria(Socio.class)
				.createAlias("sucursal", "buscaSucursal")
				.add(Restrictions.eq("buscaSucursal.id", 2L))
				.list();
		
		assertEquals(1, listaDeSocios.size());
        
        
        
    }
    @SuppressWarnings({ "unused", "unchecked" })
	@Test
    @Transactional @Rollback(true)
    public void testBuscaSocio(){
    	Session sesion = getSession();
    	Usuario usuario1 = new Usuario();
    	usuario1.setNick("nick1");
    	usuario1.setPassword("pass1");
    	sesion.save(usuario1);
        
        Socio socio1 = new Socio();
        socio1.setNombre("martin");
        socio1.setUsuario(usuario1);
        Socio socio2 = new Socio();
        socio2.setNombre("juan");
        
        sesion.save(socio1);
        sesion.save(socio2);
        
        Socio socio =  (Socio) sesion.createCriteria(Socio.class)
    			.createAlias("usuario", "buscarPorUsuario")
    			.add(Restrictions.eq("buscarPorUsuario.nick", "nick1")).uniqueResult();
        
        System.out.println(socio.getNombre());
        
    }
    
    @SuppressWarnings({ "unused", "unchecked" })
	@Test
    @Transactional @Rollback(true)
    public void listarSucursal(){
    	Session sesion = getSession();
        
              
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setId(1L);
        sucursal1.setNombre("suc1");
        
        Sucursal sucursal2 = new Sucursal();
        sucursal1.setId(2L);
        sucursal2.setNombre("suc2");
        
        sesion.save(sucursal1);
        sesion.save(sucursal2);
        
        List<Sucursal> lista = sesion.createCriteria(Sucursal.class)
				.list();
        for (Sucursal sucursal : lista) {
			System.out.println(sucursal.getNombre());
		}
    }
    
    
    
    
    
}
