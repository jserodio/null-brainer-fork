package packGestores;
import java.sql.SQLException;


import java.util.ArrayList;

import packCodigo.Partida;
import packCodigo.Partida1;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;

public class GestorBuscaminas {
    
	private static final GestorBuscaminas miGestorBuscaminas = new GestorBuscaminas();
     
    private GestorBuscaminas() {}

    public static GestorBuscaminas getGestorBuscaminas() {
        return miGestorBuscaminas;
    }
    
    /**Iniciamos el juego**/
	public void inicioJuego(int pNivel){
		GestorSesion.getSesion().setNivel(pNivel);
		GestorSesion.getSesion().setJuego(true);
		GestorSesion.getSesion().iniciarTablero(pNivel);
		GestorSesion.getSesion().setContMinas();
		GestorSesion.getSesion().setContBanderas(GestorSesion.getSesion().getContMinas());
		GestorSesion.getSesion().crono();
	}
	
	/**Iniciamos partica contrarreloj**/
	public void iniciarPartidaContrarreloj(){
		GestorSesion.getSesion().setNivel(2);
		GestorSesion.getSesion().setJuego(true);
		
		// set tipo contrarreloj
		GestorSesion.getSesion().setTipo("contrarreloj");
		GestorSesion.getSesion().iniciarTablero(2);
		GestorSesion.getSesion().setContMinas();
		GestorSesion.getSesion().setContBanderas(GestorSesion.getSesion().getContMinas());
		// iniciarCrono()
		GestorSesion.getSesion().iniciarCrono();
	}
	
    public boolean identificarse(String user, String password) throws SQLException, ExcepcionConectarBD{
		return GestorUsuarios.getGestorUsuarios().identificarse(user, password);
	}
    
    public boolean registrarse(String user, String password, String confirmedPassword, String email) throws SQLException, ExcepcionConectarBD{
    	return GestorUsuarios.getGestorUsuarios().registrarse(user, password, confirmedPassword, email);
    }
    
    public ArrayList<Partida1> obtenerListaPartidasNivel(int pNivel) throws SQLException, ExcepcionConectarBD{
    	return GestorPartidas.getGestorPartidas().obtenerListaPartidasNivel(pNivel);
    }
    
    public ArrayList<Partida1> obtenerListaPartidasTablero(String pCodTablero) throws SQLException, ExcepcionConectarBD{
    	return GestorPartidas.getGestorPartidas().obtenerListaPartidasTablero(pCodTablero);
    }
    
    public ArrayList<Partida1> obtenerListaPartidasUsuario(String pCodUsuario) throws SQLException, ExcepcionConectarBD{
    	return GestorPartidas.getGestorPartidas().obtenerListaPartidasUsuario(pCodUsuario);
    }
    
    public ArrayList<Usuario> obtenerUsuarios() throws SQLException, ExcepcionConectarBD{
    	return GestorUsuarios.getGestorUsuarios().obtenerUsuarios();
    }

}
