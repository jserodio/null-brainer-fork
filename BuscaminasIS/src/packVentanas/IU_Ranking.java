package packVentanas;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import packCodigo.Partida;
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
					IU_Ranking window = new IU_Ranking("nivel",null,1);
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
		this.tipo=pTipo;
		this.nivel=nivel;
		this.valor=valor;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 525, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][grow]", "[][][][grow][]"));
		frame.getContentPane().add(getTextArea(), "flowy,cell 0 0 7 4,grow");
		frame.getContentPane().add(getBtnAtras(), "cell 5 4");
		frame.setVisible(true);
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
		textArea.setEditable(false);
		return textArea;
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atr\u00E1s");
		}
		btnAtras.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tipo.equals("nivel")){
					IU_CRNivel vC=new IU_CRNivel();
					vC.setVisible(true);
					frame.setVisible(false);
				}
				else if(tipo.equals("codUsuario")){
					IU_CRUsuario vC=new IU_CRUsuario();
					frame.setVisible(false);
				}
				else if(tipo.equals("codTablero")){
					IU_CRTablero vC=new IU_CRTablero();
					frame.setVisible(false);
				}	
			}
		});
		return btnAtras;
	}
	
	private void getRanking() throws SQLException, ExcepcionConectarBD{
		ArrayList<Partida> partidas;
		ArrayList<Usuario> usuarios;
		System.out.println(this.tipo);
		System.out.println(this.nivel);
		if(tipo.equals("nivel")){
			if(this.nivel==1){
				partidas=GestorBuscaminas.getGestorBuscaminas().obtenerListaPartidasNivel("F");
				usuarios=GestorBuscaminas.getGestorBuscaminas().obtenerUsuarios();
				if(partidas.isEmpty()){
					this.getTextArea().append("No se han jugado partidas en este nivel");
				}
				else{
					this.getTextArea().setFont(new Font("Serif", TextAttribute.UNDERLINE_ON,Font.BOLD));
					this.getTextArea().append("Posición\tTablero\tNombre\tPuntuación\n");
					Iterator<Partida> it=partidas.iterator();
					String nombre="";
					Partida p;
					int i=1;
					while(it.hasNext()&&i<=10){
						p=it.next();
						nombre=GestorBuscaminas.getGestorBuscaminas().buscarNombre(p.getCodUsuario());
						this.getTextArea().setFont(new Font("Arial",Font.PLAIN, 15));
						this.getTextArea().append(i+"\t"+p.getCodTablero()+"\t"+nombre+"\t"+p.getPuntuacion()+"\n");
						i++;
					}
			   }
			
			}else if(this.nivel==2){
				partidas=GestorBuscaminas.getGestorBuscaminas().obtenerListaPartidasNivel("M");
				usuarios=GestorBuscaminas.getGestorBuscaminas().obtenerUsuarios();
				if(partidas.isEmpty()){
					this.getTextArea().append("No se han jugado partidas en este nivel");
				}
				else{
					this.getTextArea().setFont(new Font("Serif", TextAttribute.UNDERLINE_ON,Font.BOLD));
					this.getTextArea().append("Posición\tTablero\tNombre\tPuntuación\n");
					Iterator<Partida> it=partidas.iterator();
					String nombre="";
					Partida p;
					int i=1;
					while(it.hasNext()&&i<=10){
						p=it.next();
						nombre=GestorBuscaminas.getGestorBuscaminas().buscarNombre(p.getCodUsuario());
						this.getTextArea().setFont(new Font("Arial",Font.PLAIN, 15));
						this.getTextArea().append(i+"\t"+p.getCodTablero()+"\t"+nombre+"\t"+p.getPuntuacion()+"\n");
						i++;
					}
			   }
			}else if(this.nivel==3){
				partidas=GestorBuscaminas.getGestorBuscaminas().obtenerListaPartidasNivel("D");
				usuarios=GestorBuscaminas.getGestorBuscaminas().obtenerUsuarios();
				if(partidas.isEmpty()){
					this.getTextArea().append("No se han jugado partidas en este nivel");
				}
				else{
					this.getTextArea().setFont(new Font("Serif", TextAttribute.UNDERLINE_ON,Font.BOLD));
					this.getTextArea().append("Posición\tTablero\tNombre\tPuntuación\n");
					Iterator<Partida> it=partidas.iterator();
					String nombre="";
					Partida p;
					int i=1;
					while(it.hasNext()&&i<=10){
						p=it.next();
						nombre=GestorBuscaminas.getGestorBuscaminas().buscarNombre(p.getCodUsuario());
						this.getTextArea().setFont(new Font("Arial",Font.PLAIN, 15));
						this.getTextArea().append(i+"\t"+p.getCodTablero()+"\t"+nombre+"\t"+p.getPuntuacion()+"\n");
						i++;
					}
			   }
				
			}
		}
		else if(tipo.equals("codUsuario")){
			System.out.println("El codigo del usuario es:"+valor);
			partidas=GestorBuscaminas.getGestorBuscaminas().obtenerListaPartidasUsuario(valor);
			System.out.println(partidas.size());
			usuarios=GestorBuscaminas.getGestorBuscaminas().obtenerUsuarios();
			if(partidas.isEmpty()){
				this.getTextArea().append("No ha jugado partidas el usuario");
			}
			else{
				this.getTextArea().append("Pos\tNombre\tNivel\tTablero\tPunt\n");
				Iterator<Partida> it=partidas.iterator();
				Partida p;
				String nombre="";
				int i=1;
				while(it.hasNext()&&i<=10){
					p=it.next();
					nombre=GestorBuscaminas.getGestorBuscaminas().buscarNombre(p.getCodUsuario());
					char nivel=p.getCodTablero().charAt(0);
					if(p.getCodTablero().length()>2){
						String tablero="";
						i=1;
						while(i<=p.getCodTablero().length()){
							tablero=tablero+p.getCodTablero().charAt(i);
						}
						this.getTextArea().append(i+"\t"+nombre+"\t"+nivel+"\t"+tablero+"\t"+p.getPuntuacion()+"\n");
					}
					else{
						char tab=p.getCodTablero().charAt(1);
						this.getTextArea().append(i+"\t"+nombre+"\t"+nivel+"\t"+tab+"\t"+p.getPuntuacion()+"\n");
					}
					i++;
				}
				
			}
		}
		else if(tipo.equals("codTablero")){
			partidas=GestorBuscaminas.getGestorBuscaminas().obtenerListaPartidasTablero(valor);
			usuarios=GestorBuscaminas.getGestorBuscaminas().obtenerUsuarios();
			String nombre=null;
			if(partidas.isEmpty()){
				this.getTextArea().append("No se han jugado partidas en el tablero escogido");
			}
			else{
				this.getTextArea().append("Posición\t\tNombre\t\tPuntuación\n");
				Iterator<Partida> it=partidas.iterator();
				Partida p;
				int i=1;
				while(it.hasNext()&&i<=10){
					p=it.next();
					nombre=GestorBuscaminas.getGestorBuscaminas().buscarNombre(p.getCodUsuario());
					this.getTextArea().setFont(new Font("Arial",Font.PLAIN, 15));
					this.getTextArea().append(i+"\t\t"+nombre+"\t\t"+p.getPuntuacion()+"\n");
					i++;
				}
			}
		}
		
	}
}