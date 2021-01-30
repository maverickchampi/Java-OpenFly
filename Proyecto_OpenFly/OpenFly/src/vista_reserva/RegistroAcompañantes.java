package vista_reserva;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import mantenimientos.GestionPasajeros;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import model.Acompañante;
import vista_sesion.Iniciarsesion;

public class RegistroAcompañantes extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblRegistroDeAcompaantes;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JLabel lblApellido;
	private JLabel lblFechaNacimiento;
	private JLabel lblTipoDeDocumento;
	private JComboBox<String> cbDocumento;
	private JTextField txtNumDocumento;
	private JComboBox<String> cbSexo;
	private JLabel lblFechaNacimiento_1;
	private JDateChooser dcFechNacimiento;
	private JLabel lblCantidad;
	private JButton btnAadir;
	private JButton btnModificar;
	private JButton btnElegirAsiento;
	private JButton btnSiguiente;
	private JButton btnRetroceder;
	private JScrollPane scrollPane;
	private JTable jtRegistro;
	private JButton btnBorrarNombre;
	private JButton btnborrarApellido;
	private JButton btnBorrarDoc;
	private JLabel lblNroDeDocumento;
	/*------------------------------------------------*/
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row,int column){
		return false;
		}
	};
	GestionPasajeros gp=new GestionPasajeros();
	int filaSeleccion = 0;// INDICA FILA SELECCIONADA EN TABLA 
	private int compania = RealizarReserva.cantiPasaj - 1;//CANTIDAD DE ACOMPAÑANTES
	public static boolean escogioAsiento = false;//BOOLEAN QUE ANTES DE AVANZAR DEBE VERIFICAR SI YA ESCOGIO LOS ASIENTOS
	public static int validaAsiento=0;// VERIFICA SI YA ESCOGIO LOS ASIENTO DE IDA
	public static int validaAsientoVuelta =0;// VERIFICA SI YA ESCOGIO LOS ASIENTO DE VUELTA

	/*------------------------------------------------*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroAcompañantes frame = new RegistroAcompañantes();
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
	public RegistroAcompañantes() {
		getContentPane().setFont(new Font("Arial", Font.BOLD, 12));
		setBorder(null);
		// AÑADIR TRANSPARENCIA
		this.setBackground(new Color(0, 0, 0, 30));
		setBounds(0, 0, 661, 513);
		// QUITAR LA BARRA DE TITULO
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		getContentPane().setLayout(null);
		/*-----------DISEÑO DEL JDATECHOOSER-----------*/
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background",new Color(175, 238, 238));
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
		/*----------------COMPONENTES----------------*/
		{
			lblRegistroDeAcompaantes = new JLabel("REGISTRO DE PASAJEROS");
			lblRegistroDeAcompaantes.setForeground(new Color(0, 0, 0));
			lblRegistroDeAcompaantes.setFont(new Font("Dubai", Font.BOLD, 16));
			lblRegistroDeAcompaantes.setBounds(231, 11, 190, 28);
			getContentPane().add(lblRegistroDeAcompaantes);
		}
		{
			lblNombre = new JLabel("Nombres");
			lblNombre.setForeground(new Color(0, 0, 0));
			lblNombre.setFont(new Font("Dubai", Font.BOLD, 12));
			lblNombre.setBounds(24, 97, 83, 14);
			getContentPane().add(lblNombre);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtNombre.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			txtNombre.setBounds(24, 113, 356, 25);
			getContentPane().add(txtNombre);
			txtNombre.setColumns(10);
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					keyTypedTxtNombre(e);
				};
			});
			
		}
		{
			txtApellido = new JTextField();
			txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtApellido.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			txtApellido.setColumns(10);
			txtApellido.setBounds(24, 166, 356, 25);
			getContentPane().add(txtApellido);
			txtApellido.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					keyTypedTxtApellido(e);
				};
			});
		}
		{
			lblApellido = new JLabel("Apellidos");
			lblApellido.setForeground(new Color(0, 0, 0));
			lblApellido.setFont(new Font("Dubai", Font.BOLD, 12));
			lblApellido.setBounds(24, 149, 83, 14);
			getContentPane().add(lblApellido);
		}
		{
			lblFechaNacimiento = new JLabel("Sexo");
			lblFechaNacimiento.setForeground(new Color(0, 0, 0));
			lblFechaNacimiento.setFont(new Font("Dubai", Font.BOLD, 12));
			lblFechaNacimiento.setBounds(231, 202, 99, 14);
			getContentPane().add(lblFechaNacimiento);
		}
		{
			lblTipoDeDocumento = new JLabel("Tipo de Documento");
			lblTipoDeDocumento.setForeground(new Color(0, 0, 0));
			lblTipoDeDocumento.setFont(new Font("Dubai", Font.BOLD, 12));
			lblTipoDeDocumento.setBounds(24, 44, 109, 14);
			getContentPane().add(lblTipoDeDocumento);
		}
		{
			cbDocumento = new JComboBox<String>();
			cbDocumento.addActionListener(this);
			cbDocumento.setFont(new Font("Tahoma", Font.PLAIN, 11));
			cbDocumento.setBorder(null);
			cbDocumento.setModel(new DefaultComboBoxModel(new String[] {"DNI", "Pasaporte"}));
			cbDocumento.setBackground(Color.WHITE);
			cbDocumento.setSelectedIndex(-1);
			cbDocumento.setBounds(24, 64, 180, 25);
			((JLabel)cbDocumento.getRenderer()).setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 5));
			getContentPane().add(cbDocumento);
		}
		{
			txtNumDocumento = new JTextField();
			txtNumDocumento.setEnabled(false);
			txtNumDocumento.setFont(new Font("Tahoma", Font.PLAIN, 11));
			txtNumDocumento.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			txtNumDocumento.setBounds(231, 64, 150, 25);
			getContentPane().add(txtNumDocumento);
			txtNumDocumento.setColumns(10);
			txtNumDocumento.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					keyTypedTxtNumDocumento(e);
				};
			});
		}
		{
			cbSexo = new JComboBox<String>();
			cbSexo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			cbSexo.setBorder(null);
			cbSexo.setModel(new DefaultComboBoxModel<String>(new String[] { "F", "M" }));
			cbSexo.setBackground(Color.WHITE);
			cbSexo.setBounds(230, 218, 177, 25);
			cbSexo.setSelectedIndex(-1);
			//MARGEN A LOS DATOS
			((JLabel)cbSexo.getRenderer()).setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 5));
			getContentPane().add(cbSexo);
		}
		{
			lblFechaNacimiento_1 = new JLabel("Fecha Nacimiento");
			lblFechaNacimiento_1.setForeground(new Color(0, 0, 0));
			lblFechaNacimiento_1.setFont(new Font("Dubai", Font.BOLD, 12));
			lblFechaNacimiento_1.setBounds(24, 202, 101, 15);
			getContentPane().add(lblFechaNacimiento_1);
		}
		{
			dcFechNacimiento = new JDateChooser();
			dcFechNacimiento.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 11));
			dcFechNacimiento.getCalendarButton().setIcon(new ImageIcon(RegistroAcompañantes.class.getResource("/icon/calendario.png")));
			dcFechNacimiento.getCalendarButton().setBackground(new Color(255, 255, 255));
			dcFechNacimiento.setBounds(25, 218, 179, 25);
			getContentPane().add(dcFechNacimiento);
			//VALIDAR LA FECHA
			((JTextField)dcFechNacimiento.getDateEditor()).setEditable(false);
			((JTextField)dcFechNacimiento.getDateEditor()).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));;
			dcFechNacimiento.setSelectableDateRange(new Date(new Date().getTime() + (TimeUnit.DAYS.toMillis(-365)*100)),new Date());
		}
		{
			lblCantidad = new JLabel("Acompañantes : " + compania);
			lblCantidad.setForeground(new Color(0, 0, 0));
			lblCantidad.setFont(new Font("Dubai", Font.BOLD, 12));
			lblCantidad.setBounds(471, 64, 106, 14);
			getContentPane().add(lblCantidad);
		}
		{
			btnAadir = new JButton("A\u00D1ADIR");
			btnAadir.setFocusable(false);
			btnAadir.addActionListener(this);
			btnAadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAadir.setForeground(new Color(255, 255, 255));
			btnAadir.setFont(new Font("Dubai", Font.BOLD, 11));
			btnAadir.setBackground(new Color(139, 0, 0));
			btnAadir.setBounds(447, 99, 162, 40);
			getContentPane().add(btnAadir);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.setFocusable(false);
			btnModificar.setEnabled(false);
			btnModificar.addActionListener(this);
			btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setFont(new Font("Dubai", Font.BOLD, 11));
			btnModificar.setBackground(new Color(139, 0, 0));
			btnModificar.setBounds(447, 144, 162, 40);
			getContentPane().add(btnModificar);
		}
		{
			btnElegirAsiento = new JButton("ELEGIR ASIENTO");
			btnElegirAsiento.setFocusable(false);
			btnElegirAsiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnElegirAsiento.addActionListener(this);
			btnElegirAsiento.setForeground(new Color(255, 255, 255));
			btnElegirAsiento.setFont(new Font("Dubai", Font.BOLD, 11));
			btnElegirAsiento.setBackground(new Color(139, 0, 0));
			btnElegirAsiento.setBounds(447, 189, 162, 40);
			getContentPane().add(btnElegirAsiento);
		}
		{
			btnSiguiente = new JButton("SIGUIENTE");
			btnSiguiente.addActionListener(this);
			btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSiguiente.setBackground(new Color(128, 0, 0));
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setFont(new Font("Dubai", Font.BOLD, 11));
			btnSiguiente.setBounds(341, 462, 142, 38);
			getContentPane().add(btnSiguiente);
		}
		{
			btnRetroceder = new JButton("RETROCEDER");
			btnRetroceder.addActionListener(this);
			btnRetroceder.setBackground(Color.DARK_GRAY);
			btnRetroceder.setForeground(Color.WHITE);
			btnRetroceder.setFont(new Font("Dubai", Font.BOLD, 11));
			btnRetroceder.setBounds(181, 462, 142, 38);
			getContentPane().add(btnRetroceder);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 260, 612, 191);
			getContentPane().add(scrollPane);
			{
				jtRegistro = new JTable(modelo);
				jtRegistro.setFont(new Font("Dubai", Font.PLAIN,14));
				jtRegistro.setRowHeight(28);
				jtRegistro.setGridColor(Color.gray);
				jtRegistro.setSelectionBackground(new Color(135, 206, 250));
				jtRegistro.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						keyPressedJtRegistro(e);
					}
				});
				jtRegistro.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						mouseClickedJtRegistro(arg0);
					}
				});
				jtRegistro.getTableHeader().setReorderingAllowed(false);
				
				modelo.addColumn("Pasajero");
				modelo.addColumn("Num Doc.");
				modelo.addColumn("Tipo Doc.");
				modelo.addColumn("Nombre");
				modelo.addColumn("Apellido");
				modelo.addColumn("Sexo");

				scrollPane.setViewportView(jtRegistro);
				ajustarTabla();
			}
		}
		{
			btnBorrarNombre = new JButton("");
			btnBorrarNombre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnBorrarNombre.setFocusable(false);
			btnBorrarNombre.addActionListener(this);
			btnBorrarNombre.setIcon(new ImageIcon(RegistroAcompañantes.class.getResource("/icon/x.png")));
			btnBorrarNombre.setBorder(null);
			btnBorrarNombre.setBackground(Color.WHITE);
			btnBorrarNombre.setBounds(377, 113, 30, 25);
			getContentPane().add(btnBorrarNombre);
		}
		{
			btnborrarApellido = new JButton("");
			btnborrarApellido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnborrarApellido.setFocusable(false);
			btnborrarApellido.addActionListener(this);
			btnborrarApellido.setIcon(new ImageIcon(RegistroAcompañantes.class.getResource("/icon/x.png")));
			btnborrarApellido.setBorder(null);
			btnborrarApellido.setBackground(Color.WHITE);
			btnborrarApellido.setBounds(377, 166, 30, 25);
			getContentPane().add(btnborrarApellido);
		}
		{
			btnBorrarDoc = new JButton("");
			btnBorrarDoc.setEnabled(false);
			btnBorrarDoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnBorrarDoc.setFocusable(false);
			btnBorrarDoc.addActionListener(this);
			btnBorrarDoc.setIcon(new ImageIcon(RegistroAcompañantes.class.getResource("/icon/x.png")));
			btnBorrarDoc.setBorder(null);
			btnBorrarDoc.setBackground(Color.WHITE);
			btnBorrarDoc.setBounds(377, 64, 30, 25);
			getContentPane().add(btnBorrarDoc);
		}
		{
			lblNroDeDocumento = new JLabel("Nro\u00B0 de Documento");
			lblNroDeDocumento.setForeground(Color.BLACK);
			lblNroDeDocumento.setFont(new Font("Dubai", Font.BOLD, 12));
			lblNroDeDocumento.setBounds(230, 44, 110, 14);
			getContentPane().add(lblNroDeDocumento);
		}

		bloqueaCajas(RealizarReserva.cantiPasaj, 1);

	}

	/*-------------------BOTONES-------------------*/
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cbDocumento) {
			actionPerformedCbDocumento(arg0);
		}
		if (arg0.getSource() == btnBorrarDoc) {
			actionPerformedBtnBorrarDoc(arg0);
		}
		if (arg0.getSource() == btnborrarApellido) {
			actionPerformedBtnborrarApellido(arg0);
		}
		if (arg0.getSource() == btnBorrarNombre) {
			actionPerformedBtnBorrarNombre(arg0);
		}
		if (arg0.getSource() == btnRetroceder) {
			actionPerformedBtnRetroceder(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAadir) {
			actionPerformedBtnAadir(arg0);
		}
		if (arg0.getSource() == btnSiguiente) {
			actionPerformedBtnSiguiente(arg0);
		}
		if (arg0.getSource() == btnElegirAsiento) {
			actionPerformedBtnElegirAsiento(arg0);
		}
	}

	/*--------------------------BOTONES--------------------------*/
	protected void keyTypedTxtNombre(KeyEvent arg0) {
		ReservarVuelo.limpiarVacio(txtNombre, btnBorrarNombre);
		char car = arg0.getKeyChar();
		if(Character.isLetter(car) || Character.isSpaceChar(car)){
		}else{
			arg0.consume();
			getToolkit().beep();
		}
	}
	protected void keyTypedTxtApellido(KeyEvent arg0) {
		ReservarVuelo.limpiarVacio(txtApellido, btnborrarApellido);
		char car = arg0.getKeyChar();
		if(Character.isLetter(car) || Character.isSpaceChar(car)){
		}else{
			arg0.consume();
			getToolkit().beep();
		}
	}
	protected void keyTypedTxtNumDocumento(KeyEvent arg0) {
		ReservarVuelo.limpiarVacio(txtNumDocumento, btnBorrarDoc);
		char car = arg0.getKeyChar();
		if(Character.isDigit(car) || Character.isLetter(car)){
		}else{
			arg0.consume();
			getToolkit().beep();
		}
		if(cbDocumento.getSelectedIndex()==0 || cbDocumento.getSelectedIndex()==1){
			if (txtNumDocumento.getText().length()>=8) {
				arg0.consume();
				getToolkit().beep();
			}
		}
	}
	protected void actionPerformedCbDocumento(ActionEvent arg0) {
		if(cbDocumento.getSelectedIndex()==0 || cbDocumento.getSelectedIndex()==1 ){
			txtNumDocumento.requestFocus();
			txtNumDocumento.setEnabled(true);
			btnBorrarDoc.setEnabled(true);
		}
	}	
	protected void actionPerformedBtnAadir(ActionEvent arg0) {
		// BOTON AÑADIR
		ReservarVuelo.ventanaMensaje();
		String patron1 = "[a-zA-Zñ_ ]{3,20}";
		String patron2 = "[a-zA-Zñ_ ]{2,20}";
		Acompañante persona ;
		if (compania > 0){
			if(cbDocumento.getSelectedIndex()!=-1){
				
				if(filtro(txtNumDocumento, cbDocumento) == true){
						
					if(!gp.BuscarCliente(Iniciarsesion.codCLiente).getNumDocClie().equals(txtNumDocumento.getText())){
					
						if(validarDocumento(RealizarReserva.listaAcompañantes)){
							
							if (Pattern.matches(patron1, txtNombre.getText())){
				
								if (Pattern.matches(patron2, txtApellido.getText())){
											
									if (dcFechNacimiento.getDate()!=null){
															
										if(cbSexo.getSelectedIndex()!=-1){
											
												persona = new Acompañante(
													  txtNumDocumento.getText(), 
													  cbDocumento.getSelectedItem().toString(),
													  mayusculaText(txtNombre.getText()), 
													  mayusculaText(txtApellido.getText()), 
			                                          convierteFechaSql(dcFechNacimiento.getDate()),
			                                          cbSexo.getSelectedItem().toString());
											RealizarReserva.listaAcompañantes.add(persona);
											//SE AÑADEN AL JTABLE
											Object fila[]={
													edad(persona.getFecNacAcomp()),
													persona.getNumDocAcomp(),
													persona.getTipDocAcomp(),
													persona.getNomAcomp(),
													persona.getApeAcomp(),
													persona.getSexoAcomp(),
											};
	
											modelo.addRow(fila);

											limpiaCajas();
											compania--;

											btnModificar.setEnabled(true);
											btnModificar.setBackground(new Color(139, 0, 0));

											if (compania == 0) {
												btnAadir.setEnabled(false);
												btnAadir.setBackground(Color.LIGHT_GRAY);
											}		
										}else{
											Mensaje("No ha ingresado el tipo de sexo");}			
									}else{
										Mensaje("No ha ingresado su fecha de nacimiento");}
								}else{
									ReservarVuelo.datoVacio(txtApellido, btnborrarApellido);}
							}else{							
								ReservarVuelo.datoVacio(txtNombre, btnBorrarNombre);}
						}else{
							Mensaje("Estas insertando datos ya registrados");
							txtNumDocumento.setText("");
						}
					}else{
						Mensaje("Estas insertando datos ya registrados");
						txtNumDocumento.setText("");}
				}else{
					ReservarVuelo.datoVacio(txtNumDocumento, btnBorrarDoc);
					Mensaje("Recuerde ... DNI y CARNET: 8 dígitos");}
			}else{
				Mensaje("No ingreso tipo de documento");
			}
		}
	}
	private boolean validarDocumento(ArrayList<Acompañante> obj){
		for (Acompañante a:obj) {
			if(a.getNumDocAcomp().equals(txtNumDocumento.getText())){
				return false;
			}
		}
		return true;
	}
	private boolean modificarDocumento(){
		String dato=String.valueOf(modelo.getValueAt(jtRegistro.getSelectedRow(),1));
		if(dato!=null){
			if(dato.equals(txtNumDocumento.getText())){
				return true;
			}
		}
		return false;
	}
	public static String edad(String d){
		String n="";
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaNac = LocalDate.parse(d, fmt);
		LocalDate ahora = LocalDate.now();
		Period edad=Period.between(fechaNac, ahora);
		if(edad.getYears()==0){
			n="Bebé";
		}else if (edad.getYears()>=2 && edad.getYears()<=11){
			n="Niño";
		}else{
			n="Adulto";
		}
		return  n;
	}
	@SuppressWarnings("unused")
	private String mayusculaText(String g){
		char[] t=g.toCharArray();
		String dato="";
		t[0]= Character.toUpperCase(t[0]);
		for (int i = 0; i < t.length-1; i++) {
			if(t[i]==' ' && Character.isLetter(t[i+1])){
				t[i+1] = Character.toUpperCase(t[i+1]);
			}
		}
		return new String(t);
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		// BOTON MODIFICAR
		// BOTON MODIFICAR
				ReservarVuelo.ventanaMensaje();
				String patron1 = "[a-zA-Zñ_ ]{3,20}";
				String patron2 = "[a-zA-Zñ_ ]{2,20}";
				if(cbDocumento.getSelectedIndex()!=-1){
					
					if(filtro(txtNumDocumento, cbDocumento) == true){
						
						if(!gp.BuscarCliente(Iniciarsesion.codCLiente).getNumDocClie().equals(txtNumDocumento.getText())){
							
							if(modificarDocumento()){
							
								if (Pattern.matches(patron1, txtNombre.getText())){
					
									if (Pattern.matches(patron2, txtApellido.getText())){
												
										if (dcFechNacimiento.getDate()!=null){
																
											if(cbSexo.getSelectedIndex()!=-1){
																		
												//SE MODIFICAN DATOS EN EL JTABLE
												modelo.setValueAt(edad(convierteFechaSql(dcFechNacimiento.getDate())), filaSeleccion, 0);
												modelo.setValueAt(txtNumDocumento.getText(), filaSeleccion, 1);
												modelo.setValueAt(cbDocumento.getSelectedItem(), filaSeleccion, 2);
												modelo.setValueAt(mayusculaText(txtNombre.getText()), filaSeleccion, 3);
												modelo.setValueAt(mayusculaText(txtApellido.getText()), filaSeleccion, 4);
												modelo.setValueAt(cbSexo.getSelectedItem(), filaSeleccion, 5);			
												
												Acompañante persona =  new Acompañante(
														txtNumDocumento.getText(), 
														cbDocumento.getSelectedItem().toString(),
														mayusculaText(txtNombre.getText()), 
														mayusculaText(txtApellido.getText()), 
						                                convierteFechaSql(dcFechNacimiento.getDate()),
						                                cbSexo.getSelectedItem().toString());
												
												RealizarReserva.listaAcompañantes.set(filaSeleccion, persona);
												
												limpiaCajas();
												
												JOptionPane.showMessageDialog(null, "Datos Modificados");
												
											}else{
												Mensaje("No ha ingresado el tipo de sexo");}			
										}else{
											Mensaje("No ha ingresado su fecha de nacimiento");}
									}else{
										ReservarVuelo.datoVacio(txtApellido, btnborrarApellido);}
								}else{							
									ReservarVuelo.datoVacio(txtNombre, btnBorrarNombre);}
							 }else{
								Mensaje("Estas insertando datos ya registrados");
								txtNumDocumento.setText("");
							}
						}else{
							Mensaje("Estas insertando datos ya registrados");
							txtNumDocumento.setText("");}
					}else{
						ReservarVuelo.datoVacio(txtNumDocumento, btnBorrarDoc);
						Mensaje("Recuerde ... DNI y PASAPORTE: 8 dígitos ");}
				}else{
					Mensaje("No ingreso tipo de documento");
				}
	}

	protected void actionPerformedBtnElegirAsiento(ActionEvent arg0) {
		// BOTON ESCOGER ASIENTO
		ReservarVuelo.ventanaMensaje();
		if (compania == 0) {
			ElegirAsiento ea = new ElegirAsiento();
			ea.setModal(true);
			ea.setVisible(true);
		} else {
			Mensaje("Aún debe ingresar a sus acompañantes");
		}

	}

	protected void actionPerformedBtnSiguiente(ActionEvent arg0) {
		// BOTON SIGUIENTE
		ReservarVuelo.ventanaMensaje();
		if (escogioAsiento) {
			UIManager.put("Panel.background", new Color(0,0,0,30));
			VerificarReserva fr = new VerificarReserva();
			RealizarReserva.btnPaso3.setEnabled(true);
			RealizarReserva.btnPaso2.setEnabled(false);
			RealizarReserva.panel.add(fr);
			fr.toFront();
			fr.setVisible(true);
			this.dispose();
			escogioAsiento = false;
			
		} else {
			Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
			JOptionPane.showMessageDialog(null, "Aún debe escoger sus asientos, proceda a realizarlo por favor",
					"",JOptionPane.PLAIN_MESSAGE,icono);
		}

	}
	protected void actionPerformedBtnRetroceder(ActionEvent arg0) {
		// BOTON RETROCEDER
		ReservarVuelo.ventanaMensaje();
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		int num = JOptionPane.showConfirmDialog(null,
				"Si cancela proceso se perdera los datos actuales. Desea continuar?", "", 
				JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,icono);
		if (num == 0) {
			escogioAsiento = false;
			UIManager.put("Panel.background", new Color(0,0,0,30));
			ReservarVuelo rv = new ReservarVuelo();
			RealizarReserva.btnPaso2.setEnabled(false);
			RealizarReserva.btnPaso1.setEnabled(true);
			RealizarReserva.panel.add(rv);
			rv.setVisible(true);
			this.dispose();
			
			RealizarReserva.btnCancela.setVisible(true);
			RealizarReserva.listaAcompañantes.clear();
			RealizarReserva.codigosAsientos.clear();
			RealizarReserva.codigosAsientosVuelta.clear();
			RegistroAcompañantes.validaAsiento = 0;
			RegistroAcompañantes.validaAsientoVuelta = 0;
		}

	}
	
	protected void mouseClickedJtRegistro(MouseEvent arg0) {// MUESTRA DATOS AL SER CLICKEADO
		String dato=String.valueOf(modelo.getValueAt(jtRegistro.getSelectedRow(),1));
		String fecha=null;
		for(Acompañante obj:RealizarReserva.listaAcompañantes){
			if(obj.getNumDocAcomp().equals(dato)){
				fecha=obj.getFecNacAcomp();
			}
		}
		mostrarDatos(fecha);
	}

	protected void keyPressedJtRegistro(KeyEvent e) {// MUESTRA DATOS AL SER PRESIONADO POR TECLA
		String dato=String.valueOf(modelo.getValueAt(jtRegistro.getSelectedRow(),1));
		String fecha=null;
		for(Acompañante obj:RealizarReserva.listaAcompañantes){
			if(obj.getNumDocAcomp().equals(dato)){
				fecha=obj.getFecNacAcomp();
			}
		}
		mostrarDatos(fecha);
	}
	
	/*------METODOS--------*/
	private void bloqueaCajas(int comparador, int compara) {
		// BLOQUEA CAJAS EN CASO DE NO HABER ACOMPAÑANTES O YA SE HAYA AGREGADO A TODOS
		if (comparador == compara) {
			txtNombre.setEnabled(false);
			txtApellido.setEnabled(false);
			cbSexo.setEnabled(false);
			dcFechNacimiento.setEnabled(false);
			cbDocumento.setEnabled(false);
			txtNumDocumento.setEnabled(false);

			btnAadir.setEnabled(false);
			btnAadir.setBackground(Color.LIGHT_GRAY);
			btnModificar.setEnabled(false);
			btnModificar.setBackground(Color.LIGHT_GRAY);

			jtRegistro.setBackground(Color.LIGHT_GRAY);
		}
	}

	private void limpiaCajas() {
		// LIMPIA CAJAS
		txtNombre.setText("");
		txtApellido.setText("");
		txtNumDocumento.setText("");
		dcFechNacimiento.setDate(null);
		cbDocumento.setSelectedIndex(-1);
		cbSexo.setSelectedIndex(-1);
		txtNumDocumento.setEnabled(false);
		btnBorrarDoc.setEnabled(false);
	}

	private boolean filtro(JTextField txt, JComboBox<String> caja) {
		// PATRONES QUE DEBEN SEGUIR PARA VALIDAR (CAJA DE NOMBRE, APELLIDO Y NUMERO DE DOCUMENTO)
		if (caja.getSelectedIndex() == 0 ) {
			return Pattern.matches("[0-9]{8}", txt.getText());
		} 
		if ( caja.getSelectedIndex() == 1) {
			return Pattern.matches("[A-Z]{2}[0-9]{6}", txt.getText());
		} 
		return false;
	}
	
	private String convierteFechaSql(Date fecha){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(fecha);
	}
	public static String fecha(String valor){
		SimpleDateFormat sdf;
		sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date fecha=null;
		try {
			fecha = sdf.parse(valor);
		} catch (ParseException e) {}
		sdf= new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fecha);
	}
	private void mostrarDatos(String f) {
		// MUESTRA DATOS DE FILA SELECCIONADA EN TABLA
		filaSeleccion = jtRegistro.getSelectedRow();
		((JTextField)dcFechNacimiento.getDateEditor()).setText(fecha(f));
		txtNumDocumento.setText((String) jtRegistro.getValueAt(filaSeleccion, 1));
		cbDocumento.setSelectedItem((String) jtRegistro.getValueAt(filaSeleccion, 2));
		txtNombre.setText((String) jtRegistro.getValueAt(filaSeleccion, 3));
		txtApellido.setText((String) jtRegistro.getValueAt(filaSeleccion, 4));
		cbSexo.setSelectedItem((String) jtRegistro.getValueAt(filaSeleccion, 5));
	}
	//MOSTRAR MENSAJE
	private void Mensaje(String e){
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		JOptionPane.showMessageDialog(null, e,"",JOptionPane.PLAIN_MESSAGE,icono);
	}
	protected void actionPerformedBtnBorrarNombre(ActionEvent arg0) {
		txtNombre.setText(null);
	}
	protected void actionPerformedBtnborrarApellido(ActionEvent arg0) {
		txtApellido.setText(null);
	}
	protected void actionPerformedBtnBorrarDoc(ActionEvent arg0) {
		txtNumDocumento.setText(null);
	}
	/*--------------------------------PERSONALIZAR TABLA-------------------------------*/
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
		
	private void ajustarTabla(){
		TableColumnModel tcm= jtRegistro.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(6));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(3));
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < 6; i++) {
			jtRegistro.getColumnModel().getColumn(i).setCellRenderer(tcr); 
		}
		DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);
		tcr1.setBackground(Color.DARK_GRAY);
		tcr1.setForeground(Color.white);
		for (int i = 0; i < 6; i++) {
			jtRegistro.getColumnModel().getColumn(i).setHeaderRenderer(tcr1);
		}
	}
	
}	
