echo off
echo "Inicializar Servico"
set KAFKA_SERVER=172.17.224.247:9092 
cd target
call java -jar agendaratendimento-1.0.0.jar
cd ..
