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

ARG PORT_APP=8002
ENV PORT $PORT_APP
EXPOSE $PORT

CMD ["java", "-jar", "msvc-cursos-0.0.1-SNAPSHOT.jar"]
#CMD sleep 20 && java -jar msvc-cursos-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "msvc-cursos-0.0.1-SNAPSHOT.jar"]

# docker build -t cursos . -f .\msvc-cursos\Dockerfile
# docker run -d --name msvc-cursos --network curso-kubernates_default -p 8001:8001 msvc-cursos:latest
# docker run -p 8002:8002 --rm -d msvc-cursos

# docker run -p 8002:8002 -d --rm --name msvc-cursos --network spring cursos

# docker build -t cursos . -f .\msvc-cursos\Dockerfile
# docker tag cursos yvancho/cursos:latest
# docker push yvancho/cursos:latest
