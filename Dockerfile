FROM gradle:8.8 AS build
WORKDIR /app

# Copy gradle files first for better caching
COPY gradle gradle
COPY gradlew build.gradle settings.gradle ./

# Copy source code
COPY src src

# Build the application
RUN ./gradlew clean build --no-daemon

FROM openjdk:21-jdk-slim AS run

RUN adduser --system --group app-user

COPY --from=build --chown=app-user:app-user /app/build/libs/stock-feed-*.jar app.jar

EXPOSE 8080
USER app-user

CMD ["java", "-jar", "app.jar"]