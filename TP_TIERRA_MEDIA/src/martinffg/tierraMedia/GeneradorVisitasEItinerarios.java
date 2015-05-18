package martinffg.tierraMedia;

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
	
	private Itinerario generarItinerarioSinOrden(){
		
		PosicionGlobal posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
		int cantidadGrupoFamiliar= this.usuarioVisitante.getCantidadTicketsGrupoFamiliar();
		double tiempoViajeAlaAtraccionActual = 0.0; // En Horas
		double distanciaAlUsuario = 0.0; // En KM/H
		double costoPromoSinOrden = 0.0;
		double descuentoGrupoFamiliarSinOrden = 0.0;
		@SuppressWarnings("unused")
		Atraccion atraccionActual,atraccionPrevia;
		
		// GENERO LA LISTA
		List<Atraccion> listaSinOrden = this.getListaAtraccionesDeTierraMedia();
		ListIterator<Atraccion> iteradorListaSinOrden= listaSinOrden.listIterator();
		Itinerario itinerarioSinOrden = new Itinerario("Itinerario Sin Orden");
		
		// itero la lista
		while (iteradorListaSinOrden.hasNext()){
						 
			atraccionActual = (Atraccion) iteradorListaSinOrden.next();
			
			// primero valido si hay cupo para la compra del usuario si hay la agrego al itinerario y sera atraccionPrevia para la proxima iteracion
			if ((atraccionActual.getCuposDisponiblesAhora())>=cantidadGrupoFamiliar){
				
				distanciaAlUsuario = posicionUsuario.getDistanciaPuntoRemoto(atraccionActual.getCoordenadasPosicion());
				tiempoViajeAlaAtraccionActual = distanciaAlUsuario/this.getUsuarioVisitante().getVelocidadTraslado();
				
				if ((cantidadGrupoFamiliar>=4)&&(this.usuarioVisitante.getPromocionAsignada().getTipoPromocion()==TipoPromocion.PAQUETE_FAMILIAR)){
					
					descuentoGrupoFamiliarSinOrden+= atraccionActual.getCostoVisita()*(4*0.10+(cantidadGrupoFamiliar-4)*0.30);
					
				}
				
				atraccionActual.setCostoVisita(atraccionActual.getCostoVisita() * cantidadGrupoFamiliar);
				
				if (((this.getUsuarioVisitante().getTiempoDisponibleVisitas()-itinerarioSinOrden.getTiempoTotalItinerario()-atraccionActual.getPromedioTiempoNecesarioParaVisitar()-tiempoViajeAlaAtraccionActual)>=0) 
						&& ((this.getUsuarioVisitante().getPresupuesto()-itinerarioSinOrden.getCostoTotalItinerario()-atraccionActual.getCostoVisita()+descuentoGrupoFamiliarSinOrden)>=0)) {			
					itinerarioSinOrden.agregarAtraccion(atraccionActual);
					itinerarioSinOrden.agregarTiempoViajeAlItinerario(tiempoViajeAlaAtraccionActual);
					posicionUsuario = atraccionActual.getCoordenadasPosicion();
				}
				
				atraccionPrevia = atraccionActual;
			}
			
		}
		
		// A LOS COSTOS CALCULADOS LES APLICO LAS PROMOCIONES VIGENTES PARA CADA USUARIO (EN SU PERFIL)
		if ((cantidadGrupoFamiliar>=4)&&(this.usuarioVisitante.getPromocionAsignada().getTipoPromocion()==TipoPromocion.PAQUETE_FAMILIAR)){
			costoPromoSinOrden = itinerarioSinOrden.getCostoTotalItinerario()-descuentoGrupoFamiliarSinOrden;
		} else { 
			costoPromoSinOrden = this.getUsuarioVisitante().getPromocionAsignada().calcularCostoPromocionalDelItinerario(itinerarioSinOrden);
		}
		
		itinerarioSinOrden.setCostoTotalItinerarioConPromocion(costoPromoSinOrden);		
		
	return itinerarioSinOrden;
	}
	
	private Itinerario generarItinerarioPreferidos(){
		
		PosicionGlobal posicionUsuario = this.getUsuarioVisitante().getPosicionActual();
		int cantidadGrupoFamiliar= this.usuarioVisitante.getCantidadTicketsGrupoFamiliar();
		
		double tiempoViajeAlaAtraccionActual = 0.0; // En Horas
		double distanciaAlUsuario = 0.0; // En KM/H
		double costoPromoPreferidos = 0.0;
		double descuentoGrupoFamiliarPreferidos = 0.0;
		
		@SuppressWarnings("unused")
		Atraccion atraccionActual,atraccionPrevia;
		
		// GENERO LA LISTA
		List<Atraccion> listaPreferidosLuegoElResto = this.getListaVisitasSugeridasPorInteresYDistanciaRelativaUsuario();
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
				
				if ((cantidadGrupoFamiliar>=4)&&(this.usuarioVisitante.getPromocionAsignada().getTipoPromocion()==TipoPromocion.PAQUETE_FAMILIAR)){
					
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
		if ((cantidadGrupoFamiliar>=4)&&(this.usuarioVisitante.getPromocionAsignada().getTipoPromocion()==TipoPromocion.PAQUETE_FAMILIAR)){
			costoPromoPreferidos = itinerarioPreferidos.getCostoTotalItinerario()-descuentoGrupoFamiliarPreferidos;
		} else { 
			costoPromoPreferidos = this.getUsuarioVisitante().getPromocionAsignada().calcularCostoPromocionalDelItinerario(itinerarioPreferidos);
		}
		
		itinerarioPreferidos.setCostoTotalItinerarioConPromocion(costoPromoPreferidos);		
		
	return itinerarioPreferidos;
	}
	
	private Itinerario generarItinerarioIncomodo(){
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
		List<Atraccion> listaRestoLuegoPreferidos = this.getListaVisitasSugeridasPorDESINTERESYDistanciaRelativaUsuario();
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
				
				if ((cantidadGrupoFamiliar>=4)&&(this.usuarioVisitante.getPromocionAsignada().getTipoPromocion()==TipoPromocion.PAQUETE_FAMILIAR)){
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
		if ((cantidadGrupoFamiliar>=4)&&(this.usuarioVisitante.getPromocionAsignada().getTipoPromocion()==TipoPromocion.PAQUETE_FAMILIAR)){
			costoPromoIncomodos = itinerarioIncomodo.getCostoTotalItinerario()-descuentoGrupoFamiliarIncomodos;
		} else { 
			costoPromoIncomodos = this.getUsuarioVisitante().getPromocionAsignada().calcularCostoPromocionalDelItinerario(itinerarioIncomodo);
		}
		
		itinerarioIncomodo.setCostoTotalItinerarioConPromocion(costoPromoIncomodos);
			
	return itinerarioIncomodo;
	}
	
	public List<Itinerario> getListaItinerariosSugeridos() {
		
		// CREO LOS ITINERARIOS QUE IRAN GUARDANDO LA INFORMACION DE CADA RECORRIDO
		Itinerario itinerarioSinOrden = this.generarItinerarioSinOrden();
		Itinerario itinerarioPreferidos = this.generarItinerarioPreferidos();
		Itinerario itinerarioIncomodo = this.generarItinerarioIncomodo();
		
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
	
	public boolean esUsuarioExtranjero(List<Atraccion> listaAtraccionesDeTM,Usuario usuarioVisitante){
		
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
