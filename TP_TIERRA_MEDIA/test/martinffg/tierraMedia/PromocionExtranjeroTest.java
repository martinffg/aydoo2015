package martinffg.tierraMedia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import martinffg.tierraMedia.Atraccion;
import martinffg.tierraMedia.Itinerario;
import martinffg.tierraMedia.PosicionGlobal;
import martinffg.tierraMedia.PromocionExtranjero;
import martinffg.tierraMedia.TipoAtraccion;
import martinffg.tierraMedia.TipoPromocion;

import org.junit.Test;
import org.junit.Assert;

public class PromocionExtranjeroTest {

	@Test
    public void crearPromocionTest(){
	 	
	 	Date fechaHoy = new Date();
	 	long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
	 	PromocionExtranjero promocion = new PromocionExtranjero("Promo Extranjero Prueba",fechaVence,50.0); 
	 	
	 	// valido que se haya creado el promocion
	 	Assert.assertNotNull(promocion);
	 	Assert.assertEquals("Promo Extranjero Prueba", promocion.getNombrePromocion());
	 	Assert.assertEquals(fechaVence, promocion.getUltimoDiaVigencia());
	 	Assert.assertEquals(50.0, promocion.getValorDescuento(),0.01);
	 	Assert.assertEquals(TipoPromocion.EXTRANJERO,promocion.getTipoPromocion());
	 	
 	}
	
  	 
}