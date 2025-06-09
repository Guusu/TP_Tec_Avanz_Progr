# Pasos para Despliegue en Docker


## Crear una Red para que los contenedores se vean entre si 

    docker network create theater-app-network


# Base de Datos MySQL

## Crear el contenedor de MYSQL y asociarlo a la red

    docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v C:\Database\MySQL -d mysql:latest

    docker network connect --alias mysql-db theater-app-network mysql-container

# Theater-User-Management
Servicio de autenticacion
## generar el packete para poder subirse a Docker

Para esto generar el paquete con mvn una vez situados en la raiz del proyecto.

    mvn clean package

## Generar la imagen y desplegar el servicio de autenticacion asociado a la red creada 

    docker build -t theater-user-management:1.0.0 .

    docker run -d --name theater-user-mng-app --network theater-app-network -p 8088:8080 theater-user-management:1.0.0

# Theater-Manager
Servicio de Administracion de teatro
## generar el packete para poder subirse a Docker

Para esto generar el paquete con mvn una vez situados en la raiz del proyecto.

    mvn clean package
    
## Generar la imagen y desplegar el servicio de administracion del teatro asociado a la red
  
    docker build -t theater-manager:1.0.0 .

    docker run -d --name theater-manage-app --network theater-app-network -p 8080:8080 theater-manage:1.0.0

### De esta forma quedaran los contenedores levantados y asociados a una misma red para que puedan interactuar.

![DockerDesktop](DockerDesktop.png)