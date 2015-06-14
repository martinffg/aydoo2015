package martinffg.tierraMedia;

import java.util.Date;

public class PromocionPaqueteFamiliar extends Promocion {
	
	// el valorDescuento es indiferente en el constructor
	public PromocionPaqueteFamiliar(String nombre,long fechaVencimiento,double valorDescuento){
	
		super(nombre,fechaVencimiento,valorDescuento);
		
	}
	
	public double calcularDescuentoPromocionalAlItinerario(Itinerario itinerario){
		
		Date fechaHoy = new Date();
	 	long fechaHoyTimeStamp = fechaHoy.getTime();
	 	double descuento=0.0;
	 
	 	if (fechaHoyTimeStamp<=this.getUltimoDiaVigencia()){
	 		descuento= itinerario.getCostoTotalItinerario()-itinerario.getCostoTotalItinerarioConPromocion();
	 	}
	 	
		return descuento;
		
	}
	
	public double calcularCostoPromocionalDelItinerario(Itinerario itinerario){
		
		Date fechaHoy = new Date();
	 	long fechaHoyTimeStamp = fechaHoy.getTime();
	 	double costo=0.0;
	 	
	 	if (fechaHoyTimeStamp<=this.getUltimoDiaVigencia()){
	 		costo= itinerario.getCostoTotalItinerarioConPromocion();
	 	}		
	 	
		return costo;
		
	}

}
