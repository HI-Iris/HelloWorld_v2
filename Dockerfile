FROM openjdk:13-alpine
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test
EXPOSE 80
ENTRYPOINT ./gradlew run