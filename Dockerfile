FROM openjdk:13-alpine
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test
CMD ["java", "-jar", "./app.jar"]
EXPOSE 80