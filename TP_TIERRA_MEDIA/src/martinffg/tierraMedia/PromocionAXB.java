package martinffg.tierraMedia;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class PromocionAXB extends Promocion {
	
	public PromocionAXB(String nombre,long fechaVencimiento,double valorDescuento){
		
		super(nombre,fechaVencimiento,valorDescuento);
		
	}
	
	public double calcularDescuentoPromocionalAlItinerario(Itinerario itinerario){
		
		Date fechaHoy = new Date();
	 	long fechaHoyTimeStamp = fechaHoy.getTime();
	 	double descuento=0.0;
	 	int contador = 0;
	 	
	 	Atraccion atraccionActual;
	 	List<Atraccion> atracciones = itinerario.getAtracciones();
		ListIterator<Atraccion> iterador= atracciones.listIterator();
	 	
	 	if ((fechaHoyTimeStamp<=this.getUltimoDiaVigencia())&&(itinerario.getCantidadAtraccionesIncluidas()>=4)){
	 			 		
			while ((iterador.hasNext()) && (contador<3)){
				contador++;
				iterador.next();
			}
	 		
			atraccionActual = (Atraccion) iterador.next();
			
			descuento=atraccionActual.getCostoVisita();
	 	}
		
		return descuento;
		
	}
	
	public double calcularCostoPromocionalDelItinerario(Itinerario itinerario){
		
		Date fechaHoy = new Date();
	 	long fechaHoyTimeStamp = fechaHoy.getTime();
	 	double costo=0.0;
	 	int contador = 0;
	 	
	 	Atraccion atraccionActual;
	 	List<Atraccion> atracciones = itinerario.getAtracciones();
		ListIterator<Atraccion> iterador= atracciones.listIterator();
	 	
	 	if ((fechaHoyTimeStamp<=this.getUltimoDiaVigencia())&&(itinerario.getCantidadAtraccionesIncluidas()>=4)){
	 		
	 		while ((iterador.hasNext()) && (contador<3)){
				contador++;
				atraccionActual = (Atraccion) iterador.next();
				costo+=atraccionActual.getCostoVisita();
				
				
			}	

	 	}
		
		return costo;
		
	}
	

}
