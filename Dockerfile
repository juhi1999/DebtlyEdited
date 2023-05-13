#FROM openjdk:17
#ADD ./target/Debtly1.0-0.0.1-SNAPSHOT.jar ./
#WORKDIR ./
#CMD ["java", "-jar", "Debtly1.0-0.0.1-SNAPSHOT.jar "]

FROM openjdk:11
ADD ./target/Debtly1.0-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9192
ENTRYPOINT ["java", "-jar", "/app.jar"]