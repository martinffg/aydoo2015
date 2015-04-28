package martinffg.TIERRA_MEDIA;

import org.junit.Test;
import org.junit.Assert;

public class AtraccionTest {

 @Test
    public void crearAtraccion(){
	 
	 	PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
        
	 	// creo atraccion nombre Mordor, en la pos 20,20, con costo 152,20, tiempo prom recorrido 122 de tipo Aventura
	 	Atraccion atraccion = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA); 
	 	
	 	// valido que se haya creado el usuario
	 	Assert.assertNotNull(atraccion);
 
 	}
 
 	@Test
	public void probarConsultaDatosAtraccion(){
	 	
	 	PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
     
	 	// creo atraccion nombre Mordor, en la pos 10,10, con costo 152,20, tiempo prom recorrido 122 de tipo Aventura
	 	Atraccion atraccion = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA);
	 
	 	// valido que los datos ingresados en el creador por defecto sean correctos
	 	Assert.assertEquals("Mordor", atraccion.getNombreAtraccion());
	 	Assert.assertEquals(10, atraccion.getCoordenadasPosicion().getCoordenada_X());
	 	Assert.assertEquals(10, atraccion.getCoordenadasPosicion().getCoordenada_Y());
	 	Assert.assertEquals(152.20, atraccion.getCostoVisita(),0.01);
	 	Assert.assertEquals(1000, atraccion.getCupoVisitantesDiarios());
	 	Assert.assertEquals(122.0, atraccion.getPromedioTiempoNecesarioParaVisitar(),0.01);
	 	Assert.assertSame(TipoAtraccion.AVENTURA, atraccion.getTipoDeAtraccion());
 	 
	}
 	
 	@Test
 	public void probarCambiosADatosAtraccion(){
 		
 		PosicionGlobal posicionMordor = new PosicionGlobal(10,10,"Posicion Mordor");
 	     
	 	// creo atraccion nombre Mordor, en la pos 10,10, con costo 152,20, tiempo prom recorrido 122 de tipo Aventura
	 	Atraccion atraccion = new Atraccion("Mordor",posicionMordor,152.20,1000,122.0,TipoAtraccion.AVENTURA);
	 	
 		atraccion.setNombreAtraccion("GondorLand");
 		PosicionGlobal posicionNueva = new PosicionGlobal(30,30,"Posicion Nueva");
 		atraccion.setCoordenadasPosicion(posicionNueva);
 		atraccion.setCostoVisita(300.0);
 		atraccion.setCupoVisitantesDiarios(1500);
 		atraccion.setPromedioTiempoNecesarioParaVisitar(180.0);
 		atraccion.setTipoDeAtraccion(TipoAtraccion.PAISAJE);
 		
 		Assert.assertEquals("GondorLand", atraccion.getNombreAtraccion());
	 	Assert.assertEquals(30, atraccion.getCoordenadasPosicion().getCoordenada_X());
	 	Assert.assertEquals(30, atraccion.getCoordenadasPosicion().getCoordenada_Y());
	 	Assert.assertEquals(300.0, atraccion.getCostoVisita(),0.01);
	 	Assert.assertEquals(1500, atraccion.getCupoVisitantesDiarios());
	 	Assert.assertEquals(180.0, atraccion.getPromedioTiempoNecesarioParaVisitar(),0.01);
	 	Assert.assertSame(TipoAtraccion.PAISAJE, atraccion.getTipoDeAtraccion());
 		
 	}
 
  
}