import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection con = criaConexao.recuperarConexao();
		
		PreparedStatement stm = con.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
		stm.setInt(1, 2);
		
		stm.execute();
		
		System.out.println("Qutd linhas modificadas: " + stm.getUpdateCount());
		
		con.close();
		
	}

}
