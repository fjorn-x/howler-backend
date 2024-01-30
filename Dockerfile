FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/howler-0.0.1-SNAPSHOT.jar howler-0.0.1-SNAPSHOT.jar
EXPOSE 8443
CMD ["java","-jar","howler-0.0.1-SNAPSHOT.jar"]
