# Microservices Blog Application
This is a simple blog application designed with microservices architecture. The application is divided into three microservices: Blog Service, Comment Service, and Reaction Service. Each microservice is responsible for a specific functionality, making the application scalable and easy to maintain.

# Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Setup](#setup)
      - [Blog Service](#blog-service)
      - [Comment Service](#comment-service)
      - [Reaction Service](#reaction-service)
- [API Endpoints](#api-endpoints)
  - [Blog Service Api](#blog-service-api)
  - [Comment Service Api](#comment-service-api)
  - [Reaction Service Api](#reaction-service-api)


## Features

- Blog Service: Manages blog posts, including creation, retrieval, updating, and deletion of blog posts.

- Comment Service: Handles comments for each blog post. Users can add, view, and delete comments.

- Reaction Service: Provides the ability for users to react to blog posts with different emoticons (like, love, laugh, etc.).

## Getting Started

### Prerequisites

- Make sure you have the following installed on your system:
- Spring Boot 3
- Java 17+
- Maven

### Setup and Run
- Run all the services on the same machine.
  - If the services to be run on different machine then following properties must be set explicitly
    - Blog service
      ```properties
          backend.comment-service.hostname: http://<IP_ADDRESS>
          backend.comment-service.port: 8001

          backend.reaction-service.hostname: http://<IP_ADDRESS>
          backend.reaction-service.port: 8002
      ```
      - Comment service
      ```properties
          backend.blog-service.hostname: http://<IP_ADDRESS>
          backend.blog-service.port: 8000

          backend.reaction-service.hostname: http://<IP_ADDRESS>
          backend.reaction-service.port: 8002
      ```
      - Reaction service
      ```properties
          backend.blog-service.hostname: http://<IP_ADDRESS>
          backend.blog-service.port: 8000

          backend.comment-service.hostname: http://<IP_ADDRESS>
          backend.comment-service.port: 8001
      ```
- Used an in memory H2 database 
- But if external database is intended to be used then following configuration need to set/changed in the application.properties file: <br>
  ```properties
        spring.datasource.url= jdbc:h2:mem:blogdb
        spring.datasource.driverClassName= org.h2.Driver
        spring.datasource.username= admin
        spring.datasource.password= admin
        spring.jpa.hibernate.ddl-auto= create
        spring.jpa.database-platform= org.hibernate.dialect.H2Dialect
  ```
- Port Configuration
   - Blog Service: 8000
   - Comment Service: 8001
   - Reaction Service: 8002

- #### Blog Service
  - Move to blog-service directory
  - Build the project with the following the command this will generate a *.jar file in target/ folder
    ```bash
      mvn clean install
  - Move to target/ folder and run the following the command to run the application
    ```bash
    cd target/
    java -jar blog-service-0.0.1-SNAPSHOT.jar
- #### Comment Service
  - Move to comment-service directory
  - Build the project with the following the command this will generate a *.jar file in target/ folder
    ```bash
      mvn clean install
  - Move to target/ folder and run the following the command to run the application
    ```bash
    cd target/
    java -jar comment-service-0.0.1-SNAPSHOT.jar
- #### Reaction Service
  - Move to reaction-service directory
  - Build the project with the following the command this will generate a *.jar file in target/ folder
    ```bash
      mvn clean install
  - Move to target/ folder and run the following the command to run the application
    ```bash
    cd target/
    java -jar reaction-service-0.0.1-SNAPSHOT.jar
    
## API Endpoints
- Detailed information about APIs is included in the postman collection file

- #### Blog Service Api
  - Fetch a Single Blog: Use GET mapping with API endpoint /api/blogs/{id}.
  - Fetch All Blogs: Use GET mapping with API endpoint /api/blogs/.
  - Create a Single Blog: Use POST mapping with API endpoint /api/blogs/.
    - Request Body
      ```json 
      {
      "title":"Blog on C++",
      "content":"C++ is very pupular for embedded applications"
      }
      ```
  - Update a Single Blog: Use PUT mapping with API endpoint /api/blogs/{id}.
    - Request Body
    ```json 
    {
    "title":"Blog on C++",
    "content":"C++ is very pupular for embedded applications updated"
    }
    ```
  - Delete a Single Blog: Use DELETE mapping with API endpoint /api/blogs/{id}.
  - Search for Blogs: Use GET mapping with API endpoint /api/blogs/search?title=some_text.
  - #### Comment Service Api
    - Fetch Comments for a Specific Blog: Use GET mapping with API endpoint /api/comments/blog/{id}.
    - Add a Comment for a Blog: Use POST mapping with API endpoint /api/comments.
      - Request Body
      ```json
      {
      "blogId":8,
      "text":"First Comment on C++"
      }
      ```
    - Fetch a Single Comment: Use GET mapping with API endpoint /api/comments/{id}.
    - Update a Single Comment: Use PUT mapping with API endpoint /api/comments/{id}.
      - Request Body
        ```json
        {
        "blogId":8,
        "text":"First Comment on C++"
        }
        ```
    - Delete All Comments for a Specific Blog: Use DELETE mapping with API endpoint /api/comments/blog/{id}/comments.

  - #### Reaction Service Api
    - Add a Reaction: Use POST mapping with API endpoint /api/reactions/add.
      - Request Body
      ```json
      {
      "reactionType":"HEART",
      "targetType":"COMMENT",
      "targetId":3
      }
      ```
      ```json
        {
      "reactionType":"HEART",
      "targetType":"BLOG",
      "targetId":5
      }
      ```
    - Delete a Reaction: Use DELETE mapping with API endpoint /api/reactions/delete/{id}.
  


