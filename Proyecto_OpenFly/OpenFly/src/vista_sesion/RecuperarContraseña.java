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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

public class RecuperarContraseña extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblRecuperarContrasea;
	private JLabel lblCorreo;
	private JTextField textField;
	private JButton btnRecuperar;
	private JButton btnSalir;
	private JLabel lblAhora;
	private JLabel lblLogo;
	/*--------------------------------------*/
	Diseño img = new Diseño();
	Iniciarsesion a = new Iniciarsesion();
	public static String contraRecuperada="";	 
	public int cont=0;
	public String generarAleatorio(){
		String codigo="";	
		Random rnd = new Random();	
		for(int i=0; i<10; i++){
			if(i%2==0){
				codigo+=rnd.nextInt(10);
			}
			else
				codigo+=(char)(rnd.nextInt(91)+65);
		}
		return codigo;
	}	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecuperarContraseña frame = new RecuperarContraseña();
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
	public RecuperarContraseña() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RecuperarContraseña.class.getResource("/icon/logoOP.png")));
		setTitle("OpenFly\u00AE ");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		/*-----------DISEÑO -----------*/
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", new Color(70, 130, 180));;
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
		{
			lblAhora = new JLabel("AHORA!");
			lblAhora.setFont(new Font("Dubai Light", Font.BOLD, 14));
			lblAhora.setBounds(199, 41, 55, 24);
			contentPane.add(lblAhora);
		}
		{
			lblRecuperarContrasea = new JLabel("RECUPERA SU CONTRASE\u00D1A");
			lblRecuperarContrasea.setFont(new Font("Dubai Light", Font.BOLD, 14));
			lblRecuperarContrasea.setBounds(128, 17, 201, 24);
			contentPane.add(lblRecuperarContrasea);
		}
		{
			lblCorreo = new JLabel("Correo");
			lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCorreo.setBounds(51, 87, 58, 14);
			contentPane.add(lblCorreo);
		}
		{
			textField = new JTextField();
			textField.setBorder(new EmptyBorder(0, 5, 0, 5));
			textField.setBounds(116, 82, 266, 25);
			contentPane.add(textField);
			textField.setColumns(10);
		}
		{
			btnRecuperar = new JButton("RECUPERAR");
			btnRecuperar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRecuperar.addActionListener(this);
			btnRecuperar.setForeground(Color.WHITE);
			btnRecuperar.setBackground(new Color(70, 130, 180));
			btnRecuperar.setFont(new Font("Dubai", Font.BOLD, 9));
			btnRecuperar.setBounds(106, 141, 119, 36);
			contentPane.add(btnRecuperar);
		}
		{
			btnSalir = new JButton("SALIR");
			btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSalir.setForeground(Color.WHITE);
			btnSalir.setBackground(new Color(70, 130, 180));
			btnSalir.setFont(new Font("Dubai", Font.BOLD, 9));
			btnSalir.addActionListener(this);
			btnSalir.setBounds(237, 141, 119, 36);
			contentPane.add(btnSalir);
		}
		{
			lblLogo = new JLabel("");
			lblLogo.setBounds(0, 0, 444, 207);
			contentPane.add(lblLogo);
			img.img(RecuperarContraseña.class.getResource("/img/fondo5.jpg"), lblLogo);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnRecuperar) {
			actionPerformedBtnRecuperar(arg0);
		}
		if (arg0.getSource() == btnSalir) {
			actionPerformedBtnSalir(arg0);
		}
	}
	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		SesionGeneral sg=new SesionGeneral();
		sg.toFront();
		sg.setVisible(true);
		this.dispose();
	}
	public void imagenLabel(String url, JLabel label){
		ImageIcon logo=new ImageIcon(url);
		Image imgLogo=logo.getImage();
		Image imgLogoReduc=imgLogo.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imgLogoReduc));
	}
	protected void actionPerformedBtnRecuperar(ActionEvent arg0) {
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		Icon icono1=new ImageIcon(getClass().getResource("/icon/error.png"));
		GestionPasajeros gp= new GestionPasajeros();
		Cliente objCli=new Cliente();
		List<Cliente> data=gp.listaCliente();

		for (int i = 0; i < data.size(); i++) {
			objCli=data.get(i);
			Object Lista[]=new Object[4];
			Lista[0]=objCli.getNumDocClie();
			Lista[1]=objCli.getNomUsu();
			Lista[2]=objCli.getContUsu();
			Lista[3]=objCli.getCorreoClie();
			
			if(Lista[3].equals(textField.getText())){
				String correo=textField.getText();
				if(JOptionPane.showConfirmDialog(null, "Se le asignará una contraseña aleatoria la cual"
						+ "\n       se enviará a su correo. Está seguro?\n","",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE,icono)==JOptionPane.OK_OPTION){
					contraRecuperada=generarAleatorio();
					gp.CambiarContra(Lista[1].toString(), contraRecuperada);
					String para=correo;
					if(para.equals(correo)){
					String de="OpenFly2020@gmail.com";
					String deContra="aerolinea_2020";
					String asunto="Nueva contraseña - OpenFly";
					
					Properties propiedades=new Properties();
					propiedades.put("mail.smtp.auth", "true");
					propiedades.put("mail.smtp.starttls.enable", "true");
					propiedades.put("mail.smtp.host", "smtp.gmail.com");
					propiedades.put("mail.smtp.port", "587");
					
					Session sesion = Session.getDefaultInstance(propiedades,new javax.mail.Authenticator(){
						protected PasswordAuthentication getPasswordAuthentication(){
							return new PasswordAuthentication(de,deContra);
						}
					});
					
					try{
						MimeMessage mensaje = new MimeMessage(sesion);
						mensaje.setFrom(new InternetAddress(de));
						mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
						mensaje.setSubject(asunto);
						mensaje.setText("La aerolínea OpenFly le comunica,"+
									"\n\nQue se ha solicitado una recuperación de contraseña por el usuario  "+
								objCli.getNomUsu()+
						           ", por lo que se creo la siguiente contraseña : "+contraRecuperada);
						Transport.send(mensaje);
						cont++;
						JOptionPane.showMessageDialog(null, "La contraseña asignada "
								+ "fue enviada a su correo!.","",JOptionPane.PLAIN_MESSAGE,icono);
						Iniciarsesion ini=new Iniciarsesion();
						ini.setVisible(true);
						this.dispose();
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, ""+ex);
					}
				}			
			   }else{
				   return;
			   }
				break;
			}
			else if (i==data.size()-1){
				JOptionPane.showMessageDialog(null, "  No existe ningún usuario "
						+ "\nregistrado con este correo!", 
						"", JOptionPane.PLAIN_MESSAGE,icono1);
			}
			
			}
	
	}
}
