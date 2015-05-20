package martinffg.tierraMedia;

import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

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
	
	@Test
	public void probarDescuentoPaqueteFamiliarAItinerario(){
		 
		double descuentoObtenido=0.0;
		
		Itinerario itinerario=null;
		 
		ArrayList<Atraccion> atracciones = generarAtraccionesDePrueba();
		 
		PromocionPaqueteFamiliar promocion = generarPromocionPaqueteFamiliarDePrueba();
		 
		Usuario usuario = new Usuario("usuarioPrueba",10000000,12,12,promocion,TipoAtraccion.AVENTURA); 
		 
		usuario.setCantidadTicketsGrupoFamiliar(4);
		 
		GeneradorVisitasEItinerarios generador = new GeneradorVisitasEItinerarios(atracciones,usuario);
		
		ArrayList<Itinerario> itinerariosSugeridos = generador.getListaItinerariosSugeridos();
		 
		ListIterator<Itinerario> iterador = itinerariosSugeridos.listIterator();
		 
		if (iterador.hasNext()) {
			
			itinerario=(Itinerario)iterador.next();
		 
			descuentoObtenido = promocion.calcularDescuentoPromocionalAlItinerario(itinerario);
			 
		}
		
		Assert.assertEquals(3,itinerariosSugeridos.size());
		Assert.assertEquals(4,usuario.getCantidadTicketsGrupoFamiliar());
		Assert.assertEquals(4,itinerario.getAtracciones().size());
		Assert.assertEquals(200.0,descuentoObtenido,0.01);
	}
	
	@Test
	public void probarTotalConDescuentoPorcentualAItinerario(){
		
		 double pagoConDescuento=0.0;
		
		Itinerario itinerario=null;
		 
		ArrayList<Atraccion> atracciones = generarAtraccionesDePrueba();
		 
		PromocionPaqueteFamiliar promocion = generarPromocionPaqueteFamiliarDePrueba();
		 
		Usuario usuario = new Usuario("usuarioPrueba",10000000,12,12,promocion,TipoAtraccion.AVENTURA); 
		 
		usuario.setCantidadTicketsGrupoFamiliar(4);
		 
		GeneradorVisitasEItinerarios generador = new GeneradorVisitasEItinerarios(atracciones,usuario);
		
		ArrayList<Itinerario> itinerariosSugeridos = generador.getListaItinerariosSugeridos();
		 
		ListIterator<Itinerario> iterador = itinerariosSugeridos.listIterator();
		 
		if (iterador.hasNext()) {
			
			itinerario=(Itinerario)iterador.next();
		 
			pagoConDescuento = promocion.calcularCostoPromocionalDelItinerario(itinerario);
			 
		}
		
		Assert.assertEquals(3,itinerariosSugeridos.size());
		Assert.assertEquals(4,usuario.getCantidadTicketsGrupoFamiliar());
		Assert.assertEquals(4,itinerario.getAtracciones().size());
		Assert.assertEquals(1800.0,pagoConDescuento,0.01);
	}
	
	
	// METODOS PRIVADOS DE LA CLASE DE PRUEBAS
	private PromocionPaqueteFamiliar generarPromocionPaqueteFamiliarDePrueba(){
		
		Date fechaHoy = new Date();
		long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
		
		return (new PromocionPaqueteFamiliar("Promo Paquete Familiar Prueba",fechaVence,0.0));
	}
	
	private ArrayList<Atraccion> generarAtraccionesDePrueba() {
		
		// Genero la lista de atracciones para poder correr la prueba
				 PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
				 PosicionGlobal posicionAldea = new PosicionGlobal(20,20,"Posicion Aldea");
				 PosicionGlobal posicionGondor = new PosicionGlobal(30,30,"Posicion Gondor");
				 PosicionGlobal posicionPancheria = new PosicionGlobal(5,5,"Posicion Pancheria");
			     
				 // creo las distintas atracciones
				 Atraccion atraccion1 = new Atraccion("Mordor",posicionMordor,152.20,1000,2.0,TipoAtraccion.AVENTURA);
				 Atraccion atraccion2 = new Atraccion("Aldea",posicionAldea,47.80,1500,1.0,TipoAtraccion.DEGUSTACION);
				 Atraccion atraccion3 = new Atraccion("Gondor",posicionGondor,200.00,500,1.0,TipoAtraccion.PAISAJE);
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