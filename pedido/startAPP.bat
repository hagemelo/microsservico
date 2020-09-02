call net start postgresql-x64-9.5
timeout 5 > NUL
start java -jar \project\jhage\pedido-api\target\pedido-api-1.4.0.jar

timeout 10 > NUL

start \Users\Inmetrics\Software\Apache24\bin\httpd.exe

start \Users\Inmetrics\Software\Apache24\bin\httpd.exe