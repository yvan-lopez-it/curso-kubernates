# Correr desde la raíz del proyecto pom padre curso-kubernetes

ARG MSVC_NAME=msvc-cursos

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
COPY --from=builder $TARGET_FOLDER/msvc-cursos-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

# Establecer el perfil activo para que use application-docker.properties
ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "-Xms2048m", "-Xmx2048m", "msvc-cursos-0.0.1-SNAPSHOT.jar"]

# docker build -t cursos . -f .\msvc-cursos\Dockerfile
# docker run -d --name msvc-cursos --network curso-kubernates_default -p 8001:8001 msvc-cursos:latest
# docker run -p 8002:8002 --rm -d msvc-cursos

# docker run -p 8002:8002 -d --rm --name msvc-cursos --network spring cursos
