package cadastroprodutos;

import java.util.List;
import java.util.Scanner;

/**
 * Aplicacao Java em modo texto que se conecta ao MySQL e realiza as 4 operacoes
 * do CRUD sobre a tabela "produto" (NUP 02 - Banco de Dados I).
 *
 *   1 - Inserir  (Create - Aula 06 - INSERT)
 *   2 - Listar   (Read   - Aula 09 - SELECT)
 *   3 - Alterar  (Update - Aula 08 - UPDATE)
 *   4 - Remover  (Delete - Aula 07 - DELETE)
 */
public class CadastroProdutos {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final ProdutoDAO DAO = new ProdutoDAO();

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("   CADASTRO DE PRODUTOS - NUP 02");
        System.out.println("   (Java modo texto + MySQL/JDBC)");
        System.out.println("====================================");

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            switch (opcao) {
                case 1 -> inserir();
                case 2 -> listar();
                case 3 -> alterar();
                case 4 -> remover();
                case 0 -> System.out.println("\nEncerrando... ate mais!");
                default -> System.out.println("\n[!] Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);

        ENTRADA.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--------- MENU ---------");
        System.out.println("1 - Inserir produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Alterar produto");
        System.out.println("4 - Remover produto");
        System.out.println("0 - Sair");
        System.out.println("------------------------");
    }

    /** Opcao 1 - CREATE. */
    private static void inserir() {
        System.out.println("\n>> NOVO PRODUTO");
        String nome = lerTexto("Nome.......: ");
        double preco = lerDouble("Preco......: R$ ");
        int qtd = lerInteiro("Quantidade.: ");
        String categoria = lerTexto("Categoria..: ");

        Produto produto = new Produto(nome, preco, qtd, categoria);
        if (DAO.inserir(produto)) {
            System.out.println("[OK] Produto inserido com sucesso!");
        } else {
            System.out.println("[ERRO] Nao foi possivel inserir o produto.");
        }
    }

    /** Opcao 2 - READ. */
    private static void listar() {
        System.out.println("\n>> LISTA DE PRODUTOS");
        List<Produto> produtos = DAO.listar();
        if (produtos.isEmpty()) {
            System.out.println("(nenhum produto cadastrado)");
            return;
        }
        System.out.println("-------------------------------------------------------------------");
        for (Produto p : produtos) {
            System.out.println(p);
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Total: " + produtos.size() + " produto(s).");
    }

    /** Opcao 3 - UPDATE. */
    private static void alterar() {
        System.out.println("\n>> ALTERAR PRODUTO");
        int id = lerInteiro("Informe o ID do produto: ");
        Produto produto = DAO.buscarPorId(id);
        if (produto == null) {
            System.out.println("[!] Produto com ID " + id + " nao encontrado.");
            return;
        }
        System.out.println("Editando: " + produto);
        System.out.println("(deixe em branco para manter o valor atual)");

        String nome = lerTexto("Novo nome [" + produto.getNome() + "]: ");
        if (!nome.isBlank()) produto.setNome(nome);

        String precoStr = lerTexto("Novo preco [" + produto.getPreco() + "]: ");
        if (!precoStr.isBlank()) produto.setPreco(converterDouble(precoStr, produto.getPreco()));

        String qtdStr = lerTexto("Nova quantidade [" + produto.getQuantidade() + "]: ");
        if (!qtdStr.isBlank()) produto.setQuantidade(converterInteiro(qtdStr, produto.getQuantidade()));

        String categoria = lerTexto("Nova categoria [" + produto.getCategoria() + "]: ");
        if (!categoria.isBlank()) produto.setCategoria(categoria);

        if (DAO.alterar(produto)) {
            System.out.println("[OK] Produto alterado com sucesso!");
        } else {
            System.out.println("[ERRO] Nao foi possivel alterar o produto.");
        }
    }

    /** Opcao 4 - DELETE. */
    private static void remover() {
        System.out.println("\n>> REMOVER PRODUTO");
        int id = lerInteiro("Informe o ID do produto: ");
        Produto produto = DAO.buscarPorId(id);
        if (produto == null) {
            System.out.println("[!] Produto com ID " + id + " nao encontrado.");
            return;
        }
        System.out.println("Produto: " + produto);
        String confirma = lerTexto("Confirma a remocao? (S/N): ");
        if (confirma.equalsIgnoreCase("S")) {
            if (DAO.remover(id)) {
                System.out.println("[OK] Produto removido com sucesso!");
            } else {
                System.out.println("[ERRO] Nao foi possivel remover o produto.");
            }
        } else {
            System.out.println("Remocao cancelada.");
        }
    }

    // ===== Utilitarios de leitura do teclado =====

    private static String lerTexto(String rotulo) {
        System.out.print(rotulo);
        return ENTRADA.nextLine().trim();
    }

    private static int lerInteiro(String rotulo) {
        while (true) {
            String linha = lerTexto(rotulo);
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("[!] Digite um numero inteiro valido.");
            }
        }
    }

    private static double lerDouble(String rotulo) {
        while (true) {
            String linha = lerTexto(rotulo).replace(",", ".");
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("[!] Digite um valor numerico valido (ex: 9.90).");
            }
        }
    }

    private static double converterDouble(String valor, double padrao) {
        try {
            return Double.parseDouble(valor.replace(",", "."));
        } catch (NumberFormatException e) {
            return padrao;
        }
    }

    private static int converterInteiro(String valor, int padrao) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return padrao;
        }
    }
}
