import argparse

from markdownArchiveStandalone.ArchivePage import ArchivePage
from markdownArchiveStandalone.RestorePage import RestorePage

'''

'''

parser = argparse.ArgumentParser(description='Archive system for storing into a database schema must be of '
                                             'CREATE TABLE wiki.page(\n  page_id integer NOT NULL,\n  page_name text NOT NULL,'
                                             '\n  commit_id integer NOT NULL,\n  date_update date NOT NULL,\n  page_file text NOT NULL,'
                                             '\n  CONSTRAINT page_pkey PRIMARY KEY (page_id, commit_id))WITH (  OIDS=FALSE);'
                                             '\n  With database active, this can store versions of files or restore to'
                                             '\n  previous versions already stored in the database')
parser.add_argument('name',metavar='name', type=str, help='Name of File to archive/retrieve', required=True)
parser.add_argument('path',metavar='path', type=str, help='Complete file path to file', required=True)
parser.add_argument('commit_id',metavar='commit_id', type=int, help='Commit ID of a previous version only use with --retrieve')
parser.add_argument('--store', help='Specifies to store the given file', action='store_true')
parser.add_argument('--retrieve', help='Retrieves previous ', action='store_true')
if parser.store:
    try:
        ArchivePage(parser.name,parser.path).store()
        print "Stored the markdown file into database"
    except:
        print "Error: please check your inputted filename and path"
elif parser.retrieve:
    try:
        #   RestorePage()
    except:
        print "E"
else:
    print "please supply a --store or a --retrieve"

