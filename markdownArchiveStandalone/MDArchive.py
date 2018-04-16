import argparse

parser = argparse.ArgumentParser(description='Archive system for storing into a database schema must be of '
                                             'CREATE TABLE wiki.page(\n  page_id integer NOT NULL,\n  page_name text NOT NULL,'
                                             '\n  commit_id integer NOT NULL,\n  date_update date NOT NULL,\n  page_file text NOT NULL,'
                                             '\n  CONSTRAINT page_pkey PRIMARY KEY (page_id, commit_id))WITH (  OIDS=FALSE);'
                                             '\n ')
