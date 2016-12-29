package packGestores;
import packCodigo.Usuario;

public class GestorSesion {
	
	private static final GestorSesion miGestorSesion = new GestorSesion();
	private Usuario usuario;
    
    private GestorSesion() {}

    public static GestorSesion getGestorSesion() {
        return miGestorSesion;
    }
    
    public void setUsuario(Usuario pUser){
    	this.usuario=pUser;
    }
    
    public Usuario getUsuario(){
    	return this.usuario;
    }

}
