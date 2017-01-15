package packVentanas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import packCodigo.Ranking;
import packExcepciones.ExcepcionConectarBD;
import java.sql.SQLException;

public class IU_Buscaminas {

	private JFrame frmMenuBuscaminas;
	private static IU_Buscaminas ventana;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Buscaminas window = new IU_Buscaminas();
					window.frmMenuBuscaminas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public IU_Buscaminas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenuBuscaminas = new JFrame();
		frmMenuBuscaminas.setTitle("Menu Buscaminas (IU_Buscaminas)");
		frmMenuBuscaminas.setBounds(100, 100, 450, 300);
		frmMenuBuscaminas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuBuscaminas.setLocationRelativeTo(null);;
		frmMenuBuscaminas.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("17px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("BUSCAMINAS");
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmMenuBuscaminas.getContentPane().add(lblNewJgoodiesTitle, "2, 1, 1, 3, fill, fill");
		
		JButton btnComenzarPartida = new JButton("Comenzar Partida");
		btnComenzarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Ranking.getRanking().cargarLista();
				 IU_Jugar vB = new IU_Jugar(3);
				 vB.setVisible(true);
				 setVisible(false);
			}
		});
		frmMenuBuscaminas.getContentPane().add(btnComenzarPartida, "2, 5");
		
		JButton btnReanudarPartida = new JButton("Reanudar Partida");
		btnReanudarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IU_ReanudarPartida();
				frmMenuBuscaminas.dispose();
			}
		});
		frmMenuBuscaminas.getContentPane().add(btnReanudarPartida, "2, 7");
		
		JButton btnCrearTablero = new JButton("Crear Tablero");
		frmMenuBuscaminas.getContentPane().add(btnCrearTablero, "2, 9");
		
		JButton btnListaDeRetos = new JButton("Lista Retos");
		btnListaDeRetos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					VLista_Retos.getVentana().setVisible(true);
					setVisible(false);
				} catch (ExcepcionConectarBD | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		frmMenuBuscaminas.getContentPane().add(btnListaDeRetos, "2, 11");
		
		JButton btnConsultarRanking = new JButton("Consultar Ranking");
		frmMenuBuscaminas.getContentPane().add(btnConsultarRanking, "2, 13");
		btnConsultarRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IU_ConsultarRanking vCR=new IU_ConsultarRanking();
				setVisible(false);
			}
		});
		
		JButton btnJugarContrarreloj = new JButton("Jugar Contrarreloj");
		btnJugarContrarreloj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IU_Jugar iu_jugar = new IU_Jugar();
				iu_jugar.setVisible(true);
				frmMenuBuscaminas.dispose();
			}
		});
		frmMenuBuscaminas.getContentPane().add(btnJugarContrarreloj, "2, 15");
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		frmMenuBuscaminas.getContentPane().add(btnSalir, "2, 17");
	}
	
	public void setVisible(boolean pB){
		frmMenuBuscaminas.setVisible(pB);
	}

}
