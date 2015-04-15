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

	/*
	@Test
    public void howManyShouldReturnNegativeWhenCharIsSymbolMoney(){
        
        CharCounter counter = new CharCounter("hello world");

        Assert.assertEquals(-1, counter.howMany('$'));

    }*/
	
	
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