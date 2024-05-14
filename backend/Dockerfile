
FROM FROM eclipse-temurin:22-jdk-jammy AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:22-jre-jammy

WORKDIR /app

COPY --from=build /app/target/server-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8080

CMD ["java", "-jar", "server.jar"]