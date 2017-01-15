package packCodigo;

import packGestores.GestorSesion;

public class Jugador {
	private String nombre;
	private int puntuacion;
	
	public Jugador(String pNombre){
		nombre = pNombre;
	}
	
	public void establecerPuntuacion(int pPuntuacion){
		puntuacion=pPuntuacion;
	}

	public int compareTo(Jugador pivote) {
		if(pivote.obtenerPunt()>this.obtenerPunt()){
			return 1;
		}else{
			if(pivote.obtenerPunt()<this.obtenerPunt()){
				return -1;
			}else{return 0;}
		}
	}
	
	public String obtenerNombre(){
		return nombre;
	}
	
	public int obtenerPunt(){
		return puntuacion;
	}
	
	private void ponerPunt(){
		this.puntuacion=GestorSesion.getSesion().obtenerPuntuacion();
	}
	
	//public para las JUnit
	private boolean mismoJugador(){
		boolean mismo = false;
		if(this.obtenerNombre().equals(GestorSesion.getSesion().obtenerNombreJugador())){
			mismo = true;
		}
		return mismo;
	}
	//public para las JUnit
	private void asignarPuntuacionR(){
		mayorPunt();
	}
	
	private void mayorPunt(){
		if(GestorSesion.getSesion().obtenerPuntuacion()>=this.obtenerPunt()){
			this.ponerPunt();
		}
	}
}
