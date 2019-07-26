FROM openjdk:13-alpine
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test
ENTRYPOINT ./gradlew run
EXPOSE 80