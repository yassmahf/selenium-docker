FROM bellsoft/liberica-openjdk-alpine:21
#Instal curl js
RUN apk add curl jq
# workspace
WORKDIR /home/selenium-docker
# Add the required files
ADD target/docker-resources ./
ADD runner.sh  runner.sh

#start the runner.sh
ENTRYPOINT sh runner.sh