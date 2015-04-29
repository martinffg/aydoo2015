package martinffg.TIERRA_MEDIA;

import java.util.ArrayList;
import java.util.List;

public class GeneradorVisitasEItinerarios {
	
	private List<Atraccion> listaAtraccionesDeTierraMedia;
	private Usuario usuarioVisitante; 
	
	private List<Atraccion> listaVisitasSugeridasPorDistanciaRelativaUsuario; 
	private List<Itinerario> listaItinerariosSugeridos;
	
	public GeneradorVisitasEItinerarios(List<Atraccion> listaAtracciones,Usuario visitante){
		
		this.listaAtraccionesDeTierraMedia = listaAtracciones;
		
		this.usuarioVisitante = visitante;
		
		this.listaVisitasSugeridasPorDistanciaRelativaUsuario = new ArrayList<Atraccion>();
		
		this.listaItinerariosSugeridos = new ArrayList<Itinerario>();
		
	}

	public List<Atraccion> getListaVisitasSugeridasPorDistanciaRelativaUsuario() {
		return listaVisitasSugeridasPorDistanciaRelativaUsuario;
	}

	public void setListaVisitasSugeridasPorDistanciaRelativaUsuario(
			List<Atraccion> listaVisitasSugeridasPorDistanciaRelativaUsuario) {
		this.listaVisitasSugeridasPorDistanciaRelativaUsuario = listaVisitasSugeridasPorDistanciaRelativaUsuario;
	}

	public List<Itinerario> getListaItinerariosSugeridos() {
		return listaItinerariosSugeridos;
	}

	public void setListaItinerariosSugeridos(
			List<Itinerario> listaItinerariosSugeridos) {
		this.listaItinerariosSugeridos = listaItinerariosSugeridos;
	}

	public List<Atraccion> getListaAtraccionesDeTierraMedia() {
		return listaAtraccionesDeTierraMedia;
	}

	public Usuario getUsuarioVisitante() {
		return usuarioVisitante;
	}
	
	

}
