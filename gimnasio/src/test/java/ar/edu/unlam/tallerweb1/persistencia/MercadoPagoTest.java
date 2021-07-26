package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.EstadoPago;
import ar.edu.unlam.tallerweb1.modelo.Pago;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoPagoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioPago;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercadopago.exceptions.MPException;
import helpers.JsonConverter;
import helpers.MercadoPago;
import helpers.MercadoPagoMetadata;
import org.junit.Test;

import com.google.gson.JsonObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class MercadoPagoTest {



//    @Autowired
//    private ServicioPago servicioPago;
//
//    private ServicioEstadoPagoImpl servicioEstadoPagoImpl = mock(ServicioEstadoPagoImpl.class);
//

    @Test
    public void testJsonObject() throws JsonProcessingException {
        MercadoPagoMetadata metadata = new MercadoPagoMetadata();
        metadata.setId_pase(3L);
        metadata.setId_socio(1L);
        metadata.setId_descuento(1L);
        JsonObject meta = new JsonConverter<MercadoPagoMetadata>().classToJsonObject(metadata);
        assertThat(meta).isNotNull();

    }

    @Test
    public void JsonObjectToClass() throws IOException {
        MercadoPagoMetadata mp = new MercadoPagoMetadata();
        mp.setId_pase(3L);
        mp.setId_socio(1L);
        mp.setId_descuento(1L);
        JsonObject jsonObject = new JsonConverter<MercadoPagoMetadata>().classToJsonObject(mp);
        MercadoPagoMetadata mp2 = new JsonConverter<MercadoPagoMetadata>().JsonObjectToClass(jsonObject, MercadoPagoMetadata.class);
        assertThat(mp2).isNotNull();
        assertThat(mp2.getId_pase()).isEqualTo(3L);
    }

//    @Test
//    @Transactional
//    @Rollback
//    public void siMercadoPagoEstaOkElPagoEstaAprobado() throws MPException {
//        Pago pago = whenElPagoEsSucces();
//        thenElPagoEstaAprobado(pago);
//    }
//
//    private void thenElPagoEstaAprobado(Pago pago) {
//        Pago pagoGuardado = getSession().get(Pago.class, pago.getId());
//        assertThat(pagoGuardado.getEstado().getNombre()).isEqualTo("Aprobado");
//    }
//
//    private Pago whenElPagoEsSucces() throws MPException {
//        EstadoPago estado = new EstadoPago();
//        estado.setId(1L);
//        estado.setNombre("Aprobado");
//        //getSession().save(estado);
//        return servicioPago.abonarPase(1L, 2L,3L, estado);
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    public void siMercadoPagoEstaPendienteElPagoEstaPendiente() throws MPException {
//        Pago pago = whenElPagoEstaPendiente();
//        thenElPagoEstaPendiente(pago);
//    }
//
//    private Pago whenElPagoEstaPendiente() {
//        EstadoPago estado = new EstadoPago();
//        estado.setId(2L);
//        estado.setNombre("Pendiente");
//        return servicioPago.abonarPase(1L, 2L,3L, estado);
//    }
//
//    private void thenElPagoEstaPendiente(Pago pago) {
//        Pago pagoGuardado = getSession().get(Pago.class, pago.getId());
//        assertThat(pagoGuardado.getEstado().getNombre()).isEqualTo("Pendiente");
//    }


}
