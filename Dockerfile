FROM openjdk:17-jdk-slim

ADD target/demo-smallinvoice-api-*.jar app.jar

ARG JVM_OPTS
ENV JVM_OPTS=${JVM_OPTS}

CMD java ${JVM_OPTS} -jar app.jar
