package vista_administrador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import libreria.Diseño;
import mantenimientos.GestionBoleta;
import model.Promocion;
import vista_reserva.RealizarReserva;
import vista_reserva.RegistroAcompañantes;
import vista_reserva.ReservarVuelo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.independentsoft.share.Format;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class GestionarPromociones extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblGestionarPromocion;
	private JLabel lblDescripcion;
	private JLabel lblDescuento;
	private JTable jtPromocion;
	private JTextField txtDescuento;
	private JButton btnAñadir;
	private JButton btnModificar;
	private JTextArea txtDescripcion;
	private JButton btnCerrar;
	private JButton btnEliminar;
	private JLabel lblFondo;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JDateChooser dcFechaInicio;
	private JDateChooser dcFechaFin;
	/*-------------------------------------*/
	Diseño dise=new Diseño();
	private JScrollPane scrollPane;
	GestionBoleta gb=new GestionBoleta();
	Promocion objPromo=new Promocion();
	ArrayList<Promocion> data;
	
	DefaultTableModel model=new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row,int column){
				return false;
		}	
		
	};
	DecimalFormat df=new DecimalFormat("##%");
	DecimalFormat dfIni=new DecimalFormat("##");
	/*------------------------------------*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionarPromociones frame = new GestionarPromociones();
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
	public GestionarPromociones() {
		setTitle(" OpenFly\u00AE - PROMOCIONES\r\n");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionarPromociones.class.getResource("/icon/logoOP.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 654, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);//CENTRAR GUI
		contentPane.setLayout(null);
		/*-----------DISEÑO DEL JDATECHOOSER-----------*/
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", new Color(175, 238, 238));
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
		{
			lblFechaFin = new JLabel("Fecha finalizada");
			lblFechaFin.setFont(new Font("Dubai", Font.BOLD, 12));
			lblFechaFin.setBounds(248, 168, 90, 14);
			contentPane.add(lblFechaFin);
		}
		{
			lblFechaInicio = new JLabel("Fecha inicio");
			lblFechaInicio.setFont(new Font("Dubai", Font.BOLD, 12));
			lblFechaInicio.setBounds(35, 168, 90, 14);
			contentPane.add(lblFechaInicio);
		}
		{
			lblGestionarPromocion = new JLabel("GESTIONAR PROMOCI\u00D3N");
			lblGestionarPromocion.setFont(new Font("Dubai", Font.BOLD, 16));
			lblGestionarPromocion.setBounds(233, 17, 188, 20);
			contentPane.add(lblGestionarPromocion);
		}
		{
			lblDescripcion = new JLabel("Descripci\u00F3n");
			lblDescripcion.setFont(new Font("Dubai", Font.BOLD, 12));
			lblDescripcion.setBounds(35, 54, 90, 14);
			contentPane.add(lblDescripcion);
		}
		{
			lblDescuento = new JLabel("Descuento\r\n");
			lblDescuento.setFont(new Font("Dubai", Font.BOLD, 12));
			lblDescuento.setBounds(35, 133, 90, 14);
			contentPane.add(lblDescuento);
		}
		{
			{
				scrollPane = new JScrollPane();
				scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				scrollPane.setBounds(35, 211, 581, 200);
				contentPane.add(scrollPane);
				jtPromocion = new JTable();
				scrollPane.setViewportView(jtPromocion);
				jtPromocion.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						do_jtPromocion_mouseClicked(arg0);
					}
				});
				jtPromocion.setRowHeight(28);
				/*----------DISEÑO DE BORDER DE LA TABLA--------*/
				jtPromocion.setShowHorizontalLines(true);
				jtPromocion.setShowVerticalLines(true);
				jtPromocion.setGridColor(Color.gray);
				//AÑADIR COLUMNA A LA TABLA
				jtPromocion.setModel(model);
				model.addColumn("Código");
				model.addColumn("Descripción");
				model.addColumn("Desc.");
				model.addColumn("Fecha Inicio");
				model.addColumn("Fecha fin");
				jtPromocion.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 29));
			}
		}
		{
			txtDescuento = new JTextField();
			txtDescuento.setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
			txtDescuento.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					do_txtDescuento_keyTyped(e);
				}
			});
			txtDescuento.setBounds(99, 126, 86, 25);
			contentPane.add(txtDescuento);
			txtDescuento.setColumns(10);
		}
		{
			btnAñadir = new JButton("A\u00D1ADIR\r\n");
			btnAñadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAñadir.addActionListener(this);
			btnAñadir.setBackground(new Color(178, 34, 34));
			btnAñadir.setForeground(Color.WHITE);
			btnAñadir.setFont(new Font("Dubai", Font.BOLD, 11));
			btnAñadir.setBounds(474, 66, 131, 34);
			contentPane.add(btnAñadir);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.setEnabled(false);
			btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnModificar.addActionListener(this);
			btnModificar.setBackground(new Color(178, 34, 34));
			btnModificar.setForeground(Color.WHITE);
			btnModificar.setFont(new Font("Dubai", Font.BOLD, 11));
			btnModificar.setBounds(474, 107, 131, 34);
			contentPane.add(btnModificar);
		}
		{
			txtDescripcion = new JTextArea();
			txtDescripcion.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));
			txtDescripcion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					do_txtDescripcion_keyTyped(e);
				}
			});
			txtDescripcion.setBounds(31, 74, 420, 45);
			contentPane.add(txtDescripcion);
		}
		{
			btnCerrar = new JButton("CERRAR");
			btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCerrar.addActionListener(this);
			btnCerrar.setBackground(new Color(70, 130, 180));
			btnCerrar.setForeground(Color.WHITE);
			btnCerrar.setFont(new Font("Dubai", Font.BOLD, 11));
			btnCerrar.setBounds(264, 417, 112, 34);
			contentPane.add(btnCerrar);
		}
		{
			btnEliminar = new JButton("ELIMINAR");
			btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnEliminar.addActionListener(this);
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setFont(new Font("Dubai", Font.BOLD, 11));
			btnEliminar.setBackground(new Color(178, 34, 34));
			btnEliminar.setBounds(474, 148, 131, 34);
			contentPane.add(btnEliminar);
		}
		{
			dcFechaInicio = new JDateChooser();
			dcFechaInicio.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_dcFechaInicioCalendarButton_actionPerformed(arg0);
				}
			});
			dcFechaInicio.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			dcFechaInicio.getCalendarButton().setIcon(new ImageIcon(GestionarPromociones.class.getResource("/icon/calendario.png")));
			dcFechaInicio.getCalendarButton().setBackground(Color.WHITE);
			dcFechaInicio.setBounds(106, 164, 112, 25);
			((JTextField)dcFechaInicio.getDateEditor()).setEditable(false);
			((JTextField)dcFechaInicio.getDateEditor()).setBackground(Color.WHITE);
			dcFechaInicio.setSelectableDateRange(new Date(),new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(90)));
			contentPane.add(dcFechaInicio);
		}
		{
			dcFechaFin = new JDateChooser();
			dcFechaFin.getCalendarButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_dcFechaFinCalendarButton_actionPerformed(e);
				}
			});
			dcFechaFin.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			dcFechaFin.getCalendarButton().setIcon(new ImageIcon(GestionarPromociones.class.getResource("/icon/calendario.png")));
			dcFechaFin.getCalendarButton().setBackground(Color.WHITE);
			dcFechaFin.setBounds(339, 164, 112, 25);
			((JTextField)dcFechaFin.getDateEditor()).setEditable(false);
			((JTextField)dcFechaFin.getDateEditor()).setBackground(Color.WHITE);
			dcFechaFin.setSelectableDateRange(new Date(),new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(90)));
			contentPane.add(dcFechaFin);
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 648, 468);
			contentPane.add(lblFondo);
			dise.img(GestionarPromociones.class.getResource("/img/fondo1.jpg"), lblFondo);
		}
		/*---------------INICIO---------------*/
		txtDescripcion.requestFocus();
		mostrarDatos();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAñadir) {
			actionPerformedBtnAñadir(arg0);
		}
	}
	/*------------------------------------BOTONES-----------------------------------*/
	protected void actionPerformedBtnAñadir(ActionEvent arg0) {
		//BOTON AÑADIR
		ReservarVuelo.ventanaMensaje();
		try {
			if(!(txtDescripcion.getText().isEmpty() && txtDescuento.getText().isEmpty())
					&& dcFechaInicio.getDate()!=null && dcFechaFin.getDate()!=null){
				if(Double.parseDouble(txtDescuento.getText())>=10 && Double.parseDouble(txtDescuento.getText())<=70){
					if(ValidarFechas()){
						gb.InsertarPromociones(codigo(), txtDescripcion.getText(), Double.parseDouble(txtDescuento.getText())/100, 
								fechaSQL(dcFechaInicio.getDate()),fechaSQL(dcFechaFin.getDate()));
						model.setRowCount(0);
						limpiar();
						mostrarDatos();
					}else{
						Mensaje("La fecha fin debe ser mayor a la fecha inicio");
					}
				}else{
					Mensaje("El rango de descuento es de 10% a 70%");
				}
			}else{
				if(txtDescuento.getText().isEmpty()){
					txtDescuento.setBackground(new Color(202, 207, 210));
					txtDescuento.setBorder(BorderFactory.createLineBorder(Color.black));
					txtDescuento.requestFocus();
				}
				if(txtDescripcion.getText().isEmpty()){
					txtDescripcion.setBackground(new Color(202, 207, 210));
					txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.black));
					txtDescripcion.requestFocus();
				}
				if(dcFechaInicio.getDate()==null){
					((JTextField)dcFechaInicio.getDateEditor()).setBackground(new Color(202, 207, 210));
					((JTextField)dcFechaInicio.getDateEditor()).setBorder(BorderFactory.createLineBorder(Color.black));
				}
				if(dcFechaFin.getDate()==null){
					((JTextField)dcFechaFin.getDateEditor()).setBackground(new Color(202, 207, 210));
					((JTextField)dcFechaFin.getDateEditor()).setBorder(BorderFactory.createLineBorder(Color.black));
				}
			}
		} catch (Exception e) {
			Mensaje("No ha insertado ningún registro");
		}
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		//BOTON MODIFICAR
		ReservarVuelo.ventanaMensaje();
		try {
			if(!(txtDescripcion.getText().isEmpty() && txtDescuento.getText().isEmpty())
					&& dcFechaInicio.getDate()!=null && dcFechaFin.getDate()!=null){
				if(Double.parseDouble(txtDescuento.getText())>=10 && Double.parseDouble(txtDescuento.getText())<=70){
					if(ValidarFechas()){
						String codigo=String.valueOf(model.getValueAt(jtPromocion.getSelectedRow(),0));
						 gb.ModificarPromociones(codigo, txtDescripcion.getText(),
										(Double.parseDouble(txtDescuento.getText())/100), fechaSQL(dcFechaInicio.getDate()),
										fechaSQL(dcFechaFin.getDate()));
						Mensaje("Datos modificado");
						model.setRowCount(0);
						limpiar();
						mostrarDatos();
						btnModificar.setEnabled(false);
					}else{
						Mensaje("La fecha fin debe ser mayor a la fecha inicio");
					}
				}else{
					Mensaje("El rango de descuento es de 10% a 70%");
				}
			}else{
				if(txtDescuento.getText().isEmpty()){
					txtDescuento.setBackground(new Color(202, 207, 210));
					txtDescuento.setBorder(BorderFactory.createLineBorder(Color.black));
					txtDescuento.requestFocus();
				}
				if(txtDescripcion.getText().isEmpty()){
					txtDescripcion.setBackground(new Color(202, 207, 210));
					txtDescripcion.setBorder(BorderFactory.createLineBorder(Color.black));
					txtDescripcion.requestFocus();
				}
				if(dcFechaInicio.getDate()==null){
					((JTextField)dcFechaInicio.getDateEditor()).setBackground(new Color(202, 207, 210));
					((JTextField)dcFechaInicio.getDateEditor()).setBorder(BorderFactory.createLineBorder(Color.black));
				}
				if(dcFechaFin.getDate()==null){
					((JTextField)dcFechaFin.getDateEditor()).setBackground(new Color(202, 207, 210));
					((JTextField)dcFechaFin.getDateEditor()).setBorder(BorderFactory.createLineBorder(Color.black));
				}
			}	
		} catch (Exception e) {
			Mensaje("No ha seleccionado ninguna promoción");
		}
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		//BOTON ELIMINAR
		ReservarVuelo.ventanaMensaje();
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		try {
			String codigo=String.valueOf(model.getValueAt(jtPromocion.getSelectedRow(),0));
			int op=JOptionPane.showConfirmDialog(null, "Desea eliminar el registro?","",JOptionPane.OK_CANCEL_OPTION,
												JOptionPane.PLAIN_MESSAGE,icono);
			if(op==0){
				gb.EliminarPromociones(codigo);		
				model.setRowCount(0);
				mostrarDatos();
				limpiar();
			}
		} catch (Exception e) {
			Mensaje("No ha seleccionado ninguna opción");
		}
	}
	protected void actionPerformedBtnCerrar(ActionEvent arg0) {
		//BOTON CERRAR
		ReservarVuelo.ventanaMensaje();
		Administrador ad=new Administrador();
		ad.toFront();
		ad.setVisible(true);
		this.dispose();
	}
	protected void do_dcFechaInicioCalendarButton_actionPerformed(ActionEvent arg0) {
		((JTextField)dcFechaInicio.getDateEditor()).setBackground(new Color(255, 255, 255));
		((JTextField)dcFechaInicio.getDateEditor()).setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
	}
	protected void do_dcFechaFinCalendarButton_actionPerformed(ActionEvent e) {
		((JTextField)dcFechaFin.getDateEditor()).setBackground(new Color(255, 255, 255));
		((JTextField)dcFechaFin.getDateEditor()).setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
	}
	/*---------------------------------EVENTOS----------------------------------*/		
	protected void do_txtDescuento_keyTyped(KeyEvent e) {
		txtDescuento.setBackground(new Color(255, 255, 255));
		txtDescuento.setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
		char x=e.getKeyChar();
		if(Character.isDigit(x) ){
		}else{
			e.consume();
		}
		if(txtDescuento.getText().length()>1){
			e.consume();
			getToolkit().beep();
		}
	}
	protected void do_txtDescripcion_keyTyped(KeyEvent e) {
		txtDescripcion.setBackground(new Color(255, 255, 255));
		txtDescripcion.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));
		char x=e.getKeyChar();
		if(Character.isLetter(x) || Character.isSpaceChar(x) || Character.isDigit(x)
				|| Character.isDigit(x) || x=='-' || x=='.' || x=='%'){
		}else{
			e.consume();
		}
		if(txtDescripcion.getText().length()>60){
			e.consume();
			getToolkit().beep();
		}
	}
	protected void do_jtPromocion_mouseClicked(MouseEvent arg0) {
		mostrarTabla();
	}
	/*-------------------------------METODOS------------------------------------*/
	private void regresarObjetos(){
		txtDescuento.setBackground(new Color(255, 255, 255));
		txtDescuento.setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
		txtDescripcion.setBackground(new Color(255, 255, 255));
		txtDescripcion.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));
		((JTextField)dcFechaInicio.getDateEditor()).setBackground(new Color(255, 255, 255));
		((JTextField)dcFechaInicio.getDateEditor()).setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
		((JTextField)dcFechaFin.getDateEditor()).setBackground(new Color(255, 255, 255));
		((JTextField)dcFechaFin.getDateEditor()).setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
	}
	private boolean ValidarFechas(){		
		if(dcFechaInicio.getDate().before(dcFechaFin.getDate())){
			return true;
		}
		return false;
	}
	private String codigo(){
		String codigo=GenerarCodigo();
		data=gb.ListarPromociones();
		for (int i = 0; i < data.size(); i++) {
			objPromo=data.get(i);
			if(objPromo.getCod_Promo().equals(codigo)){
				codigo=GenerarCodigo();
			}else{
				return codigo;
			}
		}
		return null;
	}
	private String GenerarCodigo(){
		String palabra="";
		 for (int i=0; i<10; i++){ 
		     int codigoAscii = (int)Math.floor(Math.random()*(122 -97)+97); 
		         palabra = palabra + (char)codigoAscii; 
		      } 
		 return palabra;  
	}
	private String quitarUltimaLetra(String g){
		String t[]=g.split("%");
		return t[0];
	}
	private void limpiar(){
		txtDescripcion.setText(null);
		txtDescuento.setText(null);
		((JTextField)dcFechaInicio.getDateEditor()).setText(null);
		((JTextField)dcFechaFin.getDateEditor()).setText(null);
	}
	private String fechaSQL(Date e){
		SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(e);
	}
	private void mostrarDatos(){
		data=gb.ListarPromociones();
		if(data.size()==6){
			btnAñadir.setEnabled(false);
		}else{
			btnAñadir.setEnabled(true);
		}
		for (int i = 0; i < data.size(); i++) {
			objPromo=data.get(i);
			Object Lista[]=new Object[5];
			Lista[0]=objPromo.getCod_Promo();
			Lista[1]=objPromo.getDescrip_Promo();
			Lista[2]=df.format(objPromo.getPorcentaje_desc());
			Lista[3]=RegistroAcompañantes.fecha(objPromo.getFechaIni_Promo());
			Lista[4]=RegistroAcompañantes.fecha(objPromo.getFechaFin_Promo());
			model.addRow(Lista);
		}
		ajustarTabla();
	}
	private void mostrarTabla(){
		if(jtPromocion.getSelectedRow()!=-1){
			btnModificar.setEnabled(true);
			btnAñadir.setEnabled(false);
		}
		int fila=jtPromocion.getSelectedRow();
		txtDescripcion.setText((String)jtPromocion.getValueAt(fila, 1));
		txtDescuento.setText(quitarUltimaLetra((String)jtPromocion.getValueAt(fila, 2)));
		((JTextField)dcFechaInicio.getDateEditor()).setText((String)jtPromocion.getValueAt(fila, 3));
		((JTextField)dcFechaFin.getDateEditor()).setText((String)jtPromocion.getValueAt(fila, 4));
		dcFechaInicio.setSelectableDateRange(dcFechaInicio.getDate()
				,new Date(dcFechaInicio.getDate().getTime() + TimeUnit.DAYS.toMillis(90)));
		dcFechaFin.setSelectableDateRange(dcFechaFin.getDate()
				,new Date(dcFechaFin.getDate().getTime() + TimeUnit.DAYS.toMillis(90)));
		 regresarObjetos();
	}
	/*--------------------------------PERSONALIZAR TABLA-------------------------------*/
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
		
	private void ajustarTabla(){
		TableColumnModel tcm= jtPromocion.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(11));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(53));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(5));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(12));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(12));
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i <5; i++) {
			jtPromocion.getColumnModel().getColumn(i).setCellRenderer(tcr); 
		}
		
		DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);
		tcr1.setBackground(Color.DARK_GRAY);
		tcr1.setForeground(Color.white);
		for (int i = 0; i < 5; i++) {
			jtPromocion.getColumnModel().getColumn(i).setHeaderRenderer(tcr1);
		}
	}
	/*------------------------------VENTANA DE MENSAJE---------------------------*/
	private void Mensaje(String e){
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		JOptionPane.showMessageDialog(null, e,"",JOptionPane.PLAIN_MESSAGE,icono);
	}
	
}





