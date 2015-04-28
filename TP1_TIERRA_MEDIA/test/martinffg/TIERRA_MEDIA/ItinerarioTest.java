package martinffg.TIERRA_MEDIA;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

public class ItinerarioTest {

 @Test
    public void crearItinerario(){
	 
	 	Itinerario itinerario = new Itinerario("Itinerario Prueba"); 
	 	
	 	// valido que se haya creado el itinerario
	 	Assert.assertNotNull(itinerario);
 
 	}
 
 	 
}