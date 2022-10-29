FROM openjdk:8-jre-alpine

EXPOSE 8081

COPY ./target/achat-*.jar /usr/app/
WORKDIR /usr/app

CMD java -jar achat-*.jar
