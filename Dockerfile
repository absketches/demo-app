ARG MAVEN_IMAGE=maven:3.9.9-amazoncorretto-21
ARG RUNTIME_IMAGE=amazoncorretto:21-alpine

FROM ${MAVEN_IMAGE} AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -q -DskipTests package && \
    JAR_FILE="$(find target -maxdepth 1 -type f -name '*.jar' ! -name 'original-*' | head -n 1)" && \
    test -n "$JAR_FILE" && \
    cp "$JAR_FILE" /tmp/app.jar

FROM ${RUNTIME_IMAGE}
WORKDIR /app

COPY --from=build /tmp/app.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
