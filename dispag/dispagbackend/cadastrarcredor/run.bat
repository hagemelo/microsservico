echo off
echo "Inicializar Microsservico Cadastrar Credor"

##Kafka Config
set KAFKA_SERVER=172.24.158.86:9092
set KAFKA_GROUP=DISPAG
set KAFKATOPIC=CADASTRARCREDOR

##Banco Config
set DATASOURCE_URL=jdbc:postgresql://localhost:5432/tarefas
set DATASOURCE_USERNAME=programador
set DATASOURCE_PASSWORD=hidros

call java -jar -Xms768M -Xmx768M -Xmn256m -XX:MaxMetaspaceSize=64m -XX:+UseG1GC  target/cadastrarcredor-1.0.0.jar
cd ..
