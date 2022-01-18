# ShopOwnerSite

ShopOwnerSite is a website of companies that allows them to conveniently manage their product databases, monitor their rating on the market, and take orders.

## How to launch it?
1. The first step is to create a separate network in docker if there is no such network: "docker network create shared-network", it is needed to connect containers to each other for data exchange.
2. Further we recommend using IntelliJ IDEA for this. All you have to do is clone any module from this repository via github clone in the IDE. You need to open in IDE git -> clone -> paste in URL the repository of modules that you want to connect to the system.
3. Then go to the Maven compiler and select swagger-spring/Lifecycle and use "package", a "jar" file will be created. 
4. Then you run the docker-compose file, which will do the rest of the work on packaging the service for you. This way you can start all the services in turn.

## Overview  
This server was generated by the [swagger-codegen](https://github.com/swagger-api/swagger-codegen) project.  
By using the [OpenAPI-Spec](https://github.com/swagger-api/swagger-core), you can easily generate a server stub.  
This is an example of building a swagger-enabled server in Java using the SpringBoot framework.

The underlying library integrating swagger to SpringBoot is [springdoc-openapi](https://github.com/springdoc/springdoc-openapi)

You can view the api documentation in swagger-ui by pointing to  
http://localhost:9191/api  
