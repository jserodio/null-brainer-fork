package packVentanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import packCodigo.Partida;
import packCodigo.Tablero;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;
import packGestores.GestorPartidas;
import packGestores.GestorSesion;

public class IU_GuardarPartida {

	private JFrame frmGuardarPartida;
	private JTextField txtNombrePartida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_GuardarPartida window = new IU_GuardarPartida();
					window.frmGuardarPartida.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IU_GuardarPartida() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuardarPartida = new JFrame();
		frmGuardarPartida.setBounds(100, 100, 341, 224);
		frmGuardarPartida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGuardarPartida.getContentPane().setLayout(null);
		
		JLabel lbltexto = new JLabel("<html><center>Selecciona el nombre con \nel que se guardar\u00E1 el tablero:</center></html>");
		lbltexto.setHorizontalAlignment(SwingConstants.CENTER);
		lbltexto.setBounds(61, 34, 191, 43);
		frmGuardarPartida.getContentPane().add(lbltexto);
		
		txtNombrePartida = new JTextField();
		txtNombrePartida.setBounds(71, 88, 167, 20);
		frmGuardarPartida.getContentPane().add(txtNombrePartida);
		txtNombrePartida.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comprobarNombrePartida(txtNombrePartida.getText().trim())){
					// GuardarPartida
					Partida partidaActual = GestorSesion.getSesion().obtenerPartidaActual();
					partidaActual.setNombrePartida(txtNombrePartida.getText().trim());
					//Pruebas
					partidaActual.setJugador(new Usuario("2", "Galder", "grevilla", "grevilla002@ikasle.ehu.eus", 10));
					Tablero t1 = new Tablero(1,12,12);
					t1.setCodTablero("F1");
					partidaActual.setJuego(t1);
					// Añadir Partida
					GestorPartidas.getGestorPartidas().guardarPartida(partidaActual);
				}
			}
		});
		btnAceptar.setBounds(105, 119, 89, 23);
		frmGuardarPartida.getContentPane().add(btnAceptar);
	}
	
	private boolean comprobarNombrePartida(String nombrePartida){
		// Si no se ha introducido nombre partida
		if (nombrePartida.isEmpty()){
			JOptionPane.showMessageDialog(null, "No has introducido el nombre de la partida", "Faltan datos", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// Si la partida ya existe en la BD
		try {
			if(GestorPartidas.getGestorPartidas().comprobarNombrePartida(nombrePartida)){
				return false;
			}
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
}
