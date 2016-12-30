package packGestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import packExcepciones.ExcepcionConectarBD;
//https://www.youtube.com/watch?v=OyN1Uocw2AU
public class GestorBD {
	private static GestorBD miConexion;
	private String driver;
	private String url;
	private String user;

	private String pass;
	
	//Rellena las varibles con los datos para conectarse a la bd
	/*Precondicion:
	 *Postcondicion:
	 * */
	private GestorBD(){
		driver="com.mysql.jdbc.Driver";
		url="url de la BD";
		user="usuario"; //usuario que crearemos en la bd
		pass="-------------"; //poner password
	}
	
	//Singleton
	/*Precondicion:
	 *Postcondicion:
	 * */
	public static GestorBD getConexionBD(){
		if (miConexion==null){
			miConexion = new GestorBD();
		}
		return miConexion;
	}
	
	//A este metodo se le pasa un String con la consulta y nos devuelve el resultado de esta.
	/*Precondicion: Poner una consulta SQL sin errores.
	 *Postcondicion:
	 * */
	public ResultSet consultaBD(String consulta) throws ExcepcionConectarBD{
		ResultSet result=null;
		try{
			Class.forName(driver);
			Connection conexion=DriverManager.getConnection(url,user,pass);
			Statement state = (Statement) conexion.createStatement();
			conexion.setAutoCommit(false);
			result=(ResultSet) state.executeQuery(consulta);
			conexion.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			throw new ExcepcionConectarBD();
		}
		return result;
	}
	//Este metodo se llama despues de hacer una consulta. Cierra la conexion que habia quedado abierta.
	/*Precondicion: Pasarle el resultado de una consulta que se habia hecho.
	 *Postcondicion: La conexion a la bd queda cerrada.
	 * */
	public void closeResult(ResultSet result){
		try {
			result.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Metodo al que se le pasan sentencias SQL que impliquen modificaciones en la bd(añadir,eliminar o modificar).
	/*Precondicion: Pasarle una sentencia SQL valida.
	 *Postcondicion: La bd se ha actualizado correctamente.
	 * */
	public void actualizarBD(String sentencia)throws ExcepcionConectarBD{
		try{
			Class.forName(driver);
			Connection conexion=DriverManager.getConnection(url,user,pass);
			Statement state=(Statement) conexion.createStatement();
			conexion.setAutoCommit(false);
			state.executeUpdate(sentencia);
			conexion.commit();
			state.getConnection().close();
		}catch(Exception e){
			e.printStackTrace();
			throw new ExcepcionConectarBD();
		}
	}
}

