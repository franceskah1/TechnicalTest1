# TechnicalTest1
Assignment 

Create a Spring Boot microservice that exposes 4 REST APIs to retrieve data from a database, with JPA  :

- v1/data/{id} : Retrieves a single row from the database based on the provided id parameter.

- v1/data : Inserts a new row into the database based on the request body.

- v1/data/{id} : Updates an existing row in the database based on the provided id path parameter and request body.

- v1/data/{id} : Deletes a single row from the database based on the provided id parameter.

The database table should have the following columns:
id: A numeric auto-incrementing field.
title: A string field.
date1: A timestamp field.
qty: A numeric field.
Please provide the SQL script to create the table.
Please use OpenAPI/Swagger annotations to document almost the v1/data INSERT endpoint and the data structures used in it.

Plus, optional :
Include a method in your microservice that increments the "qty" field by one, every fifth minute of every hour, or simply describe how you would do it.

Swagger URL http://localhost:8081/technicaltest/swagger-ui/index.html
