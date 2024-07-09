# Correr desde la raíz del proyecto pom padre curso-kubernetes

FROM openjdk:17-alpine AS builder
LABEL authors="Yvancho"

WORKDIR /app/msvc-cursos

COPY ./pom.xml /app
COPY ./msvc-cursos/.mvn ./.mvn
COPY ./msvc-cursos/mvnw .
COPY ./msvc-cursos/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
# ./mvnw dependency:go-offline

COPY ./msvc-cursos/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/msvc-cursos/target/msvc-cursos-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

# Establecer el perfil activo para que use application-docker.properties
ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "-Xms2048m", "-Xmx2048m", "msvc-cursos-0.0.1-SNAPSHOT.jar"]

# docker build -t cursos:latest . -f .\msvc-cursos\Dockerfile
# docker run -d --name msvc-cursos --network curso-kubernates_default -p 8001:8001 msvc-cursos:latest
# docker run -p 8002:8002 --rm -d msvc-cursos

# docker run -p 8002:8002 -d --rm --name msvc-cursos --network spring cursos
