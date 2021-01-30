package vista_reserva;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import libreria.Diseño;
import mantenimientos.GestionVuelo;
import model.Aeropuerto;
import model.Vuelo;
import javax.swing.ImageIcon;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.ScrollPaneConstants;
public class ReservarVuelo extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblReservaTuVuelo;
	private JTextField txtOrigen;
	private JTextField txtDestino;
	private JLabel label;
	private JLabel label1;
	private JLabel lblOrigen;
	private JLabel lblDestino;
	private JLabel lblSalida;
	private JDateChooser dtSalida;
	private JDateChooser dtRegreso;
	private JLabel lblRetorno;
	private JLabel lblNPasajeros;
	private JTable tbVuelos;
	private JButton btnSiguiente;
	private JButton btnBuscar;
	private JComboBox<String> cbNumPasajeros;
	private JLabel lblVuelo;
	private JComboBox<String> cbTipoVuelo;
	private JScrollPane scrollPane;
	private JButton btnDetalles;
	private DefaultTableModel dm;
	private JButton btnBorrar;
	private JButton btnBorrar2;
	/*----------------------------------------------------*/
	libreria.Diseño img = new Diseño();
	Date fecha;
	Vuelo v;
	GestionVuelo gv=new GestionVuelo();
	List<Vuelo> data;
	/*----------------------------------------------------*/
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservarVuelo frame = new ReservarVuelo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ReservarVuelo() {
		getContentPane().setFont(new Font("Arial", Font.BOLD, 12));
		setBorder(null);
		// FONDO TRANSPARENTE
		this.setBackground(new Color(0, 0, 0,30));
		setBounds(0, 0, 661, 513);
		// QUITAR LA BARRA DE TITULO
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		getContentPane().setLayout(null);
		/*-----------DISEÑO DEL JDATECHOOSER-----------*/
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", new Color(175, 238, 238));
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
		/*----------------COMPONENTES----------------*/
		{
			dtRegreso = new JDateChooser();
			dtRegreso.setBackground(Color.WHITE);
			dtRegreso.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			dtRegreso.getCalendarButton().setIcon(new ImageIcon(ReservarVuelo.class.getResource("/icon/calendario.png")));
			dtRegreso.getCalendarButton().setBackground(Color.WHITE);
			dtRegreso.setBounds(341, 118, 159, 25);
			getContentPane().add(dtRegreso);
			//CAJA DE TEXTO DEL JDATECHOOSER
			((JTextField)dtRegreso.getDateEditor()).setEditable(false);
			((JTextField)dtRegreso.getDateEditor()).setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
			//VALIDAR FECHA
			dtRegreso.setSelectableDateRange(new Date(),new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(60)));
		}
		{
			dtSalida = new JDateChooser();
			dtSalida.setBackground(Color.WHITE);
			dtSalida.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			dtSalida.getCalendarButton().setIcon(new ImageIcon(ReservarVuelo.class.getResource("/icon/calendario.png")));
			dtSalida.getCalendarButton().setBackground(Color.WHITE);
			dtSalida.setBounds(149, 119, 159, 25);
			getContentPane().add(dtSalida);
			//CAJA DE TEXTO DEL JDATECHOOSER
			((JTextField)dtSalida.getDateEditor()).setEditable(false);
			((JTextField)dtSalida.getDateEditor()).setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
			((JTextField)dtSalida.getDateEditor()).setBackground(Color.WHITE);
			//SELECCIONAR UN RANGO DE FECHAS
			dtSalida.setSelectableDateRange(new Date(),new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(60)));
		}
		{
			lblReservaTuVuelo = new JLabel("RESERVA TU VUELO AHORA");
			lblReservaTuVuelo.setForeground(new Color(0, 0, 0));
			lblReservaTuVuelo.setFont(new Font("Dubai", Font.BOLD, 16));
			lblReservaTuVuelo.setBounds(206, 20, 227, 19);
			getContentPane().add(lblReservaTuVuelo);
		}
		{
			txtOrigen = new JTextField();
			txtOrigen.setMargin(new Insets(0, 0, 0, 0));
			txtOrigen.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			txtOrigen.addActionListener(this);
			txtOrigen.setBounds(23, 63, 265, 25);
			getContentPane().add(txtOrigen);
			txtOrigen.requestFocus();
			completarOrigen();
			txtOrigen.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					keyTypedTxtOrigen(e);
				}
			});
		}
		{
			txtDestino = new JTextField();
			txtDestino.setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
			txtDestino.setColumns(10);
			txtDestino.setBounds(352, 63, 252, 25);
			getContentPane().add(txtDestino);
			completarDestino();
			txtDestino.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					keyTypedTxtDestino(e);
				}
			});
		}
		{
			label = new JLabel("");
			label.setIcon(new ImageIcon(ReservarVuelo.class.getResource("/icon/flecha2.png")));
			label.setBounds(312, 121, 29, 22);
			getContentPane().add(label);
			img.centrar(label);
		}
		{
			label1 = new JLabel("");
			label1.setIcon(new ImageIcon(ReservarVuelo.class.getResource("/icon/flecha2.png")));
			label1.setBounds(324, 63, 29, 22);
			getContentPane().add(label1);
			img.centrar(label1);
		}
		{
			lblOrigen = new JLabel("Origen");
			lblOrigen.setForeground(new Color(0, 0, 0));
			lblOrigen.setFont(new Font("Arial", Font.BOLD, 12));
			lblOrigen.setBounds(23, 44, 38, 15);
			getContentPane().add(lblOrigen);
			
		}
		{
			lblDestino = new JLabel("Destino ");
			lblDestino.setForeground(new Color(0, 0, 0));
			lblDestino.setFont(new Font("Arial", Font.BOLD, 12));
			lblDestino.setBounds(354, 44, 46, 15);
			getContentPane().add(lblDestino);
		}
		{
			lblSalida = new JLabel("Salida ");
			lblSalida.setForeground(new Color(0, 0, 0));
			lblSalida.setFont(new Font("Arial", Font.BOLD, 12));
			lblSalida.setBounds(149, 99, 38, 15);
			getContentPane().add(lblSalida);
		}
		{
			lblRetorno = new JLabel("Retorno");
			lblRetorno.setForeground(new Color(0, 0, 0));
			lblRetorno.setFont(new Font("Arial", Font.BOLD, 12));
			lblRetorno.setBounds(341, 99, 45, 15);
			getContentPane().add(lblRetorno);
		}
		{
			lblNPasajeros = new JLabel("N\u00B0 Pasajeros");
			lblNPasajeros.setForeground(new Color(0, 0, 0));
			lblNPasajeros.setFont(new Font("Arial", Font.BOLD, 12));
			lblNPasajeros.setBounds(536, 7, 100, 14);
			getContentPane().add(lblNPasajeros);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setFont(new Font("Dubai", Font.BOLD, 11));
			scrollPane.setAutoscrolls(true);
			scrollPane.setBackground(Color.DARK_GRAY);
			scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			scrollPane.setViewportBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBorder(null);
			scrollPane.setBounds(22, 170, 614, 280);
			getContentPane().add(scrollPane);
			{
				tbVuelos = new JTable();
				tbVuelos.setFont(new Font("Dubai", Font.PLAIN, 13));
				tbVuelos.setSelectionBackground(new Color(135, 206, 250));
				tbVuelos.setGridColor(new Color(70, 130, 180));
				tbVuelos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				scrollPane.setViewportView(tbVuelos);
				tbVuelos.setBorder(null);
				/*----------DISEÑO DE BORDER DE LA TABLA--------*/
				tbVuelos.setShowHorizontalLines(true);
				tbVuelos.setShowVerticalLines(true);
				tbVuelos.setGridColor(Color.gray);
				//TAMAÑO DE FILA
				tbVuelos.setRowHeight(28);
				tbVuelos.setForeground(Color.BLACK);
				tbVuelos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						mouseClickedtbVuelos(arg0);
					}
				});
			}
			{

				/*--------RENDERIZAR LA TABLA--------------*/
				tbVuelos.setDefaultRenderer(Object.class, new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(JTable table, Object value, 
							boolean isSelected, boolean hasFocus,
							int row, int column) {

						if (value instanceof JButton) {
							JButton btn=(JButton)value;
							if (isSelected) {
								btn.setForeground(table.getSelectionForeground());
							}else{
								btn.setForeground(table.getForeground());
								btn.setForeground(table.getBackground());
							}
							return btn;
						}
						return getTableCellRendererComponent(table, value, isSelected,
								hasFocus, row, column);
					}
				});
				
				dm=new DefaultTableModel(){
				private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row,int column){
							return false;
					}		
				};
				/*------NOMBRE DE LAS COLUMNAS---------*/
				dm.addColumn("Vuelo");
				dm.addColumn("Tipo vuelo");
				dm.addColumn("Origen");
				dm.addColumn("Destino");
				dm.addColumn("Tiempo");
				dm.addColumn("");
			}
		}
		{
			//CREANDO EL BOTON DETALLE DE LA TABLA
		    btnDetalles=new JButton("");
			btnDetalles.setName("d");
			btnDetalles.setIcon(new ImageIcon(MetodoPago.class.getResource("/icon/detalle.png")));
			btnDetalles.setBackground(Color.WHITE);
			btnDetalles.setBorder(null);
			btnDetalles.setForeground(tbVuelos.getBackground());
		}
		{
			btnSiguiente = new JButton("SIGUIENTE");
			btnSiguiente.setFocusable(false);
			btnSiguiente.addActionListener(this);
			btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSiguiente.setBackground(new Color(128, 0, 0));
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setFont(new Font("Dubai", Font.BOLD, 11));
			btnSiguiente.setBounds(338, 461, 142, 38);
			getContentPane().add(btnSiguiente);
		}
		{
			btnBuscar = new JButton("BUSCAR");
			btnBuscar.setFocusable(false);
			btnBuscar.addActionListener(this);
			btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnBuscar.setIcon(null);
			btnBuscar.setForeground(new Color(255, 255, 255));
			btnBuscar.setFont(new Font("Dubai", Font.BOLD, 11));
			btnBuscar.setBackground(new Color(70, 130, 180));
			btnBuscar.setBounds(524, 116, 100, 30);
			getContentPane().add(btnBuscar);
		}
		{
			cbNumPasajeros = new JComboBox<String>();
			cbNumPasajeros.setBorder(null);
			cbNumPasajeros.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4"}));
			cbNumPasajeros.setSelectedIndex(0);
			cbNumPasajeros.setBackground(Color.WHITE);
			cbNumPasajeros.setBounds(536, 27, 100, 25);
			cbNumPasajeros.setSelectedIndex(0);
			((JLabel)cbNumPasajeros.getRenderer()).setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 5));
			getContentPane().add(cbNumPasajeros);
		}
		{
			lblVuelo = new JLabel("Tipo Vuelo");
			lblVuelo.setForeground(new Color(0, 0, 0));
			lblVuelo.setBounds(20, 99, 59, 15);
			getContentPane().add(lblVuelo);
		}
		{
			cbTipoVuelo = new JComboBox<String>();
			cbTipoVuelo.setBorder(null);
			cbTipoVuelo.addActionListener(this);
			cbTipoVuelo.setModel(new DefaultComboBoxModel<String>(new String[] {"Ida - Vuelta", "Ida"}));
			cbTipoVuelo.setBackground(Color.WHITE);
			cbTipoVuelo.setBounds(20, 119, 114, 25);
			cbTipoVuelo.setSelectedIndex(-1);
			((JLabel)cbTipoVuelo.getRenderer()).setBorder(BorderFactory.createEmptyBorder(0,8, 0, 5));
			getContentPane().add(cbTipoVuelo);
		}
		{
			btnBorrar = new JButton("");
			btnBorrar.addActionListener(this);
			btnBorrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnBorrar.setBorder(null);
			btnBorrar.setBackground(new Color(255, 255, 255));
			btnBorrar.setIcon(new ImageIcon(ReservarVuelo.class.getResource("/icon/x.png")));
			btnBorrar.setBounds(288, 63, 29, 25);
			getContentPane().add(btnBorrar);
		}
		{
			btnBorrar2 = new JButton("");
			btnBorrar2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnBorrar2.addActionListener(this);
			btnBorrar2.setIcon(new ImageIcon(ReservarVuelo.class.getResource("/icon/x.png")));
			btnBorrar2.setBorder(null);
			btnBorrar2.setBackground(Color.WHITE);
			btnBorrar2.setBounds(603, 63, 29, 25);
			getContentPane().add(btnBorrar2);
		}
		/*-------------------------------LISTAR LA TABLA---------------------*/
		Tabla(gv.listaVuelo("Ida"),null);
		Tabla(gv.listaVuelo("Ida"),gv.listaVuelo("Vuelta"));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBorrar2) {
			actionPerformedBtnBorrar2(e);
		}
		if (e.getSource() == btnBorrar) {
			actionPerformedBtnBorrar(e);
		}
		if (e.getSource() == cbTipoVuelo) {
			actionPerformedCbTipoVuelo(e);
		}
		if (e.getSource() == btnSiguiente) {
			actionPerformedBtnSiguiente(e);
		}
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}

	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		// BOTON BUSCAR
		ventanaMensaje();
		try {
			if(!txtOrigen.getText().isEmpty()){
				String origen[]=txtOrigen.getText().split(" - ");
				
				if(!txtDestino.getText().isEmpty()){
					String destino[]=txtDestino.getText().split(" - ");
					
					 if(!origen[0].equals(destino[0])){
						 
						 	if(cbTipoVuelo.getSelectedIndex()!=-1){
							 							 								 
									 if(dtSalida.getDate()!=null ){
										 								
										 /*-----------------------VUELO IDA Y VUELTA-----------------------------*/
										if (cbTipoVuelo.getSelectedIndex()==0) {
											
											if (dtRegreso.getDate()!=null){
												 
												if(ValidarFechasVuelo() || cbTipoVuelo.getSelectedIndex()==1){
											/*-----------------------------VUELO IDA Y VUELTA---------------------*/
													
													List<Vuelo> data=gv.BuscarTipVuelo(leerOrigenRet(), leerDestinoRet(), leerVuelta(),
															Integer.parseInt(cbNumPasajeros.getSelectedItem().toString()),
															convierteFecha(dtRegreso.getDate()));
													
													List<Vuelo> data1=gv.BuscarTipVuelo(leerOrigen(), leerDestino(), leerIda(),
															Integer.parseInt(cbNumPasajeros.getSelectedItem().toString()),
															convierteFecha(dtSalida.getDate()));
													
									                dm.setRowCount(0);/*LIMPIA TABLA ANTES DE AGREGAR*/
													Tabla(data1,data);	
														if(tbVuelos.getRowCount()==0){
															Mensaje("No se encontro ningun vuelo\n con los datos registrados");
															Tabla(gv.listaVuelo("Ida"),null);
															Tabla(gv.listaVuelo("Ida"),gv.listaVuelo("Vuelta"));
														}
													}else{
													 	Mensaje("La fecha de salida debe ser menor\n       que la fecha de retorno");
													}
											}else{
												Mensaje("No ingreso la fecha de retorno");
											}
										/*-------------------------------VUELO SOLO IDA---------------------------*/				
										}else{
											 data=gv.BuscarTipVuelo(leerOrigen(), leerDestino(), leerIda(),
																	Integer.parseInt(cbNumPasajeros.getSelectedItem().toString()),
																	convierteFecha(dtSalida.getDate()));
											 dm.setRowCount(0);/*LIMPIA TABLA ANTES DE AGREGAR*/
											 Tabla(data,null);
											 	if(tbVuelos.getRowCount()==0){
												 	Mensaje("No se encontro ningun vuelo\n con los datos registrados");
													Tabla(gv.listaVuelo("Ida"),null);
													Tabla(gv.listaVuelo("Ida"),gv.listaVuelo("Vuelta"));
											 	}
											}
									 }else{
										 Mensaje("No ingreso la fecha de salida");}
						 		}else{
						 			Mensaje("Debe insertar un tipo de vuelo");}
					 	}else{
						 Mensaje("Estan incorrectos los datos de origen y destino,\npor favor vuelve a ingresar");	
				  	 	txtDestino.setText(null);
				  	 	txtOrigen.setText(null);}
				}else{
					datoVacio(txtDestino,btnBorrar2);}
			}else{
				datoVacio(txtOrigen,btnBorrar);}
		} catch (Exception e2) {
				Mensaje("ERROR -->"+e2.getMessage());
		}
	}

	protected void actionPerformedBtnSiguiente(ActionEvent e) {
		// BOTON SIGUIENTE
		ventanaMensaje();
		try{
			RealizarReserva.cantiPasaj=Integer.parseInt(cbNumPasajeros.getSelectedItem().toString());
			int filas=tbVuelos.getSelectedRow();
			String codVuelo=(String) tbVuelos.getValueAt(filas, 0);
			String ida=(String) tbVuelos.getValueAt(filas, 1);
			Icon icono=new ImageIcon(getClass().getResource("/icon/viaje.png"));
			int num=JOptionPane.showConfirmDialog(null, "Vuelo seleccionado ￫ "+codVuelo+"\nN° de asientos por vuelo ￫ "+
												RealizarReserva.cantiPasaj,"", JOptionPane.OK_CANCEL_OPTION,
												JOptionPane.PLAIN_MESSAGE,icono);
			
			if(num==0){
				if(ida.equals("Ida")){
					RealizarReserva.codVueloIda=codVuelo;
					RealizarReserva.codVueloRetorno=null;
					avanzar();
				}else{
					RealizarReserva.codVueloIda=codVueloIda(codVuelo);
					RealizarReserva.codVueloRetorno=codVueloRetor(codVuelo);
					avanzar();
				}
				RealizarReserva.btnCancela.setVisible(false);
			}
				
		}catch(Exception e1)  {
			Mensaje("Debe seleccionar un vuelo");
		}
	}	
	protected void actionPerformedCbTipoVuelo(ActionEvent e) {
		//COMBOBOX TIPO DE VUELO
		if (cbTipoVuelo.getSelectedIndex() == 0) {
			((JTextField)dtRegreso.getDateEditor()).setBackground(Color.WHITE);
			dtRegreso.setEnabled(true);
		} else {
			dtRegreso.setEnabled(false);
			dtRegreso.setDate(null);
		}
	}
	protected void actionPerformedBtnBorrar(ActionEvent e) {
		//BORRAR
		txtOrigen.setText(null);
	}
	protected void actionPerformedBtnBorrar2(ActionEvent e) {
		//BORRAR
		txtDestino.setText(null);
	}
	/*-----------------------------------------------EVENTOS---------------------------------*/
	protected void keyTypedTxtOrigen(KeyEvent arg0) {
		limpiarVacio(txtOrigen,btnBorrar);
		char car = arg0.getKeyChar();
		if(Character.isLetter(car) || Character.isSpaceChar(car)){
		}else{
			arg0.consume();
			getToolkit().beep();
		}
	}
	protected void keyTypedTxtDestino(KeyEvent arg0) {
		limpiarVacio(txtDestino,btnBorrar2);
		char car = arg0.getKeyChar();
		if(Character.isLetter(car) || Character.isSpaceChar(car)){
		}else{
			arg0.consume();
			getToolkit().beep();
		}
	}
	/*----------------------------------MÉTODOS-----------------------------------------------*/
	public String leerIda(){
		String texto=cbTipoVuelo.getSelectedItem().toString();
		String c[]=texto.split(" - ");
		return c[0];
	}
	public String  leerVuelta(){
		String texto=cbTipoVuelo.getSelectedItem().toString();
		String c[]=texto.split(" - ");
		return c[1];
	}
	//METODO DE DIFERENCIAS DE FECHAS
	public static  String tiempoVuelo(String salida,String llegada){
		long diff;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date tiempo1 = sdf.parse(salida);
			Date tiempo2=sdf.parse(llegada);
			diff=tiempo2.getTime()-tiempo1.getTime();
			
			long hora = TimeUnit.MILLISECONDS.toHours(diff);
		    long minuto=TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
		   //long segundo=TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
		    
			if(minuto==0){
				return hora+"h "+minuto+"0min";
			}else if(hora==0){
				return minuto+"min";
			}else{	
			return hora+"h "+minuto+"min";
			}
		} catch (ParseException e) {
			return null;
		}
	}
	/* CONVIERTE FECHA A FORMATO yyyy-MM-dd*/
	private String convierteFecha(Date fecha) {
		String miFormato = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(miFormato);

		return sdf.format(fecha);
	}
	//VERIFICA QUE LA FECHA DE SALIDA SEA MENOR QUE LA FECHA DE RETORNO
	private boolean ValidarFechasVuelo(){		
		if(dtSalida.getDate().before(dtRegreso.getDate())){
			return true;
		}
		return false;
	}
	
	private void avanzar() {
		UIManager.put("Panel.background", new Color(0,0,0,30));
		RegistroAcompañantes ra = new RegistroAcompañantes();
		RealizarReserva.btnPaso2.setEnabled(true);
		RealizarReserva.btnPaso1.setEnabled(false);
		RealizarReserva.panel.add(ra);
		ra.toFront();
		ra.setVisible(true);
		this.dispose();
	}
	
	/*----------------------------------------------AUTOCOMPLETADOR---------------------------------*/
	//COMPLETA DATOS
	private void completarOrigen(){
		TextAutoCompleter ac;
		List<Aeropuerto> data=gv.ListarOrigen();
		ac=new TextAutoCompleter(txtOrigen);
		for(Aeropuerto a:data){
			Object[] origen={"Arpt. "+
							a.getDescAero()+
							" - "+a.getDescCiu()+" - "+a.getCodigo()};	
			for (int i = 0; i < origen.length; i++) {
				ac.addItem(origen[i]);
			}
		}
		
	}
	private void completarDestino(){
		TextAutoCompleter ac;
		List<Aeropuerto> data=gv.ListarDestino();
		ac=new TextAutoCompleter(txtDestino);
		for(Aeropuerto a:data){
			Object[] destino={"Arpt. "+
							a.getDescAero()+
							" - "+a.getDescCiu()+" - "+a.getCodigo()};	
			for (int i = 0; i < destino.length; i++) {
				ac.addItem(destino[i]);
			}
		}
	}
	public static void datoVacio(JTextField txt,JButton btn){
		txt.setBackground(new Color(202, 207, 210));
		txt.requestFocus();
		if(btn!=null){
			btn.setBackground(new Color(202, 207, 210));
		}
	}
	public static void limpiarVacio(JTextField txt,JButton btn){
		txt.setBackground(new Color(255, 255, 255));
		txt.setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
		if(btn!=null){
			btn.setBackground(new Color(255, 255, 255));
		}
	}
	//LEER CODIGO DE ORIGEN DE IDA Y RETORNO
	public String leerOrigen(){
		String texto=null;
		String c[]=txtOrigen.getText().split(" - ");
		texto=c[2];
		return texto;
	}
	private String leerOrigenRet(){
		String texto=null;
		String c[]=txtDestino.getText().split(" - ");
		texto=c[2];
		return "O"+texto.charAt(1)+texto.charAt(2);
	}
	/*LEER CODIGO DE DESTINO DE IDA Y RETORNO*/
	public String leerDestino(){
		String texto=null;
		String c[]=txtDestino.getText().split(" - ");
		texto=c[2];
		return texto;
	}
	private String leerDestinoRet(){
		String texto=null;
		String c[]=txtOrigen.getText().split(" - ");
		texto=c[2];
		return "D"+texto.charAt(1)+texto.charAt(2);
	}
	//METODO PARA OBTENER EL CODIGO DEL VUELO DE IDA Y VUELTA
	private String codVueloIda(String s){
		String c[]=s.split(" - ");
		return c[0];
	}
	private String codVueloRetor(String s){
		String c[]=s.split(" - ");
		return c[1];
	}
	/*-------------------------------------------TABLA--------------------------------------------*/
	/*--------------------------------------DISEÑO TABLA--------------------------------*/
	//PERSONALIZAR TABLA

	private void Tabla(List<Vuelo> ida, List<Vuelo> retorno){
		
		/*----------------------DATOS DEL VUELO IDA--------------------*/
		if(retorno==null){
		data=ida;	
		if(data.size()>0){
			for (int i = 0; i < data.size(); i++) {
				Object fila[]=new Object[6];
				v=data.get(i);
				fila[0]=v.getCodVue();
				fila[1]=v.getTipVuelo();
				fila[2]=v.getDescCiuorig();
				fila[3]=v.getDescCiuDest();
				fila[4]=tiempoVuelo(v.getFechSalVue()+" "+v.getHoraSalVue(),v.getFechSalVue()+" "+v.getHoraLlegVue());
				fila[5]=btnDetalles;
				dm.addRow(fila);
			}
		}
		/*---------------DATOS DEL VUELO VUELTA------------------*/
		}else{
		data=retorno;
		List<Vuelo> data2=ida;
		Vuelo v2=new Vuelo();
		if(data.size()>0 && data2.size()>0){
			for (int i = 0; i < data.size() && i < data2.size(); i++) {
				Object fila[]=new Object[6];
				v=data.get(i);
				v2=data2.get(i);
				
				fila[0]=v2.getCodVue()+" - "+v.getCodVue();
				fila[1]=v2.getTipVuelo()+"-"+v.getTipVuelo();
				fila[2]=v2.getDescCiuorig();
				fila[3]=v2.getDescCiuDest();
				fila[4]=tiempoVuelo(v.getFechSalVue()+" "+v.getHoraSalVue(),v.getFechSalVue()+" "+v.getHoraLlegVue());
				fila[5]=btnDetalles;
				dm.addRow(fila);
			}
		}
		}
		
		/*------------ADJUNTAR DATOS A LA TABLA----------------*/
		tbVuelos.setModel(dm);	
		ajustarTabla();
	}	
	/*-------------------------------------METODO PARA PERSONALIZAR JOPTIONPANE--------------------*/
	public static void ventanaMensaje(){
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", new Color(70, 130, 180));
		UIManager.put("OptionPane.okButtonText", "  Aceptar ");
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
	}
	/*-----------------------------------------INFORMACIÓN DEL VUELO-------------------------------*/
	private JTextArea  InformacionVuelo(List<Vuelo> ida, List<Vuelo> retorno){
		//
		JTextArea infor=new JTextArea();

		DefaultTableModel tm = (DefaultTableModel) tbVuelos.getModel();		
		String dato=String.valueOf(tm.getValueAt(tbVuelos.getSelectedRow(),0));
		
		//FILAS SELECCCIONADAS		
		Vuelo v= new Vuelo();
		/*-----------------------DATOS DE IDA-------------------*/
			data=ida;	
			if(data.size()>0){
				for (int i = 0; i < data.size(); i++) {
					v=data.get(i);
					if(v.getCodVue().equals(dato)){
					infor.setText(
							VerificarReserva.fecha(v.getFechSalVue())+"\n"+
							v.getHoraSalVue()+" "+(v.getDescCiuorig()).toUpperCase()+"  🢂  "+
							(v.getDescCiuDest()).toUpperCase()+" "+v.getHoraLlegVue()+
							"\n\nOrigen »   Arpt. "+v.getDescAeroOrig()+
							"\nDestino »   Arpt. "+v.getDescAeroDest()+
							"\nAsiento disponibles »  "+v.getCantiAsieVue()+"                "+
							"\tPrecio »  S/."+v.getPrecioVuel());
					infor.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 15));
					infor.setEditable(false);
					infor.setBackground(Color.WHITE);
					return infor;
					}
				}
			/*---------------DATOS DEL VUELO VUELTA------------------*/
			data=ida;
			List<Vuelo> dataRetor=retorno;
			Vuelo v2=new Vuelo();
			if(data.size()>0 && dataRetor.size()>0){
				for (int i = 0; i < data.size() && i < dataRetor.size(); i++) {
					v=data.get(i);
					v2=dataRetor.get(i);
					if(v.getCodVue().equals(codVueloIda(dato)) && v2.getCodVue().equals(codVueloRetor(dato))){
						infor.setText(
								VerificarReserva.fecha(v.getFechSalVue())+"\n"+
								v.getHoraSalVue()+" "+(v.getDescCiuorig()).toUpperCase()+"  🢂  "+
								(v.getDescCiuDest()).toUpperCase()+" "+v.getHoraLlegVue()+
								"\n\nOrigen »   Arpt. "+v.getDescAeroOrig()+
								"\nDestino »   Arpt. "+v.getDescAeroDest()+
								"\nAsiento disponibles »  "+v.getCantiAsieVue()+"                "+
								"\tPrecio »  S/."+v.getPrecioVuel()+
								
								"\n__________________________________________________\n\n"+
								
								VerificarReserva.fecha(v2.getFechSalVue())+"\n"+
								v2.getHoraSalVue()+" "+(v2.getDescCiuorig()).toUpperCase()+"  🢂  "+
								(v2.getDescCiuDest()).toUpperCase()+" "+v2.getHoraLlegVue()+
								"\n\nOrigen »   Arpt. "+v2.getDescAeroOrig()+
								"\nDestino »   Arpt. "+v2.getDescAeroDest()+
								"\nAsiento disponibles »  "+v2.getCantiAsieVue()+"                "+
								"\tPrecio »  S/."+v.getPrecioVuel());
						infor.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 15));
						infor.setBackground(Color.white);
						infor.setEditable(false);
						return infor;
					}
				}
			}
		}
		return null;
	}
	protected void mouseClickedtbVuelos(MouseEvent arg0){
		InformacionVuelo(gv.listaVuelo("Ida"),gv.listaVuelo("Vuelta"));			
		
		int column=tbVuelos.getColumnModel().getColumnIndexAtX(arg0.getX());
		int row=arg0.getY()/tbVuelos.getRowHeight();
			
		//VERIFICANDO QUE CUANDO HAGA CLICK ESTE DENTRO DE LA TABLA
		if (row<tbVuelos.getRowCount() && row>=0 && column<tbVuelos.getColumnCount() && column>=0) {
			Object value=tbVuelos.getValueAt(row, column);
			if (value instanceof JButton) {
				((JButton)value).doClick();
				btnDetalles.setBackground(tbVuelos.getSelectionBackground());
				ventanaMensaje();
				JOptionPane.showMessageDialog(null, InformacionVuelo(gv.listaVuelo("Ida"),gv.listaVuelo("Vuelta")),"",
						JOptionPane.PLAIN_MESSAGE);
				
			}
			btnDetalles.setBackground(Color.white);
		}
	}
	/*--------------------------------PERSONALIZAR TABLA-------------------------------*/
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
		
	private void ajustarTabla(){
		TableColumnModel tcm= tbVuelos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(8));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(5));
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i <5; i++) {
			tbVuelos.getColumnModel().getColumn(i).setCellRenderer(tcr); 
		}
		
		DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);
		tcr1.setBackground(Color.DARK_GRAY);
		tcr1.setForeground(Color.white);
		for (int i = 0; i < 6; i++) {
			tbVuelos.getColumnModel().getColumn(i).setHeaderRenderer(tcr1);
		}
	}
	/*------------------------------VENTANA DE MENSAJE---------------------------*/
	private void Mensaje(String e){
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		JOptionPane.showMessageDialog(null, e,"",JOptionPane.PLAIN_MESSAGE,icono);
	}
	
}
