
FROM eclipse-temurin:17-jdk-focal AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jre-focal

WORKDIR /app

COPY --from=build /app/target/server-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8080

CMD ["java", "-jar", "server.jar"]