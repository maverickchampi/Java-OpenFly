package vista_usuario;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.border.LineBorder;
import mantenimientos.GestionBoleta;
import model.Promocion;
import vista_reserva.RegistroAcompañantes;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Promociones extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static  JButton btnSalir;
	private JLabel lblPromo1;
	private JButton btnCopiar1;
	private JLabel lblPromo2;
	private JLabel lblPromo3;
	private JLabel label_1;
	private JButton btnCopiar2;
	private JButton btnCopiar3;
	private JButton btnCopiar4;
	private JButton btnCopiar5;
	private JButton btnCopiar6;
	private JLabel lblPromo4;
	private JLabel lblPromo5;
	private JLabel lblPromo6;
	private JTextField cod1;
	private JTextField cod2;
	private JTextField cod3;
	private JTextField cod4;
	private JTextField cod5;
	private JTextField cod6;
	private JTextArea textArea1;
	private JTextArea textArea2;
	private JTextArea textArea3;
	private JTextArea textArea4;
	private JTextArea textArea5;
	private JTextArea textArea6;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel lblFondo;
	public static JButton btnSalir2;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Promociones frame = new Promociones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void imagenLabel(String url, JLabel label){
		ImageIcon logo=new ImageIcon(url);
		Image imgLogo=logo.getImage();
		Image imgLogoReduc=imgLogo.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imgLogoReduc));
	}
	
	public void iconoBoton(String url, JButton boton){
		ImageIcon logo=new ImageIcon(url);
		Image imgLogo=logo.getImage();
		Image imgLogoReduc=imgLogo.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imgLogoReduc));
	}
	
	public Promociones() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Promociones.class.getResource("/icon/logoOP.png")));
		setTitle("OpenFly\u00AE - PROMOCIONES");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 590, 590);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
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
			btnSalir = new JButton("Salir");
			btnSalir.setForeground(new Color(0, 0, 0));
			btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSalir.addActionListener(this);
			{
				btnSalir2 = new JButton("Salir");
				btnSalir2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						do_btnSalir2_actionPerformed(arg0);
					}
				});
				btnSalir2.setVisible(false);
				btnSalir2.setEnabled(false);
				btnSalir2.setBounds(210, 516, 160, 25);
				contentPane.add(btnSalir2);
			}
			{
				label1 = new JLabel("");
				label1.setForeground(new Color(128, 0, 0));
				label1.setHorizontalAlignment(SwingConstants.CENTER);
				label1.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
				label1.setBounds(25, 210, 160, 25);
				contentPane.add(label1);
			}
			{
				label2 = new JLabel("");
				label2.setHorizontalAlignment(SwingConstants.CENTER);
				label2.setForeground(new Color(128, 0, 0));
				label2.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
				label2.setBounds(210, 210, 160, 25);
				contentPane.add(label2);
			}
			{
				label3 = new JLabel("");
				label3.setHorizontalAlignment(SwingConstants.CENTER);
				label3.setForeground(new Color(128, 0, 0));
				label3.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
				label3.setBounds(395, 210, 160, 25);
				contentPane.add(label3);
			}
			{
				label4 = new JLabel("");
				label4.setHorizontalAlignment(SwingConstants.CENTER);
				label4.setForeground(new Color(128, 0, 0));
				label4.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
				label4.setBounds(25, 455, 160, 25);
				contentPane.add(label4);
			}
			{
				label5 = new JLabel("");
				label5.setHorizontalAlignment(SwingConstants.CENTER);
				label5.setForeground(new Color(128, 0, 0));
				label5.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
				label5.setBounds(210, 455, 160, 25);
				contentPane.add(label5);
			}
			{
				label6 = new JLabel("");
				label6.setHorizontalAlignment(SwingConstants.CENTER);
				label6.setForeground(new Color(128, 0, 0));
				label6.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
				label6.setBounds(395, 455, 160, 25);
				contentPane.add(label6);
			}
			{
				textArea6 = new JTextArea();
				textArea6.setWrapStyleWord(true);
				textArea6.setLineWrap(true);
				textArea6.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
				textArea6.setEditable(false);
				textArea6.setBackground(Color.decode("#ffab5a"));
				textArea6.setBounds(433, 288, 100, 55);
				contentPane.add(textArea6);
			}
			{
				textArea5 = new JTextArea();
				textArea5.setWrapStyleWord(true);
				textArea5.setLineWrap(true);
				textArea5.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
				textArea5.setEditable(false);
				textArea5.setBackground(Color.decode("#fff25a"));
				textArea5.setBounds(248, 288, 100, 55);
				contentPane.add(textArea5);
			}
			{
				textArea4 = new JTextArea();
				textArea4.setWrapStyleWord(true);
				textArea4.setLineWrap(true);
				textArea4.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
				textArea4.setEditable(false);
				textArea4.setBackground(Color.decode("#5aff5d"));
				textArea4.setBounds(63, 288, 100, 55);
				contentPane.add(textArea4);
			}
			{
				textArea3 = new JTextArea();
				textArea3.setWrapStyleWord(true);
				textArea3.setLineWrap(true);
				textArea3.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
				textArea3.setEditable(false);
				textArea3.setBackground(Color.decode("#5a9cff"));
				textArea3.setBounds(433, 43, 100, 55);
				contentPane.add(textArea3);
			}
			{
				textArea2 = new JTextArea();
				textArea2.setWrapStyleWord(true);
				textArea2.setLineWrap(true);
				textArea2.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
				textArea2.setEditable(false);
				textArea2.setBackground(Color.decode("#a75aff"));
				textArea2.setBounds(248, 43, 100, 55);
				contentPane.add(textArea2);
			}
			{
				textArea1 = new JTextArea();
				textArea1.setWrapStyleWord(true);
				textArea1.setLineWrap(true);
				textArea1.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
				textArea1.setEditable(false);
				textArea1.setBounds(63, 43, 100, 55);
				textArea1.setBackground(Color.decode("#ff5a59"));
				contentPane.add(textArea1);
			}
			btnSalir.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnSalir.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			btnSalir.setBackground(Color.WHITE);
			btnSalir.setBounds(210, 515, 160, 25);
			contentPane.add(btnSalir);
		}
		{
			lblPromo1 = new JLabel("");
			lblPromo1.setBounds(25, 25, 160, 160);
			contentPane.add(lblPromo1);
		}
		{
			btnCopiar1 = new JButton("");
			btnCopiar1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCopiar1.addActionListener(this);
			btnCopiar1.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnCopiar1.setBackground(Color.WHITE);
			btnCopiar1.setBounds(155, 185, 30, 25);
			contentPane.add(btnCopiar1);
		}
		{
			lblPromo2 = new JLabel("");
			lblPromo2.setBounds(210, 25, 160, 160);
			contentPane.add(lblPromo2);
		}
		{
			lblPromo3 = new JLabel("");
			lblPromo3.setBounds(395, 25, 160, 160);
			contentPane.add(lblPromo3);
		}
		{
			label_1 = new JLabel("");
			label_1.setBounds(410, 25, 160, 160);
			contentPane.add(label_1);
		}
		{
			btnCopiar2 = new JButton("");
			btnCopiar2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCopiar2.addActionListener(this);
			btnCopiar2.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnCopiar2.setBackground(Color.WHITE);
			btnCopiar2.setBounds(340, 185, 30, 25);
			contentPane.add(btnCopiar2);
		}
		{
			btnCopiar3 = new JButton("");
			btnCopiar3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCopiar3.addActionListener(this);
			btnCopiar3.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnCopiar3.setBackground(Color.WHITE);
			btnCopiar3.setBounds(525, 185, 30, 25);
			contentPane.add(btnCopiar3);
		}
		{
			btnCopiar4 = new JButton("");
			btnCopiar4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCopiar4.addActionListener(this);
			btnCopiar4.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnCopiar4.setBackground(Color.WHITE);
			btnCopiar4.setBounds(155, 430, 30, 25);
			contentPane.add(btnCopiar4);
		}
		{
			btnCopiar5 = new JButton("");
			btnCopiar5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCopiar5.addActionListener(this);
			btnCopiar5.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnCopiar5.setBackground(Color.WHITE);
			btnCopiar5.setBounds(340, 430, 30, 25);
			contentPane.add(btnCopiar5);
		}
		{
			btnCopiar6 = new JButton("");
			btnCopiar6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnCopiar6.addActionListener(this);
			btnCopiar6.setBorder(new LineBorder(new Color(0, 0, 0)));
			btnCopiar6.setBackground(Color.WHITE);
			btnCopiar6.setBounds(525, 430, 30, 25);
			contentPane.add(btnCopiar6);
		}
		{
			lblPromo4 = new JLabel("");
			lblPromo4.setBounds(25, 270, 160, 160);
			contentPane.add(lblPromo4);
		}
		{
			lblPromo5 = new JLabel("");
			lblPromo5.setBounds(210, 270, 160, 160);
			contentPane.add(lblPromo5);
		}
		{
			lblPromo6 = new JLabel("");
			lblPromo6.setBounds(395, 270, 160, 160);
			contentPane.add(lblPromo6);
		}
		{
			cod1 = new JTextField();
			cod1.setHorizontalAlignment(SwingConstants.CENTER);
			cod1.setEditable(false);
			cod1.setBorder(new LineBorder(Color.BLACK));
			cod1.setBounds(25, 185, 130, 25);
			contentPane.add(cod1);
			cod1.setColumns(10);
		}
		{
			cod2 = new JTextField();
			cod2.setHorizontalAlignment(SwingConstants.CENTER);
			cod2.setEditable(false);
			cod2.setColumns(10);
			cod2.setBorder(new LineBorder(Color.BLACK));
			cod2.setBounds(210, 185, 130, 25);
			contentPane.add(cod2);
		}
		{
			cod3 = new JTextField();
			cod3.setHorizontalAlignment(SwingConstants.CENTER);
			cod3.setEditable(false);
			cod3.setColumns(10);
			cod3.setBorder(new LineBorder(Color.BLACK));
			cod3.setBounds(395, 185, 130, 25);
			contentPane.add(cod3);
		}
		{
			cod4 = new JTextField();
			cod4.setHorizontalAlignment(SwingConstants.CENTER);
			cod4.setEditable(false);
			cod4.setColumns(10);
			cod4.setBorder(new LineBorder(Color.BLACK));
			cod4.setBounds(25, 430, 130, 25);
			contentPane.add(cod4);
		}
		{
			cod5 = new JTextField();
			cod5.setHorizontalAlignment(SwingConstants.CENTER);
			cod5.setEditable(false);
			cod5.setColumns(10);
			cod5.setBorder(new LineBorder(Color.BLACK));
			cod5.setBounds(210, 430, 130, 25);
			contentPane.add(cod5);
		}
		{
			cod6 = new JTextField();
			cod6.setHorizontalAlignment(SwingConstants.CENTER);
			cod6.setEditable(false);
			cod6.setColumns(10);
			cod6.setBorder(new LineBorder(Color.BLACK));
			cod6.setBounds(395, 430, 130, 25);
			contentPane.add(cod6);
		}
		{
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 584, 561);
			contentPane.add(lblFondo);
		}
		imagenLabel("src/img/paneOferta1.png",lblPromo1);
		imagenLabel("src/img/paneOferta2.png",lblPromo2);
		imagenLabel("src/img/paneOferta3.png",lblPromo3);
		imagenLabel("src/img/paneOferta4.png",lblPromo4);
		imagenLabel("src/img/paneOferta5.png",lblPromo5);
		imagenLabel("src/img/paneOferta6.png",lblPromo6);
		iconoBoton("src/img/icoSalir.png", btnSalir);
		iconoBoton("src/img/copy.png", btnCopiar1);
		iconoBoton("src/img/copy.png", btnCopiar2);
		iconoBoton("src/img/copy.png", btnCopiar3);
		iconoBoton("src/img/copy.png", btnCopiar4);
		iconoBoton("src/img/copy.png", btnCopiar5);
		iconoBoton("src/img/copy.png", btnCopiar6);
		imagenLabel("src/img/fondo.jpg",lblFondo);
		GestionBoleta gb=new GestionBoleta();
		Promocion objPromo=new Promocion();
		ArrayList<Promocion> data;
		data=gb.ListarPromociones();
		
		
		for (int i = 0; i < data.size(); i++) {
			objPromo=data.get(i);
			Object Lista[]=new Object[3];
			Lista[0]=objPromo.getCod_Promo();
			Lista[1]=objPromo.getDescrip_Promo();
			//Lista[2]=df.format(objPromo.getPorcentaje_desc());
			//Lista[2]=RegistroAcompañantes.fecha(objPromo.getFechaIni_Promo());
			Lista[2]=RegistroAcompañantes.fecha(objPromo.getFechaFin_Promo());
			//model.addRow(Lista);
			
			
			if(i==0){
				cod1.setText(Lista[0].toString());
				textArea1.setText(Lista[1].toString());
				label1.setText("Termina el: "+Lista[2].toString());
			}
			if(i==1){
				cod2.setText(Lista[0].toString());
				textArea2.setText(Lista[1].toString());
				label2.setText("Termina el: "+Lista[2].toString());
			}
			if(i==2){
				cod3.setText(Lista[0].toString());
				textArea3.setText(Lista[1].toString());
				label3.setText("Termina el: "+Lista[2].toString());
			}
			if(i==3){
				cod4.setText(Lista[0].toString());
				textArea4.setText(Lista[1].toString());
				label4.setText("Termina el: "+Lista[2].toString());
			}
			if(i==4){
				cod5.setText(Lista[0].toString());
				textArea5.setText(Lista[1].toString());
				label5.setText("Termina el: "+Lista[2].toString());
			}
			if(i==5){
				cod6.setText(Lista[0].toString());
				textArea6.setText(Lista[1].toString());
				label6.setText("Termina el: "+Lista[2].toString());
			}
			
			
			
		}
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnSalir) {
			actionPerformedBtnSalir(arg0);
		}
		if (arg0.getSource() == btnCopiar6) {
			actionPerformedBtnCopiar6(arg0);
		}
		if (arg0.getSource() == btnCopiar5) {
			actionPerformedBtnCopiar5(arg0);
		}
		if (arg0.getSource() == btnCopiar4) {
			actionPerformedBtnCopiar4(arg0);
		}
		if (arg0.getSource() == btnCopiar3) {
			actionPerformedBtnCopiar3(arg0);
		}
		if (arg0.getSource() == btnCopiar2) {
			actionPerformedBtnCopiar2(arg0);
		}
		if (arg0.getSource() == btnCopiar1) {
			actionPerformedBtnCopiar1(arg0);
		}
	}
	protected void actionPerformedBtnCopiar1(ActionEvent arg0) {
		StringSelection selec = new StringSelection(cod1.getText());
		Clipboard port = Toolkit.getDefaultToolkit().getSystemClipboard();
		port.setContents(selec, null);
		JOptionPane.showMessageDialog(null, "Código copiado.", "Ok.", JOptionPane.INFORMATION_MESSAGE);
	}
	protected void actionPerformedBtnCopiar2(ActionEvent arg0) {
		StringSelection selec = new StringSelection(cod2.getText());
		Clipboard port = Toolkit.getDefaultToolkit().getSystemClipboard();
		port.setContents(selec, null);
		JOptionPane.showMessageDialog(null, "Código copiado.", "Ok.", JOptionPane.INFORMATION_MESSAGE);
	}
	protected void actionPerformedBtnCopiar3(ActionEvent arg0) {
		StringSelection selec = new StringSelection(cod3.getText());
		Clipboard port = Toolkit.getDefaultToolkit().getSystemClipboard();
		port.setContents(selec, null);
		JOptionPane.showMessageDialog(null, "Código copiado.", "Ok.", JOptionPane.INFORMATION_MESSAGE);
	}
	protected void actionPerformedBtnCopiar4(ActionEvent arg0) {
		StringSelection selec = new StringSelection(cod4.getText());
		Clipboard port = Toolkit.getDefaultToolkit().getSystemClipboard();
		port.setContents(selec, null);
		JOptionPane.showMessageDialog(null, "Código copiado.", "Ok.", JOptionPane.INFORMATION_MESSAGE);
	}
	protected void actionPerformedBtnCopiar5(ActionEvent arg0) {
		StringSelection selec = new StringSelection(cod5.getText());
		Clipboard port = Toolkit.getDefaultToolkit().getSystemClipboard();
		port.setContents(selec, null);
		JOptionPane.showMessageDialog(null, "Código copiado.", "Ok.", JOptionPane.INFORMATION_MESSAGE);
	}
	protected void actionPerformedBtnCopiar6(ActionEvent arg0) {
		StringSelection selec = new StringSelection(cod6.getText());
		Clipboard port = Toolkit.getDefaultToolkit().getSystemClipboard();
		port.setContents(selec, null);
		JOptionPane.showMessageDialog(null, "Código copiado.", "Ok.", JOptionPane.INFORMATION_MESSAGE);
	}
	protected void actionPerformedBtnSalir(ActionEvent arg0) {
		MenuUsuario mu=new MenuUsuario();
		mu.toFront();
		mu.setVisible(true);
		this.dispose();
	}
	protected void do_btnSalir2_actionPerformed(ActionEvent arg0) {
		this.dispose();
	}
}
