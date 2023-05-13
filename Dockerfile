FROM openjdk:8
COPY ./target/Debtly-1.0-SNAPSHOT-shaded.jar ./
WORKDIR ./
CMD ["java", "-jar", "Debtly-1.0-SNAPSHOT-shaded.jar"]