package packVentanas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import packCodigo.Partida;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;
import packGestores.GestorBuscaminas;
import packGestores.GestorPartidas;
import packGestores.GestorSesion;

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
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lbltitulo = new JLabel("Lista partidas guardadas");
		lbltitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltitulo.setBounds(126, 26, 179, 32);
		frame.getContentPane().add(lbltitulo);

		JList<Partida> listaPartidas = new JList<Partida>();
		listaPartidas.setBounds(45, 69, 341, 140);
		frame.getContentPane().add(listaPartidas);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IU_Buscaminas window = new IU_Buscaminas();
				window.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnCancelar.setBounds(297, 227, 89, 23);
		frame.getContentPane().add(btnCancelar);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorBuscaminas.getGestorBuscaminas().reanudarPartida(listaPartidas.getSelectedValue());
				System.exit(0);
			}
		});
		btnAceptar.setBounds(198, 227, 89, 23);
		frame.getContentPane().add(btnAceptar);
		cargarLista(listaPartidas);
		
		
		this.frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	IU_Buscaminas window = new IU_Buscaminas();
				window.setVisible(true);
				frame.setVisible(false);
            }
        });
	}

	private void cargarLista(JList<Partida> listaPartidas) {
		try {
			Usuario jugador = GestorSesion.getSesion().getUsuario();
			ArrayList<Partida> partidas = GestorPartidas.getGestorPartidas()
					.obtenerListaPartidasUsuario(jugador.getCodUsuario());
			if (partidas.isEmpty()) {
				// Si el usuario no tiene Partidas error y fuera
				JOptionPane.showMessageDialog(null, "No existen partidas guardadas", "Reanudar Partida",
						JOptionPane.INFORMATION_MESSAGE);
				IU_Buscaminas window = new IU_Buscaminas();
				window.setVisible(true);
				frame.setVisible(false);
			} else {
				DefaultListModel<Partida> modelo = new DefaultListModel<Partida>();
				for (Partida partida : partidas) {
					modelo.add(modelo.size(), partida);
				}
				listaPartidas.setModel(modelo);
				listaPartidas.setSelectedIndex(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ExcepcionConectarBD e) {
			e.printStackTrace();
		}
	}
}
