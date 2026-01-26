FROM gradle:8.8 AS build
WORKDIR /app

# Copy gradle files first for better caching
COPY gradle gradle
COPY gradlew gradlew.bat build.gradle settings.gradle ./

# Ensure gradlew is executable
RUN chmod +x gradlew

# Copy source code
COPY src src

# Build the application (skip tests)
ENV SKIP_TESTS=true
RUN ./gradlew clean build --no-daemon --stacktrace -x test

FROM openjdk:21-ea-21-jdk-slim AS run

RUN adduser --system --group app-user

COPY --from=build --chown=app-user:app-user /app/build/libs/stock-feed-*.jar app.jar

EXPOSE 8080
USER app-user

CMD ["java", "-jar", "app.jar"]