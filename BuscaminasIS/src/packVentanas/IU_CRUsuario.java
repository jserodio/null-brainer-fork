package packVentanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;
import packGestores.GestorBuscaminas;

public class IU_CRUsuario extends JFrame{
	private JFrame frame;
	private JButton btnAtras;
	private JButton btnAceptar;
	private JLabel lblUsuario;
	private JComboBox usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_CRNivel window = new IU_CRNivel();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public IU_CRUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][grow][grow][]", "[][][][grow]"));
		frame.getContentPane().add(getLblUsuario(), "cell 4 1,alignx trailing");
		frame.getContentPane().add(getUsuario(), "cell 5 1,growx");
		frame.getContentPane().add(getBtnNewButton_1(), "cell 4 3");
		frame.getContentPane().add(getBtnAtras(), "flowx,cell 6 3");

	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atr\u00E1s");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IU_ConsultarRanking vCR=new IU_ConsultarRanking();
					vCR.setVisible(true);
				}
			});
		}
		return btnAtras;
	}
	private JButton getBtnNewButton_1() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addMouseListener(new MouseAdapter() {
				public void mouseClicked(ActionEvent e) {
					String nombre=(String) usuario.getSelectedItem();
					try {
						ArrayList<Usuario> usuarios=GestorBuscaminas.getGestorBuscaminas().obtenerUsuarios();
						Iterator<Usuario> it=usuarios.iterator();
						Usuario u=null;
						boolean enc=false;
						while(it.hasNext()||!enc){
							u=it.next();
							if(nombre.equals(u.getNombre())){
								enc=true;
							}
						}
						IU_Ranking vCR=new IU_Ranking("codUsuario",u.getCodUsuario(),0);
						vCR.setVisible(true);
						setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ExcepcionConectarBD e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		return btnAceptar;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario");
		}
		return lblUsuario;
	}
	private JComboBox getUsuario() {
		if (usuario == null) {
			usuario = new JComboBox();
		}
		usuario.addItem("Nombres de usuarios");
		try {
			ArrayList<Usuario> usuarios=GestorBuscaminas.getGestorBuscaminas().obtenerUsuarios();
			Iterator<Usuario> it=usuarios.iterator();
			Usuario u=null;
			while(it.hasNext()){
				u=it.next();
				usuario.addItem(u.getNombre());
			}
		} catch (SQLException | ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}
}

