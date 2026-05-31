#!/usr/bin/env bash
# Executa a aplicacao (compila antes, se necessario)
cd "$(dirname "$0")" || exit 1
if [ ! -d out ]; then
  ./compilar.sh || exit 1
fi
java -cp "out:lib/mysql-connector-j-8.2.0.jar" cadastroprodutos.CadastroProdutos
