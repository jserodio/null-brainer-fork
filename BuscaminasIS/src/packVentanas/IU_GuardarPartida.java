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
import packGestores.GestorBuscaminas;
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
		frmGuardarPartida.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuardarPartida = new JFrame();
		frmGuardarPartida.setBounds(100, 100, 341, 224);
		frmGuardarPartida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGuardarPartida.getContentPane().setLayout(null);
		frmGuardarPartida.setLocationRelativeTo(null);

		JLabel lbltexto = new JLabel(
				"<html><center>Selecciona el nombre con \nel que se guardar\u00E1 el tablero:</center></html>");
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
				if (txtNombrePartida.getText().trim().isEmpty()) {
					// Si no ha introducido nombre de partida
					JOptionPane.showMessageDialog(null, "No has introducido el nombre de la partida", "Faltan datos",
							JOptionPane.ERROR_MESSAGE);
					txtNombrePartida.setText("");
					txtNombrePartida.requestFocus();
				} else {
					Partida partidaActual = GestorSesion.getSesion().obtenerPartidaActual();
					partidaActual.setNombrePartida(txtNombrePartida.getText().trim());
					
					// Para Pruebas
					partidaActual.setJugador(new Usuario("2", "Galder", "grevilla", "grevilla002@ikasle.ehu.eus", 10));
					Tablero t1 = new Tablero("F", 12, 12);
					partidaActual.setPuntuacion(20);
					t1.setCodTablero("F1");
					partidaActual.setJuego(t1);
					// Fin para Pruebas
					
					// Añadir Partida
					GestorBuscaminas.getGestorBuscaminas().guardarPartida(partidaActual);
					// Tras añadir cerramos
					System.exit(0);
				}
			}
		});
		btnAceptar.setBounds(105, 119, 89, 23);
		frmGuardarPartida.getContentPane().add(btnAceptar);
	}
}
