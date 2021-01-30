package vista_reserva;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import mantenimientos.GestionPasajeros;
import mantenimientos.GestionVuelo;
import model.Acompañante;
import model.Cliente;
import model.Vuelo;
import vista_sesion.Iniciarsesion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;


public class VerificarReserva extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	boolean verificaNumero = false;
	
	private JLabel lblTitulo;
	private JButton btnRetroceder;
	private JTextArea txtBoleta;
	private JLabel lblSubTitulo;
	private JLabel lblNCelular;
	private JLabel lblEmail;
	private JComboBox<String> cbCodCelular;
	private JTextField txtNumero;
	private JTextField txtEmail;
	private JScrollPane scrollPane;
	/*-------------------------------------*/
	private boolean telef=false;
	private boolean corr=false;
	public static String telefono="0000000";
	public static String correo=null;
	GestionVuelo gv=new GestionVuelo();
	GestionPasajeros gp=new GestionPasajeros();
	Cliente objCliente=gp.BuscarCliente(Iniciarsesion.codCLiente);
	/*-----------------------------------*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerificarReserva frame = new VerificarReserva();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerificarReserva() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBorder(null);
		//AÑADIR TRANSPARENCIA 
		this.setBackground(new Color(0, 0, 0,30));
		setBounds(0,0, 661, 513);
		//QUITAR LA BARRA DE TITULO
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		getContentPane().setLayout(null);
		{
			lblTitulo = new JLabel("VERIFICAR RESERVA");
			lblTitulo.setForeground(new Color(0, 0, 0));
			lblTitulo.setFont(new Font("Dubai", Font.BOLD, 18));
			lblTitulo.setBounds(427, 42, 166, 32);
			getContentPane().add(lblTitulo);
		}
		{
			btnRetroceder = new JButton("RETROCEDER");
			btnRetroceder.setFont(new Font("Dubai", Font.BOLD, 11));
			btnRetroceder.addActionListener(this);
			btnRetroceder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRetroceder.setBackground(Color.DARK_GRAY);
			btnRetroceder.setForeground(Color.WHITE);
			btnRetroceder.setBounds(175, 459, 142, 38);
			getContentPane().add(btnRetroceder);
		}
		{
			lblSubTitulo = new JLabel("ELIGA LA FORMA EN LA ");
			lblSubTitulo.setBackground(Color.GRAY);
			lblSubTitulo.setForeground(new Color(0, 0, 0));
			lblSubTitulo.setFont(new Font("Dubai Light", Font.BOLD, 11));
			lblSubTitulo.setBounds(431, 115, 157, 23);
			getContentPane().add(lblSubTitulo);
		}
		{
			lblNCelular = new JLabel("N\u00B0 Celular");
			lblNCelular.setForeground(new Color(0, 0, 0));
			lblNCelular.setFont(new Font("Arial", Font.BOLD, 12));
			lblNCelular.setBounds(385, 230, 56, 15);
			getContentPane().add(lblNCelular);
		}
		{
			lblEmail = new JLabel("E-mail");
			lblEmail.setForeground(new Color(0, 0, 0));
			lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
			lblEmail.setBounds(385, 291, 35, 14);
			getContentPane().add(lblEmail);
		}
		{
			cbCodCelular = new JComboBox<String>();
			cbCodCelular.setEnabled(false);
			cbCodCelular.setModel(new DefaultComboBoxModel<String>(new String[] {"+54", "+591", "+55", "+57", "+593", "+595", "+51", "+598", "+58"}));
			cbCodCelular.setBackground(Color.WHITE);
			cbCodCelular.setBounds(385, 255, 55, 25);
			getContentPane().add(cbCodCelular);
		}
		{
			txtNumero = new JTextField();
			txtNumero.setBackground(Color.WHITE);
			txtNumero.setEnabled(false);
			txtNumero.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					keyTypedTxtNumero(arg0);
				}
			});
			txtNumero.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			txtNumero.setBounds(459, 255, 171, 25);
			getContentPane().add(txtNumero);
			txtNumero.setColumns(10);
		}
		{
			txtEmail = new JTextField();
			txtEmail.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					keyTypedTxtEmail(arg0);
				}
			});
			txtEmail.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			txtEmail.setBounds(385, 316, 245, 25);
			getContentPane().add(txtEmail);
			txtEmail.setColumns(10);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBackground(new Color(255, 255, 255));
			scrollPane.setBorder(null);
			scrollPane.setBounds(26, 28, 349, 405);
			getContentPane().add(scrollPane);
			{
				txtBoleta = new JTextArea();
				txtBoleta.setBackground(Color.WHITE);
				txtBoleta.setOpaque(false);
				txtBoleta.setEditable(false);
				txtBoleta.setForeground(Color.BLACK);
				txtBoleta.setFont(new Font("Dubai", Font.PLAIN, 12));
				txtBoleta.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				scrollPane.setViewportView(txtBoleta);
			}
		}
		{
			btnPgar = new JButton("PAGAR");
			btnPgar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnPgar.setBackground(new Color(128, 0, 0));
			btnPgar.setForeground(Color.WHITE);
			btnPgar.setBounds(329, 459, 142, 38);
			btnPgar.setFont(new Font("Dubai", Font.BOLD, 11));
			btnPgar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actionPerformedBtnNewButton(arg0);
				}
			});
			getContentPane().add(btnPgar);
		}
		{
			btnOpcion1 = new JButton("");
			btnOpcion1.setBackground(Color.LIGHT_GRAY);
			btnOpcion1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actionPerformedBtnOpcion1JButton(arg0);
				}
			});
			btnOpcion1.setIcon(new ImageIcon(VerificarReserva.class.getResource("/img/telef.png")));
			btnOpcion1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnOpcion1.setBounds(441, 159, 60, 60);
			getContentPane().add(btnOpcion1);
		}
		{
			btnOpcion2 = new JButton("");
			btnOpcion2.setBackground(Color.LIGHT_GRAY);
			btnOpcion2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnOpcion2JButton(e);
				}
			});
			btnOpcion2.setIcon(new ImageIcon(VerificarReserva.class.getResource("/img/correo.png")));
			btnOpcion2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnOpcion2.setBounds(511, 159, 60, 60);
			getContentPane().add(btnOpcion2);
		}
		{
			lblQuePodamosContactarle = new JLabel("QUE PODAMOS CONTACTARLE");
			lblQuePodamosContactarle.setForeground(Color.BLACK);
			lblQuePodamosContactarle.setFont(new Font("Dubai Light", Font.BOLD, 11));
			lblQuePodamosContactarle.setBackground(Color.GRAY);
			lblQuePodamosContactarle.setBounds(416, 132, 196, 23);
			getContentPane().add(lblQuePodamosContactarle);
		}
		{
			cbVuelo = new JComboBox<String>();
			cbVuelo.setFont(new Font("Dubai", Font.PLAIN, 13));
			cbVuelo.addActionListener(this);
			cbVuelo.setBackground(Color.WHITE);
			cbVuelo.setBounds(485, 79, 101, 25);
			getContentPane().add(cbVuelo);
		}
		{
			lblVuelo = new JLabel("Vuelo");
			lblVuelo.setForeground(Color.BLACK);
			lblVuelo.setFont(new Font("Dubai Light", Font.BOLD, 13));
			lblVuelo.setBackground(Color.GRAY);
			lblVuelo.setBounds(437, 81, 35, 23);
			getContentPane().add(lblVuelo);
		}
		{
			lblRecuerdeQueSi = new JLabel("Recuerde que si no selecciona ninguna opci\u00F3n");
			lblRecuerdeQueSi.setForeground(new Color(0, 0, 0));
			lblRecuerdeQueSi.setFont(new Font("Dubai", Font.BOLD, 12));
			lblRecuerdeQueSi.setBounds(386, 363, 238, 21);
			getContentPane().add(lblRecuerdeQueSi);
		}
		{
			lblDeContactoSe = new JLabel("de contacto se le enviar\u00E1 la factura a su correo ");
			lblDeContactoSe.setForeground(new Color(0, 0, 0));
			lblDeContactoSe.setFont(new Font("Dubai", Font.BOLD, 12));
			lblDeContactoSe.setBounds(386, 377, 244, 21);
			getContentPane().add(lblDeContactoSe);
		}
		{
			lblElectrnicoRegistrado = new JLabel(" electr\u00F3nico registrado.");
			lblElectrnicoRegistrado.setForeground(new Color(0, 0, 0));
			lblElectrnicoRegistrado.setFont(new Font("Dubai", Font.BOLD, 12));
			lblElectrnicoRegistrado.setBounds(384, 392, 158, 21);
			getContentPane().add(lblElectrnicoRegistrado);
		}
		/*-----------------*/
		cbVuelo.addItem(RealizarReserva.codVueloIda);
		if(RealizarReserva.codVueloRetorno!=null){
		cbVuelo.addItem(RealizarReserva.codVueloRetorno);
		UIManager.put("Panel.background", Color.white);
		}
	}
	protected void keyTypedTxtNumero(KeyEvent arg0) {
		char car = arg0.getKeyChar();
		if(Character.isDigit(car)){
		}else{
			arg0.consume();
			getToolkit().beep();
		}
		if (txtNumero.getText().length()>=9) {
			arg0.consume();
			getToolkit().beep();
		}
	}
	protected void keyTypedTxtEmail(KeyEvent arg0) {
		if (txtEmail.getText().length()>=30) {
			arg0.consume();
			getToolkit().beep();
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cbVuelo) {
			actionPerformedComboBox(arg0);
		}
		if (arg0.getSource() == btnRetroceder) {
			actionPerformedBtnReservar(arg0);
		}
	}
	protected void actionPerformedBtnReservar(ActionEvent arg0) {
		// BOTON RETROCEDER
		ReservarVuelo.ventanaMensaje();
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
				int num = JOptionPane.showConfirmDialog(null,
						"Si cancela se perdera los datos actuales. Desea continuar?", "", 
						JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,icono);
				if (num == 0) {
					UIManager.put("Panel.background", new Color(0,0,0,30));
					RegistroAcompañantes ra = new RegistroAcompañantes();
					RealizarReserva.btnPaso3.setEnabled(false);
					RealizarReserva.btnPaso2.setEnabled(true);
					RealizarReserva.panel.add(ra);
					ra.setVisible(true);
					this.dispose();
					RealizarReserva.listaAcompañantes.clear();
					RealizarReserva.codigosAsientos.clear();
					RealizarReserva.codigosAsientosVuelta.clear();
					RegistroAcompañantes.validaAsiento = 0;
					RegistroAcompañantes.validaAsientoVuelta = 0;
					
				}
	}
	public static String fecha(String valor){
		SimpleDateFormat sdf;
		sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date fecha=null;
		try {
			fecha = sdf.parse(valor);
			DateFormat formato = DateFormat.getDateInstance(DateFormat.LONG);
			return formato.format(fecha);
		} catch (ParseException e) {
			return null;
		}
	}
	/*-------------------------VERIFICAR VUELO---------------------------*/
	protected void actionPerformedComboBox(ActionEvent arg0) {
		//VUELOS 
		txtBoleta.setText(null);
		if(cbVuelo.getSelectedIndex()==0){
			cargarDatosDeBoleta(gv.BuscarVuelo(RealizarReserva.codVueloIda),RealizarReserva.codigosAsientos);
		}else{
			cargarDatosDeBoleta(gv.BuscarVuelo(RealizarReserva.codVueloRetorno),RealizarReserva.codigosAsientosVuelta);
		}
	}
	private void cargarDatosDeBoleta(Vuelo objVuelo,ArrayList <String> lista) {
		
		subtotal =objVuelo.getPrecioVuel()*RealizarReserva.cantiPasaj; 
		
		txtBoleta.setText("--------------------------------------------------------------\n");
		txtBoleta.append("                                       DATOS DE BOLETA                    \n");
		txtBoleta.append("--------------------------------------------------------------");
		txtBoleta.append("\nTipo de Vuelo :  " + objVuelo.getTipVuelo());
		txtBoleta.append("\tCosto :  " + (objVuelo.getPrecioVuel()*RealizarReserva.cantiPasaj));
		txtBoleta.append("\nOrigen :  " + objVuelo.getDescAeroOrig()+" - "+objVuelo.getDescCiuorig());
		txtBoleta.append("\nDestino :  " + objVuelo.getDescAeroDest()+" - "+objVuelo.getDescCiuDest());
		txtBoleta.append("\nFecha de salida :  " + fecha(objVuelo.getFechSalVue()));
		txtBoleta.append("\nHora de salida :  " + objVuelo.getHoraSalVue());
		txtBoleta.append("\nHora de Llegada :  " + objVuelo.getHoraLlegVue());
		
		txtBoleta.append("\n\n--------------------------------------------------------------\n");
		txtBoleta.append("                                  DATOS DE PASAJEROS                   \n");
		txtBoleta.append("--------------------------------------------------------------");
		/*---------------*/
		txtBoleta.append("\nTipo Pasajero :  " + RegistroAcompañantes.edad(objCliente.getFecNacClie()));
		txtBoleta.append("\nDocumento :  " + objCliente.getNomTipDocClie()+" - "+objCliente.getNumDocClie());
		txtBoleta.append("\nPasajero :  " + objCliente.getNomClie()+" "+ objCliente.getApeClie());
		txtBoleta.append("\nSexo :  " + objCliente.getSexoClie()+"\n");
		if(RealizarReserva.cantiPasaj > 1) {	
			for(Acompañante acomp : RealizarReserva.listaAcompañantes) {
			txtBoleta.append("\nTipo Pasajero :  " + RegistroAcompañantes.edad(acomp.getFecNacAcomp()));
			txtBoleta.append("\nDocumento :  " + acomp.getTipDocAcomp()+" - "+acomp.getNumDocAcomp());
			txtBoleta.append("\nPasajero :  " + acomp.getNomAcomp()+" " + acomp.getApeAcomp());
			txtBoleta.append("\nSexo :  " + acomp.getSexoAcomp());
			}
		}		
		txtBoleta.append("\n\n-------------------------------------------------------------\n");
		txtBoleta.append("                                   DATOS DE ASIENTO                   \n");
		txtBoleta.append("--------------------------------------------------------------");
		
		for(int i = 0; RealizarReserva.cantiPasaj > i;i++) {
		txtBoleta.append("\nAsiento comprado : " + lista.get(i));
		}
		txtBoleta.append("\n\n-------------------------------------------------------------\n");
		txtBoleta.append("                                          A PAGAR                       \n");
		txtBoleta.append("-------------------------------------------------------------");
		txtBoleta.append("\nSub total :   " + MetodoPago.df.format(subtotal));
		txtBoleta.append("\nIGV :   " + MetodoPago.df.format(igv()));
		txtBoleta.append("\nTotal :   " + MetodoPago.df.format(total()));
		
	}
	
	double subtotal ;
	private JButton btnPgar;
	private JButton btnOpcion1;
	private JButton btnOpcion2;
	private JLabel lblQuePodamosContactarle;
	private JComboBox<String> cbVuelo;
	private JLabel lblVuelo;
	private JLabel lblRecuerdeQueSi;
	private JLabel lblDeContactoSe;
	private JLabel lblElectrnicoRegistrado;
	
	@SuppressWarnings("unused")
	protected void actionPerformedBtnNewButton(ActionEvent arg0) {	
		//BOTON SIGUIENTE
		ReservarVuelo.ventanaMensaje();
		Cliente data=gp.BuscarClienteXusu(Iniciarsesion.usuClie);
		
		if(telef){
			validaCodigo();
			if(verificaNumero){
				telefono=txtNumero.getText();
					if(corr){
					  boolean em = verificaEmail();
						if(em){
							correo=txtEmail.getText();
								avanzar();
						}else{
							Mensaje("Correo no válido.");
						}
					}else
						avanzar();
			}
		}else if(corr){
			boolean em = verificaEmail();
			if(em){
					correo=txtEmail.getText();
					avanzar();			
			}else{
				Mensaje("Correo no válido.");
			}
		}else
			
		avanzar();
		
	}
	protected void actionPerformedBtnOpcion1JButton(ActionEvent arg0) {
		if(telef == false){
			btnOpcion1.setIcon(new ImageIcon(VerificarReserva.class.getResource("/img/telef1.png")));
			txtNumero.requestFocus();
			telef=true;
			mostrarCelular();
		}else{
		btnOpcion1.setIcon(new ImageIcon(VerificarReserva.class.getResource("/img/telef.png")));
		telef = false;
		ocultarCelular();
		}
	}
	protected void actionPerformedBtnOpcion2JButton(ActionEvent e) {
		if(corr == false){
			btnOpcion2.setIcon(new ImageIcon(VerificarReserva.class.getResource("/img/correo1.png")));
			txtEmail.requestFocus();
			corr=true;
			mostrarCorreo();
		}else{
		btnOpcion2.setIcon(new ImageIcon(VerificarReserva.class.getResource("/img/correo.png")));
		corr = false;
		ocultarCorreo();
		}
	}
	/*------------------------------------------METODOS---------------------------------*/
	private void mostrarCelular(){
		cbCodCelular.setEnabled(true);
		txtNumero.setEnabled(true);
	}
	
	private void ocultarCelular(){
		cbCodCelular.setEnabled(false);
		txtNumero.setEnabled(false);
	}
	private void mostrarCorreo(){
		txtEmail.setEnabled(true);
	}
	
	private void ocultarCorreo(){
		txtEmail.setEnabled(false);
	}
	
	private void avanzar(){
		UIManager.put("Panel.background", new Color(0,0,0,30));
		RealizarReserva.btnPaso3.setEnabled(false);
		RealizarReserva.btnPaso4.setEnabled(true);
		MetodoPago mp = new MetodoPago();
		RealizarReserva.panel.add(mp);
		mp.toFront();
		mp.setVisible(true);
		this.dispose();
	}
	private double igv() {
		return subtotal * 0.18;
	}
	
	private double total() {
		return subtotal + igv();
	}
	
	private boolean verificaEmail() {
		
		String patron = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		/*if(cbTerminos.isSelected() == false) 
			return 0;*/
		if(Pattern.matches(patron, txtEmail.getText()))
			return true;
		else
			return false;
	}
	
	private void validaCodigo() {
		
		if(cbCodCelular.getSelectedIndex() == 0 ) {
			
			String patronCod = "[0-9]{8}";
			if(Pattern.matches(patronCod, txtNumero.getText())) {
				verificaNumero = true;}
			else {
				Mensaje("Argentina","8");}
			
		}else if(cbCodCelular.getSelectedIndex() == 1) {
			
			String patronCod = "[0-9]{8}";
			if(Pattern.matches(patronCod, txtNumero.getText())) {
				verificaNumero = true;}
			else {
				Mensaje("Bolivia","8");}
			
		}else if(cbCodCelular.getSelectedIndex() == 2) {
			
			String patronCod = "[0-9]{10,11}";
			if(Pattern.matches(patronCod, txtNumero.getText())) {
				verificaNumero = true;}
			else {
				Mensaje("Brasil", "10 o 11");}
			
		}else if(cbCodCelular.getSelectedIndex() == 3) {
			
			String patronCod = "[0-9]{10}";
			if(Pattern.matches(patronCod, txtNumero.getText())) {
				verificaNumero = true;}
			else {
				Mensaje("Colombia", "10");}
			
		}else if(cbCodCelular.getSelectedIndex() == 4) {
			
			String patronCod = "[0-9]{10}";
			if(Pattern.matches(patronCod, txtNumero.getText())) {
				verificaNumero = true;}
			else {
				Mensaje("Ecuador", "10");}
			
		}else if(cbCodCelular.getSelectedIndex() == 5) {
			
			String patronCod = "[0-9]{8,9}";
			if(Pattern.matches(patronCod, txtNumero.getText())) {
				verificaNumero = true;}
			else {
				Mensaje("Paraguay", "8 o 9");}
			
		}else if(cbCodCelular.getSelectedIndex() == 6) {
			
			String patronCod = "[0-9]{9}";
			if(Pattern.matches(patronCod, txtNumero.getText())) {
				verificaNumero = true;}
			else {
				Mensaje("Perú", "9");}
			
		}else if(cbCodCelular.getSelectedIndex() == 7) {
			
			String patronCod = "[0-9]{8}";
			if(Pattern.matches(patronCod, txtNumero.getText())) {
				verificaNumero = true;}
			else {
				Mensaje("Uruguay", "8");}
			
		}else if(cbCodCelular.getSelectedIndex() == 8) {
			
			String patronCod = "[0-9]{7}";
			if(Pattern.matches(patronCod, txtNumero.getText())) {
				verificaNumero = true;}
			else {
				Mensaje("Venezuela", "7");}
			
		}
	}
	
	private void Mensaje(String x, String y) {
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		JOptionPane.showMessageDialog(null, "Esta tratando de usar un número de " +x+ " , necesita solo "+y+" dígitos",
				"",JOptionPane.PLAIN_MESSAGE,icono);
	}
	private void Mensaje(String e){
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		JOptionPane.showMessageDialog(null, e,"INFORMACIÓN DE REGISTRO",JOptionPane.PLAIN_MESSAGE,icono);
	}
}
