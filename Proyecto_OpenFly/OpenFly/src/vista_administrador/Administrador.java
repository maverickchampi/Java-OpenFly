package vista_administrador;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import libreria.Diseño;
import vista_sesion.SesionGeneral;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Administrador extends JFrame {

	private JPanel contentPane;
	private JButton btnAgregarVuelos;
	private JButton btnGestionarPromociones;
	private JLabel lblAdministrador;
	private JLabel label;
	private JLabel lblFondo;
	private JLabel lblLogo;

	/**
	 * Launch the application.
	 */
	Diseño d=new Diseño();
	private JButton btnSalir;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrador frame = new Administrador();
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
	public Administrador() {
		setTitle("OpenFly\u00AE - ADMINISTRADOR");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Administrador.class.getResource("/icon/logoOP.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 468, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		/*-----------DISEÑO -----------*/
		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", new Color(70, 130, 180));
		UIManager.put("Button.font", new java.awt.Font("Dubai", java.awt.Font.BOLD, 12));
		UIManager.put("Button.foreground", Color.white);
		{
			btnAgregarVuelos = new JButton("GESTIONAR VUELOS");
			btnAgregarVuelos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAgregarVuelos.setForeground(new Color(255, 255, 255));
			btnAgregarVuelos.setBackground(new Color(0, 139, 139));
			btnAgregarVuelos.setFont(new Font("Dubai", Font.BOLD, 10));
			btnAgregarVuelos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_btnAgregarVuelos_actionPerformed(arg0);
				}
			});
			{
				btnSalir = new JButton("");
				btnSalir.setBorder(null);
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						do_btnSalir_actionPerformed(arg0);
					}
				});
				btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnSalir.setOpaque(false);
				btnSalir.setIcon(new ImageIcon(Administrador.class.getResource("/icon/cerrar.png")));
				btnSalir.setBounds(12, 17, 46, 51);
				contentPane.add(btnSalir);
			}
			{
				lblLogo = new JLabel("");
				lblLogo.setBounds(405, 17, 46, 51);
				contentPane.add(lblLogo);
				d.img(Administrador.class.getResource("/icon/logoOP.png"),lblLogo);
			}
			btnAgregarVuelos.setBounds(70, 212, 166, 43);
			contentPane.add(btnAgregarVuelos);
		}
		{
			btnGestionarPromociones = new JButton("GESTIONAR PROMOCIONES");
			btnGestionarPromociones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnGestionarPromociones.setForeground(new Color(255, 255, 255));
			btnGestionarPromociones.setBackground(new Color(0, 139, 139));
			btnGestionarPromociones.setFont(new Font("Dubai", Font.BOLD, 10));
			btnGestionarPromociones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnGestionarPromociones_actionPerformed(e);
				}
			});
			btnGestionarPromociones.setBounds(244, 212, 166, 43);
			contentPane.add(btnGestionarPromociones);
		}
		{
			lblAdministrador = new JLabel("ADMINISTRADOR ");
			lblAdministrador.setFont(new Font("Dubai", Font.BOLD, 17));
			lblAdministrador.setBounds(171, 17, 138, 30);
			contentPane.add(lblAdministrador);
		}
		{
			label = new JLabel("");
			label.setBounds(159, 64, 177, 131);
			contentPane.add(label);
			d.img(Administrador.class.getResource("/icon/admi1.png"),label);
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 490, 289);
			contentPane.add(lblFondo);
			d.img(Administrador.class.getResource("/img/fondo.jpg"),lblFondo);
		}
	}

	protected void do_btnAgregarVuelos_actionPerformed(ActionEvent arg0) {
		GestionarVuelos gv=new GestionarVuelos();
		gv.toFront();
		gv.setVisible(true);
		this.dispose();
	}
	protected void do_btnGestionarPromociones_actionPerformed(ActionEvent e) {
		GestionarPromociones gp=new GestionarPromociones();
		gp.toFront();
		gp.setVisible(true);
		this.dispose();
	}
	protected void do_btnSalir_actionPerformed(ActionEvent arg0) {
		SesionGeneral sg=new  SesionGeneral();
		sg.toFront();
		sg.setVisible(true);
		this.dispose();
	}
}
