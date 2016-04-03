package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements IDBConnection{
	
	public static DBConnection instance;
	public Connection con;
	private DBConnection() throws SQLException, ClassNotFoundException {
		try { 
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=master;namedPipe=true","sa","lucks123");
			System.out.println("Conexao ok");
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
			} catch (SQLException e) {
			e.printStackTrace();
			} catch (Exception e) { 
			e.printStackTrace();
			}
	}
	
	public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null)
			instance = new DBConnection();
		return instance;
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		return con;
	}

	@Override
	public void fechaConexao() throws SQLException {
		try {
			if (con != null)
				con.close();
			con = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
