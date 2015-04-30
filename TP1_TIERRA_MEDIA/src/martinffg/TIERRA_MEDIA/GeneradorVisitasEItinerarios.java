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
		
		this.listaItinerariosSugeridos = new ArrayList<Itinerario>();
			
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
		
		// inicializo variables locales necesarias
		PosicionGlobal posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
		
		double tiempoViajeAlaAtraccionActual = 0.0; // En Horas
		double distanciaAlUsuario = 0.0; // En KM/H
		double costoPromoSinOrden = 0.0;
		double costoPromoPreferidos = 0.0;
		double costoPromoIncomodos = 0.0;
		
		@SuppressWarnings("unused")
		Atraccion atraccionActual,atraccionPrevia;
		
		// GENERO LAS LISTAS PARA EVALUAR LOS ITINERARIOS PARA EL USUARIO
		List<Atraccion> listaSinOrden = this.getListaAtraccionesDeTierraMedia();
		List<Atraccion> listaPreferidosLuegoElResto = this.getListaVisitasSugeridasPorInteresYDistanciaRelativaUsuario();
		List<Atraccion> listaRestoLuegoPreferidos = this.getListaVisitasSugeridasPorDESINTERESYDistanciaRelativaUsuario();
		
		// GENERO LOS ITERADORES DE CADA LISTA
		ListIterator<Atraccion> iteradorListaSinOrden= listaSinOrden.listIterator();
		ListIterator<Atraccion> iteradorListaPreferidosLuegoElResto= listaPreferidosLuegoElResto.listIterator();
		ListIterator<Atraccion> iteradorListaRestoLuegoPreferidos= listaRestoLuegoPreferidos.listIterator();
		
		// CREO LOS ITINERARIOS QUE IRAN GUARDANDO LA INFORMACION DE CADA RECORRIDO
		Itinerario itinerarioSinOrden = new Itinerario("Itinerario Sin Orden");
		Itinerario itinerarioPreferidos = new Itinerario("Itinerario Preferidos");
		Itinerario itinerarioIncomodo = new Itinerario("Itinerario Incomodo");
		
		// COMIENZO A ITERAR CADA LISTA Y GUARDANDO LA INFORMACION EN CADA ITINERARIO, 
		// CORTARA SI NO EXCEDE ALGUNA LIMITACION DEL USUARIO O FALTA DE CUPO
		
		while (iteradorListaSinOrden.hasNext()){
						 
			atraccionActual = (Atraccion) iteradorListaSinOrden.next();
			
			distanciaAlUsuario = posicionUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
			tiempoViajeAlaAtraccionActual = distanciaAlUsuario/this.getUsuarioVisitante().getVelocidadTraslado();
			
			if (((this.getUsuarioVisitante().getTiempoDisponibleVisitas()-itinerarioSinOrden.getTiempoTotalItinerario()-atraccionActual.getPromedioTiempoNecesarioParaVisitar()-tiempoViajeAlaAtraccionActual)>=0) 
					&& ((this.getUsuarioVisitante().getPresupuesto()-itinerarioSinOrden.getCostoTotalItinerario()-atraccionActual.getCostoVisita())>=0)
					&&(atraccionActual.getCuposDisponiblesAhora()>0)) {			
				itinerarioSinOrden.agregarAtraccion(atraccionActual);
				itinerarioSinOrden.agregarTiempoViajeAlItinerario(tiempoViajeAlaAtraccionActual);
				posicionUsuario = atraccionActual.getCoordenadasPosicion();
			}
			
			atraccionPrevia = atraccionActual;
			
		}
		
		// VUELVO AL USUARIO AL ORIGEN PARA ANALIZAR EL PROXIMO ITINERARIO 
		posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
		tiempoViajeAlaAtraccionActual = 0.0; // En Horas
		distanciaAlUsuario = 0.0; // En KM/H
		
		while (iteradorListaPreferidosLuegoElResto.hasNext()){
			 
			atraccionActual = (Atraccion) iteradorListaPreferidosLuegoElResto.next();
			
			distanciaAlUsuario = posicionUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
			tiempoViajeAlaAtraccionActual = distanciaAlUsuario/this.getUsuarioVisitante().getVelocidadTraslado();
			
			if (((this.getUsuarioVisitante().getTiempoDisponibleVisitas()-itinerarioPreferidos.getTiempoTotalItinerario()-atraccionActual.getPromedioTiempoNecesarioParaVisitar()-tiempoViajeAlaAtraccionActual)>=0) 
					&& ((this.getUsuarioVisitante().getPresupuesto()-itinerarioPreferidos.getCostoTotalItinerario()-atraccionActual.getCostoVisita())>=0)
					&&(atraccionActual.getCuposDisponiblesAhora()>0)) {			
				itinerarioPreferidos.agregarAtraccion(atraccionActual);
				itinerarioPreferidos.agregarTiempoViajeAlItinerario(tiempoViajeAlaAtraccionActual);
				posicionUsuario = atraccionActual.getCoordenadasPosicion();
			}
			
			atraccionPrevia = atraccionActual;
			
		}
		
		// VUELVO AL USUARIO AL ORIGEN PARA ANALIZAR EL PROXIMO ITINERARIO 
		posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
		tiempoViajeAlaAtraccionActual = 0.0; // En Horas
		distanciaAlUsuario = 0.0; // En KM/H
				
		while (iteradorListaRestoLuegoPreferidos.hasNext()){
			 
			atraccionActual = (Atraccion) iteradorListaRestoLuegoPreferidos.next();
			
			distanciaAlUsuario = posicionUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
			tiempoViajeAlaAtraccionActual = distanciaAlUsuario/this.getUsuarioVisitante().getVelocidadTraslado();
			
			if (((this.getUsuarioVisitante().getTiempoDisponibleVisitas()-itinerarioIncomodo.getTiempoTotalItinerario()-atraccionActual.getPromedioTiempoNecesarioParaVisitar()-tiempoViajeAlaAtraccionActual)>=0) 
					&& ((this.getUsuarioVisitante().getPresupuesto()-itinerarioIncomodo.getCostoTotalItinerario()-atraccionActual.getCostoVisita())>=0)
					&&(atraccionActual.getCuposDisponiblesAhora()>0)) {			
				itinerarioIncomodo.agregarAtraccion(atraccionActual);
				itinerarioIncomodo.agregarTiempoViajeAlItinerario(tiempoViajeAlaAtraccionActual);
				posicionUsuario = atraccionActual.getCoordenadasPosicion();
			}
			
			atraccionPrevia = atraccionActual;
			
		}	
		
		// A LOS COSTOS CALCULADOS LES APLICO LAS PROMOCIONES VIGENTES PARA CADA USUARIO (EN SU PERFIL)
		costoPromoSinOrden = this.getUsuarioVisitante().getPromocionAsignada().calcularCostoPromocionalDelItinerario(itinerarioSinOrden);
		itinerarioSinOrden.setCostoTotalItinerarioConPromocion(costoPromoSinOrden);
		costoPromoPreferidos = this.getUsuarioVisitante().getPromocionAsignada().calcularCostoPromocionalDelItinerario(itinerarioPreferidos);
		itinerarioPreferidos.setCostoTotalItinerarioConPromocion(costoPromoPreferidos);
		costoPromoIncomodos = this.getUsuarioVisitante().getPromocionAsignada().calcularCostoPromocionalDelItinerario(itinerarioIncomodo);
		itinerarioIncomodo.setCostoTotalItinerarioConPromocion(costoPromoIncomodos);
		
		// AGREGO LOS ITINERARIOS GENERADOS A LA LISTA DE ITINERARIOS QUE DARE COMO RESULTADO
		this.listaItinerariosSugeridos.add(itinerarioSinOrden);
		this.listaItinerariosSugeridos.add(itinerarioPreferidos);
		this.listaItinerariosSugeridos.add(itinerarioIncomodo);		
		
		return this.listaItinerariosSugeridos;
	}

		public List<Atraccion> getListaAtraccionesDeTierraMedia() {
		return listaAtraccionesDeTierraMedia;
	}

	public Usuario getUsuarioVisitante() {
		return usuarioVisitante;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Atraccion> getListaVisitasSugeridasPorDESINTERESYDistanciaRelativaUsuario() {
		
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
		listaVisitasSugeridas.addAll(listaVisitasNoPreferidas);
		listaVisitasSugeridas.addAll(listaVisitasPreferidas);
						
		return listaVisitasSugeridas;
		
	}
	

}
