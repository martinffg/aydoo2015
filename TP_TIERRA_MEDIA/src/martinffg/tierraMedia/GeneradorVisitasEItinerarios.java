package martinffg.tierraMedia;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.ListIterator;

public class GeneradorVisitasEItinerarios {
	
	private ArrayList<Atraccion> listaAtraccionesDeTierraMedia;
	private Usuario usuarioVisitante; 
	
	//private ArrayList<Atraccion> listaVisitasSugeridasPorInteresYDistanciaRelativaUsuario; 
	private ArrayList<Itinerario> listaItinerariosSugeridos;
	
	public GeneradorVisitasEItinerarios(ArrayList<Atraccion> listaAtracciones,Usuario visitante){
		
		this.listaAtraccionesDeTierraMedia = listaAtracciones;
		
		this.usuarioVisitante = visitante;
		
		this.listaItinerariosSugeridos = new ArrayList<Itinerario>();
		
		this.setListaItinerariosSugeridos(listaAtracciones,visitante);
			
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Atraccion> getListaVisitasSugeridasPorInteresYDistanciaRelativaUsuario () {
		
		PosicionGlobal posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
			
		ArrayList<Atraccion> listaVisitasSugeridas = new ArrayList<Atraccion>();
		ArrayList<Atraccion> listaVisitasPreferidas = new ArrayList<Atraccion>();
		ArrayList<Atraccion> listaVisitasNoPreferidas = new ArrayList<Atraccion>();
		
		Atraccion atraccionActual;
		
		double distanciaAlUsuario= 0.0;
	 	
		ListIterator<Atraccion> iteradorVisitas= this.getListaAtraccionesDeTierraMedia().listIterator();
				
		while (iteradorVisitas.hasNext()){
			
			atraccionActual = (Atraccion) iteradorVisitas.next();
			
			distanciaAlUsuario = posicionUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
			
			atraccionActual.setDistanciaAlPuntoDeConsulta(distanciaAlUsuario);
			
			if ((atraccionActual.getCuposDisponiblesAhora())>=(this.usuarioVisitante.getCantidadTicketsGrupoFamiliar())){
			
				if (atraccionActual.getTipoDeAtraccion()==this.getUsuarioVisitante().getTipoAtraccionPreferida()){
					listaVisitasPreferidas.add(atraccionActual);
				}  else {
					listaVisitasNoPreferidas.add(atraccionActual);
				}
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
				
				return 0;
			}
		});
		
		// ahora completo la lista de sugerencias con el orden impuesto
		listaVisitasSugeridas.addAll(listaVisitasPreferidas);
		listaVisitasSugeridas.addAll(listaVisitasNoPreferidas);
				
		return listaVisitasSugeridas;
		
	}	
	
	private Itinerario generarItinerarioSinOrden(ArrayList<Atraccion> listaAtracciones,Usuario visitante){
		
		PosicionGlobal posicionUsuario = visitante.getPosicionActual();
		int cantidadGrupoFamiliar= visitante.getCantidadTicketsGrupoFamiliar();
		double tiempoViajeAlaAtraccionActual = 0.0; // En Horas
		double distanciaAlUsuario = 0.0; // En KM/H
		double costoPromoSinOrden = 0.0;
		double descuentoGrupoFamiliarSinOrden = 0.0;
		@SuppressWarnings("unused")
		Atraccion atraccionActual,atraccionPrevia;
		
		// GENERO LA LISTA
		ArrayList<Atraccion> listaSinOrden = listaAtracciones;
		ListIterator<Atraccion> iteradorListaSinOrden= listaSinOrden.listIterator();
		Itinerario itinerarioSinOrden = new Itinerario("Itinerario Sin Orden");
		
		// itero la lista
		while (iteradorListaSinOrden.hasNext()){
						 
			atraccionActual = (Atraccion) iteradorListaSinOrden.next();
			
			// primero valido si hay cupo para la compra del usuario si hay la agrego al itinerario y sera atraccionPrevia para la proxima iteracion
			if ((atraccionActual.getCuposDisponiblesAhora())>=cantidadGrupoFamiliar){
				
				distanciaAlUsuario = posicionUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
				tiempoViajeAlaAtraccionActual = distanciaAlUsuario/visitante.getVelocidadTraslado();
				
				if ((cantidadGrupoFamiliar>=4)&&(visitante.getPromocionAsignada().getClass()==PromocionPaqueteFamiliar.class)){
					
					descuentoGrupoFamiliarSinOrden+= atraccionActual.getCostoVisita()*(4*0.10+(cantidadGrupoFamiliar-4)*0.30);
					
				}
				
				atraccionActual.setCostoVisita(atraccionActual.getCostoVisita() * cantidadGrupoFamiliar);
				
				if (((visitante.getTiempoDisponibleVisitas()-itinerarioSinOrden.getTiempoTotalItinerario()-atraccionActual.getPromedioTiempoNecesarioParaVisitar()-tiempoViajeAlaAtraccionActual)>=0) 
						&& ((visitante.getPresupuesto()-itinerarioSinOrden.getCostoTotalItinerario()-atraccionActual.getCostoVisita()+descuentoGrupoFamiliarSinOrden)>=0)) {			
					itinerarioSinOrden.agregarAtraccion(atraccionActual);
					itinerarioSinOrden.agregarTiempoViajeAlItinerario(tiempoViajeAlaAtraccionActual);
					posicionUsuario = atraccionActual.getCoordenadasPosicion();
				}
				
				atraccionPrevia = atraccionActual;
			}
			
		}
		
		// A LOS COSTOS CALCULADOS LES APLICO LAS PROMOCIONES VIGENTES PARA CADA USUARIO (EN SU PERFIL)
		if ((cantidadGrupoFamiliar>=4)&&(visitante.getPromocionAsignada().getClass()==PromocionPaqueteFamiliar.class)){
			costoPromoSinOrden = itinerarioSinOrden.getCostoTotalItinerario()-descuentoGrupoFamiliarSinOrden;
		} else { 
			costoPromoSinOrden = visitante.getPromocionAsignada().calcularCostoPromocionalDelItinerario(itinerarioSinOrden);
		}
		
		itinerarioSinOrden.setCostoTotalItinerarioConPromocion(costoPromoSinOrden);		
		
	return itinerarioSinOrden;
	}
	
