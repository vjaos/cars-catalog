FROM openjdk:11-jre-slim

ADD ./target/api-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/api-server.jar"]

EXPOSE 8080