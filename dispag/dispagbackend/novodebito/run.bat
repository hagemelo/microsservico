echo off
echo "Inicializar Microsservico novo Debito"

##Kafka Config
set KAFKA_SERVER=172.26.190.234:9092
set KAFKA_GROUP=DISPAG
set KAFKATOPIC=NOVODEBITO

##Banco Config
set DATASOURCE_URL=jdbc:postgresql://localhost:5432/tarefas
set DATASOURCE_USERNAME=programador
set DATASOURCE_PASSWORD=hidros

call java -jar -Xms768M -Xmx768M -Xmn256m -XX:MaxMetaspaceSize=64m -XX:+UseG1GC  target/novodebito-1.0.0.jar
cd ..
