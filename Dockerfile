FROM openjdk:8-jdk-alpine
ADD target/dropwizard-example.jar /app/dropwizard-example.jar
ADD src/test/resources/config.yml /app/config.yml
CMD java -jar /app/dropwizard-example.jar server /app/config.yml
EXPOSE 8080