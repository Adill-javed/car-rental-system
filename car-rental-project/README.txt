Car Rental Spring Boot Project
=============================

What is included:
- Spring Boot backend (package: com.example.carrental)
- REST API endpoints to list available cars, check availability, estimate, and rent
- Simple frontend (index.html, style.css, app.js) in src/main/resources/static
- sample SQL (src/main/resources/data.sql) to create database and seed cars

Prerequisites:
- Java 17+ installed
- Maven installed
- MySQL running locally with user `root` and password `pass` (change in application.properties if different)

How to use:
1. Create the database and tables by running the SQL in src/main/resources/data.sql.
   - You can run it in MySQL Workbench or: mysql -u root -p < src/main/resources/data.sql
   - (Alternatively, you can import the SQL file via your DB tool.)

2. Import the project into IntelliJ (Open -> choose the folder).
   Or build and run with maven:
     mvn clean package
     mvn spring-boot:run

3. Open http://localhost:8080 in your browser. The frontend will call the API endpoints.

Notes:
- If you want Spring Boot to auto-run `data.sql` on startup, set
  spring.datasource.initialization-mode=always
  in application.properties and ensure Spring Boot version supports it.
- Feel free to change DB credentials in src/main/resources/application.properties.

Enjoy! 🚗
