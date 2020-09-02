
sleep 30
start java -jar -Xms256m -Xmx256m -Xmn64m -XX:MaxMetaspaceSize=64m  C:\Users\Inmetrics\Documents\Codigos\microsservice\agendaconsulta\command\target\command-1.0.0.jar


sleep 30
start java -jar -Xms256m -Xmx256m -Xmn64m -XX:MaxMetaspaceSize=64m  C:\Users\Inmetrics\Documents\Codigos\microsservice\agendaconsulta\cadastrarcliente\target\cadastrarcliente-1.0.0.jar


sleep 30
start java -jar -Xms256m -Xmx256m -Xmn64m -XX:MaxMetaspaceSize=64m  C:\Users\Inmetrics\Documents\Codigos\microsservice\agendaconsulta\agendaratendimento\target\agendaratendimento-1.0.0.jar
