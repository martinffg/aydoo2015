package martinffg.TIERRA_MEDIA;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;
import org.junit.Assert;

public class GeneradorVisitasEItinerariosTest {

 @Test
    public void crearGeneradorVisitasEItinerariosTest(){
	 
		 // Genero la lista de atracciones para poder correr la prueba
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
		 List<Atraccion> atracciones = new ArrayList<Atraccion>();
		 
		 // por ultimo agrego las atracciones a la lista de atraciones.
		 atracciones.add(atraccion1);
		 atracciones.add(atraccion2);
		 atracciones.add(atraccion3);
		 atracciones.add(atraccion4);
		 
		 // Genero un usuario de pruebas para poder correr el test
		 Date fechaHoy = new Date();
		 long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		 PromocionPorcentual promocionAsignada = new PromocionPorcentual("Promo Porcentual Prueba",fechaVence,5.0);
		 
		 Usuario usuario = new Usuario("usuarioPrueba",10000,6,6,promocionAsignada,TipoAtraccion.AVENTURA); 
		 
		 // Finalmente Instancio el generador de Visitas e Itinerarios con las atracciones y el usuario pasado
		 GeneradorVisitasEItinerarios generador = new GeneradorVisitasEItinerarios(atracciones,usuario); 
	 	
	 	// valido que se haya creado el itinerario
	 	Assert.assertNotNull(generador);
	 	Assert.assertNotNull(generador.getListaAtraccionesDeTierraMedia());
	 	Assert.assertNotNull(generador.getUsuarioVisitante());
 
 	}
 
 @Test
	 public void getListaVisitasSugeridasPorInteresYDistanciaRelativaUsuarioTEST(){
		 
	 	// Genero la lista de atracciones para poder correr la prueba
		 
	 	 PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
		 PosicionGlobal posicionAldea = new PosicionGlobal(20,20,"Posicion Aldea");
		 PosicionGlobal posicionGondor = new PosicionGlobal(30,30,"Posicion Gondor");
		 PosicionGlobal posicionPancheria = new PosicionGlobal(5,5,"Posicion Pancheria");
	     
		 // creo las distintas atracciones
		 Atraccion atraccion1 = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA);
		 Atraccion atraccion2 = new Atraccion("Aldea",posicionAldea,47.80,1500,78.0,TipoAtraccion.DEGUSTACION);
		 Atraccion atraccion3 = new Atraccion("Gondor",posicionGondor,200.00,500,100.0,TipoAtraccion.PAISAJE);
		 Atraccion atraccion4 = new Atraccion("Pancheria",posicionPancheria,100.00,500,100.0,TipoAtraccion.DEGUSTACION);
		 
		 Atraccion atraccionActual;
		 
		 // ahora genero la lista de atracciones
		 List<Atraccion> atracciones = new ArrayList<Atraccion>();
		 List<Atraccion> atraccionesSugeridas;
		 
		 // por ultimo agrego las atracciones a la lista de atraciones.
		 atracciones.add(atraccion1);
		 atracciones.add(atraccion2);
		 atracciones.add(atraccion3);
		 atracciones.add(atraccion4);
		 
		 // Genero un usuario de pruebas para poder correr el test
		 Date fechaHoy = new Date();
		 long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		 PromocionPorcentual promocionAsignada = new PromocionPorcentual("Promo Porcentual Prueba",fechaVence,5.0);
		 
		 Usuario usuario = new Usuario("usuarioPrueba",10000,6,6,promocionAsignada,TipoAtraccion.DEGUSTACION); 
		 
		 // Finalmente Instancio el generador de Visitas e Itinerarios con las atracciones y el usuario pasado
		 GeneradorVisitasEItinerarios generador = new GeneradorVisitasEItinerarios(atracciones,usuario); 
	 	
