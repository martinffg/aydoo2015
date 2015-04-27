package martinffg.TIERRA_MEDIA;

import org.junit.Test;
import org.junit.Assert;

public class PosicionGlobalTest {

 @Test
    public void crearPosicionValida(){
        
        PosicionGlobal posicionNueva = new PosicionGlobal(-100,20,"nueva1");

        Assert.assertNotNull(posicionNueva);
        Assert.assertEquals(-100, posicionNueva.getCoordenada_X());
        Assert.assertEquals(20, posicionNueva.getCoordenada_Y());
    }
 
 @Test
 public void crearPosicionSinEtiqueta(){
     
     PosicionGlobal posicionNueva = new PosicionGlobal(-100,20,"");

     Assert.assertNotNull(posicionNueva);
     Assert.assertEquals(-100, posicionNueva.getCoordenada_X());
     Assert.assertEquals(20, posicionNueva.getCoordenada_Y());
     Assert.assertEquals("", posicionNueva.getEtiquetaPosicionGlobal());
     
 }
 
@Test
 public void probarGetDistanciaEntrePuntos(){
	 
	 PosicionGlobal posicionOrigen = new PosicionGlobal(0,0,"origen");
	 PosicionGlobal posicion1 = new PosicionGlobal(1,0,"posicion1");
	 PosicionGlobal posicion2 = new PosicionGlobal(1,1,"posicion2");
	 
	 Assert.assertEquals(1.0, posicionOrigen.getDistanciaPuntoRemoto(posicion1), 0.01);
	 Assert.assertEquals(1.41, posicionOrigen.getDistanciaPuntoRemoto(posicion2), 0.01);
	 
 }
 
}