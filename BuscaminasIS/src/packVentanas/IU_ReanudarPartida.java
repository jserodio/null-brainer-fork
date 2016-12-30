package packVentanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;

public class IU_ReanudarPartida {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_ReanudarPartida window = new IU_ReanudarPartida();
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
	public IU_ReanudarPartida() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbltitulo = new JLabel("Lista partidas guardadas");
		lbltitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltitulo.setBounds(126, 26, 179, 32);
		frame.getContentPane().add(lbltitulo);
		
		JList list = new JList();
		list.setBounds(45, 69, 341, 140);
		frame.getContentPane().add(list);
		
		JButton btnCargar = new JButton("Cancelar");
		btnCargar.setBounds(297, 227, 89, 23);
		frame.getContentPane().add(btnCargar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(198, 227, 89, 23);
		frame.getContentPane().add(btnAceptar);
	}
}
