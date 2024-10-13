#!/bin/bash

# Levanta el contenedor de MySQL
docker-compose up -d mysql-db

# Espera a que MySQL esté listo
echo "Esperando a que MySQL esté listo..."
until docker exec mysql-container mysql -u admin -ppass -e "select 1;" &> /dev/null; do
  sleep 1
done
echo "MySQL está listo."

# Ejecuta el script SQL
docker exec -i mysql-container mysql -u admin -ppass accounts < init.sql

# Levanta la aplicación
docker-compose up -d app
