import psycopg2

try:
    conn = psycopg2.connect("dbname='tarefas' user='alexsander' host='localhost' password='hidros'")
except:
    print ("I am unable to connect to the database")
