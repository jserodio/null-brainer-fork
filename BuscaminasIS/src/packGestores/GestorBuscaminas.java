package packGestores;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;

import packCodigo.Partida;
import packCodigo.Partida1;
import packCodigo.Tablero;
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

	public void compartirTwitter() {
		Partida p = GestorSesion.getSesion().getPartida();
		int puntos = p.getPuntuacion();
		String codJugador = p.getJugador().getCodUsuario();
		int codTablero = p.getTablero().getCodTablero();
		int nivel = 0;
		int maxPuntuacionTableros = 0;
		int maxPuntuacionNiveles = 0;
		int maxPuntuacionUsuario = 0;
		try {
			ResultSet result = GestorBD.getConexionBD().consultaBD("SELECT NIVEL FROM TABLERO WHERE CODTABLERO=" + codTablero + ";");
			if(result != null){
				result.next();
				nivel = result.getInt("NIVEL");
			}
			//MaxPuntuacion de tableros
			ResultSet result1 = GestorBD.getConexionBD().consultaBD("“SELECT MAX(PUNTUACION) FROM Partida WHERE acabado = true;”) SELECT MAX(puntuacion) FROM PARTIDA WHERE CODTABLERO=" + codTablero + " AND ACABADO=TRUE;");
			if(result1 != null){
				result1.next();
				maxPuntuacionTableros = result.getInt("MAX(PUNTUACION)");
			}
			//MaxPuntuacion de niveles
			ResultSet result2 = GestorBD.getConexionBD().consultaBD("SELECT MAX(PUNTUACION) FROM PARTIDA NATURAL JOIN TABLERO WHERE Tablero.nivel="+nivel+";");
			if(result2 != null){
				result2.next();
				maxPuntuacionNiveles = result.getInt("MAX(PUNTUACION)");
			}
			//MaxPuntuacion de usuario
			ResultSet result3 = GestorBD.getConexionBD().consultaBD("SELECT MAX(PUNTUACION) FROM PARTIDA WHERE CODUSUARIO="+codJugador+";");
			if(result3 != null){
				result3.next();
				maxPuntuacionUsuario = result.getInt("MAX(PUNTUACION)");
			}
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String mensaje = "";
		if(puntos > maxPuntuacionTableros){
			mensaje += "¡Has superado la puntuación máxima de los tableros! \n\n";
		}
		if(puntos > maxPuntuacionNiveles){
			mensaje += "¡Has superado la puntuación máxima de los niveles! \n\n";
		}
		if(puntos > maxPuntuacionUsuario){
			mensaje += "¡Has superado tu puntuación máxima!";
		}
		try {
			GestorTwitter.getGestorTwitter().compartirTwitter(mensaje);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