	private Itinerario generarItinerarioPreferidos(ArrayList<Atraccion> listaAtracciones,Usuario visitante){
		
		PosicionGlobal posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
		int cantidadGrupoFamiliar= this.usuarioVisitante.getCantidadTicketsGrupoFamiliar();
		
		double tiempoViajeAlaAtraccionActual = 0.0; // En Horas
		double distanciaAlUsuario = 0.0; // En KM/H
		double costoPromoPreferidos = 0.0;
		double descuentoGrupoFamiliarPreferidos = 0.0;
		
		@SuppressWarnings("unused")
		Atraccion atraccionActual,atraccionPrevia;
		
		// GENERO LA LISTA
		ArrayList<Atraccion> listaPreferidosLuegoElResto = this.getListaVisitasSugeridasPorInteresYDistanciaRelativaUsuario();
		ListIterator<Atraccion> iteradorListaPreferidosLuegoElResto= listaPreferidosLuegoElResto.listIterator();
		// CREO EL ITINERARIO
		Itinerario itinerarioPreferidos = new Itinerario("Itinerario Preferidos");
		
		// itero la lista
		while (iteradorListaPreferidosLuegoElResto.hasNext()){
			 
			atraccionActual = (Atraccion) iteradorListaPreferidosLuegoElResto.next();
			
			// primero valido si hay cupo para la compra del usuario si hay la agrego al itinerario y sera atraccionPrevia para la proxima iteracion
			if ((atraccionActual.getCuposDisponiblesAhora())>=cantidadGrupoFamiliar){
			
				distanciaAlUsuario = posicionUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
				tiempoViajeAlaAtraccionActual = distanciaAlUsuario/this.getUsuarioVisitante().getVelocidadTraslado();
				
				if ((cantidadGrupoFamiliar>=4)&&(this.usuarioVisitante.getPromocionAsignada().getClass()==PromocionPaqueteFamiliar.class)){
					
					descuentoGrupoFamiliarPreferidos+= atraccionActual.getCostoVisita()*(4*0.10+(cantidadGrupoFamiliar-4)*0.30);
					
				}
				
				atraccionActual.setCostoVisita(atraccionActual.getCostoVisita() * cantidadGrupoFamiliar);			
				
				if (((this.getUsuarioVisitante().getTiempoDisponibleVisitas()-itinerarioPreferidos.getTiempoTotalItinerario()-atraccionActual.getPromedioTiempoNecesarioParaVisitar()-tiempoViajeAlaAtraccionActual)>=0) 
						&& ((this.getUsuarioVisitante().getPresupuesto()-itinerarioPreferidos.getCostoTotalItinerario()-atraccionActual.getCostoVisita()+descuentoGrupoFamiliarPreferidos)>=0)) {			
					itinerarioPreferidos.agregarAtraccion(atraccionActual);
					itinerarioPreferidos.agregarTiempoViajeAlItinerario(tiempoViajeAlaAtraccionActual);
					posicionUsuario = atraccionActual.getCoordenadasPosicion();
				}
				
				atraccionPrevia = atraccionActual;
			
			}
			
		}
		
		// A LOS COSTOS CALCULADOS LES APLICO LAS PROMOCIONES VIGENTES PARA CADA USUARIO (EN SU PERFIL)
		if ((cantidadGrupoFamiliar>=4)&&(this.usuarioVisitante.getPromocionAsignada().getClass()==PromocionPaqueteFamiliar.class)){
			costoPromoPreferidos = itinerarioPreferidos.getCostoTotalItinerario()-descuentoGrupoFamiliarPreferidos;
		} else { 
			costoPromoPreferidos = this.getUsuarioVisitante().getPromocionAsignada().calcularCostoPromocionalDelItinerario(itinerarioPreferidos);
		}
		
		itinerarioPreferidos.setCostoTotalItinerarioConPromocion(costoPromoPreferidos);		
		
	return itinerarioPreferidos;
	}
	
