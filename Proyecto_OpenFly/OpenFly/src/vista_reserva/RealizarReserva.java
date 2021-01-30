package vista_reserva;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Cursor;
import libreria.Diseño;
import model.*;
import vista_usuario.MenuUsuario;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;


public class RealizarReserva extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFondo;
	private JLabel lblLogo;
	libreria.Diseño imagen=new Diseño();
	
	/*-----------------------------------------------------------------*/
	public static JButton btnPaso1;
	public static JButton btnPaso2;
	public static JButton btnPaso3;
	public static JButton btnPaso4;
	public static JDesktopPane panel;
	//
	public static int cantiPasaj=0;
	public static String codVueloIda;
	public static String codVueloRetorno;
	public static ArrayList<Acompañante> listaAcompañantes = new ArrayList<Acompañante>() ;
	public static ArrayList<String> codigosAsientos = new ArrayList<String>();
	public static ArrayList<String> codigosAsientosVuelta = new ArrayList<String>();
	
	public static JButton btnCancela;
	public static JButton btnAceptar;
	public static JProgressBar progressBar;
	public static JLabel lblEspere;
	/*----------------------------------------------------------------*/
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RealizarReserva frame = new RealizarReserva();
					frame.toFront();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RealizarReserva() {
		setTitle("OpenFly\u00AE - RESERVAR VUELO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RealizarReserva.class.getResource("/icon/logoOP.png")));
		setResizable(false);
		setBackground(new Color(255, 255, 240));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 10, 954, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		UIManager.put("Panel.background", new Color(0,0,0,30));
		{
			btnCancela = new JButton("CANCELAR");
			btnCancela.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnCancelaJButton(e);
				}
			});
			{
				btnAceptar = new JButton("SALIR");
				btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnAceptar.setVisible(false);
				btnAceptar.setBackground(new Color(70, 130, 180));
				btnAceptar.setBounds(386, 382, 142, 38);
				contentPane.add(btnAceptar);
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						actionPerformedBtnSalidaJButton(e);
					}
				});
				btnAceptar.setForeground(new Color(255, 255, 255));
				btnAceptar.setFont(new Font("Dubai", Font.BOLD, 10));
				btnAceptar.setEnabled(false);
			}
			{
				lblEspere = new JLabel("Se estan registrando los datos.....");
				lblEspere.setVisible(false);
				lblEspere.setBounds(235, 245, 438, 24);
				contentPane.add(lblEspere);
				lblEspere.setVerticalAlignment(SwingConstants.CENTER);
				lblEspere.setFont(new Font("Dubai", Font.PLAIN, 18));
				imagen.centrar(lblEspere);
			}
			{
				progressBar = new JProgressBar(0, 100);
				progressBar.setVisible(false);
				progressBar.setBounds(235, 297, 438, 66);
				contentPane.add(progressBar);
				progressBar.setValue(0);
				progressBar.setStringPainted(true);
				progressBar.setForeground(new Color(65, 105, 225));
				progressBar.setBackground(Color.WHITE);
			}
			btnCancela.setForeground(Color.WHITE);
			btnCancela.setFont(new Font("Dubai", Font.BOLD, 10));
			btnCancela.setFocusable(false);
			btnCancela.setBackground(Color.DARK_GRAY);
			btnCancela.setBounds(321, 580, 142, 38);
			contentPane.add(btnCancela);
		}
		{
			panel = new JDesktopPane();
			panel.setBackground(new Color(0, 0, 0,30));
			panel.setOpaque(false);
			//panel.setBounds(707, 318, 661, 513);
			panel.setBounds(134, 118, 661, 513);
			contentPane.add(panel);
		}
		{
			btnPaso1 = new JButton("");
			btnPaso1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			btnPaso1.setBorder(null);
			btnPaso1.setIcon(new ImageIcon(RealizarReserva.class.getResource("/icon/paso1.png")));
			btnPaso1.setContentAreaFilled(false);
			btnPaso1.setBounds(224, 47, 137, 60);
			contentPane.add(btnPaso1);
			//ABRIR RESERVA VUELO
			ReservarVuelo rv=new ReservarVuelo();
			panel.add(rv);
			rv.setVisible(true);
		}
		{
			btnPaso2 = new JButton("");
			btnPaso2.setEnabled(false);
			btnPaso2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			btnPaso2.setBorder(null);
			btnPaso2.setIcon(new ImageIcon(RealizarReserva.class.getResource("/icon/paso2.png")));
			btnPaso2.setContentAreaFilled(false);
			btnPaso2.setBounds(340, 47, 137, 60);
			contentPane.add(btnPaso2);
		}
		{
			btnPaso3 = new JButton("");
			btnPaso3.setEnabled(false);
			btnPaso3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			btnPaso3.setBorder(null);
			btnPaso3.setIcon(new ImageIcon(RealizarReserva.class.getResource("/icon/paso3.png")));
			btnPaso3.setContentAreaFilled(false);
			btnPaso3.setBounds(458, 47, 137, 60);
			contentPane.add(btnPaso3);
		}
		{
			btnPaso4 = new JButton("");
			btnPaso4.setEnabled(false);
			btnPaso4.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			btnPaso4.setBorder(null);
			btnPaso4.setIcon(new ImageIcon(RealizarReserva.class.getResource("/icon/paso4.png")));
			btnPaso4.setContentAreaFilled(false);
			btnPaso4.setBounds(576, 47, 137, 60);
			contentPane.add(btnPaso4);
		}
		{
			lblLogo = new JLabel("");
			lblLogo.setBounds(12, 17, 188, 115);
			contentPane.add(lblLogo);
			imagen.img(RealizarReserva.class.getResource("/icon/logoOpenFly.png"), lblLogo);
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setBackground(new Color(250, 235, 215));
			lblFondo.setBounds(0, 0, 948, 671);
			contentPane.add(lblFondo);
			imagen.img(RealizarReserva.class.getResource("/img/fondo.jpg"), lblFondo);
		}
	}
	protected void actionPerformedBtnCancelaJButton(ActionEvent e) {
		// BOTON CANCELAR
		ReservarVuelo.ventanaMensaje();
		Icon icono=new ImageIcon(getClass().getResource("/icon/pregunta.png"));
		int num = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas salir ?", "", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,icono);
		if (num == 0) {
			dispose();
			panel.setVisible(false);
			MenuUsuario mu = new MenuUsuario();
			mu.setVisible(true);
		} 
	}
	protected void actionPerformedBtnSalidaJButton(ActionEvent e) {
		dispose();
		MenuUsuario mu = new MenuUsuario();
		mu.setVisible(true);
	}
	/*----------------------*/
}








