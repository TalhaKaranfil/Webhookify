FROM eclipse-temurin:22-jdk AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:22.0.1_8-jre-ubi9-minimal

COPY --from=build /target/server-0.0.1-SNAPSHOT.jar server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "server.jar"]