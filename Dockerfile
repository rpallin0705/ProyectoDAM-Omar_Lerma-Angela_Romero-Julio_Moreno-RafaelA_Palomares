FROM alpine:latest

RUN apk --no-cache add sqlite

WORKDIR /data

EXPOSE 3000 3000

COPY ./db/db.sql /data/db.sql

CMD sqlite3 /data/proyecto.db < /data/db.sql && tail -f /dev/null
