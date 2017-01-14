package packGestores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import packExcepciones.ExcepcionConectarBD;

public class GestorTablero {
	private static final GestorTablero miGestorTablero=new GestorTablero();
	
	private GestorTablero(){}
	
	public static GestorTablero getGestorTablero(){
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
	

}
