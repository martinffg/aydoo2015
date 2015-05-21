package martinffg.tierraMedia;

import martinffg.tierraMedia.TipoAtraccion;

import org.junit.Assert;
import org.junit.Test;


public class TipoAtraccionTest {
	
	@Test
	public void prueba1CompararTipoAtraccionTest(){
		
		TipoAtraccion tipo1 = TipoAtraccion.AVENTURA;
				
		Assert.assertEquals(TipoAtraccion.AVENTURA, tipo1);
		Assert.assertNotSame(TipoAtraccion.PAISAJE, tipo1);
		
	}
	
	@Test
	public void prueba2CompararTipoAtraccionTest(){
		
		TipoAtraccion tipo2 = TipoAtraccion.PAISAJE;

		Assert.assertEquals(TipoAtraccion.PAISAJE, tipo2);
		Assert.assertNotSame(TipoAtraccion.DEGUSTACION, tipo2);
		
	}
	
	@Test
	public void prueba3CompararTipoAtraccionTest(){
		
		TipoAtraccion tipo3 = TipoAtraccion.DEGUSTACION;

		Assert.assertEquals(TipoAtraccion.DEGUSTACION, tipo3);
		Assert.assertNotSame(TipoAtraccion.PAISAJE, tipo3);
		
	}

}
