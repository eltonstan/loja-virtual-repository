import java.sql.SQLException;

public class TestaPoolConexoes {

	public static void main(String[] args) throws SQLException {
	
		ConnectionFactory con = new ConnectionFactory();
		
		for (int i = 1; i <= 20; i++) {
			con.recuperarConexao();
			System.out.println("Conex�o de numero: " + i);
		}

	}

}
