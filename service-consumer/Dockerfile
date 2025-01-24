FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/service-consumer-1.0.0.jar /app/service-consumer.jar

EXPOSE 9093

ENTRYPOINT ["java", "-jar", "/app/service-consumer.jar"] 