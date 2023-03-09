#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 -U "$POSTGRES_USER" -d "$POSTGRES_DB" <<-EOSQL

    ALTER ROLE admin CREATEDB CREATEROLE REPLICATION BYPASSRLS;

    CREATE ROLE postgres WITH LOGIN SUPERUSER PASSWORD 'postgres';

EOSQL