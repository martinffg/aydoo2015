package martinffg.TIERRA_MEDIA;

import org.junit.Test;
import org.junit.Assert;

public class PosicionGlobalTest {

 @Test
    public void crearPosicionValida(){
        
        PosicionGlobal posicionNueva = new PosicionGlobal(-100,20,"nueva1");

        Assert.assertNotNull(posicionNueva);
    }
 
 @Test
 public void crearPosicionSinEtiqueta(){
     
     PosicionGlobal posicionNueva = new PosicionGlobal(-100,20,"");

     Assert.assertNotNull(posicionNueva);
 }
 
}