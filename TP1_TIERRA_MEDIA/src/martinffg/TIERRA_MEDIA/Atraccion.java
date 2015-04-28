package martinffg.TIERRA_MEDIA;

public class Atraccion {
	
	private String nombreAtraccion;
	private PosicionGlobal coordenadasPosicion;
	private double costoVisita;
	private int cupoVisitantesDiarios;
	private int cuposDisponiblesAhora;
	private double promedioTiempoNecesarioParaVisitar; // En Horas
	private TipoAtraccion tipoDeAtraccion;
	
	public Atraccion(String nombre,PosicionGlobal coordenadas,double costo,int cupoDiario,
					double tiempoPromedio,TipoAtraccion tipo_atraccion){
		
		this.nombreAtraccion = nombre;
		this.coordenadasPosicion = coordenadas;
		this.costoVisita = costo;
		this.cupoVisitantesDiarios = cupoDiario;
		this.cuposDisponiblesAhora = cupoDiario;
		this.promedioTiempoNecesarioParaVisitar = tiempoPromedio;
		this.tipoDeAtraccion = tipo_atraccion;

	}

	public String getNombreAtraccion() {
		return nombreAtraccion;
	}

	public void setNombreAtraccion(String nombreAtraccion) {
		this.nombreAtraccion = nombreAtraccion;
	}

	public PosicionGlobal getCoordenadasPosicion() {
		return coordenadasPosicion;
	}

	public void setCoordenadasPosicion(PosicionGlobal coordenadasPosicion) {
		this.coordenadasPosicion = coordenadasPosicion;
	}

	public double getCostoVisita() {
		return costoVisita;
	}

	public void setCostoVisita(double costoVisita) {
		this.costoVisita = costoVisita;
	}

	public int getCupoVisitantesDiarios() {
		return cupoVisitantesDiarios;
	}

	public void setCupoVisitantesDiarios(int cupoVisitantesDiarios) {
		this.cupoVisitantesDiarios = cupoVisitantesDiarios;
	}

	public double getPromedioTiempoNecesarioParaVisitar() {
		return promedioTiempoNecesarioParaVisitar;
	}

	public void setPromedioTiempoNecesarioParaVisitar(
			double promedioTiempoNecesarioParaVisitar) {
		this.promedioTiempoNecesarioParaVisitar = promedioTiempoNecesarioParaVisitar;
	}

	public TipoAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public void setTipoDeAtraccion(TipoAtraccion tipoDeAtraccion) {
		this.tipoDeAtraccion = tipoDeAtraccion;
	}

	public int getCuposDisponiblesAhora() {
		return cuposDisponiblesAhora;
	}

	public void setCuposDisponiblesAhora(int cuposDisponiblesAhora) {
		this.cuposDisponiblesAhora = cuposDisponiblesAhora;
	}
	
}
