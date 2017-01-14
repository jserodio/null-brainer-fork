package packCodigo;

import java.util.Observable;

public class Usuario extends Observable {
	private String codUsuario;
	private String nombre;
	private String clave;
	private String email;
	private int numPistas;
	
	public Usuario(String pCu,String pNom,String pClav,String pEmail,int pNp){
		this.codUsuario=pCu;
		this.nombre=pNom;
		this.clave=pClav;
		this.email=pEmail;
		this.numPistas=pNp;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getCodUsuario(){
		return this.codUsuario;
	}

	public int getNumPistas(){
		return this.numPistas;
	}

	public void restarPista() {
		numPistas--;
	}

	public void anadirPistas(int i) {
		numPistas = numPistas + i;
	}
	
}
