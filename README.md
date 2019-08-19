# Hello World Web Application
[![Build Status](https://badge.buildkite.com/af0b7cd26de4130b01475f90bb8129710804c1084d0eab7811.svg)](https://buildkite.com/myob/iris-hello-world)

This is the solution for Hello World kata developed by Iris Hou. 

## Outline
This kata's requirement can be found here. :point_down:
https://github.com/MYOB-Technology/Accelerate_Mentor_Material/blob/master/acceleration-hello-world-web-application.md

## Getting Started

### Prerequisites
To run the application, you must have the following installed:
* Java 12

### Built With
* Java 12 - The source compatibility is Java 12, there is no assurance that it works with versions prior to 12.
* [JUnit](https://junit.org/junit4/) - Testing framework used
* Gradle 4.10.3 - Build, automate and deliver tool

## Running the application

### IntelliJ
Create new configuration, set the main class as
```
com.myob.iris.HelloWorldApplication
```

### Command Line
In the root directory of the project, run the following command:
```
./gradlew run
```

## Running Tests

### IntelliJ
Right click on the test folder in the project structure, click `Run 'All Tests'`

### Command Line
In the root directory of the project, run the following command:
```
./gradlew test
```

## Making Request
Requests can be made through Postman, curl or your http tool of choice.

### GET
`https://iris-hello-world.svc.platform.myobdev.com/`
The server will return user name and current date time (greeting)
`https://iris-hello-world.svc.platform.myobdev.com//users`
The server will return a list of user name without greeting

### POST
`https://iris-hello-world.svc.platform.myobdev.com//users?name=TestName`
The server will add user `TestName` to the list

### PUT
`https://iris-hello-world.svc.platform.myobdev.com//users?name=TestName&newName=TestName2`
The server will change the user name from `TestName` to `TestName2`

### DELETE
`https://iris-hello-world.svc.platform.myobdev.com//users?name=TestName`
The server will delete user `TestName` from list

### All other request
Making any other request will return a 501-request not implemented response

## Deployment
Currently deployed to Jupiter Preprod environment. The pipeline yml can be found in the .buildkite folder in the root directory, while the relevant pipeline scripts are in the ops folder. 
