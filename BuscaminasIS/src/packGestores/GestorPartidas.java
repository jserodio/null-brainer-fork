package packGestores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packCodigo.Partida;
import packCodigo.Partida1;
import packExcepciones.ExcepcionConectarBD;

public class GestorPartidas {
	private static final GestorPartidas miGestorPartidas = new GestorPartidas();
	private ArrayList<Partida1> listaPartidas;

	private GestorPartidas() {
		listaPartidas = new ArrayList<Partida1>();
	}

	public static GestorPartidas getGestorPartidas() {
		return miGestorPartidas;
	}

	public ArrayList<Partida1> obtenerListaPartidasNivel(int pNivel) throws ExcepcionConectarBD, SQLException {
		if (listaPartidas.isEmpty() == false) {
			listaPartidas.clear();
		}
		  String niv=""+pNivel;
		  ResultSet rs = null;
		  String cadena = "SELECT * FROM partida WHERE nivel LIKE '"+niv+"%' AND acabado ='true' ORDER BY puntuacion DESC";
		  rs = GestorBD.getConexionBD().consultaBD(cadena);
		  
		  if(rs!=null){
			  while(rs.next()){
					  Partida1 p = new Partida1(rs.getInt("puntuacion"),rs.getString("tipo"),rs.getString("codUsuario"),rs.getString("codTablero"));
					  listaPartidas.add(p);
			  }
		  }
		  else{
			  System.out.println("No hay Jugadores");
		  }
		  GestorBD.getConexionBD().closeResult(rs);
		  return listaPartidas;
	}

	public ArrayList<Partida1> obtenerListaPartidasTablero(String pCodTablero)
			throws SQLException, ExcepcionConectarBD {
		if (listaPartidas.isEmpty() == false) {
			listaPartidas.clear();
		}
		ResultSet rs = null;
		String cadena = "SELECT * FROM partida WHERE codTablero ='" + pCodTablero
				+ "' AND acabado ='true' ORDER BY puntuacion DESC";
		rs = GestorBD.getConexionBD().consultaBD(cadena);

		if (rs != null) {
			while (rs.next()) {
				Partida1 p = new Partida1(rs.getInt("puntuacion"), rs.getString("tipo"), rs.getString("codUsuario"),
						rs.getString("codTablero"));
				listaPartidas.add(p);
			}
		} else {
			System.out.println("No hay Partidas jugadas en el tablero escogido");
		}
		GestorBD.getConexionBD().closeResult(rs);
		return listaPartidas;
	}

	public ArrayList<Partida1> obtenerListaPartidasUsuario(String pCodUsuario)
			throws SQLException, ExcepcionConectarBD {
		if (listaPartidas.isEmpty() == false) {
			listaPartidas.clear();
		}
		ResultSet rs = null;
		String cadena = "SELECT * FROM partida WHERE codUsuario ='" + pCodUsuario
				+ "' AND acabado ='true' ORDER BY puntuacion DESC";
		rs = GestorBD.getConexionBD().consultaBD(cadena);

		if (rs != null) {
			while (rs.next()) {
				Partida1 p = new Partida1(rs.getInt("puntuacion"), rs.getString("tipo"), rs.getString("codUsuario"),
						rs.getString("codTablero"));
				listaPartidas.add(p);
			}
		} else {
			System.out.println("El usuario no ha jugado ninguna partida");
		}
		GestorBD.getConexionBD().closeResult(rs);
		return listaPartidas;
	}

	// Si la partida existe devuelve true
	public boolean comprobarNombrePartida(String nombrePartida) throws ExcepcionConectarBD {
		ResultSet rs = null;
		boolean rdo = false;
		String cadena = "SELECT * FROM PARTIDAS WHERE nombrepartida ='" + nombrePartida + "' ";
		rs = GestorBD.getConexionBD().consultaBD(cadena);
		if (rs != null) {
			rdo = true;
		}
		GestorBD.getConexionBD().closeResult(rs);
		return rdo;

	}

	public void guardarPartida(Partida pPartida) {
		String sentencia = "INSERT INTO PARTIDAS(CODUSUARIO,CODTABLERO,PUNTUACION,TIPO,ACABADO,NOMBREPARTIDA) VALUES(" + pPartida.getJugador().getCodUsuario() + "," + pPartida.getJuego().getCodTablero() + ","
				+ pPartida.getPuntuacion() + "," + pPartida.getTipo()  + "," + pPartida.isAcabado() + ",'" + pPartida.getNombrePartida() +"')" ;
		try {
			GestorBD.getConexionBD().actualizarBD(sentencia);
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
