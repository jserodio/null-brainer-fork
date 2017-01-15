package packGestores;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import packCodigo.Jugador;
import packCodigo.Partida;
import packCodigo.Ranking;
import packCodigo.Tablero;
import packCodigo.TableroBuilderN1;
import packCodigo.TableroBuilderN2;
import packCodigo.TableroBuilderN3;
import packCodigo.Usuario;
import packVentanas.IU_Jugar;

public class GestorSesion extends Observable implements Observer {
	
	private static final GestorSesion miGestorSesion = new GestorSesion();
	private Usuario usuario;
	private Tablero tablero;
	private int tiempo;
	private boolean juego;
	private int nivel;
	private Timer timer=new Timer();//Aqui va el tiempo
	private String tipo = "partida";
	private boolean finalizado = false;
	private int contBanderas;
	private int contMinas;
	private Jugador j;
	private int puntuacion;
    
    private GestorSesion() {}

    public static GestorSesion getSesion() {
        return miGestorSesion;
    }
    
    public void setUsuario(Usuario pUser){
    	this.usuario=pUser;
    }
    
    public Usuario getUsuario(){
    	return this.usuario;
    }
    
	
	public Timer getTimer() {
		return this.timer;
	}
	
	public void setContMinas(){
		contMinas = tablero.minas().size();
	}
	
	public int getContMinas() {
		return contMinas;
	}
	
	public int getNivel() {
		return this.nivel;
	}
	
	public Tablero getTablero() {
		return this.tablero;
	}
	
	public int getContBanderas() {
		return this.contBanderas;
	}
	
	public float getTiempo() {
		return this.tiempo;
	}
	
	/********************
	 * @param pNivel	*
	 ********************/
	public void setNivel(int pNivel){
		nivel = pNivel;
	}
	
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	
	/**SetJuego**/
	public void setJuego(boolean pJuego){
		this.juego = pJuego;
		setChanged();
		notifyObservers(juego);
	}
	
	public boolean getJuego(){
		return juego;
	}
	
	public void setContBanderas(int pCont) {
		this.contBanderas = pCont;
	}
	
	public void setTipo(String pTipo) {
		this.tipo = pTipo;
	}
	
	public void setFinalizado(boolean fin) {
		this.finalizado = fin;
		if(finalizado){
			timer.cancel();
			setChanged();
			notifyObservers("FINALIZADO");
		}
	}
	
	public boolean getFinalizado() {
		return this.finalizado;
	}
	
	public int obtenerPuntuacion(){
		return puntuacion;
	}
	
	private void establecerPuntuacion(int pPunt){
		puntuacion = pPunt;
	}
	
	public String obtenerNombreJugador(){
		return j.obtenerNombre();
	}
	
	private void asignarPuntos(){
		if(j.obtenerPunt()<puntuacion){
			j.establecerPuntuacion(puntuacion);
		}
	}
	
