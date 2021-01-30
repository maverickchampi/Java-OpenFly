package mantenimientos;


import java.sql.*;
import java.util.ArrayList;
import interfaces.BoletaInterface;
import model.Acompañante;
import model.Boleto;
import model.Promocion;
import util.MySQLConexion;

public class GestionBoleta implements BoletaInterface{
	
	private CallableStatement Stmt;
	private ResultSet rs;
	private Connection Cn ;
	
	/*---------------GESTION DE PROMOCION--------------*/
	ArrayList<Promocion> data;
	Promocion objProm;
	@Override
	public ArrayList<Promocion> ListarPromociones() {
		data=new ArrayList<>();
		
		try {
			Cn=MySQLConexion.getConexion();
			String sql="CALL ListarPromociones()";
			
			Stmt=Cn.prepareCall(sql);			
			rs=Stmt.executeQuery();
			
			while (rs.next()) {
				objProm=new Promocion();
				objProm.setCod_Promo(rs.getString("cod_Promo"));
				objProm.setPorcentaje_desc(rs.getDouble("porcentaje_desc"));
				objProm.setDescrip_Promo(rs.getString("descrip_Promo"));
				objProm.setFechaIni_Promo(rs.getString("fechaIni_Promo"));
				objProm.setFechaFin_Promo(rs.getString("fechaFin_Promo"));
				data.add(objProm);
			}
			
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
			
		}finally{
			try {
				if(Stmt!=null){
					Stmt.close();
				}
				if(Cn!=null){
					Cn.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR "+e2.getMessage());
			}
		}
		return data;
	}
	
	@Override
	public Promocion BuscarPromociones(String COD) {
		
		try {
			Cn=MySQLConexion.getConexion();
			String sql="CALL BuscarPromociones(?)";
		
			Stmt=Cn.prepareCall(sql);	
			Stmt.setString(1, COD);
			
			rs=Stmt.executeQuery();
			while(rs.next()){
				objProm=new Promocion();
				objProm.setCod_Promo(rs.getString("cod_Promo"));
				objProm.setPorcentaje_desc(rs.getDouble("porcentaje_desc"));
				objProm.setDescrip_Promo(rs.getString("descrip_Promo"));
				objProm.setFechaIni_Promo(rs.getString("fechaIni_Promo"));
				objProm.setFechaFin_Promo(rs.getString("fechaFin_Promo"));
			}
			
			
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
			
		}finally{
			try {
				if(Stmt!=null){
					Stmt.close();
				}
				if(Cn!=null){
					Cn.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR BUSCAR PROMOCIÓN"+e2.getMessage());
			}
		}
		return objProm;
	}


	@Override
	public void InsertarPromociones(String COD, String DESCRIP, double DESCUENTO, String FECHAINICIO,
			String FECHAFINAL) {
		try {
			Cn=MySQLConexion.getConexion();
			String sql="CALL InsertarPromociones(?,?,?,?,?)";
			
			Stmt=Cn.prepareCall(sql);	
			Stmt.setString(1, COD);
			Stmt.setString(2, DESCRIP);
			Stmt.setDouble(3,  DESCUENTO);
			Stmt.setString(4, FECHAINICIO);
			Stmt.setString(5, FECHAFINAL);
			
			Stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("ERROR INGRESAR PROMOCIONES"+e.getMessage());
			
		}
		
	}

	@Override
	public void ModificarPromociones(String COD, String DESCRIP, double DESCUENTO, String FECHAINICIO,
			String FECHAFINAL) {
		try {
			Cn=MySQLConexion.getConexion();
			String sql="CALL ModificarPromociones(?,?,?,?,?)";
			
			Stmt=Cn.prepareCall(sql);	
			Stmt.setString(1, COD);
			Stmt.setString(2, DESCRIP);
			Stmt.setDouble(3,  DESCUENTO);
			Stmt.setString(4, FECHAINICIO);
			Stmt.setString(5, FECHAFINAL);
			
			Stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("ERROR MODIFICAR PROMOCIÓN"+e.getMessage());
			
		}
		
	}

	@Override
	public void EliminarPromociones(String COD) {
		try {
			Cn=MySQLConexion.getConexion();
			String sql="CALL EliminarPromociones(?)";
			
			Stmt=Cn.prepareCall(sql);	
			Stmt.setString(1, COD);
			
			Stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("ERROR ELIMINAR PROMOCION  "+e.getMessage());
		}
	}
	/*---------------REGISTRO DE FACTURA---------------------*/
	//INSERTAR ACOMPAÑANTE
	@Override
	public void insertarAcompañante(Acompañante ObjA) {
		String SQL = "call insertarAcompañante(?,?,?,?,?,?)";
		try {
			 	Cn = MySQLConexion.getConexion();
			    Stmt = Cn.prepareCall(SQL);
				Stmt.setString(1, ObjA.getNumDocAcomp());
				Stmt.setString(2, ObjA.getTipDocAcomp());
				Stmt.setString(3, ObjA.getNomAcomp());
				Stmt.setString(4, ObjA.getApeAcomp());
				Stmt.setString(5, ObjA.getFecNacAcomp());
				Stmt.setString(6, ObjA.getSexoAcomp());
				Stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
			
		}
	}
	
	
	//INSERTAR BOLETO
	@Override
	public void insertarBoleto(String codB, String codC,String codV,String codVV ,int asi) {
		String SQL = "call insertarBoleto(?,?,?,?,?)";
		try {
				Cn = MySQLConexion.getConexion();
				Stmt = Cn.prepareCall(SQL);
				Stmt.setString(1, codB);
				Stmt.setString(2, codC);
				Stmt.setString(3, codV);
				Stmt.setString(4, codVV);
				Stmt.setInt(5, asi);
				Stmt.executeUpdate();
				
				
		} catch (Exception e) {
			System.out.println("ERROR INSERTAR BOLETA "+e.getMessage());
			
		}
	}
	
	//INSERTAR DETALLE BOLETA
	@Override
	public void insertarDetalleBoleta(String codB , String numDocAcomp) {
		String SQL = "call insertarDetalleBoleto(?,?)";
		try {
			Cn = MySQLConexion.getConexion();
			Stmt = Cn.prepareCall(SQL);
			Stmt.setString(1, codB);
			Stmt.setString(2, numDocAcomp);
			Stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ERROR DETALLE BOLETA "+e.getMessage());
			
		}
	}
			
	//INSERTAR FACTURA
	@Override
	public void insertarFactura(String numF, String codB, String fechEm,String numTarj, double importe,
								double igv, double total,String codDes) {
		String SQL = "call insertarFactura(?,?,?,?,?,?,?,?)";
		try {
			Cn = MySQLConexion.getConexion();
			Stmt = Cn.prepareCall(SQL);
			Stmt.setString(1, numF);
			Stmt.setString(2, codB);
			Stmt.setString(3, fechEm);
			Stmt.setString(4, numTarj);
			Stmt.setDouble(5, importe);
			Stmt.setDouble(6, igv);
			Stmt.setDouble(7, total);
			Stmt.setString(8, codDes);
			Stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ERROR FACTURA "+e.getMessage());
			
		}
	}
		
	//BUSCA ULTIMA BOLETA
	@Override
	public String buscaUltimaBoleta(){
			String codigo = "";
			String SQL = "call buscarUltimaBoleto";
					
		try {
			Cn = MySQLConexion.getConexion();
			Stmt = Cn.prepareCall(SQL);
			ResultSet rs =  Stmt.executeQuery();
						
			if (rs.next()){
				codigo = rs.getString("codBoleto");
			}
						
		} catch (Exception e) {
			System.out.println("ERROR ULTIMA BOLETA"+e.getMessage());
			
		}finally{
			try {
				if(Stmt!=null){
					Stmt.close();
				}
				if(Cn!=null){
					Cn.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR ULTIMA  BOLETO"+e2.getMessage());
			}
		}
					
		return codigo;
	}	
	//BUSCA ULTIMA FACTURA
	@Override
	public String buscaUltimaFactura(){
		String codigo = "";
		String SQL = "call buscarUltimaFactura";
					
		try {
			Cn = MySQLConexion.getConexion();
			Stmt = Cn.prepareCall(SQL);
			ResultSet rs =  Stmt.executeQuery();
						
			if (rs.next()){
				codigo = rs.getString("numFac");
			}
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
			
		}finally{
			try {
				if(Stmt!=null){
					Stmt.close();
				}
				if(Cn!=null){
					Cn.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR ULTIMA FACTURA"+e2.getMessage());
			}
		}
					
			return codigo;
	}
				
	//INSERTAR ASIENTO
	@Override
	public void insertarAsiento(String codB,String codV, String nomA) {
		String SQL = "call insertarAsiento(?,?,?)";
		try {
			Cn = MySQLConexion.getConexion();
			Stmt = Cn.prepareCall(SQL);
			Stmt.setString(1, codB);
			Stmt.setString(2, codV);
			Stmt.setString(3, nomA);
			Stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());			
		}
	}
	
	@Override
	public ArrayList<String> listarAsiento(String codigo) {
		ArrayList<String> asientos = new ArrayList<String>();
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
					
		try {
			con=MySQLConexion.getConexion();
			String sql="call listarAsiento(?)";
			cs=con.prepareCall(sql);
			cs.setString(1, codigo);
			rs=cs.executeQuery();
						
			while(rs.next()){
				asientos.add(rs.getString("nomAsiento"));
			}
						
		} catch (Exception e) {
			System.out.println("ERROR "+e);
			}finally{
				try {
					if(cs!=null){
					cs.close();
				}
				if(con!=null){
					con.close();
				}
				} catch (SQLException e2) {
					System.out.println("ERROR ASIENTO"+e2);
				}
			}
	return asientos;
	}
	/*LISTAR BOLETOS*/
	ArrayList<Boleto> data1;
	Boleto objBol;
	@Override
	public ArrayList<Boleto> ListarBoletos() {
		data1=new ArrayList<>();
		
		try {
			Cn=MySQLConexion.getConexion();
			String sql="CALL ListarBoletos()";
			
			Stmt=Cn.prepareCall(sql);			
			rs=Stmt.executeQuery();
			
			while (rs.next()) {
				objBol=new Boleto();
				objBol.setCodBol(rs.getString("codBoleto"));
				objBol.setCodUsu(rs.getString("codClie"));
				objBol.setCodVI(rs.getString("codVue"));
				objBol.setCodVV(rs.getString("codVV"));
				objBol.setAsientos(rs.getInt("cantiAsienReser"));
				data1.add(objBol);
			}
			
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
			
		}finally{
			try {
				if(Stmt!=null){
					Stmt.close();
				}
				if(Cn!=null){
					Cn.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR "+e2.getMessage());
			}
		}
		return data1;
	}
	



}
