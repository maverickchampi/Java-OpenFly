package vista_usuario;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.LineBorder;
import libreria.Diseño;
import mantenimientos.GestionPasajeros;
import vista_reserva.RealizarReserva;
import vista_sesion.EditarInformacion;
import vista_sesion.Iniciarsesion;
import vista_sesion.SesionGeneral;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;

public class MenuUsuario extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnComprarBoelto;
	private JButton btnPromociones;
	private JButton btnHistorialDeVuelos;
	private JButton btnEditarInformacion;
	private JButton btnSalir;
	private JLabel lblBienvenido;
	private JLabel lblComprar;
	private JLabel lblPromociones;
	private JLabel lblHistorial;
	private JLabel lblSalir;
	private JLabel lblEditar;
	private JLabel lblLogo;
	private JLabel lblFondo;
	
	/*----------------------------------*/
	GestionPasajeros gp=new GestionPasajeros();
	HistorialVuelos ver = new HistorialVuelos();
	Promociones ver3 = new Promociones();
	Diseño imagenLabel=new Diseño();

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUsuario frame = new MenuUsuario();
					frame.toFront();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public MenuUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuario.class.getResource("/icon/logoOP.png")));
		setTitle("OpenFly\u00AE - MEN\u00DA PRINCIPAL\r\n");
		setFont(new Font("Bell MT", Font.PLAIN, 14));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(350, 100, 645, 473);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
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
			btnComprarBoelto = new JButton("COMPRAR BOLETO ");
			btnComprarBoelto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnComprarBoelto.addActionListener(this);
			btnComprarBoelto.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnComprarBoelto.setBackground(new Color(192, 192, 192));
			btnComprarBoelto.setFont(new Font("Dubai Light", Font.BOLD, 12));
			btnComprarBoelto.setBounds(132, 102, 180, 35);
			contentPane.add(btnComprarBoelto);
		}
		{
			btnPromociones = new JButton("PROMOCIONES");
			btnPromociones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnPromociones.addActionListener(this);
			btnPromociones.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnPromociones.setBackground(new Color(192, 192, 192));
			btnPromociones.setFont(new Font("Dubai Light", Font.BOLD, 12));
			btnPromociones.setBounds(336, 102, 180, 35);
			contentPane.add(btnPromociones);
		}
		{
			btnHistorialDeVuelos = new JButton("HISTORIAL DE VUELOS");
			btnHistorialDeVuelos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnHistorialDeVuelos.addActionListener(this);
			btnHistorialDeVuelos.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnHistorialDeVuelos.setBackground(new Color(192, 192, 192));
			btnHistorialDeVuelos.setFont(new Font("Dubai Light", Font.BOLD, 12));
			btnHistorialDeVuelos.setBounds(25, 254, 180, 35);
			contentPane.add(btnHistorialDeVuelos);
		}
		{
			btnEditarInformacion = new JButton("EDITAR INFORMACIÓN");
			btnEditarInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_btnEditarInformacion_actionPerformed(arg0);
				}
			});
			btnEditarInformacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnEditarInformacion.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnEditarInformacion.setBackground(new Color(192, 192, 192));
			btnEditarInformacion.setFont(new Font("Dubai Light", Font.BOLD, 12));
			btnEditarInformacion.setBounds(230, 254, 180, 35);
			contentPane.add(btnEditarInformacion);
		}
		{
			btnSalir = new JButton("SALIR");
			btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSalir.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnSalir.setBackground(new Color(192, 192, 192));
			btnSalir.addActionListener(this);
			btnSalir.setFont(new Font("Dubai Light", Font.BOLD, 12));
			btnSalir.setBounds(435, 254, 180, 35);
			contentPane.add(btnSalir);
		}
		{
			lblBienvenido = new JLabel("\u00A1 BIENVENIDO "+gp.BuscarCliente(Iniciarsesion.codCLiente).getNomUsu()+" !...");
			lblBienvenido.setForeground(new Color(255, 255, 255));
			lblBienvenido.setHorizontalAlignment(SwingConstants.LEFT);
			lblBienvenido.setFont(new Font("Dubai", Font.BOLD, 27));
			lblBienvenido.setBounds(25, 36, 590, 35);
			imagenLabel.centrar(lblBienvenido);
			contentPane.add(lblBienvenido);
		}
		{
			lblComprar = new JLabel("");
			lblComprar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					do_lblComprar_mouseClicked(e);
				}
			});
			lblComprar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblComprar.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblComprar.setBounds(132, 137, 180, 100);
			contentPane.add(lblComprar);
		}
		{
			lblPromociones = new JLabel("");
			lblPromociones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblPromociones.addMouseListener(this);
			lblPromociones.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblPromociones.setBounds(336, 137, 180, 100);
			contentPane.add(lblPromociones);
		}
		{
			lblHistorial = new JLabel("");
			lblHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblHistorial.addMouseListener(this);
			lblHistorial.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblHistorial.setBounds(25, 289, 180, 100);
			contentPane.add(lblHistorial);
		}
		{
			lblSalir = new JLabel("");
			lblSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblSalir.addMouseListener(this);
			lblSalir.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblSalir.setBounds(435, 289, 180, 100);
			contentPane.add(lblSalir);
		}
		{
			lblEditar = new JLabel("");
			lblEditar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					do_lblEditar_mouseClicked(e);
				}
			});
			lblEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblEditar.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblEditar.setBounds(230, 289, 180, 100);
			contentPane.add(lblEditar);
		}
		{
			lblLogo = new JLabel("");
			lblLogo.setBounds(527, 18, 100, 53);
			contentPane.add(lblLogo);
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 639, 444);
			contentPane.add(lblFondo);
		}
		
		imagenLabel.img(MenuUsuario.class.getResource("/icon/logoOpenFly.png"),lblLogo);
		imagenLabel.img(MenuUsuario.class.getResource("/img/comprar.jpeg"), lblComprar);
		imagenLabel.img(MenuUsuario.class.getResource("/img/promos.jpeg"), lblPromociones);
		imagenLabel.img(MenuUsuario.class.getResource("/img/historial.jpeg"), lblHistorial);
		imagenLabel.img(MenuUsuario.class.getResource("/img/editar.jpeg"), lblEditar);
		imagenLabel.img(MenuUsuario.class.getResource("/img/salir.jpeg"), lblSalir);
		imagenLabel.img(MenuUsuario.class.getResource("/img/fondo3.jpg"), lblFondo);
		
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnPromociones) {
			actionPerformedBtnPromociones(arg0);
		}
		if (arg0.getSource() == btnHistorialDeVuelos) {
			actionPerformedBtnHistorialDeVuelos(arg0);
		}
		if (arg0.getSource() == btnComprarBoelto) {
			actionPerformedBtnComprarBoelto(arg0);
		}
		if (arg0.getSource() == btnSalir) {
			actionPerformedBtnSalir(arg0);
		}
	}
	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		int num=JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cerrar sesión?",
				"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,icono);
		if(num==0){
			SesionGeneral ini=new SesionGeneral();
			ini.toFront();
			ini.setVisible(true);
			this.dispose();
		}
	}
	protected void actionPerformedBtnComprarBoelto(ActionEvent arg0) {
		RealizarReserva rr=new RealizarReserva();
		UIManager.put("Panel.background", new Color(0,0,0,30));
		rr.setVisible(true);
		this.dispose();
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == lblPromociones) {
			mouseClickedLblPromociones(arg0);
		}
		if (arg0.getSource() == lblHistorial) {
			mouseClickedLblHistorial(arg0);
		}
		if (arg0.getSource() == lblSalir) {
			mouseClickedLblSalir(arg0);
		}
	}
	
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	
	protected void mouseClickedLblSalir(MouseEvent arg0) {
		Icon icono=new ImageIcon(getClass().getResource("/icon/informacion.png"));
		int num=JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cerrar sesión?",
				"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,icono);
		if(num==0){
			SesionGeneral ini=new SesionGeneral();
			ini.toFront();
			ini.setVisible(true);
			this.dispose();
		}
	}
	protected void actionPerformedBtnHistorialDeVuelos(ActionEvent arg0) {
		HistorialVuelos hv=new HistorialVuelos();
		hv.toFront();
		hv.setVisible(true);
		this.dispose();
	}
	protected void mouseClickedLblHistorial(MouseEvent arg0) {
		HistorialVuelos hv=new HistorialVuelos();
		hv.toFront();
		hv.setVisible(true);
		this.dispose();
	}
	protected void actionPerformedBtnPromociones(ActionEvent arg0) {
		Promociones p=new Promociones();
		p.toFront();
		p.setVisible(true);
		this.dispose();
	}
	protected void mouseClickedLblPromociones(MouseEvent arg0) {
		Promociones p=new Promociones();
		p.toFront();
		p.setVisible(true);
		this.dispose();
	}
	protected void do_btnEditarInformacion_actionPerformed(ActionEvent arg0) {
		EditarInformacion ei=new EditarInformacion();
		ei.toFront();
		ei.setVisible(true);
		this.dispose();
	}
	protected void do_lblComprar_mouseClicked(MouseEvent e) {
		RealizarReserva rr=new RealizarReserva();
		UIManager.put("Panel.background", new Color(0,0,0,30));
		rr.setVisible(true);
		this.dispose();
	}
	protected void do_lblEditar_mouseClicked(MouseEvent e) {
		EditarInformacion ei=new EditarInformacion();
		ei.toFront();
		ei.setVisible(true);
		this.dispose();
	}
}
