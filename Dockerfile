FROM ubuntu:latest
LABEL authors="beaver"

ENTRYPOINT ["top", "-b"]