	private Itinerario generarItinerarioIncomodo(ArrayList<Atraccion> listaAtracciones,Usuario visitante){
		// inicializo variables locales necesarias
		PosicionGlobal posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
		int cantidadGrupoFamiliar= this.usuarioVisitante.getCantidadTicketsGrupoFamiliar();
		
		double tiempoViajeAlaAtraccionActual = 0.0; // En Horas
		double distanciaAlUsuario = 0.0; // En KM/H
		double costoPromoIncomodos = 0.0;
		double descuentoGrupoFamiliarIncomodos = 0.0;
		
		@SuppressWarnings("unused")
		Atraccion atraccionActual,atraccionPrevia;
		
		// GENERO LAS LISTAS 
		ArrayList<Atraccion> listaRestoLuegoPreferidos = this.getListaVisitasSugeridasPorDESINTERESYDistanciaRelativaUsuario();
		ListIterator<Atraccion> iteradorListaRestoLuegoPreferidos= listaRestoLuegoPreferidos.listIterator();
		// genero el itinerario
		Itinerario itinerarioIncomodo = new Itinerario("Itinerario Incomodo");
		
		// VUELVO AL USUARIO AL ORIGEN PARA ANALIZAR EL PROXIMO ITINERARIO 
		posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
		tiempoViajeAlaAtraccionActual = 0.0; // En Horas
		distanciaAlUsuario = 0.0; // En KM/H
				
		while (iteradorListaRestoLuegoPreferidos.hasNext()){
			 
			atraccionActual = (Atraccion) iteradorListaRestoLuegoPreferidos.next();
			
			// primero valido si hay cupo para la compra del usuario si hay la agrego al itinerario y sera atraccionPrevia para la proxima iteracion
			if ((atraccionActual.getCuposDisponiblesAhora())>=cantidadGrupoFamiliar){
			
				distanciaAlUsuario = posicionUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
				tiempoViajeAlaAtraccionActual = distanciaAlUsuario/this.getUsuarioVisitante().getVelocidadTraslado();
				
				if ((cantidadGrupoFamiliar>=4)&&(this.usuarioVisitante.getPromocionAsignada().getClass()==PromocionPaqueteFamiliar.class)){
					descuentoGrupoFamiliarIncomodos+= atraccionActual.getCostoVisita()*(4*0.10+(cantidadGrupoFamiliar-4)*0.30);
				}
				
				atraccionActual.setCostoVisita(atraccionActual.getCostoVisita() * cantidadGrupoFamiliar);			
				
				if (((this.getUsuarioVisitante().getTiempoDisponibleVisitas()-itinerarioIncomodo.getTiempoTotalItinerario()-atraccionActual.getPromedioTiempoNecesarioParaVisitar()-tiempoViajeAlaAtraccionActual)>=0) 
						&& ((this.getUsuarioVisitante().getPresupuesto()-itinerarioIncomodo.getCostoTotalItinerario()-atraccionActual.getCostoVisita()+descuentoGrupoFamiliarIncomodos)>=0)) {			
					itinerarioIncomodo.agregarAtraccion(atraccionActual);
					itinerarioIncomodo.agregarTiempoViajeAlItinerario(tiempoViajeAlaAtraccionActual);
					posicionUsuario = atraccionActual.getCoordenadasPosicion();
				}
				
				atraccionPrevia = atraccionActual;
			}
			
		}	
		
		// A LOS COSTOS CALCULADOS LES APLICO LAS PROMOCIONES VIGENTES PARA CADA USUARIO (EN SU PERFIL)
		if ((cantidadGrupoFamiliar>=4)&&(this.usuarioVisitante.getPromocionAsignada().getClass()==PromocionPaqueteFamiliar.class)){
			costoPromoIncomodos = itinerarioIncomodo.getCostoTotalItinerario()-descuentoGrupoFamiliarIncomodos;
		} else { 
			costoPromoIncomodos = this.getUsuarioVisitante().getPromocionAsignada().calcularCostoPromocionalDelItinerario(itinerarioIncomodo);
		}
		
		itinerarioIncomodo.setCostoTotalItinerarioConPromocion(costoPromoIncomodos);
			
	return itinerarioIncomodo;
	}
	
