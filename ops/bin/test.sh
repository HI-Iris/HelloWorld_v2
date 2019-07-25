#!/usr/bin/env bash
set -euo pipefail

docker build -f Dockerfile.test -t iris-hello-world .

docker run --rm iris-hello-world:latest ./gradlew test