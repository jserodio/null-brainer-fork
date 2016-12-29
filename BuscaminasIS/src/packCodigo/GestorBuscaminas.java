package packCodigo;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import packVentanas.IU_Jugar;

public class GestorBuscaminas extends Observable implements Observer{

	private static GestorBuscaminas miBuscaminas = new GestorBuscaminas();
	private int nivel;
	private int contMinas;
	private boolean juego;
	private int puntuacion;
	private boolean finalizado = false;
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
	private void setContMinas(){
		contMinas = GestorSesion.getSesion().getTablero().minas().size();
	}

	/**Iniciamos el juego**/
	public void inicioJuego(int pNivel){
		setNivel(pNivel);
		setJuego(true);
		GestorSesion.getSesion().iniciarTablero(pNivel);
		setContMinas();
		GestorSesion.getSesion().setContBanderas(contMinas);
		GestorSesion.getSesion().crono();
	}
	
	/**Iniciamos partica contrarreloj**/
	public void iniciarPartidaContrarreloj(){
		setNivel(2);
		setJuego(true);
		
		// set tipo contrarreloj
		GestorSesion.getSesion().setTipo("contrarreloj");
		GestorSesion.getSesion().iniciarTablero(2);
		setContMinas();
		GestorSesion.getSesion().setContBanderas(contMinas);
		// iniciarCrono()
		GestorSesion.getSesion().iniciarCrono();
	}
	
	

	
	/************************************************************
	 * Resetea el Buscaminas haciendo una nueva instancia de	*
	 * tablero, casilla, casillasVacias, lCasillasVisitadas 	*
	 * y lCasillasVacias volviendo a calcular el numero de 		*
	 * minas. El tiempo se resetea.								*												*
	 ************************************************************/
	public void reset(IU_Jugar vBuscaminas){
		GestorSesion.getSesion().iniciarTablero(nivel);
		GestorSesion.getSesion().getTablero().addObserver(vBuscaminas);
		setContMinas();
		GestorSesion.getSesion().setContBanderas(contMinas);
		GestorSesion.getSesion().setTiempo(GestorSesion.getSesion().getTiempo() - 1 );
		GestorSesion.getSesion().getTimer().cancel();
		GestorSesion.getSesion().crono();
		GestorSesion.getSesion().getTablero().addObserver(this);
		setJuego(true);
		setFinalizado(false);
	}
	
	/**SetJuego**/
	private void setJuego(boolean pJuego){
		this.juego = pJuego;
		setChanged();
		notifyObservers(juego);
	}
	
	/********************
	 * @param pNivel	*
	 ********************/
	private void setNivel(int pNivel){
		nivel = pNivel;
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
		setJuego(false);
	}

	public int obtenerNumFilas() {
		
		return GestorSesion.getSesion().getTablero().obtenerNumFilas();
	}
	
	public int obtenerNumColumnas() {
		
		return GestorSesion.getSesion().getTablero().obtenerNumColumnas();
	}

	public boolean getJuego(){
		return juego;
	}
	
	public void ponerBandera(int fila, int col) {
		int contBanderas = GestorSesion.getSesion().getContBanderas();
		int aux = contBanderas;
		if(0<=contBanderas){
			GestorSesion.getSesion().getTablero().ponerBandera(fila,col);
			if(contBanderas < aux){
				setChanged();
				notifyObservers(fila+","+col+","+"PonerBandera");
			} else if (contBanderas > aux){
				setChanged();
				notifyObservers(fila+","+col+","+"QuitarBandera");
			}
		}
	}
	
	@Override

	public void update(Observable pObservable, Object pObjeto) {
		if(pObservable instanceof Tablero){
			String[]p = pObjeto.toString().split(",");
			if(p[1].equals("BANDERA") && p[0].equals("true")){
				if(GestorSesion.getSesion().getContBanderas()>0){
					GestorSesion.getSesion().setContBanderas(GestorSesion.getSesion().getContBanderas() - 1);
				}
			}else if(p[1].equals("BANDERA") && p[0].equals("false")){
				if(GestorSesion.getSesion().getContBanderas()<contMinas){
					GestorSesion.getSesion().setContBanderas(GestorSesion.getSesion().getContBanderas() + 1);
				}
			}
		}
	}


	public void anadirObservador(IU_Jugar iU_Jugar) {
		this.addObserver(iU_Jugar);
		GestorSesion.getSesion().getTablero().addObserver(iU_Jugar);
		GestorSesion.getSesion().getTablero().addObserver(this);
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
		nivel = Integer.parseInt(selectedItem);
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
			setFinalizado(fin);
		}
		
	}

	private void setFinalizado(boolean fin) {
		this.finalizado = fin;
		if(finalizado){
			GestorSesion.getSesion().getTimer().cancel();
			setChanged();
			notifyObservers("FINALIZADO");
		}
	}

	public void calcularPuntos() {
		if(!finalizado){
			puntuacion = 0;
		} else {
			puntuacion =(int) ((((6000-GestorSesion.getSesion().getTiempo())*Math.sqrt(nivel))/10)-(int)GestorSesion.getSesion().getTiempo());			
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