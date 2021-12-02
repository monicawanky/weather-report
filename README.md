## Introduction
This is a Spring Boot-based application for creating an HTTP Service that reports weather of the country.

## Prerequisites

* Java 13+
* Maven 3.8.1+
* Redis 6.2.5+
* Command Line Tools
* Your preferred IDE
	*  IntelliJ IDEA
	*  VS Code

## Special Configurations
Normally, you don't need to do any configuration. You can config the following item by modify the file 
```src/main/resources/application.properties```:
>server.port=
>
>spring.redisson.address=

## Getting Started
The application built using Maven. You can build a jar file and run it from the command line

>mvn clean package
>
>java -jar target/*.jar

After starting the application, visiting 
[http://localhost:8080/v1/weather?city=melboune](http://localhost:8080/v1/weather?city=melboune) in your browser will output the following JSON payload

>{ 
> "wind_speed": 20, 
> "temperature_degrees": 29 
>} 

**Note:** The unit of wind_speed is km/h and temperature_degrees is celsius.

## Source
[Weather Stack](https://weatherstack.com) - Primary

[Open Weather Map](https://openweathermap.org) - Secondary
