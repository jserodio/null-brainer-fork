package packVentanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import packGestores.GestorBuscaminas;
import packGestores.GestorSesion;
import packGestores.GestorUsuarios;
import packCodigo.Usuario;
import packExcepciones.ExcepcionConectarBD;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class IU_IdRg extends JFrame{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		// ventana
		private JPanel contentPane;
		
		// controles del formulario
		private JTextField txtNombreReg;
		private JLabel cargandoNombreId;
		private JButton btnIdentificarse;
		private JLabel btnNombre;
		private JLabel lblcontraReg;
		private JTextField txtPassReg;
		private JTextField txtPassConfReg;
		//private JTextField txtRepiteLaContrasea;
		
		// variables
//		private boolean txtchange = true;
		private boolean dentro = false;
		private boolean cargarventana = false;
		int color;
		private JLabel lblNombreId;
		private JLabel lblPassId;
		private JTextField txtNombreId;
		private JPasswordField txtPassId;
		private JTextField txtEmail;
		private JLabel btnRecuperarContrasea;
		private JButton btnRegistrarse;
		private JLabel lblIdentificacion;
		private JLabel lblRegistro;
		private JLabel lblEmail;
		private JLabel cargandoPassId;
		private JLabel lblInformacion;
		private JLabel cargando;

		
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						IU_IdRg frame = new IU_IdRg();
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
		public IU_IdRg() {
			
			// Iniciando ventana
			setUndecorated(true);
			setBounds(100, 100, 1280, 850);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			setLocationRelativeTo(null);
			setVisible(true);
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			contentPane.add(panel, BorderLayout.CENTER);
			
			
			// Más colores
			Random rand = new Random();
			final float R = rand.nextFloat() / 2f;
			final float G = rand.nextFloat() / 2f;
			final float B = rand.nextFloat();
			Color colorAzulado = new Color(R, G, B);

			panel.setLayout(new MigLayout("", "[50,grow][151.00,grow][200px,grow][75][151.00,grow][200px,grow][50,grow]", "[100.00][100.00,grow][60:n][25px][60:n][grow][25px][34.00,grow][21.00,grow][100px,grow]"));
			
			// Inicializo la carga
			
			gestionNombre();
			
			// Controles
			
			txtNombreReg = new JTextField();
			txtNombreReg.setText("Introduce tu nombre");
			txtNombreReg.setHorizontalAlignment(SwingConstants.CENTER);
			txtNombreReg.setPreferredSize(new Dimension(6, 55));
			txtNombreReg.setMinimumSize(new Dimension(6, 55));
			txtNombreReg.setForeground(new Color(255, 255, 255));
			txtNombreReg.setBackground(colorAzulado);
			txtNombreReg.setBounds(100, 100, 190, 40);
			txtNombreReg.setBorder(null);
			txtNombreReg.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtNombreReg.setColumns(10);
			txtNombreReg.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					txtNombreReg.setText("");
				}
			});
			
			cargandoNombreId = new JLabel("");
			cargandoNombreId.setIcon(null);//el de los cuatro colores
			cargandoNombreId.setBounds(100, 100, 68, 40);
			panel.add(cargandoNombreId, "cell 3 2,alignx center,aligny center");
			panel.add(txtNombreReg, "cell 5 2,growx,aligny center");
			
			lblIdentificacion = new JLabel("IDENTIFICACI\u00D3N");
			lblIdentificacion.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblIdentificacion.setForeground(colorAzulado);
			panel.add(lblIdentificacion, "cell 1 1 2 1,alignx center,aligny bottom");
			
			lblRegistro = new JLabel("REGISTRO");
			lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblRegistro.setForeground(colorAzulado);
			panel.add(lblRegistro, "cell 4 1 2 1,alignx center,aligny bottom");
			
			lblNombreId = new JLabel("Nombre");
			lblNombreId.setBackground(Color.WHITE);
			lblNombreId.setFont(new Font("Tahoma", Font.PLAIN, 11));
			panel.add(lblNombreId, "cell 1 2,alignx center,aligny center");
			
			txtNombreId = new JTextField();
			txtNombreId.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					txtPassId.grabFocus();
				}
			});
			txtNombreId.setHorizontalAlignment(SwingConstants.CENTER);
			txtNombreId.setText("Introduce tu nombre");
			txtNombreId.setPreferredSize(new Dimension(6, 55));
			txtNombreId.setMinimumSize(new Dimension(6, 55));
			txtNombreId.setForeground(Color.WHITE);
			txtNombreId.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtNombreId.setColumns(10);
			txtNombreId.setBorder(null);
			txtNombreId.setBackground(colorAzulado);
			txtNombreId.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					txtNombreId.setText("");
			}
			});
			panel.add(txtNombreId, "cell 2 2,growx");
			
			btnNombre = new JLabel("Nombre");
			btnNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
			panel.add(btnNombre, "cell 4 2,alignx center");
			
			lblPassId = new JLabel("Contraseña");
			lblPassId.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblPassId.setBackground(Color.WHITE);
			panel.add(lblPassId, "cell 1 4,alignx center");
			
			txtPassId = new JPasswordField();
			txtPassId.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtPassId.setPreferredSize(new Dimension(6, 55));
			txtPassId.setHorizontalAlignment(SwingConstants.CENTER);
			txtPassId.setForeground(Color.WHITE);
			txtPassId.setColumns(10);
			txtPassId.setBorder(null);
			txtPassId.setBackground(colorAzulado);
			panel.add(txtPassId, "cell 2 4,growx");
			
			cargandoPassId = new JLabel("");
			cargandoPassId.setIcon(null);
			panel.add(cargandoPassId, "cell 3 4");
			
			lblcontraReg = new JLabel("Contraseña");
			panel.add(lblcontraReg, "cell 4 4,alignx center");
			
			txtPassReg = new JPasswordField();
			txtPassReg.setPreferredSize(new Dimension(6, 55));
			txtPassReg.setHorizontalAlignment(SwingConstants.CENTER);
			txtPassReg.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtPassReg.setForeground(Color.WHITE);
			txtPassReg.setColumns(10);
			txtPassReg.setBorder(null);
			txtPassReg.setBackground(colorAzulado);
			panel.add(txtPassReg, "cell 5 4,growx,aligny center");;
			
			
			txtPassConfReg = new JPasswordField();
			txtPassConfReg.setPreferredSize(new Dimension(6, 55));
			txtPassConfReg.setHorizontalAlignment(SwingConstants.CENTER);
			txtPassConfReg.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtPassConfReg.setForeground(Color.WHITE);
			txtPassConfReg.setColumns(10);
			txtPassConfReg.setBorder(null);
			txtPassConfReg.setBackground(colorAzulado);
			panel.add(txtPassConfReg, "cell 5 5,growx");
			
			txtEmail = new JTextField();
			txtEmail.setText("Introduce tu Email");
			txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
			txtEmail.setPreferredSize(new Dimension(6, 55));
			txtEmail.setMinimumSize(new Dimension(6, 55));
			txtEmail.setForeground(Color.WHITE);
			txtEmail.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtEmail.setColumns(10);
			txtEmail.setBorder(null);
			txtEmail.setBackground(colorAzulado);
			txtEmail.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					txtEmail.setText("");
				}
			});
			
			
			btnIdentificarse = new JButton("Identificarse");
			panel.add(btnIdentificarse, "cell 2 7,alignx center,aligny center");
			btnIdentificarse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String user = txtNombreId.getText();
					String pass = new String(txtPassId.getPassword());
					Boolean continua = true;
					
					// Cargando
					cargando.setIcon(null);
					dentro=true;
					
					// Campo usuario vacio
					if(!(user.equals("") || user.equals("Introduce tu nombre"))){
						cargandoNombreId.setIcon(new ImageIcon(IU_IdRg.class.getResource("/ok/btnTick.png")));
						if (!pass.equals("")){
							cargandoPassId.setIcon(new ImageIcon(IU_IdRg.class.getResource("/ok/btnTick.png")));
						}else{
							cargandoPassId.setIcon(new ImageIcon(IU_IdRg.class.getResource("/ok/btnX.png")));
							lblInformacion.setText("La contraseña no puede estar vacía en Identificación");
							continua = false;
						}
					}else{
						cargandoNombreId.setIcon(new ImageIcon(IU_IdRg.class.getResource("/ok/btnX.png")));
						lblInformacion.setText("Revisa el campo nombre de Identificación");
						continua = false;
					}
					
					if (continua){
					// Si todo ha ido bien, y no hay ninguna X roja.
						//IDENTIFICARSE Introducir el mio
						try {
							boolean correcto=GestorBuscaminas.getGestorBuscaminas().identificarse(user, pass);
							if(correcto){
								lblInformacion.setText("Todo correcto");
								dentro=false;
								cargando.setIcon(new ImageIcon(IU_IdRg.class.getResource("/ok/btnTick.png")));
								setEnabled(false);
								cargarventana = true;
							}
							else{
								lblInformacion.setText("Este usuario no ha sido registrado aún o la contraseña que ha introducido es incorrecta");
							}
							

						} catch(SQLException e1){
							e1.printStackTrace();
							System.out.println("Algo ha fallado");
					} catch (ExcepcionConectarBD e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					
					// Cargo IU_Buscaminas si todo ha ido bien
					if (cargarventana){					
								// Carga la ventana IU_Buscaminas
								IU_Buscaminas window = new IU_Buscaminas();
								window.setVisible(true);
							    setVisible(false);
					}

				}

			}});
			btnIdentificarse.setHorizontalAlignment(SwingConstants.TRAILING);
			
			lblEmail = new JLabel("EMAIL");
			lblEmail.setFont(new Font("Dialog", Font.PLAIN, 20));
			lblEmail.setBackground(Color.WHITE);
			panel.add(lblEmail, "cell 4 7,alignx center,aligny center");
			panel.add(txtEmail, "cell 5 7,growx");
			
			btnRegistrarse = new JButton("Registrarse");
			btnRegistrarse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					/*
					 * TO-DO:	Comprobar campos no nulos
					 * 			Llamar al método: registrarse (user, password, confirmedPass, email) 
					 */
					String user = txtNombreReg.getText();
					String pass = txtPassReg.getText();
					String confirmedPass = txtPassConfReg.getText();
					String email = txtEmail.getText();
					Boolean continua = true;
					btnRegistrarse.setEnabled(false);
					
					// Cargando
					cargando.setIcon(null);
					dentro=true;
					
					// Vacio la información residual
					txtNombreId.setText("");
					txtPassId.setText("");
					cargandoNombreId.setIcon(null);
					cargandoPassId.setIcon(null);
					lblInformacion.setText("");
					cargando.setIcon(null);
					
					// Campo usuario vacio
					if(!(user.equals("") || user.equals("Introduce tu nombre"))){
						if (!pass.equals(confirmedPass) || pass.equals("")){
							// Si las contraseñas no coinciden, no continua y le dice error.
							lblInformacion.setText("Las contraseñas de Registro no coinciden o estan vacias.");
							cargando.setIcon(new ImageIcon(IU_IdRg.class.getResource("/ok/btnX.png")));
							continua = false;
							dentro=false;
							btnRegistrarse.setEnabled(true);
						}else{
							if (email.equals("") || email.equals("Introduce tu Email")){
								lblInformacion.setText("El campo de email está vacio.");
								cargando.setIcon(new ImageIcon(IU_IdRg.class.getResource("/ok/btnX.png")));
								continua = false;
								dentro=false;
								btnRegistrarse.setEnabled(true);
							}
						}
					}else{
						// Nombre de usuario de registro vacio
						lblInformacion.setText("Verifica que el campo nombre de Registro no este vacío.");
						cargando.setIcon(new ImageIcon(IU_IdRg.class.getResource("/ok/btnX.png")));
						continua = false;
						dentro=false;
						btnRegistrarse.setEnabled(true);
					}
					
					if (continua){
						dentro = true;
						try {
							//REGISTRARSE Introducir el mio
							boolean correct=GestorBuscaminas.getGestorBuscaminas().registrarse(user, pass, confirmedPass, email);
							if(correct){
								lblInformacion.setText("Muy bien "+user+", has sido registrado como Jugador");
								Thread.sleep(200);
								dentro=false;
								cargando.setIcon(new ImageIcon(IU_IdRg.class.getResource("/ok/btnTick.png"))); 
							}
							else{
								lblInformacion.setText("¡Vaya! "+user+" parece que ya estás registrado.");
								Thread.sleep(200);
								dentro=false;
								cargando.setIcon(new ImageIcon(IU_IdRg.class.getResource("/ok/btnX.png")));
							}
							btnRegistrarse.setEnabled(true);
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						} catch (ExcepcionConectarBD e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			
			lblInformacion = new JLabel("");
			panel.add(lblInformacion, "cell 1 8 2 1,alignx center,aligny center");
			lblInformacion.setFont(new Font("Dialog", Font.BOLD, 14));
			lblInformacion.setForeground(colorAzulado);
			
			cargando = new JLabel("");
			cargando.setIcon(null);
			panel.add(cargando, "cell 3 8,alignx center,aligny bottom");
			
			
			panel.add(btnRegistrarse, "cell 5 8,alignx center,aligny center");
		}
		
		private void gestionNombre(){
			Timer timer;
			TimerTask  timerTask = new TimerTask() {
				int p =0;
				@Override
				public void run() {
					try{
			    		 Thread.sleep(200); 
			    	  }catch (Exception e) {}
					if(p==4){
						p=0;
					}
					if(dentro){
						switch (p) {
					      case 0:
					    	  cargando.setIcon(new ImageIcon(IU_IdRg.class.getResource("/load/Cargar1.png")));
					          p++;
					    	  break;
					      case 1:
					    	  cargando.setIcon(new ImageIcon(IU_IdRg.class.getResource("/load/Cargar2.png")));
					           p++;
					    	  break;
					      case 2:
					    	  cargando.setIcon(new ImageIcon(IU_IdRg.class.getResource("/load/Cargar3.png")));
					           p++;
					    	  break;
					      case 3:
					    	  cargando.setIcon(new ImageIcon(IU_IdRg.class.getResource("/load/Cargar4.png")));
					           p++;
					    	  break;
					      default:

					           break;
					      }
					}
				}		
			};
			timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, 50);
		}

}
