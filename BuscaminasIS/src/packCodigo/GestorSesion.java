package packCodigo;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer;

public class GestorSesion  extends Observable implements Observer{

	private static GestorSesion miSesion = new GestorSesion();
	private Tablero tablero;
	private float tiempo;
	private Timer timer=new Timer();//Aqui va el tiempo
	private String tipo = "partida";
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
	
	public Tablero getTablero() {
		return this.tablero;
	}
	
	public int getContBanderas() {
		return this.contBanderas;
	}
	
	public float getTiempo() {
		return this.tiempo;
	}
	
	public void setTiempo(float tiempo) {
		this.tiempo = tiempo;
	}
	
	public void setContBanderas(int pCont) {
		this.contBanderas = pCont;
	}
	
	public void setTipo(String pTipo) {
		this.tipo = pTipo;
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
