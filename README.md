## Overview
This program is a deep web crawler to go upto provided depth (max limits apply). Deep crawling service is exposed as REST endpoint and have basic HTTP authentication. It also uses caching (Open source Caffeine implementation) mechanism to improve performance for repeated urls.

## Implementation
The solution delivered here is a Java project implemented as a Spring Boot / Gradle project.


## Building the program
In order to build the program, the following is required

- Java 8 JDK
- Gradle 4.1.x

In order to start a project build this project to your development machine then at the top-level directory type:
$ ./gradlew clean build


## Running the program in local mode
After building the application you can run the service by performing the following steps:

1. At the top-level project directory run a local instance of ActiveMQ by using the supplied docker-compose script:
$ docker-compose up -d

1. At the top-level directory run the following command to start the Spring Boot executable which launches the application:
$ java -jar ./build/web-crawler-service-*[1.0.0-SNAPSHOT]*-exec.jar

To run the service in a different profile use, say local
$ java -jar -Dspring.profiles.active=local ./build/web-crawler-service-*[1.0.0-SNAPSHOT]*-exec.jar

NOTE: the actual version number will vary depending on your current revision

Now the service is available at:
http://localhost:8100/web-crawler-service/crawler?url=<pageUrl>&depth=<depthValue>

Example for a quick test over PostMan or any rest client:

http://localhost:8100/web-crawler-service/crawler?url=http://spring.io/&depth=1

## Additional Notes
*** Key features ***
Following fields in response are available for any website:
- Url
- Valid - True or false
- title
- list of child modes

- Similar child urls are ignored to prevent looping.
- Max allowed depth for the service is used to prevent accidental high depth queries that may abrupt the service causing DOS.
- Cache implementation Cafeinne is used that provides good support for auto expiry of cached items, well internally management of cached objects - like count etc.
- Swagger API docs and UI specs are available - http://localhost:8100/web-crawler-service/swagger-ui.html. Little config give it without effort but handy and useful for clients/testers

Some tools used for speeding up the development:
- https://start.spring.io/ for spring boot app structure
- editor.swagger.io - For openAPI spec (available in source) for auto generating Rest controllers and models
- JSoup is used for fetching web contents. Multi-threading was attempted but as crawler moves further on previous results hence didn't have time to make it work with Java Futures.
- Spring rest server is generated from http://editor.swagger.io using OpenAPI spec.
- Standard development practice that I normally follow at work is used.
- Basic security is designed in OpenAPI spec though JWT or auth2 can be used to protect the endpoint.


## Improvements TODO
- Proxy support for HttpClient
- Automation tests can be added but running out of time. Happy that the whole solution is working!!!

