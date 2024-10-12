# Usa una imagen base de Java
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/appusermanagement-0.0.1-SNAPSHOT.jar appusermanagement.jar

# Expone el puerto en el que la aplicación escuchará (ajusta según tu configuración)
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "appusermanagement.jar"]
