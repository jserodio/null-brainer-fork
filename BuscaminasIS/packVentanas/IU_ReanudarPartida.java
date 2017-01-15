package packVentanas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		frame.setLocationRelativeTo(null);

		JLabel lbltitulo = new JLabel("Lista partidas guardadas");
		lbltitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltitulo.setBounds(126, 26, 179, 32);
		frame.getContentPane().add(lbltitulo);

		JList<Partida> listaPartidas = new JList<Partida>();
		listaPartidas.setBounds(45, 69, 341, 140);
		frame.getContentPane().add(listaPartidas);

		JButton btnCargar = new JButton("Cancelar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCargar.setBounds(297, 227, 89, 23);
		frame.getContentPane().add(btnCargar);

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
	}

	private void cargarLista(JList<Partida> listaPartidas) {
		try {
			// PRUEBAS
			// Usuario jugador = GestorSesion.getSesion().getUsuario();
			Usuario jugador = new Usuario("2", "Galder", "grevilla", "grevilla002@ikasle.ehu.eus", 10);
			// FIN PRUEBAS

			ArrayList<Partida> partidas = GestorPartidas.getGestorPartidas()
					.obtenerListaPartidasUsuario(jugador.getCodUsuario());
			if (partidas.isEmpty()) {
				// Si el usuario no tiene Partidas error y fuera
				JOptionPane.showMessageDialog(null, "No existen partidas guardadas", "Reanudar Partida",
						JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			} else {
				DefaultListModel<Partida> modelo = new DefaultListModel<Partida>();
				for (Partida partida : partidas) {
					modelo.add(modelo.size(), partida);
				}
				listaPartidas.setModel(modelo);
				listaPartidas.setSelectedIndex(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcepcionConectarBD e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}