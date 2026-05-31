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

    /** DELETE -- remove um produto pelo id (Aula 07). */
    public boolean remover(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        Connection conexao = Conexao.conectar();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
            return false;
        } finally {
            Conexao.fechar(conexao);
        }
    }

    /** UPDATE -- altera um produto existente (Aula 08). */
    public boolean alterar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, quantidade = ?, categoria = ? WHERE id = ?";
        Connection conexao = Conexao.conectar();
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidade());
            ps.setString(4, produto.getCategoria());
            ps.setInt(5, produto.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar produto: " + e.getMessage());
            return false;
        } finally {
            Conexao.fechar(conexao);
        }
    }
}
