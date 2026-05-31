#!/usr/bin/env bash
# Compila o projeto para a pasta out/
cd "$(dirname "$0")" || exit 1
mkdir -p out
javac -cp "lib/mysql-connector-j-8.2.0.jar" -d out src/cadastroprodutos/*.java
echo "Compilado em out/"
