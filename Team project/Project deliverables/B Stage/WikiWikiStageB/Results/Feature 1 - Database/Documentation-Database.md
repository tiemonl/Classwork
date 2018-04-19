# Feature - Database.

* Programmer: Chris Groppe
* Date: 04/19/2018

# Problem Definition
In order for any feature that involves storing and retrieving data at a larger scale we must have a database that can hold both the contents of the file and meta data.
Additionally it must be able to handle multiple versions of the same file to allow for the restore feature to be able to be made.


## Requirement 

Stores information about the markdown file such as(username, file name, contents).


## User stories

1. As a Developer, I want a database that can hold my markdown file data so that I can best implement store and retrieval functality for the user
2. As a Developer, I need it to be able to store multiple versions so that I can enable the user to later pull previous versions of files
3. As a Developer, I need my Database hosted on a server so that I can have my users able to pull data from any client.

\newpage

# Design:

## Detailed design/Implementation:

### How to set up an RDS PostgreSQL instance in AWS.

1. Search the AWS console for RDS. After clicking on RDS look on the right hand side and select "Instances".

2. Choose PostgreSQL and Free Usage Tier. 

3. Select specifications. 

4. Create an identifier, username, and password.

5. Select your personal VPC, subnet, and security (stick with default)

6. Select name, do not change port (5432 is default).

7. Hit launch (last options are up to you).


### PostgreSQL Schema/Table/Users

1. Create Roles 

```SQL
CREATE ROLE wiki_user WITH LOGIN ENCRYPTED PASSWORD '--removed--';
CREATE ROLE wiki_unit_test WITH LOGIN ENCRYPTED PASSWORD '--removed--';
```

2. Create Schema

```SQL
CREATE SCHEMA wiki
    AUTHORIZATION wiki_admin;
	
GRANT ALL ON SCHEMA wiki to wiki_admin;
GRANT ALL ON SCHEMA wiki to wiki_user;
GRANT ALL ON SCHEMA wiki to wiki_unit_test;
```

3. Create Table and Premissions 

```SQL
CREATE TABLE wiki.page
(
  page_id integer NOT NULL,
  page_name text NOT NULL,
  commit_id integer NOT NULL,
  date_update date NOT NULL,
  page_file text NOT NULL,
  user_last_update text,
  CONSTRAINT page_pkey PRIMARY KEY (page_id, commit_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE wiki.page
  OWNER TO wiki_admin;
GRANT ALL ON TABLE wiki.page TO wiki_admin;
GRANT SELECT, INSERT ON TABLE wiki.page TO wiki_user;
GRANT ALL ON TABLE wiki.page TO wiki_unit_test;
```

### Information on implementation

host: wiki440.cmaehsb3sum0.us-east-2.rds.amazonaws.com
port; 5432
db: wiki
schema: wiki
table: page

user: wiki_user
password: jazzhouse123

user: wiki_unit_test
password: jazzhouse123


# Tests

## Limitations of tests

1. Unit tests covering any code interacting with the database must have to be sufficient as it is hard to make unit tests directly for a database.
2. We also manually tested through selecting, insterting, and deleting before implementing code to be sure this met our needs

# User documentation

This schema info is viewable in the part A -h prompts to allow for users to replicate our databases and use our system.

