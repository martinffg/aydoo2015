package martinffg.TIERRA_MEDIA;

import org.junit.Assert;
import org.junit.Test;


public class TipoAtraccionTest {
	
	@Test
	public void probarTipoAtraccionTest(){
		
		TipoAtraccion tipo1 = TipoAtraccion.AVENTURA;
		TipoAtraccion tipo2 = TipoAtraccion.PAISAJE;
		TipoAtraccion tipo3 = TipoAtraccion.DEGUSTACION;
		
		
		Assert.assertEquals(TipoAtraccion.AVENTURA, tipo1);
		Assert.assertEquals(TipoAtraccion.PAISAJE, tipo2);
		Assert.assertEquals(TipoAtraccion.DEGUSTACION, tipo3);
		Assert.assertNotSame(TipoAtraccion.AVENTURA, tipo3);
		Assert.assertNotSame(TipoAtraccion.PAISAJE, tipo1);
		Assert.assertNotSame(TipoAtraccion.DEGUSTACION, tipo2);
		
	}

}
