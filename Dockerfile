FROM openjdk:11
EXPOSE 8084
WORKDIR /app
COPY target/internetbanking-0.0.1-SNAPSHOT.jar .
ENTRYPOINT [ "java", "-jar", "internetbanking-0.0.1-SNAPSHOT.jar" ]