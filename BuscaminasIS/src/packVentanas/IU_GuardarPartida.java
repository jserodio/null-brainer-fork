package packVentanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class IU_GuardarPartida {

	private JFrame frmGuardarPartida;
	private JTextField textField;

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
		frmGuardarPartida.setTitle("Guardar Partida");
		frmGuardarPartida.setBounds(100, 100, 341, 224);
		frmGuardarPartida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGuardarPartida.getContentPane().setLayout(null);
		
		JLabel lbltexto = new JLabel("<html><center>Selecciona el nombre con \nel que se guardar\u00E1 el tablero:</center></html>");
		lbltexto.setHorizontalAlignment(SwingConstants.CENTER);
		lbltexto.setBounds(61, 34, 191, 43);
		frmGuardarPartida.getContentPane().add(lbltexto);
		
		textField = new JTextField();
		textField.setBounds(71, 88, 167, 20);
		frmGuardarPartida.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(105, 119, 89, 23);
		frmGuardarPartida.getContentPane().add(btnAceptar);
	}
}
