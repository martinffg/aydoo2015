package martinffg.tierraMedia;

import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import martinffg.tierraMedia.Atraccion;
import martinffg.tierraMedia.GeneradorVisitasEItinerarios;
import martinffg.tierraMedia.Itinerario;
import martinffg.tierraMedia.PosicionGlobal;
import martinffg.tierraMedia.PromocionPorcentual;
import martinffg.tierraMedia.TipoAtraccion;
import martinffg.tierraMedia.Usuario;
import org.junit.Test;
import org.junit.Assert;

public class GeneradorVisitasEItinerariosTest {

 @Test
    public void crearGeneradorVisitasEItinerariosTest(){
	 
		  // ahora genero la lista de atracciones
		 ArrayList<Atraccion> atracciones = this.generarAtraccionesDePrueba();
		 
		 // Genero un usuario de pruebas para poder correr el test
		 Date fechaHoy = new Date();
		 long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		 PromocionPorcentual promocionAsignada = new PromocionPorcentual("Promo Porcentual Prueba",fechaVence,5.0);
		 
		 Usuario usuario = new Usuario("usuarioPrueba",10000,12,12,promocionAsignada,TipoAtraccion.AVENTURA); 
		 
		 // Finalmente Instancio el generador de Visitas e Itinerarios con las atracciones y el usuario pasado
		 GeneradorVisitasEItinerarios generador = new GeneradorVisitasEItinerarios(atracciones,usuario);
		 	 	
	 	// valido que se haya creado el itinerario
	 	Assert.assertNotNull(generador);
	 	Assert.assertNotNull(generador.getListaAtraccionesDeTierraMedia());
	 	Assert.assertNotNull(generador.getUsuarioVisitante());
 
 	}
 
 @Test
	public void getListaVisitasSugeridasPorInteresYDistanciaRelativaUsuarioTEST(){
		 
	 	 // ahora genero la lista de atracciones
		 ArrayList<Atraccion> atracciones = this.generarAtraccionesDePrueba();
	     ArrayList<Atraccion> atraccionesSugeridas;
	     Atraccion atraccionActual;
		 
		 // Genero un usuario de pruebas para poder correr el test
		 Date fechaHoy = new Date();
		 long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		 PromocionPorcentual promocionAsignada = new PromocionPorcentual("Promo Porcentual Prueba",fechaVence,5.0);
		 
		 Usuario usuario = new Usuario("usuarioPrueba",100000,12,16,promocionAsignada,TipoAtraccion.DEGUSTACION); 
		 
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
 		
 		 // ahora genero la lista de atracciones
 		 ArrayList<Atraccion> atracciones = this.generarAtraccionesDePrueba();
 		 // listas de itinerarios
 		 ArrayList<Itinerario> itinerariosSugeridos;
		 ListIterator<Itinerario> iterador;
		 Itinerario itinerarioActual;
		 
		 // Genero un usuario de pruebas para poder correr el test
		 Date fechaHoy = new Date();
		 long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		 PromocionPorcentual promocionAsignada = new PromocionPorcentual("Promo Porcentual Prueba",fechaVence,5.0);
		 
		 Usuario usuario = new Usuario("usuarioPrueba",100000,12,16,promocionAsignada,TipoAtraccion.DEGUSTACION); 
		 
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
	 	Assert.assertEquals(4, itinerarioActual.getAtracciones().size()); 		
 		
 	}
 	
 	private ArrayList<Atraccion> generarAtraccionesDePrueba() {
		
		// Genero la lista de atracciones para poder correr la prueba
				 PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
				 PosicionGlobal posicionAldea = new PosicionGlobal(20,20,"Posicion Aldea");
				 PosicionGlobal posicionGondor = new PosicionGlobal(30,30,"Posicion Gondor");
				 PosicionGlobal posicionPancheria = new PosicionGlobal(5,5,"Posicion Pancheria");
			     
				 // creo las distintas atracciones
				 Atraccion atraccion1 = new Atraccion("Mordor",posicionMordor,152.20,1000,1.0,TipoAtraccion.AVENTURA);
				 Atraccion atraccion2 = new Atraccion("Aldea",posicionAldea,47.80,1500,1.0,TipoAtraccion.DEGUSTACION);
				 Atraccion atraccion3 = new Atraccion("Gondor",posicionGondor,200.00,500,1.5,TipoAtraccion.PAISAJE);
				 Atraccion atraccion4 = new Atraccion("Pancheria",posicionPancheria,100.00,500,1.0,TipoAtraccion.DEGUSTACION);
				 
				 // ahora genero la lista de atracciones
				 ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
				 
				 // por ultimo agrego las atracciones a la lista de atraciones.
				 atracciones.add(atraccion1);
				 atracciones.add(atraccion2);
				 atracciones.add(atraccion3);
				 atracciones.add(atraccion4);
		 
		 return atracciones;
		
	}
 	
}