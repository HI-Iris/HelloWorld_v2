#! /usr/bin/env bash
set -euo pipefail
ktmpl $(pwd)/ops/deploy/deployment.yml \
kubectl apply -f -