	public void calcularPuntos() {
		if(!finalizado){
			puntuacion = 0;
		} else {
			puntuacion =(int) ((((6000-tiempo)*Math.sqrt(nivel))/10)-(int)tiempo);			
		}	
		asignarPuntos();
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
	
	/**Iniciar el tablero**/
	public void iniciarTablero(int pNivel){
		if(pNivel == 1){
			tablero = TableroBuilderN1.getTableroBuilderN1().asignarTablero();
		} else if (pNivel == 2){
			tablero = TableroBuilderN2.getTableroBuilderN2().asignarTablero();
			
		} else if (pNivel == 3){
			tablero = TableroBuilderN3.getTableroBuilderN3().asignarTablero();
		}
		// Guardamos en BD
		GestorTablero.getGestorTablero().guardarTablero(tablero);
	}
	
	public void comprobarJuego(){
		if(tablero.getContadorCasillasDescubrir() == contMinas){
			boolean fin = tablero.comprobarJuego();
			finalizado = fin;
		}
	}
	
	/* Crono de contrarreloj */
	public void iniciarCrono(){

		tiempo = 999;
		
		  TimerTask  timerTask = new TimerTask() {
		   @Override
		   public void run() {
		    String texto;
		    tiempo--;
		    texto = ""+(int)tiempo;
		    if(tiempo<10){
		    	setChanged();
		 	    notifyObservers("00"+texto+","+contBanderas);
		    }else if(tiempo<100){
		    	 setChanged();
		 	    notifyObservers("0"+texto+","+contBanderas);
		    }else{
		    	setChanged();
		    	notifyObservers(texto+","+contBanderas);
		    	}
		   }
		  };
		  timer = new Timer();
		  timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
	
	public void crono(){

		  TimerTask  timerTask = new TimerTask() {
		   @Override
		   public void run() {
		    String texto;
		    tiempo++;
		    texto = ""+(int)tiempo;
		    if(tiempo<10){
		    	setChanged();
		 	    notifyObservers("00"+texto+","+contBanderas);
		    }else if(tiempo<100){
		    	 setChanged();
		 	    notifyObservers("0"+texto+","+contBanderas);
		    }else{
		    	setChanged();
		    	notifyObservers(texto+","+contBanderas);
		    	}
		   }
		  };
		  timer = new Timer();
		  timer.scheduleAtFixedRate(timerTask, 0, 1000);
   }
	
	/************************************************************
	 * Resetea el Buscaminas haciendo una nueva instancia de	*
	 * tablero, casilla, casillasVacias, lCasillasVisitadas 	*
	 * y lCasillasVacias volviendo a calcular el numero de 		*
	 * minas. El tiempo se resetea.								*
	 ************************************************************/
	public void reset(IU_Jugar vBuscaminas){
		iniciarTablero(nivel);
		tablero.addObserver(vBuscaminas);
		setContMinas();
		contBanderas = contMinas;
		tiempo--;
		timer.cancel();
		crono();
		tablero.addObserver(this);
		juego = true;
		finalizado = false;
	}
	
	public void ponerBandera(int fila, int col) {
		int aux = contBanderas;
		if(0<=contBanderas) {
			tablero.ponerBandera(fila,col);
			if(contBanderas < aux){
				setChanged();
				notifyObservers(fila+","+col+","+"PonerBandera");
			} else if (contBanderas > aux){
				setChanged();
				notifyObservers(fila+","+col+","+"QuitarBandera");
			}
		}
	}
	
	public void descubrirCasilla(int pFila, int pCol){
		tablero.descubrirCasilla(pFila, pCol);
	}
	
	/**
	 * 
	 */
	public void gameOver(){
		timer.cancel();
		tablero.mostrarTablero();
		this.setJuego(false);
	}

	public int obtenerNumFilas() {
		return tablero.obtenerNumFilas();
	}
	
	public int obtenerNumColumnas() {
		return tablero.obtenerNumColumnas();
	}

	private void establecerNivel(String selectedItem) {
		nivel = Integer.parseInt(selectedItem);
	}
		
	public int obtenerBanderas(){
		return contBanderas;
	}
	
	public void descubrirTodosLosVecinos(int a, int b) {
		tablero.descubrirTodosLosVecinos(a,b);
	}
	
	public void anadirObservador(IU_Jugar iU_Jugar) {
		this.addObserver(iU_Jugar);
		tablero.addObserver(iU_Jugar);
		tablero.addObserver(this);
	}

	@Override
	public void update(Observable pObservable, Object pObjeto) {
		if(pObservable instanceof Tablero){
			String[]p = pObjeto.toString().split(",");
			if(p[1].equals("BANDERA") && p[0].equals("true")){
				if(contBanderas>0){
					contBanderas--;
				}
			}else if(p[1].equals("BANDERA") && p[0].equals("false")){
				if(contBanderas<contMinas){
					contBanderas++;
				}
			}
		}
	}

	public Partida obtenerPartidaActual() {
		return new Partida(puntuacion, tiempo, tipo, finalizado, tablero, usuario, "");
	}

}
