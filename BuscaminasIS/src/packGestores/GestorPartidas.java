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
		  String niv=""+pNivel;
		  ResultSet rs = null;
		  String cadena = "SELECT * FROM PARTIDA WHERE CODTABLERO LIKE '"+niv+"%' AND acabado =1 ORDER BY puntuacion DESC";
		  rs = GestorBD.getConexionBD().consultaBD(cadena);
		  
		  if(rs!=null){
			  while(rs.next()){
					  Partida p = new Partida(rs.getInt("puntuacion"),rs.getString("tipo"),""+rs.getInt("codUsuario"),rs.getString("codTablero"));
					  listaPartidas.add(p);
			  }
		  }
		  else{
			  System.out.println("No hay Jugadores");
		  }
		  GestorBD.getConexionBD().closeResult(rs);
		  return listaPartidas;
	}

	public ArrayList<Partida> obtenerListaPartidasTablero(String pCodTablero)
			throws SQLException, ExcepcionConectarBD {
		if (listaPartidas.isEmpty() == false) {
			listaPartidas.clear();
		}
		ResultSet rs = null;
		String cadena = "SELECT * FROM PARTIDA WHERE codTablero ='"+pCodTablero
				+ "' AND acabado =1 ORDER BY PUNTUACION DESC";
		rs = GestorBD.getConexionBD().consultaBD(cadena);

		if (rs != null) {
			while (rs.next()) {
				Partida p = new Partida(rs.getInt("puntuacion"), rs.getString("tipo"), ""+rs.getInt("codUsuario"),
						rs.getString("codTablero"));
				listaPartidas.add(p);
			}
		} else {
			System.out.println("No hay Partidas jugadas en el tablero escogido");
		}
		GestorBD.getConexionBD().closeResult(rs);
		return listaPartidas;
	}
	
	public ArrayList<Partida> obtenerListaPartidasUsuario(String pCodUsuario)
			throws SQLException, ExcepcionConectarBD {
		if (listaPartidas.isEmpty() == false) {
			listaPartidas.clear();
		}
		ResultSet rs = null;
		String cadena = "SELECT * FROM PARTIDA WHERE codUsuario ='" +pCodUsuario
				+ "' AND acabado ='1' ORDER BY PUNTUACION DESC";
		rs = GestorBD.getConexionBD().consultaBD(cadena);
		
//		if(!rs.next()){
//			System.out.println("No ha jugado el usuario ninguna partida");
//		}
//		else{
//			while (rs.next()) {
//				Partida p = new Partida(rs.getInt("puntuacion"), rs.getString("tipo"), ""+rs.getInt("codUsuario"),
//						""+rs.getString("codTablero"));
//				listaPartidas.add(p);
//			}
//		}
//		
		if (rs != null) {
			while (rs.next()) {
				Partida p = new Partida(rs.getInt("puntuacion"), rs.getString("tipo"), ""+rs.getInt("codUsuario"),
						""+rs.getString("codTablero"));
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
