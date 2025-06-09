# Usar la imagen oficial de OpenJDK 21
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR del proyecto (asegurarte de haberlo generado previamente con Maven o Gradle)
COPY target/theater-manager-1.0.0.jar /app/theater-manager.jar

# Exponer el puerto en el que la aplicación escuchará (por defecto, 8080 en aplicaciones Spring Boot)
EXPOSE 8080

# Comando para ejecutar el JAR
CMD ["java", "-jar", "theater-manager.jar"]
