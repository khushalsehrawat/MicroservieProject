# MicroservieProject

A Spring Cloud-based microservices demo showcasing service registration, discovery, monitoring, and API Gateway routing. This project is ideal for:

- **Learners** exploring Spring Cloud Netflix stack (Eureka, Spring Boot Admin, Spring Cloud Gateway).  
- **Developers** building modular microservice architectures with centralized configuration.

---

## 🚀 Project Overview

**MicroservieProject** demonstrates:

1. **Service Registry (Eureka)** – Centralized discovery of microservices.  
2. **Admin Server** – Monitoring and managing service health via Spring Boot Admin UI.  
3. **API Gateway** – Routing and load‑balancing external requests to microservices.  
4. **Greet‑Api & Welcome‑Api** – Two sample microservices:  
   - **Greet‑Api** returns greeting messages.  
   - **Welcome‑Api** delegates to Greet‑Api and composes a “Welcome” response.  

Key features:  
- Zero‑configuration client registration with Eureka.  
- Dynamic routing through Spring Cloud Gateway using service names.  
- Health checks, metrics, and logs in centralized Admin console.  
- Simple REST endpoints demonstrating inter‑service calls.  

---

## 📁 Folder / File Structure

```
MicroservieProject/
├── API-Gateway/            # Spring Cloud Gateway app
│   ├── src/main/java/…     # Gateway configuration & filters
│   └── pom.xml             # Maven build file
├── Admin-Server/           # Spring Boot Admin Server
│   ├── src/main/java/…     # Admin server setup
│   └── pom.xml
├── Service-Registory/      # Eureka Server
│   ├── src/main/java/…     # Eureka server config
│   └── pom.xml
├── Greet-Api/              # “Greet” microservice
│   ├── src/main/java/…     # REST controller for greeting
│   └── pom.xml
├── Welcome-Api/            # “Welcome” microservice
│   ├── src/main/java/…     # REST controller calling Greet-Api
│   └── pom.xml
└── README.md               # This documentation
```

### Entry Points & Control Flow

1. **Service-Registory**  
   - **Main class:** `ServiceRegistryApplication.java`  
   - **Port:** `8761`  
   - **Function:** Starts Eureka server on port 8761.

2. **Admin-Server**  
   - **Main class:** `AdminServerApplication.java`  
   - **Port:** `9090`  
   - **Function:** Connects to Eureka and displays registered services in Spring Boot Admin UI.

3. **Greet-Api**  
   - **Main class:** `GreetApiApplication.java`  
   - **Port:** `8081`  
   - **Function:** Offers `GET /greet?name={name}` returning `"Hello, {name}!"`.

4. **Welcome-Api**  
   - **Main class:** `WelcomeApiApplication.java`  
   - **Port:** `8082`  
   - **Function:** Offers `GET /welcome?name={name}`; internally calls Greet-Api at `http://GREET-API/greet?name={name}` to compose `"Welcome! Hello, {name}!"`.

5. **API-Gateway**  
   - **Main class:** `ApiGatewayApplication.java`  
   - **Port:** `8080`  
   - **Function:**  
     - Routes `/greet/**` → `GREET-API`  
     - Routes `/welcome/**` → `WELCOME-API`  

---

## 🛠️ Setup Instructions

### Prerequisites

- **Java JDK 11+**  
- **Maven 3.6+** (`mvn` on PATH)  
- **Git**  
- *(Optional)* Docker (if running with containers)

### Clone the Repository

```bash
git clone https://github.com/khushalsehrawat/MicroservieProject.git
cd MicroservieProject
```

### Build All Services

Each module uses Maven. From the repo root:

```bash
mvn clean install
```

This compiles and packages all five projects.

---

## ▶️ Running the Project End‑to‑End

1. **Start Eureka (Service-Registory)**  
   ```bash
   cd Service-Registory
   mvn spring-boot:run
   # → Service registry available at http://localhost:8761/
   ```

2. **Start Admin Server**  
   ```bash
   cd ../Admin-Server
   mvn spring-boot:run
   # → Admin UI: http://localhost:9090/
   ```

3. **Start Greet‑Api**  
   ```bash
   cd ../Greet-Api
   mvn spring-boot:run
   ```

4. **Start Welcome‑Api**  
   ```bash
   cd ../Welcome-Api
   mvn spring-boot:run
   ```

5. **Start API Gateway**  
   ```bash
   cd ../API-Gateway
   mvn spring-boot:run
   # → Gateway: http://localhost:8080/
   ```

> **Tip:** You can start multiple services in parallel terminals or via your IDE (all have `main` in `src/main/java`).

### Environment Variables

All services use default ports and connect via Eureka. To override:

```bash
# Example for Welcome‑Api
export EUREKA_SERVER=http://localhost:8761/eureka/
export SERVER_PORT=8082
mvn spring-boot:run
```

---

## 📖 Usage Guide

### Sample Requests

- **Direct to microservice (bypasses gateway):**

  ```bash
  curl http://localhost:8081/greet?name=Khushal
  # → Hello, Khushal!
  ```

- **Via API Gateway:**

  ```bash
  curl http://localhost:8080/greet?name=Khushal
  # → Hello, Khushal!

  curl http://localhost:8080/welcome?name=Khushal
  # → Welcome! Hello, Khushal!
  ```

### UI & Monitoring

- **Eureka Dashboard:** http://localhost:8761/  
- **Spring Boot Admin:** http://localhost:9090/  
  - View health, metrics, and logs for all registered services.

---

## 🐞 Troubleshooting & Tips

| Issue                                           | Resolution                                                                 |
|-------------------------------------------------|-----------------------------------------------------------------------------|
| **Port already in use**                         | Change `server.port` in `application.properties` or set `SERVER_PORT` env. |
| **Service not registering in Eureka**           | Verify `eureka.client.serviceUrl.defaultZone` matches `http://localhost:8761/eureka/`. |
| **Gateway routing errors (404)**                | Confirm service names in `application.yml` match registered Eureka names.  |
| **Admin UI shows “UNKNOWN” status**             | Ensure Admin Server’s `spring.boot.admin.client.url` points to itself.      |

---

## 🤝 Contributing

1. Fork the repo  
2. Create a feature branch: `git checkout -b feature/YourFeature`  
3. Commit changes: `git commit -m "Add YourFeature"`  
4. Push: `git push origin feature/YourFeature`  
5. Open a Pull Request – we’ll review and merge!

---

## 🛣️ Roadmap

- [ ] Centralized Configuration with Spring Cloud Config  
- [ ] Circuit Breaker (Resilience4j) integration  
- [ ] Distributed tracing (Zipkin / Sleuth)  
- [ ] Docker Compose setup for all services  

---

## 📄 License

This project is licensed under the **MIT License**. See [LICENSE](LICENSE) for details.

---
*Happy microservicing!* 🚀
