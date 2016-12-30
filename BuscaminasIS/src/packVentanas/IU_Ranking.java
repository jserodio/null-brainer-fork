package packVentanas;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import packCodigo.Partida1;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;
import packGestores.GestorBuscaminas;

import javax.swing.JTextArea;
import javax.swing.JButton;

public class IU_Ranking extends JFrame{

	private JFrame frame;
	private JTextArea textArea;
	private JButton btnAtras;
	private String tipo;
	private int nivel;
	private String valor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Ranking window = new IU_Ranking("nivel",null,0);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IU_Ranking(String pTipo,String valor,int nivel) {
		initialize();
		this.tipo=pTipo;
		this.nivel=nivel;
		this.valor=valor;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][grow]", "[][][][grow][]"));
		frame.getContentPane().add(getTextArea(), "flowy,cell 0 0 7 4,grow");
		frame.getContentPane().add(getBtnAtras(), "cell 5 4");
		try {
			this.getRanking();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atr\u00E1s");
		}
		btnAtras.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				IU_Ranking.this.setVisible(false);
				if(tipo.equals("nivel")){
					IU_CRNivel vC=new IU_CRNivel();
					vC.setVisible(true);
					setVisible(false);
				}
				else if(tipo.equals("codUsuario")){
					IU_CRUsuario vC=new IU_CRUsuario();
					vC.setVisible(true);
					setVisible(false);
				}
				else if(tipo.equals("codTablero")){
					IU_CRTablero vC=new IU_CRTablero();
					vC.setVisible(true);
					setVisible(false);
				}	
			}
		});
		return btnAtras;
	}
	
	private void getRanking() throws SQLException, ExcepcionConectarBD{
		ArrayList<Partida1> partidas;
		ArrayList<Usuario> usuarios;
		boolean enc=false;
		if(tipo.equals("nivel")){
			this.getTextArea().append("Posición\t\tNombre\t\tPuntuación");
			partidas=GestorBuscaminas.getGestorBuscaminas().obtenerListaPartidasNivel(nivel);
			usuarios=GestorBuscaminas.getGestorBuscaminas().obtenerUsuarios();
			Iterator<Partida1> it=partidas.iterator();
			Iterator<Usuario> it1;
			Partida1 p;
			Usuario u=null;
			int i=1;
			while(it.hasNext()||i<=10){
				p=it.next();
				it1=usuarios.iterator();
				while(it1.hasNext()||!enc){
					u=it1.next();
					if(u.getCodUsuario().equals(p.getCodUsuario())){
						enc=true;
					}
				}
				if(enc==false){
					this.getTextArea().append("No ha jugado ninguna partida");
				}
				else{
					this.getTextArea().append(i+"\t\t"+u.getNombre()+"\t\t"+p.getPuntuacion());
				}
			}
		}
		else if(tipo.equals("codUsuario")){
			this.getTextArea().append("Pos\tNombre\tNivel\tTablero\tPunt");
			partidas=GestorBuscaminas.getGestorBuscaminas().obtenerListaPartidasUsuario(valor);
			usuarios=GestorBuscaminas.getGestorBuscaminas().obtenerUsuarios();
			Iterator<Partida1> it=partidas.iterator();
			Iterator<Usuario> it1;
			Partida1 p;
			Usuario u=null;
			int i=1;
			while(it.hasNext()||i<=10){
				p=it.next();
				it1=usuarios.iterator();
				while(it1.hasNext()||enc){
					u=it1.next();
					if(u.getCodUsuario().equals(p.getCodUsuario())){
						enc=true;
					}
				}
				if(enc==false){
					this.getTextArea().append("No ha jugado ninguna partida");
				}
				else{
					char nivel=p.getCodTablero().charAt(0);
					if(p.getCodTablero().charAt(1)=='0'){
						char tablero=p.getCodTablero().charAt(p.getCodTablero().length()-1);
						this.getTextArea().append(i+"\t"+u.getNombre()+"\t"+nivel+"\t"+tablero+"\t"+p.getPuntuacion());
					}
					else{
						String tab=p.getCodTablero().substring(1, p.getCodTablero().length()-1);
						this.getTextArea().append(i+"\t"+u.getNombre()+"\t"+nivel+"\t"+tab+"\t"+p.getPuntuacion());
					}
				}
			}
		}
		else if(tipo.equals("codTablero")){
			this.getTextArea().append("Posición\t\tNombre\t\tPuntuación");
			partidas=GestorBuscaminas.getGestorBuscaminas().obtenerListaPartidasTablero(valor);
			usuarios=GestorBuscaminas.getGestorBuscaminas().obtenerUsuarios();
			Iterator<Partida1> it=partidas.iterator();
			Iterator<Usuario> it1;
			Partida1 p;
			Usuario u=null;
			int i=1;
			while(it.hasNext()||i<=10){
				p=it.next();
				it1=usuarios.iterator();
				while(it1.hasNext()||enc){
					u=it1.next();
					if(u.getCodUsuario().equals(p.getCodUsuario())){
						enc=true;
					}
				}
				if(enc==false){
					this.getTextArea().append("No ha jugado ninguna partida");
				}
				else{
					this.getTextArea().append(i+"\t"+u.getNombre()+"\t"+p.getPuntuacion());
				}
			}
		}
		
	}
}
