package vista_administrador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.independentsoft.share.SimpleDataRow;
import com.toedter.calendar.JDateChooser;

import libreria.Diseño;
import mantenimientos.GestionBoleta;
import mantenimientos.GestionVuelo;
import model.Vuelo;
import vista_reserva.RegistroAcompañantes;
import vista_reserva.ReservarVuelo;
import model.Aeropuerto;

import javax.swing.JScrollPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class GestionarVuelos extends JFrame {

	private JPanel contentPane;
	private JButton btnRegistrar;
	private JButton btnModificar;
	private JLabel lblGestionarVuelos;
	private JTable jtVuelos;
	private JLabel lblOrigen;
	private JComboBox txtOrigen;
	private JLabel lblDestino;
	private JComboBox txtDestino;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JLabel lblTipo;
	private JRadioButton rdbtnIda;
	private JRadioButton rdbtnVuelta;
	private JLabel lblTipos;
	private JButton btnSalir;
	private JLabel lblFechaDeSalida;
	private JLabel lblHoraDeSalida;
	private JLabel lblHoraDeLlegada;
	private JTextField txtCantidad;
	private JDateChooser dcFechaSalida;
	private JTextField txtHoraSalida;
	private JTextField txtHoraLlegada;
	private JScrollPane scrollPane;
	private JLabel lblFondo;
	
	
	/*---------------------------------------*/
	Diseño dise=new Diseño();
	DefaultTableModel model=new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row,int column){
				return false;
		}		
	};
	GestionVuelo gv=new GestionVuelo();
	List<Vuelo> data;
	Vuelo v;
	public String vueloIda=null;
	public String vueloRetorno=null;
	private JLabel lblFechaDeRetorno;
	private JDateChooser dcFechaRetorno;
	/*--------------------------------------**/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarVuelos frame = new GestionarVuelos();
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
	public GestionarVuelos() {
		setTitle("OpenFly\u00AE - VUELO\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionarVuelos.class.getResource("/icon/logoOP.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 10, 954, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);//CENTRAR GUI
		/*-----------DISEÑO -----------*/
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", new Color(70, 130, 180));
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
		{
			btnRegistrar = new JButton("REGISTRAR");
			btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnRegistrar_actionPerformed(e);
				}
			});
			{
				dcFechaRetorno = new JDateChooser();
				dcFechaRetorno.setBackground(Color.WHITE);
				dcFechaRetorno.getCalendarButton().setIcon(new ImageIcon(GestionarVuelos.class.getResource("/icon/calendario.png")));
				dcFechaRetorno.getCalendarButton().setBackground(Color.WHITE);
				dcFechaRetorno.setBounds(454, 99, 175, 25);
				((JTextField)dcFechaRetorno.getDateEditor()).setEditable(false);
				((JTextField)dcFechaRetorno.getDateEditor()).setBackground(Color.WHITE);
				{
					txtIdaVuelta = new JTextField();
					txtIdaVuelta.setBackground(Color.WHITE);
					txtIdaVuelta.setBorder(new EmptyBorder(0, 5, 0, 5));
					txtIdaVuelta.setText("Ida - Vuelta");
					txtIdaVuelta.setEditable(false);
					txtIdaVuelta.setBounds(772, 63, 86, 25);
					contentPane.add(txtIdaVuelta);
					txtIdaVuelta.setColumns(10);
				}
				{
					label = new JLabel("Tipo");
					label.setFont(new Font("Dubai", Font.BOLD, 12));
					label.setBounds(671, 63, 26, 21);
					contentPane.add(label);
				}
				contentPane.add(dcFechaRetorno);
			}
			{
				lblFechaDeRetorno = new JLabel("Fecha de Retorno");
				lblFechaDeRetorno.setFont(new Font("Dubai", Font.BOLD, 12));
				lblFechaDeRetorno.setBounds(342, 106, 91, 21);
				contentPane.add(lblFechaDeRetorno);
			}
			btnRegistrar.setBackground(new Color(70, 130, 180));
			btnRegistrar.setForeground(Color.WHITE);
			btnRegistrar.setFont(new Font("Dubai", Font.BOLD, 11));
			btnRegistrar.setBounds(291, 595, 104, 42);
			contentPane.add(btnRegistrar);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.setEnabled(false);
			btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_btnModificar_actionPerformed(arg0);
				}
			});
			btnModificar.setBackground(new Color(70, 130, 180));
			btnModificar.setForeground(Color.WHITE);
			btnModificar.setFont(new Font("Dubai", Font.BOLD, 11));
			btnModificar.setBounds(401, 595, 104, 42);
			contentPane.add(btnModificar);
		}
		{
			lblGestionarVuelos = new JLabel("GESTIONAR VUELOS");
			lblGestionarVuelos.setFont(new Font("Dubai", Font.BOLD, 17));
			lblGestionarVuelos.setBounds(392, 17, 157, 30);
			contentPane.add(lblGestionarVuelos);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					do_scrollPane_mouseClicked(arg0);
				}
			});
			scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			scrollPane.setFont(new Font("Dubai", Font.PLAIN, 12));
			scrollPane.setViewportBorder(null);
			scrollPane.setBackground(Color.DARK_GRAY);
			scrollPane.setBorder(null);
			scrollPane.setBounds(40, 182, 845, 395);
			contentPane.add(scrollPane);
			{
				jtVuelos = new JTable();
				jtVuelos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						do_jtVuelos_mouseClicked(arg0);
					}
				});
				scrollPane.setViewportView(jtVuelos);
				jtVuelos.setBorder(null);
				/*----------DISEÑO DE BORDER DE LA TABLA--------*/
				jtVuelos.setShowHorizontalLines(true);
				jtVuelos.setShowVerticalLines(true);
				jtVuelos.setGridColor(Color.DARK_GRAY);
				//TAMAÑO DE FILA
				jtVuelos.setRowHeight(28);
				jtVuelos.setForeground(Color.BLACK);
				
				jtVuelos.setModel(model);
				model.addColumn("Cod. Vuelo");
				model.addColumn("Tipo");
				model.addColumn("Origen");
				model.addColumn("Destino");
				model.addColumn("Fecha Salida");
				model.addColumn("Horario");//HORA DE SALIDA Y LLEGADA JUNTOS
				model.addColumn("N°");
				model.addColumn("Precio");
				
				jtVuelos.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 30));
			}
		}
		{
			lblOrigen = new JLabel("Origen\r\n");
			lblOrigen.setFont(new Font("Dubai", Font.BOLD, 12));
			lblOrigen.setBounds(40, 59, 46, 21);
			contentPane.add(lblOrigen);
		}
		{
			txtOrigen = new JComboBox();
			txtOrigen.setBackground(Color.WHITE);
			txtOrigen.setModel(new DefaultComboBoxModel(new String[] {"Arequipa", "Ayacucho", "Cajamarca", "Cuzco", "Iquitos", 
															"Lima", "Piura", "Puerto Maldonado", "Tarapoto", "Trujillo", "Tumbes"}));
			txtOrigen.setBorder(null);
			txtOrigen.setBounds(142, 57, 175, 25);
			contentPane.add(txtOrigen);
		}
		{
			lblDestino = new JLabel("Destino");
			lblDestino.setFont(new Font("Dubai", Font.BOLD, 12));
			lblDestino.setBounds(342, 63, 48, 21);
			contentPane.add(lblDestino);
		}
		{
			txtDestino = new JComboBox();
			txtDestino.setBackground(Color.WHITE);
			txtDestino.setModel(new DefaultComboBoxModel(new String[] {"Arequipa", "Ayacucho", "Cajamarca", "Cuzco", "Iquitos",
											"Lima", "Piura", "Puerto Maldonado", "Tarapoto", "Trujillo", "Tumbes"}));
			txtDestino.setBorder(null);
			txtDestino.setBounds(453, 59, 189, 25);
			contentPane.add(txtDestino);
		}
		{
			lblPrecio = new JLabel("Precio");
			lblPrecio.setFont(new Font("Dubai", Font.BOLD, 12));
			lblPrecio.setBounds(671, 144, 45, 21);
			contentPane.add(lblPrecio);
		}
		{
			txtPrecio = new JTextField();
			txtPrecio.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					do_txtPrecio_keyTyped(e);
				}
			});
			txtPrecio.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtPrecio.setBounds(772, 141, 86, 25);
			contentPane.add(txtPrecio);
			txtPrecio.setColumns(10);
		}
		{
			lblTipo = new JLabel("Tipo");
			lblTipo.setFont(new Font("Dubai", Font.BOLD, 12));
			lblTipo.setBounds(696, 614, 26, 21);
			contentPane.add(lblTipo);
		}
		{
			rdbtnIda = new JRadioButton("Ida ");
			rdbtnIda.setForeground(Color.BLACK);
			rdbtnIda.setEnabled(false);
			rdbtnIda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rdbtnIda.setOpaque(false);
			rdbtnIda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_rdbtnIda_actionPerformed(arg0);
				}
			});
			rdbtnIda.setBackground(Color.LIGHT_GRAY);
			rdbtnIda.setBounds(745, 610, 56, 23);
			contentPane.add(rdbtnIda);
		}
		{
			rdbtnVuelta = new JRadioButton("Vuelta");
			rdbtnVuelta.setForeground(Color.BLACK);
			rdbtnVuelta.setEnabled(false);
			rdbtnVuelta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rdbtnVuelta.setOpaque(false);
			rdbtnVuelta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_rdbtnVuelta_actionPerformed(e);
				}
			});
			rdbtnVuelta.setBackground(Color.LIGHT_GRAY);
			rdbtnVuelta.setBounds(803, 610, 80, 23);
			contentPane.add(rdbtnVuelta);
		}
		{
			lblTipos = new JLabel("N\u00B0 de asientos");
			lblTipos.setFont(new Font("Dubai", Font.BOLD, 12));
			lblTipos.setBounds(666, 99, 76, 21);
			contentPane.add(lblTipos);
		}
		{
			btnSalir = new JButton("SALIR");
			btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnSalir_actionPerformed(e);
				}
			});
			btnSalir.setBackground(new Color(70, 130, 180));
			btnSalir.setForeground(Color.WHITE);
			btnSalir.setFont(new Font("Dubai", Font.BOLD, 11));
			btnSalir.setBounds(509, 595, 104, 42);
			contentPane.add(btnSalir);
		}
		{
			lblFechaDeSalida = new JLabel("Fecha de Salida");
			lblFechaDeSalida.setFont(new Font("Dubai", Font.BOLD, 12));
			lblFechaDeSalida.setBounds(41, 103, 80, 21);
			contentPane.add(lblFechaDeSalida);
		}
		{
			lblHoraDeSalida = new JLabel("Hora de Salida");
			lblHoraDeSalida.setFont(new Font("Dubai", Font.BOLD, 12));
			lblHoraDeSalida.setBounds(38, 144, 78, 21);
			contentPane.add(lblHoraDeSalida);
		}
		{
			lblHoraDeLlegada = new JLabel("Hora de llegada");
			lblHoraDeLlegada.setFont(new Font("Dubai", Font.BOLD, 12));
			lblHoraDeLlegada.setBounds(342, 144, 83, 21);
			contentPane.add(lblHoraDeLlegada);
		}
		{
			txtCantidad = new JTextField();
			txtCantidad.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtCantidad.setEnabled(false);
			txtCantidad.setDisabledTextColor(Color.BLACK);
			txtCantidad.setText("42");
			txtCantidad.setColumns(10);
			txtCantidad.setBounds(772, 99, 86, 25);
			contentPane.add(txtCantidad);
		}
		{
			dcFechaSalida = new JDateChooser();
			dcFechaSalida.setBackground(Color.WHITE);
			dcFechaSalida.getCalendarButton().setIcon(new ImageIcon(GestionarVuelos.class.getResource("/icon/calendario.png")));
			dcFechaSalida.getCalendarButton().setBackground(Color.WHITE);
			dcFechaSalida.setBounds(142, 103, 175, 25);
			dcFechaSalida.setSelectableDateRange(new Date(),new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(60)));
			((JTextField)dcFechaSalida.getDateEditor()).setEditable(false);
			((JTextField)dcFechaSalida.getDateEditor()).setBackground(Color.WHITE);
			contentPane.add(dcFechaSalida);
		}
		{
			txtHoraSalida = new JTextField();
			txtHoraSalida.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					do_txtHoraSalida_keyTyped(e);
				}
			});
			txtHoraSalida.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtHoraSalida.setColumns(10);
			txtHoraSalida.setBounds(135, 137, 102, 25);
			contentPane.add(txtHoraSalida);
		}
		{
			txtHoraLlegada = new JTextField();
			txtHoraLlegada.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					do_txtHoraLlegada_keyTyped(e);
				}
			});
			txtHoraLlegada.setBorder(new EmptyBorder(0, 5, 0, 5));
			txtHoraLlegada.setColumns(10);
			txtHoraLlegada.setBounds(454, 143, 105, 25);
			contentPane.add(txtHoraLlegada);
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 948, 671);
			contentPane.add(lblFondo);
			dise.img(GestionarPromociones.class.getResource("/img/fondo1.jpg"), lblFondo);
		}
		/*-------------------*/
		ajustarTabla();
		Tabla(gv.listaVuelo("Ida"), gv.listaVuelo("Vuelta"));
	}
	/*------------------------------------------EVENTOS---------------------------------------------*/
	protected void do_txtOrigen_keyTyped(KeyEvent arg0) {
	}
	protected void do_txtDestino_keyTyped(KeyEvent e) {
	}
	protected void do_txtHoraSalida_keyTyped(KeyEvent e) {
		char x=e.getKeyChar();
		if(Character.isDigit(x)){	
			if(txtHoraSalida.getText().length() == 2){
				txtHoraSalida.setText( txtHoraSalida.getText() + ":");
			}
			if(txtHoraSalida.getText().length() == 5){
				txtHoraSalida.setText( txtHoraSalida.getText() + ":");
			}
		}else{
			e.consume();
			getToolkit().beep();
		}
		if(txtHoraSalida.getText().length()>7){
			e.consume();
		}
	}
	protected void do_txtPrecio_keyTyped(KeyEvent e) {
		char x=e.getKeyChar();
		if(Character.isDigit(x)){			
		}else{
			e.consume();
			getToolkit().beep();
		}
		if(txtPrecio.getText().length()>2){
			e.consume();
		}
	}
	protected void do_txtHoraLlegada_keyTyped(KeyEvent e) {
		char x=e.getKeyChar();
		if(Character.isDigit(x)){		
			if(txtHoraLlegada.getText().length() == 2){
				txtHoraLlegada.setText( txtHoraLlegada.getText() + ":");
			}
			if(txtHoraLlegada.getText().length() == 5){
				txtHoraLlegada.setText( txtHoraLlegada.getText() + ":");
			}
		}else{
			e.consume();
			getToolkit().beep();
		}
		if(txtHoraLlegada.getText().length()>7){
			e.consume();
		}
		
	}
	/*--------------------------------------------BOTONES------------------------------------------*/
	protected void do_btnModificar_actionPerformed(ActionEvent arg0) {
		//BOTON MODIFICAR
		if(Integer.parseInt(txtCantidad.getText())>=42){
			if(rdbtnIda.isSelected()){
				if(dcFechaSalida.getDate()!=null){
					gv.ModificarVuelo(obtenerVuelo1(), fechaSQL(dcFechaSalida.getDate()),
								 txtHoraSalida.getText(),txtHoraLlegada.getText(), Double.parseDouble(txtPrecio.getText()));
					limpiar();
					model.setRowCount(0);
					ajustarTabla();
					Tabla(gv.listaVuelo("Ida"), gv.listaVuelo("Vuelta"));
					btnRegistrar.setEnabled(true);
					rdbtnIda.setEnabled(false);
					rdbtnVuelta.setEnabled(false);
					txtOrigen.setEnabled(true);
					txtDestino.setEnabled(true);
					btnModificar.setEnabled(false);
					}else{
						Mensaje("No ingreso fecha de salida");
					}
				}else{
					if(dcFechaRetorno.getDate()!=null){
						gv.ModificarVuelo(obtenerVuelo2(), fechaSQL(dcFechaRetorno.getDate()),
								txtHoraSalida.getText(),txtHoraLlegada.getText(), Double.parseDouble(txtPrecio.getText()));
						limpiar();
						model.setRowCount(0);
						ajustarTabla();
						Tabla(gv.listaVuelo("Ida"), gv.listaVuelo("Vuelta"));
						btnRegistrar.setEnabled(true);
						rdbtnIda.setEnabled(false);
						rdbtnVuelta.setEnabled(false);
						txtOrigen.setEnabled(true);
						txtDestino.setEnabled(true);
						btnModificar.setEnabled(false);
					}else{
						Mensaje("No ingreso fecha de retorno");
					}	
				}
			}else{
				Mensaje("No se pude modificar registro de vuelo");
			}
	}
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		//BOTON REGISTRAR
				String codigo = generarCodigo();
				String codOri = CodigoOrigenIda();
				String codDes = CodigoDestinoIda();
				int cant = Integer.parseInt(txtCantidad.getText());
				
				if(dcFechaSalida.getDate() != null){
					String fech = fechaSQL(dcFechaSalida.getDate());
						
					if(dcFechaRetorno.getDate() != null){
						String fechR = fechaSQL(dcFechaRetorno.getDate());
						
					  if(ValidarFechas()){  
					  
						if(txtHoraSalida.getText() != null){
							String patron = "(?:0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
							String horaS = txtHoraSalida.getText();
							
							if(Pattern.matches(patron, horaS)){
							
								if(txtHoraLlegada.getText() != null){
									String horaL = txtHoraLlegada.getText();
										
									if(Pattern.matches(patron, horaL)){
										
										if(txtPrecio.getText().length() != 0){
											
												double precio = Double.parseDouble(txtPrecio.getText());
												if(precio > 0 && precio <999){
												//VUELO IDA
												gv.InsertarVuelo(codigo, codOri, codDes, "Ida", cant, fech, horaS, horaL, precio);
												
												//VUELO VUELTA --- SI ESTAN SELCCIONADOS LOS DOS RADIO BUTTON
												gv.InsertarVuelo(generarCodigo(), CodigoOrigenVue(codDes), CodigoDestinoVue(codOri),"Vuelta", 
														cant,fechR , horaS, horaL, precio);
											
												model.setRowCount(0);
												ajustarTabla();
												Tabla(gv.listaVuelo("Ida"), gv.listaVuelo("Vuelta"));
												JOptionPane.showMessageDialog(null, "Registro con éxito");
												limpiar();
												}else
													Mensaje("Precio no válido");
										}else
											Mensaje("No ingresó precio");
									}else
										Mensaje("Hora de retorno no válida");
								}else
									Mensaje("No ingresó hora de retorno");
							}else
								Mensaje("Hora de salida no válida");
						}else
							Mensaje("No ingresó hora de salida");
					  }else
							Mensaje("La fecha de salida debe ser menor que la fecha de retorno");
					}else
						Mensaje("No ingresó fecha de retorno");
				}else
					Mensaje("No ingresó fecha de salida");
				
	}
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		//BOTON SALIR
		Administrador ad=new Administrador();
		ad.toFront();
		ad.setVisible(true);
		this.dispose();
	}
	boolean num=false;
	private JLabel label;
	private JTextField txtIdaVuelta;
	protected void do_rdbtnIda_actionPerformed(ActionEvent arg0) {
		//CHECK IDA
		if(num){
			limpiar();
			MostrarDatos(obtenerVuelo1());
			rdbtnVuelta.setSelected(false);
		}else{
			rdbtnIda.setSelected(false);
			rdbtnVuelta.setSelected(true);
		}
	}
	protected void do_rdbtnVuelta_actionPerformed(ActionEvent e) {
		//CHECK VUELTA
		if(num){
			limpiar();
			MostrarDatos(obtenerVuelo2());
			rdbtnIda.setSelected(false);
			
		}else{
			rdbtnIda.setSelected(true);
			rdbtnVuelta.setSelected(false);
			
		}
	}
	protected void do_scrollPane_mouseClicked(MouseEvent arg0) {
		//MostrarDatos();//NO ES
	}
	protected void do_jtVuelos_mouseClicked(MouseEvent arg0) {
		limpiar();
		verVuelos();
		rdbtnIda.setEnabled(true);
		rdbtnVuelta.setEnabled(true);
		btnModificar.setEnabled(true);
	}
	/*------------------------------------METODOS----------------------------------------------*/
	private String fechaSQL(Date f){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(f);
	}
	private void Tabla(List<Vuelo> ida, List<Vuelo> retorno){
		
		/*----------------------DATOS DEL VUELO IDA--------------------*/
		if(retorno!=null){
		data=retorno;
		List<Vuelo> data2=ida;
		Vuelo v2=new Vuelo();
		if(data.size()>0 && data2.size()>0){
			for (int i = 0; i < data.size() && i < data2.size(); i++) {
				Object fila[]=new Object[8];
				v=data.get(i);
				v2=data2.get(i);
				
				fila[0]=v2.getCodVue()+" - "+v.getCodVue();
				fila[1]=v2.getTipVuelo()+"-"+v.getTipVuelo();
				fila[2]=v2.getDescCiuorig();
				fila[3]=v2.getDescCiuDest();
				fila[4]=RegistroAcompañantes.fecha(v2.getFechSalVue())+" - "+RegistroAcompañantes.fecha(v.getFechSalVue());
				fila[5]=ReservarVuelo.tiempoVuelo(v2.getFechSalVue()+" "+v2.getHoraSalVue(),
											v2.getFechSalVue()+" "+ v2.getHoraLlegVue())+" - "+
						ReservarVuelo.tiempoVuelo(v.getFechSalVue()+" "+v.getHoraSalVue(),
													v.getFechSalVue()+" "+ v.getHoraLlegVue());
				fila[6]=v2.getCantiAsieVue()+" - "+v.getCantiAsieVue();
				fila[7]=v2.getPrecioVuel()+" - "+v.getPrecioVuel();
				model.addRow(fila);
			}
		}
		}
		
		/*------------ADJUNTAR DATOS A LA TABLA----------------*/
		jtVuelos.setModel(model);	
		ajustarTabla();
	}	
	private void verVuelos(){
		String dato=String.valueOf(model.getValueAt(jtVuelos.getSelectedRow(),0));
		String tipo=String.valueOf(model.getValueAt(jtVuelos.getSelectedRow(),1));
		if(tipo.equals("Ida-Vuelta")){
			num=true;
		}
	}
	private void MostrarDatos(String dato){
		Vuelo obj=gv.BuscarVuelo(dato);
		if(btnModificar.isEnabled()){
			btnRegistrar.setEnabled(false);
		}else{
			btnRegistrar.setEnabled(true);
		}
			txtOrigen.setSelectedItem(obj.getDescCiuorig());
			txtDestino.setSelectedItem(obj.getDescCiuDest());
			txtPrecio.setText(""+obj.getPrecioVuel());
			txtHoraSalida.setText(obj.getHoraSalVue());
			txtHoraLlegada.setText(obj.getHoraLlegVue());
			txtCantidad.setText(obj.getCantiAsieVue()+"");
			if(rdbtnVuelta.isSelected()){
				((JTextField)dcFechaRetorno.getDateEditor()).setText(RegistroAcompañantes.fecha(obj.getFechSalVue()));
			}else{
				((JTextField)dcFechaSalida.getDateEditor()).setText(RegistroAcompañantes.fecha(obj.getFechSalVue()));
			}
			//NO EDITABLES
			txtOrigen.setEnabled(false);
			txtDestino.setEnabled(false);
	}
	private String obtenerVuelo1(){
		String texto=null;
		String dato=String.valueOf(model.getValueAt(jtVuelos.getSelectedRow(),0));
		String t[]=dato.split(" - ");
		texto=t[0];
		return texto;
	}
	private String obtenerVuelo2(){
		String texto=null;
		String dato=String.valueOf(model.getValueAt(jtVuelos.getSelectedRow(),0));
		String t[]=dato.split(" - ");
		texto=t[1];
		return texto;
	}
	/*--------------------LISTAR CODIGO ORIGEN---------------------------*/
	private String CodigoOrigenIda(){
		List<Aeropuerto>data=gv.ListarOrigen();
		Aeropuerto A=new Aeropuerto();
		String codigo=null;
		for(int i=0;i<data.size();i++){
			A=data.get(i);
			if(A.getDescCiu().equals((txtOrigen.getSelectedItem()))){
				codigo=A.getCodigo();
				break;
			}
		}
		return codigo;
	}
	private String CodigoDestinoIda(){
		List<Aeropuerto>data=gv.ListarDestino();
		Aeropuerto A=new Aeropuerto();
		String codigo=null;
		for(int i=0;i<data.size();i++){
			A=data.get(i);
			if(A.getDescCiu().equals((txtDestino.getSelectedItem()))){
				codigo=A.getCodigo();
			}
		}return codigo;
	}
	/*----------------------LISTAR CODIGO DESTINO------------------------*/
	private String CodigoDestinoVue(String g){
		String part=g.substring(1, 3);
		return "D"+part;
	}
	private String CodigoOrigenVue(String g){
		String part=g.substring(1, 3);
		return "O"+part;
	}
	/*------------------BUSCAR ULTIMO CODIGO VUELO---------------------------*/
	private String generarCodigo(){
		String codigo = "";
		GestionVuelo gv = new GestionVuelo();
		
		codigo = gv.buscaUltimoVuelo();
		
		String part = codigo.substring(2,6);
		int num = Integer.parseInt(part);
		
		num++;
		
		codigo = "VA" + num;
		return codigo;
	}
	
	private void limpiar(){
		txtOrigen.setSelectedIndex(-1);
		txtDestino.setSelectedIndex(-1);
		((JTextField)dcFechaSalida.getDateEditor()).setText(null);
		((JTextField)dcFechaRetorno.getDateEditor()).setText(null);
		txtHoraSalida.setText(null);
		txtHoraLlegada.setText(null);
		txtPrecio.setText(null);
	}
	private boolean ValidarFechas(){		
		if(dcFechaSalida.getDate().before(dcFechaRetorno.getDate())){
			return true;
		}else{
		return false;}
	}	
	/*--------------------------------PERSONALIZAR TABLA-------------------------------*/
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
		
	private void ajustarTabla(){
		TableColumnModel tcm= jtVuelos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(5));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(12));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(12));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(14));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(12));
		tcm.getColumn(6).setPreferredWidth(anchoColumna(3));
		tcm.getColumn(7).setPreferredWidth(anchoColumna(8));
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i <8; i++) {
			jtVuelos.getColumnModel().getColumn(i).setCellRenderer(tcr); 
		}
		
		DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);
		tcr1.setBackground(Color.DARK_GRAY);
		tcr1.setForeground(Color.white);
		for (int i = 0; i < 8; i++) {
			jtVuelos.getColumnModel().getColumn(i).setHeaderRenderer(tcr1);
		}
	}
	/*------------------------------VENTANA DE MENSAJE---------------------------*/
	private void Mensaje(String e){
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		JOptionPane.showMessageDialog(null, e,"",JOptionPane.PLAIN_MESSAGE,icono);
	}
}
