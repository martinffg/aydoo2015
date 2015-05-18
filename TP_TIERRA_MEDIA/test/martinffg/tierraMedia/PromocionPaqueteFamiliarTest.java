package martinffg.tierraMedia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import martinffg.tierraMedia.PromocionPaqueteFamiliar;
import martinffg.tierraMedia.TipoPromocion;

import org.junit.Test;
import org.junit.Assert;

public class PromocionPaqueteFamiliarTest {

	@Test
    public void crearPromocionTest(){
	 	
	 	Date fechaHoy = new Date();
	 	long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
	 	PromocionPaqueteFamiliar promocion = new PromocionPaqueteFamiliar("Promo Paquete Familiar Prueba",fechaVence,0.0); 
	 	
	 	// valido que se haya creado el promocion
	 	Assert.assertNotNull(promocion);
	 	Assert.assertEquals("Promo Paquete Familiar Prueba", promocion.getNombrePromocion());
	 	Assert.assertEquals(fechaVence, promocion.getUltimoDiaVigencia());
	 	Assert.assertEquals(0.0, promocion.getValorDescuento(),0.01);
	 	Assert.assertEquals(TipoPromocion.PAQUETE_FAMILIAR,promocion.getTipoPromocion());
	 	
 	}
	
	/*@Test
	public void probarDescuentoPaqueteFamiliarAItinerario(){
		
		 Itinerario itinerario = this.generarItinerarioDePrueba();
		
		 PromocionPaqueteFamiliar promocion = this.generarPromocionPaqueteFamiliarDePrueba();
		 
		 double descuentoObtenido = promocion.calcularDescuentoPromocionalAlItinerario(itinerario);
		 
		 Assert.assertEquals(400.0,descuentoObtenido,0.01);
	}
	
	@Test
	public void probarTotalConDescuentoPorcentualAItinerario(){
		
		 Itinerario itinerario = this.generarItinerarioDePrueba();
		
		 PromocionPaqueteFamiliar promocion = this.generarPromocionPaqueteFamiliarDePrueba();
		 
		 double pagoConDescuento = promocion.calcularCostoPromocionalDelItinerario(itinerario);
		 
		 Assert.assertEquals(0.0,pagoConDescuento,0.01);
	}
	
	
	// METODOS PRIVADOS DE LA CLASE DE PRUEBAS
	private PromocionPaqueteFamiliar generarPromocionPaqueteFamiliarDePrueba(){
		
		Date fechaHoy = new Date();
		long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		
		return (new PromocionPaqueteFamiliar("Promo Paquete Familiar Prueba",fechaVence,0.0));
	}
	
	private Itinerario generarItinerarioDePrueba() {
		
		 PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
		 PosicionGlobal posicionAldea = new PosicionGlobal(20,20,"Posicion Aldea");
		 PosicionGlobal posicionGondor = new PosicionGlobal(30,30,"Posicion Gondor");
	     
		 // creo las distintas atracciones
		 Atraccion atraccion1 = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA);
		 Atraccion atraccion2 = new Atraccion("Aldea",posicionAldea,47.80,1500,78.0,TipoAtraccion.DEGUSTACION);
		 Atraccion atraccion3 = new Atraccion("Gondor",posicionGondor,200.00,500,100.0,TipoAtraccion.PAISAJE);
		 
		 // ahora genero la lista de atracciones
		 List<Atraccion> atracciones = new ArrayList<Atraccion>();
		 
		 atracciones.add(atraccion1);
		 atracciones.add(atraccion2);
		 atracciones.add(atraccion3);
		 
		 // armo el itinerario de prueba
		 Itinerario itinerario = new Itinerario("Itinerario de prueba");
		 
		 itinerario.setAtracciones(atracciones);
		 
		 return itinerario;
		
	}
	*/  	 
}