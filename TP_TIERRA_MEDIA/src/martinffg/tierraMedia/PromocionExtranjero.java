package martinffg.tierraMedia;

import java.util.Date;

public class PromocionExtranjero extends Promocion {
	
	public PromocionExtranjero(String nombre,long fechaVencimiento,double valorDescuento){
		
		super(nombre,fechaVencimiento,valorDescuento,TipoPromocion.EXTRANJERO);
		
	}
	
	public double calcularDescuentoPromocionalAlItinerario(Itinerario itinerario){
		
		Date fechaHoy = new Date();
	 	long fechaHoyTimeStamp = fechaHoy.getTime();
	 	double descuento=0.0;
	 	
	 
	 	if (fechaHoyTimeStamp<=this.getUltimoDiaVigencia()){
	 		// al ser extranjero recibe descuento del 50% para todas las atracciones
	 		descuento= itinerario.getCostoTotalItinerario() * 0.5; 
	 	}
	 	
		return descuento;
		
	}
	
	public double calcularCostoPromocionalDelItinerario(Itinerario itinerario){
		
		Date fechaHoy = new Date();
	 	long fechaHoyTimeStamp = fechaHoy.getTime();
	 	double costo=0.0;
	 	
	 	if (fechaHoyTimeStamp<=this.getUltimoDiaVigencia()){
	 		costo= itinerario.getCostoTotalItinerario() * 0.5;
	 	}		
	 	
		return costo;
		
	}

}
