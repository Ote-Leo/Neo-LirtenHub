# **_Neo-LirtenHub Project Managing App_**

</hr>

## Table of Contents

- [**_Neo-LirtenHub Project Managing App_**](#neo-lirtenhub-project-managing-app)
  - [Table of Contents](#table-of-contents)
  - [Requirements](#requirements)
  - [TODO](#todo)
  - [Dependencies](#dependencies)
  - [Java Spring Dependencies](#java-spring-dependencies)
    - [1. Lombok](#1-lombok)
    - [2. Spring Web](#2-spring-web)
    - [3. MongoDB Driver](#3-mongodb-driver)
    - [4. RabbitMQ](#4-rabbitmq)
  - [Usage Instructions](#usage-instructions)
  - [API Document File](#api-document-file)

</hr>

## Requirements

## TODO

- [x] Create a project with description
- [x] Edit Project
- [x] Delete Project
- [x] Apply on a project
- [x] Remove a project applicant
- [x] Accept a project applicant
- [x] User finish his project task
- [x] Project owner pay user who finished his task 

## Dependencies

1. [OpenJDK11](https://openjdk.java.net/projects/jdk/11/)
2. [Gradle7+](https://gradle.org/)
3. [MongoDB](https://www.mongodb.com/)
4. [Java Spring 2.6+](https://spring.io/)
5. [RabbitMQ](https://www.rabbitmq.com/)

## Java Spring Dependencies

Though the following are included on the `build.gradle` it would nice to know what you're getting into before hand.

### 1. Lombok

Java `annotation` library for reducing boiler plate code.


### 2. Spring Web

Build web, including RESTFUL applications using Spring MVC. Uses `apache Tomcat` as the default server.


### 3. MongoDB Driver

MongoDB is a document database with the scalability and flexibility that you want with the querying and indexing that you need.

### 4. RabbitMQ 

RabbitMQ is lightweight and easy to deploy on premises and in the cloud. It supports multiple messaging protocols. RabbitMQ can be deployed in distributed and federated configurations to meet high-scale, high-availability requirements.


## Usage Instructions

1. Update the `application.properties` with your mongodb `username` and `password`, at port 8080

## API Document File

[API_doc](user/../API_doc.md)