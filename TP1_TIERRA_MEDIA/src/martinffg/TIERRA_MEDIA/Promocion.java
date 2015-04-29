package martinffg.TIERRA_MEDIA;

public abstract class Promocion {
	
	private String nombrePromocion;
	
	private long ultimoDiaVigencia;
	
	private double valorDescuento;
	
	private TipoPromocion tipoPromocion;
	
	public Promocion(String nombre,long vigenteHasta,double valorDesc,TipoPromocion tipo){
		
		this.setNombrePromocion(nombre);
		this.setUltimoDiaVigencia(vigenteHasta);
		this.setValorDescuento(valorDesc);
		this.setTipoPromocion(tipo);
		
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

	public TipoPromocion getTipoPromocion() {
		return tipoPromocion;
	}

	public void setTipoPromocion(TipoPromocion tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}
		

}
