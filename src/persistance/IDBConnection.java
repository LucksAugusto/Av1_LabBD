package persistance;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBConnection {
	public Connection getConnection() throws SQLException;
	public void fechaConexao() throws SQLException;
}
