package packGestores;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import packModelo.Conexion;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;

public class GestorUsuarios {
	
	private static final GestorUsuarios miGestorUsuarios = new GestorUsuarios();
	private ArrayList<Usuario> listaUsuarios;
    
    private GestorUsuarios() {
    	listaUsuarios=new ArrayList<Usuario>();
    }

    public static GestorUsuarios getGestorUsuarios() {
        return miGestorUsuarios;
    }
	
	public boolean identificarse(String user, String password) throws SQLException, ExcepcionConectarBD{
		
		  ResultSet rs = null;
		  String cadena = "SELECT * FROM USUARIO WHERE nombre ='"+user+"' AND clave ='"+password+"'";
		  String nombre=null,clave=null;
		  String cod=null,email=null;
		  int numP=0;
		  rs = GestorBD.getConexionBD().consultaBD(cadena);
		  if(rs!=null){
				  int i=0;
				  //se rellena el atributo retos con los retos que tiene el usuario sin jugar
				  System.out.println("AQUIIIIIIII");
				  while(rs.next()){
					  nombre=rs.getString("nombre");
					  clave=rs.getString("clave");
					  cod=rs.getString("codUsuario");
					  email=rs.getString("email");
					  numP=rs.getInt("pistas");
				  }
				  System.out.println(nombre);
				  System.out.println(clave);
				  if(!(nombre.equals(null)&&clave.equals(null))){
					  GestorSesion.getSesion().setUsuario(new Usuario(cod,nombre,clave,email,numP));
					  GestorBD.getConexionBD().closeResult(rs);
					  System.out.println("Identificación correcta");
					  return true;
				  }
				  else{
					  System.out.println("Identificación incorrecta");
					  GestorBD.getConexionBD().closeResult(rs);
					  return false;
				  }
			  }
		else{
				  System.out.println("Identificación incorrecta por aque");
				  GestorBD.getConexionBD().closeResult(rs);
				  return false;
		}
	}
	
	public Boolean existeUser (String user) throws SQLException, ExcepcionConectarBD{
		ResultSet rs = null;
		int resultado = 0;
		String cadena = "SELECT Count(NOMBRE) as numero FROM USUARIO WHERE NOMBRE='"+user+"';";
			

		rs = GestorBD.getConexionBD().consultaBD(cadena);
		
		if (rs != null){
			rs.next();
			resultado = Integer.parseInt((String) rs.getObject("numero").toString());
		}

		  GestorBD.getConexionBD().closeResult(rs);
		
		if (resultado>0)
			return true;
		return false;
	}
	
	public boolean registrarse(String user,String password,String confirmedPassword,String email) throws SQLException, ExcepcionConectarBD{
		if(password.equals(confirmedPassword)){
			  if(!this.existeUser(user)){
				  	String cadena = "INSERT INTO USUARIO(NOMBRE,CLAVE,PISTAS,EMAIL) VALUES ('"+user+"', '"+password+"', '10', '"+email+"');";
					GestorBD.getConexionBD().actualizarBD(cadena);
					return true;
			  }
			  else{
				  System.out.println("Registro incorrecto, existe el usuario");
				  return false;
			  }
		}
		else{
			System.out.println("Registro incorrecto, no coinciden las contraseñas");
			return false;
		}
	}
	
	public ArrayList<Usuario> obtenerUsuarios() throws SQLException, ExcepcionConectarBD{
		if(listaUsuarios.isEmpty()==false){
			listaUsuarios.clear();
		}
		  ResultSet rs = null;
		  String cadena = "SELECT * FROM USUARIO ORDER BY nombre ASC";
		  rs = GestorBD.getConexionBD().consultaBD(cadena);
		  
		  if(rs!=null){
			  while(rs.next()){
				  if(!rs.getString("nombre").equals(GestorSesion.getSesion().getUsuario().getNombre())){
					  Usuario u = new Usuario(rs.getString("codUsuario"),rs.getString("nombre"),rs.getString("clave"),rs.getString("email"),rs.getInt("numeroPistas"));
					  listaUsuarios.add(u);
				  }
			  }
		  }
		  else{
			  System.out.println("No hay Jugadores");
		  }
		  GestorBD.getConexionBD().closeResult(rs);
		  return listaUsuarios;
	}

}
