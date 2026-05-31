@echo off
REM Executa a aplicacao
cd /d "%~dp0"
java -cp "out;lib\mysql-connector-j-8.2.0.jar" cadastroprodutos.CadastroProdutos
