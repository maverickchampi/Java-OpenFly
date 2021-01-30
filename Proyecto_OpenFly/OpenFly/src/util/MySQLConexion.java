package util;
 
import java.sql.*;

public class MySQLConexion {
	
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/bd_openfly";
	private static String usr="root";
	private static String psw="MySQL2020";
	
	private static Connection con=null;
		
	public static Connection getConexion(){
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, usr, psw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR-> Driver no instalado");
		}catch(SQLException e){
			System.out.println("ERROR -> Conexión con MySQL");
		}
		return con;
	}
}

