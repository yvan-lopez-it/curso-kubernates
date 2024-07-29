# Correr desde la raíz del proyecto pom padre curso-kubernetes

ARG MSVC_NAME=msvc-gateway

FROM openjdk:17-alpine AS builder

ARG MSVC_NAME

LABEL authors="Yvancho"
WORKDIR /app/$MSVC_NAME

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
# ./mvnw dependency:go-offline

COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine

ARG MSVC_NAME

WORKDIR /app
RUN mkdir ./logs
ARG TARGET_FOLDER=/app/$MSVC_NAME/target
COPY --from=builder $TARGET_FOLDER/msvc-gateway-0.0.1-SNAPSHOT.jar .
EXPOSE 8090

CMD ["java", "-jar", "msvc-gateway-0.0.1-SNAPSHOT.jar"]

# docker build -t gateway . -f .\msvc-gateway\Dockerfile
# docker tag gateway yvancho/gateway:latest
# docker push yvancho/gateway:latest
