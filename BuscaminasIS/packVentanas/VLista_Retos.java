package packVentanas;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import packCodigo.Ranking;
import packCodigo.Reto;
import packExcepciones.ExcepcionConectarBD;
import packGestores.GestorBuscaminas;
import packGestores.GestorRetos;
import packGestores.GestorUsuarios;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;

public class VLista_Retos extends JFrame{
		int reto;
		private static VLista_Retos ventana;
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VLista_Retos window = new VLista_Retos();
						window.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		public static VLista_Retos getVentana() throws ExcepcionConectarBD, SQLException{
			if(ventana == null){
				ventana = new VLista_Retos();
			}
			return ventana;
		}
		
		private VLista_Retos() throws ExcepcionConectarBD, SQLException {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			getContentPane().setLayout(gridBagLayout);
			
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
			gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsuario.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblUsuario.gridx = 2;
			gbc_lblUsuario.gridy = 0;
			getContentPane().add(lblUsuario, gbc_lblUsuario);
			
			JLabel lblPuntuacin = new JLabel("Puntuaci\u00F3n");
			lblPuntuacin.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblPuntuacin = new GridBagConstraints();
			gbc_lblPuntuacin.insets = new Insets(0, 0, 5, 5);
			gbc_lblPuntuacin.gridx = 3;
			gbc_lblPuntuacin.gridy = 0;
			getContentPane().add(lblPuntuacin, gbc_lblPuntuacin);
			
			
			ArrayList<Reto> listaRetos = GestorRetos.getGestorRetos().getListaRetos();
			int x = 0;
			if(x<listaRetos.size()) {
				JLabel lblUsuario_1 = new JLabel(GestorUsuarios.getGestorUsuarios().buscarNombre(listaRetos.get(x).getUsuarioRetador()));
				GridBagConstraints gbc_lblUsuario_1 = new GridBagConstraints();
				gbc_lblUsuario_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblUsuario_1.gridx = 2;
				gbc_lblUsuario_1.gridy = 2+x;
				getContentPane().add(lblUsuario_1, gbc_lblUsuario_1);
				
				JLabel lblPuntuacion = new JLabel(""+listaRetos.get(x).getPuntuacion());
				GridBagConstraints gbc_lblPuntuacion = new GridBagConstraints();
				gbc_lblPuntuacion.insets = new Insets(0, 0, 5, 5);
				gbc_lblPuntuacion.gridx = 3;
				gbc_lblPuntuacion.gridy = 2+x;
				getContentPane().add(lblPuntuacion, gbc_lblPuntuacion);
				
				JButton btnRechazar = new JButton("Rechazar");
				GridBagConstraints gbc_btnRechazar = new GridBagConstraints();
				gbc_btnRechazar.insets = new Insets(0, 0, 5, 5);
				gbc_btnRechazar.gridx = 4;
				gbc_btnRechazar.gridy = 2+x;
				getContentPane().add(btnRechazar, gbc_btnRechazar);
				btnRechazar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							VLista_Retos.getVentana().rechazar(1);
						} catch (ExcepcionConectarBD | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
				JButton btnAceptar = new JButton("Aceptar");
				GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
				gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
				gbc_btnAceptar.gridx = 5;
				gbc_btnAceptar.gridy = 2+x;
				getContentPane().add(btnAceptar, gbc_btnAceptar);
				btnRechazar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							VLista_Retos.getVentana().aceptar(1);
						} catch (ExcepcionConectarBD | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					}
				});
			}
			x = x + 1;
			if(x<listaRetos.size()) {
				JLabel lblUsuario_2 = new JLabel(GestorUsuarios.getGestorUsuarios().buscarNombre(listaRetos.get(x).getUsuarioRetador()));
				GridBagConstraints gbc_lblUsuario_2 = new GridBagConstraints();
				gbc_lblUsuario_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblUsuario_2.gridx = 2;
				gbc_lblUsuario_2.gridy = 2+x;
				getContentPane().add(lblUsuario_2, gbc_lblUsuario_2);
				
				JLabel lblPuntuacion2 = new JLabel(""+listaRetos.get(x).getPuntuacion());
				GridBagConstraints gbc_lblPuntuacion2 = new GridBagConstraints();
				gbc_lblPuntuacion2.insets = new Insets(0, 0, 5, 5);
				gbc_lblPuntuacion2.gridx = 3;
				gbc_lblPuntuacion2.gridy = 2+x;
				getContentPane().add(lblPuntuacion2, gbc_lblPuntuacion2);
				
				JButton btnRechazar2 = new JButton("Rechazar");
				GridBagConstraints gbc_btnRechazar2 = new GridBagConstraints();
				gbc_btnRechazar2.insets = new Insets(0, 0, 5, 5);
				gbc_btnRechazar2.gridx = 4;
				gbc_btnRechazar2.gridy = 2+x;
				getContentPane().add(btnRechazar2, gbc_btnRechazar2);
				btnRechazar2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							VLista_Retos.getVentana().rechazar(2);
						} catch (ExcepcionConectarBD | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
				JButton btnAceptar2 = new JButton("Aceptar");
				GridBagConstraints gbc_btnAceptar2 = new GridBagConstraints();
				gbc_btnAceptar2.insets = new Insets(0, 0, 5, 5);
				gbc_btnAceptar2.gridx = 5;
				gbc_btnAceptar2.gridy = 2+x;
				getContentPane().add(btnAceptar2, gbc_btnAceptar2);
				btnRechazar2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							VLista_Retos.getVentana().aceptar(2);
						} catch (ExcepcionConectarBD | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			x = x + 1;
			if(x<listaRetos.size()) {
				JLabel lblUsuario_3 = new JLabel(GestorUsuarios.getGestorUsuarios().buscarNombre(listaRetos.get(x).getUsuarioRetador()));
				GridBagConstraints gbc_lblUsuario_3 = new GridBagConstraints();
				gbc_lblUsuario_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblUsuario_3.gridx = 2;
				gbc_lblUsuario_3.gridy = 2+x;
				getContentPane().add(lblUsuario_3, gbc_lblUsuario_3);
				
				JLabel lblPuntuacion3 = new JLabel(""+listaRetos.get(x).getPuntuacion());
				GridBagConstraints gbc_lblPuntuacion3 = new GridBagConstraints();
				gbc_lblPuntuacion3.insets = new Insets(0, 0, 5, 5);
				gbc_lblPuntuacion3.gridx = 3;
				gbc_lblPuntuacion3.gridy = 2+x;
				getContentPane().add(lblPuntuacion3, gbc_lblPuntuacion3);
				
				JButton btnRechazar3 = new JButton("Rechazar");
				GridBagConstraints gbc_btnRechazar3 = new GridBagConstraints();
				gbc_btnRechazar3.insets = new Insets(0, 0, 5, 5);
				gbc_btnRechazar3.gridx = 4;
				gbc_btnRechazar3.gridy = 2+x;
				getContentPane().add(btnRechazar3, gbc_btnRechazar3);
				btnRechazar3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							VLista_Retos.getVentana().rechazar(3);
						} catch (ExcepcionConectarBD | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
				JButton btnAceptar3 = new JButton("Aceptar");
				GridBagConstraints gbc_btnAceptar3 = new GridBagConstraints();
				gbc_btnAceptar3.insets = new Insets(0, 0, 5, 5);
				gbc_btnAceptar3.gridx = 5;
				gbc_btnAceptar3.gridy = 2+x;
				getContentPane().add(btnAceptar3, gbc_btnAceptar3);
				btnRechazar3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							VLista_Retos.getVentana().aceptar(3);
						} catch (ExcepcionConectarBD | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			x = x + 1;
			if(x<listaRetos.size()) {
				JLabel lblUsuario_4 = new JLabel(GestorUsuarios.getGestorUsuarios().buscarNombre(listaRetos.get(x).getUsuarioRetador()));
				GridBagConstraints gbc_lblUsuario_4 = new GridBagConstraints();
				gbc_lblUsuario_4.insets = new Insets(0, 0, 5, 5);
				gbc_lblUsuario_4.gridx = 2;
				gbc_lblUsuario_4.gridy = 2+x;
				getContentPane().add(lblUsuario_4, gbc_lblUsuario_4);
				
				JLabel lblPuntuacion4 = new JLabel(""+listaRetos.get(x).getPuntuacion());
				GridBagConstraints gbc_lblPuntuacion4 = new GridBagConstraints();
				gbc_lblPuntuacion4.insets = new Insets(0, 0, 5, 5);
				gbc_lblPuntuacion4.gridx = 3;
				gbc_lblPuntuacion4.gridy = 2+x;
				getContentPane().add(lblPuntuacion4, gbc_lblPuntuacion4);
				
				JButton btnRechazar4 = new JButton("Rechazar");
				GridBagConstraints gbc_btnRechazar4 = new GridBagConstraints();
				gbc_btnRechazar4.insets = new Insets(0, 0, 5, 5);
				gbc_btnRechazar4.gridx = 4;
				gbc_btnRechazar4.gridy = 2+x;
				getContentPane().add(btnRechazar4, gbc_btnRechazar4);
				btnRechazar4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							VLista_Retos.getVentana().rechazar(4);
						} catch (ExcepcionConectarBD | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
					}
				});
				
				JButton btnAceptar4 = new JButton("Aceptar");
				GridBagConstraints gbc_btnAceptar4 = new GridBagConstraints();
				gbc_btnAceptar4.insets = new Insets(0, 0, 5, 5);
				gbc_btnAceptar4.gridx = 5;
				gbc_btnAceptar4.gridy = 2+x;
				getContentPane().add(btnAceptar4, gbc_btnAceptar4);
				btnRechazar4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							VLista_Retos.getVentana().aceptar(4);
						} catch (ExcepcionConectarBD | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			
			JButton btnVolver = new JButton("Volver");
			GridBagConstraints gbc_btnVolver = new GridBagConstraints();
			gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
			gbc_btnVolver.gridx = 5;
			gbc_btnVolver.gridy = 7+x;
			getContentPane().add(btnVolver, gbc_btnVolver);
			
	}
	public void rechazar(int x) throws ExcepcionConectarBD, SQLException{
		ArrayList<Reto> listaRetos = GestorRetos.getGestorRetos().getListaRetos();
		GestorRetos.getGestorRetos().eliminarReto(listaRetos.get(x).getCodReto());
	}
	public void aceptar(int x) throws ExcepcionConectarBD, SQLException{
		ArrayList<Reto> listaRetos = GestorRetos.getGestorRetos().getListaRetos();
		GestorBuscaminas.getGestorBuscaminas().iniciarPartidaReto(listaRetos.get(x).getCodReto());
	}
}
