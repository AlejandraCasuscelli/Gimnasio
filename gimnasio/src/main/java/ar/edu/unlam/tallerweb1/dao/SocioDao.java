package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Socio;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface SocioDao {
	
	List<Socio> buscarSocios(Long idSucursal);
	List<Socio> buscarSocios();
	Socio buscarSocio(Usuario usuario); 
	void agregarPaseASocio(Long idSocio, Long idPase);
	Socio buscarSocio(Long idSocio);
	Socio buscarSocioPorDni(String dni);
	void registrarSocio (Socio socio, Socio socioReferente, Sucursal sucursal);
	void registrarSocioSinReferente (Socio socio, Sucursal sucursal);
	void actualizarSocio(Socio socio);
}
