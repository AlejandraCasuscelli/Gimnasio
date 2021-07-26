package ar.edu.unlam.tallerweb1.controladores;


import ar.edu.unlam.tallerweb1.modelo.Estado;
import ar.edu.unlam.tallerweb1.modelo.EstadoPago;
import ar.edu.unlam.tallerweb1.modelo.Pago;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.junit.Test;
import org.mockito.configuration.IMockitoConfiguration;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorOperadorTest {

    private ServicioOperador servicioOperador = mock(ServicioOperador.class);
    private ServicioPago servicioPago = mock(ServicioPago.class);
    private ControladorOperador controladorOperador = new ControladorOperador(servicioOperador, servicioPago);
    private final String REDIRECT = "redirect:/ver-pagos";

    private ModelAndView mav = new ModelAndView();

    @Test
    public void elOperadorDeLaSucursalPuedeVerPagosDeSocios(){
        givenExisteSocioEnLaSucursalDelOperador();
        whenElOperadorIngresaAVerPagos();
        thenSeMuestranPagos();

    }

    @Test
    public void elOperadorPuedeAprobarUnPagoPendiente(){
        givenExisteSocioEnLaSucursalDelOperador();
        Pago pago = givenExisteUnPagoPendiente();
        whenElOperadorApruebaUnPago(pago);
        thenElPagoSeEncuentraAprobado();
    }

    @Test
    public void aprobarUnPagoQueNoExisteDeberiaDarmeError(){
        givenNoExistePago();
        whenAprueboUnPagoQueNoExiste();
        thenObtengoUnError();

    }

    @Test
    public void elOperadorNoPuedeAprobarUnPagoYaAprobado(){
        givenExisteSocioEnLaSucursalDelOperador();
        Pago pago = givenExisteUnPagoAprobado();
        whenElOperadorApruebaUnPago(pago);
        thenNoSePuedeAprobarUnAprobado();
    }

    @Test
    public void elOperadorNoPuedeAprobarUnPagoRechazado(){
        givenExisteSocioEnLaSucursalDelOperador();
        Pago pago = givenExisteUnPagoRechazado();
        whenElOperadorApruebaUnPago(pago);
        thenNoSePuedeAprobarUnRechazado();
    }

    private void thenNoSePuedeAprobarUnRechazado() {
        assertThat(mav.getViewName()).isEqualTo(REDIRECT);
        assertThat(mav.getModel().get("mensaje")).isEqualTo("El pago ya se encuentra rechazado");
    }

    private Pago givenExisteUnPagoRechazado() {
        Pago pago = new Pago();
        pago.setEstado(new EstadoPago(Estado.RECHAZADO.getVal()));
        pago.setId(1L);
        when(servicioPago.getPagoById(pago.getId())).thenReturn(pago);
        doThrow(PagoYaRechazadoException.class)
                .when(servicioOperador).aprobarPago(pago);
        return pago;
    }

    private void givenExisteSocioEnLaSucursalDelOperador() {
    }

    private void whenElOperadorIngresaAVerPagos() {
        mav = controladorOperador.verPagos();
    }

    private void thenSeMuestranPagos() {
        assertThat(mav).isNotNull();
        assertThat(mav.getViewName()).isEqualTo("pagos");
        assertThat(mav.getModel().get("pagos")).isNotNull();

    }



    private Pago givenExisteUnPagoPendiente() {
        Pago pago = new Pago();
        pago.setEstado(new EstadoPago(Estado.PENDIENTE.getVal()));
        pago.setId(1L);
        when(servicioPago.getPagoById(pago.getId())).thenReturn(pago);
        return pago;
    }

    private void whenElOperadorApruebaUnPago(Pago pago) {
        mav = controladorOperador.aprobarPago(pago.getId());
    }

    private void thenElPagoSeEncuentraAprobado() {
        assertThat(mav.getViewName()).isEqualTo(REDIRECT);
    }


    private void thenObtengoUnError() {
        assertThat(mav.getViewName()).isEqualTo(REDIRECT);
        assertThat(mav.getModel().get("mensaje")).isEqualTo("Debe seleccionar una pago para aprobar");
    }

    private void whenAprueboUnPagoQueNoExiste() {
        mav = controladorOperador.aprobarPago(null);
    }

    private void givenNoExistePago() {
        doThrow(NoExistePagoException.class)
                .when(servicioOperador).aprobarPago(null);
    }

    private void thenNoSePuedeAprobarUnAprobado() {
        assertThat(mav.getViewName()).isEqualTo(REDIRECT);
        assertThat(mav.getModel().get("mensaje")).isEqualTo("El pago ya se encuentra aprobado");
    }

    private Pago givenExisteUnPagoAprobado() {
        Pago pago = new Pago();
        pago.setEstado(new EstadoPago(Estado.APROBADO.getVal()));
        pago.setId(1L);
        when(servicioPago.getPagoById(pago.getId())).thenReturn(pago);
        doThrow(PagoYaAprobadoException.class)
                .when(servicioOperador).aprobarPago(pago);
        return pago;
    }

}
