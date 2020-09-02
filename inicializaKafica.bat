cd C:\Users\Inmetrics\Softwares\kafka
start .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
sleep 5
cd C:\Users\Inmetrics\Softwares\kafka
start .\bin\windows\kafka-server-start.bat .\config\server.properties
