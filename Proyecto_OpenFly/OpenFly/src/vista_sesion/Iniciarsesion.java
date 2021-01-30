package vista_sesion;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import libreria.Diseño;
import mantenimientos.GestionPasajeros;
import model.Cliente;
import vista_usuario.MenuUsuario;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;

@SuppressWarnings("serial")
public class Iniciarsesion extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblIniciarSesin;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	private JTextField textField;
	private JPasswordField textField_1;
	private JButton btnIngresar;
	private JButton btnCerrar;
	private JLabel lblNoTeHas;
	private JCheckBox chbxMostrar;
	private JLabel lbllogo;
	private JLabel label_1;
	
	/*--------------------------------*/
	Diseño img = new Diseño();
	
	public static String nroDocClie;
	public static String usuClie;
	public static String contClie;
	public static String correoClie;
	public static String codCLiente;
	private JLabel lblRecuperarContraseña;
		
	GestionPasajeros gp= new GestionPasajeros();
	Cliente objCli=new Cliente();
	List<Cliente> data;
	private JLabel lblLogoO;
	private JLabel lblSesin;
	private JLabel lblOpenfly;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Iniciarsesion frame = new Iniciarsesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Iniciarsesion() {
		setTitle("OpenFly\u00AE -  CLIENTE");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Iniciarsesion.class.getResource("/icon/logoOP.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 453, 336);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		/*-----------DISEÑO -----------*/
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", new Color(70, 130, 180));
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
		{
			lblOpenfly = new JLabel("OPENFLY");
			lblOpenfly.setForeground(Color.BLACK);
			lblOpenfly.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 18));
			lblOpenfly.setBounds(223, 80, 80, 25);
			contentPane.add(lblOpenfly);
		}
		{
			lblSesin = new JLabel("SESI\u00D3N");
			lblSesin.setForeground(Color.BLACK);
			lblSesin.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 18));
			lblSesin.setBounds(265, 54, 64, 25);
			contentPane.add(lblSesin);
		}
		{
			lblLogoO = new JLabel("");
			lblLogoO.setBorder(null);
			lblLogoO.setBounds(86, 31, 92, 87);
			contentPane.add(lblLogoO);
			img.img(Iniciarsesion.class.getResource("/icon/avion.png"), lblLogoO);
		}
		{
			lblIniciarSesin = new JLabel("INICIAR ");
			lblIniciarSesin.setForeground(Color.BLACK);
			lblIniciarSesin.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 18));
			lblIniciarSesin.setBounds(190, 54, 70, 25);
			contentPane.add(lblIniciarSesin);
		}
		{
			lblUsuario = new JLabel("Usuario");
			lblUsuario.setForeground(Color.BLACK);
			lblUsuario.setFont(new Font("Dubai", Font.PLAIN, 14));
			lblUsuario.setBounds(83, 153, 70, 14);
			contentPane.add(lblUsuario);
		}
		{
			lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setForeground(Color.BLACK);
			lblContrasea.setFont(new Font("Dubai", Font.PLAIN, 14));
			lblContrasea.setBounds(61, 194, 92, 14);
			contentPane.add(lblContrasea);
		}
		{
			textField = new JTextField();
			textField.setBorder(new EmptyBorder(0, 5, 0, 5));
			textField.setBounds(149, 150, 196, 25);
			contentPane.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JPasswordField();
			textField_1.setBorder(new EmptyBorder(0, 5, 0, 5));
			textField_1.setColumns(10);
			textField_1.setBounds(149, 191, 196, 25);
			contentPane.add(textField_1);
		}
		{
			btnIngresar = new JButton("INGRESAR");
			btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnIngresar.setFont(new Font("Dubai", Font.BOLD, 9));
			btnIngresar.setForeground(Color.WHITE);
			btnIngresar.setBackground(new Color(70, 130, 180));
			btnIngresar.addActionListener(this);
			btnIngresar.setBounds(121, 233, 98, 30);
			contentPane.add(btnIngresar);
		}
		{
			btnCerrar = new JButton("CERRAR");
			btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCerrar.addActionListener(this);
			btnCerrar.setFont(new Font("Dubai", Font.BOLD, 9));
			btnCerrar.setForeground(Color.WHITE);
			btnCerrar.setBackground(new Color(70, 130, 180));
			btnCerrar.setBounds(231, 233, 98, 30);
			contentPane.add(btnCerrar);
		}
		{
			lblNoTeHas = new JLabel("No te has registrado? Registrate");
			lblNoTeHas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					do_lblNoTeHas_mouseClicked(e);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					do_lblNoTeHas_mouseEntered(e);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					do_lblNoTeHas_mouseExited(e);
				}
			});
			lblNoTeHas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblNoTeHas.setLabelFor(this);
			lblNoTeHas.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblNoTeHas.setForeground(Color.BLACK);
			lblNoTeHas.setBounds(264, 270, 159, 21);
			contentPane.add(lblNoTeHas);
		}
		{
			chbxMostrar = new JCheckBox("");
			chbxMostrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			chbxMostrar.setFont(new Font("Tahoma", Font.BOLD, 11));
			chbxMostrar.setBackground(Color.LIGHT_GRAY);
			chbxMostrar.setBounds(353, 191, 28, 25);
			chbxMostrar.setIcon(new ImageIcon(Iniciarsesion.class.getResource("/icon/ojo.png")));
			contentPane.add(chbxMostrar);
			chbxMostrar.setOpaque(false);
			chbxMostrar.setBackground(new Color(0,0,0,0));
			chbxMostrar.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					do_cbMostrar_itemStateChanged(e);
				}
			});
		}
		{
			lblRecuperarContraseña = new JLabel("Recuperar Contrase\u00F1a");
			lblRecuperarContraseña.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					do_lblRecuperarContrasea_mouseEntered(e);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					do_lblRecuperarContraseña_mouseExited(e);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					do_lblRecuperarContraseña_mouseClicked(e);
				}
			});
			lblRecuperarContraseña.setToolTipText("");
			lblRecuperarContraseña.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblRecuperarContraseña.setLabelFor(this);
			lblRecuperarContraseña.setFont(new Font("Dubai", Font.PLAIN, 12));
			lblRecuperarContraseña.setForeground(Color.BLACK);
			lblRecuperarContraseña.setBounds(29, 270, 109, 21);
			contentPane.add(lblRecuperarContraseña);
		}
		{
			lbllogo = new JLabel("");
			lbllogo.setBounds(377, 17, 46, 46);
			contentPane.add(lbllogo);
			imagenLabel("img/logoOpenFly.png", lbllogo);
			img.img(Iniciarsesion.class.getResource("/icon/logoOP.png"), lbllogo);
		}
		{
			label_1 = new JLabel("");
			label_1.setBounds(0, 0, 447, 307);
			contentPane.add(label_1);
			imagenLabel("img/fondo.jpg", label_1);
			img.img(Iniciarsesion.class.getResource("/img/fondo1.jpg"), label_1);
		}				
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}
	public void imagenLabel(String url, JLabel label){
		ImageIcon logo=new ImageIcon(url);
		Image imgLogo=logo.getImage();
		Image imgLogoReduc=imgLogo.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imgLogoReduc));
	}
	@SuppressWarnings("deprecation")
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		data=gp.listaCliente();
		Icon icono=new ImageIcon(getClass().getResource("/icon/error.png"));
		for (int i = 0; i < data.size(); i++) {
			objCli=data.get(i);
			Object Lista[]=new Object[4];
			Lista[0]=objCli.getNumDocClie();
			Lista[1]=objCli.getNomUsu();
			Lista[2]=objCli.getContUsu();
			Lista[3]=objCli.getCorreoClie();
			if(Lista[1].equals(textField.getText()) && Lista[2].equals(textField_1.getText())){
				nroDocClie=Lista[0].toString();
				usuClie=Lista[1].toString();
				contClie=Lista[2].toString();
				correoClie=Lista[3].toString();
				codCLiente=gp.BuscarClienteXusu(usuClie).getCodClie();
				JOptionPane.showMessageDialog(null, usuClie+" ha iniciado sesión!.");
				MenuUsuario a = new MenuUsuario();
				a.setVisible(true);
				this.dispose();
				break;
			}else if (i==data.size()-1){
				JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos", 
						"", JOptionPane.PLAIN_MESSAGE,icono);
			}
			
			}
	}
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		SesionGeneral sg=new SesionGeneral();
		sg.toFront();
		sg.setVisible(true);
		this.dispose();
	}
	protected void do_cbMostrar_itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			textField_1.setEchoChar((char) 0);
        } else {
        	textField_1.setEchoChar('•');
        }
	}
	protected void mouseClickedLblRecuperarContrasea(MouseEvent arg0) {
		RecuperarContraseña a = new RecuperarContraseña();
		a.setVisible(true);
		this.dispose();
	}
	// RECUPERAR CONTRASEÑA
	protected void do_lblRecuperarContrasea_mouseEntered(MouseEvent e) {
		 Font font = lblRecuperarContraseña.getFont();
         Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
         attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
         lblRecuperarContraseña.setFont(font.deriveFont(attributes));
	}
	protected void do_lblRecuperarContraseña_mouseExited(MouseEvent e) {
		Font font = lblRecuperarContraseña.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, -1);
        lblRecuperarContraseña.setFont(font.deriveFont(attributes));
	}
	protected void do_lblRecuperarContraseña_mouseClicked(MouseEvent e) {
		RecuperarContraseña rc=new RecuperarContraseña();
		rc.toFront();
		rc.setVisible(true);
		this.dispose();
	}
	// REGISTRAR
	protected void do_lblNoTeHas_mouseClicked(MouseEvent e) {
		Registrar r=new Registrar();
		r.toFront();
		r.setVisible(true);
		this.dispose();
	}
	protected void do_lblNoTeHas_mouseEntered(MouseEvent e) {
		Font font = lblNoTeHas.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        lblNoTeHas.setFont(font.deriveFont(attributes));
	}
	protected void do_lblNoTeHas_mouseExited(MouseEvent e) {
		Font font = lblNoTeHas.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, -1);
        lblNoTeHas.setFont(font.deriveFont(attributes));
	}
}
