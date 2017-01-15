package packGestores;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import packCodigo.Partida;
import packCodigo.Reto;
import packCodigo.Tablero;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;
import packVentanas.IU_Buscaminas;
import packVentanas.IU_Jugar;
import packVentanas.VRetar;

public class GestorBuscaminas {

	private static final GestorBuscaminas miGestorBuscaminas = new GestorBuscaminas();

	private GestorBuscaminas() {
	}

	public static GestorBuscaminas getGestorBuscaminas() {
		return miGestorBuscaminas;
	}

	/** Iniciamos el juego **/
	public void inicioJuego(int pNivel) {
		GestorSesion.getSesion().setNivel(pNivel);
		GestorSesion.getSesion().setJuego(true);
		GestorSesion.getSesion().setTipo("partida");
		GestorSesion.getSesion().iniciarTablero(pNivel);
		GestorSesion.getSesion().setContMinas();
		GestorSesion.getSesion().setContBanderas(GestorSesion.getSesion().getContMinas());
		GestorSesion.getSesion().crono();
	}

	/** Iniciamos partica contrarreloj **/
	public void iniciarPartidaContrarreloj() {
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

	
	public void retar(String nombreUsuario) throws ExcepcionConectarBD, SQLException{
		String usuRetado = GestorUsuarios.getGestorUsuarios().getUsuario(nombreUsuario);			
		if (usuRetado==null){
			VRetar vR = new VRetar(true);
		}else{
			Tablero tab = GestorSesion.getSesion().getTablero();
			int punt = GestorSesion.getSesion().obtenerPuntuacion();
			String usuRetador = GestorSesion.getSesion().getUsuario().getCodUsuario();
			float tiempo = GestorSesion.getSesion().getTiempo();
			String codTablero = tab.getCodTablero();
			GestorRetos gestorR = GestorRetos.getGestorRetos();
			GestorTablero.getGestorTablero().guardarTablero();
			gestorR.nuevoReto(punt,codTablero,usuRetado,usuRetador);
//			IU_Buscaminas.getVentana().setVisible(true);
			IU_Buscaminas menu = new IU_Buscaminas();
			menu.setVisible(true);
		}
	}
	
	@SuppressWarnings("null")
	public ArrayList<Reto> getListaRetos() throws ExcepcionConectarBD, SQLException{
		return GestorRetos.getGestorRetos().getListaRetos();
		
	}
	
	public void iniciarPartidaReto(String codReto) throws ExcepcionConectarBD, SQLException{
		Reto pReto = GestorRetos.getGestorRetos().getReto(codReto);
		String codTablero = pReto.getCodTablero();
		Tablero pTab = GestorTablero.getGestorTablero().getTablero(codTablero);
		GestorSesion.getSesion().setNivel(pTab.getValorNivel());
		GestorSesion.getSesion().setJuego(true);		
		// set tipo Reto
		GestorSesion.getSesion().setTipo("Reto");
		GestorSesion.getSesion().setTablero(pTab);
		GestorSesion.getSesion().setContMinas();
		GestorSesion.getSesion().setContBanderas(GestorSesion.getSesion().getContMinas());
		IU_Jugar iu_jugar = new IU_Jugar(pTab.getValorNivel());
		iu_jugar.setVisible(true);
//		pReto.setPuntuacionRetado(this.obtenerPuntuacion());
//		gestorR.guardarRetoTerminado(codReto,this.obtenerPuntuacion());
	}
	
	public void removeReto(String codReto) throws ExcepcionConectarBD{
		GestorRetos gestorR = GestorRetos.getGestorRetos();
		gestorR.eliminarReto(codReto);
	}
	private void getIU_RepitaRetar() {
		// TODO Auto-generated method stub
		
	}

	public boolean identificarse(String user, String password) throws SQLException, ExcepcionConectarBD {
		return GestorUsuarios.getGestorUsuarios().identificarse(user, password);
	}

    public boolean registrarse(String user, String password, String confirmedPassword, String email) throws SQLException, ExcepcionConectarBD{
    	return GestorUsuarios.getGestorUsuarios().registrarse(user, password, confirmedPassword, email);
    }
    
    public ArrayList<Partida> obtenerListaPartidasNivel(String pNivel) throws SQLException, ExcepcionConectarBD{
    	return GestorPartidas.getGestorPartidas().obtenerListaPartidasNivel(pNivel);
    }
    
    public ArrayList<Partida> obtenerListaPartidasTablero(String pCodTablero) throws SQLException, ExcepcionConectarBD{
    	return GestorPartidas.getGestorPartidas().obtenerListaPartidasTablero(pCodTablero);
    }
    
    public ArrayList<Partida> obtenerListaPartidasUsuario(String pCodUsuario) throws SQLException, ExcepcionConectarBD{
    	return GestorPartidas.getGestorPartidas().obtenerListaPartidasUsuario(pCodUsuario);
    }
    
    public ArrayList<Usuario> obtenerUsuarios() throws SQLException, ExcepcionConectarBD{
    	return GestorUsuarios.getGestorUsuarios().obtenerUsuarios();
    }

	public void guardarPartida(Partida pPartida) {
		String sentencia = "";
		try {
			if (GestorPartidas.getGestorPartidas().comprobarNombrePartida(pPartida)) {
				// Existe la partida, sobreescribirla
				sentencia = "UPDATE PARTIDA SET CODTABLERO = '" + pPartida.getJuego().getCodTablero()
						+ "', PUNTUACION = " + pPartida.getPuntuacion() + ", TIPO = '" + pPartida.getTipo()
						+ "', ACABADO = " + pPartida.isAcabado() + " WHERE NOMBREPARTIDA = '"
						+ pPartida.getNombrePartida() + "' AND CODUSUARIO = " + pPartida.getJugador().getCodUsuario();
			} else {
				// No existe la partida, añadir nueva
				sentencia = "INSERT INTO PARTIDA (CODUSUARIO,CODTABLERO,PUNTUACION,TIPO,ACABADO,NOMBREPARTIDA) VALUES("
						+ pPartida.getJugador().getCodUsuario() + ",'" + pPartida.getJuego().getCodTablero() + "',"
						+ pPartida.getPuntuacion() + ",'" + pPartida.getTipo() + "'," + pPartida.isAcabado() + ",'"
						+ pPartida.getNombrePartida() + "')";
			}
			GestorBD.getConexionBD().actualizarBD(sentencia);
		} catch (ExcepcionConectarBD e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void reanudarPartida(Partida pPartida) {
		GestorSesion.getSesion().setNivel(pPartida.getTablero().getValorNivel());
		GestorSesion.getSesion().setJuego(true);
		GestorSesion.getSesion().setTipo(pPartida.getTipo());
		GestorSesion.getSesion().iniciarTableroGuardado(pPartida.getTablero().getValorNivel());
		GestorSesion.getSesion().setContMinas();
		GestorSesion.getSesion().setContBanderas(GestorSesion.getSesion().getContMinas());
		GestorSesion.getSesion().crono();

	}


	public ArrayList<String> obtenerTableros(String pNivel) throws ExcepcionConectarBD, SQLException {
		// TODO Auto-generated method stub
		return GestorTablero.getGestorTablero().obtenerTableros(pNivel);
	}
	
	public String buscarNombre(String pCodUsuario){
		return GestorUsuarios.getGestorUsuarios().buscarNombre(pCodUsuario);
	}

	public void compartirTwitter() {
		Partida p = GestorSesion.getSesion().obtenerPartidaActual();
		int puntos = p.getPuntuacion();
		String codJugador = p.getJugador().getCodUsuario();
		String codTablero = p.getJuego().getCodTablero();
		int nivel = 0;
		int maxPuntuacionTablero = 0;
		int maxPuntuacionNivel = 0;
		int maxPuntuacionUsuario = 0;
		try {
			ResultSet result = GestorBD.getConexionBD().consultaBD("SELECT NIVEL FROM TABLERO WHERE CODTABLERO=" + codTablero + ";");
			if(result != null){
				result.next();
				nivel = result.getInt("NIVEL");
			}
			//MaxPuntuacion de un tablero
			ResultSet result1 = GestorBD.getConexionBD().consultaBD("SELECT MAX(PUNTUACION) FROM PARTIDA WHERE CODTABLERO=" + codTablero + ";");
			if(result1 != null){
				result1.next();
				maxPuntuacionTablero = result.getInt("MAX(PUNTUACION)");
			}
			//MaxPuntuacion de un nivel
			ResultSet result2 = GestorBD.getConexionBD().consultaBD("SELECT MAX(PUNTUACION) FROM PARTIDA NATURAL JOIN TABLERO WHERE Tablero.nivel="+nivel+";");
			if(result2 != null){
				result2.next();
				maxPuntuacionNivel = result.getInt("MAX(PUNTUACION)");
			}
			//MaxPuntuacion de usuario
			ResultSet result3 = GestorBD.getConexionBD().consultaBD("SELECT MAX(PUNTUACION) FROM PARTIDA WHERE CODUSUARIO="+ codJugador +";");
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
		boolean puntSuperada = false;
		if(puntos > maxPuntuacionTablero){
			puntSuperada = true;
			mensaje += "¡Has superado la puntuación máxima de los tableros! \n\n";
		}
		if(puntos > maxPuntuacionNivel){
			puntSuperada = true;
			mensaje += "¡Has superado la puntuación máxima de los niveles! \n\n";
		}
		if(puntos > maxPuntuacionUsuario){
			puntSuperada = true;
			mensaje += "¡Has superado tu puntuación máxima!";
		}
		if(!puntSuperada){
			mensaje = "No has superado ninguna puntuación.";
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

	public int obtenerNumPistas(IU_Jugar iU_Jugar) {
		Usuario u = GestorSesion.getSesion().getUsuario();
//		u.addObserver(iU_Jugar);
		int pistas = u.getNumPistas();
		return pistas;
	}

	public int[] utilizarPista() {
		Usuario u = GestorSesion.getSesion().getUsuario();
		int numPistas = u.getNumPistas();
		int[] casillasYMinaAMarcar = new int[6];
		int[] casillasMarcadas = new int[4];
		int[] minaAMarcar = new int[2];
		if(numPistas > 0){
			u.restarPista();
			for(int i=0;i<4;i++){
				casillasMarcadas = GestorSesion.getSesion().marcarCasillas();
				//[0] y [1] fila y columna para la primera casilla a marcar
				//[2] y [3] fila y columna para la segunda casilla a marcar
				casillasYMinaAMarcar[i] = casillasMarcadas[i];
			}
			minaAMarcar = GestorSesion.getSesion().escogerMina();
			//[4] y [5] fila y columna para la mina a marcar
			casillasYMinaAMarcar[4] = minaAMarcar[0];
			casillasYMinaAMarcar[5] = minaAMarcar[1];
		}
		return casillasYMinaAMarcar;
	}

	public void anadirPistas() {
		Partida p = GestorSesion.getSesion().obtenerPartidaActual();
		int puntos = p.getPuntuacion();
		Usuario u = p.getJugador();
		String codJugador = u.getCodUsuario();
		Tablero t = p.getJuego();
		String codTablero = t.getCodTablero();
		int maxPuntuacionTablero = 0;
		if(t.getValorNivel() == 1){
			u.anadirPistas(1);
		}else if(t.getValorNivel() == 2){
			u.anadirPistas(2);
		}else if(t.getValorNivel() == 3){
			u.anadirPistas(5);
		}
		//MaxPuntuacion de tablero
		ResultSet result;
		try {
			result = GestorBD.getConexionBD().consultaBD("SELECT MAX(PUNTUACION) FROM PARTIDA WHERE CODTABLERO=" + codTablero + ";");
			if(result != null){
				result.next();
				maxPuntuacionTablero = result.getInt("MAX(PUNTUACION)");
			}
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(puntos > maxPuntuacionTablero){
			u.anadirPistas(1);
		}
		try {
			GestorBD.getConexionBD().actualizarBD("UPDATE USUARIO SET PISTAS=" +u.getNumPistas()+ " WHERE CODUSUARIO=" + codJugador + ";");
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
