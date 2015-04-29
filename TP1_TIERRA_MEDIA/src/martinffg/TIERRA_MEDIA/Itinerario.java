package martinffg.TIERRA_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
		
	// todos los setters	
	public void setNombreItinerario(String nombreItinerario) {
		this.nombreItinerario = nombreItinerario;
	}

	public void agregarAtraccion(Atraccion atraccionActual){
		
		if (atraccionActual.getCuposDisponiblesAhora()>0) {
			this.cantidadAtraccionesIncluidas++;
			this.costoTotalItinerario+=atraccionActual.getCostoVisita();
			this.tiempoTotalItinerario+=atraccionActual.getPromedioTiempoNecesarioParaVisitar();
			this.atracciones.add(atraccionActual);
		}
		
	}
	
	public void setAtracciones(List<Atraccion> atracciones) {
		Atraccion atraccionActual;
		ListIterator<Atraccion> iterador= atracciones.listIterator();
		while (iterador.hasNext()){
			atraccionActual = (Atraccion) iterador.next();
			if (atraccionActual.getCuposDisponiblesAhora()>0) {
				this.cantidadAtraccionesIncluidas++;
				this.costoTotalItinerario+=atraccionActual.getCostoVisita();
				this.tiempoTotalItinerario+=atraccionActual.getPromedioTiempoNecesarioParaVisitar();
				this.atracciones.add(atraccionActual);
			}
		}
	}
	
	public void agregarTiempoViajeAlItinerario(double tiempoViaje) {
		
		this.tiempoTotalItinerario+=tiempoViaje;
	}
	
	// todos los getters
		public String getNombreItinerario() {
			return nombreItinerario;
		}
		
		public List<Atraccion> getAtracciones() {
			return atracciones;
		}
		
		public double getCostoTotalItinerario() {
			return costoTotalItinerario;
		}

		public double getTiempoTotalItinerario() {
			return tiempoTotalItinerario;
		}

		public int getCantidadAtraccionesIncluidas() {
			return cantidadAtraccionesIncluidas;
		}
	
}
