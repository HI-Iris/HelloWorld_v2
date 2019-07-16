#!/usr/bin/env bash
./gradlew clean build
aws sts get-caller-identity
$(aws ecr get-login --no-include-email --region ap-southeast-2)
docker build -t iris-hello-world .
docker tag iris-hello-world:latest 945367126992.dkr.ecr.ap-southeast-2.amazonaws.com/iris-hello-world:latest
docker push 945367126992.dkr.ecr.ap-southeast-2.amazonaws.com/iris-hello-world:latest
