package mantenimientos;

import interfaces.PasajerosInterface;
import model.Acompañante;
import model.Cliente;
import util.MySQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class GestionPasajeros implements PasajerosInterface{
	/*---------------------------CLIENTE------------------------*/
	@Override
	public List<Cliente> listaCliente() {
		Connection con=MySQLConexion.getConexion();
		Statement stm;
		ResultSet rs=null;
		ArrayList<Cliente> data = new ArrayList<>();
		try {
			String SQL="SELECT numDocClie,nomUsu,contUsu,correoClie FROM CLIENTE";
			stm=con.createStatement();
			rs=stm.executeQuery(SQL);
			while(rs.next()){
				Cliente lista=new Cliente(rs.getString("numDocClie"),
						rs.getString("nomUsu"),rs.getString("contUsu"),
						rs.getString("correoClie"));
				data.add(lista);
			}
			
		} catch (Exception e) {
			System.out.println("ERROR "+e.getMessage());
			
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR LISTA CLIENTE"+e2.getMessage());
			}
		}
		
		return data;
	}
	@Override
	public String UltimoCliente() {
		String codigo="";
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		try {
			con=MySQLConexion.getConexion();
			String sql="CALL UltimoCliente()";
			cs=con.prepareCall(sql);
			rs=cs.executeQuery();
			if(rs.next()){
				codigo=rs.getString("codClie");
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
				System.out.println("ERROR CLIENTE"+e2.getMessage());
			}
		}
		return codigo;
	}
	@Override
	public void AgregarCliente(String codClie, String nomTipDocClie, String numDocClie, String nomClie, String apeClie,
			String fecNacClie, String sexoClie, String telfClie, String correoClie, String codPais, String nomUsu,
			String contUsu) {
		Connection con=null;
		CallableStatement cs=null;
		try {
			con=MySQLConexion.getConexion();
			String sql="CALL AgregarCliente(?,?,?,?,?,?,?,?,?,?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, codClie);
			cs.setString(2, nomTipDocClie);
			cs.setString(3, numDocClie);
			cs.setString(4, nomClie);
			cs.setString(5, apeClie);
			cs.setString(6, fecNacClie);
			cs.setString(7, sexoClie);
			cs.setString(8, telfClie);
			cs.setString(9, correoClie);
			cs.setString(10,  codPais);
			cs.setString(11, nomUsu);
			cs.setString(12, contUsu);
			cs.executeUpdate();
		} catch (Exception e) {
			System.out.println("ERROR AL INGRESAR CLIENTE "+e.getMessage());
		}
		
	}

	/*----------------------PASAJERO---------------------------*/
	@SuppressWarnings("static-access")
	@Override
	public Acompañante BuscarAcompañante(String CodACcomp) {
		Acompañante lista=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		try {
			con=new MySQLConexion().getConexion();
			String sql="CALL BuscarAcompañante(?)";
			
			cs=con.prepareCall(sql);
			cs.setString(1, CodACcomp);
			
			rs=cs.executeQuery();
			
			while(rs.next()){
				lista=new Acompañante(rs.getString("numDocAcomp"), rs.getString("tipDocAcomp"),
						rs.getString("nomAcomp"),rs.getString("apeAcomp"),
						rs.getString("fecNacAcomp"),rs.getString("sexoAcomp"));
			}
			return lista;
		} catch (Exception e) {
			System.out.println("ERROR ACOMPAÑANTE  "+e.getMessage());
		}finally{
			try {
				if(cs!=null){
					cs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR SQL "+e2.getMessage());
			}
		}
		return lista;
	}
	
	
	/*===============================================================================*/
	@SuppressWarnings({ "static-access", "unused" })
	public void CambiarContra(String nomUsu, String contNueva){
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		try {
			con=new MySQLConexion().getConexion();
			String sql="CALL ContraseñaCliente(?,?)";
			
			cs=con.prepareCall(sql);
			cs.setString(1, nomUsu);
			cs.setString(2, contNueva);
			
			rs=cs.executeQuery();
		} catch (Exception e) {
			System.out.println("ERROR AL CAMBIAR CONTRASEÑA: "+e.getMessage());
		}finally{
			try {
				if(cs!=null){
					cs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR SQL: "+e2.getMessage());
			}
		}
	}
	@Override
	public Cliente BuscarCliente(String codigo) {
		Cliente lista=new Cliente();
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		try {
			con=new MySQLConexion().getConexion();
			String sql="CALL BuscarCliente(?)";
			
			cs=con.prepareCall(sql);
			cs.setString(1, codigo);
			
			rs=cs.executeQuery();
			
			while(rs.next()){
				lista.setCodClie(rs.getString("codClie"));
				lista.setNomTipDocClie(rs.getString("nomTipDocClie"));
				lista.setNumDocClie(rs.getString("numDocClie"));
				lista.setNomClie(rs.getString("nomClie"));
				lista.setApeClie(rs.getString("apeClie"));
				lista.setFecNacClie(rs.getString("fecNacClie"));
				lista.setSexoClie(rs.getString("sexoClie"));
				lista.setTelfClie(rs.getString("telfClie"));
				lista.setCorreoClie(rs.getString("correoClie"));
				lista.setCodPais(rs.getString("codPais"));
				lista.setDescPais(rs.getString("P.descPais"));
				lista.setNomUsu(rs.getString("nomUsu"));
				lista.setContUsu(rs.getString("contUsu"));
			}
			
		} catch (Exception e) {
			System.out.println("ERROR CLIENTE "+e.getMessage());
		}finally{
			try {
				if(cs!=null){
					cs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR SQL "+e2.getMessage());
			}
		}
		return lista;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public Cliente BuscarClienteXusu(String usu) {
		Cliente lista=new Cliente();
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		try {
			con=new MySQLConexion().getConexion();
			String sql="CALL BuscarClienteXusu(?)";
			
			cs=con.prepareCall(sql);
			cs.setString(1, usu);
			
			rs=cs.executeQuery();
			
			while(rs.next()){
				lista.setCodClie(rs.getString("codClie"));
				lista.setNomTipDocClie(rs.getString("nomTipDocClie"));
				lista.setNumDocClie(rs.getString("numDocClie"));
				lista.setNomClie(rs.getString("nomClie"));
				lista.setApeClie(rs.getString("apeClie"));
				lista.setFecNacClie(rs.getString("fecNacClie"));
				lista.setSexoClie(rs.getString("sexoClie"));
				lista.setTelfClie(rs.getString("telfClie"));
				lista.setCorreoClie(rs.getString("correoClie"));
				lista.setCodPais(rs.getString("codPais"));
				lista.setDescPais(rs.getString("P.descPais"));
				lista.setNomUsu(rs.getString("nomUsu"));
				lista.setContUsu(rs.getString("contUsu"));
			}
			
		} catch (Exception e) {
			System.out.println("ERROR CLIENTE "+e.getMessage());
		}finally{
			try {
				if(cs!=null){
					cs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR SQL "+e2.getMessage());
			}
		}
		return lista;
	}
	
	@SuppressWarnings("static-access")
	public void EditarInfo(String nroD, String telf, String correo, String usu, String cont){
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		try {
			con=new MySQLConexion().getConexion();
			String sql="CALL EditarInfo(?,?,?,?,?)";
			
			cs=con.prepareCall(sql);
			cs.setString(1, nroD);
			cs.setString(2, telf);
			cs.setString(3, correo);
			cs.setString(4, usu);
			cs.setString(5, cont);
			
			rs=cs.executeQuery();
		} catch (Exception e) {
			System.out.println("ERROR AL CAMBIAR CONTRASEÑA: "+e.getMessage());
		}finally{
			try {
				if(cs!=null){
					cs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e2) {
				System.out.println("ERROR SQL: "+e2.getMessage());
			}
		}
	}

	
}







