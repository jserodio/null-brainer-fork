package packVentanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class IU_CRTablero extends JFrame {
	private JFrame frame;
	private JButton btnAtras;
	private JButton btnAceptar;
	private JLabel lblNivel;
	private JComboBox nivel;
	private JLabel lblTablero;
	private JComboBox tablero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_CRTablero window = new IU_CRTablero();
					window.frame.setVisible(true);
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
	public IU_CRTablero() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 250, 225, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][grow][grow][]", "[][][][grow]"));
		frame.getContentPane().add(getLblNivel(), "cell 4 1,alignx center");
		frame.getContentPane().add(getNivel(), "cell 5 1,growx");
		frame.getContentPane().add(getLblTablero(), "cell 4 2,alignx center");
		frame.getContentPane().add(getTablero(), "cell 5 2,growx");
		frame.getContentPane().add(getBtnAceptar(), "cell 4 3");
		frame.getContentPane().add(getBtnAtras(), "flowx,cell 5 3");
		frame.setVisible(true);
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atr\u00E1s");
			btnAtras.addMouseListener(new MouseAdapter() {
				public void mouseClicked(ActionEvent e) {
					IU_ConsultarRanking vCR=new IU_ConsultarRanking();
					vCR.setVisible(true);
					setVisible(false);
				}
			});
		}
		return btnAtras;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String n=(String)nivel.getSelectedItem();
					String t=(String)tablero.getSelectedItem();
					String aux=null;
					if(t.length()==1){
						t=aux;
						t="0";
						t=t+aux;
					}
					IU_Ranking vCR=new IU_Ranking("codTablero",n+t,0);
					vCR.setVisible(true);
					setVisible(false);
				}
			});
		}
		return btnAceptar;
	}
	private JLabel getLblNivel() {
		if (lblNivel == null) {
			lblNivel = new JLabel("Nivel");
		}
		return lblNivel;
	}
	private JComboBox getNivel() {
		if (nivel == null) {
			nivel = new JComboBox();
		}
		nivel.addItem("");
		nivel.addItem("Fácil");
		nivel.addItem("Medio");
		nivel.addItem("Dificil");
		return nivel;
	}
	private JLabel getLblTablero() {
		if (lblTablero == null) {
			lblTablero = new JLabel("Tablero");
		}
		return lblTablero;
	}
	private JComboBox getTablero() {
		if (tablero == null) {
			tablero = new JComboBox();
		}
		return tablero;
	}
}

