package martinffg.tierraMedia;

import java.util.ArrayList;
import martinffg.tierraMedia.Atraccion;
import martinffg.tierraMedia.Itinerario;
import martinffg.tierraMedia.PosicionGlobal;
import martinffg.tierraMedia.TipoAtraccion;

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
 public void cargarAtraccionEnItinerarioTest() {
	 
	 PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
     // creo atraccion nombre Mordor, en la pos 20,20, con costo 152,20, tiempo prom recorrido 122 de tipo Aventura
	 Atraccion atraccion = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA);
	 
	 Itinerario itinerario = new Itinerario("Itinerario de prueba");
	 
	 itinerario.agregarAtraccion(atraccion);
	 
	 Assert.assertEquals(152.20, itinerario.getCostoTotalItinerario(),0.01);
	 Assert.assertEquals(1,itinerario.getCantidadAtraccionesIncluidas());
	 Assert.assertEquals(122.0, itinerario.getTiempoTotalItinerario(),0.01); 
	 
	 
 }

 @Test 
	public void cargarAtraccionesEnListaDelItinerarioTest() {
		 
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
		 
		 // valido los datos ingresados al itinerario tras procesar la info de las atracciones 
		 Assert.assertEquals(400.00, itinerario.getCostoTotalItinerario(),0.01);
		 Assert.assertEquals(3,itinerario.getCantidadAtraccionesIncluidas());
		 Assert.assertEquals(300.0, itinerario.getTiempoTotalItinerario(),0.01); 
		  
	 }
  	 
}