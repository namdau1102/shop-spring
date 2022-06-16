# Animal store
<img alt="GitHub top language" src="https://img.shields.io/github/languages/top/grzegorz103/animal-store">

## Table of contents
* [General info](#general-info)
* [Demo](#demo)
* [Technologies](#technologies)
* [Features](#features)
* [Setup](#setup)

## General info
Store system with miscallenous types of animals, foods, accessories.
## Try live demo:    
https://animal-store.herokuapp.com/  

### Example accounts
| __Login__ | __Password__ | Role |
| -------------- | ------------ | --- |
| admin | admin | Admin |
| user | user | User |
| user2 | user2 | User |   

## Technologies
* Spring (Boot, MVC, Data JPA, Security)
* Hibernate
* PostgreSQL, H2
* JSP
* Maven, Lombok

## Setup
### Prerequisites

- Java 8 or greater

### Deployment

```
$ mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
Run browser and head to ```http://localhost:4200```    
