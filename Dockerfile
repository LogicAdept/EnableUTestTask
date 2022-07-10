FROM openjdk:11-jre-slim
COPY target/enableUTestTask-1.0-SNAPSHOT.jar /test.jar
EXPOSE 8080 465
CMD ["java", "-jar", "/test.jar"]
