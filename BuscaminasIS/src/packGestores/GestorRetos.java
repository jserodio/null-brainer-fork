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
		listaRetos = new ArrayList<Reto>();
		Usuario usuario = GestorSesion.getSesion().getUsuario();
		GestorBD gestorBD = GestorBD.getConexionBD();
		String cad = "SELECT CODRETO,CODUSUARIORETADOR,PUNTUACIONRETADOR,CODTABLERO FROM RETO WHERE CODUSUARIORETADO = '"+usuario.getCodUsuario()+"' AND estado='pendiente' ";
		ResultSet resultado = gestorBD.consultaBD(cad);
		String codR,codUR,codP,codT;
		int puntR;
		while(resultado.next()){
			codR = resultado.getString("codReto");
			codUR = resultado.getString("codUsuarioRetador");
			codT = resultado.getString("codTablero");
			puntR = resultado.getShort("puntuacionRetador");
			Reto rt = new Reto(codR,codUR,codT,puntR);
			this.listaRetos.add(rt);
		}
		gestorBD.closeResult(resultado);
		
		return this.listaRetos;
	}

	public Reto getReto(String codReto) {
		// TODO Auto-generated method stub
		boolean esta = false;
		int x = 0;
		Reto rt = null;
		while(!esta || x != this.listaRetos.size() ){
			if (this.listaRetos.get(x).getCodReto().equals(codReto)){
				esta = true;
				rt = this.listaRetos.get(x);
			}
			x = x+1;
		}
		return rt;
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
