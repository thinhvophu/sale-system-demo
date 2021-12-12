FROM postgres:12

COPY waitforready.sh /

COPY 1-init-db.sql /docker-entrypoint-initdb.d/

ENV POSTGRES_DB=salesystem \
    POSTGRES_USER=postgres \
    POSTGRES_PASSWORD=postgres