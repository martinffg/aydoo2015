package martinffg.TIERRA_MEDIA;

public class Atraccion {
	
	private String nombreAtraccion;
	private PosicionGlobal coordenadasPosicion;
	private double costoVisita;
	private int cupoVisitantesDiarios;
	private double promedioTiempoNecesarioParaVisitar; // En Horas
	private TipoAtraccion tipoDeAtraccion;
	
	public Atraccion(String nombre,PosicionGlobal coordenadas,double costo,int cupoDiario,
					double tiempoPromedio,TipoAtraccion tipo_atraccion){
		
		this.nombreAtraccion = nombre;
		this.coordenadasPosicion = coordenadas;
		this.costoVisita = costo;
		this.cupoVisitantesDiarios = cupoDiario;
		this.promedioTiempoNecesarioParaVisitar = tiempoPromedio;
		this.tipoDeAtraccion = tipo_atraccion;

	}
	
}
