package vista_usuario;

import java.util.regex.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import mantenimientos.GestionBoleta;
import mantenimientos.GestionPasajeros;
import model.Boleto;
import vista_sesion.Iniciarsesion;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Cursor;

public class HistorialVuelos extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTable table;
	private JButton btnSalir;
	private JScrollPane scrollPane;
	private JLabel lblCodigo;
	private JButton btnBuscar;
	
	private DefaultTableModel modelo=new DefaultTableModel();
	private GestionBoleta gb=new GestionBoleta();
	GestionPasajeros gp=new GestionPasajeros();
	private Boleto objBol=new Boleto();
	private ArrayList<Boleto> data=gb.ListarBoletos();
	private JButton btnRefresh;
	private JLabel lblFondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistorialVuelos frame = new HistorialVuelos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void iconoBoton(String url, JButton boton){
		ImageIcon logo=new ImageIcon(url);
		Image imgLogo=logo.getImage();
		Image imgLogoReduc=imgLogo.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imgLogoReduc));
	}
	@SuppressWarnings("unused")
	public void imagenLabel(String url, JLabel label){
		ImageIcon logo=new ImageIcon(url);
		Image imgLogo=logo.getImage();
	}
	public void cargarRegistros(String usuario){
		for (int i = 0; i < data.size(); i++) {
			objBol=data.get(i);
			Object Lista[]=new Object[4];
			Lista[0]=objBol.getCodBol();
			Lista[1]=objBol.getCodVI();
			if(objBol.getCodVV()==null){
				Lista[2]="SOLO IDA.";
			} else Lista[2]=objBol.getCodVV();
			Lista[3]=objBol.getAsientos();
			if(objBol.getCodUsu().equals(usuario))
			modelo.addRow(Lista);
			}
		ajustarTabla();
	}
	
	public HistorialVuelos() {
		setTitle("OpenFly\u00AE - HISTORIAL DE VUELOS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(HistorialVuelos.class.getResource("/icon/logoOP.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 316);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
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
			btnRefresh = new JButton("");
			btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRefresh.addActionListener(this);
			btnRefresh.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			btnRefresh.setBackground(Color.WHITE);
			btnRefresh.setBounds(460, 23, 25, 25);
			contentPane.add(btnRefresh);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			txtCodigo.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			txtCodigo.setBounds(141, 25, 170, 25);
			contentPane.add(txtCodigo);
			txtCodigo.setColumns(10);
		}
		{
			{
				modelo.addColumn("Código boleto");
				modelo.addColumn("Código vuelo ida");
				modelo.addColumn("Código vuelo vuelta");
				modelo.addColumn("Cantidad asientos");
			}
		}
		{
			btnSalir = new JButton("Salir");
			btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSalir.setForeground(new Color(0, 0, 0));
			btnSalir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			btnSalir.setBackground(Color.WHITE);
			btnSalir.addActionListener(this);
			btnSalir.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			btnSalir.setBounds(495, 23, 89, 25);
			contentPane.add(btnSalir);
		}
		{
			lblCodigo = new JLabel("C\u00F3digo de boleto:");
			lblCodigo.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));			
			lblCodigo.setBounds(10, 30, 121, 14);
			contentPane.add(lblCodigo);
			
		}
		{
			btnBuscar = new JButton("Buscar Vuelos");
			btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnBuscar.setForeground(new Color(0, 0, 0));
			btnBuscar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			btnBuscar.setBackground(Color.WHITE);
			btnBuscar.addActionListener(this);
			btnBuscar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			btnBuscar.setBounds(323, 23, 127, 25);
			contentPane.add(btnBuscar);
		}
		
			scrollPane = new JScrollPane();
			scrollPane.setBorder(new LineBorder(Color.BLACK, 1, true));
			scrollPane.setFont(new Font("Gill Sans MT", Font.PLAIN, 11));
			scrollPane.setBounds(10, 68, 574, 200);
			contentPane.add(scrollPane);
			table = new JTable();
			table.setEnabled(false);
			scrollPane.setViewportView(table);
			table.setModel(modelo);
			table.setRowHeight(25);
			table.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 25));
		
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 594, 287);
			contentPane.add(lblFondo);
			imagenLabel("src/img/fondo.jpg",lblFondo);
		}
		iconoBoton("src/icon/buscar.png", btnBuscar);
		iconoBoton("src/img/icoSalir.png", btnSalir);
		iconoBoton("src/icon/refresh.png",btnRefresh);

		
		btnRefresh.setToolTipText("Resetear");
		cargarRegistros(Iniciarsesion.codCLiente);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnRefresh) {
			actionPerformedBtnRefresh(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == btnSalir) {
			actionPerformedBtnSalir(arg0);
		}
	}
	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		MenuUsuario mu=new MenuUsuario();
		mu.toFront();
		mu.setVisible(true);
		this.dispose();
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		Pattern p= Pattern.compile("BOL[0-9]{4}");
		Matcher m=p.matcher(txtCodigo.getText());
		if (m.matches()==true && txtCodigo.getText().isEmpty()==false) {
			for(int i=modelo.getRowCount()-1; i>=0; i--){
				if(modelo.getValueAt(i, 0).equals(txtCodigo.getText())){
					JOptionPane.showMessageDialog(null, "Boleto encontrado!.");
					for(int j=modelo.getRowCount()-1; j>=0; j--){
						if(!modelo.getValueAt(j, 0).equals(txtCodigo.getText())){
							modelo.removeRow(j);
						}
					}
				}
				if(i==0 && !modelo.getValueAt(0, 0).equals(txtCodigo.getText()))
					JOptionPane.showMessageDialog(null, "Boleto NO encontrado!.");
			}				
		} else{
			JOptionPane.showMessageDialog(null, "El código no cumple con el formato o no ingresó. "
					+ "\nEjemplo: BOL1001", "", JOptionPane.ERROR_MESSAGE);
		}
	}
	protected void actionPerformedBtnRefresh(ActionEvent arg0) {
		for(int i=modelo.getRowCount()-1; i>=0; i--)
			modelo.removeRow(i);
		cargarRegistros(Iniciarsesion.codCLiente);
	}
	/*--------------------------------PERSONALIZAR TABLA-------------------------------*/
	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
		
	private void ajustarTabla(){
		TableColumnModel tcm= table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15));

		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i <4; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(tcr); 
		}
		
		DefaultTableCellRenderer tcr1 = new DefaultTableCellRenderer();
		tcr1.setHorizontalAlignment(SwingConstants.CENTER);
		tcr1.setBackground(Color.DARK_GRAY);
		tcr1.setForeground(Color.white);
		for (int i = 0; i < 4; i++) {
			table.getColumnModel().getColumn(i).setHeaderRenderer(tcr1);
		}
	}
}
