package martinffg.ejercicio1;

import java.util.HashMap;


public class CharCounter {
	
	private int cantidadCaracteres;
	
	private String cadenaCaracteres;
	
	
	public CharCounter(String cadena) {
		
		this.setCantidadCaracteres(cadena.length());
		
		this.setCadenaCaracteres(cadena);
	
	}
	
	public int howMany(char caracter){
		
		int cantidadOcurrencias=0;
		char[] cadenaChar = this.cadenaCaracteres.toCharArray();
		
		
		switch (caracter){
			case '$': cantidadOcurrencias=-1; 
					break;
			case '@': cantidadOcurrencias=-2; 
				break;
			default: {
				
				if (!this.cadenaCaracteres.isEmpty()){
					
					for (int i=0;i<this.getCantidadCaracteres();i++){
						
						if( cadenaChar[i]==caracter){
							
							cantidadOcurrencias++;
						}			
						
					}				
					
				}
			}
		
		}
				
		return cantidadOcurrencias;
		
	}

	
	public HashMap<Character,Integer> countAll() {
		
		HashMap<Character,Integer> mapa = new HashMap<Character,Integer>();
		char[] cadenaChar = this.getCadenaCaracteres().toCharArray();
		
		// inicializo el hashmap con los 255 caracteres ascii en 0 ocurrencias
		for (int k=0;k<256;k++){
			
			
				mapa.put((char)k, (int)0);			
			
		}			
		
		
		// ahora analizo la cadena de caracteres específica		
		for (int i=0;i<this.getCantidadCaracteres();i++){
			
			if (cadenaChar[i] !=' ') {
				
				mapa.put((Character)cadenaChar[i], (Integer)this.howMany(cadenaChar[i]));
				
			}			
			
		}				
			
		return mapa;
	}
	
	
	public int getCantidadCaracteres() {
		return cantidadCaracteres;
	}


	public void setCantidadCaracteres(int cantidadCaracteres) {
		this.cantidadCaracteres = cantidadCaracteres;
	}


	public String getCadenaCaracteres() {
		return cadenaCaracteres;
	}


	public void setCadenaCaracteres(String cadenaCaracteres) {
		this.cadenaCaracteres = cadenaCaracteres;
	}
	
}