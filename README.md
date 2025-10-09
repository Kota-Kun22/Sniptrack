# 🔗 SnipTrack — Smart URL Shortener & Analytics API

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Built with Spring Boot](https://img.shields.io/badge/Built%20with-Spring%20Boot%203-brightgreen)](https://spring.io/projects/spring-boot)
[![Java 21](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/downloads/)
[![Spring Security](https://img.shields.io/badge/Security-Spring%20Security%206-green)](https://spring.io/projects/spring-security)
[![JWT Authentication](https://img.shields.io/badge/Auth-JWT-red.svg)](https://jwt.io/)
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue.svg)](https://www.postgresql.org/)
[![Docker Ready](https://img.shields.io/badge/Containerized-Docker-blue.svg)](https://www.docker.com/)

---

> **Snip it short. Track it smart.**
>
> SnipTrack is a full-featured **URL shortener and analytics backend** built with **Spring Boot 3**, **Spring Security 6**, and **JWT Authentication**.  
> It enables users to **shorten URLs**, **track clicks**, and **analyze performance** over time — all with secure authentication and clean RESTful design.

---

## ✨ Key Features

* 🔐 **User Authentication**
    * Register and login with JWT-based authentication.
    * Role-based access (`USER`).

* 🔗 **URL Shortening**
    * Generate short URLs that redirect to long original URLs.
    * Each user’s URLs are securely stored and accessible only by them.

* 📊 **Analytics**
    * Track every click event (timestamp, date, user).
    * View analytics for specific URLs within date ranges.
    * Get total clicks per day across all URLs owned by a user.

* 🚀 **Redirect Service**
    * Accessing the short URL automatically redirects (HTTP 302) to the original link.
    * Tracks and logs each click event for analytics.

* 🧩 **Modular Architecture**
    * Controllers → Services → Repositories with clear separation of concerns.
    * DTO-based data transfer and clean mapping.

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-------------|
| **Backend Framework** | Spring Boot 3.x |
| **Security & Auth** | Spring Security 6, JWT |
| **Database** | PostgreSQL |
| **ORM** | JPA / Hibernate |
| **Build Tool** | Maven |
| **Language** | Java 21 |
| **Containerization** | Docker |
| **Mapping** | ModelMapper |
| **Lombok** | Reduces boilerplate (getters/setters/constructors) |

---

## 🧭 API Endpoints Overview

### 🧑‍💻 **Authentication Controller** — `/api/auth`

| Method | Endpoint | Description |
|---------|-----------|-------------|
| `POST` | `/public/register` | Register a new user with username, email, and password. |
| `POST` | `/public/login` | Authenticate existing user and return JWT token. |

---

### 🔗 **URL Mapping Controller** — `/api/urls`

| Method | Endpoint | Description |
|---------|-----------|-------------|
| `POST` | `/shorten` | Shorten a given URL (requires authentication). |
| `GET` | `/myurls` | Retrieve all URLs created by the logged-in user. |
| `GET` | `/analytics/{shortUrl}?startDate=...&endDate=...` | Get analytics for a specific short URL over a date range. |
| `GET` | `/totalClicks?startDate=...&endDate=...` | Get total clicks per day across all of a user’s URLs. |

---

### 🔁 **Redirect Controller**

| Method | Endpoint | Description |
|---------|-----------|-------------|
| `GET` | `/{shortUrl}` | Redirects the short URL to the original URL (HTTP 302). Tracks click events automatically. |

---

## 🗃️ Example Request/Response

### **Shorten URL**

**Request**
```http
POST /api/urls/shorten
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json

{
  "originalUrl": "https://example.com"
}
```
**Response**
```http
{
  "shortUrl": "http://localhost:8080/abc123",
  "originalUrl": "https://example.com",
  "createdAt": "2025-10-09T14:30:00Z"
}
```
## ⚙️ Local Setup & Run
### 🧩 Prerequisites  

Make sure you have installed:

* Java 21
* Maven
* PostgreSQL
* Docker
(optional but recommended)

## 🛠️ Setup Instructions
1. Clone the repository:

  ```` 
   git clone https://github.com/Kota-Kun22/SnipTrack.git
   cd SnipTrack
 ````

2. Configure Environment Variables
 - Create a `.env` file in the root directory of the project and add the following:
3. Run the application
4. Open the browser and navigate to `http://localhost:8080`

## 🐳 Docker Setup
* Create the Docker file
```dockerfile
# Use the official OpenJDK image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the project jar file
COPY target/sniptrack-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## 🐋 Build and Run with Docker
*  Build the jar file
``` mvn clean package -DskipTests ```

* Build Docker image 
``` docker build -t sniptrack:latest . ```

* Run Docker container
``` docker run -p 8080:8080 sniptrack:latest ```

## ☁️ Deployment Notes

This app can be deployed easily on:

- [Render](https://render.com/)
- [Railway](https://railway.app/)
- [AWS Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/)
- [Heroku](https://www.heroku.com/)


## 🧠 Future Improvements

- Implement a role-based admin panel for enhanced user management and access control.
- Integrate with analytics platforms (e.g., Google Analytics) for advanced URL performance insights.
- Add click heatmaps and geolocation-based statistics to visualize user engagement.
- Introduce Redis caching for faster URL lookups and improved backend performance.


---

## 🤝 Contributing

Contributions are always welcome!

1. Fork the repo
2. Create a new branch (`feature/awesome-feature`)
3. Commit your changes
4. Open a Pull Request 🎉

---

## 🪪 License

This project is licensed under the [MIT License](LICENSE).

---

## 🖥️ Frontend Repository

The frontend for SnipTrack is available here: [SnipTrack Frontend](https://github.com/Kota-Kun22/SnipTrack-Frontend)  
🚀 Open for enhancement – contributions and improvements are welcome! Let's build something amazing together.



## 💬 Author

**Harsh Rastogi**  
📧 [harshrastogi.dev@gmail.com](mailto:harshrastogi.dev@gmail.com)  
🔗 [LinkedIn](https://www.linkedin.com/in/harshrastogi2103/) | [GitHub](https://github.com/Kota-Kun22)
