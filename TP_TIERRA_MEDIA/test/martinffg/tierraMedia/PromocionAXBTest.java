package martinffg.tierraMedia;

import java.util.ArrayList;
import java.util.Date;
import martinffg.tierraMedia.Atraccion;
import martinffg.tierraMedia.Itinerario;
import martinffg.tierraMedia.PosicionGlobal;
import martinffg.tierraMedia.PromocionAXB;
import martinffg.tierraMedia.TipoAtraccion;
import org.junit.Test;
import org.junit.Assert;

public class PromocionAXBTest {

	@Test
    public void crearPromocionTest(){
	 	
	 	Date fechaHoy = new Date();
	 	long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
	 	PromocionAXB promocion = new PromocionAXB("Promo AXB Prueba",fechaVence,5.0); 
	 	
	 	// valido que se haya creado el promocion
	 	Assert.assertNotNull(promocion);
	 	Assert.assertEquals("Promo AXB Prueba", promocion.getNombrePromocion());
	 	Assert.assertEquals(fechaVence, promocion.getUltimoDiaVigencia());
	 	Assert.assertEquals(5.0, promocion.getValorDescuento(),0.01);
	 	Assert.assertEquals(PromocionAXB.class,promocion.getClass());
	 	
 	}
	
	@Test
	public void calcularDescuentoAXBAItinerarioTest(){
		
		 PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
		 PosicionGlobal posicionAldea = new PosicionGlobal(20,20,"Posicion Aldea");
		 PosicionGlobal posicionGondor = new PosicionGlobal(30,30,"Posicion Gondor");
		 PosicionGlobal posicionPancheria = new PosicionGlobal(5,5,"Posicion Pancheria");
	     
		 // creo las distintas atracciones
		 Atraccion atraccion1 = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA);
		 Atraccion atraccion2 = new Atraccion("Aldea",posicionAldea,47.80,1500,78.0,TipoAtraccion.DEGUSTACION);
		 Atraccion atraccion3 = new Atraccion("Gondor",posicionGondor,200.00,500,100.0,TipoAtraccion.PAISAJE);
		 Atraccion atraccion4 = new Atraccion("Pancheria",posicionPancheria,100.00,500,100.0,TipoAtraccion.DEGUSTACION);
		 
		 // ahora genero la lista de atracciones
		 ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		 
		 atracciones.add(atraccion1);
		 atracciones.add(atraccion2);
		 atracciones.add(atraccion3);
		 atracciones.add(atraccion4);
		 
		 // armo el itinerario de prueba
		 Itinerario itinerario = new Itinerario("Itinerario de prueba");
		 
		 itinerario.setAtracciones(atracciones);
		
		 Date fechaHoy = new Date();
		 long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		 PromocionAXB promocion = new PromocionAXB("Promo AXB Prueba",fechaVence,5.0);
		 
		 double descuentoObtenido = promocion.calcularDescuentoPromocionalAlItinerario(itinerario);
		 // valido el resultado obtenido
		 Assert.assertEquals(100.0,descuentoObtenido,0.01);
	}
	
	@Test
	public void calcularTotalConDescuentoAXBAItinerarioTest(){
		
		PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
		 PosicionGlobal posicionAldea = new PosicionGlobal(20,20,"Posicion Aldea");
		 PosicionGlobal posicionGondor = new PosicionGlobal(30,30,"Posicion Gondor");
		 PosicionGlobal posicionPancheria = new PosicionGlobal(5,5,"Posicion Pancheria");
	     
		 // creo las distintas atracciones
		 Atraccion atraccion1 = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA);
		 Atraccion atraccion2 = new Atraccion("Aldea",posicionAldea,47.80,1500,78.0,TipoAtraccion.DEGUSTACION);
		 Atraccion atraccion3 = new Atraccion("Gondor",posicionGondor,200.00,500,100.0,TipoAtraccion.PAISAJE);
		 Atraccion atraccion4 = new Atraccion("Pancheria",posicionPancheria,100.00,500,100.0,TipoAtraccion.DEGUSTACION);
		 
		 // ahora genero la lista de atracciones
		 ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		 
		 atracciones.add(atraccion1);
		 atracciones.add(atraccion2);
		 atracciones.add(atraccion3);
		 atracciones.add(atraccion4);
		 
		 // armo el itinerario de prueba
		 Itinerario itinerario = new Itinerario("Itinerario de prueba");
		 
		 itinerario.setAtracciones(atracciones);
		
		 Date fechaHoy = new Date();
		 long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		 PromocionAXB promocion = new PromocionAXB("Promo AXB Prueba",fechaVence,5.0);
		 
		 double pagoConDescuento = promocion.calcularCostoPromocionalDelItinerario(itinerario);
		 // valido el resultado obtenido
		 Assert.assertEquals(400.0,pagoConDescuento,0.01);
	}
  	 
}