# EmpTrackPro

EmpTrackPro is a Spring Boot based Employee Management System that performs complete CRUD operations and showcases best practices in modern Java backend development.


 Tech Stack Used
 ----------------

- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- MySQL
- Lombok
- Spring Validation
- RESTful APIs
- Git & GitHub

---

Features Implemented
-----------------------------------------
CRUD Operations for Employee:
- Create, Read, Update, and Delete employee records
- DTO pattern used for clean API structure
- Address is embedded inside Employee as a value object

 Entity Structure:
- Employee is the main entity
- Address is an embedded value type
- Unique constraints handled with @Id and auto-generated IDs

 DTOs Used:
- EmployeeDTO
- AddressDTO

Helps in separating business layer from API layer and reduces unnecessary exposure of internal entities.

  Repository Layer:
- Built using JpaRepository<Employee, Long>
- Auto query support using Spring Data JPA

 Service Layer:
- Clean separation of business logic
- Update logic allows partial updates by checking for nulls
- Address update handled internally

 Controller Layer:
- RESTful endpoints with meaningful paths:
  - POST /employee/api/create
  - GET /employee/api/allEmployees
  - GET /employee/api/getById/{id}
  - PUT /employee/api/updateEmployee/{id}
  - DELETE /employee/api/deleteByEmployeeId/{id}

 Validation:
- Validation annotations added for fields (to be improved with custom/global exception handling)

Exception Handling:
- Setup for global exception handling using:
  - @ControllerAdvice
  - @ExceptionHandler
  - CustomException
- Handles validation errors, bad requests, and entity not found

 Swagger (to be integrated):
- Setup will be provided for Spring Boot 3.x + Java 17
- API documentation and testing from browser

---

Folder Structure
------------------------
SpringBoot-Application/
│
├── controller/ REST API controllers
├── service/ Business logic
├── repository/ JPA repositories
├── model/ JPA entities (Employee, Address)
├── dto/ Data Transfer Objects
├── exception/ Global & custom exception handling
├── config/ Swagger config (to be added)
└── application.properties DB & JPA configuration


---

Database Configuration (MySQL)
-----------------------------------
properties
-----------
spring.datasource.url=jdbc:mysql://localhost:3306/emp
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


Author
--------
Sandeep Sathyala
Backend Java Developer
GitHub: @SandeepSathyala

