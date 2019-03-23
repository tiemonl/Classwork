-- Table: wiki.page

-- DROP TABLE wiki.page;

CREATE TABLE wiki.page
(
  page_id integer NOT NULL,
  page_name text NOT NULL,
  commit_id integer NOT NULL,
  date_update date NOT NULL,
  page_file text NOT NULL,
  CONSTRAINT page_pkey PRIMARY KEY (page_id, commit_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE wiki.page
  OWNER TO wiki_admin;
GRANT ALL ON TABLE wiki.page TO wiki_admin;
GRANT SELECT, INSERT ON TABLE wiki.page TO wiki_user;
