package interfaces;

import java.util.List;

import model.Aeropuerto;
import model.Vuelo;

public interface VueloInterface {
	/*-----------------PASO 1---------------------*/ 
	public List<Vuelo> listaVuelo(String vuelo);
		
	public List<Vuelo> BuscarTipVuelo(String Origen,String Destino,String tipVue, int canti, String fecha);
		
	//DATOS DEL AVION
	public Vuelo BuscarVuelo(String codVuelo);
	
	//LISTA DE ORIGEN Y DESTINO
	public List<Aeropuerto> ListarOrigen();
	
	public List<Aeropuerto> ListarDestino();
	
	//
	public void InsertarVuelo(String CODIGOVUELO ,String CODIGOORIGEN,String CODIGODESTINO,String TIPO,
			int CANTIDADASIENTO, String FECHA,String HORASALIDA,String HORALLEGADA,double PRECIO);
	public String buscaUltimoVuelo();
	
	//MODIFICAR VUELOS
	public void ModificarVuelo(String codVue,String fechSalVue,String horaSalVue,
			String horaLlegVue, double precioVuel);
}
