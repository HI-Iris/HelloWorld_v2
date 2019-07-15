# Hello World Web Application
[![Build Status](https://badge.buildkite.com/af0b7cd26de4130b01475f90bb8129710804c1084d0eab7811.svg)](https://buildkite.com/myob/iris-hello-world)

This Hello World application is developed by Iris Hou.

## Outline
Implement a basic Hello World web application without using any frameworks.

In it's simplest form, when calling the web application it should return your name, a greeting and the current date/time of the server.

I.e. If my name were Mark, when hitting the web server (http://localhost:8080) it should display "Hello Mark - the time on the server is 10:48pm on 14 March 2018"

By default your web application should just show your name however, you should also be able to do some additional tasks including:

You should be able to add Bob to your hello world, after calling the appropriate http requests it should display "Hello Mark and Bob - the time on the server is 10:59pm on 14 March 2018"
You should then be able to add Mary to your hello world, "Hello Mark, Bob and Mary - the time on the server is 10:59pm on 14 March 2018"
You can also remove people from the greeting, you could remove Bob while keeping Mary, "Hello Mark & Mary - the time on the server is 11:01pm on 14 March 2018"
You should be able to have a custom url to get just a list of people's names without the greetings
You should be able to change someones name, if you have already added Bob and Bob now want's to be called Dave, you should be able to do that.
You can never remove yourself from the world - in this world Mark will always be there, in your world you should always be there!
You can also assume that everyone in your hello world has a unique name, there is only ever one Mark, one Bob, one Mary, etc.
You don't need to worry about forms for adding people, just interact using curl or postman or your http tool of choice to interact with the Hello World.

## Getting Started

### Prerequisites
To run the application, you must have the following installed:
* Java 12

### Built With
* Java 12 - The source compatibility is Java 12, there is no assurance that it works with versions prior to 12.
* [JUnit](https://junit.org/junit4/) - Testing framework used

## Running the application

### IntelliJ

### Command Line
In the root directory of the project, run the following command:
```
./gradlew 
```

## Running Tests

### IntelliJ
Right click on the test folder in the project structure, click `Run 'All Tests'`

### Command Line
In the root directory of the project, run the following command:
```
./gradlew test
```

##Making Request
Requests can be made through Postman, curl or your http tool of choice.

###GET
`localhost:8080` the server will return user name and current date time (greeting)
`localhost:8080/users` the server will return a list of user name without greeting

###POST
`localhost:8080/users?name=TestName` the server will add user `TestName` to the list

###PUT
`localhost:8080/users?name=TestName&newName=TestName2` the server will change the user name from `TestName` to `TestName2`

###DELETE
`localhost:8080/users?name=TestName` the server will delete user `TestName` from list

###All other request
Making any other request will return a 501-request not implemented response

## Deployment
Currently deployed to Jupiter Preprod environment. The pipeline yaml can be found in the .buildkite folder in the root directory, while the relevant pipeline scripts are in the ops folder. 
