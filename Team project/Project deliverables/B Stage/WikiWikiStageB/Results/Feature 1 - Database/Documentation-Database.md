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

