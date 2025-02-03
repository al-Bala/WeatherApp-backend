FROM maven:3.9-amazoncorretto-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:17
COPY --from=builder /app/target/weather-app.jar /weather-app.jar
ENTRYPOINT ["java","-jar","/weather-app.jar"]