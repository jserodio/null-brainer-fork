package packGestores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;

import packCodigo.Casilla;
import packCodigo.Tablero;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;

public class GestorTablero {
	private static final GestorTablero miGestorTablero = new GestorTablero();

	private GestorTablero() {
	}

	public static GestorTablero getGestorTablero() {
		return miGestorTablero;
	}

	public ArrayList<String> obtenerTableros(String pNivel) throws ExcepcionConectarBD, SQLException {
		ArrayList<String> tablero = new ArrayList<String>();
		ResultSet rs = null;
		String cadena = "SELECT codTablero FROM TABLERO WHERE nivel ='" + pNivel + "' ORDER BY codTablero ASC";
		rs = GestorBD.getConexionBD().consultaBD(cadena);

		if (rs != null) {
			while (rs.next()) {
				String p = rs.getString("codTablero");
				tablero.add(p.substring(1, p.length() - 1));
			}
		} else {
			System.out.println("No hay Tableros");
		}
		GestorBD.getConexionBD().closeResult(rs);
		return tablero;
	}

	public Tablero getTablero(String pTablero) throws ExcepcionConectarBD, SQLException {
		ResultSet rs = null;
		int filas = 0, columnas = 0;
		String nivel = null;
		String cadena = "SELECT nivel, filas, columnas FROM TABLERO WHERE codTablero ='" + pTablero + "' ";
		rs = GestorBD.getConexionBD().consultaBD(cadena);

		if (rs != null) {
			while (rs.next()) {
				nivel = rs.getObject("nivel").toString();
				filas = Integer.parseInt((String) rs.getObject("filas").toString());
				columnas = Integer.parseInt((String) rs.getObject("columnas").toString());
			}
		} else {
			System.out.println("No hay Tableros");
		}
		Tablero tablero = new Tablero(nivel, filas, columnas);
		GestorBD.getConexionBD().closeResult(rs);
		return tablero;
	}

	public void guardarTablero() throws ExcepcionConectarBD {
		Tablero tab = GestorSesion.getSesion().getTablero();
		String sentencia = "INSERT INTO tablero (CODTABLERO,NIVEL,FILAS,COLUMNAS) VALUES (" + tab.getCodTablero() + ","
				+ tab.getNivel() + "," + tab.getFilas() + "," + tab.getColumnas() + ")";
		GestorBD.getConexionBD().actualizarBD(sentencia);
	}

	public String obtenerCodTablero(String nivel) {
		ResultSet rs = null;
		String rdo = "";
		String cadena = "SELECT COUNT(*) NUM FROM TABLERO WHERE nivel ='" + nivel + "' ";

		try {
			rs = GestorBD.getConexionBD().consultaBD(cadena);
			if (rs != null) {
				while (rs.next()) {
					Integer p = rs.getInt("NUM") + 1;
					rdo = nivel + p.toString();
				}
			} else {
				rdo = nivel + 1;
			}

		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GestorBD.getConexionBD().closeResult(rs);
		return rdo;
	}

	public void guardarTablero(Tablero pTablero) {
		String sentencia = "";
		Usuario u = GestorSesion.getSesion().getUsuario();
		sentencia = "INSERT INTO TABLERO VALUES ('" + pTablero.getCodTablero() + "','" + pTablero.getNivel() + "',"
				+ pTablero.getlMinas().size() + ",10," + pTablero.getFilas() + "," + pTablero.getColumnas() + ","
				+ u.getCodUsuario() + ")";

		try {
			GestorBD.getConexionBD().actualizarBD(sentencia);
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Tablero getTablero(Tablero pTablero) throws ExcepcionConectarBD, SQLException {
		ResultSet rs = null;
		String sentencia = "";
		sentencia = "SELECT CODTABLERO,NIVEL,CONTADORMINAS,CASILLAS,FILAS,COLUMNAS,CREADOR,LMINAS,LCASILLASVACIAS,LCASILLASPORVISITAR,LCASILLASVISITADAS,LCASILLASBANDERA FROM TABLERO WHERE CODTABLERO='"
				+ pTablero.getCodTablero() + "'";
		rs = GestorBD.getConexionBD().consultaBD(sentencia);

		if (rs != null) {
			while (rs.next()) {
				String codtablero = rs.getString("CODTABLERO");
				String nivel = rs.getString("NIVEL");
				int columnas = rs.getInt("COLUMNAS");
				int filas = rs.getInt("FILAS");
				String minas = rs.getString("LMINAS");
				String casillasVacias = rs.getString("LCASILLASVACIAS");
				String casillasXVisitar = rs.getString("LCASILLASPORVISITAR");
				String casillasVisitadas = rs.getString("LCASILLASVISITADAS");
				String casillasBandera = rs.getString("LCASILLASBANDERA");
				ArrayList<String> lMinas = stringToArrayList(minas);
				ArrayList<String> lCasillasVacias = stringToArrayList(casillasVacias);
				Stack<String> casillasPorVisitar = stringToStack(casillasXVisitar);
				ArrayList<String> lCasillasVisitadas = stringToArrayList(casillasVisitadas);
				ArrayList<String> lCasillasBandera = stringToArrayList(casillasBandera);

				pTablero.setCodTablero(codtablero);
				pTablero.setNivel(nivel);
				pTablero.setColumnas(columnas);
				pTablero.setFilas(filas);
				pTablero.setlMinas(lMinas);
				pTablero.setlCasillasVacias(lCasillasVacias);
				pTablero.setCasillasPorVisitar(casillasPorVisitar);
				pTablero.setlCasillasVisitadas(lCasillasVisitadas);
				pTablero.setlCasillasBandera(lCasillasBandera);
			}
		} else {
			System.out.println("No hay Tableros");
		}
		GestorBD.getConexionBD().closeResult(rs);
		return pTablero;
	}

	private ArrayList<String> stringToArrayList(String pDatos) {
		ArrayList<String> datos = new ArrayList<String>();
		// borrar corchetes
		pDatos = pDatos.replace("[", "");
		pDatos = pDatos.replace("]", "");

		// insertar pares de comas
		while (pDatos != "") {
			// Buscar index of espacio
			int posEspacio = pDatos.indexOf(" ");
			// Añadir datos
			if (posEspacio != -1) {
				datos.add(pDatos.substring(0, posEspacio - 1));
				// Recortar cadena
				pDatos = pDatos.substring(posEspacio + 1);
			} else {
				// Es la ultima
				datos.add(pDatos);
				pDatos = "";
			}

		}
		return datos;
	}

	private Stack<String> stringToStack(String pDatos) {
		Stack<String> datos = new Stack<String>();
		// borrar corchetes
		pDatos = pDatos.replace("[", "");
		pDatos = pDatos.replace("]", "");

		// insertar pares de comas
		while (pDatos != "") {
			// Buscar index of espacio
			int posEspacio = pDatos.indexOf(" ");
			// Añadir datos
			if (posEspacio != -1) {
				datos.push(pDatos.substring(0, posEspacio - 1));
				// Recortar cadena
				pDatos = pDatos.substring(posEspacio + 1);
			} else {
				// Es la ultima
				datos.push(pDatos);
				pDatos = "";
			}

		}
		return datos;
	}
}
