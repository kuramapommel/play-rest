# --- !Ups

CREATE SEQUENCE r_wannatag_wannatag_id_seq;
CREATE TABLE r_wannatag (
  wannatag_id int DEFAULT nextval('r_wannatag_wannatag_id_seq')　PRIMARY KEY,  -- WannaTag ID
  author_id varchar(80) NOT NULL,  -- author's user ID
  create_datetime timestamp with time zone NOT NULL  -- WannaTag create datetime
);

CREATE TABLE e_update_wannatag (
  wannatag_id int references r_wannatag(wannatag_id),  -- WannaTag ID
  title varchar(100) NOT NULL,  -- WannaTag Title
  body varchar(500) NOT NULL,  -- WannaTag Text
  PRIMARY KEY(wannatag_id)
);



# --- !Downs

DROP SEQUENCE r_wannatag_wannatag_id_seq;
DROP TABLE r_wannatag;
DROP TABLE e_update_wannatag;