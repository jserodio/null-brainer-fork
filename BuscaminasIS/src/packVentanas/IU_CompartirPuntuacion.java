package packVentanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IU_CompartirPuntuacion extends JFrame {

	/**
	 * 
	 */
	private static IU_CompartirPuntuacion ventana;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_CompartirPuntuacion frame = new IU_CompartirPuntuacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static IU_CompartirPuntuacion getVentana(){
		if(ventana == null){
			ventana = new IU_CompartirPuntuacion();
		}
		return ventana;
	}
	
	/**
	 * Create the frame.
	 */
	public IU_CompartirPuntuacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
