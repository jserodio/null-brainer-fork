package packVentanas;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;

import packExcepciones.ExcepcionConectarBD;
import packGestores.GestorBuscaminas;
import packGestores.GestorRetos;

import javax.swing.JButton;

public class VRetar extends JFrame{
	private JTextField textField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VRetar frame = new VRetar(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VRetar(boolean arg) {
		if (arg){
			JOptionPane.showMessageDialog(null, "Vuelvelo a intentar");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 250, 200);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblquieresRetarA = new JLabel("\u00BFQuieres Retar a alguien?");
		lblquieresRetarA.setFont(new Font("Tahoma", Font.ITALIC, 30));
		GridBagConstraints gbc_lblquieresRetarA = new GridBagConstraints();
		gbc_lblquieresRetarA.anchor = GridBagConstraints.WEST;
		gbc_lblquieresRetarA.gridwidth = 3;
		gbc_lblquieresRetarA.insets = new Insets(0, 0, 5, 0);
		gbc_lblquieresRetarA.gridx = 1;
		gbc_lblquieresRetarA.gridy = 0;
		getContentPane().add(lblquieresRetarA, gbc_lblquieresRetarA);
		
		JLabel lblIntroduzcaElNombre = new JLabel("Introduzca el nombre");
		GridBagConstraints gbc_lblIntroduzcaElNombre = new GridBagConstraints();
		gbc_lblIntroduzcaElNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblIntroduzcaElNombre.gridx = 1;
		gbc_lblIntroduzcaElNombre.gridy = 3;
		getContentPane().add(lblIntroduzcaElNombre, gbc_lblIntroduzcaElNombre);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnRetar = new JButton("Retar");
		GridBagConstraints gbc_btnRetar = new GridBagConstraints();
		gbc_btnRetar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRetar.gridx = 1;
		gbc_btnRetar.gridy = 7;
		getContentPane().add(btnRetar, gbc_btnRetar);
		btnRetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GestorBuscaminas.getGestorBuscaminas().retar(textField.getText());
				} catch (ExcepcionConectarBD | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 7;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

}
