package martinffg.TIERRA_MEDIA;

import org.junit.Test;
import org.junit.Assert;

public class UsuarioTest {

 @Test
    public void crearUsuario(){
        
	 	// creo usuarioPrueba, con 10000 pesos, 6 Hs para recorrer, se mueve a 6 KM/H y prefiere las Aventuras
	 	Usuario usuario = new Usuario("usuarioPrueba",10000,6,6,TipoAtraccion.AVENTURA); 
	 	
	 	// valido que se haya creado el usuario
	 	Assert.assertNotNull(usuario);
 
 	}
 
 @Test
 	public void probarConsultaDatosUsuario(){
	 	
	 	Usuario usuario = new Usuario("usuarioPrueba",10000,6,6,TipoAtraccion.AVENTURA);
	 
	 	// valido que los datos ingresados en el creador por defecto sean correctos
	 	Assert.assertEquals("usuarioPrueba", usuario.getNombre());
	 	Assert.assertEquals("", usuario.getPassword());
	 	Assert.assertEquals(6, usuario.getTiempoDisponibleVisitas(),0.01);
	 	Assert.assertEquals(6, usuario.getVelocidadTraslado(),0.01);
	 	Assert.assertSame(TipoAtraccion.AVENTURA, usuario.getTipoAtraccionPreferida());
	 	Assert.assertEquals(0, usuario.getPosicionActual().getCoordenada_X());
	 	Assert.assertEquals(0, usuario.getPosicionActual().getCoordenada_Y());  
	 
 	}
 
 
}