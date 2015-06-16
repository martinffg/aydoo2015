package martinffg.tierraMedia;

import java.util.ArrayList;
import java.util.Date;
import martinffg.tierraMedia.Atraccion;
import martinffg.tierraMedia.Itinerario;
import martinffg.tierraMedia.PosicionGlobal;
import martinffg.tierraMedia.PromocionAbsoluta;
import martinffg.tierraMedia.TipoAtraccion;

import org.junit.Test;
import org.junit.Assert;

public class PromocionAbsolutaTest {

	@Test
    public void crearPromocionTest(){
	 	
	 	Date fechaHoy = new Date();
	 	long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
	 	PromocionAbsoluta promocion = new PromocionAbsoluta("Promo Absoluta Prueba",fechaVence,5.0); 
	 	
	 	// valido que se haya creado el promocion
	 	Assert.assertNotNull(promocion);
	 	Assert.assertEquals("Promo Absoluta Prueba", promocion.getNombrePromocion());
	 	Assert.assertEquals(fechaVence, promocion.getUltimoDiaVigencia());
	 	Assert.assertEquals(5.0, promocion.getValorDescuento(),0.01);
	 	
 	}
	
	@Test
	public void calcularDescuentoAbsolutoAItinerarioTest(){
		
		 PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
		 PosicionGlobal posicionAldea = new PosicionGlobal(20,20,"Posicion Aldea");
		 PosicionGlobal posicionGondor = new PosicionGlobal(30,30,"Posicion Gondor");
	     
		 // creo las distintas atracciones
		 Atraccion atraccion1 = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA);
		 Atraccion atraccion2 = new Atraccion("Aldea",posicionAldea,47.80,1500,78.0,TipoAtraccion.DEGUSTACION);
		 Atraccion atraccion3 = new Atraccion("Gondor",posicionGondor,200.00,500,100.0,TipoAtraccion.PAISAJE);
		 
		 // ahora genero la lista de atracciones
		 ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		 
		 atracciones.add(atraccion1);
		 atracciones.add(atraccion2);
		 atracciones.add(atraccion3);
		 
		 // armo el itinerario de prueba
		 Itinerario itinerario = new Itinerario("Itinerario de prueba");
		 
		 itinerario.setAtracciones(atracciones);
		
		 Date fechaHoy = new Date();
		 long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		 PromocionAbsoluta promocion = new PromocionAbsoluta("Promo Absoluta Prueba",fechaVence,5.0);
		 
		 double descuentoObtenido = promocion.calcularDescuentoPromocionalAlItinerario(itinerario);
		 
		 // valido que el calculo del descuento sea correcto
		 Assert.assertEquals(5.0,descuentoObtenido,0.01);
	}
	
	@Test
	public void calcularTotalConDescuentoAbsolutoAItinerario(){
		
		 PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
		 PosicionGlobal posicionAldea = new PosicionGlobal(20,20,"Posicion Aldea");
		 PosicionGlobal posicionGondor = new PosicionGlobal(30,30,"Posicion Gondor");
	     
		 // creo las distintas atracciones
		 Atraccion atraccion1 = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA);
		 Atraccion atraccion2 = new Atraccion("Aldea",posicionAldea,47.80,1500,78.0,TipoAtraccion.DEGUSTACION);
		 Atraccion atraccion3 = new Atraccion("Gondor",posicionGondor,200.00,500,100.0,TipoAtraccion.PAISAJE);
		 
		 // ahora genero la lista de atracciones
		 ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		 
		 atracciones.add(atraccion1);
		 atracciones.add(atraccion2);
		 atracciones.add(atraccion3);
		 
		 // armo el itinerario de prueba
		 Itinerario itinerario = new Itinerario("Itinerario de prueba");
		 
		 itinerario.setAtracciones(atracciones);
		
		 Date fechaHoy = new Date();
		 long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		 PromocionAbsoluta promocion = new PromocionAbsoluta("Promo Absoluta Prueba",fechaVence,5.0);
		 
		 double pagoConDescuento = promocion.calcularCostoPromocionalDelItinerario(itinerario);
		 
		 
		 // valido que el valor devuelto sea consistente con el calculo
		 Assert.assertEquals(395.0,pagoConDescuento,0.01);
	}
  	 
}