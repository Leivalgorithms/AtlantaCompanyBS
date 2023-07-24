package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Statement;

import Modelo.Conexion;
import Modelo.LoginDAO;
import Modelo.login;

import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.awt.event.ActionEvent;


public class Login extends  javax.swing.JFrame {
	
	login lg = new login();
    LoginDAO login = new LoginDAO();
	public JPanel contentPane;
	public JTextField txtCorreo;
	public JPasswordField txtPass;

    Conexion cc=new Conexion();
    Connection con=cc.getConnection();
	/**
	 * Launch the application.
	 */
	
    
    
    	   public void validar(){
    	        String correo = txtCorreo.getText();
    	        String pass = String.valueOf(txtPass.getPassword());
    	        if (!"".equals(correo) && !"".equals(pass)) {
    	            
    	            lg = login.log(correo, pass);
    	            if (lg.getCorreo()!= null && lg.getPass() != null) {
    	                Systema sis = new Systema(lg);
    	                sis.setVisible(true);
    	                dispose();
    	            }else{
    	                JOptionPane.showMessageDialog(null, "Correo o la Contraseña incorrecta");
    	            }
    	        }
    	    }	
    	
    	
    	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	
	public Login() {
		
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
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(121, 38, 100, 71);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/Img/iniciar.png")));
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(35, 132, 160, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(35, 224, 160, 14);
		panel_1.add(lblNewLabel_3);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(35, 180, 289, 20);
		panel_1.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(35, 253, 289, 20);
		panel_1.add(txtPass);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validar();
			}
		});
		btnNewButton.setBackground(new Color(150, 206, 19));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(121, 300, 100, 36);
		panel_1.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(150, 206, 19));
		panel.setBounds(0, 0, 422, 494);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/Img/logo.png")));
		lblNewLabel_4.setBounds(55, 11, 199, 171);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Sales System Management");
		lblNewLabel_5.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 17));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(27, 193, 267, 61);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(55, 277, 216, 164);
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
