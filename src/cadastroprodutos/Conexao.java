package cadastroprodutos;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe responsavel pela conexao com o SGBD MySQL via JDBC (Aula 05).
 *
 * Por padrao usa as constantes abaixo (localhost / root / loja). Se existir um
 * arquivo "config.properties" na raiz do projeto, os valores dele tem prioridade
 * -- isso permite testar em outro servidor sem alterar (nem versionar) o codigo.
 */
public class Conexao {

    // === Configuracao padrao do banco -- ajuste conforme o seu MySQL ===
    private static final String HOST    = "localhost";
    private static final String PORTA   = "3306";
    private static final String BANCO   = "loja";
    private static final String USUARIO = "root";
    private static final String SENHA   = "root";

    /**
     * Abre e retorna uma conexao com o banco de dados.
     */
    public static Connection conectar() {
        Properties cfg = carregarConfig();

        String host    = cfg.getProperty("db.host", HOST);
        String porta   = cfg.getProperty("db.porta", PORTA);
        String banco   = cfg.getProperty("db.banco", BANCO);
        String usuario = cfg.getProperty("db.usuario", USUARIO);
        String senha   = cfg.getProperty("db.senha", SENHA);

        String url = "jdbc:mysql://" + host + ":" + porta + "/" + banco
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        try {
            // Registra o driver do MySQL (opcional no JDBC 4+, mantido por clareza).
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                    "Driver do MySQL nao encontrado. Confira se o JAR esta em lib/ e no classpath.", e);
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao conectar no MySQL (" + url + "): " + e.getMessage(), e);
        }
    }

    /**
     * Fecha a conexao com seguranca.
     */
    public static void fechar(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.out.println("Aviso: erro ao fechar a conexao: " + e.getMessage());
        }
    }

    /**
     * Le o arquivo opcional config.properties (se existir) para sobrescrever os
     * dados de conexao sem mexer no codigo-fonte.
     */
    private static Properties carregarConfig() {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream("config.properties")) {
            props.load(in);
        } catch (Exception ignorado) {
            // Arquivo nao existe -> usa as constantes padrao. Tudo certo.
        }
        return props;
    }
}
