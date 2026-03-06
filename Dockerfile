# Imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio dentro del contenedor
WORKDIR /app

# Copiar el jar generado por Maven
COPY target/chakray-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto de la aplicación
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java","-jar","app.jar"]