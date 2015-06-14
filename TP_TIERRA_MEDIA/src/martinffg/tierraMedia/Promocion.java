package martinffg.tierraMedia;

public abstract class Promocion {
	
	private String nombrePromocion;
	
	private long ultimoDiaVigencia;
	
	private double valorDescuento;
	
	public Promocion(String nombre,long vigenteHasta,double valorDesc){
		
		this.setNombrePromocion(nombre);
		this.setUltimoDiaVigencia(vigenteHasta);
		this.setValorDescuento(valorDesc);
	}
		
	public abstract double calcularDescuentoPromocionalAlItinerario(Itinerario itinerario);
	
	public abstract double calcularCostoPromocionalDelItinerario(Itinerario itinerario);

	public String getNombrePromocion() {
		return nombrePromocion;
	}

	public void setNombrePromocion(String nombrePromocion) {
		this.nombrePromocion = nombrePromocion;
	}

	public long getUltimoDiaVigencia() {
		return ultimoDiaVigencia;
	}

	public void setUltimoDiaVigencia(long ultimoDiaVigencia) {
		this.ultimoDiaVigencia = ultimoDiaVigencia;
	}
	
	public double getValorDescuento() {
		return valorDescuento;
	}

	public void setValorDescuento(double valorDescuento) {
		this.valorDescuento = valorDescuento;
	}
}
