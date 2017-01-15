package packCodigo;

public class Reto {
	private String codReto;
	private String codUsuarioRetador;
	private String codTablero;
	private int puntuacion;	

	public Reto(String codR, String codUR, String codT, int puntR) {
		// TODO Auto-generated constructor stub
		this.codReto = codR;
		this.codTablero = codT;
		this.codUsuarioRetador = codUR;
		this.puntuacion = puntR;
	}

	
	public int getPuntuacion(){
		return this.puntuacion;
	}
	
	public String getCodReto(){
		return this.codReto;
	}
	
	public String getCodTablero(){
		return this.codTablero;
	}
	
	public String getUsuarioRetador(){
		return this.codUsuarioRetador;
	}

}
