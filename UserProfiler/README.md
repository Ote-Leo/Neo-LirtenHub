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
  - [API Document File](#api-document-file)

</hr>

## Requirements

## TODO

- [x] Edit Profile
- [x] Add bio
- [x] Set location manually
- [x] Set location automatically
- [x] Add multiple favorite Coding languages
- [x] Block users
- [x] Report users
- [x] Attach link to GitHub account
- [x] Choose set of interests
- [x] Choose preferences for Projects
- [x] Moderators see reported users

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

## API Document File

[API_doc](user/../API_doc.md)