FROM mysql:5.7.15

COPY csv/* /var/lib/mysql-files/
ADD data/ /docker-entrypoint-initdb.d/

