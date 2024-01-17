FROM maven:3-openjdk-11 AS build
COPY..
RUN mvn clean package -DskipTests

FROM eclipse-temurin:11
COPY --from=build /target/banking-0.0.1-SNAPSHOT.jar banking.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","banking.jar"]