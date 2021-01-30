package libreria;


import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Diseño {
	//CONFIGURACIÓN DE TAMAÑO DE UNA IMAGEN
	
	public void img(URL ruta,JLabel a){
		ImageIcon imagen=new ImageIcon(ruta);
		Icon fondo=new ImageIcon(imagen.getImage().getScaledInstance(
						a.getWidth(),a.getHeight(), Image.SCALE_DEFAULT));
		a.setIcon(fondo);
	}
	public void centrar(JLabel a){
		a.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		a.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
	}
	
	
}
