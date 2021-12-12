#!/usr/bin/env bash
set -euo pipefail

for i in {1..300}; do

	result="$(PGPASSWORD=$POSTGRES_PASSWORD psql -h "$1" $POSTGRES_DB $POSTGRES_USER -c "select 23 * 59" 2>&1 | grep '1357' || true)"

	if [ -n "$result" ]; then
		exit 0
	fi

	sleep 1
done

echo "error: server is not ready"
exit 1