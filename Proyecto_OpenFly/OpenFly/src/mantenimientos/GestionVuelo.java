package mantenimientos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import interfaces.VueloInterface;
import util.MySQLConexion;
import model.Aeropuerto;
import model.Vuelo;

public class GestionVuelo implements VueloInterface {
	private List<Vuelo> data;
	private Vuelo vuelo;
	private Connection con;
	private CallableStatement cs;
	private ResultSet rs;
		
	/*------------------------------LISTA DE VUELOS----------------------*/
	@SuppressWarnings("static-access")
	@Override
	public List<Vuelo> listaVuelo(String vuelo) {		
		data=new  ArrayList<Vuelo>();		
			try {
				con=new MySQLConexion().getConexion();
				String sql="CALL ListarVuelo(?)";
				cs=con.prepareCall(sql);
				cs.setString(1, vuelo);
				
				rs=cs.executeQuery();
				
				while (rs.next()) {
					Vuelo lista=new Vuelo();
					lista.setCodVue(rs.getString("codVue"));
					lista.setTipVuelo(rs.getString("tipVuelo"));
					lista.setCantiAsieVue(rs.getInt("cantiAsieVue"));
					lista.setFechSalVue(rs.getString("fechSalVue"));
					lista.setHoraLlegVue(rs.getString("horaLlegVue"));
					lista.setHoraSalVue(rs.getString("horaSalVue"));
					lista.setPrecioVuel(rs.getDouble("precioVuel"));
					//DETALLE
					lista.setCodOrig(rs.getString("codOrig"));
					lista.setDescAeroOrig(rs.getString("A.descAero"));
					lista.setDescCiuorig(rs.getString("C.descCiu"));					
					lista.setCodDest(rs.getString("codDest"));
					lista.setDescAeroDest(rs.getString("AE.descAero"));
					lista.setDescCiuDest(rs.getString("CI.descCiu"));
					data.add(lista);
				}
				
			} catch (Exception e) {
				System.out.println("ERROR "+e.getMessage());
			}finally{
				try {
					if(cs!=null){
						cs.close();
					}
					if(con!=null){
						con.close();
					}
				} catch (SQLException e2) {
					System.out.println("ERROR "+e2.getMessage());
				}
			}
			return data;
		}
	/*---------------------------------BUSQUEDA DE VUELOS--------------------------------------------*/
	@SuppressWarnings("static-access")
	@Override
	public List<Vuelo>  BuscarTipVuelo(String Origen, String Destino, String tipVue, int canti, String fecha){
		data=new  ArrayList<Vuelo>();	
		
		try {
			con=new MySQLConexion().getConexion();
			String sql="CALL BuscarTipVuelo(?,?,?,?,?)";	
			cs=con.prepareCall(sql);
			
			cs.setString(1, Origen);
			cs.setString(2, Destino);
			cs.setString(3, tipVue);
			cs.setInt(4, canti);
			cs.setString(5, fecha);
			
			rs=cs.executeQuery();
			
			while(rs.next()){
				Vuelo lista=new Vuelo();
				lista.setCodVue(rs.getString("codVue"));
				lista.setTipVuelo(rs.getString("tipVuelo"));
				lista.setCantiAsieVue(rs.getInt("cantiAsieVue"));
				lista.setFechSalVue(rs.getString("fechSalVue"));
				lista.setHoraLlegVue(rs.getString("horaLlegVue"));
				lista.setHoraSalVue(rs.getString("horaSalVue"));
				lista.setPrecioVuel(rs.getDouble("precioVuel"));
				//DETALLE
				lista.setCodOrig(rs.getString("codOrig"));
				lista.setDescAeroOrig(rs.getString("A.descAero"));
				lista.setDescCiuorig(rs.getString("C.descCiu"));
				
				lista.setCodDest(rs.getString("codDest"));
				lista.setDescAeroDest(rs.getString("AE.descAero"));
				lista.setDescCiuDest(rs.getString("CI.descCiu"));
				data.add(lista);
			}
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
		}finally{
			try {
				if(cs!=null){
					cs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR "+e2.getMessage());
			}
		}	
		return data;
	}
	/*---------------------------------MODIFICAR VUELO------------------------------*/
	@SuppressWarnings("static-access")
	@Override
	public void ModificarVuelo(String codVue, String fechSalVue, String horaSalVue, String horaLlegVue,
			double precioVuel) {
		try {
			con=new MySQLConexion().getConexion();				
			String sql="CALL ModificarVuelo(?,?,?,?,?)";	
			cs=con.prepareCall(sql);
			cs.setString(1, codVue);
			cs.setString(2, fechSalVue);
			cs.setString(3, horaSalVue);
			cs.setString(4, horaLlegVue);
			cs.setDouble(5, precioVuel);
			cs.executeUpdate();
		} catch (Exception e) {
			System.out.println("ERROR MODIFICAR CLIENTE "+e.getMessage());
		}
	}
	/*------------------------------- LISTA DE NOMBRES DE AEROPUERTOS-------------------*/	
		@SuppressWarnings("static-access")
		@Override
		public List<Aeropuerto> ListarOrigen() {
			List<Aeropuerto> data=new ArrayList<Aeropuerto>();
			
			try {
				con=new MySQLConexion().getConexion();				
				String sql="CALL ListarOrigen()";				
				cs=con.prepareCall(sql);
				rs=cs.executeQuery();
				Aeropuerto a=null;
				
				 while(rs.next()){
					 a=new Aeropuerto();
					 a.setCodigo(rs.getString("codOrig"));
					 a.setDescAero(rs.getString("descAero"));
					 a.setDescCiu(rs.getString("descCiu"));
					 data.add(a);
				 }
			} catch (Exception e) {
				System.out.println("ERROR "+e.getMessage());
			}finally{
				try {
						if(cs!=null){
							cs.close();
						}
						if(con!=null){
							con.close();
						}
					} catch (SQLException e2) {
						System.out.println("ERROR "+e2.getMessage());
					}
			}
			return data;
		}
		@SuppressWarnings("static-access")
		@Override
		public List<Aeropuerto> ListarDestino() {
			List<Aeropuerto> data=new ArrayList<Aeropuerto>();
			
			try {
				con=new MySQLConexion().getConexion();
				
				String sql="CALL ListarDestino()";
				
				cs=con.prepareCall(sql);
				rs=cs.executeQuery();
				Aeropuerto a=null;
				
				 while(rs.next()){
					 a=new Aeropuerto();
					 a.setCodigo(rs.getString("codDest"));
					 a.setDescAero(rs.getString("descAero"));
					 a.setDescCiu(rs.getString("descCiu"));
					 data.add(a);
				 }
			} catch (Exception e) {
				System.out.println("ERROR "+e.getMessage());
			}finally{
				try {
						if(cs!=null){
							cs.close();
						}
						if(con!=null){
							con.close();
						}
					} catch (SQLException e2) {
						System.out.println("ERROR "+e2.getMessage());
					}
			}
			return data;
		}
		/*-------------------------------OBTENER DATOS DEL VUELO------------------------------------*/
		@SuppressWarnings("static-access")
		@Override
		public Vuelo BuscarVuelo(String codVuelo) {	
			try {
				con=new MySQLConexion().getConexion();
				
				String sql="CALL BuscarVuelo(?);";
				
				cs=con.prepareCall(sql);
				cs.setString(1, codVuelo);
				
				rs=cs.executeQuery();
				
				 while(rs.next()){
					vuelo=new Vuelo();
					vuelo.setCodVue(rs.getString("codVue"));
					vuelo.setTipVuelo(rs.getString("tipVuelo"));
					vuelo.setCantiAsieVue(rs.getInt("cantiAsieVue"));
					vuelo.setFechSalVue(rs.getString("fechSalVue"));
					vuelo.setHoraLlegVue(rs.getString("horaLlegVue"));
					vuelo.setHoraSalVue(rs.getString("horaSalVue"));
					vuelo.setPrecioVuel(rs.getDouble("precioVuel"));
					//DETALLE
					vuelo.setCodOrig(rs.getString("codOrig"));
					vuelo.setDescAeroOrig(rs.getString("A.descAero"));
					vuelo.setDescCiuorig(rs.getString("C.descCiu"));
					
					vuelo.setCodDest(rs.getString("codDest"));
					vuelo.setDescAeroDest(rs.getString("AE.descAero"));
					vuelo.setDescCiuDest(rs.getString("CI.descCiu"));
				 }
			} catch (Exception e) {
				System.out.println("ERROR "+e.getMessage());
			}finally{
				try {
						if(cs!=null){
							cs.close();
						}
						if(con!=null){
							con.close();
						}
					} catch (SQLException e2) {
						System.out.println("ERROR "+e2.getMessage());
					}
			}
			return vuelo;
		}
		/*-------------------------------INSERTAR VUELOS -------------------------------*/
		@Override
		public void InsertarVuelo(String CODIGOVUELO ,String CODIGOORIGEN,String CODIGODESTINO,String TIPO,
				int CANTIDADASIENTO, String FECHA,String HORASALIDA,String HORALLEGADA,double PRECIO){
			try {
				
				con=MySQLConexion.getConexion();
				String sql="CALL InsertarVuelo(?,?,?,?,?,?,?,?,?)";
				cs=con.prepareCall(sql);
				cs.setString(1, CODIGOVUELO);
				cs.setString(2, CODIGOORIGEN);
				cs.setString(3, CODIGODESTINO);
				cs.setString(4, TIPO);
				cs.setInt(5, CANTIDADASIENTO);
				cs.setString(6, FECHA);
				cs.setString(7, HORASALIDA);
				cs.setString(8, HORALLEGADA);
				cs.setDouble(9, PRECIO);
				
				cs.executeUpdate();
			} catch (Exception e) {
				System.out.println("ERROR "+e.getMessage());
			}
		}
		@Override
		public String buscaUltimoVuelo() {
			String codigo = "";
			String SQL = "call BuscarUltimoVuelo()";
								
			try {
				con = MySQLConexion.getConexion();
				cs = con.prepareCall(SQL);
				ResultSet rs =  cs.executeQuery();
									
				if (rs.next()){
					codigo = rs.getString("codVue");
				}
									
				} catch (Exception e) {
					System.out.println("ERROR VUELO"+e.getMessage());
						
				}finally{
					try {
						if(cs!=null){
							cs.close();
						}
						if(con!=null){
							con.close();
						}
					} catch (SQLException e2) {
						System.out.println("ERROR ULTIMA VUELO"+e2.getMessage());
				}
			}
								
			return codigo;
		}
		
		
}
