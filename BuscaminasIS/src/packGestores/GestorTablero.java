package packGestores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packCodigo.Partida1;
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
		  String cadena = "SELECT codTablero FROM TABLEROS WHERE nivel ='"+pNivel+"' ORDER BY ASC";
		  rs = GestorBD.getConexionBD().consultaBD(cadena);
		  
		  if(rs!=null){
			  while(rs.next()){
					  String p = rs.getString("codTablero");
					  String aux=null;
					  if(p.length()==3){
						  if(p.charAt(p.charAt(1))=='0'){
							  p=aux;
							  p=""+p.charAt(p.length()-1);
						  }
						  else{
							  p=""+p.charAt(1)+p.charAt(p.length()-1);
						  }
						  tablero.add(p);
					  }
					  else if(p.length()>3){
						  int i=1;
						  p=aux;
						  p="";
						  while(p.length()-1>=i){
							  p=p+p.charAt(i);
						  }
					  }
			  }
		  }
		  else{
			  System.out.println("No hay Jugadores");
		  }
		  GestorBD.getConexionBD().closeResult(rs);
		  return tablero;
	}
	

}
