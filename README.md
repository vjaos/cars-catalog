[![Build Status](https://travis-ci.com/vjaos/cars-catalog.svg?branch=master)](https://travis-ci.com/vjaos/cars-catalog)
[![codecov.io](https://codecov.io/gh/vjaos/cars-catalog/coverage.svg?branch=master)](https://codecov.io/gh/vjaos/cars-catalog)
### Content Table:
* [Description](#description)  
* [Usage](#usage)  
* [Built With](#built-with)  

### Description
This project consist of two main parts: `Api-Server` and `UI`  
Let's take a closer look at each of them.

#### API-server
It is a server based on MVC pattern which is implemented using [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)

It has following endpoints: 
 
|Method| Endpoint  | Parameters | Result |
|------| ------------- | ------------- | -------|
|GET   | `/api/cars`  | No parameters  |JSON object with 2 fields `total_count` and `cars_list`| 
|POST  | `/api/cars` | JSON with objects parameters| 1)Return created object when response status is `CREATED`<br>2)Error message with response status `CONFLICT` when object with given data already exists|
|DELETE | `/api/cars/{id}` | `id` of object to be deleted |1)Response with status `NO_CONTENT` when object successfully deleted<br>2)Error message with response status `NOT_FOUND` when object with given id not found|
|GET| `/api/cars/statistics/`| No parameters | Database statistics |

also `API-server` use [Spring JPA](https://spring.io/projects/spring-data-jpa) 
to access to [PostgreSQL](https://www.postgresql.org/) database 
and [Liquibase](https://www.liquibase.org/) for tracking, managing 
and applying database schema changes
#### UI
Just a simple user interface built with [VueJs](https://vuejs.org/) as a framework for creation user interfaces
<br>[Vuex](https://vuex.vuejs.org/) as a state management pattern
<br>[Axios](https://github.com/axios/axios) as HTTP client
<br>[Vuetify](https://vuetifyjs.com) as Material Design Component Framework


### Usage
Make sure you have `Docker` installed on your computer

    $ git clone https://github.com/vjaos/cars-catalog.git
    
    $ docker-compose up
    
Actually, you don't even need to clone  the whole project  
All you need is `docker-compose.yml` and `.env`(Optional) files, because 
Images of api-server and ui already pushed to dockerhub

### Built with:
[Spring Framework](https://spring.io/)  
[VueJS](https://vuejs.org/)  
[Liquibase](https://www.liquibase.org/)  
[Docker](https://www.docker.com/)  
[Maven](https://maven.apache.org/)  
[TravicCI](https://travis-ci.org/)

