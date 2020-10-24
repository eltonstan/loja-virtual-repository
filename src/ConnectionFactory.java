import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private DataSource dataSource;

	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("HP1sgay0");
		
		comboPooledDataSource.setMaxPoolSize(15);

		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexao() throws SQLException {

		// usando o c3po que controla o pool de conexões
		return dataSource.getConnection();

		// forma mais nativa do java
//		return DriverManager.getConnection(
//				"jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", 
//				"root", "HP1sgay0");
	}

}
