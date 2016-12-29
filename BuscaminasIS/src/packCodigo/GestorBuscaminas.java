package packCodigo;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import packVentanas.IU_Jugar;

public class GestorBuscaminas {

	private static GestorBuscaminas miBuscaminas = new GestorBuscaminas();
	private int contMinas;
	private int puntuacion;
	private Jugador j;
	
	/****************
	 * CONSTRUCTORA	*
	 ****************/
	private GestorBuscaminas(){
	}
	
	/************************
	 * Singleton.			*
	 * @return miBuscaminas	*
	 ************************/
	public static GestorBuscaminas getBuscaminas(){
		return miBuscaminas;
	}
	

	/************************
	 * 						*
	 * @return 				*
	 ************************/
	public void setContMinas(){
		contMinas = GestorSesion.getSesion().getTablero().minas().size();
	}
	
	public int getContMinas() {
		return contMinas;
	}
	

	/**Iniciamos el juego**/
	public void inicioJuego(int pNivel){
		GestorSesion.getSesion().setNivel(pNivel);
		GestorSesion.getSesion().setJuego(true);
		GestorSesion.getSesion().iniciarTablero(pNivel);
		setContMinas();
		GestorSesion.getSesion().setContBanderas(contMinas);
		GestorSesion.getSesion().crono();
	}
	
	/**Iniciamos partica contrarreloj**/
	public void iniciarPartidaContrarreloj(){
		GestorSesion.getSesion().setNivel(2);
		GestorSesion.getSesion().setJuego(true);
		
		// set tipo contrarreloj
		GestorSesion.getSesion().setTipo("contrarreloj");
		GestorSesion.getSesion().iniciarTablero(2);
		setContMinas();
		GestorSesion.getSesion().setContBanderas(contMinas);
		// iniciarCrono()
		GestorSesion.getSesion().iniciarCrono();
	}
	


	public void descubrirCasilla(int pFila, int pCol){
		GestorSesion.getSesion().getTablero().descubrirCasilla(pFila, pCol);
	}
	
	/**
	 * 
	 */
	public void gameOver(){
		GestorSesion.getSesion().getTimer().cancel();
		GestorSesion.getSesion().getTablero().mostrarTablero();
		GestorSesion.getSesion().setJuego(false);
	}

	public int obtenerNumFilas() {
		
		return GestorSesion.getSesion().getTablero().obtenerNumFilas();
	}
	
	public int obtenerNumColumnas() {
		
		return GestorSesion.getSesion().getTablero().obtenerNumColumnas();
	}
	

	
	




	public void establecerNombreJugador(String text) {
		boolean esta = false;
		if(text==""){
			esta =  Ranking.getRanking().estaEnRanking("Desconocido");
		}else{
			esta =  Ranking.getRanking().estaEnRanking(text);
		}
		
		if(!esta){
			if(text.equals("")){
				j = new Jugador("Desconocido");
			} else {
				j = new Jugador(text);
			}
			j.establecerPuntuacion(0);
			Ranking.getRanking().anadirLista(j);
		} else{
			if(text.equals("")){
				j = Ranking.getRanking().obtJugador("Desconocido");
			} else {
				j = Ranking.getRanking().obtJugador(text);
			}
		}
	}

	private void establecerNivel(String selectedItem) {
		GestorSesion.getSesion().setNivel(Integer.parseInt(selectedItem));
	}
	
	private void establecerPuntuacion(int pPunt){
		puntuacion = pPunt;
	}
	
	public String obtenerNombreJugador(){
		return j.obtenerNombre();
	}
	
	public int obtenerBanderas(){
		return GestorSesion.getSesion().getContBanderas();
	}
	
	public int obtenerPuntuacion(){
		return puntuacion;
	}
	public void comprobarJuego(){
		if(GestorSesion.getSesion().getTablero().getContadorCasillasDescubrir() == contMinas){
			boolean fin = GestorSesion.getSesion().getTablero().comprobarJuego();
			GestorSesion.getSesion().setFinalizado(fin);
		}
		
	}

	public void calcularPuntos() {
		if(!GestorSesion.getSesion().getFinalizado()){
			puntuacion = 0;
		} else {
			puntuacion =(int) ((((6000-GestorSesion.getSesion().getTiempo())*Math.sqrt(GestorSesion.getSesion().getNivel()))/10)-(int)GestorSesion.getSesion().getTiempo());			
		}	
		asignarPuntos();
	}
	
	private void asignarPuntos(){
		if(j.obtenerPunt()<puntuacion){
			j.establecerPuntuacion(puntuacion);
		}
	}

	public void descubrirTodosLosVecinos(int a, int b) {
		GestorSesion.getSesion().getTablero().descubrirTodosLosVecinos(a,b);
	}
	

}