package packGestores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
