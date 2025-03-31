# --- !Ups

CREATE TABLE todos (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    completed BOOLEAN NOT NULL DEFAULT FALSE
);

# --- !Downs

DROP TABLE IF EXISTS todos; 