FROM openjdk:17
LABEL maintainer="artur@artur.com"
VOLUME /main-app
ADD target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]
