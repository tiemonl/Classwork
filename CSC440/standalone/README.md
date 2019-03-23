## MarkdownPostgresArchive

* Team: WikiWiki
* Group members: Ryan Guard, Elizabeth Gieske, Ronnie Hoover, Christopher Groppe, Liam Tiemon

### Features:

* Sequentially store data of markdown files into postgres database
* Retrieve stored data to save an old version of markdown file or overwrite old one
* View previous stored/archived versions
* Look into individual contents of commits

### Setup:

* You will need to setup a postgresql database of the schema:

CREATE TABLE wiki.page\
(\
  page_id integer NOT NULL,\
  page_name text NOT NULL,\
  commit_id integer NOT NULL,\
  date_update date NOT NULL,\
  page_file text NOT NULL,\
  CONSTRAINT page_pkey PRIMARY KEY (page_id, commit_id)\
)\
WITH (\
  OIDS=FALSE\
);

* Download requirements.txt
* You will have to run the markdownPostgresArchive.MDArchiveSetup command to do initial setup of Database credentials
* After Setup: Interface with the program using markdownPostgresArchive.MDArchive

