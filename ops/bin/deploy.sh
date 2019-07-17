#! /usr/bin/env bash
set -euo pipefail
ktmpl $(pwd)/ops/deploy/deployment.yml \
--parameter imageTag latest | kubectl apply -f -