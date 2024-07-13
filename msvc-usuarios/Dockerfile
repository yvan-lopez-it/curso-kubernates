# Correr desde la raíz del proyecto pom padre curso-kubernetes

ARG MSVC_NAME=msvc-usuarios

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
COPY --from=builder $TARGET_FOLDER/msvc-usuarios-0.0.1-SNAPSHOT.jar .

ARG PORT_APP=8001
ENV PORT $PORT_APP
EXPOSE $PORT

# Establecer el perfil activo para que use application-docker.properties
ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-jar", "-Xms2048m", "-Xmx2048m", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]

# docker build -t usuarios . -f .\msvc-usuarios\Dockerfile
# docker run -d --name msvc-usuarios --network curso-kubernates_default -p 8001:8001 msvc-usuarios:latest
# docker run -p 8001:8000 --rm -d msvc-usuarios

# docker run -p 8001:8000 -d --rm --name msvc-usuarios --network spring usuarios

# docker build tag usuarios yvancho/usuarios
# docker push yvancho/usuarios