	private void setListaItinerariosSugeridos(ArrayList<Atraccion> listaAtracciones,Usuario visitante) {
		
		// CREO LOS ITINERARIOS QUE IRAN GUARDANDO LA INFORMACION DE CADA RECORRIDO
		Itinerario itinerarioSinOrden = this.generarItinerarioSinOrden(listaAtracciones,visitante);
		Itinerario itinerarioPreferidos = this.generarItinerarioPreferidos(listaAtracciones,visitante);
		Itinerario itinerarioIncomodo = this.generarItinerarioIncomodo(listaAtracciones,visitante);
		
		// AGREGO LOS ITINERARIOS GENERADOS A LA LISTA DE ITINERARIOS QUE DARE COMO RESULTADO
		ArrayList<Itinerario> listaIt = new ArrayList<Itinerario>();
		
		listaIt.add(itinerarioSinOrden);
		listaIt.add(itinerarioPreferidos);
		listaIt.add(itinerarioIncomodo);
		
		this.listaItinerariosSugeridos.addAll(listaIt);
		
	}	
	
	public ArrayList<Itinerario> getListaItinerariosSugeridos() {
		return listaItinerariosSugeridos;
	}

	public ArrayList<Atraccion> getListaAtraccionesDeTierraMedia() {
			return listaAtraccionesDeTierraMedia;
	}

	public Usuario getUsuarioVisitante() {
		return usuarioVisitante;
	}
		
@SuppressWarnings({ "unchecked", "rawtypes" })
private ArrayList<Atraccion> getListaVisitasSugeridasPorDESINTERESYDistanciaRelativaUsuario() {
		
		PosicionGlobal posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
			
		ArrayList<Atraccion> listaVisitasSugeridas = new ArrayList<Atraccion>();
		ArrayList<Atraccion> listaVisitasPreferidas = new ArrayList<Atraccion>();
		ArrayList<Atraccion> listaVisitasNoPreferidas = new ArrayList<Atraccion>();
		
		Atraccion atraccionActual;
		
		double distanciaAlUsuario= 0.0;
	 	
		ListIterator<Atraccion> iteradorVisitas= this.getListaAtraccionesDeTierraMedia().listIterator();
				
		while (iteradorVisitas.hasNext()){
			
			atraccionActual = (Atraccion) iteradorVisitas.next();
			
			distanciaAlUsuario = posicionUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
			
			atraccionActual.setDistanciaAlPuntoDeConsulta(distanciaAlUsuario);
			
			if ((atraccionActual.getCuposDisponiblesAhora())>=(this.usuarioVisitante.getCantidadTicketsGrupoFamiliar())){
			
				if (atraccionActual.getTipoDeAtraccion()==this.getUsuarioVisitante().getTipoAtraccionPreferida()){
					listaVisitasPreferidas.add(atraccionActual);
				}  else {
					listaVisitasNoPreferidas.add(atraccionActual);
				}
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
	
	public boolean esUsuarioExtranjero(ArrayList<Atraccion> listaAtraccionesDeTM,Usuario usuarioVisitante){
		
		boolean resultadoConsulta=false;
		PosicionGlobal posicionDomicilioUsuario = usuarioVisitante.getPosicionDomicilio();
		Atraccion atraccionActual;
		double distanciaMenorAlUsuario= 0.0;
		double distanciaActualAlUsuario= 0.0;
		int contador = 0;
	 	
		ListIterator<Atraccion> iteradorVisitas= listaAtraccionesDeTM.listIterator();
				
		while (iteradorVisitas.hasNext()){
			
			atraccionActual = (Atraccion) iteradorVisitas.next();
			
			distanciaActualAlUsuario = posicionDomicilioUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
			
			if ((distanciaActualAlUsuario<distanciaMenorAlUsuario) || (contador==0)) {
				distanciaMenorAlUsuario = distanciaActualAlUsuario;
			}
			contador++;
		}
		
		if (distanciaMenorAlUsuario>200.0) {
			resultadoConsulta = true;
		}
		
		return resultadoConsulta;
	}
	

}
