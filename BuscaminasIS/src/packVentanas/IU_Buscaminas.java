package packVentanas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;

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

	public static IU_Buscaminas getVentana(){
		if(ventana == null){
			ventana = new IU_Buscaminas();
		}
		return ventana;
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
		frmMenuBuscaminas.getContentPane().add(btnComenzarPartida, "2, 5");
		
		JButton btnReanudarPartida = new JButton("Reanudar Partida");
		frmMenuBuscaminas.getContentPane().add(btnReanudarPartida, "2, 7");
		
		JButton btnCrearTablero = new JButton("Crear Tablero");
		frmMenuBuscaminas.getContentPane().add(btnCrearTablero, "2, 9");
		
		JButton btnListaDeRetos = new JButton("Lista de Retos");
		frmMenuBuscaminas.getContentPane().add(btnListaDeRetos, "2, 11");
		
		JButton btnConsultarRanking = new JButton("Consultar Ranking");
		frmMenuBuscaminas.getContentPane().add(btnConsultarRanking, "2, 13");
		btnConsultarRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IU_ConsultarRanking vCR=new IU_ConsultarRanking();
				vCR.setVisible(true);
			}
		});
		
		JButton btnJugarContrarreloj = new JButton("Jugar Contrarreloj");
		btnJugarContrarreloj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IU_Jugar iu_jugar = new IU_Jugar();
				iu_jugar.setVisible(true);
			}
		});
		frmMenuBuscaminas.getContentPane().add(btnJugarContrarreloj, "2, 15");
		
		JButton btnSalir = new JButton("Salir");
		frmMenuBuscaminas.getContentPane().add(btnSalir, "2, 17");
	}
	
	public void setVisible(boolean pB){
		frmMenuBuscaminas.setVisible(pB);
	}

}
