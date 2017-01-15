package packGestores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packCodigo.Partida;
import packCodigo.Tablero;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;

public class GestorPartidas {
	private static final GestorPartidas miGestorPartidas = new GestorPartidas();
	private ArrayList<Partida> listaPartidas;

	private GestorPartidas() {
		listaPartidas = new ArrayList<Partida>();
	}

	public static GestorPartidas getGestorPartidas() {
		return miGestorPartidas;
	}

	public ArrayList<Partida> obtenerListaPartidasNivel(String pNivel) throws ExcepcionConectarBD, SQLException {
		if (listaPartidas.isEmpty() == false) {
			listaPartidas.clear();
		}
		ResultSet rs = null;
		String cadena = "SELECT PUNTUACION, TIPO, ACABADO, PARTIDA.CODTABLERO, USUARIO.CODUSUARIO, USUARIO.NOMBRE, USUARIO.CLAVE, USUARIO.EMAIL, PISTAS, NOMBREPARTIDA, TABLERO.CODTABLERO, TABLERO.NIVEL, TABLERO.CONTADORMINAS, TABLERO.CASILLAS, TABLERO.COLUMNAS, TABLERO.FILAS, TABLERO.CREADOR FROM PARTIDA, USUARIO, TABLERO WHERE PARTIDA.CODUSUARIO = USUARIO.CODUSUARIO AND PARTIDA.CODTABLERO=TABLERO.CODTABLERO AND TABLERO.NIVEL = '"
				+ pNivel + "'AND PARTIDA.ACABADO = 1 ORDER BY PUNTUACION DESC";
		rs = GestorBD.getConexionBD().consultaBD(cadena);

		if (rs != null) {
			while (rs.next()) {
//				Partida p = new Partida(rs.getInt("PUNTUACION"),rs.getString("TIPO"),""+rs.getInt("CODUSUARIO"),""+rs.getString("CODTABLERO"));
				Partida p = new Partida(rs.getInt("PUNTUACION"), 0, rs.getString("TIPO"), rs.getBoolean("ACABADO"),
						// OBtener el tablero
						new Tablero(rs.getString("CODTABLERO"),rs.getString("NIVEL"), rs.getInt("FILAS"),rs.getInt("COLUMNAS")),
						// Obtener el jugador
						new Usuario(rs.getString("CODUSUARIO"),rs.getString("NOMBRE"),rs.getString("CLAVE"),rs.getString("EMAIL"),rs.getInt("PISTAS")), rs.getString("NOMBREPARTIDA"));
				listaPartidas.add(p);
			}
		} else {
			System.out.println("No hay Jugadores");
		}
		GestorBD.getConexionBD().closeResult(rs);
		return listaPartidas;
	}

	public ArrayList<Partida> obtenerListaPartidasTablero(String pCodTablero) throws SQLException, ExcepcionConectarBD {
		if (listaPartidas.isEmpty() == false) {
			listaPartidas.clear();
		}
		ResultSet rs = null;
		String cadena = "SELECT PUNTUACION, TIPO, ACABADO, PARTIDA.CODTABLERO, USUARIO.CODUSUARIO, USUARIO.NOMBRE, USUARIO.CLAVE, USUARIO.EMAIL, PISTAS, NOMBREPARTIDA, TABLERO.CODTABLERO, TABLERO.NIVEL, TABLERO.CONTADORMINAS, TABLERO.CASILLAS, TABLERO.COLUMNAS, TABLERO.FILAS, TABLERO.CREADOR FROM PARTIDA, USUARIO, TABLERO WHERE PARTIDA.CODUSUARIO = USUARIO.CODUSUARIO AND PARTIDA.CODTABLERO=TABLERO.CODTABLERO AND TABLERO.CODTABLERO = '"
				+ pCodTablero + "' AND PARTIDA.ACABADO = 1 ORDER BY PUNTUACION DESC";
		rs = GestorBD.getConexionBD().consultaBD(cadena);

		if (rs != null) {
			while (rs.next()) {
//				Partida p = new Partida(rs.getInt("PUNTUACION"),rs.getString("TIPO"),""+rs.getInt("CODUSUARIO"),""+rs.getString("CODTABLERO"));
				Partida p = new Partida(rs.getInt("PUNTUACION"), 0, rs.getString("TIPO"), rs.getBoolean("ACABADO"),
						// OBtener el tablero
						new Tablero(rs.getString("NIVEL"), rs.getInt("FILAS"), rs.getInt("COLUMNAS")),
						// Obtener el jugador
						new Usuario(rs.getString("CODUSUARIO"), rs.getString("NOMBRE"), rs.getString("CLAVE"),
								rs.getString("EMAIL"), rs.getInt("PISTAS")),
						rs.getString("NOMBREPARTIDA"));
				listaPartidas.add(p);
			}
		} else {
			System.out.println("No hay Partidas jugadas en el tablero escogido");
		}
		GestorBD.getConexionBD().closeResult(rs);
		return listaPartidas;
	}

