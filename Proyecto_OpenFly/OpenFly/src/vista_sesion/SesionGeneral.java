package vista_sesion;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import libreria.Diseño;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class SesionGeneral extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblCiberplane;
	private JButton btnUsuario;
	private JButton btnAdministrador;
	private JLabel label;
	
	Diseño dise=new Diseño();
	private JButton button;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SesionGeneral frame = new SesionGeneral();
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
	public SesionGeneral() {
		setTitle("OpenFly\u00AE ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SesionGeneral.class.getResource("/icon/logoOP.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 453, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);//CENTRAR GUI
		{
			button = new JButton("");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_button_actionPerformed(arg0);
				}
			});
			button.setBorder(null);
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.setOpaque(false);
			button.setBackground(new Color(0, 0, 0));
			button.setIcon(new ImageIcon(SesionGeneral.class.getResource("/icon/cerrar.png")));
			button.setBounds(12, 17, 47, 35);
			contentPane.add(button);
		}
		{
			lblCiberplane = new JLabel("OpenFly");
			lblCiberplane.setFont(new Font("Pristina", Font.BOLD, 43));
			lblCiberplane.setBounds(161, 29, 141, 53);
			contentPane.add(lblCiberplane);
		}
		{
			btnUsuario = new JButton("          USUARIO");
			btnUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnUsuario.setIcon(new ImageIcon(SesionGeneral.class.getResource("/icon/usuario.png")));
			btnUsuario.setFont(new Font("Dubai", Font.BOLD, 11));
			btnUsuario.setBackground(Color.DARK_GRAY);
			btnUsuario.setForeground(Color.WHITE);
			btnUsuario.addActionListener(this);
			btnUsuario.setBounds(129, 103, 205, 60);
			contentPane.add(btnUsuario);
		}
		{
			btnAdministrador = new JButton("       ADMINISTRADOR");
			btnAdministrador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAdministrador.setIcon(new ImageIcon(SesionGeneral.class.getResource("/icon/persona.png")));
			btnAdministrador.setFont(new Font("Dubai", Font.BOLD, 11));
			btnAdministrador.setBackground(Color.DARK_GRAY);
			btnAdministrador.setForeground(Color.WHITE);
			btnAdministrador.addActionListener(this);
			btnAdministrador.setBounds(129, 183, 205, 60);
			contentPane.add(btnAdministrador);
		}
		{
			label = new JLabel("");
			label.setBounds(0, 0, 447, 307);
			contentPane.add(label);
			dise.img(SesionGeneral.class.getResource("/img/fondo4.jpg"), label);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAdministrador) {
			actionPerformedBtnAdministrador(arg0);
		}
		if (arg0.getSource() == btnUsuario) {
			actionPerformedBtnUsuario(arg0);
		}
	}
	protected void actionPerformedBtnUsuario(ActionEvent arg0) {
		Iniciarsesion iniU=new Iniciarsesion();
		iniU.setVisible(true);
		this.dispose();
	}
	protected void actionPerformedBtnAdministrador(ActionEvent arg0) {
		IniciarSesionAdmi iniAD = new IniciarSesionAdmi();
		iniAD.setVisible(true);
		this.dispose();
	}
	protected void do_button_actionPerformed(ActionEvent arg0) {
		//CERRAR
		System.exit(0);
	}
}
