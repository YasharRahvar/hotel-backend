FROM mysql:5.6

MAINTAINER cv

COPY createSchemas.sql /docker-entrypoint-initdb.d/
EXPOSE 3308
ENV MYSQL_ROOT_PASSWORD 12345