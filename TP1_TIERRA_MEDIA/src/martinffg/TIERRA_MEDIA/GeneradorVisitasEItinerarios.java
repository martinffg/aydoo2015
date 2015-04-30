package martinffg.TIERRA_MEDIA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class GeneradorVisitasEItinerarios {
	
	private List<Atraccion> listaAtraccionesDeTierraMedia;
	private Usuario usuarioVisitante; 
	
	//private List<Atraccion> listaVisitasSugeridasPorInteresYDistanciaRelativaUsuario; 
	private List<Itinerario> listaItinerariosSugeridos;
	
	public GeneradorVisitasEItinerarios(List<Atraccion> listaAtracciones,Usuario visitante){
		
		this.listaAtraccionesDeTierraMedia = listaAtracciones;
		
		this.usuarioVisitante = visitante;
			
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Atraccion> getListaVisitasSugeridasPorInteresYDistanciaRelativaUsuario () {
		
		PosicionGlobal posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
			
		List<Atraccion> listaVisitasSugeridas = new ArrayList<Atraccion>();
		List<Atraccion> listaVisitasPreferidas = new ArrayList<Atraccion>();
		List<Atraccion> listaVisitasNoPreferidas = new ArrayList<Atraccion>();
		
		Atraccion atraccionActual;
		
		double distanciaAlUsuario= 0.0;
	 	
		ListIterator<Atraccion> iteradorVisitas= this.getListaAtraccionesDeTierraMedia().listIterator();
				
		while (iteradorVisitas.hasNext()){
			
			atraccionActual = (Atraccion) iteradorVisitas.next();
			
			distanciaAlUsuario = posicionUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
			
			atraccionActual.setDistanciaAlPuntoDeConsulta(distanciaAlUsuario);
			
			if (atraccionActual.getTipoDeAtraccion()==this.getUsuarioVisitante().getTipoAtraccionPreferida()){
				listaVisitasPreferidas.add(atraccionActual);
			}  else {
				listaVisitasNoPreferidas.add(atraccionActual);
			}
			
		}
		
		// ordeno la lista de visitasPreferidas de menor a mayor distancia al usuario
		Collections.sort(listaVisitasPreferidas, new Comparator() {
			
			@SuppressWarnings("unused")
			public int compare(Atraccion a1, Atraccion a2) {
				
				return new Double(a1.getDistanciaAlPuntoDeConsulta()).compareTo(new Double(a2.getDistanciaAlPuntoDeConsulta()));
			}

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		
		// ordeno la lista de visitasNoPreferidas de menor a mayor distancia al usuario
		Collections.sort(listaVisitasNoPreferidas, new Comparator() {
			
			@SuppressWarnings("unused")
			public int compare(Atraccion a1, Atraccion a2) {
				
				return new Double(a1.getDistanciaAlPuntoDeConsulta()).compareTo(new Double(a2.getDistanciaAlPuntoDeConsulta()));
			}

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		
		// ahora completo la lista de sugerencias con el orden impuesto
		listaVisitasSugeridas.addAll(listaVisitasPreferidas);
		listaVisitasSugeridas.addAll(listaVisitasNoPreferidas);
				
		return listaVisitasSugeridas;
		
	}	
	
	public List<Itinerario> getListaItinerariosSugeridos() {
		return listaItinerariosSugeridos;
	}

		public List<Atraccion> getListaAtraccionesDeTierraMedia() {
		return listaAtraccionesDeTierraMedia;
	}

	public Usuario getUsuarioVisitante() {
		return usuarioVisitante;
	}
	
	

}
