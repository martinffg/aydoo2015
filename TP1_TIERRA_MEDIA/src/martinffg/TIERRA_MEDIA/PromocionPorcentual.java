package martinffg.TIERRA_MEDIA;

public class PromocionPorcentual extends Promocion {
	
	public PromocionPorcentual(String nombre,long fechaVencimiento,double valorDescuento){
		
		super(nombre,fechaVencimiento,valorDescuento,TipoPromocion.PORCENTUAL);
		
	}
	
	public double calcularDescuentoPromocionalAlItinerario(Itinerario itinerario){
		
		return (itinerario.getCostoTotalItinerario() * super.getValorDescuento())/100.0;
		
	}
	
	public double calcularCostoPromocionalDelItinerario(Itinerario itinerario){
		
		return (itinerario.getCostoTotalItinerario() * (100.0 - super.getValorDescuento()))/100.0;
		
	}
	

}
