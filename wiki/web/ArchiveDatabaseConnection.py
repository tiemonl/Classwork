import psycopg2

"""
Dev: Ryan Guard (Guardr2)

Object that is responsible for connecting to our AWS postgresql database. This module was made with the intention
of reuse throughout the project as necessary.
"""

class ArchiveDatabaseConnection():
    def __init__(self):
        try:
            self.conn = psycopg2.connect("dbname='wiki' "
                                 "user='wiki_user'"
                                 "host='wiki440.cmaehsb3sum0.us-east-2.rds.amazonaws.com'"
                                 "port='5432'"
                                 "password='jazzhouse123'")
            print "connection success"
        except:
            print "connection failure"