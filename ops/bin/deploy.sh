#! /usr/bin/env bash
set -euo pipefail
ktmpl $(pwd)/ops/deploy/deployment.yml \
--parameter SERVICE_NAME iris-hello-world | kubectl apply -f -