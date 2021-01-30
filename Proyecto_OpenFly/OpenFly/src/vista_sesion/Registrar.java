package vista_sesion;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import com.toedter.calendar.JDateChooser;
import libreria.Diseño;
import libreria.insertarImagen;
import mantenimientos.GestionPasajeros;
import model.Cliente;
import vista_reserva.ReservarVuelo;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.independentsoft.share.Service;
import com.independentsoft.share.ServiceException;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JProgressBar;

public class Registrar extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private JLabel lblRegistrarUsuario;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JLabel lblFechaDeNacimeinto;
	private JLabel lblCorreo;
	private JButton btnRegistrar;
	private JButton btnSalir;
	private JLabel lblTipoDeDocumento;
	@SuppressWarnings("rawtypes")
	private JComboBox txtPais;
	@SuppressWarnings("unused")
	private JLabel lblN;
	private JTextField txtNDocumento;
	private JLabel lblImagen;
	private JButton btnAadir;
	private JLabel lblTelefono;
	private JTextField txtTelef;
	private JLabel lblPais;
	@SuppressWarnings("rawtypes")
	private JComboBox ComboDocumento;
	private JDateChooser txtFecha;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblContrasea;
	private JLabel lblRepitaContrasea;
	public static File fichero;
	public static String image;
	private JLabel lblSexo;
	private JRadioButton rdFemenino;
	private JRadioButton rfMasculino;
	private JTextField txtCorreo;
	private JPasswordField txtContraseña;
	private JPasswordField txtContraseña01;
	private JButton btnOmitir;
	private JPanel contentPane;
	private JLabel lblFondo;
	public String nombre,apellido,usuario;
	public String contraseña,correo;
	public String pais;
	public String sexo;
	public String fecha;
	public String tipoDoc;
	public String Ndoc;
	public String telefono;
	public int itemselect;
	private JCheckBox cbMostrar;
	@SuppressWarnings("unused")
	private JCheckBox cbMostrar1;
	private JLabel lblNewLabel;
	private JLabel lblalMenosUna;
	private JLabel lblalMenosUna_1;
	private JLabel lblunCarecterEspecial;
	private JLabel lblunNmero;
	private JRadioButton radioButton;
	private JProgressBar barra;
	/*---------------------------------------*/
	Iniciarsesion ini=new Iniciarsesion();
	Diseño dise=new Diseño();
	GestionPasajeros gp=new GestionPasajeros();
	
	List<Cliente> data;
	Cliente cliente=new Cliente();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrar frame = new Registrar();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Registrar() {
		setTitle("OpenFly\u00AE - REGISTRO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registrar.class.getResource("/icon/logoOP.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 601, 547);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		/*-----------DISEÑO-----------*/
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", new Color(175, 238, 238));
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
		{
			radioButton = new JRadioButton("");
			radioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			radioButton.setIcon(new ImageIcon(Registrar.class.getResource("/icon/ojo.png")));
			radioButton.setOpaque(false);
			radioButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					do_radioButton_itemStateChanged(arg0);
				}
			});
			radioButton.setBounds(250, 401, 25, 25);
			contentPane.add(radioButton);
		}
		{
			barra = new JProgressBar(1,100);
			barra.setStringPainted(true);
			barra.setValue(0);
			barra.setForeground(new Color(123, 104, 238));
			barra.setBorder(new LineBorder(new Color(0, 0, 0)));
			barra.setBounds(318, 185, 120, 23);
			contentPane.add(barra);
		}
		{
			lblRegistrarUsuario = new JLabel("REGISTRAR CLIENTE");
			lblRegistrarUsuario.setFont(new Font("Dubai Light", Font.BOLD, 17));
			lblRegistrarUsuario.setBounds(217, 17, 167, 30);
			contentPane.add(lblRegistrarUsuario);
		}
		{
			lblNombre = new JLabel("Nombres");
			lblNombre.setForeground(new Color(0, 0, 0));
			lblNombre.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblNombre.setBounds(43, 52, 52, 16);
			contentPane.add(lblNombre);
		}
		{
			lblApellido = new JLabel("Apellidos");
			lblApellido.setForeground(new Color(0, 0, 0));
			lblApellido.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblApellido.setBounds(43, 102, 59, 21);
			contentPane.add(lblApellido);
		}
		{
			txtNombre = new JTextField();
			txtNombre.addKeyListener(this);
			txtNombre.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtNombre.setBounds(39, 74, 236, 25);
			contentPane.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			txtApellido = new JTextField();
			txtApellido.addKeyListener(this);
			txtApellido.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtApellido.setBounds(39, 125, 236, 25);
			contentPane.add(txtApellido);
			txtApellido.setColumns(10);
		}
		{
			lblFechaDeNacimeinto = new JLabel("Fecha de Nacimiento");
			lblFechaDeNacimeinto.setForeground(new Color(0, 0, 0));
			lblFechaDeNacimeinto.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblFechaDeNacimeinto.setBounds(39, 159, 135, 21);
			contentPane.add(lblFechaDeNacimeinto);
		}
		{
			lblCorreo = new JLabel("Correo");
			lblCorreo.setForeground(new Color(0, 0, 0));
			lblCorreo.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblCorreo.setBounds(301, 271, 51, 21);
			contentPane.add(lblCorreo);
		}
		{
			btnRegistrar = new JButton("REGISTRAR");
			btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRegistrar.setFont(new Font("Dubai", Font.BOLD, 11));
			btnRegistrar.setForeground(Color.WHITE);
			btnRegistrar.setBackground(Color.DARK_GRAY);
			btnRegistrar.addActionListener(this);
			btnRegistrar.setBounds(153, 457, 129, 34);
			contentPane.add(btnRegistrar);
		}
		{
			btnSalir = new JButton("SALIR");
			btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSalir.setFont(new Font("Dubai", Font.BOLD, 11));
			btnSalir.setForeground(Color.WHITE);
			btnSalir.setBackground(Color.DARK_GRAY);
			btnSalir.addActionListener(this);
			btnSalir.setBounds(298, 457, 131, 34);
			contentPane.add(btnSalir);
		}
		{
			lblTipoDeDocumento = new JLabel("Documento\r\n");
			lblTipoDeDocumento.setForeground(new Color(0, 0, 0));
			lblTipoDeDocumento.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblTipoDeDocumento.setBounds(39, 215, 79, 21);
			contentPane.add(lblTipoDeDocumento);
		}
		{
			txtPais = new JComboBox();
			txtPais.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_txtPais_actionPerformed(e);
				}
			});
			txtPais.setBackground(Color.WHITE);
			txtPais.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un Pais", "Argentina (+54)", "Bolivia (+591)", "Brasil (+55)", "Chile (+56)", "Colombia (+57)", "Costa Rica (+506)", "Cuba (+53)", "Ecuador (+593)", "Guatemala (+502)", "Honduras (+504)", "Jamaica (+876)", "M\u00E9xico (+52)", "Nicaragua (+505)", "Paraguay (+595)", "Panam\u00E1 (+507)", "Per\u00FA (+51)", "Puerto Rico (+1787)", "Rep.  Dominicana (+809)", "Uruguay (+598)", "Venezuela (+58)"}));
			txtPais.setBounds(301, 239, 243, 25);
			contentPane.add(txtPais);
		}
		{
			txtNDocumento = new JTextField();
			txtNDocumento.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					do_txtNDocumento_keyTyped(arg0);
				}
			});
			txtNDocumento.setBackground(Color.WHITE);
			txtNDocumento.setEnabled(false);
			txtNDocumento.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtNDocumento.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtNDocumento.setColumns(10);
			txtNDocumento.setBounds(147, 239, 129, 25);
			contentPane.add(txtNDocumento);
		}
		{
			lblImagen = new JLabel("");
			lblImagen.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblImagen.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblImagen.setBounds(320, 64, 118, 120);
			contentPane.add(lblImagen);
		}
		{
			btnAadir = new JButton("A\u00D1ADIR");
			btnAadir.setEnabled(false);
			btnAadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAadir.setForeground(Color.WHITE);
			btnAadir.setBackground(new Color(70, 130, 180));
			btnAadir.setFont(new Font("Dubai", Font.BOLD, 9));
			btnAadir.addActionListener(this);
			btnAadir.setBounds(454, 93, 76, 34);
			contentPane.add(btnAadir);
		}
		{
			lblTelefono = new JLabel("Tel\u00E9fono");
			lblTelefono.setForeground(new Color(0, 0, 0));
			lblTelefono.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblTelefono.setBounds(39, 271, 65, 21);
			contentPane.add(lblTelefono);
		}
		{
			txtTelef = new JTextField();
			txtTelef.setBackground(Color.WHITE);
			txtTelef.setEnabled(false);
			txtTelef.addKeyListener(this);
			txtTelef.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtTelef.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtTelef.setBounds(39, 291, 236, 25);
			contentPane.add(txtTelef);
			txtTelef.setColumns(10);
		}
		{
			lblPais = new JLabel("Pa\u00EDs");
			lblPais.setForeground(new Color(0, 0, 0));
			lblPais.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblPais.setBounds(301, 215, 30, 21);
			contentPane.add(lblPais);
		}
		{
			ComboDocumento = new JComboBox();
			ComboDocumento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_ComboDocumento_actionPerformed(arg0);
				}
			});
			ComboDocumento.setBackground(Color.WHITE);
			ComboDocumento.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "DNI", "Pasaporte"}));
			ComboDocumento.setBounds(39, 239, 96, 25);
			contentPane.add(ComboDocumento);
		}
		{
			txtFecha = new JDateChooser();
			txtFecha.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			txtFecha.getCalendarButton().setIcon(new ImageIcon(Registrar.class.getResource("/icon/calendario.png")));
			txtFecha.getCalendarButton().setBackground(Color.WHITE);
			txtFecha.setBounds(39, 183, 125, 25);
			txtFecha.setMaxSelectableDate(new Date());
			((JTextField)txtFecha.getDateEditor()).setEditable(false);
			((JTextField)txtFecha.getDateEditor()).setBackground(Color.WHITE);
			contentPane.add(txtFecha);
		}
		{
			lblUsuario = new JLabel("Usuario");
			lblUsuario.setForeground(new Color(0, 0, 0));
			lblUsuario.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblUsuario.setBounds(36, 326, 55, 21);
			contentPane.add(lblUsuario);
		}
		{
			txtUsuario = new JTextField();
			txtUsuario.addKeyListener(this);
			txtUsuario.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtUsuario.setColumns(10);
			txtUsuario.setBounds(39, 347, 236, 25);
			contentPane.add(txtUsuario);
		}
		{
			lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setForeground(new Color(0, 0, 0));
			lblContrasea.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblContrasea.setBounds(301, 326, 83, 21);
			contentPane.add(lblContrasea);
		}
		{
			lblRepitaContrasea = new JLabel("Repita contrase\u00F1a");
			lblRepitaContrasea.setForeground(new Color(0, 0, 0));
			lblRepitaContrasea.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblRepitaContrasea.setBounds(39, 382, 125, 21);
			contentPane.add(lblRepitaContrasea);
		}
		{
			lblSexo = new JLabel("Sexo");
			lblSexo.setForeground(new Color(0, 0, 0));
			lblSexo.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblSexo.setBounds(180, 159, 33, 21);
			contentPane.add(lblSexo);
		}
		{
			rdFemenino = new JRadioButton("F");
			rdFemenino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rdFemenino.setFont(new Font("Tahoma", Font.BOLD, 11));
			rdFemenino.setBackground(Color.LIGHT_GRAY);
			rdFemenino.addActionListener(this);
			rdFemenino.setBounds(180, 185, 39, 23);
			contentPane.add(rdFemenino);
			rdFemenino.setOpaque(false);
			rdFemenino.setBackground(new Color(0,0,0,0));
		}
		{
			rfMasculino = new JRadioButton("M");
			rfMasculino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rfMasculino.setFont(new Font("Tahoma", Font.BOLD, 11));
			rfMasculino.setBackground(Color.LIGHT_GRAY);
			rfMasculino.addActionListener(this);
			rfMasculino.setBounds(223, 185, 52, 23);
			contentPane.add(rfMasculino);
			rfMasculino.setOpaque(false);
			rfMasculino.setBackground(new Color(0,0,0,0));
		}
		{
			txtCorreo = new JTextField();
			txtCorreo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					do_txtCorreo_keyTyped(e);
				}
			});
			txtCorreo.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtCorreo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtCorreo.setColumns(10);
			txtCorreo.setBounds(301, 291, 243, 25);
			contentPane.add(txtCorreo);
		}
		{
			txtContraseña = new JPasswordField();
			txtContraseña.addKeyListener(this);
			txtContraseña.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtContraseña.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtContraseña.setAlignmentX(Component.LEFT_ALIGNMENT);
			txtContraseña.setBounds(300, 347, 220, 25);
			contentPane.add(txtContraseña);
		}
		{
			txtContraseña01 = new JPasswordField();
			txtContraseña01.addKeyListener(this);
			txtContraseña01.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtContraseña01.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtContraseña01.setBounds(39, 401, 208, 25);
			contentPane.add(txtContraseña01);
		}
		{
			btnOmitir = new JButton("OMITIR");
			btnOmitir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnOmitir.setForeground(Color.WHITE);
			btnOmitir.setBackground(new Color(70, 130, 180));
			btnOmitir.setFont(new Font("Dubai", Font.BOLD, 9));
			btnOmitir.addActionListener(this);
			btnOmitir.setBounds(454, 138, 76, 34);
			btnOmitir.setEnabled(false);
			contentPane.add(btnOmitir);
		}
		{
			cbMostrar = new JCheckBox("");
			cbMostrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cbMostrar.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					do_cbMostrar_itemStateChanged(e);
				}
			});
			cbMostrar.setIcon(new ImageIcon(Registrar.class.getResource("/icon/ojo.png")));
			cbMostrar.setBounds(528, 347, 25, 25);
			contentPane.add(cbMostrar);
			cbMostrar.setOpaque(false);
			cbMostrar.setBackground(new Color(0,0,0,0));
		}
		{
			lblNewLabel = new JLabel("- M\u00EDnimo de ocho caracteres\r\n");
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setFont(new Font("Dubai", Font.PLAIN, 10));
			lblNewLabel.setBounds(301, 382, 134, 14);
			contentPane.add(lblNewLabel);
		}
		{
			lblalMenosUna = new JLabel("- Al menos una letra may\u00FAscula\r\n");
			lblalMenosUna.setForeground(new Color(0, 0, 0));
			lblalMenosUna.setFont(new Font("Dubai", Font.PLAIN, 10));
			lblalMenosUna.setBounds(301, 396, 149, 14);
			contentPane.add(lblalMenosUna);
		}
		{
			lblalMenosUna_1 = new JLabel("- Al menos una letra min\u00FAscula");
			lblalMenosUna_1.setForeground(new Color(0, 0, 0));
			lblalMenosUna_1.setFont(new Font("Dubai", Font.PLAIN, 10));
			lblalMenosUna_1.setBounds(301, 409, 145, 14);
			contentPane.add(lblalMenosUna_1);
		}
		{
			lblunCarecterEspecial = new JLabel("- Un carecter especial ");
			lblunCarecterEspecial.setForeground(new Color(0, 0, 0));
			lblunCarecterEspecial.setFont(new Font("Dubai", Font.PLAIN, 10));
			lblunCarecterEspecial.setBounds(446, 396, 107, 14);
			contentPane.add(lblunCarecterEspecial);
		}
		{
			lblunNmero = new JLabel("- Un n\u00FAmero");
			lblunNmero.setForeground(new Color(0, 0, 0));
			lblunNmero.setFont(new Font("Dubai", Font.PLAIN, 10));
			lblunNmero.setBounds(447, 382, 59, 14);
			contentPane.add(lblunNmero);
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 585, 519);
			contentPane.add(lblFondo);
			dise.img(Registrar.class.getResource("/img/fondo5.jpg"), lblFondo);
		}
		
	}	
	/*-------------------------EVENTOS---------------------*/
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtContraseña01) {
			keyTypedTxtContraseña01(e);
		}
		if (e.getSource() == txtContraseña) {
			keyTypedTxtContraseña(e);
		}
		if (e.getSource() == txtUsuario) {
			keyTypedTxtUsuario(e);
		}
		if (e.getSource() == txtTelef) {
			keyTypedTxtTelef(e);
		}
		if (e.getSource() == txtApellido) {
			keyTypedTxtApellido(e);
		}
		if (e.getSource() == txtNombre) {
			keyTypedTxtNombre(e);
		}
	}
	protected void keyTypedTxtNombre(KeyEvent e) {
		char c=e.getKeyChar();
		if(Character.isLetter(c)){
		}else{
			e.consume();
		}
		if(txtNombre.getText().length()>40){
			e.consume();
		}
	}
	protected void keyTypedTxtApellido(KeyEvent e) {
		char c=e.getKeyChar();
		if(Character.isLetter(c)){
		}else{
			e.consume();
		}
		if(txtApellido.getText().length()>40){
			e.consume();
		}
	}
	protected void keyTypedTxtTelef(KeyEvent e) {
		char c = e.getKeyChar();
		if(c<'0' || c>'9')e.consume();
		if(txtTelef.getText().length()>15){
			e.consume();
		}
	}
	protected void keyTypedTxtUsuario(KeyEvent e) {
		if(txtUsuario.getText().length()>20){
			e.consume();
		}
	}	
	@SuppressWarnings("deprecation")
	protected void keyTypedTxtContraseña(KeyEvent e) {
		if(txtContraseña.getText().length()>20){
			e.consume();
		}
	}
	@SuppressWarnings("deprecation")
	protected void keyTypedTxtContraseña01(KeyEvent e) {
		if(txtContraseña01.getText().length()>20){
			e.consume();
		}
	}
	protected void do_txtNDocumento_keyTyped(KeyEvent arg0) {
		char x=arg0.getKeyChar();
		if(ComboDocumento.getSelectedIndex()==1){
			if(Character.isDigit(x)){	
			}else{
				arg0.consume();
				getToolkit().beep();
			}
		}else{
			if(Character.isDigit(x) || Character.isLetter(x)){	
			}else{
				arg0.consume();
				getToolkit().beep();
			}
		}
		if(txtNDocumento.getText().length()>7){
			arg0.consume();
		}
	}
	protected void do_txtCorreo_keyTyped(KeyEvent e) {
		if(txtCorreo.getText().length()>30){
			e.consume();
		}
	}
	protected void do_radioButton_itemStateChanged(ItemEvent arg0) {
		if (arg0.getStateChange() == ItemEvent.SELECTED) {
            //txtContraseña.setEchoChar('*');
            txtContraseña01.setEchoChar((char) 0);
        } else {
        	txtContraseña01.setEchoChar('•');
        }
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnOmitir) {
			actionPerformedBtnOmitir(arg0);
		}
		if (arg0.getSource() == rfMasculino) {
			actionPerformedRadio2(arg0);
		}
		if (arg0.getSource() == rdFemenino) {
			actionPerformedRadio1(arg0);
		}
		if (arg0.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(arg0);
		}
		if (arg0.getSource() == btnSalir) {
			actionPerformedBtnSalir(arg0);
		}
		if (arg0.getSource() == btnAadir) {
			actionPerformedBtnAadir(arg0);
		}
	}
	/*---------------------------------------BOTONES------------------------------------*/
	protected void do_ComboDocumento_actionPerformed(ActionEvent arg0) {
		if(ComboDocumento.getSelectedIndex()!=0){
			txtNDocumento.setEnabled(true);
			txtNDocumento.requestFocus();
		}else{
			txtNDocumento.setEnabled(false);
		}
		
	}
	protected void do_txtPais_actionPerformed(ActionEvent e) {
		if(txtPais.getSelectedIndex()!=0){
			txtTelef.setEnabled(true);
			txtTelef.requestFocus();
		}else{
			txtTelef.setEnabled(false);
		}
	}
	@SuppressWarnings("deprecation")
	protected void actionPerformedBtnRegistrar(ActionEvent arg0) {		
		ReservarVuelo.ventanaMensaje();
		//NOMBRE 
		Pattern n = Pattern.compile("[A-Za-z_ ]{1,40}");
		Matcher N = n.matcher(txtNombre.getText());
		if (N.matches()==true && txtNombre.getText().isEmpty()==false) {
			nombre=txtNombre.getText();
		} else{
			Mensaje("Ingrese correctamente su nombre.");
			return;
		}	
		//APELLIDO
		Pattern a = Pattern.compile("[A-Za-z_ ]{1,40}");
		Matcher A = a.matcher(txtApellido.getText());
		if (A.matches()==true && txtApellido.getText().isEmpty()==false) {
			apellido=txtApellido.getText();
		} else{
			Mensaje("Ingrese correctamente su apellido.");
			return;
		}	
		//FECHA DE NACIMIENTO
		Date fechaSis=new Date();
		Date fechaSiformato=txtFecha.getDate();			
		if(fechaSis.getYear()-fechaSiformato.getYear()>=18){
			SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
			fecha=formato.format(fechaSiformato);
		} else{
			Mensaje( "Usted no es mayor de edad!.");
			return;
		}			
		//SEXO
		if(rdFemenino.isSelected()==false && rfMasculino.isSelected()==false){
			Mensaje("No ha seleccionado tipo de sexo!.");
			return;
		}
		 else
			if(rdFemenino.isSelected()==true)
				sexo="F";
			else 
				if(rfMasculino.isSelected()==true)
					sexo="M";
		
		//TIPO DE DOCUMENTO
		if(ComboDocumento.getSelectedIndex()==0){
			Mensaje("Elija un tipo de documento!.");
			return;
		} else
			if(ComboDocumento.getSelectedIndex()==1)
				tipoDoc="DNI";
			else
				if(ComboDocumento.getSelectedIndex()==1)
					tipoDoc="Pasaporte";
		
		//NUMERO DE DOCUMENTO
		if(ComboDocumento.getSelectedIndex()==1){
			Pattern t = Pattern.compile("[0-9]{8}");
			Matcher T = t.matcher(txtNDocumento.getText());
			if (T.matches()==true && txtNDocumento.getText().isEmpty()==false) {
				Ndoc = txtNDocumento.getText();	
			} else{
				Mensaje( "Ingrese correctamente su N° de DNI.");
				return;
			}
		} else if(ComboDocumento.getSelectedIndex()==2){
			Pattern t = Pattern.compile("[A-Z]{2}[0-9]{6}");
			Matcher T = t.matcher(txtNDocumento.getText());
			if (T.matches()==true && txtNDocumento.getText().isEmpty()==false) {
				Ndoc = txtNDocumento.getText();	
			} else{
				Mensaje( "Ingrese correctamente su N° de pasaporte.");
				return;
			}
		}
		if(validarCliente(Ndoc)){
			Mensaje("Cliente ya registrado");
			return;
		}
		//PAIS
		telefono="";
		itemselect=txtPais.getSelectedIndex();		
		if(itemselect==0){
			JOptionPane.showMessageDialog(null, "Seleccione un país.");
			return;
		}
		else{			
			if(itemselect==1){
				telefono+="+54";
			}
			if(itemselect==2){
				telefono+="+591";
			}
			if(itemselect==3){
				telefono+="+55";
			}
			if(itemselect==4){
				telefono+="+56";
			}
			if(itemselect==5){
				telefono+="+57";
			}
			if(itemselect==6){
				telefono+="+506";
			}
			if(itemselect==7){
				telefono+="+53";
			}
			if(itemselect==8){
				telefono+="+593";
			}
			if(itemselect==9){
				telefono+="+502";
			}
			if(itemselect==10){
				telefono+="+504";
			}
			if(itemselect==11){
				telefono+="+876";
			}
			if(itemselect==12){
				telefono+="+52";
			}
			if(itemselect==13){
				telefono+="+505";
			}
			if(itemselect==14){
				telefono+="+595";
			}
			if(itemselect==15){
				telefono+="+507";
			}
			if(itemselect==16){
				telefono+="+51";
			}
			if(itemselect==17){
				telefono+="+1787";
			}
			if(itemselect==18){
				telefono+="+809";
			}
			if(itemselect==19){
				telefono+="+598";
			}
			if(itemselect==20){
				telefono+="+58";
			}			
			//TELEFONO
			Pattern t = Pattern.compile("[0-9]{1,15}");
			Matcher T = t.matcher(txtTelef.getText());
			if (T.matches()==true && txtTelef.getText().isEmpty()==false) {
				telefono=txtTelef.getText();				
				
			} else{
				JOptionPane.showMessageDialog(null, "El número de teléfono es incorrecto.");
				return;
			}
		}		
		//CORREO
		Pattern cr = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher CR = cr.matcher(txtCorreo.getText());
		if (CR.matches()==true && txtCorreo.getText().isEmpty()==false) {
			correo=txtCorreo.getText();
		} else{
			JOptionPane.showMessageDialog(null, "Esta ingresando un correo incorrecto.");
			return;
		}
		if(validarCorreo(correo)){
			Mensaje("Correo ya registrado.");
			return;
		}
		//USUARIO
		Pattern u = Pattern.compile("^[_a-zA-Z0-9-ñ]{5,20}");
		Matcher U = u.matcher(txtUsuario.getText());
		if (U.matches()==true && txtUsuario.getText().isEmpty()==false) {
			usuario=txtUsuario.getText();
		} else{
			JOptionPane.showMessageDialog(null, "El nombre de usuario es incorrecto.");
			return;
		}
		if(validarUsuario(usuario)){
			Mensaje("Usuario ya creado.");
			return;
		}
		//CONTRASEÑA
		//Mínimo de ocho caracteres, al menos una letra mayúscula, una letra minúscula, un carecter especial y  un número:
		Pattern con = Pattern.compile("^.*(?=.{8,20})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_?¿@#$%^&+=]).*$");
		Matcher CON = con.matcher(txtContraseña.getText());
		if (CON.matches()==true && txtContraseña.getText().isEmpty()==false) {
			if(txtContraseña01.getText().equals(txtContraseña.getText())){
				contraseña=txtContraseña.getText();
			}else {
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Contraseña incorrecta, vuelva a ingresar.");
			return;
		}
		
		if(validarCliente(Ndoc) && validarUsuario(usuario)){
			Mensaje("Ya estas registrado");
			return;
		}else{
			
		gp.AgregarCliente(generarCodigo(),tipoDoc , Ndoc, nombre, apellido, fecha,
				 sexo, telefono, correo,pais(txtPais.getSelectedIndex()), usuario, contraseña);	
		}
		JOptionPane.showMessageDialog(null, "Datos Registrados. Ahora suba una foto!...");
		/*INGRESARLO A LA BD*/
		deshabilitar();
		
	}
	String nombreArchivoUsu;
	@SuppressWarnings("static-access")
	protected void actionPerformedBtnAadir(ActionEvent arg0) {
		UIManager.put("Button.background", new Color(70, 130, 180));
		ReservarVuelo.ventanaMensaje();
		int resultado;
		insertarImagen ventana=new insertarImagen();
		FileNameExtensionFilter filtro=new FileNameExtensionFilter("JPG","jpg");
		ventana.getCargarFoto().setFileFilter(filtro);
		resultado=ventana.getCargarFoto().showOpenDialog(null);
		nombreArchivoUsu="foto"+gp.BuscarClienteXusu(usuario).getCodClie()+".jpg";
		if(ventana.getCargarFoto().APPROVE_OPTION==resultado){
			fichero=ventana.getCargarFoto().getSelectedFile();
		}
		if(fichero!=null){
			btnAadir.setEnabled(false);
			btnOmitir.setEnabled(false);
			Thread t=new Thread(new Runnable() {
				public void run() {
					try
			    	{
			    		Service service = new Service("https://cibertecedu-my.sharepoint.com/personal/i201910562_cibertec_edu_pe",
			            		"i201910562@cibertec.edu.pe", "imCaLaX590");
			    		FileInputStream fileStream = null;
				    	
				        try
				        {
				        	fileStream = new FileInputStream(fichero);
				        	service.createFile("/personal/i201910562_cibertec_edu_pe/Documents/BD_OPENFLY/"+nombreArchivoUsu, fileStream);
				        }
				    	finally
				    	{
				    		if(fileStream != null)
				    		{
				    			fileStream.close();
				    		}
				    		//JOptionPane.showMessageDialog(null, "ARCHIVO SUBIDO!.");		    		
				    	}
			        } 
			        catch (ServiceException ex)
			        {
			        	System.out.println("Error: " + ex.getMessage());
			        	System.out.println("Error: " + ex.getErrorCode());
			        	System.out.println("Error: " + ex.getErrorString());
			        	System.out.println("Error: " + ex.getRequestUrl());
			        	ex.printStackTrace();
			        }
			        catch (IOException ex)
			        {
			        	System.out.println("Error: " + ex.getMessage());	        	
			        	ex.printStackTrace();
			        }			
								
					try
			    	{
			            Service service = new Service("https://cibertecedu-my.sharepoint.com/personal/i201910562_cibertec_edu_pe",
			            		"i201910562@cibertec.edu.pe", "imCaLaX590");		
			            InputStream inputStream = service.getFileStream("/personal/i201910562_cibertec_edu_pe/Documents/BD_OPENFLY/"+nombreArchivoUsu);
			            FileOutputStream outputStream = new FileOutputStream("src/img/"+nombreArchivoUsu);		    	
				        try
				        {
			                byte[] buffer = new byte[8192];
			                int len = 0;
			                while ((len = inputStream.read(buffer, 0, buffer.length)) > 0)
			                {
			                    outputStream.write(buffer, 0, len);
			                }
				        }
				    	finally
				    	{   		
				    		if(inputStream != null)
				    		{
				    			inputStream.close();
				    		}		    		
				    		if(outputStream != null)
				    		{
				    			outputStream.close();
				    		}
				    	}
			        } 
			        catch (ServiceException ex)
			        {
			        	System.out.println("Error: " + ex.getMessage());
			        	System.out.println("Error: " + ex.getErrorCode());
			        	System.out.println("Error: " + ex.getErrorString());
			        	System.out.println("Error: " + ex.getRequestUrl());

			        	ex.printStackTrace();
			        }
			        catch (IOException ex)
			        {
			        	System.out.println("Error: " + ex.getMessage());
			        	
			        	ex.printStackTrace();
			        }	
				}
			});	
			t.start();
			Thread t2=new Thread(new Runnable() {
				public void run() {
					try {
						for (int i = 0; i <=100; i++) {
							barra.setValue(i);
							barra.repaint();
							if(i==100){
								btnRegistrar.setEnabled(false);
								btnSalir.setEnabled(true);
								mostrar(nombreArchivoUsu);	
							}
							Thread.sleep(85);
						}
					} catch (Exception e) {
							// TODO: handle exception
					}
				}
			});
			t2.start();
		}
		
		/*
		 **************************VALIDAR Y/O ACTULIZAR LA FOTO ELIMINANDO Y REEMPLAZANDO EN EL ONEDRIVE
		 * GUARADAR LA FOTO COMO DATO EN LA BASE DED DATOS
		 * CONSULTAR LA FOTO DE LA BASE DE DATOS
		 * INGRESAR TODOS LOS DATOS A LA BASE DE DATOS
		 * CONVALIDAR QUE NO HAYA REPETICION DE USUARIO Y DOCUMENTO
		 */
	}
	private void mostrar(String nombreArchivoUsu){
		imagenLabel("src/img/"+nombreArchivoUsu, lblImagen);
		//btnAadir.setEnabled(false);
		JOptionPane.showMessageDialog(null, "FOTO SUBIDA!");
	}
	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		UIManager.put("Button.background", new Color(70, 130, 180));
		Iniciarsesion sg=new Iniciarsesion();
		sg.toFront();
		sg.setVisible(true);
		this.dispose();
	}
	protected void actionPerformedRadio1(ActionEvent arg0) {
		rfMasculino.setSelected(false);;
	}
	protected void actionPerformedRadio2(ActionEvent arg0) {
		rdFemenino.setSelected(false);;
	}
	protected void actionPerformedBtnOmitir(ActionEvent arg0) {
		UIManager.put("Button.background", new Color(70, 130, 180));
		if(JOptionPane.showConfirmDialog(null, "Se le asignará un perfil predeterminado,\n acepta?...","",
				JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
			nombreArchivoUsu="foto"+gp.BuscarClienteXusu(usuario).getCodClie()+".jpg";
			btnAadir.setEnabled(false);
			btnOmitir.setEnabled(false);
			Thread t=new Thread(new Runnable() {
				public void run() {
					try
			    	{
			    		Service service = new Service("https://cibertecedu-my.sharepoint.com/personal/i201910562_cibertec_edu_pe",
			            		"i201910562@cibertec.edu.pe", "imCaLaX590");    		
				    	FileInputStream fileStream = null;		    	
				        try
				        {
				        	if(sexo=="F")
				        		fileStream = new FileInputStream("src/img/perfilMujer.jpg");
				        	else if (sexo=="M")
				        		fileStream = new FileInputStream("src/img/perfilHombre.jpg");
				        		service.createFile("/personal/i201910562_cibertec_edu_pe/Documents/BD_OPENFLY/"+nombreArchivoUsu, fileStream);
				        }
				    	finally
				    	{
				    		if(fileStream != null)
				    		{
				    			fileStream.close();
				    		}		    		
				    	}
			        } 
			        catch (ServiceException ex)
			        {
			        	System.out.println("Error: " + ex.getMessage());
			        	System.out.println("Error: " + ex.getErrorCode());
			        	System.out.println("Error: " + ex.getErrorString());
			        	System.out.println("Error: " + ex.getRequestUrl());
			        	ex.printStackTrace();
			        }
			        catch (IOException ex)
			        {
			        	System.out.println("Error: " + ex.getMessage());	        	
			        	ex.printStackTrace();
			        }			
								
					try
			    	{
			            Service service = new Service("https://cibertecedu-my.sharepoint.com/personal/i201910562_cibertec_edu_pe",
			            		"i201910562@cibertec.edu.pe", "imCaLaX590");		
			            InputStream inputStream = service.getFileStream("/personal/i201910562_cibertec_edu_pe/Documents/BD_OPENFLY/"+nombreArchivoUsu);
			            FileOutputStream outputStream = new FileOutputStream("src/img/"+nombreArchivoUsu);		    	
				        try
				        {
			                byte[] buffer = new byte[8192];
			                int len = 0;
			                while ((len = inputStream.read(buffer, 0, buffer.length)) > 0)
			                {
			                    outputStream.write(buffer, 0, len);
			                }
				        }
				    	finally
				    	{   		
				    		if(inputStream != null)
				    		{
				    			inputStream.close();
				    		}		    		
				    		if(outputStream != null)
				    		{
				    			outputStream.close();
				    		}
				    	}
			        } 
			        catch (ServiceException ex)
			        {
			        	System.out.println("Error: " + ex.getMessage());
			        	System.out.println("Error: " + ex.getErrorCode());
			        	System.out.println("Error: " + ex.getErrorString());
			        	System.out.println("Error: " + ex.getRequestUrl());
			        	ex.printStackTrace();
			        }
			        catch (IOException ex)
			        {
			        	System.out.println("Error: " + ex.getMessage());	        	
			        	ex.printStackTrace();
			        }				
				}
			});	
			t.start();
			Thread t2=new Thread(new Runnable() {
				public void run() {
					try {
						for (int i = 0; i <=100; i++) {
							barra.setValue(i);
							barra.repaint();
							if(i==100){
								btnRegistrar.setEnabled(false);
								btnSalir.setEnabled(true);
								mostrar(nombreArchivoUsu);	
								btnAadir.setEnabled(false);
								btnOmitir.setEnabled(false);
							}
							Thread.sleep(85);
						}
					} catch (Exception e) {
							// TODO: handle exception
					}
				}
			});
			t2.start();
		} else{
			JOptionPane.showMessageDialog(null, "Elija una foto!.");
		}
			
	}
	protected void do_cbMostrar_itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
            //txtContraseña.setEchoChar('*');
            txtContraseña.setEchoChar((char) 0);
        } else {
        	txtContraseña.setEchoChar('•');
        }
	}
	/*---------------------------METODOS----------------------------*/
	@SuppressWarnings("unused")
	private boolean validarCliente(String d){
			data=gp.listaCliente();
			for (int i = 0; i < data.size(); i++) {
				cliente=data.get(i);
				if(cliente.getNumDocClie().equals(d)){
					return true;
				}
			}
		return false;
	}
	@SuppressWarnings("unused")
	private boolean validarUsuario(String d){
		data=gp.listaCliente();
		for (int i = 0; i < data.size(); i++) {
			cliente=data.get(i);
			if(cliente.getNomUsu().equals(d)){
				return true;
			}
		}
	return false;
	}
	
	@SuppressWarnings("unused")
	private boolean validarCorreo(String d){
		data=gp.listaCliente();
		for (int i = 0; i < data.size(); i++) {
			cliente=data.get(i);
			if(cliente.getCorreoClie().equals(d)){
				return true;
			}
		}
	return false;
	}
	private String generarCodigo(){
		String codigo="";
		GestionPasajeros gp=new GestionPasajeros();
		 codigo=gp.UltimoCliente();
		String part = codigo.substring(1,6);
		int num = Integer.parseInt(part);
		num++;
		
		codigo = "C" + num;
		return codigo;
	}
	@SuppressWarnings("unused")
	private void limpieza(){
		txtNombre.setText("");
		txtApellido.setText("");
		txtUsuario.setText("");
		txtContraseña.setText("");
		txtContraseña01.setText("");
		txtCorreo.setText("");
		txtNDocumento.setText("");
		txtTelef.setText("");
		rdFemenino.setSelected(false);
		rfMasculino.setSelected(false);		
	}	
	/*----TIPO DE SEXO---*/
	@SuppressWarnings("unused")
	private String sexo(){
		String boton="";
		if(rdFemenino.isSelected()==true){
			boton="F";
		}else if(rfMasculino.isSelected()==true){
			boton ="M";
		}else{
			boton=null;
		}
		return boton;
	}
	private String pais(int indice){
		String  valor=null;
		switch(indice){
		case 1:		valor="+54";break;
		case 2:		valor="+591";break;
		case 3:		valor="+55";break;
		case 4:		valor="+56";break;
		case 5:		valor="+57";break;
		case 6:		valor="+506";break;
		case 7:		valor="+53";break;
		case 8:		valor="+593";break;
		case 9:		valor="+502";break;
		case 10:	valor="+504";break;
		case 11:	valor="+876";break;
		case 12:	valor="+52";break;
		case 13:	valor="+505";break;
		case 14:	valor="+595";break;
		case 15:	valor="+507";break;
		case 16:	valor="+51";break;
		case 17:	valor="+1787";break;
		case 18:	valor="+809";break;
		case 19:	valor="+598";break;
		case 20:    valor="+58";break;
		}
		return valor;
	}
	
	private void Mensaje(String e){
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		JOptionPane.showMessageDialog(null, e,"",JOptionPane.PLAIN_MESSAGE,icono);
	}	
	private void imagenLabel(String url, JLabel label){
		ImageIcon logo=new ImageIcon(url);
		Image imgLogo=logo.getImage();
		Image imgLogoReduc=imgLogo.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imgLogoReduc));
	}
	
	private void deshabilitar(){
		btnAadir.setEnabled(true);
		btnOmitir.setEnabled(true);
		btnRegistrar.setEnabled(false);
		txtNombre.setEnabled(false);
		txtApellido.setEnabled(false);
		txtFecha.setEnabled(false);
		ComboDocumento.setEnabled(false);
		txtPais.setEnabled(false);
		txtUsuario.setEnabled(false);
		txtContraseña.setEnabled(false);
		txtContraseña01.setEnabled(false);
		txtCorreo.setEnabled(false);
		txtNDocumento.setEnabled(false);
		cbMostrar.setEnabled(false);
		txtTelef.setEnabled(false);
		rdFemenino.setEnabled(false);
		rfMasculino.setEnabled(false);
	}
}
