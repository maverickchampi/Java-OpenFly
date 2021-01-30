package vista_sesion;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import libreria.Diseño;
import vista_administrador.Administrador;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class IniciarSesionAdmi extends JFrame {

	private JPanel contentPane;
	private JLabel lblIngresar;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JLabel lblLogo;
	private JTextField txtUsuario;
	private JLabel lblFondo;
	private JButton btnIngresar;
	private JButton btnCerrar;
	private JPasswordField txtContraseña;
	private JCheckBox checkBox;
	private JLabel lblAdministrador;
	
	/*----------------------------ATRIBUTOS---------------*/
	int cont=0;
	public static String usuario="OpenFly2020";
	public static String contraseña="202051";
	Diseño img = new Diseño();
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesionAdmi frame = new IniciarSesionAdmi();
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
	public IniciarSesionAdmi() {
		setTitle("OpenFly\u00AE - ADMINISTRADOR");
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(IniciarSesionAdmi.class.getResource("/icon/logoOP.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 453, 336);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);//CENTRAR GUI
		/*-----------DISEÑO-----------*/
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", new Color(70, 130, 180));;
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
		{
			label = new JLabel("");
			label.setBounds(27, 244, 46, 46);
			contentPane.add(label);
			img.img(Iniciarsesion.class.getResource("/icon/logoOP.png"), label);
		}
		{
			lblAdministrador = new JLabel("ADMINISTRADOR");
			lblAdministrador.setForeground(new Color(0, 0, 0));
			lblAdministrador.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
			lblAdministrador.setBounds(220, 81, 135, 22);
			contentPane.add(lblAdministrador);
		}
		{
			lblIngresar = new JLabel("INGRESAR");
			lblIngresar.setForeground(new Color(0, 0, 0));
			lblIngresar.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 16));
			lblIngresar.setBounds(248, 55, 79, 22);
			contentPane.add(lblIngresar);
		}
		{
			lblUsuario = new JLabel("Usuario");
			lblUsuario.setForeground(new Color(0, 0, 0));
			lblUsuario.setFont(new Font("Dubai", Font.PLAIN, 14));
			lblUsuario.setBounds(65, 152, 55, 16);
			contentPane.add(lblUsuario);
		}
		{
			lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setForeground(new Color(0, 0, 0));
			lblContrasea.setFont(new Font("Dubai", Font.PLAIN, 14));
			lblContrasea.setBounds(65, 193, 82, 16);
			contentPane.add(lblContrasea);
		}
		{
			lblLogo = new JLabel("");
			lblLogo.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblLogo.setBounds(85, 17, 127, 115);
			contentPane.add(lblLogo);
			img.img(IniciarSesionAdmi.class.getResource("/icon/admi.png"), lblLogo);
			lblLogo.setBorder(null);
		}	
		{
			txtUsuario = new JTextField();
			txtUsuario.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtUsuario.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					do_txtUsuario_keyTyped(e);
				}
			});
			txtUsuario.setBounds(159, 149, 204, 25);
			contentPane.add(txtUsuario);
			txtUsuario.setColumns(10);
		}
		{
			btnIngresar = new JButton("INGRESAR");
			btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					do_btnIngresar_actionPerformed(e);
				}
			});
			btnIngresar.setFont(new Font("Dubai", Font.BOLD, 10));
			btnIngresar.setForeground(Color.WHITE);
			btnIngresar.setBackground(new Color(70, 130, 180));
			btnIngresar.setBounds(130, 244, 98, 30);
			contentPane.add(btnIngresar);
		}
		{
			btnCerrar = new JButton("CERRAR");
			btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCerrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					btnCerrarActionPerformed(e);
				}
			});
			btnCerrar.setFont(new Font("Dubai", Font.BOLD, 10));
			btnCerrar.setForeground(Color.WHITE);
			btnCerrar.setBackground(new Color(70, 130, 180));
			btnCerrar.setBounds(237, 244, 98, 30);
			contentPane.add(btnCerrar);
		}
		{
			txtContraseña = new JPasswordField();
			txtContraseña.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtContraseña.setBounds(159, 190, 204, 25);
			contentPane.add(txtContraseña);
		}
		
		checkBox = new JCheckBox("");
		checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkBox.setBounds(368, 190, 27, 25);
		contentPane.add(checkBox);
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				do_cbMostrar_itemStateChanged(e);
			}
		});
		checkBox.setIcon(new ImageIcon(IniciarSesionAdmi.class.getResource("/icon/ojo.png")));
		checkBox.setOpaque(false);
		checkBox.setBackground(new Color(0,0,0,0));
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 447, 307);
			contentPane.add(lblFondo);
			img.img(IniciarSesionAdmi.class.getResource("/img/fondo1.jpg"), lblFondo);
		}
	}
	/*------------------------------BOTONES----------------------------------------*/
	protected void btnCerrarActionPerformed(ActionEvent e) {
		//BOTON CERRAR
		SesionGeneral sg=new SesionGeneral();
		sg.toFront();
		sg.setVisible(true);
		this.dispose();
	}
	protected void do_btnIngresar_actionPerformed(ActionEvent e) {
		//BOTON INGRESAR
		Icon icono=new ImageIcon(getClass().getResource("/icon/error.png"));
		char []clave=txtContraseña.getPassword();
	    String claveFinal=new String(clave);
	    Administrador ingresar=new Administrador();
		if(txtUsuario.getText().length()==0 & claveFinal.length()==0){
			imprimir("No ha rellenado ningun casillero");
		}else if(txtUsuario.getText().isEmpty()){
			imprimir("No ha rellenado el casillero usuario");
		}else if(claveFinal.isEmpty()){
			imprimir("No ha rellenado el casillero contraseña");
		}else if(txtUsuario.getText().equals(usuario) & claveFinal.equals(contraseña)){
				ingresar.setVisible(true);
				this.dispose();
		}else{
			cont++;
			if(cont<3){	
			JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto","",
					JOptionPane.PLAIN_MESSAGE,icono);
			}else{
			Icon icono1=new ImageIcon(getClass().getResource("/icon/errorI.png"));
			JOptionPane.showMessageDialog(null, "Usted no es el administrador", "",
					JOptionPane.PLAIN_MESSAGE,icono1);
			btnIngresar.setEnabled(false);
		    }
		}
	}
	//METODO
	void imprimir(String n){
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		JOptionPane.showMessageDialog(null, n,"",JOptionPane.PLAIN_MESSAGE,icono);
	}
	/*------------------------------EVENTOS--------------------------------------*/
	protected void do_txtUsuario_keyTyped(KeyEvent e) {
	}
	protected void do_cbMostrar_itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
            txtContraseña.setEchoChar((char) 0);
        } else {
        	txtContraseña.setEchoChar('•');
        }
	}
}