package martinffg.tierraMedia;

import java.util.ArrayList;
import java.util.ListIterator;

public class Itinerario {
	
	private String nombreItinerario;
	private ArrayList<Atraccion> atracciones;
	private double costoTotalItinerario;
	private double costoTotalItinerarioConPromocion;
	private double tiempoTotalItinerario;
	private int cantidadAtraccionesIncluidas;
	
	public Itinerario(String nombre) {
		
		this.nombreItinerario=nombre;
		this.atracciones = new ArrayList<Atraccion>();
		this.costoTotalItinerario= 0.0;
		this.costoTotalItinerarioConPromocion=0.0;
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
	
	public void setAtracciones(ArrayList<Atraccion> atracciones) {
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
		
		public ArrayList<Atraccion> getAtracciones() {
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

		public double getCostoTotalItinerarioConPromocion() {
			return costoTotalItinerarioConPromocion;
		}

	// setters
		public void setCostoTotalItinerarioConPromocion(
				double costoTotalItinerarioConPromocion) {
			this.costoTotalItinerarioConPromocion = costoTotalItinerarioConPromocion;
		}
	
}
