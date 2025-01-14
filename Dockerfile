# Usar uma imagem base com Java 21
FROM openjdk:21-jdk-slim

# Configurar o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o arquivo JAR gerado pela aplicação para o contêiner
COPY target/autenticacao-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta usada pela aplicação
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
