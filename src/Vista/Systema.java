package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.UIManager;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Event;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JComboBox;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Conexion;
import Modelo.Config;
import Modelo.Detalle;
import Modelo.Eventos;
import Modelo.LoginDAO;
import Modelo.Productos;
import Modelo.ProductosDAO;
import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import Modelo.login;
import Reports.Excel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Systema extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigoVenta;
	private JTextField txtDescripcionVenta;
	private JTextField txtCantidadVenta;
	private JTextField txtPrecioVenta;
	private JTextField txtStockDisponible;
	private JTable TableVenta;
	private JTextField txtRucVenta;
	private JTextField txtNombreClienteVenta;
	private JTextField txtDniCliente;
	private JTextField txtNombreCliente;
	private JTextField txtTelefonoCliente;
	private JTextField txtDireccionCliente;
	private JTextField txtRazonCliente;
	private JTable TableCliente;
	private JTextField txtRucProveedor;
	private JTextField txtNombreProveedor;
	private JTextField txtTelefonoProveedor;
	private JTextField txtDireccionProveedor;
	private JTextField txtRazonProveedor;
	private JTable TableProveedor;
	private JTextField txtCodigoPro;
	private JTextField txtDesPro;
	private JTextField txtCantPro;
	private JTextField txtPrecioPro;
	private JTable TableProducto;
	private JTable TableVentas;
	private JTextField txtRucConfig;
	private JTextField txtNombreConfig;
	private JTextField txtTelefonoConfig;
	private JTextField txtDireccionConfig;
	private JTextField txtRazonConfig;
	private JTextField txtTelefonoClienteVenta;
	private JTextField txtDireccionClienteVenta;
	private JTextField txtRazonClienteVenta;
	private JTextField txtIdCliente;
	private JTextField txtIdProveedor;
	private JTextField txtIdpro;
	private JTextField txtIdVenta1;
	private JTextField txtIdPro;

	/**
	 * Launch the application.
	 */

	Cliente cl = new Cliente();
	ClienteDAO client = new ClienteDAO();
	DefaultTableModel modelo = new DefaultTableModel();
	DefaultTableModel tmp = new DefaultTableModel();
	Proveedor pr = new Proveedor();
	ProveedorDAO PrDao = new ProveedorDAO();
	Productos pro = new Productos();
	ProductosDAO prodao = new ProductosDAO();
	Venta v = new Venta();
	VentaDAO vdao = new VentaDAO();
	Detalle Dv = new Detalle();
	Config conf = new Config();
	Eventos event = new Eventos();
	private JComboBox<?> cbxProveedorPro;
	int item;
	double Totalpagar = 00.0;
	double palabel = 00.0;
	private JLabel labelTotal;
	private JLabel LabelVendedor;
	private JTextField txtIdConfig;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Systema frame = new Systema();
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

	public void ListarCliente() {
		List<Cliente> ListarCl = client.ListarCliente();
		modelo = (DefaultTableModel) TableCliente.getModel();
		Object[] ob = new Object[6];
		for (int i = 0; i < ListarCl.size(); i++) {
			ob[0] = ListarCl.get(i).getId();
			ob[1] = ListarCl.get(i).getDni();
			ob[2] = ListarCl.get(i).getNombre();
			ob[3] = ListarCl.get(i).getTelefono();
			ob[4] = ListarCl.get(i).getDireccion();
			ob[5] = ListarCl.get(i).getRazon();
			modelo.addRow(ob);
		}
		TableCliente.setModel(modelo);
	}

	public void ListarProveedor() {
		List<Proveedor> ListarPr = PrDao.ListarProveedor();
		modelo = (DefaultTableModel) TableProveedor.getModel();
		Object[] ob = new Object[6];
		for (int i = 0; i < ListarPr.size(); i++) {
			ob[0] = ListarPr.get(i).getId();
			ob[1] = ListarPr.get(i).getRuc();
			ob[2] = ListarPr.get(i).getNombre();
			ob[3] = ListarPr.get(i).getTelefono();
			ob[4] = ListarPr.get(i).getDireccion();
			ob[5] = ListarPr.get(i).getRazon();
			modelo.addRow(ob);
		}
		TableProveedor.setModel(modelo);
	}

	public void ListarProductos() {
		List<Productos> ListarPro = prodao.ListarProductos();
		modelo = (DefaultTableModel) TableProducto.getModel();
		Object[] ob = new Object[6];
		for (int i = 0; i < ListarPro.size(); i++) {
			ob[0] = ListarPro.get(i).getId();
			ob[1] = ListarPro.get(i).getCodigo();
			ob[2] = ListarPro.get(i).getNombre();
			ob[3] = ListarPro.get(i).getProveedor();
			ob[4] = ListarPro.get(i).getStock();
			ob[5] = ListarPro.get(i).getPrecio();
			modelo.addRow(ob);
		}
		TableProducto.setModel(modelo);
	}

	public void ListarConfig() {

		conf = prodao.BuscarDatos();
		txtIdConfig.setText("" + conf.getId());
		txtRucConfig.setText("" + conf.getRuc());
		txtNombreConfig.setText("" + conf.getNombre());
		txtTelefonoConfig.setText("" + conf.getTelefono());
		txtDireccionConfig.setText("" + conf.getDireccion());
		txtRazonConfig.setText("" + conf.getRazon());

	}
	
	
	public void ListarVentas() {
		List<Venta> ListarVenta = vdao.ListarVentas();
		modelo = (DefaultTableModel) TableVentas.getModel();
		Object[] ob = new Object[4];
		for (int i = 0; i < ListarVenta.size(); i++) {
			ob[0] = ListarVenta.get(i).getId();
			ob[1] = ListarVenta.get(i).getCliente();
			ob[2] = ListarVenta.get(i).getVendedor();
			ob[3] = ListarVenta.get(i).getTotal();

			modelo.addRow(ob);
		}
		TableVentas.setModel(modelo);
	}

	public void LimpiarTable() {
		for (int i = 0; i < modelo.getRowCount(); i++) {
			modelo.removeRow(i);
			i = i - 1;
		}
	}

	public Systema(login priv) {
		
		
		if(priv.getRol().equals("Asistente")) {
			
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1314, 836);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(186, 304, 1112, 518);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Product Code");
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(33, 22, 100, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Description");
		lblNewLabel_2_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(184, 22, 74, 14);
		panel_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Quantity");
		lblNewLabel_2_2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lblNewLabel_2_2.setBounds(367, 22, 68, 14);
		panel_1.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Value");
		lblNewLabel_2_3.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lblNewLabel_2_3.setBounds(527, 22, 46, 14);
		panel_1.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel("Stock");
		lblNewLabel_2_4.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lblNewLabel_2_4.setBounds(701, 22, 46, 14);
		panel_1.add(lblNewLabel_2_4);

		JButton btnEliminarVenta = new JButton("");
		btnEliminarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo = (DefaultTableModel) TableVenta.getModel();
				modelo.removeRow(TableVenta.getSelectedRow());
				TotalPagar();
				txtCodigoVenta.requestFocus();
			}
		});
		btnEliminarVenta.setIcon(new ImageIcon(Systema.class.getResource("/Img/eliminar.png")));
		btnEliminarVenta.setBounds(881, 29, 86, 38);
		panel_1.add(btnEliminarVenta);

		txtCodigoVenta = new JTextField();
		txtCodigoVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!"".equals(txtCodigoVenta.getText())) {
						String cod = txtCodigoVenta.getText();
						pro = prodao.BuscarPro(cod);
						if (pro.getNombre() != null) {
							txtDescripcionVenta.setText("" + pro.getNombre());
							txtPrecioVenta.setText("" + pro.getPrecio());
							txtStockDisponible.setText("" + pro.getStock());
							txtCantidadVenta.requestFocus();
						} else {
							LimpiarVenta();
							txtCodigoVenta.requestFocus();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please enter product code");
						txtCodigoVenta.requestFocus();
					}
				}

			}
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtCodigoVenta.setBounds(33, 47, 86, 20);
		panel_1.add(txtCodigoVenta);
		txtCodigoVenta.setColumns(10);

		txtDescripcionVenta = new JTextField();
		txtDescripcionVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtDescripcionVenta.setColumns(10);
		txtDescripcionVenta.setBounds(184, 47, 148, 20);
		panel_1.add(txtDescripcionVenta);

		txtCantidadVenta = new JTextField();
		txtCantidadVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!"".equals(txtCantidadVenta.getText())) {
						String cod = txtCodigoVenta.getText();
						String descripcion = txtDescripcionVenta.getText();
						int cant = Integer.parseInt(txtCantidadVenta.getText());
						double precio = Double.parseDouble(txtPrecioVenta.getText());
						double total = cant * precio;
						int stock = Integer.parseInt(txtStockDisponible.getText());
						if (stock >= cant) {
							item = item + 1;
							tmp = (DefaultTableModel) TableVenta.getModel();
							for (int i = 0; i < TableVenta.getRowCount(); i++) {
								if (TableVenta.getValueAt(i, 1).equals(txtDescripcionVenta.getText())) {
									JOptionPane.showMessageDialog(null, "Product already registered");
									return;
								}
							}
							ArrayList lista = new ArrayList();
							lista.add(item);
							lista.add(cod);
							lista.add(descripcion);
							lista.add(cant);
							lista.add(precio);
							lista.add(total);
							Object[] O = new Object[5];
							O[0] = lista.get(1);
							O[1] = lista.get(2);
							O[2] = lista.get(3);
							O[3] = lista.get(4);
							O[4] = lista.get(4);
							tmp.addRow(O);
							TableVenta.setModel(tmp);
							TotalPagar();
							LimpiarVenta();
							txtCodigoVenta.requestFocus();
						} else {
							JOptionPane.showMessageDialog(null, "Stock not available");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please enter a quantity");
					}
				}

			}
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtCantidadVenta.setColumns(10);
		txtCantidadVenta.setBounds(367, 47, 86, 20);
		panel_1.add(txtCantidadVenta);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setEditable(false);
		txtPrecioVenta.setColumns(10);
		txtPrecioVenta.setBounds(527, 47, 86, 20);
		panel_1.add(txtPrecioVenta);

		txtStockDisponible = new JTextField();
		txtStockDisponible.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtStockDisponible.setColumns(10);
		txtStockDisponible.setBounds(701, 47, 86, 20);
		panel_1.add(txtStockDisponible);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 1013, 289);
		panel_1.add(scrollPane);

		TableVenta = new JTable();
		TableVenta.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Product Code", "Description", "Quantity", "Value", "Total" }));
		scrollPane.setViewportView(TableVenta);

		JLabel lblNewLabel_3 = new JLabel("DNI/RUC");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 384, 86, 14);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("NAME");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(123, 382, 46, 14);
		panel_1.add(lblNewLabel_4);

		txtRucVenta = new JTextField();
		txtRucVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!"".equals(txtRucVenta.getText())) {
						int dni = Integer.parseInt(txtRucVenta.getText());
						cl = client.Buscarcliente(dni);
						if (cl.getNombre() != null) {
							txtNombreClienteVenta.setText("" + cl.getNombre());
							txtTelefonoClienteVenta.setText("" + cl.getTelefono());
							txtDireccionClienteVenta.setText("" + cl.getDireccion());
							txtRazonClienteVenta.setText("" + cl.getRazon());
						} else {
							txtRucVenta.setText("");
							JOptionPane.showMessageDialog(null, "Unknown Client");
						}
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtRucVenta.setBounds(10, 409, 86, 20);
		panel_1.add(txtRucVenta);
		txtRucVenta.setColumns(10);

		txtNombreClienteVenta = new JTextField();
		txtNombreClienteVenta.setEditable(false);
		txtNombreClienteVenta.setBounds(123, 410, 171, 20);
		panel_1.add(txtNombreClienteVenta);
		txtNombreClienteVenta.setColumns(10);

		JButton btnGenerarVenta = new JButton("");
		btnGenerarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TableVenta.getRowCount() > 0) {

					if (!"".equals(txtNombreClienteVenta.getText())) {

						RegistrarVenta();
						RegistrarDetalle();
						ActualizarStock();
						pdf();
						LimpiarTableVenta();
						LimpiarClienteVenta();
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Client");
					}

				}else {
					JOptionPane.showMessageDialog(null, "No Products in this sale");
				}

			}
		});
		btnGenerarVenta.setIcon(new ImageIcon(Systema.class.getResource("/Img/print.png")));
		btnGenerarVenta.setBounds(513, 395, 100, 57);
		panel_1.add(btnGenerarVenta);

		JLabel lblNewLabel_5 = new JLabel("Total:");
		lblNewLabel_5.setIcon(new ImageIcon(Systema.class.getResource("/Img/money.png")));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(752, 384, 100, 45);
		panel_1.add(lblNewLabel_5);

		labelTotal = new JLabel("-----------");
		labelTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelTotal.setBounds(862, 399, 69, 14);
		panel_1.add(labelTotal);

		txtTelefonoClienteVenta = new JTextField();
		txtTelefonoClienteVenta.setBounds(315, 409, 5, 20);
		panel_1.add(txtTelefonoClienteVenta);
		txtTelefonoClienteVenta.setColumns(10);

		txtDireccionClienteVenta = new JTextField();
		txtDireccionClienteVenta.setBounds(336, 409, 5, 20);
		panel_1.add(txtDireccionClienteVenta);
		txtDireccionClienteVenta.setColumns(10);

		txtRazonClienteVenta = new JTextField();
		txtRazonClienteVenta.setBounds(357, 409, 5, 20);
		panel_1.add(txtRazonClienteVenta);
		txtRazonClienteVenta.setColumns(10);

		txtIdPro = new JTextField();
		txtIdPro.setVisible(false);
		txtIdPro.setBounds(797, 47, 13, 20);
		panel_1.add(txtIdPro);
		txtIdPro.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("DNI:");
		lblNewLabel_6.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_6.setBounds(32, 39, 70, 14);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Name:");
		lblNewLabel_7.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_7.setBounds(32, 80, 46, 14);
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Phone:");
		lblNewLabel_8.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_8.setBounds(32, 125, 46, 14);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Address:");
		lblNewLabel_9.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_9.setBounds(32, 165, 56, 14);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Business Name:");
		lblNewLabel_10.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_10.setBounds(32, 207, 98, 14);
		panel_2.add(lblNewLabel_10);

		txtDniCliente = new JTextField();
		txtDniCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtDniCliente.setBounds(155, 37, 91, 20);
		panel_2.add(txtDniCliente);
		txtDniCliente.setColumns(10);

		txtNombreCliente = new JTextField();
		txtNombreCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtNombreCliente.setColumns(10);
		txtNombreCliente.setBounds(155, 78, 117, 20);
		panel_2.add(txtNombreCliente);

		txtTelefonoCliente = new JTextField();
		txtTelefonoCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtTelefonoCliente.setColumns(10);
		txtTelefonoCliente.setBounds(155, 123, 117, 20);
		panel_2.add(txtTelefonoCliente);

		txtDireccionCliente = new JTextField();
		txtDireccionCliente.setColumns(10);
		txtDireccionCliente.setBounds(155, 163, 117, 20);
		panel_2.add(txtDireccionCliente);

		txtRazonCliente = new JTextField();
		txtRazonCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtRazonCliente.setColumns(10);
		txtRazonCliente.setBounds(155, 205, 117, 20);
		panel_2.add(txtRazonCliente);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(324, 51, 745, 422);
		panel_2.add(scrollPane_1);

		TableCliente = new JTable();
		TableCliente.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int fila = TableCliente.rowAtPoint(e.getPoint());
				txtIdCliente.setText(TableCliente.getValueAt(fila, 0).toString());
				txtDniCliente.setText(TableCliente.getValueAt(fila, 1).toString());
				txtNombreCliente.setText(TableCliente.getValueAt(fila, 2).toString());
				txtTelefonoCliente.setText(TableCliente.getValueAt(fila, 3).toString());
				txtDireccionCliente.setText(TableCliente.getValueAt(fila, 4).toString());
				txtRazonCliente.setText(TableCliente.getValueAt(fila, 5).toString());

			}
		});
		TableCliente.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "DNI", "NAME", "PHONE", "ADDRESS", "Business Name" }));
		TableCliente.getColumnModel().getColumn(4).setPreferredWidth(100);
		TableCliente.getColumnModel().getColumn(5).setPreferredWidth(215);
		scrollPane_1.setViewportView(TableCliente);

		JButton btnGuardarCliente = new JButton("");
		btnGuardarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText())
						|| !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtDireccionCliente.getText())) {
					cl.setDni(txtDniCliente.getText());
					cl.setNombre(txtNombreCliente.getText());
					cl.setTelefono(txtTelefonoCliente.getText());
					cl.setDireccion(txtDireccionCliente.getText());
					cl.setRazon(txtRazonCliente.getText());
					client.RegistrarCliente(cl);
					JOptionPane.showMessageDialog(null, "Client successfully registered");
					LimpiarTable();
					LimpiarCliente();
					ListarCliente();

					JOptionPane.showMessageDialog(null, "Client Registered succesfully");
				} else {
					JOptionPane.showMessageDialog(null, "Some Fields are empty");
				}

			}
		});
		btnGuardarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardarCliente.setIcon(new ImageIcon(Systema.class.getResource("/Img/GuardarTodo.png")));
		btnGuardarCliente.setBounds(172, 310, 86, 35);
		panel_2.add(btnGuardarCliente);

		JButton btnEditarCliente = new JButton("");
		btnEditarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarCliente.setIcon(new ImageIcon(Systema.class.getResource("/Img/actualizar.png")));
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(txtIdCliente.getText())) {
					JOptionPane.showMessageDialog(null, "Select a row");
				} else {

					if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText())
							|| !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtDireccionCliente.getText())
							|| !"".equals(txtRazonCliente.getText())) {
						cl.setDni(txtDniCliente.getText());
						cl.setNombre(txtNombreCliente.getText());
						cl.setTelefono(txtTelefonoCliente.getText());
						cl.setDireccion(txtDireccionCliente.getText());
						cl.setRazon(txtRazonCliente.getText());
						cl.setId(Integer.parseInt(txtIdCliente.getText()));
						client.ModificarCliente(cl);
						JOptionPane.showMessageDialog(null, "Client successfully modified");
						LimpiarTable();
						LimpiarCliente();
						ListarCliente();
					} else {
						JOptionPane.showMessageDialog(null, "Los campos estan vacios");
					}
				}
			}
		});
		btnEditarCliente.setBounds(60, 310, 86, 35);
		panel_2.add(btnEditarCliente);

		JButton btnEliminarCliente = new JButton("");
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(txtIdCliente.getText())) {
					int pregunta = JOptionPane.showConfirmDialog(null, "Are you sure to delete this?");
					if (pregunta == 0) {
						int id = Integer.parseInt(txtIdCliente.getText());
						client.EliminarCliente(id);
						LimpiarTable();
						LimpiarCliente();
						ListarCliente();

					}
				}

			}
		});
		btnEliminarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarCliente.setIcon(new ImageIcon(Systema.class.getResource("/Img/eliminar.png")));
		btnEliminarCliente.setBounds(172, 264, 86, 35);
		panel_2.add(btnEliminarCliente);

		JButton btnNuevoCliente = new JButton("");
		btnNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarCliente();
			}
		});
		btnNuevoCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevoCliente.setIcon(new ImageIcon(Systema.class.getResource("/Img/nuevo.png")));
		btnNuevoCliente.setBounds(60, 263, 86, 35);
		panel_2.add(btnNuevoCliente);

		txtIdCliente = new JTextField();
		txtIdCliente.setVisible(false);
		txtIdCliente.setBounds(252, 37, 20, 20);
		panel_2.add(txtIdCliente);
		txtIdCliente.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_11 = new JLabel("DNI:");
		lblNewLabel_11.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_11.setBounds(32, 34, 46, 14);
		panel_3.add(lblNewLabel_11);

		JLabel lblNewLabel_11_1 = new JLabel("Name:");
		lblNewLabel_11_1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_11_1.setBounds(32, 72, 46, 14);
		panel_3.add(lblNewLabel_11_1);

		JLabel lblNewLabel_11_2 = new JLabel("Phone:");
		lblNewLabel_11_2.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_11_2.setBounds(32, 110, 46, 14);
		panel_3.add(lblNewLabel_11_2);

		JLabel lblNewLabel_11_3 = new JLabel("Address:");
		lblNewLabel_11_3.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_11_3.setBounds(32, 154, 60, 14);
		panel_3.add(lblNewLabel_11_3);

		JLabel lblNewLabel_11_4 = new JLabel("Business name:");
		lblNewLabel_11_4.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_11_4.setBounds(32, 193, 115, 14);
		panel_3.add(lblNewLabel_11_4);

		txtRucProveedor = new JTextField();
		txtRucProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtRucProveedor.setBounds(166, 34, 86, 20);
		panel_3.add(txtRucProveedor);
		txtRucProveedor.setColumns(10);

		txtNombreProveedor = new JTextField();
		txtNombreProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtNombreProveedor.setColumns(10);
		txtNombreProveedor.setBounds(166, 68, 115, 20);
		panel_3.add(txtNombreProveedor);

		txtTelefonoProveedor = new JTextField();
		txtTelefonoProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtTelefonoProveedor.setColumns(10);
		txtTelefonoProveedor.setBounds(166, 106, 115, 20);
		panel_3.add(txtTelefonoProveedor);

		txtDireccionProveedor = new JTextField();
		txtDireccionProveedor.setColumns(10);
		txtDireccionProveedor.setBounds(166, 150, 115, 20);
		panel_3.add(txtDireccionProveedor);

		txtRazonProveedor = new JTextField();
		txtRazonProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtRazonProveedor.setColumns(10);
		txtRazonProveedor.setBounds(167, 189, 114, 20);
		panel_3.add(txtRazonProveedor);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(291, 32, 804, 441);
		panel_3.add(scrollPane_2);

		TableProveedor = new JTable();
		TableProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = TableProveedor.rowAtPoint(e.getPoint());
				txtIdProveedor.setText(TableProveedor.getValueAt(fila, 0).toString());
				txtRucProveedor.setText(TableProveedor.getValueAt(fila, 1).toString());
				txtNombreProveedor.setText(TableProveedor.getValueAt(fila, 2).toString());
				txtTelefonoProveedor.setText(TableProveedor.getValueAt(fila, 3).toString());
				txtDireccionProveedor.setText(TableProveedor.getValueAt(fila, 4).toString());
				txtRazonProveedor.setText(TableProveedor.getValueAt(fila, 5).toString());

			}
		});
		TableProveedor.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "RUC", "NAME", "PHONE", "ADDRESS", "BUSINESS NAME" }));
		scrollPane_2.setViewportView(TableProveedor);

		JButton btnGuardarProveedor = new JButton("");
		btnGuardarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(txtRucProveedor.getText()) || !"".equals(txtNombreProveedor.getText())
						|| !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())
						|| !"".equals(txtRazonProveedor.getText())) {
					pr.setRuc(txtRucProveedor.getText());
					pr.setNombre(txtNombreProveedor.getText());
					pr.setTelefono(txtTelefonoProveedor.getText());
					pr.setDireccion(txtDireccionProveedor.getText());
					pr.setRazon(txtRazonProveedor.getText());
					PrDao.RegistrarProveedor(pr);
					JOptionPane.showMessageDialog(null, "Supplier registered successfully");
					LimpiarTable();
					ListarProveedor();
					LimpiarProveedor();
				} else {
					JOptionPane.showMessageDialog(null, "Warning: Some fields are empty");
				}
			}
		});
		btnGuardarProveedor.setIcon(new ImageIcon(Systema.class.getResource("/Img/GuardarTodo.png")));
		btnGuardarProveedor.setBounds(152, 304, 86, 35);
		panel_3.add(btnGuardarProveedor);

		JButton btnEditarProveedor = new JButton("");
		btnEditarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(txtIdProveedor.getText())) {
					JOptionPane.showMessageDialog(null, "Please choose a row");
				} else {
					if (!"".equals(txtRucProveedor.getText()) || !"".equals(txtNombreProveedor.getText())
							|| !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())
							|| !"".equals(txtRazonProveedor.getText())) {
						pr.setRuc(txtRucProveedor.getText());
						pr.setNombre(txtNombreProveedor.getText());
						pr.setTelefono(txtTelefonoProveedor.getText());
						pr.setDireccion(txtDireccionProveedor.getText());
						pr.setRazon(txtRazonProveedor.getText());
						pr.setId(Integer.parseInt(txtIdProveedor.getText()));
						PrDao.ModificarProveedor(pr);
						JOptionPane.showMessageDialog(null, "Supplier edited succesfully");
						LimpiarTable();
						ListarProveedor();
						LimpiarProveedor();
					}
				}
			}
		});
		btnEditarProveedor.setIcon(new ImageIcon(Systema.class.getResource("/Img/actualizar.png")));
		btnEditarProveedor.setBounds(45, 304, 86, 35);
		panel_3.add(btnEditarProveedor);

		JButton btnEliminarProveedor = new JButton("");
		btnEliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!"".equals(txtIdProveedor.getText())) {
					int pregunta = JOptionPane.showConfirmDialog(null, "Are you sure of delete this?");
					if (pregunta == 0) {
						int id = Integer.parseInt(txtIdProveedor.getText());
						PrDao.EliminarProveedor(id);
						LimpiarTable();
						ListarProveedor();
						LimpiarProveedor();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please choose a row");
				}
			}
		});
		btnEliminarProveedor.setIcon(new ImageIcon(Systema.class.getResource("/Img/eliminar.png")));
		btnEliminarProveedor.setBounds(152, 257, 86, 35);
		panel_3.add(btnEliminarProveedor);

		JButton btnNuevoProveedor = new JButton("");
		btnNuevoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarProveedor();
			}
		});
		btnNuevoProveedor.setIcon(new ImageIcon(Systema.class.getResource("/Img/nuevo.png")));
		btnNuevoProveedor.setBounds(45, 257, 86, 35);
		panel_3.add(btnNuevoProveedor);

		txtIdProveedor = new JTextField();
		txtIdProveedor.setVisible(false);
		txtIdProveedor.setBounds(262, 34, 19, 18);
		panel_3.add(txtIdProveedor);
		txtIdProveedor.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New tab", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblNewLabel_12 = new JLabel("Bar Code:");
		lblNewLabel_12.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_12.setBounds(21, 26, 66, 14);
		panel_4.add(lblNewLabel_12);

		JLabel lblNewLabel_12_1 = new JLabel("Description:");
		lblNewLabel_12_1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_12_1.setBounds(21, 72, 99, 14);
		panel_4.add(lblNewLabel_12_1);

		JLabel lblNewLabel_12_2 = new JLabel("Quantity:");
		lblNewLabel_12_2.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_12_2.setBounds(21, 116, 66, 14);
		panel_4.add(lblNewLabel_12_2);

		JLabel lblNewLabel_12_3 = new JLabel("Value:");
		lblNewLabel_12_3.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_12_3.setBounds(21, 159, 46, 14);
		panel_4.add(lblNewLabel_12_3);

		JLabel lblNewLabel_12_4 = new JLabel("Supplier:");
		lblNewLabel_12_4.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_12_4.setBounds(21, 202, 66, 14);
		panel_4.add(lblNewLabel_12_4);

		txtCodigoPro = new JTextField();
		txtCodigoPro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtCodigoPro.setBounds(144, 26, 99, 20);
		panel_4.add(txtCodigoPro);
		txtCodigoPro.setColumns(10);

		txtDesPro = new JTextField();
		txtDesPro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtDesPro.setColumns(10);
		txtDesPro.setBounds(144, 72, 99, 20);
		panel_4.add(txtDesPro);

		txtCantPro = new JTextField();
		txtCantPro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtCantPro.setColumns(10);
		txtCantPro.setBounds(144, 116, 99, 20);
		panel_4.add(txtCantPro);

		txtPrecioPro = new JTextField();
		txtPrecioPro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumerosDecimal(e, txtPrecioPro);
			}
		});
		txtPrecioPro.setColumns(10);
		txtPrecioPro.setBounds(144, 159, 99, 20);
		panel_4.add(txtPrecioPro);

		cbxProveedorPro = new JComboBox<Object>();
		cbxProveedorPro.setEditable(true);
		cbxProveedorPro.setBounds(144, 201, 155, 22);
		panel_4.add(cbxProveedorPro);
		prodao.ConsultarProveedor(cbxProveedorPro);
		AutoCompleteDecorator.decorate(cbxProveedorPro);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(326, 26, 759, 447);
		panel_4.add(scrollPane_3);

		TableProducto = new JTable();
		TableProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = TableProducto.rowAtPoint(e.getPoint());
				txtIdPro.setText(TableProducto.getValueAt(fila, 0).toString());
				txtCodigoPro.setText(TableProducto.getValueAt(fila, 1).toString());
				txtDesPro.setText(TableProducto.getValueAt(fila, 2).toString());
				cbxProveedorPro.setSelectedItem(TableProducto.getValueAt(fila, 3).toString());
				txtCantPro.setText(TableProducto.getValueAt(fila, 4).toString());
				txtPrecioPro.setText(TableProducto.getValueAt(fila, 5).toString());
			}
		});
		TableProducto.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "BAR CODE", "DESCRIPTION", "SUPPLIER", "STOCK", "VALUE" }));
		TableProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane_3.setViewportView(TableProducto);

		JButton btnNuevoPro = new JButton("");
		btnNuevoPro.setIcon(new ImageIcon(Systema.class.getResource("/Img/nuevo.png")));
		btnNuevoPro.setBounds(47, 279, 86, 35);
		panel_4.add(btnNuevoPro);

		JButton btnEditarPro = new JButton("");
		btnEditarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ("".equals(txtIdPro.getText())) {
					JOptionPane.showMessageDialog(null, "Select a row");
				} else {

					if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtDesPro.getText())
							|| !"".equals(txtCantPro.getText()) || !"".equals(txtPrecioPro.getText())) {
						pro.setCodigo(txtCodigoPro.getText());
						pro.setNombre(txtDesPro.getText());
						pro.setProveedor(cbxProveedorPro.getSelectedItem().toString());
						pro.setStock(Integer.parseInt(txtCantPro.getText()));
						pro.setPrecio(Double.parseDouble(txtPrecioPro.getText()));
						pro.setId(Integer.parseInt(txtIdPro.getText()));
						prodao.ModificarProductos(pro);
						JOptionPane.showMessageDialog(null, "Product successfully modified");
						LimpiarTable();
						ListarProductos();
						LimpiarProductos();

					} else {
						JOptionPane.showMessageDialog(null, "Los campos estan vacios");
					}
				}

			}
		});
		btnEditarPro.setIcon(new ImageIcon(Systema.class.getResource("/Img/actualizar.png")));
		btnEditarPro.setBounds(47, 326, 86, 35);
		panel_4.add(btnEditarPro);

		JButton btnEliminarPro = new JButton("");
		btnEliminarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!"".equals(txtIdPro.getText())) {
					int pregunta = JOptionPane.showConfirmDialog(null, "Are you sure of delete this?");
					if (pregunta == 0) {
						int id = Integer.parseInt(txtIdPro.getText());
						prodao.EliminarProductos(id);
						LimpiarTable();
						ListarProductos();
						LimpiarProductos();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please choose a row");
				}

			}
		});
		btnEliminarPro.setIcon(new ImageIcon(Systema.class.getResource("/Img/eliminar.png")));
		btnEliminarPro.setBounds(154, 279, 86, 35);
		panel_4.add(btnEliminarPro);

		JButton btnGuardarPro = new JButton("");
		btnGuardarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtDesPro.getText())
						|| !"".equals(cbxProveedorPro.getSelectedItem()) || !"".equals(txtCantPro.getText())
						|| !"".equals(txtPrecioPro.getText())) {
					pro.setCodigo(txtCodigoPro.getText());
					pro.setNombre(txtDesPro.getText());
					pro.setProveedor(cbxProveedorPro.getSelectedItem().toString());
					pro.setStock(Integer.parseInt(txtCantPro.getText()));
					pro.setPrecio(Double.parseDouble(txtPrecioPro.getText()));
					prodao.RegistrarProductos(pro);
					LimpiarTable();
					LimpiarProductos();
					ListarProductos();

					JOptionPane.showMessageDialog(null, "Product registered successfully");

				} else {
					JOptionPane.showMessageDialog(null, "Some fields are empty");
				}
			}
		});
		btnGuardarPro.setIcon(new ImageIcon(Systema.class.getResource("/Img/GuardarTodo.png")));
		btnGuardarPro.setBounds(154, 326, 86, 35);
		panel_4.add(btnGuardarPro);

		JButton btnExcelPro = new JButton("");
		btnExcelPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Excel.reporte();

			}
		});
		btnExcelPro.setIcon(new ImageIcon(Systema.class.getResource("/Img/excel.png")));
		btnExcelPro.setBounds(209, 418, 86, 35);
		panel_4.add(btnExcelPro);

		txtIdpro = new JTextField();
		txtIdpro.setVisible(false);
		txtIdpro.setBounds(253, 24, 12, 20);
		panel_4.add(txtIdpro);
		txtIdpro.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New tab", null, panel_5, null);
		panel_5.setLayout(null);

		JScrollPane scrollPane_3_1 = new JScrollPane();
		scrollPane_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
			}
		});
		scrollPane_3_1.setBounds(165, 82, 755, 391);
		panel_5.add(scrollPane_3_1);

		TableVentas = new JTable();
		TableVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = TableVentas.rowAtPoint(e.getPoint());
				txtIdVenta1.setText(TableVentas.getValueAt(fila, 0).toString());
			}
		});
		TableVentas.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Costumer", "Seller", "Total" }));
		scrollPane_3_1.setViewportView(TableVentas);

		JButton btnPdfVentas = new JButton("");
		btnPdfVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					int id = Integer.parseInt(txtIdVenta1.getText());
					File file = new File("src/pdf/sale"+id+".pdf");
					//System.out.print(id);
					Desktop.getDesktop().open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Logger.getLogger(Systema.class.getName()).log(Level.SEVERE,null,e1);
				}
			}
		});
		btnPdfVentas.setIcon(new ImageIcon(Systema.class.getResource("/Img/pdf.png")));
		btnPdfVentas.setBounds(963, 361, 83, 53);
		panel_5.add(btnPdfVentas);

		txtIdVenta1 = new JTextField();
		txtIdVenta1.setVisible(false);
		txtIdVenta1.setText("");
		txtIdVenta1.setBounds(165, 40, 42, 20);
		panel_5.add(txtIdVenta1);
		txtIdVenta1.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New tab", null, panel_6, null);
		panel_6.setLayout(null);

		JLabel lblNewLabel_13 = new JLabel("RUC:");
		lblNewLabel_13.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_13.setBounds(607, 69, 46, 14);
		panel_6.add(lblNewLabel_13);

		JLabel lblNewLabel_13_1 = new JLabel("NAME:");
		lblNewLabel_13_1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_13_1.setBounds(607, 143, 46, 14);
		panel_6.add(lblNewLabel_13_1);

		JLabel lblNewLabel_13_2 = new JLabel("PHONE:");
		lblNewLabel_13_2.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_13_2.setBounds(607, 227, 86, 14);
		panel_6.add(lblNewLabel_13_2);

		JLabel lblNewLabel_13_3 = new JLabel("ADDRESS:");
		lblNewLabel_13_3.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_13_3.setBounds(607, 316, 86, 14);
		panel_6.add(lblNewLabel_13_3);

		JLabel lblNewLabel_13_4 = new JLabel("BUSINESS NAME:");
		lblNewLabel_13_4.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_13_4.setBounds(607, 395, 151, 14);
		panel_6.add(lblNewLabel_13_4);

		txtRucConfig = new JTextField();
		txtRucConfig.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtRucConfig.setBounds(586, 94, 235, 20);
		panel_6.add(txtRucConfig);
		txtRucConfig.setColumns(10);

		txtNombreConfig = new JTextField();
		txtNombreConfig.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtNombreConfig.setColumns(10);
		txtNombreConfig.setBounds(586, 168, 235, 20);
		panel_6.add(txtNombreConfig);

		txtTelefonoConfig = new JTextField();
		txtTelefonoConfig.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtTelefonoConfig.setColumns(10);
		txtTelefonoConfig.setBounds(586, 252, 235, 20);
		panel_6.add(txtTelefonoConfig);

		txtDireccionConfig = new JTextField();
		txtDireccionConfig.setColumns(10);
		txtDireccionConfig.setBounds(586, 341, 235, 20);
		panel_6.add(txtDireccionConfig);

		txtRazonConfig = new JTextField();
		txtRazonConfig.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtRazonConfig.setColumns(10);
		txtRazonConfig.setBounds(586, 420, 235, 20);
		panel_6.add(txtRazonConfig);

		JLabel lblNewLabel_14 = new JLabel("Company information");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 24));
		lblNewLabel_14.setBounds(10, 12, 566, 72);
		panel_6.add(lblNewLabel_14);

		JButton btnActualizarConfig = new JButton("Update");
		btnActualizarConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!"".equals(txtRucConfig.getText()) || !"".equals(txtNombreConfig.getText())
						|| !"".equals(txtTelefonoConfig.getText()) || !"".equals(txtDireccionConfig.getText())
						|| !"".equals(txtRazonConfig.getText())) {
					conf.setRuc(Integer.parseInt(txtRucConfig.getText()));
					conf.setNombre(txtNombreConfig.getText());
					conf.setTelefono(Integer.parseInt(txtTelefonoConfig.getText()));
					conf.setDireccion(txtDireccionConfig.getText());
					conf.setRazon(txtRazonConfig.getText());
					conf.setId(Integer.parseInt(txtIdConfig.getText()));
					prodao.ModificarDatos(conf);
					JOptionPane.showMessageDialog(null, "Company information modified succesfully");
					ListarConfig();
				} else {
					JOptionPane.showMessageDialog(null, "Some Fields are empty");
				}
				
				
				
				
			}
		});
		btnActualizarConfig.setIcon(new ImageIcon(Systema.class.getResource("/Img/actualizar.png")));
		btnActualizarConfig.setBounds(220, 120, 141, 38);
		panel_6.add(btnActualizarConfig);

		txtIdConfig = new JTextField();
		txtIdConfig.setVisible(false);
		txtIdConfig.setBounds(802, 43, 19, 20);
		panel_6.add(txtIdConfig);
		txtIdConfig.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 255, 47));
		panel.setBounds(0, 0, 188, 797);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNuevaVenta = new JButton("New");
		btnNuevaVenta.setBackground(new Color(255, 255, 240));
		btnNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_1));
			}
		});
		btnNuevaVenta.setIcon(new ImageIcon(Systema.class.getResource("/Img/Nventa.png")));
		btnNuevaVenta.setBounds(22, 273, 136, 58);
		panel.add(btnNuevaVenta);

		JButton btnClientes = new JButton("Costumers");
		btnClientes.setBackground(new Color(255, 255, 240));
		if(priv.getRol().equals("Asistente")) {
			btnClientes.setEnabled(false);
		}
		btnClientes.setIcon(new ImageIcon(Systema.class.getResource("/Img/Clientes.png")));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarTable();
				ListarCliente();
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_2));

			}
		});
		btnClientes.setBounds(22, 342, 136, 58);
		panel.add(btnClientes);

		
		JButton btnProveedor = new JButton("Suppliers");
		btnProveedor.setBackground(new Color(255, 255, 240));
		if(priv.getRol().equals("Asistente")) {
			btnProveedor.setEnabled(false);
		}
		
		btnProveedor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				LimpiarTable();
				ListarProveedor();
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_3));

			}
		});
		btnProveedor.setIcon(new ImageIcon(Systema.class.getResource("/Img/proveedor.png")));
		btnProveedor.setBounds(22, 411, 136, 58);
		panel.add(btnProveedor);

		JButton btnProductos = new JButton("Stock");
		btnProductos.setBackground(new Color(255, 255, 240));
		if(priv.getRol().equals("Asistente")) {
			btnProductos.setEnabled(false);
		}
		btnProductos.setIcon(new ImageIcon(Systema.class.getResource("/Img/producto.png")));
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarTable();
				ListarProductos();
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_4));
			}
		});
		btnProductos.setBounds(22, 480, 136, 58);
		panel.add(btnProductos);

		JButton btnVentas = new JButton("Sellings");
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_5));
				LimpiarTable();
				ListarVentas();
			}
		});
		btnVentas.setBackground(new Color(255, 255, 240));
		btnVentas.setIcon(new ImageIcon(Systema.class.getResource("/Img/compras.png")));
		btnVentas.setBounds(22, 549, 136, 58);
		panel.add(btnVentas);

		JButton btnConfig = new JButton("Settings");
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_6));
			}
		});
		btnConfig.setBackground(new Color(224, 255, 255));
		btnConfig.setIcon(new ImageIcon(Systema.class.getResource("/Img/config.png")));
		btnConfig.setBounds(22, 712, 136, 58);
		panel.add(btnConfig);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Systema.class.getResource("/Img/logo.png")));
		lblNewLabel_1.setBounds(0, 48, 186, 149);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon(Systema.class.getResource("/Img/encabezado.png")));
		lblNewLabel.setBounds(0, 0, 1264, 729);
		contentPane.add(lblNewLabel);
		
		JLabel LabelVendedor_1 = new JLabel("Vendedor: ");
		LabelVendedor_1.setForeground(Color.WHITE);
		LabelVendedor_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		LabelVendedor_1.setBounds(1044, 159, 220, 53);
		contentPane.add(LabelVendedor_1);
		
		LabelVendedor = new JLabel(" ");
		LabelVendedor.setForeground(new Color(255, 255, 255));
		LabelVendedor.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		LabelVendedor.setBounds(1141, 159, 220, 53);
		contentPane.add(LabelVendedor);
		if(priv.getRol().equals("Asistente")) {
			LabelVendedor.setText(priv.getNombre());
		}else {
			LabelVendedor.setText(priv.getNombre());
		}
		
		

		
	}
	
	public Systema() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1314, 836);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(186, 304, 1112, 518);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Product Code");
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(33, 22, 100, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Description");
		lblNewLabel_2_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(184, 22, 74, 14);
		panel_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Quantity");
		lblNewLabel_2_2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lblNewLabel_2_2.setBounds(367, 22, 68, 14);
		panel_1.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("Value");
		lblNewLabel_2_3.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lblNewLabel_2_3.setBounds(527, 22, 46, 14);
		panel_1.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel("Stock");
		lblNewLabel_2_4.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 13));
		lblNewLabel_2_4.setBounds(701, 22, 46, 14);
		panel_1.add(lblNewLabel_2_4);

		JButton btnEliminarVenta = new JButton("");
		btnEliminarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo = (DefaultTableModel) TableVenta.getModel();
				modelo.removeRow(TableVenta.getSelectedRow());
				TotalPagar();
				txtCodigoVenta.requestFocus();
			}
		});
		btnEliminarVenta.setIcon(new ImageIcon(Systema.class.getResource("/Img/eliminar.png")));
		btnEliminarVenta.setBounds(881, 29, 86, 38);
		panel_1.add(btnEliminarVenta);

		txtCodigoVenta = new JTextField();
		txtCodigoVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!"".equals(txtCodigoVenta.getText())) {
						String cod = txtCodigoVenta.getText();
						pro = prodao.BuscarPro(cod);
						if (pro.getNombre() != null) {
							txtDescripcionVenta.setText("" + pro.getNombre());
							txtPrecioVenta.setText("" + pro.getPrecio());
							txtStockDisponible.setText("" + pro.getStock());
							txtCantidadVenta.requestFocus();
						} else {
							LimpiarVenta();
							txtCodigoVenta.requestFocus();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please enter product code");
						txtCodigoVenta.requestFocus();
					}
				}

			}
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtCodigoVenta.setBounds(33, 47, 86, 20);
		panel_1.add(txtCodigoVenta);
		txtCodigoVenta.setColumns(10);

		txtDescripcionVenta = new JTextField();
		txtDescripcionVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtDescripcionVenta.setColumns(10);
		txtDescripcionVenta.setBounds(184, 47, 148, 20);
		panel_1.add(txtDescripcionVenta);

		txtCantidadVenta = new JTextField();
		txtCantidadVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!"".equals(txtCantidadVenta.getText())) {
						String cod = txtCodigoVenta.getText();
						String descripcion = txtDescripcionVenta.getText();
						int cant = Integer.parseInt(txtCantidadVenta.getText());
						double precio = Double.parseDouble(txtPrecioVenta.getText());
						double total = cant * precio;
						int stock = Integer.parseInt(txtStockDisponible.getText());
						if (stock >= cant) {
							item = item + 1;
							tmp = (DefaultTableModel) TableVenta.getModel();
							for (int i = 0; i < TableVenta.getRowCount(); i++) {
								if (TableVenta.getValueAt(i, 1).equals(txtDescripcionVenta.getText())) {
									JOptionPane.showMessageDialog(null, "Product already registered");
									return;
								}
							}
							ArrayList lista = new ArrayList();
							lista.add(item);
							lista.add(cod);
							lista.add(descripcion);
							lista.add(cant);
							lista.add(precio);
							lista.add(total);
							Object[] O = new Object[5];
							O[0] = lista.get(1);
							O[1] = lista.get(2);
							O[2] = lista.get(3);
							O[3] = lista.get(4);
							O[4] = lista.get(4);
							tmp.addRow(O);
							TableVenta.setModel(tmp);
							TotalPagar();
							LimpiarVenta();
							txtCodigoVenta.requestFocus();
						} else {
							JOptionPane.showMessageDialog(null, "Stock not available");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please enter a quantity");
					}
				}

			}
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtCantidadVenta.setColumns(10);
		txtCantidadVenta.setBounds(367, 47, 86, 20);
		panel_1.add(txtCantidadVenta);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setEditable(false);
		txtPrecioVenta.setColumns(10);
		txtPrecioVenta.setBounds(527, 47, 86, 20);
		panel_1.add(txtPrecioVenta);

		txtStockDisponible = new JTextField();
		txtStockDisponible.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtStockDisponible.setColumns(10);
		txtStockDisponible.setBounds(701, 47, 86, 20);
		panel_1.add(txtStockDisponible);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 1013, 289);
		panel_1.add(scrollPane);

		TableVenta = new JTable();
		TableVenta.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Product Code", "Description", "Quantity", "Value", "Total" }));
		scrollPane.setViewportView(TableVenta);

		JLabel lblNewLabel_3 = new JLabel("DNI/RUC");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 384, 86, 14);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("NAME");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(123, 382, 46, 14);
		panel_1.add(lblNewLabel_4);

		txtRucVenta = new JTextField();
		txtRucVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!"".equals(txtRucVenta.getText())) {
						int dni = Integer.parseInt(txtRucVenta.getText());
						cl = client.Buscarcliente(dni);
						if (cl.getNombre() != null) {
							txtNombreClienteVenta.setText("" + cl.getNombre());
							txtTelefonoClienteVenta.setText("" + cl.getTelefono());
							txtDireccionClienteVenta.setText("" + cl.getDireccion());
							txtRazonClienteVenta.setText("" + cl.getRazon());
						} else {
							txtRucVenta.setText("");
							JOptionPane.showMessageDialog(null, "Unknown Client");
						}
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtRucVenta.setBounds(10, 409, 86, 20);
		panel_1.add(txtRucVenta);
		txtRucVenta.setColumns(10);

		txtNombreClienteVenta = new JTextField();
		txtNombreClienteVenta.setEditable(false);
		txtNombreClienteVenta.setBounds(123, 410, 171, 20);
		panel_1.add(txtNombreClienteVenta);
		txtNombreClienteVenta.setColumns(10);

		JButton btnGenerarVenta = new JButton("");
		btnGenerarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TableVenta.getRowCount() > 0) {

					if (!"".equals(txtNombreClienteVenta.getText())) {

						RegistrarVenta();
						RegistrarDetalle();
						ActualizarStock();
						pdf();
						LimpiarTableVenta();
						LimpiarClienteVenta();
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Client");
					}

				}else {
					JOptionPane.showMessageDialog(null, "No Products in this sale");
				}

			}
		});
		btnGenerarVenta.setIcon(new ImageIcon(Systema.class.getResource("/Img/print.png")));
		btnGenerarVenta.setBounds(513, 395, 100, 57);
		panel_1.add(btnGenerarVenta);

		JLabel lblNewLabel_5 = new JLabel("Total:");
		lblNewLabel_5.setIcon(new ImageIcon(Systema.class.getResource("/Img/money.png")));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(752, 384, 100, 45);
		panel_1.add(lblNewLabel_5);

		labelTotal = new JLabel("-----------");
		labelTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelTotal.setBounds(862, 399, 69, 14);
		panel_1.add(labelTotal);

		txtTelefonoClienteVenta = new JTextField();
		txtTelefonoClienteVenta.setVisible(false);
		txtTelefonoClienteVenta.setBounds(315, 409, 5, 20);
		panel_1.add(txtTelefonoClienteVenta);
		txtTelefonoClienteVenta.setColumns(10);

		txtDireccionClienteVenta = new JTextField();
		txtDireccionClienteVenta.setVisible(false);
		txtDireccionClienteVenta.setBounds(336, 409, 5, 20);
		panel_1.add(txtDireccionClienteVenta);
		txtDireccionClienteVenta.setColumns(10);

		txtRazonClienteVenta = new JTextField();
		txtRazonClienteVenta.setVisible(false);
		txtRazonClienteVenta.setBounds(357, 409, 5, 20);
		panel_1.add(txtRazonClienteVenta);
		txtRazonClienteVenta.setColumns(10);

		txtIdPro = new JTextField();
		txtIdPro.setVisible(false);
		txtIdPro.setBounds(797, 47, 13, 20);
		panel_1.add(txtIdPro);
		txtIdPro.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("DNI:");
		lblNewLabel_6.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_6.setBounds(32, 39, 70, 14);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Name:");
		lblNewLabel_7.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_7.setBounds(32, 80, 46, 14);
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Phone:");
		lblNewLabel_8.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_8.setBounds(32, 125, 46, 14);
		panel_2.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Address:");
		lblNewLabel_9.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_9.setBounds(32, 165, 56, 14);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Business Name:");
		lblNewLabel_10.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_10.setBounds(32, 207, 98, 14);
		panel_2.add(lblNewLabel_10);

		txtDniCliente = new JTextField();
		txtDniCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtDniCliente.setBounds(155, 37, 91, 20);
		panel_2.add(txtDniCliente);
		txtDniCliente.setColumns(10);

		txtNombreCliente = new JTextField();
		txtNombreCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtNombreCliente.setColumns(10);
		txtNombreCliente.setBounds(155, 78, 117, 20);
		panel_2.add(txtNombreCliente);

		txtTelefonoCliente = new JTextField();
		txtTelefonoCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtTelefonoCliente.setColumns(10);
		txtTelefonoCliente.setBounds(155, 123, 117, 20);
		panel_2.add(txtTelefonoCliente);

		txtDireccionCliente = new JTextField();
		txtDireccionCliente.setColumns(10);
		txtDireccionCliente.setBounds(155, 163, 117, 20);
		panel_2.add(txtDireccionCliente);

		txtRazonCliente = new JTextField();
		txtRazonCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtRazonCliente.setColumns(10);
		txtRazonCliente.setBounds(155, 205, 117, 20);
		panel_2.add(txtRazonCliente);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(324, 51, 745, 422);
		panel_2.add(scrollPane_1);

		TableCliente = new JTable();
		TableCliente.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int fila = TableCliente.rowAtPoint(e.getPoint());
				txtIdCliente.setText(TableCliente.getValueAt(fila, 0).toString());
				txtDniCliente.setText(TableCliente.getValueAt(fila, 1).toString());
				txtNombreCliente.setText(TableCliente.getValueAt(fila, 2).toString());
				txtTelefonoCliente.setText(TableCliente.getValueAt(fila, 3).toString());
				txtDireccionCliente.setText(TableCliente.getValueAt(fila, 4).toString());
				txtRazonCliente.setText(TableCliente.getValueAt(fila, 5).toString());

			}
		});
		TableCliente.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "DNI", "NAME", "PHONE", "ADDRESS", "Business Name" }));
		TableCliente.getColumnModel().getColumn(4).setPreferredWidth(100);
		TableCliente.getColumnModel().getColumn(5).setPreferredWidth(215);
		scrollPane_1.setViewportView(TableCliente);

		JButton btnGuardarCliente = new JButton("");
		btnGuardarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText())
						|| !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtDireccionCliente.getText())) {
					cl.setDni(txtDniCliente.getText());
					cl.setNombre(txtNombreCliente.getText());
					cl.setTelefono(txtTelefonoCliente.getText());
					cl.setDireccion(txtDireccionCliente.getText());
					cl.setRazon(txtRazonCliente.getText());
					client.RegistrarCliente(cl);
					JOptionPane.showMessageDialog(null, "Client successfully registered");
					LimpiarTable();
					LimpiarCliente();
					ListarCliente();

					JOptionPane.showMessageDialog(null, "Client Registered succesfully");
				} else {
					JOptionPane.showMessageDialog(null, "Some Fields are empty");
				}

			}
		});
		btnGuardarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardarCliente.setIcon(new ImageIcon(Systema.class.getResource("/Img/GuardarTodo.png")));
		btnGuardarCliente.setBounds(172, 310, 86, 35);
		panel_2.add(btnGuardarCliente);

		JButton btnEditarCliente = new JButton("");
		btnEditarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditarCliente.setIcon(new ImageIcon(Systema.class.getResource("/Img/actualizar.png")));
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(txtIdCliente.getText())) {
					JOptionPane.showMessageDialog(null, "Select a row");
				} else {

					if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText())
							|| !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtDireccionCliente.getText())
							|| !"".equals(txtRazonCliente.getText())) {
						cl.setDni(txtDniCliente.getText());
						cl.setNombre(txtNombreCliente.getText());
						cl.setTelefono(txtTelefonoCliente.getText());
						cl.setDireccion(txtDireccionCliente.getText());
						cl.setRazon(txtRazonCliente.getText());
						cl.setId(Integer.parseInt(txtIdCliente.getText()));
						client.ModificarCliente(cl);
						JOptionPane.showMessageDialog(null, "Client successfully modified");
						LimpiarTable();
						LimpiarCliente();
						ListarCliente();
					} else {
						JOptionPane.showMessageDialog(null, "Los campos estan vacios");
					}
				}
			}
		});
		btnEditarCliente.setBounds(60, 310, 86, 35);
		panel_2.add(btnEditarCliente);

		JButton btnEliminarCliente = new JButton("");
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(txtIdCliente.getText())) {
					int pregunta = JOptionPane.showConfirmDialog(null, "Are you sure to delete this?");
					if (pregunta == 0) {
						int id = Integer.parseInt(txtIdCliente.getText());
						client.EliminarCliente(id);
						LimpiarTable();
						LimpiarCliente();
						ListarCliente();

					}
				}

			}
		});
		btnEliminarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarCliente.setIcon(new ImageIcon(Systema.class.getResource("/Img/eliminar.png")));
		btnEliminarCliente.setBounds(172, 264, 86, 35);
		panel_2.add(btnEliminarCliente);

		JButton btnNuevoCliente = new JButton("");
		btnNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarCliente();
			}
		});
		btnNuevoCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevoCliente.setIcon(new ImageIcon(Systema.class.getResource("/Img/nuevo.png")));
		btnNuevoCliente.setBounds(60, 263, 86, 35);
		panel_2.add(btnNuevoCliente);

		txtIdCliente = new JTextField();
		txtIdCliente.setVisible(false);
		txtIdCliente.setBounds(252, 37, 20, 20);
		panel_2.add(txtIdCliente);
		txtIdCliente.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New tab", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_11 = new JLabel("DNI:");
		lblNewLabel_11.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_11.setBounds(32, 34, 46, 14);
		panel_3.add(lblNewLabel_11);

		JLabel lblNewLabel_11_1 = new JLabel("Name:");
		lblNewLabel_11_1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_11_1.setBounds(32, 72, 46, 14);
		panel_3.add(lblNewLabel_11_1);

		JLabel lblNewLabel_11_2 = new JLabel("Phone:");
		lblNewLabel_11_2.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_11_2.setBounds(32, 110, 46, 14);
		panel_3.add(lblNewLabel_11_2);

		JLabel lblNewLabel_11_3 = new JLabel("Address:");
		lblNewLabel_11_3.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_11_3.setBounds(32, 154, 60, 14);
		panel_3.add(lblNewLabel_11_3);

		JLabel lblNewLabel_11_4 = new JLabel("Business name:");
		lblNewLabel_11_4.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_11_4.setBounds(32, 193, 115, 14);
		panel_3.add(lblNewLabel_11_4);

		txtRucProveedor = new JTextField();
		txtRucProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtRucProveedor.setBounds(166, 34, 86, 20);
		panel_3.add(txtRucProveedor);
		txtRucProveedor.setColumns(10);

		txtNombreProveedor = new JTextField();
		txtNombreProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtNombreProveedor.setColumns(10);
		txtNombreProveedor.setBounds(166, 68, 115, 20);
		panel_3.add(txtNombreProveedor);

		txtTelefonoProveedor = new JTextField();
		txtTelefonoProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtTelefonoProveedor.setColumns(10);
		txtTelefonoProveedor.setBounds(166, 106, 115, 20);
		panel_3.add(txtTelefonoProveedor);

		txtDireccionProveedor = new JTextField();
		txtDireccionProveedor.setColumns(10);
		txtDireccionProveedor.setBounds(166, 150, 115, 20);
		panel_3.add(txtDireccionProveedor);

		txtRazonProveedor = new JTextField();
		txtRazonProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtRazonProveedor.setColumns(10);
		txtRazonProveedor.setBounds(167, 189, 114, 20);
		panel_3.add(txtRazonProveedor);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(291, 32, 804, 441);
		panel_3.add(scrollPane_2);

		TableProveedor = new JTable();
		TableProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = TableProveedor.rowAtPoint(e.getPoint());
				txtIdProveedor.setText(TableProveedor.getValueAt(fila, 0).toString());
				txtRucProveedor.setText(TableProveedor.getValueAt(fila, 1).toString());
				txtNombreProveedor.setText(TableProveedor.getValueAt(fila, 2).toString());
				txtTelefonoProveedor.setText(TableProveedor.getValueAt(fila, 3).toString());
				txtDireccionProveedor.setText(TableProveedor.getValueAt(fila, 4).toString());
				txtRazonProveedor.setText(TableProveedor.getValueAt(fila, 5).toString());

			}
		});
		TableProveedor.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "RUC", "NAME", "PHONE", "ADDRESS", "BUSINESS NAME" }));
		scrollPane_2.setViewportView(TableProveedor);

		JButton btnGuardarProveedor = new JButton("");
		btnGuardarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(txtRucProveedor.getText()) || !"".equals(txtNombreProveedor.getText())
						|| !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())
						|| !"".equals(txtRazonProveedor.getText())) {
					pr.setRuc(txtRucProveedor.getText());
					pr.setNombre(txtNombreProveedor.getText());
					pr.setTelefono(txtTelefonoProveedor.getText());
					pr.setDireccion(txtDireccionProveedor.getText());
					pr.setRazon(txtRazonProveedor.getText());
					PrDao.RegistrarProveedor(pr);
					JOptionPane.showMessageDialog(null, "Supplier registered successfully");
					LimpiarTable();
					ListarProveedor();
					LimpiarProveedor();
				} else {
					JOptionPane.showMessageDialog(null, "Warning: Some fields are empty");
				}
			}
		});
		btnGuardarProveedor.setIcon(new ImageIcon(Systema.class.getResource("/Img/GuardarTodo.png")));
		btnGuardarProveedor.setBounds(152, 304, 86, 35);
		panel_3.add(btnGuardarProveedor);

		JButton btnEditarProveedor = new JButton("");
		btnEditarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(txtIdProveedor.getText())) {
					JOptionPane.showMessageDialog(null, "Please choose a row");
				} else {
					if (!"".equals(txtRucProveedor.getText()) || !"".equals(txtNombreProveedor.getText())
							|| !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())
							|| !"".equals(txtRazonProveedor.getText())) {
						pr.setRuc(txtRucProveedor.getText());
						pr.setNombre(txtNombreProveedor.getText());
						pr.setTelefono(txtTelefonoProveedor.getText());
						pr.setDireccion(txtDireccionProveedor.getText());
						pr.setRazon(txtRazonProveedor.getText());
						pr.setId(Integer.parseInt(txtIdProveedor.getText()));
						PrDao.ModificarProveedor(pr);
						JOptionPane.showMessageDialog(null, "Supplier edited succesfully");
						LimpiarTable();
						ListarProveedor();
						LimpiarProveedor();
					}
				}
			}
		});
		btnEditarProveedor.setIcon(new ImageIcon(Systema.class.getResource("/Img/actualizar.png")));
		btnEditarProveedor.setBounds(45, 304, 86, 35);
		panel_3.add(btnEditarProveedor);

		JButton btnEliminarProveedor = new JButton("");
		btnEliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!"".equals(txtIdProveedor.getText())) {
					int pregunta = JOptionPane.showConfirmDialog(null, "Are you sure of delete this?");
					if (pregunta == 0) {
						int id = Integer.parseInt(txtIdProveedor.getText());
						PrDao.EliminarProveedor(id);
						LimpiarTable();
						ListarProveedor();
						LimpiarProveedor();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please choose a row");
				}
			}
		});
		btnEliminarProveedor.setIcon(new ImageIcon(Systema.class.getResource("/Img/eliminar.png")));
		btnEliminarProveedor.setBounds(152, 257, 86, 35);
		panel_3.add(btnEliminarProveedor);

		JButton btnNuevoProveedor = new JButton("");
		btnNuevoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarProveedor();
			}
		});
		btnNuevoProveedor.setIcon(new ImageIcon(Systema.class.getResource("/Img/nuevo.png")));
		btnNuevoProveedor.setBounds(45, 257, 86, 35);
		panel_3.add(btnNuevoProveedor);

		txtIdProveedor = new JTextField();
		txtIdProveedor.setVisible(false);
		txtIdProveedor.setBounds(262, 34, 19, 18);
		panel_3.add(txtIdProveedor);
		txtIdProveedor.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New tab", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblNewLabel_12 = new JLabel("Bar Code:");
		lblNewLabel_12.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_12.setBounds(21, 26, 66, 14);
		panel_4.add(lblNewLabel_12);

		JLabel lblNewLabel_12_1 = new JLabel("Description:");
		lblNewLabel_12_1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_12_1.setBounds(21, 72, 99, 14);
		panel_4.add(lblNewLabel_12_1);

		JLabel lblNewLabel_12_2 = new JLabel("Quantity:");
		lblNewLabel_12_2.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_12_2.setBounds(21, 116, 66, 14);
		panel_4.add(lblNewLabel_12_2);

		JLabel lblNewLabel_12_3 = new JLabel("Value:");
		lblNewLabel_12_3.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_12_3.setBounds(21, 159, 46, 14);
		panel_4.add(lblNewLabel_12_3);

		JLabel lblNewLabel_12_4 = new JLabel("Supplier:");
		lblNewLabel_12_4.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_12_4.setBounds(21, 202, 66, 14);
		panel_4.add(lblNewLabel_12_4);

		txtCodigoPro = new JTextField();
		txtCodigoPro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtCodigoPro.setBounds(144, 26, 99, 20);
		panel_4.add(txtCodigoPro);
		txtCodigoPro.setColumns(10);

		txtDesPro = new JTextField();
		txtDesPro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtDesPro.setColumns(10);
		txtDesPro.setBounds(144, 72, 99, 20);
		panel_4.add(txtDesPro);

		txtCantPro = new JTextField();
		txtCantPro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtCantPro.setColumns(10);
		txtCantPro.setBounds(144, 116, 99, 20);
		panel_4.add(txtCantPro);

		txtPrecioPro = new JTextField();
		txtPrecioPro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumerosDecimal(e, txtPrecioPro);
			}
		});
		txtPrecioPro.setColumns(10);
		txtPrecioPro.setBounds(144, 159, 99, 20);
		panel_4.add(txtPrecioPro);

		cbxProveedorPro = new JComboBox<Object>();
		cbxProveedorPro.setEditable(true);
		cbxProveedorPro.setBounds(144, 201, 155, 22);
		panel_4.add(cbxProveedorPro);
		prodao.ConsultarProveedor(cbxProveedorPro);
		AutoCompleteDecorator.decorate(cbxProveedorPro);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(326, 26, 759, 447);
		panel_4.add(scrollPane_3);

		TableProducto = new JTable();
		TableProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = TableProducto.rowAtPoint(e.getPoint());
				txtIdPro.setText(TableProducto.getValueAt(fila, 0).toString());
				txtCodigoPro.setText(TableProducto.getValueAt(fila, 1).toString());
				txtDesPro.setText(TableProducto.getValueAt(fila, 2).toString());
				cbxProveedorPro.setSelectedItem(TableProducto.getValueAt(fila, 3).toString());
				txtCantPro.setText(TableProducto.getValueAt(fila, 4).toString());
				txtPrecioPro.setText(TableProducto.getValueAt(fila, 5).toString());
			}
		});
		TableProducto.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "BAR CODE", "DESCRIPTION", "SUPPLIER", "STOCK", "VALUE" }));
		TableProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane_3.setViewportView(TableProducto);

		JButton btnNuevoPro = new JButton("");
		btnNuevoPro.setIcon(new ImageIcon(Systema.class.getResource("/Img/nuevo.png")));
		btnNuevoPro.setBounds(47, 279, 86, 35);
		panel_4.add(btnNuevoPro);

		JButton btnEditarPro = new JButton("");
		btnEditarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ("".equals(txtIdPro.getText())) {
					JOptionPane.showMessageDialog(null, "Select a row");
				} else {

					if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtDesPro.getText())
							|| !"".equals(txtCantPro.getText()) || !"".equals(txtPrecioPro.getText())) {
						pro.setCodigo(txtCodigoPro.getText());
						pro.setNombre(txtDesPro.getText());
						pro.setProveedor(cbxProveedorPro.getSelectedItem().toString());
						pro.setStock(Integer.parseInt(txtCantPro.getText()));
						pro.setPrecio(Double.parseDouble(txtPrecioPro.getText()));
						pro.setId(Integer.parseInt(txtIdPro.getText()));
						prodao.ModificarProductos(pro);
						JOptionPane.showMessageDialog(null, "Product successfully modified");
						LimpiarTable();
						ListarProductos();
						LimpiarProductos();

					} else {
						JOptionPane.showMessageDialog(null, "Los campos estan vacios");
					}
				}

			}
		});
		btnEditarPro.setIcon(new ImageIcon(Systema.class.getResource("/Img/actualizar.png")));
		btnEditarPro.setBounds(47, 326, 86, 35);
		panel_4.add(btnEditarPro);

		JButton btnEliminarPro = new JButton("");
		btnEliminarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!"".equals(txtIdPro.getText())) {
					int pregunta = JOptionPane.showConfirmDialog(null, "Are you sure of delete this?");
					if (pregunta == 0) {
						int id = Integer.parseInt(txtIdPro.getText());
						prodao.EliminarProductos(id);
						LimpiarTable();
						ListarProductos();
						LimpiarProductos();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please choose a row");
				}

			}
		});
		btnEliminarPro.setIcon(new ImageIcon(Systema.class.getResource("/Img/eliminar.png")));
		btnEliminarPro.setBounds(154, 279, 86, 35);
		panel_4.add(btnEliminarPro);

		JButton btnGuardarPro = new JButton("");
		btnGuardarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!"".equals(txtCodigoPro.getText()) || !"".equals(txtDesPro.getText())
						|| !"".equals(cbxProveedorPro.getSelectedItem()) || !"".equals(txtCantPro.getText())
						|| !"".equals(txtPrecioPro.getText())) {
					pro.setCodigo(txtCodigoPro.getText());
					pro.setNombre(txtDesPro.getText());
					pro.setProveedor(cbxProveedorPro.getSelectedItem().toString());
					pro.setStock(Integer.parseInt(txtCantPro.getText()));
					pro.setPrecio(Double.parseDouble(txtPrecioPro.getText()));
					prodao.RegistrarProductos(pro);
					LimpiarTable();
					LimpiarProductos();
					ListarProductos();

					JOptionPane.showMessageDialog(null, "Product registered successfully");

				} else {
					JOptionPane.showMessageDialog(null, "Some fields are empty");
				}
			}
		});
		btnGuardarPro.setIcon(new ImageIcon(Systema.class.getResource("/Img/GuardarTodo.png")));
		btnGuardarPro.setBounds(154, 326, 86, 35);
		panel_4.add(btnGuardarPro);

		JButton btnExcelPro = new JButton("");
		btnExcelPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Excel.reporte();

			}
		});
		btnExcelPro.setIcon(new ImageIcon(Systema.class.getResource("/Img/excel.png")));
		btnExcelPro.setBounds(209, 418, 86, 35);
		panel_4.add(btnExcelPro);

		txtIdpro = new JTextField();
		txtIdpro.setVisible(false);
		txtIdpro.setBounds(253, 24, 12, 20);
		panel_4.add(txtIdpro);
		txtIdpro.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New tab", null, panel_5, null);
		panel_5.setLayout(null);

		JScrollPane scrollPane_3_1 = new JScrollPane();
		scrollPane_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
			}
		});
		scrollPane_3_1.setBounds(165, 82, 755, 391);
		panel_5.add(scrollPane_3_1);

		TableVentas = new JTable();
		TableVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = TableVentas.rowAtPoint(e.getPoint());
				txtIdVenta1.setText(TableVentas.getValueAt(fila, 0).toString());
			}
		});
		TableVentas.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Costumer", "Seller", "Total" }));
		scrollPane_3_1.setViewportView(TableVentas);

		JButton btnPdfVentas = new JButton("");
		btnPdfVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					int id = Integer.parseInt(txtIdVenta1.getText());
					File file = new File("src/pdf/sale"+id+".pdf");
					//System.out.print(id);
					Desktop.getDesktop().open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Logger.getLogger(Systema.class.getName()).log(Level.SEVERE,null,e1);
				}
			}
		});
		btnPdfVentas.setIcon(new ImageIcon(Systema.class.getResource("/Img/pdf.png")));
		btnPdfVentas.setBounds(963, 361, 83, 53);
		panel_5.add(btnPdfVentas);

		txtIdVenta1 = new JTextField();
		txtIdVenta1.setVisible(false);
		txtIdVenta1.setText("");
		txtIdVenta1.setBounds(165, 40, 42, 20);
		panel_5.add(txtIdVenta1);
		txtIdVenta1.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("New tab", null, panel_6, null);
		panel_6.setLayout(null);

		JLabel lblNewLabel_13 = new JLabel("RUC:");
		lblNewLabel_13.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_13.setBounds(607, 69, 46, 14);
		panel_6.add(lblNewLabel_13);

		JLabel lblNewLabel_13_1 = new JLabel("NAME:");
		lblNewLabel_13_1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_13_1.setBounds(607, 143, 46, 14);
		panel_6.add(lblNewLabel_13_1);

		JLabel lblNewLabel_13_2 = new JLabel("PHONE:");
		lblNewLabel_13_2.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_13_2.setBounds(607, 227, 86, 14);
		panel_6.add(lblNewLabel_13_2);

		JLabel lblNewLabel_13_3 = new JLabel("ADDRESS:");
		lblNewLabel_13_3.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_13_3.setBounds(607, 316, 86, 14);
		panel_6.add(lblNewLabel_13_3);

		JLabel lblNewLabel_13_4 = new JLabel("BUSINESS NAME:");
		lblNewLabel_13_4.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 13));
		lblNewLabel_13_4.setBounds(607, 395, 151, 14);
		panel_6.add(lblNewLabel_13_4);

		txtRucConfig = new JTextField();
		txtRucConfig.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtRucConfig.setBounds(586, 94, 235, 20);
		panel_6.add(txtRucConfig);
		txtRucConfig.setColumns(10);

		txtNombreConfig = new JTextField();
		txtNombreConfig.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtNombreConfig.setColumns(10);
		txtNombreConfig.setBounds(586, 168, 235, 20);
		panel_6.add(txtNombreConfig);

		txtTelefonoConfig = new JTextField();
		txtTelefonoConfig.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloNumeros(e);
			}
		});
		txtTelefonoConfig.setColumns(10);
		txtTelefonoConfig.setBounds(586, 252, 235, 20);
		panel_6.add(txtTelefonoConfig);

		txtDireccionConfig = new JTextField();
		txtDireccionConfig.setColumns(10);
		txtDireccionConfig.setBounds(586, 341, 235, 20);
		panel_6.add(txtDireccionConfig);

		txtRazonConfig = new JTextField();
		txtRazonConfig.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.SoloCaracteres(e);
			}
		});
		txtRazonConfig.setColumns(10);
		txtRazonConfig.setBounds(586, 420, 235, 20);
		panel_6.add(txtRazonConfig);

		JLabel lblNewLabel_14 = new JLabel("Company information");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 24));
		lblNewLabel_14.setBounds(10, 12, 566, 72);
		panel_6.add(lblNewLabel_14);

		JButton btnActualizarConfig = new JButton("Update");
		btnActualizarConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!"".equals(txtRucConfig.getText()) || !"".equals(txtNombreConfig.getText())
						|| !"".equals(txtTelefonoConfig.getText()) || !"".equals(txtDireccionConfig.getText())
						|| !"".equals(txtRazonConfig.getText())) {
					conf.setRuc(Integer.parseInt(txtRucConfig.getText()));
					conf.setNombre(txtNombreConfig.getText());
					conf.setTelefono(Integer.parseInt(txtTelefonoConfig.getText()));
					conf.setDireccion(txtDireccionConfig.getText());
					conf.setRazon(txtRazonConfig.getText());
					conf.setId(Integer.parseInt(txtIdConfig.getText()));
					prodao.ModificarDatos(conf);
					JOptionPane.showMessageDialog(null, "Company information modified succesfully");
					ListarConfig();
				} else {
					JOptionPane.showMessageDialog(null, "Some Fields are empty");
				}
				
				
				
				
			}
		});
		btnActualizarConfig.setIcon(new ImageIcon(Systema.class.getResource("/Img/actualizar.png")));
		btnActualizarConfig.setBounds(220, 120, 141, 38);
		panel_6.add(btnActualizarConfig);

		txtIdConfig = new JTextField();
		txtIdConfig.setVisible(false);
		txtIdConfig.setBounds(802, 43, 19, 20);
		panel_6.add(txtIdConfig);
		txtIdConfig.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 255, 47));
		panel.setBounds(0, 0, 188, 797);
		contentPane.add(panel);

		JButton btnNuevaVenta = new JButton("New");
		btnNuevaVenta.setBounds(22, 273, 136, 58);
		btnNuevaVenta.setBackground(new Color(255, 255, 240));
		btnNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_1));
			}
		});
		panel.setLayout(null);
		btnNuevaVenta.setIcon(new ImageIcon(Systema.class.getResource("/Img/Nventa.png")));
		panel.add(btnNuevaVenta);

		JButton btnClientes = new JButton("Costumers");
		btnClientes.setBounds(22, 342, 136, 58);
		btnClientes.setBackground(new Color(255, 255, 240));
		btnClientes.setIcon(new ImageIcon(Systema.class.getResource("/Img/Clientes.png")));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarTable();
				ListarCliente();
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_2));

			}
		});
		panel.add(btnClientes);

		
		JButton btnProveedor = new JButton("Suppliers");
		btnProveedor.setBounds(22, 411, 136, 58);
		btnProveedor.setBackground(new Color(255, 255, 240));
		btnProveedor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				LimpiarTable();
				ListarProveedor();
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_3));

			}
		});
		btnProveedor.setIcon(new ImageIcon(Systema.class.getResource("/Img/proveedor.png")));
		panel.add(btnProveedor);

		JButton btnProductos = new JButton("Stock");
		btnProductos.setBounds(22, 480, 136, 58);
		btnProductos.setBackground(new Color(255, 255, 240));
		btnProductos.setIcon(new ImageIcon(Systema.class.getResource("/Img/producto.png")));
		btnProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimpiarTable();
				ListarProductos();
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_4));
			}
		});
		panel.add(btnProductos);

		JButton btnVentas = new JButton("Sellings");
		btnVentas.setBounds(22, 549, 136, 58);
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_5));
				LimpiarTable();
				ListarVentas();
			}
		});
		btnVentas.setBackground(new Color(255, 255, 240));
		btnVentas.setIcon(new ImageIcon(Systema.class.getResource("/Img/compras.png")));
		panel.add(btnVentas);

		JButton btnConfig = new JButton("Settings");
		btnConfig.setBounds(22, 712, 136, 58);
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(tabbedPane.indexOfComponent(panel_6));
			}
		});
		btnConfig.setBackground(new Color(224, 255, 255));
		btnConfig.setIcon(new ImageIcon(Systema.class.getResource("/Img/config.png")));
		panel.add(btnConfig);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 48, 186, 149);
		lblNewLabel_1.setIcon(new ImageIcon(Systema.class.getResource("/Img/logo.png")));
		panel.add(lblNewLabel_1);
		
		JLabel LabelVendedor_1 = new JLabel("Vendedor: ");
		LabelVendedor_1.setForeground(Color.WHITE);
		LabelVendedor_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		LabelVendedor_1.setBounds(1044, 159, 220, 53);
		contentPane.add(LabelVendedor_1);

		LabelVendedor = new JLabel("Vendedor: ");
		LabelVendedor.setForeground(new Color(255, 255, 255));
		LabelVendedor.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		LabelVendedor.setBounds(1141, 159, 220, 53);
		contentPane.add(LabelVendedor);
		
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
				lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
				lblNewLabel.setIcon(new ImageIcon(Systema.class.getResource("/Img/encabezado.png")));
				lblNewLabel.setBounds(0, 0, 1264, 729);
				contentPane.add(lblNewLabel);
		

	}
	




	


	private void LimpiarCliente() {
		txtIdCliente.setText("");
		txtDniCliente.setText("");
		txtNombreCliente.setText("");
		txtTelefonoCliente.setText("");
		txtDireccionCliente.setText("");
		txtRazonCliente.setText("");

	}

	private void LimpiarProveedor() {
		txtIdProveedor.setText("");
		txtRucProveedor.setText("");
		txtNombreProveedor.setText("");
		txtTelefonoProveedor.setText("");
		txtDireccionProveedor.setText("");
		txtRazonProveedor.setText("");

	}

	private void LimpiarProductos() {
		txtIdPro.setText("");
		txtCodigoPro.setText("");
		cbxProveedorPro.setSelectedItem(null);
		txtDesPro.setText("");
		txtCantPro.setText("");
		txtPrecioPro.setText("");

	}

	private void TotalPagar() {
		Totalpagar = 0.00;
		int numFila = TableVenta.getRowCount();
		for (int i = 0; i < numFila; i++) {
			double cal = Double.parseDouble(String.valueOf(TableVenta.getModel().getValueAt(i, 4)));
			int mult = Integer.parseInt(String.valueOf(TableVenta.getModel().getValueAt(i, 2)));
			Totalpagar = Totalpagar + cal * mult;
		}
		labelTotal.setText(String.format("%.2f", Totalpagar));
	}

	private void LimpiarVenta() {
		txtCodigoVenta.setText("");
		txtDescripcionVenta.setText("");
		txtCantidadVenta.setText("");
		txtStockDisponible.setText("");
		txtPrecioVenta.setText("");
		txtIdVenta1.setText("");
	}

	private void RegistrarVenta() {
		String cliente = txtNombreClienteVenta.getText();
		String vendedor = LabelVendedor.getText();
		double monto = Totalpagar;
		v.setCliente(cliente);
		v.setVendedor(vendedor);
		v.setTotal(monto);
		vdao.RegistrarVenta(v);
	}

	private void RegistrarDetalle() {
		int id = vdao.IdVenta();
		for (int i = 0; i < TableVenta.getRowCount(); i++) {
			String cod = TableVenta.getValueAt(i, 0).toString();
			int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
			double precio = Double.parseDouble(TableVenta.getValueAt(i, 3).toString());
			Dv.setCod_pro(cod);
			Dv.setCantidad(cant);
			Dv.setPrecio(precio);
			Dv.setId(id);
			vdao.RegistrarDetalle(Dv);

		}
	}

	private void ActualizarStock() {
		for (int i = 0; i < TableVenta.getRowCount(); i++) {
			String cod = TableVenta.getValueAt(i, 0).toString();
			int cant = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
			pro = prodao.BuscarPro(cod);
			int StockActual = pro.getStock() - cant;
			vdao.ActualizarStock(StockActual, cod);

		}
	}

	private void LimpiarTableVenta() {
		tmp = (DefaultTableModel) TableVenta.getModel();
		int fila = TableVenta.getRowCount();
		for (int i = 0; i < fila; i++) {
			tmp.removeRow(0);
		}

	}

	private void LimpiarClienteVenta() {

		txtRucVenta.setText("");
		txtNombreClienteVenta.setText("");
		txtTelefonoClienteVenta.setText("");
		txtDireccionClienteVenta.setText("");
		txtRazonClienteVenta.setText("");

	}
	

	

	private void pdf() {

		try {
			int id = vdao.IdVenta();
			FileOutputStream archivo;
			File file = new File("src/pdf/sale" + id + ".pdf");
			archivo = new FileOutputStream(file);
			Document doc = new Document();
			PdfWriter.getInstance(doc, archivo);
			doc.open();
			Image img = Image.getInstance("src/img/logo_pdf.png");
			Paragraph fecha = new Paragraph();
			Font negrita = new Font("Times New Roman", Font.BOLD, 18);
			fecha.add(Chunk.NEWLINE);
			Date date = new Date();
			fecha.add("Factura: " + id + "\n" + "Fecha: " + new SimpleDateFormat("dd-mm-yyyy").format(date) + "\n\n");

			PdfPTable Encabezado = new PdfPTable(4);
			Encabezado.setWidthPercentage(100);
			Encabezado.getDefaultCell().setBorder(0);
			float[] ColumnaEncabezado = new float[] { 20f, 30f, 70f, 40f };
			Encabezado.setWidths(ColumnaEncabezado);
			Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

			Encabezado.addCell(img);

			String ruc = txtRucConfig.getText();
			String nom = txtNombreConfig.getText();
			String tel = txtTelefonoConfig.getText();
			;
			String dir = txtDireccionConfig.getText();
			;
			String ra = txtRazonConfig.getText();
			;

			Encabezado.addCell("");
			Encabezado.addCell("Ruc: " + ruc + "\nNombre: " + nom + "\nTelefono: " + tel + "\nDireccion: " + dir
					+ "\nRazon: " + ra);
			Encabezado.addCell(fecha);
			doc.add(Encabezado);

			Paragraph cli = new Paragraph();
			cli.add(Chunk.NEWLINE);
			cli.add("Customer data" + "\n\n");
			doc.add(cli);

			PdfPTable tablacli = new PdfPTable(4);
			tablacli.setWidthPercentage(100);
			tablacli.getDefaultCell().setBorder(0);
			float[] Columnacli = new float[] { 20f, 50f, 30f, 40f };
			tablacli.setWidths(Columnacli);
			tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cl1 = new PdfPCell(new Phrase("Dni"));
			PdfPCell cl2 = new PdfPCell(new Phrase("Name"));
			PdfPCell cl3 = new PdfPCell(new Phrase("Phone"));
			PdfPCell cl4 = new PdfPCell(new Phrase("Address"));
			cl1.setBorder(0);
			cl2.setBorder(0);
			cl3.setBorder(0);
			cl4.setBorder(0);
			tablacli.addCell(cl1);
			tablacli.addCell(cl2);
			tablacli.addCell(cl3);
			tablacli.addCell(cl4);
			tablacli.addCell(txtRucVenta.getText());
			tablacli.addCell(txtNombreClienteVenta.getText());
			tablacli.addCell(txtTelefonoClienteVenta.getText());
			tablacli.addCell(txtDireccionClienteVenta.getText());
			doc.add(tablacli);

			////////////////////////////////////////////////////////////

			PdfPTable tablapro = new PdfPTable(4);
			tablapro.setWidthPercentage(100);
			tablapro.getDefaultCell().setBorder(0);
			float[] Columnapro = new float[] { 10f, 50f, 15f, 20f };
			tablapro.setWidths(Columnapro);
			tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell pro1 = new PdfPCell(new Phrase("Quant."));
			PdfPCell pro2 = new PdfPCell(new Phrase("Description"));
			PdfPCell pro3 = new PdfPCell(new Phrase("Value U"));
			PdfPCell pro4 = new PdfPCell(new Phrase("Value T"));
			pro1.setBorder(0);
			pro1.setBorder(0);
			pro3.setBorder(0);
			pro4.setBorder(0);
			pro1.setBackgroundColor(BaseColor.DARK_GRAY);
			pro2.setBackgroundColor(BaseColor.DARK_GRAY);
			pro3.setBackgroundColor(BaseColor.DARK_GRAY);
			pro4.setBackgroundColor(BaseColor.DARK_GRAY);
			tablapro.addCell(pro1);
			tablapro.addCell(pro2);
			tablapro.addCell(pro3);
			tablapro.addCell(pro4);
			for (int i = 0; i < TableVenta.getRowCount(); i++) {
				String producto = TableVenta.getValueAt(i, 1).toString();
				String cantidad = TableVenta.getValueAt(i, 2).toString();
				String precio = TableVenta.getValueAt(i, 3).toString();
				String total = TableVenta.getValueAt(i, 4).toString();
				tablapro.addCell(cantidad);
				tablapro.addCell(producto);
				tablapro.addCell(precio);
				tablapro.addCell(total);

			}

			doc.add(tablapro);

			Paragraph info = new Paragraph();
			info.add(Chunk.NEWLINE);
			info.add("Overall " + Totalpagar);
			info.setAlignment(Element.ALIGN_RIGHT);
			doc.add(info);

			Paragraph firma = new Paragraph();
			firma.add(Chunk.NEWLINE);
			firma.add("Signature \n\n");
			firma.add("____________________");
			firma.setAlignment(Element.ALIGN_CENTER);
			doc.add(firma);

			Paragraph mensaje = new Paragraph();
			mensaje.add(Chunk.NEWLINE);
			mensaje.add("Thanks for your preference");
			mensaje.setAlignment(Element.ALIGN_CENTER);
			doc.add(mensaje);
			doc.close();
			archivo.close();
			Desktop.getDesktop().open(file);
		} catch (DocumentException | IOException e) {
			System.out.println(e.toString());
		}

	}
}
