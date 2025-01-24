# Use a imagem base do OpenJDK
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR gerado pelo Maven para o container
COPY target/app-control-1.0.0.jar /app/app-control.jar

# Expor a porta que será usada pela aplicação
EXPOSE 8085

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app-control.jar"]