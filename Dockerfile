FROM openjdk:13-alpine
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test
CMD ["./gradlew", "run"]
EXPOSE 80