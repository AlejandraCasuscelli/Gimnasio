package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Beneficio;


public interface ServicioBeneficio {

	List<Beneficio> listarBeneficios(Long idPase); 
	
}
