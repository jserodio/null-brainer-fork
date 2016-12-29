package packCodigo;

public class Partida1 {
	private int puntuacion;
	private String tipo;
	private String codTablero;
	private String codUsuario;
	
	public Partida1(int pPunt,String pTipo,String pCt, String pCu){
		this.puntuacion=pPunt;
		this.tipo=pTipo;
		this.codTablero=pCt;
		this.codUsuario=pCu;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}

	public String getCodUsuario(){
		return this.codUsuario;
	}

}
