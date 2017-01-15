package packCodigo;

import java.util.ArrayList;
import packGestores.GestorSesion;

public abstract class Casilla {

	private boolean bandera=false;
	private String coordenada;
	private boolean desvelada=false;
	private ArrayList<String> lVecinas = new ArrayList<String>();
	private boolean banderaPista = false;
	
	public Casilla(){
		
		
	}

	public String obtenerCoordenadas() {
		return this.coordenada;

	}
	
	public void descubrir(){
		if (!desvelada){
			this.desvelada = true;
		}
	}
	
	public void anadirVecino(String vecino){
		if(!lVecinas.contains(vecino)){
			lVecinas.add(vecino);
		}

	}
	
	public void inicializar(String coor){
		this.coordenada = coor;
	}
	
	public void cambioBandera(){
		if(!desvelada){
			if (!bandera && GestorSesion.getSesion().obtenerBanderas() != 0){
				bandera = true;
			} else if(bandera == true){
				bandera = false;
			}
		}
	}
	
	public ArrayList<String> obtenerVecinos(){
		return this.lVecinas;
	}
	
	public boolean estaDesvelada(){
		return this.desvelada;
	}
	
	public boolean tieneBandera(){
		return this.bandera;
	}

	public void imprimirInfo() {
		
	}
	
	public boolean getBanderaPista(){
		return this.banderaPista;
	}
	
	public void setBanderaPista(String pCoord){
		if(coordenada.equals(pCoord)){
			banderaPista = true;
		}
	}
}

