package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PagoDao;
import ar.edu.unlam.tallerweb1.dao.PaseDao;
import ar.edu.unlam.tallerweb1.modelo.Pago;
import ar.edu.unlam.tallerweb1.modelo.Pase;
import ar.edu.unlam.tallerweb1.modelo.Socio;

@Service("servicioPago")
@Transactional
public class ServicioPagoImpl implements ServicioPago {

	@Inject
	PagoDao pagoDao;

	@Override
	public List<Pago> listaPagos(List<Socio> socios, Date fechaDesde, Date fechaHasta) {
		return pagoDao.listaPagos(socios, fechaDesde, fechaHasta);
	}
	
	public Double getTotalRecaudado(List<Pago> pagos){
		Double total = 0.0;
		for (Pago pago : pagos) {
			total += pago.getImporte();
		}
		return total;
	}
	

}