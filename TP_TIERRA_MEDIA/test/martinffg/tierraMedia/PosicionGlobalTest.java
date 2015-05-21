package martinffg.tierraMedia;

import martinffg.tierraMedia.PosicionGlobal;

import org.junit.Test;
import org.junit.Assert;

public class PosicionGlobalTest {

 @Test
    public void validarPosicionCreadaTest(){
        
        PosicionGlobal posicionNueva = new PosicionGlobal(-100,20,"nueva1");
        
        // valido la info de la posicion
        Assert.assertNotNull(posicionNueva);
        Assert.assertEquals(-100, posicionNueva.getCoordenada_X());
        Assert.assertEquals(20, posicionNueva.getCoordenada_Y());
    }
 
 @Test
 public void crearPosicionSinEtiquetaTest(){
     
     PosicionGlobal posicionNueva = new PosicionGlobal(-100,20,"");

     //valido la info de la posicion creada
     Assert.assertNotNull(posicionNueva);
     Assert.assertEquals(-100, posicionNueva.getCoordenada_X());
     Assert.assertEquals(20, posicionNueva.getCoordenada_Y());
     Assert.assertEquals("", posicionNueva.getEtiquetaPosicionGlobal());
     
 }
 
@Test
 public void probarGetDistanciaEntrePuntosTest(){
	 
	 PosicionGlobal posicionOrigen = new PosicionGlobal(0,0,"origen");
	 PosicionGlobal posicion1 = new PosicionGlobal(1,0,"posicion1");
	 PosicionGlobal posicion2 = new PosicionGlobal(1,1,"posicion2");
	 
	 //valido las distancias comparando
	 Assert.assertEquals(1.0, posicionOrigen.getDistanciaPuntoRemoto(posicion1), 0.01);
	 Assert.assertEquals(1.41, posicionOrigen.getDistanciaPuntoRemoto(posicion2), 0.01);
	 
 }
 
}