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
	
	public ArrayList<String> obtenerTableros(String pNivel) throws ExcepcionConectarBD, SQLException{
		ArrayList<String> tablero=new ArrayList<String>();
		ResultSet rs = null;
		  String cadena = "SELECT codTablero FROM TABLERO WHERE nivel ='"+pNivel+"' ORDER BY codTablero ASC";
		  rs = GestorBD.getConexionBD().consultaBD(cadena);
		  
		  if(rs!=null){
			  while(rs.next()){
					  String p = rs.getString("codTablero");
					  tablero.add(p.substring(1, p.length()-1));
			  }
		  }
		  else{
			  System.out.println("No hay Tableros");
		  }
		  GestorBD.getConexionBD().closeResult(rs);
		  return tablero;
	}
	
	public Tablero getTablero(String pTablero) throws ExcepcionConectarBD, SQLException{
		ResultSet rs = null;
		int filas = 0,columnas = 0;
		String nivel = null;
		String cadena = "SELECT nivel, filas, columnas FROM TABLERO WHERE codTablero ='"+pTablero+"' ";
		rs = GestorBD.getConexionBD().consultaBD(cadena);
		  
		if(rs!=null){
			while(rs.next()){
				  	  nivel = rs.getObject("nivel").toString();
					  filas = Integer.parseInt((String) rs.getObject("filas").toString());
					  columnas = Integer.parseInt((String) rs.getObject("columnas").toString());
			  }
		  }
		  else{
			  System.out.println("No hay Tableros");
		  }
		  Tablero tablero = new Tablero(nivel, filas, columnas);
		  GestorBD.getConexionBD().closeResult(rs);
		  return tablero;
	}

	public void guardarTablero() throws ExcepcionConectarBD {
		Tablero tab = GestorSesion.getSesion().getTablero();
		String sentencia = "INSERT INTO tablero (CODTABLERO,NIVEL,FILAS,COLUMNAS) VALUES ("+tab.getCodTablero()+","+tab.getNivel()+","+tab.getFilas()+","+tab.getColumnas()+")";
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
}
