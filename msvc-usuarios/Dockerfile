# Correr desde la raíz del proyecto pom padre curso-kubernetes

FROM openjdk:17-alpine AS builder
LABEL authors="Yvancho"

WORKDIR /app/msvc-usuarios

COPY ./pom.xml /app
COPY ./msvc-usuarios/.mvn ./.mvn
COPY ./msvc-usuarios/mvnw .
COPY ./msvc-usuarios/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
# ./mvnw dependency:go-offline

COPY ./msvc-usuarios/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/msvc-usuarios/target/msvc-usuarios-0.0.1-SNAPSHOT.jar .
EXPOSE 8001

# Establecer el perfil activo para que use application-docker.properties
ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "-Xms2048m", "-Xmx2048m", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-jar", "-Xms2048m", "-Xmx2048m", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]

# docker build -t usuarios:latest . -f .\msvc-usuarios\Dockerfile
# docker run -d --name msvc-usuarios --network curso-kubernates_default -p 8001:8001 msvc-usuarios:latest
# docker run -p 8001:8001 --rm -d msvc-usuarios

# docker run -p 8001:8001 -d --rm --name msvc-usuarios --network spring usuarios
