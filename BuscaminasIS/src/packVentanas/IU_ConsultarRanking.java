package packVentanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IU_ConsultarRanking extends JFrame {

	private JPanel contentPane;
	private JButton btnNivel;
	private JButton btnTableroConcreto;
	private JButton btnUsuario;
	private JButton btnAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_ConsultarRanking frame = new IU_ConsultarRanking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IU_ConsultarRanking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 250, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][]", "[][][][][]"));
		contentPane.add(getBtnNivel(), "cell 4 1");
		contentPane.add(getBtnTableroConcreto(), "cell 4 2,alignx center");
		contentPane.add(getBtnUsuario(), "cell 4 3,alignx center");
		contentPane.add(getBtnAtras(), "cell 4 4,alignx center");
	}

	private JButton getBtnNivel() {
		if (btnNivel == null) {
			btnNivel = new JButton("Nivel de dificultad concreto");
			btnNivel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					IU_CRNivel vN=new IU_CRNivel();
					vN.setVisible(true);
					setVisible(false);
				}
			});
		}
		return btnNivel;
	}
	private JButton getBtnTableroConcreto() {
		if (btnTableroConcreto == null) {
			btnTableroConcreto = new JButton("Tablero concreto");
			btnTableroConcreto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IU_CRTablero vT=new IU_CRTablero();
					vT.setVisible(true);
					setVisible(false);
				}
			});
		}
		return btnTableroConcreto;
	}
	private JButton getBtnUsuario() {
		if (btnUsuario == null) {
			btnUsuario = new JButton("Usuario concreto");
			btnUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IU_CRUsuario vU=new IU_CRUsuario();
					vU.setVisible(true);
					setVisible(false);
				}
			});
		}
		return btnUsuario;
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atr\u00E1s");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IU_Buscaminas vB=new IU_Buscaminas();
					vB.setVisible(true);
					setVisible(false);
				}
			});
		}
		return btnAtras;
	}
}
