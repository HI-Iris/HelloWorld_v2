FROM openjdk:13-alpine

WORKDIR /app

COPY ./build/libs/HelloWorld-1.0-SNAPSHOT.jar ./app.jar

CMD ["java", "-jar", "./app.jar"]

EXPOSE 80