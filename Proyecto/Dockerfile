FROM alpine:latest AS sqlite

RUN apk --no-cache add sqlite

WORKDIR /data

EXPOSE 8080 8080

COPY ./database/db.sql /data/db.sql

CMD sqlite3 /data/proyecto.db < /data/db.sql && tail -f /dev/null


