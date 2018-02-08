# --- !Ups

CREATE TABLE r_wannatag (
  wannatag_id int PRIMARY KEY,  -- WannaTag ID
  author_id varchar(80) NOT NULL,  -- author's user ID
  create_datetime timestamp with time zone NOT NULL  -- WannaTag create datetime
);

CREATE TABLE e_update_wannatag (
  wannatag_id int references r_wannatag(wannatag_id),  -- WannaTag ID
  update_seq int,  -- WannaTag update sequence
  title varchar(100) NOT NULL,  -- WannaTag Title
  body varchar(500) NOT NULL,  -- WannaTag Text
  update_datetime timestamp with time zone NOT NULL,  -- WannaTag update datetime
  PRIMARY KEY(wannatag_id, update_seq)
);



# --- !Downs

DROP TABLE r_wannatag;
DROP TABLE e_wannatag_info;