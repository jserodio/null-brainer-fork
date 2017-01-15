package packGestores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import packCodigo.Reto;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;

public class GestorRetos {
	private ArrayList<Reto> listaRetos;
	private static  GestorRetos miGestor;
	
	private GestorRetos() {
		
	}
	
	public static GestorRetos getGestorRetos(){
		if (miGestor==null){
			miGestor = new GestorRetos();
		}
		return miGestor;		
	}

	public void nuevoReto(int punt, String codTablero, String usuRetado, String usuRetador) throws ExcepcionConectarBD {
		// TODO Auto-generated method stub
		String sentencia = "INSERT INTO reto VALUES("+usuRetado+","+usuRetador+","+punt+",'pendiente',"+codTablero+")";
		GestorBD.getConexionBD().actualizarBD(sentencia);
	}

	public ArrayList<Reto> getListaRetos() throws ExcepcionConectarBD, SQLException {
		// TODO Auto-generated method stub
		Usuario usuario = GestorSesion.getSesion().getUsuario();
		GestorBD gestorBD = GestorBD.getConexionBD();
		String cad = "SELECT codReto,codUsuarioRetador,puntuacionRetador,codTablero FROM Reto WHERE nombreUsuarioRetado = '"+usuario.getCodUsuario()+"' AND estado='pendiente' ORDER BY fecha";
		ResultSet resultado = gestorBD.consultaBD(cad);
		String codR,codUR,codP,codT;
		int puntR;
		while(resultado.next()){
			codR = resultado.getString("codReto");
			codUR = resultado.getString("codUsuarioRetador");
			codP = resultado.getString("codPartida");
			codT = resultado.getString("codTablero");
			puntR = resultado.getShort("puntuacionRetador");
			this.listaRetos.add(new Reto(codR,codUR,codT,puntR));
		}
		gestorBD.closeResult(resultado);
		return this.listaRetos;
	}

	public Reto getReto(String codReto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void guardarRetoTerminado(String codReto,int punt) throws ExcepcionConectarBD {
		// TODO Auto-generated method stub
		Reto pReto = this.getReto(codReto);
//		int punt = GestorSesion.getSesion().obtenerPuntuacion();
		GestorBD gestorBD = GestorBD.getConexionBD();
		String sentencia = "UPDATE reto SET estado='terminado',puntuacionRetado='"+punt+"' WHERE codReto='"+codReto+"'";
		gestorBD.actualizarBD(sentencia);		
	}
	
	public void eliminarReto(String codReto) throws ExcepcionConectarBD{
		GestorBD gestorBD = GestorBD.getConexionBD();
		String sentencia = "DELETE FROM reto WHERE codReto='"+codReto+"'";
		gestorBD.actualizarBD(sentencia);
	}

	
	
}
