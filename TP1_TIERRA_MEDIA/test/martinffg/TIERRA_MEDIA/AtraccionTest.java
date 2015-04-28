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
  
}