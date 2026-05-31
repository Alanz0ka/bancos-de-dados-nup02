@echo off
REM Compila o projeto para a pasta out\
cd /d "%~dp0"
if not exist out mkdir out
javac -cp "lib\mysql-connector-j-8.2.0.jar" -d out src\cadastroprodutos\*.java
echo Compilado em out\
