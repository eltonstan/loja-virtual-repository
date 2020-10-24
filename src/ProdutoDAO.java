import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	private Connection con;

	public ProdutoDAO(Connection con) {
		this.con = con;
	}

	public void salvarProduto(Produto produto) throws SQLException {

		String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

		try (PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}

		}

		System.out.println(produto.toString());

	}

	public List<Produto> listar() throws SQLException {
		
		List<Produto> produtos = new ArrayList<Produto>();

		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";
		try (PreparedStatement stm = con.prepareStatement(sql)) {
			stm.execute();

			try(ResultSet rst = stm.getResultSet()) {
				while (rst.next()) {
					
					Integer id = rst.getInt("ID");
					String nome = rst.getString("NOME");
					String descricao = rst.getString("DESCRICAO");
					Produto produto = new Produto(id, nome, descricao);
					
					produtos.add(produto);
				}
			}
		}
		
		return produtos;

	}

}
