# **_LirtenHub User App_**

</hr>

## Table of Contents

- [**_LirtenHub User App_**](#lirtenhub-user-app)
  - [Table of Contents](#table-of-contents)
  - [Requirements](#requirements)
  - [TODO](#todo)
  - [Dependencies](#dependencies)
  - [Java Spring Dependencies](#java-spring-dependencies)
    - [1. Lombok](#1-lombok)
    - [2. Spring Web](#2-spring-web)
    - [3. MongoDB Driver](#3-mongodb-driver)
  - [Usage Instructions](#usage-instructions)
  - [CURL](#curl)

</hr>

## Requirements

## TODO


- [x] project_preference
- [x] add_interests
- [x] github_link
- [x] add bio
- [x] block user
- [ ] edit profile
- [ ] set location 

## Dependencies

1. [OpenJDK11](https://openjdk.java.net/projects/jdk/11/)
2. [Gradle7+](https://gradle.org/)
3. [MongoDB](https://www.mongodb.com/)
4. [Java Spring 2.6+](https://spring.io/)

## Java Spring Dependencies

Though the following are included on the `build.gradle` it would nice to know what you're getting into before hand.

### 1. Lombok

Java `annotation` library for reducing boiler plate code.


### 2. Spring Web

Build web, including RESTFUL applications using Spring MVC. Uses `apache Tomcat` as the default server.


### 3. MongoDB Driver

MongoDB is a document database with the scalability and flexibility that you want with the querying and indexing that you need.


## Usage Instructions

1. Update the `application.properties` with your mongodb `username` and `password`, at port 8080

## CURL

```curl
curl --location --request POST 'localhost:8080/api/v1/registration' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Ote",
    "lastName":  "Leo",
    "email":     "ote@leo.com",
    "password":  "password"
}'
- ```