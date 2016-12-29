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
