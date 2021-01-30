package interfaces;

import java.util.ArrayList;
import model.Acompañante;
import model.Boleto;
import model.Promocion;

public interface BoletaInterface {
	/*---------------GESTION DE PROMOCION--------------*/
	public ArrayList<Promocion> ListarPromociones();
	
	public Promocion BuscarPromociones(String COD);
	
	public void InsertarPromociones(String COD,String DESCRIP,double DESCUENTO,
					String FECHAINICIO,String FECHAFINAL);
	
	public void ModificarPromociones(String COD,String DESCRIP,double DESCUENTO,
			String FECHAINICIO,String FECHAFINAL);
	
	public void EliminarPromociones(String COD);
	
	/*---------------REGISTRO DE FACTURA---------------------*/
	public void insertarAcompañante(Acompañante ObjA);
	
	public void insertarBoleto(String codB, String codC,String codV,String codVV ,int asi);
	
	public void insertarDetalleBoleta(String codB , String numDocAcomp);
	
	public void insertarFactura(String numF, String codB, String fechEm,String numTarj, 
			double importe, double igv, double total,String codDes);
	
	public String buscaUltimaBoleta();
	
	public String buscaUltimaFactura();
	
	public void insertarAsiento(String codB,String codV, String nomA);
	
	public ArrayList<String> listarAsiento(String codigo);

	public ArrayList<Boleto> ListarBoletos();
	
	
}
