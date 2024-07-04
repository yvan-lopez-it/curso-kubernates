FROM openjdk:17-alpine
LABEL authors="Yvancho"

WORKDIR /app
COPY ./target/msvc-cursos-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

ENV MSVC_USUARIOS_URL=msvc-usuarios:8001

ENTRYPOINT ["java", "-jar", "-Xms2048m", "-Xmx2048m", "msvc-cursos-0.0.1-SNAPSHOT.jar"]
