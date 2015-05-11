package martinffg.tierraMedia;

public class Usuario {
	
	private String nombre;
	private String password;
	private double presupuesto;
	private double tiempoDisponibleVisitas; // En Horas
	private double velocidadTraslado; // En KM/H
	private TipoAtraccion tipoAtraccionPreferida;
	private Promocion promocionAsignada;
	private PosicionGlobal posicionActual;
	
	public Usuario(String nombre_usuario,double presupuesto,double tiempoDisponible,
					double velocidad, Promocion promocionAsignadaVisitante,TipoAtraccion atraccion_pref){
		
		this.nombre = nombre_usuario;
		this.password = "";
		this.presupuesto = presupuesto;
		this.tiempoDisponibleVisitas = tiempoDisponible;
		this.velocidadTraslado = velocidad;
		this.tipoAtraccionPreferida = atraccion_pref;
		this.promocionAsignada = promocionAsignadaVisitante;
		this.posicionActual = new PosicionGlobal(0,0,"Origen_coordenadas");

	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}
	public double getTiempoDisponibleVisitas() {
		return tiempoDisponibleVisitas;
	}
	public void setTiempoDisponibleVisitas(int tiempoDisponibleVisitas) {
		this.tiempoDisponibleVisitas = tiempoDisponibleVisitas;
	}
	public double getVelocidadTraslado() {
		return velocidadTraslado;
	}
	public void setVelocidadTraslado(double velocidadTraslado) {
		this.velocidadTraslado = velocidadTraslado;
	}
	public TipoAtraccion getTipoAtraccionPreferida() {
		return tipoAtraccionPreferida;
	}
	public void setTipoAtraccionPreferida(TipoAtraccion tipoAtraccionPreferida) {
		this.tipoAtraccionPreferida = tipoAtraccionPreferida;
	}
	
	
	public Promocion getPromocionAsignada() {
		return promocionAsignada;
	}

	public void setPromocionAsignada(Promocion promocionAsignada) {
		this.promocionAsignada = promocionAsignada;
	}

	public PosicionGlobal getPosicionActual() {
		return posicionActual;
	}
	public void setPosicionActual(PosicionGlobal posicionActual) {
		this.posicionActual = posicionActual;
	}

}
