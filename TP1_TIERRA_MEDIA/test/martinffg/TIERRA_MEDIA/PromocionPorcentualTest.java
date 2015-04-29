package martinffg.TIERRA_MEDIA;

import java.util.Date;
import org.junit.Test;
import org.junit.Assert;

public class PromocionPorcentualTest {

	@Test
    public void crearPromocionTest(){
	 	
	 	Date fechaHoy = new Date();
	 	long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 días, uso formato TimeStamp
	 	PromocionPorcentual promocion = new PromocionPorcentual("Promo Porcentual Prueba",fechaVence,5.0); 
	 	
	 	// valido que se haya creado el promocion
	 	Assert.assertNotNull(promocion);
	 	Assert.assertEquals("Promo Porcentual Prueba", promocion.getNombrePromocion());
	 	Assert.assertEquals(fechaVence, promocion.getUltimoDiaVigencia());
	 	Assert.assertEquals(5.0, promocion.getValorDescuento(),0.01);
	 	Assert.assertEquals(TipoPromocion.PORCENTUAL,promocion.getTipoPromocion());
	 	
 	}
  	 
}