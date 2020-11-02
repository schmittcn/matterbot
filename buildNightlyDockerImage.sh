#!/usr/bin/env bash

rm -Rf build/stage
mkdir -p build/stage && cp build/libs/*.jar build/stage && cp gradle/Dockerfile build/stage/

docker build -t matterbot:nightly build/stage/%
