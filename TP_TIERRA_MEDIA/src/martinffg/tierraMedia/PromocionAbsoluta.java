package martinffg.tierraMedia;

import java.util.Date;

public class PromocionAbsoluta extends Promocion {
	
	public PromocionAbsoluta(String nombre,long fechaVencimiento,double valorDescuento){
		
		super(nombre,fechaVencimiento,valorDescuento,TipoPromocion.ABSOLUTA);
		
	}
	
	public double calcularDescuentoPromocionalAlItinerario(Itinerario itinerario){
		
		Date fechaHoy = new Date();
	 	long fechaHoyTimeStamp = fechaHoy.getTime();
	 	double descuento=0.0;
	 	
	 	if ((fechaHoyTimeStamp<=this.getUltimoDiaVigencia())
	 			&&(this.getValorDescuento()<=itinerario.getCostoTotalItinerario())){
	 		descuento=this.getValorDescuento();
	 	}
		
		return descuento;
		
	}
	
	public double calcularCostoPromocionalDelItinerario(Itinerario itinerario){
		
		Date fechaHoy = new Date();
	 	long fechaHoyTimeStamp = fechaHoy.getTime();
	 	double costo=0.0;
	 	
	 	if ((fechaHoyTimeStamp<=this.getUltimoDiaVigencia())
	 			&&(this.getValorDescuento()<=itinerario.getCostoTotalItinerario())){
	 		costo=itinerario.getCostoTotalItinerario()-this.getValorDescuento();
	 	}
		
		return costo;
		
	}
	

}
