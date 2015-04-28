package martinffg.TIERRA_MEDIA;

import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	
	private String nombreItinerario;
	private List<Atraccion> atracciones;
	private double costoTotalItinerario;
	private double tiempoTotalItinerario;
	private int cantidadAtraccionesIncluidas;
	
	public Itinerario(String nombre) {
		
		this.nombreItinerario=nombre;
		this.atracciones = new ArrayList<Atraccion>();
		this.costoTotalItinerario= 0.0;
		this.tiempoTotalItinerario= 0.0;
		this.cantidadAtraccionesIncluidas=0;
		
	}
	
	

}
