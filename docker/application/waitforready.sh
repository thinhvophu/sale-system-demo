#!/bin/bash

set -euo pipefail

for i in {1..300}; do
	result="$(curl -ks https://$1:9090/actuator/health | grep '^{"status":"UP"}$' || true)"

	if [ -n "$result" ]; then
		exit 0
	fi

	sleep 1
done

echo "error: app server is not ready"
exit 1