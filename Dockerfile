FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/howler-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/6B7EF7003F13D16B83A0718BB760E5A9.txt /home/ubuntu/6B7EF7003F13D16B83A0718BB760E5A9.txt
EXPOSE 80
CMD ["java","-jar","app.jar"]
