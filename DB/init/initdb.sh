#!/bin/sh

if [ -z "$DB_HOST" ] || [ -z "$DB_USER" ] || [ -z "$DB_PASSWORD" ]; then
  echo "Error: Required environment variables are not set."
  exit 1
fi


check_mysql() {
  local max_attempts=30
  local attempt=1

  while [ $attempt -le $max_attempts ]; do
    if mysqladmin ping -h"$DB_HOST" -u"$DB_USER" -p"$DB_PASSWORD" --silent; then
      echo "MySQL is ready. Proceeding with initialization..."
      return 0
    else
      echo "Waiting for MySQL to start... Attempt: $attempt"
      sleep 2
      attempt=$((attempt + 1))
    fi
  done

  echo "Error: MySQL took too long to start."
  exit 1
}

check_mysql

mysql -h"$DB_HOST" -u"$DB_USER" -p"$DB_PASSWORD" < /db_resources/permission.sql
mysql -h"$DB_HOST" -u"$DB_USER" -p"$DB_PASSWORD" < /db_resources/schema.sql