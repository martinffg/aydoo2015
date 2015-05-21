package martinffg.tierraMedia;

import java.util.Date;

import martinffg.tierraMedia.PosicionGlobal;
import martinffg.tierraMedia.PromocionPorcentual;
import martinffg.tierraMedia.TipoAtraccion;
import martinffg.tierraMedia.Usuario;

import org.junit.Test;
import org.junit.Assert;

public class UsuarioTest {

 @Test
    public void crearUsuarioTest(){
        
	 	Date fechaHoy = new Date();
	 	long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
	 	PromocionPorcentual promocionAsignada = new PromocionPorcentual("Promo Porcentual Prueba",fechaVence,5.0);
	 
	 	Usuario usuario = new Usuario("usuarioPrueba",10000,6,6,promocionAsignada,TipoAtraccion.AVENTURA); 
	 	
	 	// valido que se haya creado el usuario referencia no nula
	 	Assert.assertNotNull(usuario);
 
 	}
 
 @Test
 	public void consultaDatosCargadosEnUsuarioNuevoTest(){
	 		 
	 	Date fechaHoy = new Date();
	 	long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
	 	PromocionPorcentual promocionAsignada = new PromocionPorcentual("Promo Porcentual Prueba",fechaVence,5.0);
	 
	 	Usuario usuario = new Usuario("usuarioPrueba",10000,6,6,promocionAsignada,TipoAtraccion.AVENTURA);
	 
	 	// valido que los datos ingresados en el constructor sean correctos
	 	Assert.assertEquals("usuarioPrueba", usuario.getNombre());
	 	Assert.assertEquals("", usuario.getPassword());
	 	Assert.assertEquals(6, usuario.getTiempoDisponibleVisitas(),0.01);
	 	Assert.assertEquals(6, usuario.getVelocidadTraslado(),0.01);
	 	Assert.assertSame(TipoAtraccion.AVENTURA, usuario.getTipoAtraccionPreferida());
	 	Assert.assertEquals(0, usuario.getPosicionActual().getCoordenada_X());
	 	Assert.assertEquals(0, usuario.getPosicionActual().getCoordenada_Y());  
	 
 	}
 
 	@Test
 	public void cambiarDatosAUsuarioCreadoTest(){
 		
 		Date fechaHoy = new Date();
	 	long fechaVence = fechaHoy.getTime()+864000; // Dentro de 10 dias, uso formato TimeStamp
	 	PromocionPorcentual promocionAsignada = new PromocionPorcentual("Promo Porcentual Prueba",fechaVence,5.0);
 		
 		Usuario usuario = new Usuario("usuarioPrueba",10000,6,6,promocionAsignada,TipoAtraccion.AVENTURA);
 		
 		usuario.setNombre("Pepe");
 		usuario.setPassword("Lepuf");
 		PosicionGlobal posicionNueva = new PosicionGlobal(20,20,"posicion nueva");
 		usuario.setPosicionActual(posicionNueva);
 		usuario.setPresupuesto(15000);
 		usuario.setTiempoDisponibleVisitas(7);
 		usuario.setVelocidadTraslado(8);
 		usuario.setTipoAtraccionPreferida(TipoAtraccion.PAISAJE);
 		
 		//valido que los cambios hayan aplicado
 		Assert.assertEquals("Pepe", usuario.getNombre());
	 	Assert.assertEquals("Lepuf", usuario.getPassword());
	 	Assert.assertEquals(20, usuario.getPosicionActual().getCoordenada_X());
	 	Assert.assertEquals(20, usuario.getPosicionActual().getCoordenada_Y()); 
	 	Assert.assertEquals("posicion nueva", usuario.getPosicionActual().getEtiquetaPosicionGlobal());
	 	Assert.assertEquals(15000, usuario.getPresupuesto(),0.01);
	 	Assert.assertEquals(7, usuario.getTiempoDisponibleVisitas(),0.01);
	 	Assert.assertEquals(8, usuario.getVelocidadTraslado(),0.01);
	 	Assert.assertSame(TipoAtraccion.PAISAJE, usuario.getTipoAtraccionPreferida());
 		
 	}
 
}