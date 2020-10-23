import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		
		try(Connection con = criaConexao.recuperarConexao()) {
			
			con.setAutoCommit(false);
			
			try (PreparedStatement stm = con.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
				
				adicionarVariavel("Smartphone", "Rog phone 2", stm);
				adicionarVariavel("SmartTV", "45 polegadas", stm);
				
				con.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("rollback executado");
				con.rollback();
			}
		}
		
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		
//		if (nome.equals("SmartTV")) {
//			throw new RuntimeException("Não foi possivel adicionar o produto");
//		}

		stm.execute();
		
		ResultSet rst = stm.getGeneratedKeys();
		
		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O id criado foi: " + id);
		}
	}


}