		 // Consulto las atracciones sugeridas, primero quiero saber de las preferidas, de la mas cercana a la mas lejana y luego las restantes con el mismo orden
		 atraccionesSugeridas = generador.getListaVisitasSugeridasPorInteresYDistanciaRelativaUsuario();
		 ListIterator<Atraccion> iterador = atraccionesSugeridas.listIterator();
		 atraccionActual = iterador.next();
	 	
		 
		 // valido que se haya creado el itinerario
	 	Assert.assertNotNull(generador);
	 	Assert.assertNotNull(generador.getListaAtraccionesDeTierraMedia());
	 	Assert.assertNotNull(atraccionesSugeridas);
	 	Assert.assertEquals(4, atraccionesSugeridas.size());
	 	Assert.assertEquals(TipoAtraccion.DEGUSTACION, atraccionActual.getTipoDeAtraccion());
		 
	 }
 
 	@Test
 	public void getListaItinerariosSugeridosTEST(){
 		
 		// Genero la lista de atracciones para poder correr la prueba
		 
	 	 PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
		 PosicionGlobal posicionAldea = new PosicionGlobal(20,20,"Posicion Aldea");
		 PosicionGlobal posicionGondor = new PosicionGlobal(30,30,"Posicion Gondor");
		 PosicionGlobal posicionPancheria = new PosicionGlobal(5,5,"Posicion Pancheria");
	     
		 // creo las distintas atracciones
		 Atraccion atraccion1 = new Atraccion("Mordor",posicionMordor,152.20,1000,0.20,TipoAtraccion.AVENTURA);
		 Atraccion atraccion2 = new Atraccion("Aldea",posicionAldea,47.80,1500,0.8,TipoAtraccion.DEGUSTACION);
		 Atraccion atraccion3 = new Atraccion("Gondor",posicionGondor,200.00,500,1.0,TipoAtraccion.PAISAJE);
		 Atraccion atraccion4 = new Atraccion("Pancheria",posicionPancheria,100.00,500,0.5,TipoAtraccion.DEGUSTACION);
		 
		 // ahora genero la lista de atracciones
		 List<Atraccion> atracciones = new ArrayList<Atraccion>();
		 List<Itinerario> itinerariosSugeridos;
		 ListIterator<Itinerario> iterador;
		 Itinerario itinerarioActual;
		 
		 // por ultimo agrego las atracciones a la lista de atraciones.
		 atracciones.add(atraccion1);
		 atracciones.add(atraccion2);
		 atracciones.add(atraccion3);
		 atracciones.add(atraccion4);
		 
		 // Genero un usuario de pruebas para poder correr el test
		 Date fechaHoy = new Date();
		 long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		 PromocionPorcentual promocionAsignada = new PromocionPorcentual("Promo Porcentual Prueba",fechaVence,5.0);
		 
		 Usuario usuario = new Usuario("usuarioPrueba",10000,10,6,promocionAsignada,TipoAtraccion.DEGUSTACION); 
		 
		 // Finalmente Instancio el generador de Visitas e Itinerarios con las atracciones y el usuario pasado
		 GeneradorVisitasEItinerarios generador = new GeneradorVisitasEItinerarios(atracciones,usuario); 
	 	
		 // Consulto las atracciones sugeridas, primero quiero saber de las preferidas, de la mas cercana a la mas lejana y luego las restantes con el mismo orden
		 itinerariosSugeridos = generador.getListaItinerariosSugeridos();
		 
		 iterador = itinerariosSugeridos.listIterator();
		 itinerarioActual = iterador.next();
			 
		 // valido que se haya creado el itinerario
	 	Assert.assertNotNull(generador);
	 	Assert.assertNotNull(generador.getListaAtraccionesDeTierraMedia());
	 	Assert.assertNotNull(itinerariosSugeridos);
	 	Assert.assertEquals(4, atracciones.size());
	 	Assert.assertEquals(3, itinerariosSugeridos.size());
	 	Assert.assertEquals(3, itinerarioActual.getAtracciones().size()); 		
 		
 	}
 
}