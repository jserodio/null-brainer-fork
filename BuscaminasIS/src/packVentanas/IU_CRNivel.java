package packVentanas;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class IU_CRNivel extends JFrame {

	private JFrame frame;
	private JButton btnAtras;
	private JButton btnAceptar;
	private JLabel lblNivel;
	private JComboBox comboBox;

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
	 */
	public IU_CRNivel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame()); 
		getFrame().setBounds(500, 250, 275, 150);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(new MigLayout("", "[][][][][][grow][grow][]", "[][][grow]"));
		getFrame().getContentPane().add(getLblNivel(), "cell 4 1,alignx trailing");
		getFrame().getContentPane().add(getComboBox(), "cell 5 1,growx");
		getFrame().getContentPane().add(getBtnAceptar(), "cell 4 2");
		getFrame().getContentPane().add(getBtnAtras(), "flowx,cell 6 2");
		frame.setVisible(true);
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atr\u00E1s");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
					String nivel=(String)getComboBox().getSelectedItem();
					if(nivel.equals("Fácil")){
						IU_Ranking vCR=new IU_Ranking("nivel",null,1);
						vCR.setVisible(true);
						setVisible(false);
					}
					else if(nivel.equals("Medio")){
						IU_Ranking vCR=new IU_Ranking("nivel",null,2);
						vCR.setVisible(true);
						setVisible(false);
					}
					else{
						IU_Ranking vCR=new IU_Ranking("nivel",null,3);
						vCR.setVisible(true);
						setVisible(false);
					}
					
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
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
		}
		comboBox.addItem("Fácil");
		comboBox.addItem("Medio");
		comboBox.addItem("Dificil");
		return comboBox;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
