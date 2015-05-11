package martinffg.tierraMedia;

import java.util.Date;

public class PromocionPorcentual extends Promocion {
	
	public PromocionPorcentual(String nombre,long fechaVencimiento,double valorDescuento){
		
		super(nombre,fechaVencimiento,valorDescuento,TipoPromocion.PORCENTUAL);
		
	}
	
	public double calcularDescuentoPromocionalAlItinerario(Itinerario itinerario){
		
		Date fechaHoy = new Date();
	 	long fechaHoyTimeStamp = fechaHoy.getTime();
	 	double descuento=0.0;
	 	
	 	if (fechaHoyTimeStamp<=this.getUltimoDiaVigencia()){
	 		descuento=(itinerario.getCostoTotalItinerario() * super.getValorDescuento())/100.0;
	 	}
		
		return descuento;
		
	}
	
	public double calcularCostoPromocionalDelItinerario(Itinerario itinerario){
		
		Date fechaHoy = new Date();
	 	long fechaHoyTimeStamp = fechaHoy.getTime();
	 	double costo=0.0;
	 	
	 	if (fechaHoyTimeStamp<=this.getUltimoDiaVigencia()){
	 		costo=(itinerario.getCostoTotalItinerario() * (100.0 - super.getValorDescuento()))/100.0;
	 	}
	 	
		return costo;
		
	}
	

}
