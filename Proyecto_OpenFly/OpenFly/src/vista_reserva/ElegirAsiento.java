package vista_reserva;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import libreria.Diseño;
import mantenimientos.GestionBoleta;
import mantenimientos.GestionVuelo;
import model.Vuelo;
import javax.swing.JComboBox;

public class ElegirAsiento extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JLabel lblLogo;
	private JPanel panel;
	private JLabel lblTitulo;
	private JLabel lblCate;
	private JLabel lblCate_1;
	private JLabel lblCate_2;
	private JLabel lblCate_3;
	private JButton btnContinuar;
	private JButton btnRegresar;
	private JLabel lblDestino;
	private JLabel lblFecha;
	private JLabel lblTotal;
	private JButton btnA1;
	private JButton btnB1;
	private JButton btnC1;
	private JButton btnA2;
	private JButton btnA3;
	private JButton btnA4;
	private JButton btnA5;
	private JButton btnA6;
	private JButton btnA7;
	private JButton btnB7;
	private JButton btnB6;
	private JButton btnB5;
	private JButton btnB4;
	private JButton btnB3;
	private JButton btnB2;
	private JButton btnC7;
	private JButton btnC6;
	private JButton btnC5;
	private JButton btnC4;
	private JButton btnC3;
	private JButton btnC2;
	private JButton btnD1;
	private JButton btnD2;
	private JButton btnD3;
	private JButton btnD4;
	private JButton btnD5;
	private JButton btnD6;
	private JButton btnD7;
	private JButton btnE1;
	private JButton btnE2;
	private JButton btnE3;
	private JButton btnE4;
	private JButton btnE5;
	private JButton btnE6;
	private JButton btnE7;
	private JButton btnF1;
	private JButton btnF2;
	private JButton btnF3;
	private JButton btnF4;
	private JButton btnF5;
	private JButton btnF6;
	private JButton btnF7;
	
	Diseño img=new Diseño();
	GestionVuelo gv=new GestionVuelo();
	GestionBoleta gb = new GestionBoleta();
	// ---------------------------------------MIS CAMPOS
	// (EDITABLE)--------------------------------------
	private int cantidadPasajeros = RealizarReserva.cantiPasaj;//INDICA LA CANTIDAD DE PASAJEROS (TU + ACOMPAÑANTES)
	private double costo;//PRECIO DE LA CLASE
	private String destino ;//DESTINO DEL BOLETO
	private String fecha ;// FECHA DE SALIDA DEL VUELO
	private JLabel lblFondo;
	private JComboBox<String> cbVuelo;
	
	
	public static void main(String[] args) {
		try {
			ElegirAsiento dialog = new ElegirAsiento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ElegirAsiento() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ElegirAsiento.class.getResource("/icon/icon.png")));
		setBounds(343,158, 661, 513);
		getContentPane().setLayout(new BorderLayout());
		this.setUndecorated(true);
		contentPanel.setBackground(new Color(100, 149, 237));
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		//setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			lblTitulo = new JLabel("<html>ELIGE TUS "+cantidadPasajeros+" ASIENTOS DEL VUELO</html>");
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTitulo.setForeground(new Color(0, 0, 0));
			lblTitulo.setBounds(125, 29, 264, 38);
			contentPanel.add(lblTitulo);
		}
		{
			lblCate = new JLabel("A       B       C");
			lblCate.setFont(new Font("Arial", Font.PLAIN, 15));
			lblCate.setForeground(Color.BLACK);
			lblCate.setBounds(101, 112, 100, 14);
			contentPanel.add(lblCate);
		}
		{
			lblCate_1 = new JLabel("D       E       F");
			lblCate_1.setForeground(Color.BLACK);
			lblCate_1.setFont(new Font("Arial", Font.PLAIN, 15));
			lblCate_1.setBounds(260, 112, 100, 14);
			contentPanel.add(lblCate_1);
		}

		{
			lblCate_2 = new JLabel("<html>1\r\n2\r\n3\r\n\n</html>");
			lblCate_2.setForeground(Color.BLACK);
			lblCate_2.setFont(new Font("Arial", Font.PLAIN, 15));
			lblCate_2.setBounds(220, 131, 7, 136);
			contentPanel.add(lblCate_2);
		}
		{
			lblCate_3 = new JLabel("<html>4\r\n5\r\n\r\n6\r\n\r\n7\r\n\n</html>");
			lblCate_3.setForeground(Color.BLACK);
			lblCate_3.setFont(new Font("Arial", Font.PLAIN, 15));
			lblCate_3.setBounds(220, 294, 7, 136);
			contentPanel.add(lblCate_3);
		}
		{
			btnContinuar = new JButton("CONTINUAR");
			btnContinuar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnContinuar(e);
				}
			});
			btnContinuar.setFocusable(false);
			btnContinuar.setName("Continuar");
			btnContinuar.setBackground(new Color(30, 144, 255));
			btnContinuar.setForeground(Color.WHITE);
			btnContinuar.setFont(new Font("Arial", Font.BOLD, 12));
			btnContinuar.setBounds(428, 375, 123, 34);
			contentPanel.add(btnContinuar);
		}
		{
			btnRegresar = new JButton("REGRESAR");
			btnRegresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRegresar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnRegresar(e);
				}
			});
			btnRegresar.setFocusable(false);
			btnRegresar.setName("Regreso");
			btnRegresar.setFont(new Font("Arial", Font.BOLD, 12));
			btnRegresar.setForeground(Color.WHITE);
			btnRegresar.setBackground(new Color(178, 34, 34));
			btnRegresar.setBounds(428, 420, 123, 34);
			contentPanel.add(btnRegresar);
		}
		{
			lblDestino = new JLabel("");
			lblDestino.setForeground(new Color(0, 0, 0));
			lblDestino.setFont(new Font("Dubai", Font.BOLD, 12));
			lblDestino.setBounds(412, 248, 184, 58);
			contentPanel.add(lblDestino);
		}
		{
			lblFecha = new JLabel("");
			lblFecha.setForeground(new Color(0, 0, 0));
			lblFecha.setFont(new Font("Dubai", Font.BOLD, 12));
			lblFecha.setBounds(412, 306, 168, 27);
			contentPanel.add(lblFecha);
		}
		{
			lblTotal = new JLabel("");
			lblTotal.setForeground(new Color(0, 0, 0));
			lblTotal.setFont(new Font("Dubai", Font.BOLD, 12));
			lblTotal.setBounds(412, 337, 168, 27);
			contentPanel.add(lblTotal);
		}

		// ---------------------------------------------------DISEÑO----------------------------------------------

		// --------------------------------------------------ASIENTOS A------------------------------------------------

		{
			btnA1 = new JButton("");
			btnA1.setName("a1");
			btnA1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnA1(e);
				}
			});
			btnA1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnA1.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnA1.setBounds(85, 137, 40, 40);
			btnA1.setBorder(null);
			btnA1.setContentAreaFilled(false);
			contentPanel.add(btnA1);

		}
		{
			btnA2 = new JButton("");
			btnA2.setName("a2");
			btnA2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnA2(e);
				}
			});
			btnA2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnA2.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnA2.setContentAreaFilled(false);
			btnA2.setBorder(null);
			btnA2.setBounds(85, 178, 40, 40);
			contentPanel.add(btnA2);
		}
		{
			btnA3 = new JButton("");
			btnA3.setName("a3");
			btnA3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnA3(e);
				}
			});
			btnA3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnA3.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnA3.setContentAreaFilled(false);
			btnA3.setBorder(null);
			btnA3.setBounds(85, 219, 40, 40);
			contentPanel.add(btnA3);
		}
		{
			btnA4 = new JButton("");
			btnA4.setName("a4");
			btnA4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnA4(e);
				}
			});
			btnA4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnA4.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnA4.setContentAreaFilled(false);
			btnA4.setBorder(null);
			btnA4.setBounds(85, 280, 40, 40);
			contentPanel.add(btnA4);
		}
		{
			btnA5 = new JButton("");
			btnA5.setName("a5");
			btnA5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnA5(e);
				}
			});
			btnA5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnA5.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnA5.setContentAreaFilled(false);
			btnA5.setBorder(null);
			btnA5.setBounds(85, 321, 40, 40);
			contentPanel.add(btnA5);
		}
		{
			btnA6 = new JButton("");
			btnA6.setName("a6");
			btnA6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnA6(e);
				}
			});
			btnA6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnA6.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnA6.setContentAreaFilled(false);
			btnA6.setBorder(null);
			btnA6.setBounds(85, 362, 40, 40);
			contentPanel.add(btnA6);
		}
		{
			btnA7 = new JButton("");
			btnA7.setName("a7");
			btnA7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnA7(e);
				}
			});
			btnA7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnA7.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnA7.setContentAreaFilled(false);
			btnA7.setBorder(null);
			btnA7.setBounds(85, 403, 40, 40);
			contentPanel.add(btnA7);
		}

		// --------------------------------------------------ASIENTOS B------------------------------------------------

		{
			btnB1 = new JButton("");
			btnB1.setName("b1");
			btnB1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnB1(e);
				}
			});
			btnB1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnB1.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnB1.setContentAreaFilled(false);
			btnB1.setBorder(null);
			btnB1.setBounds(125, 137, 40, 40);
			contentPanel.add(btnB1);
		}
		{
			btnB2 = new JButton("");
			btnB2.setName("b2");
			btnB2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnB2(e);
				}
			});
			btnB2.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnB2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnB2.setContentAreaFilled(false);
			btnB2.setBorder(null);
			btnB2.setBounds(125, 178, 40, 40);
			contentPanel.add(btnB2);
		}
		{
			btnB3 = new JButton("");
			btnB3.setName("b3");
			btnB3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnB3(e);
				}
			});
			btnB3.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnB3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnB3.setContentAreaFilled(false);
			btnB3.setBorder(null);
			btnB3.setBounds(125, 219, 40, 40);
			contentPanel.add(btnB3);
		}
		{
			btnB4 = new JButton("");
			btnB4.setName("b4");
			btnB4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnB4(e);
				}
			});
			btnB4.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnB4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnB4.setContentAreaFilled(false);
			btnB4.setBorder(null);
			btnB4.setBounds(125, 280, 40, 40);
			contentPanel.add(btnB4);
		}
		{
			btnB5 = new JButton("");
			btnB5.setName("b5");
			btnB5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnB5(e);
				}
			});
			btnB5.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnB5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnB5.setContentAreaFilled(false);
			btnB5.setBorder(null);
			btnB5.setBounds(125, 321, 40, 40);
			contentPanel.add(btnB5);
		}
		{
			btnB6 = new JButton("");
			btnB6.setName("b6");
			btnB6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnB6(e);
				}
			});
			btnB6.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnB6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnB6.setContentAreaFilled(false);
			btnB6.setBorder(null);
			btnB6.setBounds(125, 362, 40, 40);
			contentPanel.add(btnB6);
		}
		{
			btnB7 = new JButton("");
			btnB7.setName("b7");
			btnB7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnB7(e);
				}
			});
			btnB7.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnB7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnB7.setContentAreaFilled(false);
			btnB7.setBorder(null);
			btnB7.setBounds(125, 403, 40, 40);
			contentPanel.add(btnB7);
		}

		// --------------------------------------------------ASIENTOS C------------------------------------------------

		{
			btnC1 = new JButton("");
			btnC1.setName("c1");
			btnC1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnC1(e);
				}
			});
			btnC1.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnC1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnC1.setContentAreaFilled(false);
			btnC1.setBorder(null);
			btnC1.setBounds(165, 137, 40, 40);
			contentPanel.add(btnC1);
		}
		{
			btnC2 = new JButton("");
			btnC2.setName("c2");
			btnC2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnC2(e);
				}
			});
			btnC2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnC2.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnC2.setContentAreaFilled(false);
			btnC2.setBorder(null);
			btnC2.setBounds(165, 178, 40, 40);
			contentPanel.add(btnC2);
		}
		{
			btnC3 = new JButton("");
			btnC3.setName("c3");
			btnC3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnC3(e);
				}
			});
			btnC3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnC3.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnC3.setContentAreaFilled(false);
			btnC3.setBorder(null);
			btnC3.setBounds(165, 219, 40, 40);
			contentPanel.add(btnC3);
		}
		{
			btnC4 = new JButton("");
			btnC4.setName("c4");
			btnC4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnC4(e);
				}
			});
			btnC4.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnC4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnC4.setContentAreaFilled(false);
			btnC4.setBorder(null);
			btnC4.setBounds(165, 280, 40, 40);
			contentPanel.add(btnC4);
		}
		{
			btnC5 = new JButton("");
			btnC5.setName("c5");
			btnC5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnC5(e);
				}
			});
			btnC5.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnC5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnC5.setContentAreaFilled(false);
			btnC5.setBorder(null);
			btnC5.setBounds(165, 321, 40, 40);
			contentPanel.add(btnC5);
		}
		{
			btnC6 = new JButton("");
			btnC6.setName("c6");
			btnC6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnC6(e);
				}
			});
			btnC6.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnC6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnC6.setContentAreaFilled(false);
			btnC6.setBorder(null);
			btnC6.setBounds(165, 362, 40, 40);
			contentPanel.add(btnC6);
		}
		{
			btnC7 = new JButton("");
			btnC7.setName("c7");
			btnC7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnC7(e);
				}
			});
			btnC7.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnC7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnC7.setContentAreaFilled(false);
			btnC7.setBorder(null);
			btnC7.setBounds(165, 403, 40, 40);
			contentPanel.add(btnC7);
		}

		// --------------------------------------------------ASIENTOS D------------------------------------------------

		{
			btnD1 = new JButton("");
			btnD1.setName("d1");
			btnD1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnD1(e);
				}
			});
			btnD1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnD1.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnD1.setContentAreaFilled(false);
			btnD1.setBorder(null);
			btnD1.setBounds(245, 137, 40, 40);
			contentPanel.add(btnD1);
		}
		{
			btnD2 = new JButton("");
			btnD2.setName("d2");
			btnD2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnD2(e);
				}
			});
			btnD2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnD2.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnD2.setContentAreaFilled(false);
			btnD2.setBorder(null);
			btnD2.setBounds(245, 178, 40, 40);
			contentPanel.add(btnD2);
		}
		{
			btnD3 = new JButton("");
			btnD3.setName("d3");
			btnD3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnD3(e);
				}
			});
			btnD3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnD3.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnD3.setContentAreaFilled(false);
			btnD3.setBorder(null);
			btnD3.setBounds(245, 219, 40, 40);
			contentPanel.add(btnD3);
		}
		{
			btnD4 = new JButton("");
			btnD4.setName("d4");
			btnD4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnD4(e);
				}
			});
			btnD4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnD4.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnD4.setContentAreaFilled(false);
			btnD4.setBorder(null);
			btnD4.setBounds(245, 280, 40, 40);
			contentPanel.add(btnD4);
		}
		{
			btnD5 = new JButton("");
			btnD5.setName("d5");
			btnD5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnD5(e);
				}
			});
			btnD5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnD5.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnD5.setContentAreaFilled(false);
			btnD5.setBorder(null);
			btnD5.setBounds(245, 321, 40, 40);
			contentPanel.add(btnD5);
		}
		{
			btnD6 = new JButton("");
			btnD6.setName("d6");
			btnD6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnD6(e);
				}
			});
			btnD6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnD6.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnD6.setContentAreaFilled(false);
			btnD6.setBorder(null);
			btnD6.setBounds(245, 362, 40, 40);
			contentPanel.add(btnD6);
		}
		{
			btnD7 = new JButton("");
			btnD7.setName("d7");
			btnD7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnD7(e);
				}
			});
			btnD7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnD7.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnD7.setContentAreaFilled(false);
			btnD7.setBorder(null);
			btnD7.setBounds(245, 403, 40, 40);
			contentPanel.add(btnD7);
		}

		// --------------------------------------------------ASIENTOS E------------------------------------------------

		{
			btnE1 = new JButton("");
			btnE1.setName("e1");
			btnE1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnE1(e);
				}
			});
			btnE1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnE1.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnE1.setContentAreaFilled(false);
			btnE1.setBorder(null);
			btnE1.setBounds(285, 137, 40, 40);
			contentPanel.add(btnE1);
		}
		{
			btnE2 = new JButton("");
			btnE2.setName("e2");
			btnE2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnE2(e);
				}
			});
			btnE2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnE2.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnE2.setContentAreaFilled(false);
			btnE2.setBorder(null);
			btnE2.setBounds(285, 178, 40, 40);
			contentPanel.add(btnE2);
		}
		{
			btnE3 = new JButton("");
			btnE3.setName("e3");
			btnE3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnE3(e);
				}
			});
			btnE3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnE3.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnE3.setContentAreaFilled(false);
			btnE3.setBorder(null);
			btnE3.setBounds(285, 219, 40, 40);
			contentPanel.add(btnE3);
		}
		{
			btnE4 = new JButton("");
			btnE4.setName("e4");
			btnE4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnE4(e);
				}
			});
			btnE4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnE4.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnE4.setContentAreaFilled(false);
			btnE4.setBorder(null);
			btnE4.setBounds(285, 280, 40, 40);
			contentPanel.add(btnE4);
		}
		{
			btnE5 = new JButton("");
			btnE5.setName("e5");
			btnE5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnE5(e);
				}
			});
			btnE5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnE5.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnE5.setContentAreaFilled(false);
			btnE5.setBorder(null);
			btnE5.setBounds(285, 321, 40, 40);
			contentPanel.add(btnE5);
		}
		{
			btnE6 = new JButton("");
			btnE6.setName("e6");
			btnE6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnE6(e);
				}
			});
			btnE6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnE6.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnE6.setContentAreaFilled(false);
			btnE6.setBorder(null);
			btnE6.setBounds(285, 362, 40, 40);
			contentPanel.add(btnE6);
		}
		{
			btnE7 = new JButton("");
			btnE7.setName("e7");
			btnE7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnE7(e);
				}
			});
			btnE7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnE7.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnE7.setContentAreaFilled(false);
			btnE7.setBorder(null);
			btnE7.setBounds(285, 403, 40, 40);
			contentPanel.add(btnE7);
		}

		// --------------------------------------------------ASIENTOS F------------------------------------------------

		{
			btnF1 = new JButton("");
			btnF1.setName("f1");
			btnF1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnF1(e);
				}
			});
			btnF1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnF1.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnF1.setContentAreaFilled(false);
			btnF1.setBorder(null);
			btnF1.setBounds(325, 137, 40, 40);
			contentPanel.add(btnF1);
		}
		{
			btnF2 = new JButton("");
			btnF2.setName("f2");
			btnF2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnF2(e);
				}
			});
			btnF2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnF2.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnF2.setContentAreaFilled(false);
			btnF2.setBorder(null);
			btnF2.setBounds(325, 178, 40, 40);
			contentPanel.add(btnF2);
		}
		{
			btnF3 = new JButton("");
			btnF3.setName("f3");
			btnF3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnF3(e);
				}
			});
			btnF3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnF3.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnF3.setContentAreaFilled(false);
			btnF3.setBorder(null);
			btnF3.setBounds(325, 219, 40, 40);
			contentPanel.add(btnF3);
		}
		{
			btnF4 = new JButton("");
			btnF4.setName("f4");
			btnF4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnF4(e);
				}
			});
			btnF4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnF4.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnF4.setContentAreaFilled(false);
			btnF4.setBorder(null);
			btnF4.setBounds(325, 280, 40, 40);
			contentPanel.add(btnF4);
		}
		{
			btnF5 = new JButton("");
			btnF5.setName("f5");
			btnF5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnF5(e);
				}
			});
			btnF5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnF5.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnF5.setContentAreaFilled(false);
			btnF5.setBorder(null);
			btnF5.setBounds(325, 321, 40, 40);
			contentPanel.add(btnF5);
		}
		{
			btnF6 = new JButton("");
			btnF6.setName("f6");
			btnF6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnF6(e);
				}
			});
			btnF6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnF6.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnF6.setContentAreaFilled(false);
			btnF6.setBorder(null);
			btnF6.setBounds(325, 362, 40, 40);
			contentPanel.add(btnF6);
		}
		{
			btnF7 = new JButton("");
			btnF7.setName("f7");
			btnF7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtnF7(e);
				}
			});
			btnF7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnF7.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			btnF7.setContentAreaFilled(false);
			btnF7.setBorder(null);
			btnF7.setBounds(325, 403, 40, 40);
			contentPanel.add(btnF7);
		}
		{
			lblLogo = new JLabel("");
			lblLogo.setBounds(420, 95, 160, 136);
			contentPanel.add(lblLogo);
			img.img(ElegirAsiento.class.getResource("/img/frase.jpg"), lblLogo);
		}
		{
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			panel.setBackground(new Color(211, 211, 211));
			panel.setBounds(70, 95, 312, 359);
			contentPanel.add(panel);
		}
		{
			cbVuelo = new JComboBox<String>();
			cbVuelo.setBackground(Color.WHITE);
			cbVuelo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actionPerformedCbVueloJComboBox(arg0);
				}
			});
			cbVuelo.setBounds(403, 35, 106, 27);
			contentPanel.add(cbVuelo);
			cbVuelo.addItem(RealizarReserva.codVueloIda);
			if(RealizarReserva.codVueloRetorno!=null){
			cbVuelo.addItem(RealizarReserva.codVueloRetorno);
			}
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/fondo2.jpg")));
			lblFondo.setBounds(0, 0, 661, 513);
			contentPanel.add(lblFondo);
			img.img(RealizarReserva.class.getResource("/img/fondo2.jpg"), lblFondo);
		}
		
	}

	// --------------------------------------------------EVENTOS------------------------------------------------

	// --------------------------------------------------ASIENTOS A------------------------------------------------

	protected void actionPerformedBtnA1(ActionEvent e) {
		elegirAsiento(btnA1);
	}

	protected void actionPerformedBtnA2(ActionEvent e) {
		elegirAsiento(btnA2);
	}

	protected void actionPerformedBtnA3(ActionEvent e) {
		elegirAsiento(btnA3);
	}

	protected void actionPerformedBtnA4(ActionEvent e) {
		elegirAsiento(btnA4);
	}

	protected void actionPerformedBtnA5(ActionEvent e) {
		elegirAsiento(btnA5);
	}

	protected void actionPerformedBtnA6(ActionEvent e) {
		elegirAsiento(btnA6);
	}

	protected void actionPerformedBtnA7(ActionEvent e) {
		elegirAsiento(btnA7);
	}

	// --------------------------------------------------ASIENTOS B------------------------------------------------

	protected void actionPerformedBtnB1(ActionEvent e) {
		elegirAsiento(btnB1);
	}

	protected void actionPerformedBtnB2(ActionEvent e) {
		elegirAsiento(btnB2);
	}

	protected void actionPerformedBtnB3(ActionEvent e) {
		elegirAsiento(btnB3);
	}

	protected void actionPerformedBtnB4(ActionEvent e) {
		elegirAsiento(btnB4);
	}

	protected void actionPerformedBtnB5(ActionEvent e) {
		elegirAsiento(btnB5);
	}

	protected void actionPerformedBtnB6(ActionEvent e) {
		elegirAsiento(btnB6);
	}

	protected void actionPerformedBtnB7(ActionEvent e) {
		elegirAsiento(btnB7);
	}

	// --------------------------------------------------ASIENTOS C------------------------------------------------

	protected void actionPerformedBtnC1(ActionEvent e) {
		elegirAsiento(btnC1);
	}

	protected void actionPerformedBtnC2(ActionEvent e) {
		elegirAsiento(btnC2);
	}

	protected void actionPerformedBtnC3(ActionEvent e) {
		elegirAsiento(btnC3);
	}

	protected void actionPerformedBtnC4(ActionEvent e) {
		elegirAsiento(btnC4);
	}

	protected void actionPerformedBtnC5(ActionEvent e) {
		elegirAsiento(btnC5);
	}

	protected void actionPerformedBtnC6(ActionEvent e) {
		elegirAsiento(btnC6);
	}

	protected void actionPerformedBtnC7(ActionEvent e) {
		elegirAsiento(btnC7);
	}

	// --------------------------------------------------ASIENTOS D------------------------------------------------

	protected void actionPerformedBtnD1(ActionEvent e) {
		elegirAsiento(btnD1);
	}

	protected void actionPerformedBtnD2(ActionEvent e) {
		elegirAsiento(btnD2);
	}

	protected void actionPerformedBtnD3(ActionEvent e) {
		elegirAsiento(btnD3);
	}

	protected void actionPerformedBtnD4(ActionEvent e) {
		elegirAsiento(btnD4);
	}

	protected void actionPerformedBtnD5(ActionEvent e) {
		elegirAsiento(btnD5);
	}

	protected void actionPerformedBtnD6(ActionEvent e) {
		elegirAsiento(btnD6);
	}

	protected void actionPerformedBtnD7(ActionEvent e) {
		elegirAsiento(btnD7);
	}

	// --------------------------------------------------ASIENTOS E------------------------------------------------

	protected void actionPerformedBtnE1(ActionEvent e) {
		elegirAsiento(btnE1);
	}

	protected void actionPerformedBtnE2(ActionEvent e) {
		elegirAsiento(btnE2);
	}

	protected void actionPerformedBtnE3(ActionEvent e) {
		elegirAsiento(btnE3);
	}

	protected void actionPerformedBtnE4(ActionEvent e) {
		elegirAsiento(btnE4);
	}

	protected void actionPerformedBtnE5(ActionEvent e) {
		elegirAsiento(btnE5);
	}

	protected void actionPerformedBtnE6(ActionEvent e) {
		elegirAsiento(btnE6);
	}

	protected void actionPerformedBtnE7(ActionEvent e) {
		elegirAsiento(btnE7);
	}

	// --------------------------------------------------ASIENTOS F------------------------------------------------

	protected void actionPerformedBtnF1(ActionEvent e) {
		elegirAsiento(btnF1);
	}

	protected void actionPerformedBtnF2(ActionEvent e) {
		elegirAsiento(btnF2);
	}

	protected void actionPerformedBtnF3(ActionEvent e) {
		elegirAsiento(btnF3);
	}

	protected void actionPerformedBtnF4(ActionEvent e) {
		elegirAsiento(btnF4);
	}

	protected void actionPerformedBtnF5(ActionEvent e) {
		elegirAsiento(btnF5);
	}

	protected void actionPerformedBtnF6(ActionEvent e) {
		elegirAsiento(btnF6);
	}

	protected void actionPerformedBtnF7(ActionEvent e) {
		elegirAsiento(btnF7);
	}
	
	//----------------OTROS-------------
	
	protected void actionPerformedBtnContinuar(ActionEvent e) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icon/informacion.png"));
		if(RealizarReserva.codVueloRetorno == null){
		if(RegistroAcompañantes.validaAsiento == cantidadPasajeros ) {
			JOptionPane.showMessageDialog(null, "Asientos del vuelo reservados");
			RegistroAcompañantes.escogioAsiento = true;
			this.dispose();	
		}else {
			JOptionPane.showMessageDialog(null, "Aún debe escoger sus asientos","",JOptionPane.PLAIN_MESSAGE,icon);
		}
		}else{
			if(RegistroAcompañantes.validaAsiento == cantidadPasajeros &&
			   RegistroAcompañantes.validaAsientoVuelta == cantidadPasajeros) {
				JOptionPane.showMessageDialog(null, "Asientos del vuelo reservados");
				RegistroAcompañantes.escogioAsiento = true;
				this.dispose();	
			}else {
				JOptionPane.showMessageDialog(null, "Aún debe escoger sus asientos","",JOptionPane.PLAIN_MESSAGE,icon);
			}
		}
	}
	protected void actionPerformedBtnRegresar(ActionEvent e) {
		if(RegistroAcompañantes.escogioAsiento == false){
		RealizarReserva.codigosAsientos.clear();
		RealizarReserva.codigosAsientosVuelta.clear();
		RegistroAcompañantes.validaAsiento = 0;
		RegistroAcompañantes.validaAsientoVuelta = 0;}
		dispose();
	}
	
	// --------------------------------------------------METODOS------------------------------------------------

	private void elegirAsiento(JButton button) {
		
		if(cbVuelo.getSelectedIndex()==0){
			elegirAsientoDes(button,RealizarReserva.codigosAsientos,RegistroAcompañantes.validaAsiento);
		}else{
			elegirAsientoDes(button,RealizarReserva.codigosAsientosVuelta,RegistroAcompañantes.validaAsientoVuelta);
		}
		
	}
	private void elegirAsientoDes(JButton button,ArrayList<String> lista,int valida){
		if (recorridoAsientos(button,lista) == true) {//SI DESEA CANCELAR ASIENTO SELECCIONA SOBRE ASIENTO ESCOGIDO
			button.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
			lista.remove(button.getName());
			if(cbVuelo.getSelectedIndex()==0)
				RegistroAcompañantes.validaAsiento--;
			else
				RegistroAcompañantes.validaAsientoVuelta--;
		} else {//SI EL ASIENTO ESTA DISPONIBLE LO ESCOGE

			if(valida== cantidadPasajeros){
				JOptionPane.showMessageDialog(null, "Recuerda que solo has comprado " + cantidadPasajeros + " asientos");
			}else {// SI AUN LE FALTA SELECCIONAR ASIENTOS
				button.setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asientoElegido.png")));
				lista.add(button.getName());
				if(cbVuelo.getSelectedIndex()==0)
					RegistroAcompañantes.validaAsiento++;
				else
					RegistroAcompañantes.validaAsientoVuelta++;
			}

		}
	}

	private boolean recorridoAsientos(JButton jbuton,ArrayList<String> lista) {
		for (String name : lista)
			if (jbuton.getName().equals(name)) {
				return true;
			}
		return false;
	}
	
	private void bloqueaAsientos(ArrayList<String> asientos){
		
		limpiaAsientos();
		
		if(asientos.size()>0){
		Component[] componentes = contentPanel.getComponents();
		for(int i=0; i<componentes.length;i++){
			
		if (componentes[i] instanceof JButton)
			
			for(String name : asientos)
				if(((JButton)componentes[i]).getName().equals(name)){
					((JButton)componentes[i]).setEnabled(false);
					((JButton)componentes[i]).setBackground(Color.WHITE);
					((JButton)componentes[i]).setDisabledIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asientoOcupado.png")));
					((JButton)componentes[i]).setCursor(null);
				}
		}}
	}
	
	private void limpiaAsientos(){
	
		Component[] componentes = contentPanel.getComponents();
		for(int i=0; i<componentes.length;i++){
			
		if (componentes[i] instanceof JButton){
				if(((JButton)componentes[i]).getName().equals("Continuar") ||
						((JButton)componentes[i]).getName().equals("Regreso")){
					((JButton)componentes[i]).setIcon(null);
				}else{
					((JButton)componentes[i]).setEnabled(true);
					((JButton)componentes[i]).setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asiento.png")));
					((JButton)componentes[i]).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			}
		}
	}
	
	private void pintaAsientosElegidos(ArrayList<String> asientos) {
		
		Component[] componentes = contentPanel.getComponents();
		for(int i=0; i<componentes.length;i++){
			
		if (componentes[i] instanceof JButton)
			if(recorridoAsientos((JButton)componentes[i],asientos))
				((JButton)componentes[i]).setIcon(new ImageIcon(ElegirAsiento.class.getResource("/img/asientoElegido.png")));
		}
	}
	
	private void cargarDatos(Vuelo objVuelo){
		
		destino=objVuelo.getDescAeroDest()+" - "+objVuelo.getDescCiuDest();
		fecha=objVuelo.getFechSalVue();
		costo=objVuelo.getPrecioVuel();
		
		lblDestino.setText("<html>DESTINO : " + destino + "</html>");
		lblFecha.setText("<html>FECHA : " + RegistroAcompañantes.fecha(fecha) + "</html>");
		lblTotal.setText("<html>TOTAL : $" + (costo*cantidadPasajeros) + "</html>");
		
		
	}
	
	protected void actionPerformedCbVueloJComboBox(ActionEvent arg0) {
		if(cbVuelo.getSelectedIndex()==0){
			cargarDatos(gv.BuscarVuelo(RealizarReserva.codVueloIda));
			bloqueaAsientos(gb.listarAsiento(RealizarReserva.codVueloIda));
			pintaAsientosElegidos(RealizarReserva.codigosAsientos);
		}else{
			cargarDatos(gv.BuscarVuelo(RealizarReserva.codVueloRetorno));
			bloqueaAsientos(gb.listarAsiento(RealizarReserva.codVueloRetorno));
			pintaAsientosElegidos(RealizarReserva.codigosAsientosVuelta);
		}
	}
	
	
}