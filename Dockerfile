# Build stage
FROM openjdk:17-slim AS build

# Install sbt
RUN apt-get update && \
    apt-get install -y curl && \
    curl -L https://github.com/sbt/sbt/releases/download/v1.9.7/sbt-1.9.7.tgz | tar xzf - -C /usr/local && \
    ln -s /usr/local/sbt/bin/sbt /usr/local/bin/sbt

WORKDIR /build
COPY . /build

# Build the application
RUN sbt clean stage

# Run stage
FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=build /build/target/universal/stage /app

# Expose the port the app runs on
EXPOSE 9000

# Run the application
CMD ["/app/bin/play-scala-webapp"]