package Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.LoginDAO;
import Modelo.login;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registro extends JFrame {
	
	login lg = new login();
    LoginDAO login = new LoginDAO();
	private JPanel contentPane;
	private JTextField txtCorreoRegistro;
	private JTextField txtNombreRegistro;
	private JPasswordField txtPassRegistro; 
	private JComboBox cbxRolRegistro;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	
	
	   public void validar(){
	        String correo = txtCorreoRegistro.getText();
	        String pass = String.valueOf(txtPassRegistro.getPassword());
	        String nom = txtNombreRegistro.getText();
	        String rol = cbxRolRegistro.getSelectedItem().toString();
	        if (!"".equals(correo) || !"".equals(pass) || !"".equals(nom)) {
	            lg.setNombre(nom);
	            lg.setCorreo(correo);
	            lg.setPass(pass);
	            lg.setRol(rol);
	            login.Registrar(lg);
	            Login iniz = new Login();
	            iniz.setVisible(true);
	            dispose();
	        }
	    }
	
	
	public Registro() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(322, 11, 346, 453);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(35, 132, 160, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(35, 206, 160, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nombre");
		lblNewLabel_3_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(35, 274, 160, 14);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Rol");
		lblNewLabel_3_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3_2.setBounds(35, 342, 160, 14);
		panel_1.add(lblNewLabel_3_2);
		
		txtCorreoRegistro = new JTextField();
		txtCorreoRegistro.setBounds(35, 157, 264, 20);
		panel_1.add(txtCorreoRegistro);
		txtCorreoRegistro.setColumns(10);
		
		txtNombreRegistro = new JTextField();
		txtNombreRegistro.setColumns(10);
		txtNombreRegistro.setBounds(35, 299, 264, 20);
		panel_1.add(txtNombreRegistro);
		
		txtPassRegistro = new JPasswordField();
		txtPassRegistro.setBounds(35, 231, 264, 20);
		panel_1.add(txtPassRegistro);
		
		cbxRolRegistro = new JComboBox();
		cbxRolRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbxRolRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxRolRegistro.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Asistente"}));
		cbxRolRegistro.setBounds(35, 383, 264, 22);
		panel_1.add(cbxRolRegistro);
		
		JLabel lblNewLabel_5 = new JLabel("User Registration");
		lblNewLabel_5.setBounds(32, 39, 267, 61);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
				
			}
		});
		btnNewButton.setBounds(10, 419, 89, 23);
		panel_1.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(150, 206, 19));
		panel.setBounds(0, 0, 422, 494);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/Img/logo.png")));
		lblNewLabel_4.setBounds(77, 138, 199, 171);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(86, 138, 216, 164);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Internal Use Only");
		lblNewLabel_7.setBounds(10, 469, 112, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Img/login.png")));
		lblNewLabel.setBounds(423, 0, 311, 494);
		contentPane.add(lblNewLabel);
	}
}
