import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaInsercaoEListagemComProduto {
	
	public static void main(String[] args) throws SQLException {

		Produto comoda = new Produto("Cômoda","Cõmoda Vertical");
		
		try( Connection con = new ConnectionFactory().recuperarConexao()) {
			
			ProdutoDAO persistenciaDao = new ProdutoDAO(con);
			persistenciaDao.salvarProduto(comoda);
			List<Produto> listar = persistenciaDao.listar();
			listar.stream().forEach(lp -> System.out.println(lp));
			
		}

	}

}
