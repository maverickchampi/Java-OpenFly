package vista_sesion;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import libreria.Diseño;
import libreria.insertarImagen;
import mantenimientos.GestionPasajeros;
import model.Cliente;
import vista_reserva.ReservarVuelo;
import vista_usuario.MenuUsuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import com.independentsoft.share.Service;
import com.independentsoft.share.ServiceException;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import javax.swing.JProgressBar;

public class EditarInformacion extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblRegistrarUsuario;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JLabel lblFechaDeNacimeinto;
	private JLabel lblCorreo;
	private JButton btnRegistrar;
	private JButton btnSalir;
	public static File fichero;
	public String usuarioN;
	private JLabel lblTipoDeDocumento;
	private JLabel lblN;
	private JTextField txtDocumento;
	private JLabel label;
	private JButton btnAadir;
	private JLabel lblTelefono;
	private JTextField txtUsuario;
	private JLabel lblPais;
	public String telefonoN;
	public String contraseñaN,correoN;
	public static String image="";
	private JTextField txtPais;
	private JTextField txtFecha;
	private JTextField txtTDocumento;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JLabel lblUsuario;
	private JTextField txtContraseña;
	private JLabel lblContrasea;
	private JButton button_3;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel lblFondo;
	private JLabel lblSexo;
	private JTextField txtSexo;
	private JProgressBar barra;

	Diseño img = new Diseño();
	Iniciarsesion i = new Iniciarsesion();
	GestionPasajeros gp=new GestionPasajeros();
	Cliente cliente=new Cliente();
	@SuppressWarnings("static-access")
	String usuario=i.usuClie;
	List<Cliente> data=gp.listaCliente();
	Cliente data1=gp.BuscarCliente(Iniciarsesion.codCLiente);
	String nombreArchivoUsu="foto"+Iniciarsesion.codCLiente+".jpg";
	@SuppressWarnings("static-access")
	String nDoc=i.nroDocClie;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarInformacion frame=new EditarInformacion() ;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param lblCorreo 
	 */
	public EditarInformacion() {
		setTitle("OpenFly\u00AE - REGISTRO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registrar.class.getResource("/icon/logoOP.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		UIManager.put("Button.background", new Color(70, 130, 180));
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
		{
			barra = new JProgressBar(1,100);
			barra.setStringPainted(true);
			barra.setValue(0);
			barra.setForeground(new Color(123, 104, 238));
			barra.setBorder(new LineBorder(new Color(0, 0, 0)));
			barra.setBounds(348, 212, 155, 23);
			contentPane.add(barra);
		}
		{
			lblRegistrarUsuario = new JLabel("EDITAR INFORMACI\u00D3N");
			lblRegistrarUsuario.setFont(new Font("Dubai", Font.BOLD, 17));
			lblRegistrarUsuario.setBounds(221, 17, 173, 30);
			contentPane.add(lblRegistrarUsuario);
		}
		{
			lblNombre = new JLabel("Nombres");
			lblNombre.setFont(new Font("Dubai", Font.PLAIN, 13));
			lblNombre.setBounds(23, 45, 49, 14);
			contentPane.add(lblNombre);
		}
		{
			lblApellido = new JLabel("Apellidos");
			lblApellido.setFont(new Font("Dubai", Font.PLAIN, 13));
			lblApellido.setBounds(23, 94, 244, 14);
			contentPane.add(lblApellido);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBackground(Color.WHITE);
			txtNombre.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtNombre.setEditable(false);
			txtNombre.setBounds(23, 63, 263, 25);
			contentPane.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			txtApellido = new JTextField();
			txtApellido.setBackground(Color.WHITE);
			txtApellido.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtApellido.setEditable(false);
			txtApellido.setBounds(23, 112, 263, 25);
			contentPane.add(txtApellido);
			txtApellido.setColumns(10);
		}
		{
			lblFechaDeNacimeinto = new JLabel("Fecha de Nacimiento");
			lblFechaDeNacimeinto.setFont(new Font("Dubai", Font.PLAIN, 13));
			lblFechaDeNacimeinto.setBounds(23, 155, 131, 14);
			contentPane.add(lblFechaDeNacimeinto);
		}
		{
			lblCorreo = new JLabel("Correo");
			lblCorreo.setFont(new Font("Dubai", Font.PLAIN, 13));
			lblCorreo.setBounds(308, 325, 51, 14);
			contentPane.add(lblCorreo);
		}
		{
			btnRegistrar = new JButton("CAMBIAR");
			btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRegistrar.addActionListener(this);
			btnRegistrar.setFont(new Font("Dubai", Font.BOLD, 9));
			btnRegistrar.setBackground(Color.DARK_GRAY);
			btnRegistrar.setForeground(Color.WHITE);
			btnRegistrar.setBounds(178, 459, 107, 32);
			contentPane.add(btnRegistrar);
		}
		{
			btnSalir = new JButton("SALIR");
			btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSalir.addActionListener(this);
			btnSalir.setFont(new Font("Dubai", Font.BOLD, 9));
			btnSalir.setBackground(Color.DARK_GRAY);
			btnSalir.setForeground(Color.WHITE);
			btnSalir.setBounds(299, 459, 107, 32);
			contentPane.add(btnSalir);
		}
		{
			lblTipoDeDocumento = new JLabel("Tipo de Documento");
			lblTipoDeDocumento.setFont(new Font("Dubai", Font.PLAIN, 13));
			lblTipoDeDocumento.setBounds(23, 212, 119, 14);
			contentPane.add(lblTipoDeDocumento);
		}
		{
			lblN = new JLabel("N\u00B0 Documento");
			lblN.setFont(new Font("Dubai", Font.PLAIN, 13));
			lblN.setBounds(176, 212, 91, 14);
			contentPane.add(lblN);
		}
		{
			txtDocumento = new JTextField();
			txtDocumento.setBackground(Color.WHITE);
			txtDocumento.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtDocumento.setEditable(false);
			txtDocumento.setColumns(10);
			txtDocumento.setBounds(170, 228, 116, 25);
			contentPane.add(txtDocumento);
		}
		{
			label = new JLabel("");
			label.setBorder(new LineBorder(new Color(0, 0, 0)));
			label.setBounds(348, 79, 155, 132);
			contentPane.add(label);
		}
		{
			btnAadir = new JButton("MODIFICAR");
			btnAadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAadir.setForeground(Color.WHITE);
			btnAadir.setBackground(new Color(178, 34, 34));
			btnAadir.setFont(new Font("Dubai", Font.BOLD, 9));
			btnAadir.addActionListener(this);
			btnAadir.setBounds(385, 252, 91, 30);
			contentPane.add(btnAadir);
		}
		{
			lblTelefono = new JLabel("Tel\u00E9fono");
			lblTelefono.setFont(new Font("Dubai", Font.PLAIN, 13));
			lblTelefono.setBounds(23, 325, 60, 14);
			contentPane.add(lblTelefono);
		}
		{
			txtUsuario = new JTextField();
			txtUsuario.setBackground(Color.WHITE);
			txtUsuario.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtUsuario.setEditable(false);
			txtUsuario.addKeyListener(this);
			txtUsuario.setBounds(23, 403, 229, 25);
			contentPane.add(txtUsuario);
			txtUsuario.setColumns(10);
		}
		{
			lblPais = new JLabel("Pa\u00EDs");
			lblPais.setFont(new Font("Dubai", Font.PLAIN, 13));
			lblPais.setBounds(23, 268, 33, 14);
			contentPane.add(lblPais);
		}
		
		txtPais = new JTextField();
		txtPais.setBackground(Color.WHITE);
		txtPais.setBorder(new EmptyBorder(0, 5, 0, 5));
		txtPais.setEditable(false);
		txtPais.setBounds(23, 283, 263, 25);
		contentPane.add(txtPais);
		txtPais.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setBackground(Color.WHITE);
		txtFecha.setBorder(new EmptyBorder(0, 5, 0, 5));
		txtFecha.setEditable(false);
		txtFecha.setBounds(23, 170, 131, 25);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		txtTDocumento = new JTextField();
		txtTDocumento.setBackground(Color.WHITE);
		txtTDocumento.setBorder(new EmptyBorder(0, 5, 0, 5));
		txtTDocumento.setEditable(false);
		txtTDocumento.setBounds(23, 228, 125, 25);
		contentPane.add(txtTDocumento);
		txtTDocumento.setColumns(10);
		{
			txtTelefono = new JTextField();
			txtTelefono.setBackground(Color.WHITE);
			txtTelefono.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtTelefono.setEditable(false);
			txtTelefono.addKeyListener(this);
			txtTelefono.setBounds(23, 344, 229, 25);
			contentPane.add(txtTelefono);
			txtTelefono.setColumns(10);
		}
		{
			txtCorreo = new JTextField();
			txtCorreo.setBackground(Color.WHITE);
			txtCorreo.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtCorreo.setEditable(false);
			txtCorreo.addKeyListener(this);
			txtCorreo.setBounds(308, 344, 207, 25);
			contentPane.add(txtCorreo);
			txtCorreo.setColumns(10);
		}
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Dubai", Font.PLAIN, 13));
		lblUsuario.setBounds(28, 383, 55, 14);
		contentPane.add(lblUsuario);
		
		txtContraseña = new JTextField();
		txtContraseña.setBackground(Color.WHITE);
		txtContraseña.setBorder(new EmptyBorder(0, 5, 0, 5));
		txtContraseña.setEditable(false);
		txtContraseña.addKeyListener(this);
		txtContraseña.setBounds(308, 403, 207, 25);
		contentPane.add(txtContraseña);
		txtContraseña.setColumns(10);
		
		lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Dubai", Font.PLAIN, 13));
		lblContrasea.setBounds(308, 386, 79, 14);
		contentPane.add(lblContrasea);
		
		button_3 = new JButton("");
		button_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_3.addActionListener(this);
		button_3.setBounds(516, 405, 33, 23);
		contentPane.add(button_3);
		button_3.setIcon(new ImageIcon(EditarInformacion.class.getResource("/icon/lapiz.png")));
		button_3.setOpaque(false);
		button_3.setBackground(new Color(0,0,0,0));
		button_3.setBorder(null);
		//iconoBoton("img/lapiz.png", button_3);
		{
			button = new JButton("");
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.addActionListener(this);
			button.setBounds(516, 344, 33, 23);
			contentPane.add(button);
			button.setIcon(new ImageIcon(EditarInformacion.class.getResource("/icon/lapiz.png")));
			button.setOpaque(false);
			button.setBackground(new Color(0,0,0,0));
			button.setBorder(null);
			//iconoBoton("img/lapiz.png", button);
		}
		{
			button_1 = new JButton("");
			button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button_1.addActionListener(this);
			button_1.setBounds(253, 344, 33, 23);
			contentPane.add(button_1);
			button_1.setIcon(new ImageIcon(EditarInformacion.class.getResource("/icon/lapiz.png")));
			button_1.setOpaque(false);
			button_1.setBackground(new Color(0,0,0,0));
			button_1.setBorder(null);
			//iconoBoton("img/lapiz.png", button_1);
		}
		{
			button_2 = new JButton("");
			button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button_2.addActionListener(this);
			button_2.setBounds(253, 405, 33, 23);
			contentPane.add(button_2);
			button_2.setIcon(new ImageIcon(EditarInformacion.class.getResource("/icon/lapiz.png")));
			button_2.setOpaque(false);
			button_2.setBackground(new Color(0,0,0,0));
			button_2.setBorder(null);
			//iconoBoton("img/lapiz.png", button_2);
		}
		{
			lblSexo = new JLabel("Sexo");
			lblSexo.setFont(new Font("Dubai", Font.PLAIN, 13));
			lblSexo.setBounds(175, 155, 34, 14);
			contentPane.add(lblSexo);
		}
		
		txtSexo = new JTextField();
		txtSexo.setBackground(Color.WHITE);
		txtSexo.setBorder(new EmptyBorder(0, 5, 0, 5));
		txtSexo.setEditable(false);
		txtSexo.setBounds(171, 170, 115, 25);
		contentPane.add(txtSexo);
		txtSexo.setColumns(10);
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 585, 508);
			contentPane.add(lblFondo);
			imagenLabel("img/fondo.jpg", lblFondo);		
			img.img(EditarInformacion.class.getResource("/img/fondo5.jpg"), lblFondo);
		}
		
		
		txtNombre.setText(data1.getNomClie());
		txtApellido.setText(data1.getApeClie());
		txtSexo.setText(data1.getSexoClie());
		txtFecha.setText(data1.getDescPais());
		txtTDocumento.setText(data1.getNomTipDocClie());
		txtDocumento.setText(data1.getNumDocClie());
		txtPais.setText(data1.getDescPais());
		txtUsuario.setText(data1.getNomUsu());
		txtTelefono.setText(data1.getTelfClie());
		txtCorreo.setText(data1.getCorreoClie());
		txtContraseña.setText(data1.getContUsu());
		
		
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
		
		imagenLabel("src/img/"+nombreArchivoUsu, label);
		
		
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
		if (e.getSource() == button_3) {
			actionPerformedButton_3(e);
		}
		if (e.getSource() == button) {
			actionPerformedButton(e);
		}
		if (e.getSource() == button_1) {
			actionPerformedButton_1(e);
		}
		if (e.getSource() == btnSalir) {
			actionPerformedBtnSalir(e);
		}
		if (e.getSource() == button_2) {
			actionPerformedButton_2(e);
		}
		if (e.getSource() == btnAadir) {
			actionPerformedBtnAadir(e);
		}
	}
	/*---------CARGA----------*/
	@SuppressWarnings("static-access")
	protected void actionPerformedBtnAadir(ActionEvent e) {
		
		ReservarVuelo.ventanaMensaje();
		int resultado;
		insertarImagen ventana=new insertarImagen();
		FileNameExtensionFilter filtro=new FileNameExtensionFilter("JPG","jpg");
		ventana.getCargarFoto().setFileFilter(filtro);
		resultado=ventana.getCargarFoto().showOpenDialog(null);
		if(ventana.getCargarFoto().APPROVE_OPTION==resultado){
			fichero=ventana.getCargarFoto().getSelectedFile();
		}
		if(fichero!=null){
			btnRegistrar.setEnabled(false);
			btnSalir.setEnabled(false);
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
				        	service.deleteFile("/personal/i201910562_cibertec_edu_pe/Documents/BD_OPENFLY/"+nombreArchivoUsu);
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
								btnRegistrar.setEnabled(true);
								btnSalir.setEnabled(true);
								imagenLabel("src/img/"+nombreArchivoUsu, label);
								btnAadir.setEnabled(false);
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
		else{
			btnSalir.setEnabled(true);
			btnRegistrar.setEnabled(true);
		}
		
	}
	public void imagenLabel(String url, JLabel label){
		ImageIcon logo=new ImageIcon(url);
		Image imgLogo=logo.getImage();
		Image imgLogoReduc=imgLogo.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imgLogoReduc));
	}
	public void iconoBoton(String url, JButton boton){
		ImageIcon logo=new ImageIcon(url);
		Image imgLogo=logo.getImage();
		Image imgLogoReduc=imgLogo.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imgLogoReduc));
	}
	protected void actionPerformedButton_2(ActionEvent e) {
		txtUsuario.setEditable(true);		
	}
	protected void actionPerformedBtnSalir(ActionEvent e) {
		MenuUsuario mu=new MenuUsuario();
		mu.toFront();
		mu.setVisible(true);
		this.dispose();
	}
	protected void actionPerformedButton_1(ActionEvent e) {
		txtTelefono.setEditable(true);
		txtTelefono.setText("");
	}
	protected void actionPerformedButton(ActionEvent e) {
		txtCorreo.setEditable(true);
	}
	protected void actionPerformedButton_3(ActionEvent e) {
		txtContraseña.setEditable(true);
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtContraseña) {
			keyTypedTxtContraseña(arg0);
		}
		if (arg0.getSource() == txtCorreo) {
			keyTypedTxtCorreo(arg0);
		}
		if (arg0.getSource() == txtTelefono) {
			keyTypedTxtTelefono(arg0);
		}
		if (arg0.getSource() == txtUsuario) {
			keyTypedTxtUsuario(arg0);
		}
	}
	protected void keyTypedTxtUsuario(KeyEvent e) {
		if(txtUsuario.getText().length()>20){
			e.consume();
		}
	}
	protected void keyTypedTxtTelefono(KeyEvent e) {
		char c = e.getKeyChar();
		if(c<'0' || c>'9')e.consume();
		if(txtTelefono.getText().length()>15){
			e.consume();
		}
	}
	protected void keyTypedTxtCorreo(KeyEvent e) {
		if(txtCorreo.getText().length()>30){
			e.consume();
		}
	}
	protected void keyTypedTxtContraseña(KeyEvent e) {
		if(txtContraseña.getText().length()>20){
			e.consume();
		}
	}
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		//USUARIO
		Icon icono1=new ImageIcon(getClass().getResource("/icon/error.png"));
		data1=gp.BuscarCliente(Iniciarsesion.codCLiente);
		Pattern u = Pattern.compile("^[_a-zA-Z0-9-]{5,20}");
		Matcher U = u.matcher(txtUsuario.getText());
		if (U.matches()==true && txtUsuario.getText().isEmpty()==false) {
		} else{
			JOptionPane.showMessageDialog(null, "El nombre de usuario es incorrecto.","",JOptionPane.PLAIN_MESSAGE,icono1);
			return;
		}
		if(validarUsuario()){
			Mensaje("Usuario ya creado.");
			return;
		}
		//TELEFONO
		//txtTelefono.setText(data1.getCodPais());
		//telefonoN=data1.getCodPais();
		Pattern t = Pattern.compile("[0-9]{1,15}");
		Matcher T = t.matcher(txtTelefono.getText());
		if (T.matches()==true && txtTelefono.getText().isEmpty()==false) {
						
			
		} else{

			JOptionPane.showMessageDialog(null, "El número de teléfono es incorrecto.","",JOptionPane.PLAIN_MESSAGE,icono1);
			return;
		}
		//CORREO
		Pattern cr = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher CR = cr.matcher(txtCorreo.getText());
		if (CR.matches()==true && txtCorreo.getText().isEmpty()==false) {
			
		} else{
			JOptionPane.showMessageDialog(null, "Esta ingresando un correo incorrecto.");
			return;
		}
		if(validarCorreo()){
			Mensaje("Correo ya registrado.");
			return;
		}
		//CONTRASEÑA
		//Mínimo de ocho caracteres, al menos una letra mayúscula, una letra minúscula, un carecter especial y  un número:
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		Pattern con = Pattern.compile("^.*(?=.{8,20})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_?¿@#$%^&+=]).*$");
		Matcher CON = con.matcher(txtContraseña.getText());
		if (CON.matches()==true && txtContraseña.getText().isEmpty()==false) {
			
			if(JOptionPane.showConfirmDialog(null, "desea guardar los cambios?...",
					"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,icono)==JOptionPane.OK_OPTION){
				telefonoN=txtTelefono.getText();
				correoN=txtCorreo.getText();
				usuarioN=txtUsuario.getText();
				contraseñaN=txtContraseña.getText();
				data1.setTelfClie(telefonoN);
				data1.setCorreoClie(correoN);
				data1.setNomUsu(usuarioN);
				data1.setContUsu(contraseñaN);
				gp.EditarInfo(nDoc, telefonoN, correoN, usuarioN, contraseñaN);
				JOptionPane.showMessageDialog(null, "Los datos se han guardado correctamente");
				txtNombre.setText(data1.getNomClie());
				txtApellido.setText(data1.getApeClie());
				txtSexo.setText(data1.getSexoClie());
				txtFecha.setText(data1.getDescPais());
				txtTDocumento.setText(data1.getNomTipDocClie());
				txtDocumento.setText(data1.getNumDocClie());
				txtPais.setText(data1.getDescPais());
				txtUsuario.setText(data1.getNomUsu());
				txtTelefono.setText(data1.getTelfClie());
				txtCorreo.setText(data1.getCorreoClie());
				txtContraseña.setText(data1.getContUsu());
				
				
			} else {
				MenuUsuario mu=new MenuUsuario();
				mu.toFront();
				mu.setVisible(true);
				this.dispose();
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Contraseña incorrecta, vuelva a ingresar.","",JOptionPane.PLAIN_MESSAGE,icono1);
			return;
		}
	}		
	
	@SuppressWarnings("unused")
	private boolean validarUsuario(){
		data=gp.listaCliente();
		for (int i = 0; i < data.size(); i++) {
			cliente=data.get(i);
			if(cliente.getNomUsu().equals(txtUsuario.getText())){
				return true;
			}
		}
		return false;
	}
	private void Mensaje(String e){
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		JOptionPane.showMessageDialog(null, e,"",JOptionPane.PLAIN_MESSAGE,icono);
	}	
	@SuppressWarnings("unused")
	private boolean validarCorreo(){
		data=gp.listaCliente();
		for (int i = 0; i < data.size(); i++) {
			cliente=data.get(i);
			if(cliente.getCorreoClie().equals(correoN)){
				return true;
			}
		}
	return false;
	}
}
