# Usamos como punto de partida una máquina virtual con JDK 11 y Maven instalados
FROM maven:3.8.6-jdk-11

# Creamos una carpeta para el código fuente
RUN mkdir -p /usr/src/asi-board
# Establecemos la nueva carpeta como directorio de trabajo
WORKDIR /usr/src/asi-board
# Copiamos el código fuente del proyecto dentro de la máquina virtual
COPY . .
# Ejecutamos Maven para compilar el proyecto y generar un fichero jar ejecutable
RUN mvn clean package

# Inicia el proyecto ejecutando el ficheor jar compilado
CMD java -jar target/test-cloud-0.0.1-SNAPSHOT-jar-with-dependencies.jar
