package vista_reserva;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;
import com.toedter.calendar.JYearChooser;
import com.toedter.components.JSpinField;
import libreria.GenerarPdf;
import mantenimientos.GestionBoleta;
import mantenimientos.GestionPasajeros;
import mantenimientos.GestionVuelo;
import model.Acompañante;
import model.Cliente;
import model.Promocion;
import model.Vuelo;
import vista_sesion.Iniciarsesion;
import vista_usuario.Promociones;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;


public class MetodoPago extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel lblTitulo;
	private JLabel lblNmeroDeTarjeta;
	private JLabel lblFechaDeVencimiento;
	private JLabel lblCodigoDeSeguridad;
	private JTextField txtNumTarjeta;
	private JTextField txtCodSeguridad;
	private JButton btnSiguiente;
	private JButton btnRetroceder;
	private JLabel lblEntidad;

	int contador = 0;
	private JLabel lblEntidad_1;
	private JButton btnInformar;
	private JYearChooser ycAño;
	private JSpinField sfMes;
	private JButton btnBorrarNum;
	private JCheckBox chckTermino;
	private JLabel label;
	private JLabel label_1;
	private JTextArea txtMensaje;
	private JTextField txtDescuento;
	private JButton btnAplicar;
	private JLabel codP;
	/*----------------------------------------------------*/
	GestionVuelo gv=new GestionVuelo();
	GestionBoleta gb = new GestionBoleta();
	GestionPasajeros gp=new GestionPasajeros();
	
	public static DecimalFormat df=new DecimalFormat("S/#,###.00");
	Vuelo objVuelo=gv.BuscarVuelo(RealizarReserva.codVueloIda);
	Vuelo objVueloVuelta = gv.BuscarVuelo(RealizarReserva.codVueloRetorno);
	Cliente objCliente=gp.BuscarCliente(Iniciarsesion.codCLiente);
	public static double subtotal,igv,total;
	public static double desc=0;
	public static double nuevototal=0;
	public static String codDesc=null;
	public static boolean descuento = false;
	public static String numPromo=null;
	public static String codigo;
	public static String factura;
	public static String fechaActual;
	private JProgressBar carga;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MetodoPago frame = new MetodoPago();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MetodoPago() {
		setBorder(null);
		// FONDO TRANSPARENTE
		
		this.setBackground(new Color(0, 0, 0, 30));
		setBounds(0, 0, 661, 513);
		// QUITAR LA BARRA DE TITULO
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		getContentPane().setLayout(null);
		{
			btnInformar = new JButton("");
			btnInformar.setBackground(Color.WHITE);
			btnInformar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnInformar.addActionListener(this);
			btnInformar.setBorder(new LineBorder(new Color(255, 255, 255)));
			btnInformar.setIcon(new ImageIcon(MetodoPago.class.getResource("/icon/informar.png")));
			btnInformar.setBounds(174, 332, 32, 25);
			//btnInformar.setContentAreaFilled(false);
			getContentPane().add(btnInformar);
		}
		{
			chckTermino = new JCheckBox("Estoy acuerdo con los t\u00E9rminos y condiciones");
			chckTermino.setFocusable(false);
			chckTermino.setForeground(Color.BLACK);
			chckTermino.setFont(new Font("Dubai", Font.BOLD, 13));
			chckTermino.setBorder(UIManager.getBorder("CheckBox.border"));
			chckTermino.setBackground(new Color(128, 128, 128));
			chckTermino.setBounds(64, 388, 275, 19);
			getContentPane().add(chckTermino);

		}
		{
			lblTitulo = new JLabel("PAGO DE RESERVA\r\n");
			lblTitulo.setForeground(new Color(0, 0, 0));
			lblTitulo.setFont(new Font("Dubai", Font.BOLD, 18));
			lblTitulo.setBounds(257, 24, 153, 32);
			getContentPane().add(lblTitulo);
		}
		{
			lblNmeroDeTarjeta = new JLabel("N\u00FAmero de tarjeta ");
			lblNmeroDeTarjeta.setForeground(Color.BLACK);
			lblNmeroDeTarjeta.setFont(new Font("Dubai", Font.BOLD, 13));
			lblNmeroDeTarjeta.setBounds(64, 171, 129, 25);
			getContentPane().add(lblNmeroDeTarjeta);
		}
		{
			lblFechaDeVencimiento = new JLabel("Fecha de Vencimiento");
			lblFechaDeVencimiento.setForeground(Color.BLACK);
			lblFechaDeVencimiento.setFont(new Font("Dubai", Font.BOLD, 13));
			lblFechaDeVencimiento.setBounds(64, 243, 157, 25);
			getContentPane().add(lblFechaDeVencimiento);
		}
		{
			lblCodigoDeSeguridad = new JLabel("Codigo de Seguridad");
			lblCodigoDeSeguridad.setForeground(Color.BLACK);
			lblCodigoDeSeguridad.setFont(new Font("Dubai", Font.BOLD, 13));
			lblCodigoDeSeguridad.setBounds(64, 310, 151, 19);
			getContentPane().add(lblCodigoDeSeguridad);
		}
		{
			txtNumTarjeta = new JTextField();
			txtNumTarjeta.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			txtNumTarjeta.setForeground(Color.BLACK);
			txtNumTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtNumTarjeta.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					keyTypedTxtNumTarjeta(arg0);
				}
			});
			txtNumTarjeta.setBounds(64, 202, 259, 25);
			getContentPane().add(txtNumTarjeta);
			txtNumTarjeta.setColumns(10);
		}
		{
			txtCodSeguridad = new JTextField();
			txtCodSeguridad.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			txtCodSeguridad.setForeground(Color.BLACK);
			txtCodSeguridad.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtCodSeguridad.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					keyTypedTxtCodSeguridad(e);
				}
			});
			txtCodSeguridad.setColumns(10);
			txtCodSeguridad.setBounds(64, 332, 114, 25);
			getContentPane().add(txtCodSeguridad);
		}
		{
			btnSiguiente = new JButton("REALIZAR PAGO");
			btnSiguiente.setFocusable(false);
			btnSiguiente.addActionListener(this);
			btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setFont(new Font("Dubai", Font.BOLD, 11));
			btnSiguiente.setBackground(new Color(178, 34, 34));
			btnSiguiente.setBounds(328, 463, 142, 38);
			getContentPane().add(btnSiguiente);
		}
		{
			btnRetroceder = new JButton("RETROCEDER");
			btnRetroceder.setFocusable(false);
			btnRetroceder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRetroceder.addActionListener(this);
			btnRetroceder.setFont(new Font("Dubai", Font.BOLD, 11));
			btnRetroceder.setBackground(Color.DARK_GRAY);
			btnRetroceder.setForeground(Color.WHITE);
			btnRetroceder.setBounds(174, 463, 142, 38);
			getContentPane().add(btnRetroceder);
		}
		{
			lblEntidad = new JLabel("");
			lblEntidad.setForeground(Color.BLACK);
			lblEntidad.setFont(new Font("Arial", Font.PLAIN, 16));
			lblEntidad.setIcon(new ImageIcon(MetodoPago.class.getResource("/icon/mastercard.png")));
			lblEntidad.setBounds(138, 83, 64, 64);
			getContentPane().add(lblEntidad);
		}
		{
			lblEntidad_1 = new JLabel("");
			lblEntidad_1.setForeground(Color.BLACK);
			lblEntidad_1.setFont(new Font("Arial", Font.PLAIN, 16));
			lblEntidad_1.setIcon(new ImageIcon(MetodoPago.class.getResource("/icon/visa.png")));
			lblEntidad_1.setBounds(64, 83, 64, 64);
			getContentPane().add(lblEntidad_1);
		}
		{
			ycAño = new JYearChooser();
			ycAño.setBorder(null);
			ycAño.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 11));
			ycAño.getSpinner().setForeground(Color.BLACK);
			ycAño.setMaximum(2025);
			ycAño.setMinimum(2020);
			ycAño.setFont(new Font("Tahoma", Font.PLAIN, 11));
			ycAño.setBounds(211, 269, 139, 25);
			getContentPane().add(ycAño);
		}
		{
			sfMes = new JSpinField();
			sfMes.setBorder(null);
			sfMes.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 11));
			sfMes.getSpinner().setForeground(Color.BLACK);
			sfMes.setValue(1);
			sfMes.setMaximum(12);
			sfMes.setMinimum(01);
			sfMes.setFont(new Font("Tahoma", Font.PLAIN, 11));
			sfMes.setBounds(64, 269, 139, 25);
			
			getContentPane().add(sfMes);
			//
			
		}
		{
			btnBorrarNum = new JButton("");
			btnBorrarNum.setIcon(new ImageIcon(MetodoPago.class.getResource("/icon/x.png")));
			btnBorrarNum.setBorder(null);
			btnBorrarNum.setBackground(Color.WHITE);
			btnBorrarNum.setBounds(321, 202, 29, 25);
			getContentPane().add(btnBorrarNum);
		}
		{
			label = new JLabel("");
			label.setIcon(new ImageIcon(MetodoPago.class.getResource("/img/american.png")));
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Arial", Font.PLAIN, 16));
			label.setBounds(212, 83, 64, 64);
			getContentPane().add(label);
		}
		{
			label_1 = new JLabel("");
			label_1.setIcon(new ImageIcon(MetodoPago.class.getResource("/img/diners.png")));
			label_1.setForeground(Color.BLACK);
			label_1.setFont(new Font("Arial", Font.PLAIN, 16));
			label_1.setBounds(286, 83, 64, 64);
			getContentPane().add(label_1);
		}
		{
			txtMensaje = new JTextArea();
			txtMensaje.setBackground(new Color(255, 255, 255));
			txtMensaje.setForeground(Color.BLACK);
			txtMensaje.setFont(new Font("Dubai", Font.PLAIN, 12));
			txtMensaje.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));
			txtMensaje.setEditable(false);
			txtMensaje.setBounds(417, 67, 188, 162);
			getContentPane().add(txtMensaje);
		}
		{
			txtDescuento = new JTextField();
			txtDescuento.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			txtDescuento.setBackground(new Color(255, 255, 255));
			txtDescuento.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtDescuento.setForeground(Color.BLACK);
			txtDescuento.setBounds(417, 265, 188, 25);
			getContentPane().add(txtDescuento);
			txtDescuento.setColumns(10);
		}
		{
			btnAplicar = new JButton("APLICAR");
			btnAplicar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actionPerformedBtnAplicarJButton(arg0);
				}
			});
			btnAplicar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAplicar.setFocusable(false);
			btnAplicar.setBackground(Color.DARK_GRAY);
			btnAplicar.setForeground(Color.WHITE);
			btnAplicar.setFont(new Font("Dubai", Font.BOLD, 11));
			btnAplicar.setBounds(417, 301, 157, 30);
			getContentPane().add(btnAplicar);
		}
		{
			codP = new JLabel("C\u00F3digo Promocional :");
			codP.setForeground(Color.BLACK);
			codP.setFont(new Font("Dubai", Font.BOLD, 13));
			codP.setBounds(417, 243, 151, 19);
			getContentPane().add(codP);
		}
		{
			button = new JButton("");
			button.setIcon(new ImageIcon(MetodoPago.class.getResource("/icon/promocion.png")));
			button.setBackground(Color.DARK_GRAY);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_button_actionPerformed(arg0);
				}
			});
			button.setBounds(573, 301, 32, 30);
			getContentPane().add(button);
		}
		{
			carga = new JProgressBar(1,100);
			carga.setBounds(20, 20, 193, 38);
			carga.setStringPainted(true);
			carga.setForeground(new Color(123, 104, 238));
		}
		
		imprimir();
	}

	/*--------------------BOTONES-----------------------*/
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnInformar) {
			actionPerformedBtnInformar(arg0);
		}
		if (arg0.getSource() == btnRetroceder) {
			actionPerformedBtnRetroceder(arg0);
		}
		if (arg0.getSource() == btnSiguiente) {
			actionPerformedBtnSiguiente(arg0);
		}
	}

	protected void actionPerformedBtnSiguiente(ActionEvent arg0) {
		// BOTON SIGUIENTE
		ReservarVuelo.ventanaMensaje();
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon/informacion.png"));
		String patron = "[0-9_ -]{25}";
		String patron2 = "[0-9]{3}";
		int totalPasajeros = RealizarReserva.cantiPasaj;
		if(RealizarReserva.codVueloRetorno != null){
			totalPasajeros *= 2;
		}
		
		codigo = generarCodigo();
		factura = generarNumero();
		
		 if(codDesc!=null){
			 total=nuevototal;
		 }
		
		if(chckTermino.isSelected()){
			if(Pattern.matches(patron, txtNumTarjeta.getText()) && Pattern.matches(patron2, txtCodSeguridad.getText()) )
			{
				//INSERTAR BOLETO
				gb.insertarBoleto(codigo,gp.BuscarCliente(Iniciarsesion.codCLiente).getCodClie(),
						RealizarReserva.codVueloIda, RealizarReserva.codVueloRetorno, totalPasajeros);
				
				//INSERTAR FACTURA
				gb.insertarFactura(factura, codigo, fechaActual(), numTarjeta(txtNumTarjeta.getText()), subtotal ,
								igv, total,codDesc);
				fechaActual= fechaActual();
				if(RealizarReserva.listaAcompañantes.size() > 0) {
				//INGRESA ACOMPAÑANTES
				for(Acompañante Obja : RealizarReserva.listaAcompañantes) {
					if(gp.BuscarAcompañante(Obja.getNumDocAcomp())==null){
						gb.insertarAcompañante(Obja);
					}
					gb.insertarDetalleBoleta(codigo, Obja.getNumDocAcomp());
					}
				}				
				//INGRESA ASIENTOS
				for(String nomA : RealizarReserva.codigosAsientos){
				gb.insertarAsiento(codigo,RealizarReserva.codVueloIda, nomA);}
				if(RealizarReserva.codVueloRetorno != null){
					for(String nomB: RealizarReserva.codigosAsientosVuelta){
						gb.insertarAsiento(codigo,RealizarReserva.codVueloRetorno, nomB);}
				}
				
				RegistroAcompañantes.validaAsiento = 0;
				RegistroAcompañantes.validaAsientoVuelta = 0;
			//*****************************************************
			//MENSAJE FINAL
			validarEnvio();
			//*****************************************************
			
			}else {
				JOptionPane.showMessageDialog(null, "Recuerde poner los 16 dígitos de la tarjeta y 3 dígitos del CVV","",
											JOptionPane.PLAIN_MESSAGE,icon);				
			}
		}else{
			JOptionPane.showMessageDialog(null, "Debe aceptar términos y condiciones","",
							JOptionPane.PLAIN_MESSAGE,icon);
		}
	}

	protected void actionPerformedBtnRetroceder(ActionEvent arg0) {
		UIManager.put("Panel.background", new Color(0,0,0,30));
		RealizarReserva.btnPaso4.setEnabled(false);
		RealizarReserva.btnPaso3.setEnabled(true);
		VerificarReserva fr = new VerificarReserva();
		RealizarReserva.panel.add(fr);
		fr.toFront();
		fr.setVisible(true);
		dispose();
		
	}
	protected void actionPerformedBtnInformar(ActionEvent arg0) {
		//BOTON INFORMAR DEL CVV
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
	
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/cvv.png"));
		JOptionPane.showMessageDialog(null, new JLabel(icon,JLabel.CENTER),"CVV",JOptionPane.PLAIN_MESSAGE);
		
		
	}
	/*-----------------------EVENTOS-------------------------------------*/

	protected void keyTypedTxtNumTarjeta(KeyEvent arg0) {
		char x = arg0.getKeyChar();
		if (Character.isDigit(x)) {
			if(txtNumTarjeta.getText().length()==4){
				txtNumTarjeta.setText(txtNumTarjeta.getText()+" - ");
			}
			if(txtNumTarjeta.getText().length()==11){
				txtNumTarjeta.setText(txtNumTarjeta.getText()+" - ");
			}
			if(txtNumTarjeta.getText().length()==18){
				txtNumTarjeta.setText(txtNumTarjeta.getText()+" - ");
			}	
		} else {
			arg0.consume();
			getToolkit().beep();
		}	
		if (txtNumTarjeta.getText().length()>24) {
			arg0.consume();
		}
	}
	protected void keyTypedTxtCodSeguridad(KeyEvent e) {		
		char x = e.getKeyChar();
		if (Character.isDigit(x)) {
		} else {
			e.consume();
			getToolkit().beep();
		}
		if (txtCodSeguridad.getText().length() > 2) {
			e.consume();
		}
	}
	/*---------------------COSTO DEL VUELO---------------------------*/
	private double igv( double money) {
		return money * 0.18;
	}
	private double total(double money) {
		return money + igv(money);
	}
	private String numTarjeta(String g){
		String num[]=g.split(" - ");
		return num[0]+num[1]+num[2]+num[3];
	}
	@SuppressWarnings("unused")
	private void imprimir(){
		int cant=RealizarReserva.cantiPasaj;
		subtotal = objVuelo.getPrecioVuel()*RealizarReserva.cantiPasaj;
		if(RealizarReserva.codVueloRetorno != null){
			subtotal += objVueloVuelta.getPrecioVuel()*RealizarReserva.cantiPasaj;
			cant=RealizarReserva.cantiPasaj*2;
		}
		
		igv=igv(subtotal);
		total=total(subtotal);
		
		txtMensaje.setText("------------A PAGAR-------------");
		txtMensaje.append("\n Sub total :  " + df.format(subtotal));
		txtMensaje.append("\n IGV :  " + df.format(igv(subtotal)));
		txtMensaje.append("\n Total :  " + df.format(total(subtotal)));
	}
	
	/*---------------------BOTON PROMOCION------------------------*/
	String promo1=null;
	String promo2=null;
	private JButton button;
	private void obtenerlugar(String g){
		String descrip=null;
		ArrayList<Promocion> data=gb.ListarPromociones();
		String[] codigo = null;
		Promocion obj=new Promocion();
		for (int i = 0; i < data.size(); i++) {
			 obj=data.get(i);
			if(obj.getCod_Promo().equals(g)){
				descrip=obj.getDescrip_Promo();
				break;
			}
		}
		codigo=descrip.split(" ");
		if(codigo[codigo.length-1].equals("Maldonado") || codigo[codigo.length-3].equals("Maldonado")){
			promo1="Puerto Maldonado";
		}
		if(codigo[codigo.length-2].equals("-")){
			promo1=codigo[codigo.length-3];
			promo2=codigo[codigo.length-1];
		}else if(codigo[codigo.length-2].equals("-") && codigo[codigo.length-1].equals("Maldonado") || 
				codigo[codigo.length-3].equals("Maldonado")){
			promo2=codigo[codigo.length-1];
		}
	}
	private void actionPerformedBtnAplicarJButton(ActionEvent arg0) {
		Promociones p=new Promociones();
		p.setVisible(false);
		try {
			ReservarVuelo.ventanaMensaje();
			String codigopromocional = txtDescuento.getText();
			
			obtenerlugar(codigopromocional);

			Promocion obj=new Promocion();
			
			obj=gb.BuscarPromociones(codigopromocional);
			
			boolean existe = false;
			if(obj.getCod_Promo().equals(codigopromocional)){
				if(objVuelo.getDescCiuDest().equals(promo1) || objVuelo.getDescCiuDest().equals(promo2)){
					codDesc=obj.getCod_Promo();
					desc=total(subtotal)*obj.getPorcentaje_desc();
					numPromo=((float)obj.getPorcentaje_desc()*100)+"%";
					existe=true;
					/*if(objVueloVuelta!=null && (objVueloVuelta.getDescCiuDest().equals(promo1) ||
							objVueloVuelta.getDescCiuDest().equals(promo2))){
						codDesc=obj.getCod_Promo();
						desc=0;
						desc=((obj.getPorcentaje_desc()*2*100)-((obj.getPorcentaje_desc()*obj.getPorcentaje_desc())*100))*total(subtotal);
						numPromo=((float)obj.getPorcentaje_desc()*100)+"%";
						existe=true;
					}*/
					}
			}else{
				existe=false;
			}
				
			if(descuento == false){	
				if(existe){
					nuevototal=total-desc;
					txtMensaje.append("\n----------------------------------");
					txtMensaje.append("\n"+numPromo+" DTO. :  - " + String.format("%.2f",desc));
					txtMensaje.append("\n Total :  " + String.format("%.2f",nuevototal));
				
					descuento = true;
					txtDescuento.setText("");
					txtDescuento.requestFocus();
				}else{
					JOptionPane.showMessageDialog(null, "Código no válido");}
				
			}else{
				JOptionPane.showMessageDialog(null, "Ya ingresó código");			
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No ingreso código");	
		}
	}
	
	private String fechaActual(){
		
		Date fecha = new Date();
		
		String miFormato = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(miFormato);

		return sdf.format(fecha);
	}

	private String generarCodigo(){
		String codigo = "";
		GestionBoleta gb = new GestionBoleta();
		
		codigo = gb.buscaUltimaBoleta();
		
		String part = codigo.substring(3,7);
		int num = Integer.parseInt(part);
		
		num++;
		
		codigo = "BOL" + num;
		return codigo;
	}
	
	private String generarNumero(){
		String codigo = "";
		GestionBoleta gb = new GestionBoleta();
		
		codigo = gb.buscaUltimaFactura();
		
		String part = codigo.substring(1,5);
		int num = Integer.parseInt(part);
		
		num++;
		
		codigo = "F" + num;
		return codigo;
	}
	
	/*---------------------METODO PARA ENVIAR CORREO--------------------------------*/
	private void enviarCorreo(){
		try {
			//PROPIEDADES DE CONEXION
			
			Properties props=new Properties();
					
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			
			Session sesion=Session.getDefaultInstance(props,null);
			sesion.setDebug(true);
			
			//DATOS PARA EL CORREO
			String correoRemitente="OpenFly2020@gmail.com";
			String contraseñaRemitente="aerolinea_2020";
			
			String correoReceptor=objCliente.getCorreoClie();
			String correoSec=VerificarReserva.correo;
			String asunto="Factura del vuelo "+factura;
			String mensaje="";
			
			//INGRESAR TEXTO
			BodyPart texto=new MimeBodyPart();
			texto.setText(mensaje);
			
			//INGRESAR PDF
			BodyPart adjunto=new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource("src/img/reporte.pdf")));
			adjunto.setFileName(factura+".pdf");
			
			//ENVIAR ASUNTO Y MENSAJE
			MimeMultipart parte=new MimeMultipart();
			parte.addBodyPart(adjunto);
			parte.addBodyPart(texto);
			
			//ESTRUCTURA DEL MENSAJE
			MimeMessage correo=new MimeMessage(sesion);
					
			correo.setFrom(new InternetAddress(correoRemitente));
			//AGREGAR AL RECEPTOR
			correo.addRecipient(Message.RecipientType.TO,
								 new InternetAddress(correoReceptor));
			if(correoSec!=null){
			correo.addRecipient(Message.RecipientType.CC, 
								 new InternetAddress(correoSec));
			}
			
			correo.setSubject(asunto);
			correo.setContent(parte);
				
			//ENVIO DEL MENSAJE
			Transport t=sesion.getTransport("smtp");
			t.connect(correoRemitente,contraseñaRemitente);
			t.sendMessage(correo, correo.getAllRecipients());
			t.close();
			
			} catch (AddressException e) {
				System.out.println(e);
			} catch (MessagingException e) {		
				System.out.println(e);
			}
	}
	private void validarEnvio(){
		Thread t=new Thread(new Runnable() {
			public void run() {
				GenerarPdf pdf=new GenerarPdf();//CREAR PDF
				pdf.Documento();
				enviarCorreo();//ENVIAR CORREO*/
				descuento = false;
				codDesc = null;
				total = 0;
			}
		});
		Thread t1=new Thread(new Runnable() {
			public void run() {
				try {
					for (int i = 0; i <=100; i++) {
						RealizarReserva.progressBar.setValue(i);
						RealizarReserva.progressBar.repaint();
						if(i==100){
							RealizarReserva.lblEspere.setText("Reserva finalizada");
							RealizarReserva.btnAceptar.setEnabled(true);
						}
						Thread.sleep(60);
					}
				} catch (Exception e) {
						// TODO: handle exception
				}
			}
		});;
		RealizarReserva.panel.setVisible(false);
		RealizarReserva.btnAceptar.setVisible(true);
		RealizarReserva.progressBar.setVisible(true);
		RealizarReserva.lblEspere.setVisible(true);
		try {
			t1.start();
			t.start();
			this.dispose();
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
		}
	}
	@SuppressWarnings("static-access")
	protected void do_button_actionPerformed(ActionEvent arg0) {
		//VER PROMOCION
		Promociones p=new Promociones();
		p.toFront();
		p.btnSalir.setVisible(false);
		p.btnSalir2.setVisible(true);
		p.btnSalir2.setEnabled(true);
		p.setVisible(true);
		
	}
}
