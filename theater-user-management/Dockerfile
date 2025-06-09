# Usar la imagen oficial de OpenJDK 21
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

COPY target/theater-user-management-1.0.0.jar /app/theater-user-management.jar

EXPOSE 8088

# Comando para ejecutar el JAR
CMD ["java", "-jar", "theater-user-management.jar"]
