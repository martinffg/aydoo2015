package martinffg.TIERRA_MEDIA;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

public class ItinerarioTest {

 @Test
    public void crearItinerarioTest(){
	 
	 	Itinerario itinerario = new Itinerario("Itinerario Prueba"); 
	 	
	 	// valido que se haya creado el itinerario
	 	Assert.assertNotNull(itinerario);
	 	Assert.assertNotNull(itinerario.getAtracciones());
	 	Assert.assertEquals("Itinerario Prueba", itinerario.getNombreItinerario());
	 	Assert.assertEquals(0.0, itinerario.getCostoTotalItinerario(),0.01);
	 	Assert.assertEquals(0.0, itinerario.getTiempoTotalItinerario(),0.01);
	 	Assert.assertEquals(0,itinerario.getCantidadAtraccionesIncluidas());
 
 	}
 
 @Test
 
 public void cargarAtraccionTest() {
	 
	 PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
     // creo atraccion nombre Mordor, en la pos 20,20, con costo 152,20, tiempo prom recorrido 122 de tipo Aventura
	 Atraccion atraccion = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA);
	 
	 Itinerario itinerario = new Itinerario("Itinerario de prueba");
	 
	 itinerario.agregarAtraccion(atraccion);
	 
	 Assert.assertEquals(152.20, itinerario.getCostoTotalItinerario(),0.01);
	 Assert.assertEquals(1,itinerario.getCantidadAtraccionesIncluidas());
	 Assert.assertEquals(122.0, itinerario.getTiempoTotalItinerario(),0.01); 
	 
	 
 }
  	 
}