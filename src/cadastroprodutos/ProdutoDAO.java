package cadastroprodutos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DAO (Data Access Object) com as operacoes de acesso a tabela "produto".
 * Usa PreparedStatement para evitar SQL Injection e tratar os tipos corretamente.
 */
public class ProdutoDAO {

    /** CREATE -- insere um novo produto (Aula 06 - INSERT). */
    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, quantidade, categoria) VALUES (?, ?, ?, ?)";
        Connection conexao = Conexao.conectar();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidade());
            ps.setString(4, produto.getCategoria());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        } finally {
            Conexao.fechar(conexao);
        }
    }
}
