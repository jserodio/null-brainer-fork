package packGestores;

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
			GestorRetos.getGestorRetos().nuevoReto(punt,codTablero,usuRetado,usuRetador);
			IU_Buscaminas.getVentana().setVisible(true);
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
		// FALTA POR HACER
		System.out.println(pPartida.toString());
	}


	public ArrayList<String> obtenerTableros(String pNivel) throws ExcepcionConectarBD, SQLException {
		// TODO Auto-generated method stub
		return GestorTablero.getGestorTablero().obtenerTableros(pNivel);
	}
	
	public String buscarNombre(String pCodUsuario){
		return GestorUsuarios.getGestorUsuarios().buscarNombre(pCodUsuario);
	}

}
