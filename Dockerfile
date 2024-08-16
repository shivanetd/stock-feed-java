FROM gradle:8.8 AS build

COPY src/main ./src/main
COPY build.gradle settings.gradle  ./

RUN gradle clean build

FROM openjdk:21-jdk-slim AS run

RUN adduser --system --group app-user

COPY --from=build --chown=app-user:app-user /home/gradle/build/libs/stock-feed-*.jar app.jar

EXPOSE 8080
USER app-user

CMD ["java", "-jar", "app.jar"]