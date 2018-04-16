import psycopg2

'''
Dev: Ryan Guard (Guardr2)

Object that is responsible for connecting to our AWS postgresql database. This module was made with the intention
of reuse throughout the project as necessary.
'''

class ArchiveDatabaseConnection():
    def __init__(self, dbname,user,host,port,password):
        try:
            self.conn = psycopg2.connect("dbname='" + dbname + "' "
                                 "user='" + user + "'"
                                 "host='" + host + "'"
                                 "port='" + port + "'"
                                 "password='" + password + "'")
            print "connection success"
        except:
            print "connection failure"