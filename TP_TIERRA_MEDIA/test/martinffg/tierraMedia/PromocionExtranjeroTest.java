package martinffg.tierraMedia;

import java.util.ArrayList;
import java.util.Date;

import martinffg.tierraMedia.PromocionExtranjero;
import martinffg.tierraMedia.TipoPromocion;

import org.junit.Test;
import org.junit.Assert;

public class PromocionExtranjeroTest {

	@Test
    public void crearPromocionTest(){
	 	
	 	Date fechaHoy = new Date();
	 	long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
	 	PromocionExtranjero promocion = new PromocionExtranjero("Promo Extranjero Prueba",fechaVence,50.0); 
	 	
	 	// valido que se haya creado el promocion con los datos requeridos
	 	Assert.assertNotNull(promocion);
	 	Assert.assertEquals("Promo Extranjero Prueba", promocion.getNombrePromocion());
	 	Assert.assertEquals(fechaVence, promocion.getUltimoDiaVigencia());
	 	Assert.assertEquals(50.0, promocion.getValorDescuento(),0.01);
	 	Assert.assertEquals(TipoPromocion.EXTRANJERO,promocion.getTipoPromocion());
	 	
 	}
	
	@Test
	public void calcularDescuentoExtranjeroAItinerarioTest(){
		
		 Itinerario itinerario = this.generarItinerarioDePrueba();
		
		 PromocionExtranjero promocion = this.generarPromocionExtranjeroDePrueba();
		 
		 double descuentoObtenido = promocion.calcularDescuentoPromocionalAlItinerario(itinerario);
		 // valido el resultado obtenido
		 Assert.assertEquals(200.0,descuentoObtenido,0.01);
	}
	
	@Test
	public void calcularTotalConDescuentoPorcentualAItinerarioTest(){
		
		 Itinerario itinerario = this.generarItinerarioDePrueba();
		
		 PromocionExtranjero promocion = this.generarPromocionExtranjeroDePrueba();
		 
		 double pagoConDescuento = promocion.calcularCostoPromocionalDelItinerario(itinerario);
		// valido el resultado obtenido
		 Assert.assertEquals(200.0,pagoConDescuento,0.01);
	}
	
	@Test
	public void calcularCostoConDescuentoAUsuarioExtranjeroValidadoTest(){
		
		Itinerario itinerario = this.generarItinerarioDePrueba();
		PromocionExtranjero promocion = this.generarPromocionExtranjeroDePrueba();
		
		// creo un usuario que tiene domicilio en el exterior
		PosicionGlobal posicionEnElExterior = new PosicionGlobal(531,531,"Posicion en el Exterior");
		Usuario usuarioExtranjero = new Usuario("usuarioExtranjero",10000,12,12,promocion,TipoAtraccion.AVENTURA);
		usuarioExtranjero.setPosicionDomicilio(posicionEnElExterior);
		// ahora el generador para comparar si es extranjero
		GeneradorVisitasEItinerarios generador = new GeneradorVisitasEItinerarios(itinerario.getAtracciones(),usuarioExtranjero);
		
		// valido si el usuario es extranjero y si le cobran el monto adecuado para todo el viaje (un solo ticket)
		Assert.assertTrue(generador.esUsuarioExtranjero(itinerario.getAtracciones(), usuarioExtranjero));
		Assert.assertEquals(200.0,promocion.calcularCostoPromocionalDelItinerario(itinerario),0.01);
	}
	
	
	// METODOS PRIVADOS DE LA CLASE DE PRUEBAS
	private PromocionExtranjero generarPromocionExtranjeroDePrueba(){
		
		Date fechaHoy = new Date();
		long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		
		return (new PromocionExtranjero("Promo Extranjero Prueba",fechaVence,50.0));
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
		 ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		 
		 atracciones.add(atraccion1);
		 atracciones.add(atraccion2);
		 atracciones.add(atraccion3);
		 
		 // armo el itinerario de prueba
		 Itinerario itinerario = new Itinerario("Itinerario de prueba");
		 
		 itinerario.setAtracciones(atracciones);
		 
		 return itinerario;
		
	}
	
  	 
}