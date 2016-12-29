package packCodigo;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import packVentanas.IU_Jugar;

import java.util.Timer;

public class GestorSesion  extends Observable implements Observer{

	private static GestorSesion miSesion = new GestorSesion();
	private Tablero tablero;
	private float tiempo;
	private boolean juego;
	private int nivel;
	private Timer timer=new Timer();//Aqui va el tiempo
	private String tipo = "partida";
	private boolean finalizado = false;
	private int contBanderas=0;
	
	
	/****************
	 * CONSTRUCTORA	*
	 ****************/
	private GestorSesion(){
	}
	
	/************************
	 * Singleton.			*
	 * @return miBuscaminas	*
	 ************************/
	public static GestorSesion getSesion(){
		return miSesion;
	}
	
	public Timer getTimer() {
		return this.timer;
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
	
	public void setTiempo(float tiempo) {
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
			GestorSesion.getSesion().getTimer().cancel();
			setChanged();
			notifyObservers("FINALIZADO");
		}
	}
	
	public boolean getFinalizado() {
		return this.finalizado;
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
	 * minas. El tiempo se resetea.								*												*
	 ************************************************************/
	public void reset(IU_Jugar vBuscaminas){
		GestorSesion.getSesion().iniciarTablero(nivel);
		GestorSesion.getSesion().getTablero().addObserver(vBuscaminas);
		GestorBuscaminas.getBuscaminas().setContMinas();
		GestorSesion.getSesion().setContBanderas(GestorBuscaminas.getBuscaminas().getContMinas());
		GestorSesion.getSesion().setTiempo(GestorSesion.getSesion().getTiempo() - 1 );
		GestorSesion.getSesion().getTimer().cancel();
		GestorSesion.getSesion().crono();
		GestorSesion.getSesion().getTablero().addObserver(this);
		setJuego(true);
		setFinalizado(false);
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
				if(GestorSesion.getSesion().getContBanderas()>0){
					GestorSesion.getSesion().setContBanderas(GestorSesion.getSesion().getContBanderas() - 1);
				}
			}else if(p[1].equals("BANDERA") && p[0].equals("false")){
				if(GestorSesion.getSesion().getContBanderas()<GestorBuscaminas.getBuscaminas().getContMinas()){
					GestorSesion.getSesion().setContBanderas(GestorSesion.getSesion().getContBanderas() + 1);
				}
			}
		}
	}
}
