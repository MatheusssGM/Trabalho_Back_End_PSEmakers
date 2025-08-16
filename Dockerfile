FROM eclipse-temurin:17
LABEL maintainer="mat.gm2502@gmail.com"
WORKDIR /API-Livraria
COPY target/API-PS-0.0.1-SNAPSHOT.jar API-Livraria.jar
ENTRYPOINT ["java", "-jar","API-Livraria.jar"]