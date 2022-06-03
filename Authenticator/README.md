# **_LirtenHub Authentication App_**

</hr>

## Table of Contents

- [**_LirtenHub Authentication App_**](#lirtenhub-authentication-app)
  - [Table of Contents](#table-of-contents)
  - [Requirements](#requirements)
  - [TODO](#todo)
  - [Dependencies](#dependencies)
  - [Java Sprig Dependencies](#java-sprig-dependencies)
    - [1. Lombok](#1-lombok)
    - [2. Java Mail Sender](#2-java-mail-sender)
    - [3. Spring Web](#3-spring-web)
    - [4. Spring Security](#4-spring-security)
    - [5. PostgreSQL Driver](#5-postgresql-driver)
    - [7. Spring Data JPA](#7-spring-data-jpa)
  - [Usage Instructions](#usage-instructions)
  - [CURL](#curl)

</hr>

## Requirements

## TODO

- [x] User SignUp
- [x] User LogIn
- [x] User LogOut
- [x] Send Token Confirmation Mail
- [x] Authenticate Tokens

  - [x] Incase that the token hasn't expire
  - [ ] otherwise, remail a new token

- [ ] Delete User
- [ ] Establish a User session
- [ ] Adding Unit Tests

## Dependencies

1. [OpenJDK11](https://openjdk.java.net/projects/jdk/11/)
2. [Gradle7+](https://gradle.org/)
3. [PostgreSQL14](https://www.postgresql.org/)
4. [MailDev](https://github.com/maildev/maildev), a dummy email client for mail authentication
5. [Java Spring 2.6+](https://spring.io/)

## Java Sprig Dependencies

Though the following are included on the `build.gradle` it would nice to know what you're getting into before hand.

### 1. Lombok

Java `annotation` library for reducing boiler plate code.

### 2. Java Mail Sender

Send emails using `JavaMail` & Spring Frameworks `JavaMailSender`

### 3. Spring Web

Build web, including RESTFUL applications using Spring MVC. Uses `apache Tomcat` as the default server.

### 4. Spring Security

Highly Customizable authentication & access-control framework for spring applications.

### 5. PostgreSQL Driver

A JDBC & R2DBC driver that allow Java programs to connect to a PostgreSQL database using standard data-base independent Java code.

### 7. Spring Data JPA

Persist data in SQL stores with Java Persistence API using `SpringData` & `Hibernate`.

## Usage Instructions

1. Create a postgres database under the name of `registration`
2. Update the `application.yml` with your postgres `username` and `password`, at port 8080
3. Instantiate a `MailDev` client at port 1025

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
```
