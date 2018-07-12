package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.LocalizacionDao;
import ar.edu.unlam.tallerweb1.dao.SocioDao;
import ar.edu.unlam.tallerweb1.dao.SucursalDao;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Pase;
import ar.edu.unlam.tallerweb1.modelo.Socio;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service("servicioSocio")
@Transactional
public class ServicioSocioImpl implements ServicioSocio {

	@Inject
	private SocioDao socioDao;
	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private LocalizacionDao localizacionDao;
	@Inject
	private SucursalDao sucursalDao;
	
	@Override
	public List<Socio> buscarSocios(Long idSucursal) {
		return socioDao.buscarSocios(idSucursal);
	}
	
	@Override
	public void agregarPaseASocio(Long idSocio, Long idPase) {
		socioDao.agregarPaseASocio(idSocio, idPase);
	}

	@Override
	public Socio buscarSocio(Usuario usuario) {
		return socioDao.buscarSocio(usuario);
	}

	@Override
	public Socio buscarSocio(Long idSocio) {
		return socioDao.buscarSocio(idSocio);
	}

	@Override
	public List<Socio> buscarSocios() {
		return socioDao.buscarSocios();
	}

	@Override
	public void modificarSocio(Socio socioUpdate, Socio socioBdd) {
		socioDao.modificarSocio(socioUpdate, socioBdd);
	}

	
	//borrar despues
	@Override
	public Boolean validarActividadSocio(Long idSocio) {
		Socio socio = socioDao.buscarSocio(idSocio);
		if(socio.getPase().getCantidadActividades()==null) {
			return false;
		}
		else if (socio.getActividadesEnSucursal().size() == socio.getPase().getCantidadActividades()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean registrarSocio(Socio socio) {
		List<Usuario> lista = usuarioDao.verSiExisteUsuario(socio.getUsuario().getNick());
		if (lista.size() == 0) {
			
			Socio socioReferente;
			if(socio.getRecomendadoPor().getDni() != "") { //Es necesario hacerlo asi ya que pincha si se envia un referente vacio
			socioReferente = socioDao.buscarSocioPorDni(socio.getRecomendadoPor().getDni());
			}else {
			socioReferente = null;
			}
			Ciudad ciudad = localizacionDao.traerCiudad(socio.getCiudad().getId());
			Usuario usuario = usuarioDao.guardarUsuario(socio.getUsuario().getNick(), socio.getUsuario().getPassword());
			Sucursal sucursal = sucursalDao.getSucursal(socio.getSucursal().getId());
			socio.setUsuario(usuario);
			socio.setCiudad(ciudad);
			socio.setSucursal(sucursal);
			sucursal.getListaSocios().add(socio); //les mando la sucursal y cuando guardo socio hago update a la lista de socios de sucursal
				if (socioReferente == null) {
					socio.setRecomendadoPor(null); // Si no coloco este campo como null, tira error
					socioDao.registrarSocioSinReferente(socio, sucursal);
				} else {
					socio.setRecomendadoPor(socioReferente);
					socioReferente.setDescuento(1); //En el futuro si el campo descuento es 1, la proxima cuota 
					socioDao.registrarSocio(socio, socioReferente, sucursal);
				}
			return true;
		} else {
			
			return false;
		}
	}
	
}
