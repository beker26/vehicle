# 🚗 Vehicle API

A RESTful API developed in **Java 17** using **Spring Boot 3.4.4**, following clean architecture practices with unit testing, database versioning, and full documentation via Swagger.

---

## 🛠️ Technologies & Tools

- Java 17
- Spring Boot 3.4.4
- Swagger 3.1 (OpenAPI for API documentation)
- JUnit + Mockito (unit testing)
- Flyway (database migration/versioning)
- H2 Database (in-memory)
- Hexagonal Architecture (partial implementation)

---

## 📦 Main Endpoints

> All endpoints are documented via Swagger:  
> 🔗 [`/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)

### 🔸 Create Vehicle
`POST /vehicles`  
Creates a new vehicle in the database.  
**Response:** `201 Created`

---

### 🔸 Update Vehicle
`PUT /vehicles/{id}`  
Updates an existing vehicle.  
**Response:** `200 OK` or `404 Not Found`

---

### 🔸 Delete Vehicle
`DELETE /vehicles/{id}`  
Deletes a vehicle from the database.  
**Response:** `204 No Content` or `404 Not Found`

---

### 🔸 Count Unsold Vehicles
`GET /vehicles/count-not-sold`  
Returns the number of unsold vehicles.  
**Response:** `200 OK` or `404 Not Found`

---

### 🔸 Vehicles by Year
`GET /vehicles/{year}/vehicles-year-manufacture`  
Returns the number of vehicles manufactured in a specific year.  
**Response:** `200 OK` or `404 Not Found`

---

### 🔸 Vehicles by Brand
`GET /vehicles/brand/{brand}`  
Returns the number of vehicles by brand (case-insensitive).  
**Response:** `200 OK` or `404 Not Found`

---

## 🚀 How to Run the Application

### ✅ Requirements
- Java 17 installed
- Maven 3.8+ installed (or use the Maven wrapper included)

### 🔧 Steps to run locally

1. Clone the repository:
```bash
git clone https://github.com/beker26/vehicle.git
cd vehicle
```

2. Run the application using Maven:
```bash
./mvnw spring-boot:run
```

3. Access the Swagger UI to explore the API:
```
http://localhost:8080/swagger-ui/index.html
```

The database used is **H2 in-memory**, and initial data is populated using **Flyway** migrations.

---

## 🧪 Running Tests

Unit tests were written using JUnit and Mockito:

```bash
./mvnw test
```

---

## 📌 Notes

- The project simulates a full development workflow: high-level modeling (C4 Model), agile organization (Jira board), schema versioning (Flyway), test coverage, and API documentation (Swagger).
- The frontend was not implemented due to time constraints. It was planned to be built using **FlutterFlow + Dart** (No Code).
- I also have a mobile app (iOS & Android) related to my passion for classic cars. You can check it out here:  
  🔗 [OldCarApp](https://www.oldcarapp.com/oldCarAppDownload)

---

## 👨‍💻 Author

**Rodrigo Beker**  
Software Engineer  
📧 beker_la@hotmail.com  
🔗 [GitHub - @beker26](https://github.com/beker26)
