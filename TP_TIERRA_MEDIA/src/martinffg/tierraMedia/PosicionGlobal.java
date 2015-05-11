package martinffg.tierraMedia;

public class PosicionGlobal {
	
	private int coordenada_X;
	
	private int coordenada_Y;
	
	private String etiquetaPosicionGlobal;
	
	public PosicionGlobal(int pos_X,int pos_Y,String etiqueta) {
		
		this.coordenada_X = pos_X;
		this.coordenada_Y = pos_Y;
		this.etiquetaPosicionGlobal= etiqueta;		
		
	}
	
	public double getDistanciaPuntoRemoto (PosicionGlobal posicionRemota){
		
		double distancia=0.0;
		
		int diferenciaEnX = posicionRemota.getCoordenada_X() - this.getCoordenada_X();
		
		int diferenciaEnY = posicionRemota.getCoordenada_Y() - this.getCoordenada_Y();
		
		double sumaCuadradoDiferencias = Math.pow(diferenciaEnX, 2) + Math.pow(diferenciaEnY, 2);
		
		distancia = Math.sqrt(sumaCuadradoDiferencias);
		
		return distancia;
	}
	
	public int getCoordenada_X() {
		return coordenada_X;
	}

	public void setCoordenada_X(int coordenada_X) {
		this.coordenada_X = coordenada_X;
	}

	public int getCoordenada_Y() {
		return coordenada_Y;
	}

	public void setCoordenada_Y(int coordenada_Y) {
		this.coordenada_Y = coordenada_Y;
	}

	public String getEtiquetaPosicionGlobal() {
		return etiquetaPosicionGlobal;
	}

	public void setEtiquetaPosicionGlobal(String etiquetaPosicionGlobal) {
		this.etiquetaPosicionGlobal = etiquetaPosicionGlobal;
	}
			
}