FROM openjdk:8
COPY ./target/Debtly1.0-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
CMD ["java", "-jar", "Debtly1.0-0.0.1-SNAPSHOT.jar "]