	public ArrayList<Partida> obtenerListaPartidasUsuario(String pCodUsuario) throws SQLException, ExcepcionConectarBD {
		if (!listaPartidas.isEmpty()) {
			listaPartidas.clear();
		}
		ResultSet rs = null;
		String cadena = "SELECT PUNTUACION, TIPO, ACABADO, PARTIDA.CODTABLERO, USUARIO.CODUSUARIO, USUARIO.NOMBRE, USUARIO.CLAVE, USUARIO.EMAIL, PISTAS, NOMBREPARTIDA, TABLERO.CODTABLERO, TABLERO.NIVEL, TABLERO.CONTADORMINAS, TABLERO.CASILLAS, TABLERO.COLUMNAS, TABLERO.FILAS, TABLERO.CREADOR FROM PARTIDA, USUARIO, TABLERO WHERE PARTIDA.CODUSUARIO = USUARIO.CODUSUARIO AND PARTIDA.CODTABLERO=TABLERO.CODTABLERO AND USUARIO.CODUSUARIO = "
				+ pCodUsuario + " AND PARTIDA.ACABADO = 1 ORDER BY PUNTUACION DESC";
		rs = GestorBD.getConexionBD().consultaBD(cadena);
		System.out.println("ESTOY AQUI");
		if (rs != null) {
			while (rs.next()) {
//				Partida p = new Partida(rs.getInt("PUNTUACION"),rs.getString("TIPO"),""+rs.getInt("CODUSUARIO"),""+rs.getString("CODTABLERO"));
				Tablero t = new Tablero(rs.getString("CODTABLERO"), rs.getString("NIVEL"), rs.getInt("FILAS"), rs.getInt("COLUMNAS"));
				System.out.println(t.getCodTablero());
				Partida p = new Partida(rs.getInt("PUNTUACION"), 0, rs.getString("TIPO"), rs.getBoolean("ACABADO"), t,
						// Obtener el jugador
						new Usuario(rs.getString("CODUSUARIO"), rs.getString("NOMBRE"), rs.getString("CLAVE"),
								rs.getString("EMAIL"), rs.getInt("PISTAS")),
						rs.getString("NOMBREPARTIDA"));
				listaPartidas.add(p);
			}
		} else {
			System.out.println("El usuario no ha jugado ninguna partida");
		}
		GestorBD.getConexionBD().closeResult(rs);
		return listaPartidas;
	}

	// Si la partida existe devuelve true
	public boolean comprobarNombrePartida(Partida partida) throws ExcepcionConectarBD {
		ResultSet rs = null;
		boolean rdo = false;
		String cadena = "SELECT * FROM PARTIDA WHERE nombrepartida ='" + partida.getNombrePartida()
				+ "' AND CODUSUARIO = " + partida.getJugador().getCodUsuario();
		rs = GestorBD.getConexionBD().consultaBD(cadena);
		if (rs != null) {
			try {
				// Si existe datos es que la partida existe
				if (rs.next()) {
					rdo = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GestorBD.getConexionBD().closeResult(rs);
		return rdo;
	}

}

