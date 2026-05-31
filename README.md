# Cadastro de Produtos — NUP 02 (Banco de Dados I)

Aplicação **Java em modo texto** que se conecta ao **MySQL** via **JDBC** e realiza
as 4 operações de um **CRUD** sobre a tabela `produto`.

| Operação | CRUD   | SQL    | Aula de referência |
|----------|--------|--------|--------------------|
| Inserir  | Create | INSERT | Aula 06            |
| Listar   | Read   | SELECT | Aula 09            |
| Alterar  | Update | UPDATE | Aula 08            |
| Remover  | Delete | DELETE | Aula 07            |

A conexão com o banco fica na classe `Conexao` (Aula 05).

## Estrutura

```
CadastroProdutos/
├── src/cadastroprodutos/
│   ├── CadastroProdutos.java   # main + menu (modo texto)
│   ├── Conexao.java            # conexão JDBC (Aula 05)
│   ├── Produto.java            # entidade
│   └── ProdutoDAO.java         # CRUD (Aulas 06 a 09)
├── lib/
│   └── mysql-connector-j-8.2.0.jar   # driver JDBC do MySQL
├── sql/
│   └── backup_loja.sql         # backup do banco (estrutura + dados)
├── compilar.sh / compilar.bat  # compila o projeto
├── executar.sh / executar.bat  # executa o projeto
└── config.properties.exemplo   # modelo de configuração de conexão
```

## 1. Preparar o banco de dados

Importe o backup no seu MySQL (cria o banco `loja`, a tabela `produto` e dados de exemplo):

```bash
mysql -u root -p < sql/backup_loja.sql
```

Ou abra `sql/backup_loja.sql` no MySQL Workbench / DBeaver e execute.

## 2. Configurar a conexão

Por padrão a aplicação conecta em `localhost:3306`, banco `loja`, usuário `root`,
senha `root` (constantes em `src/cadastroprodutos/Conexao.java`).

Se o seu MySQL usa outros dados, escolha **uma** opção:
- **(a)** editar as constantes em `Conexao.java`; ou
- **(b)** copiar `config.properties.exemplo` para `config.properties` e ajustar lá
  (esse arquivo não é versionado e tem prioridade sobre as constantes).

## 3. Compilar e executar

### macOS / Linux
```bash
./compilar.sh
./executar.sh
```

### Windows
```bat
compilar.bat
executar.bat
```

### Manualmente (sem scripts)
```bash
# compilar
javac -cp "lib/mysql-connector-j-8.2.0.jar" -d out src/cadastroprodutos/*.java
# executar  (Linux/macOS usa ":" no classpath | Windows usa ";")
java -cp "out:lib/mysql-connector-j-8.2.0.jar" cadastroprodutos.CadastroProdutos
```

## Requisitos
- Java 8 ou superior (desenvolvido com Java 21)
- MySQL 8.x
