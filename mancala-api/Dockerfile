FROM maven:3.8.1-openjdk-16-slim

MAINTAINER okanmenevseoglu@gmail.com

WORKDIR /app

COPY . /app

RUN mvn clean install

ENTRYPOINT ["java","-jar","target/mancala-api-1.0.0.jar"]

EXPOSE 8082

HEALTHCHECK --start-period=10s --interval=10s --timeout=5s \
    CMD curl --silent --fail localhost:8082/actuator/health 2>&1 | grep UP || exit 1