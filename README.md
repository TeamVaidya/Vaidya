# 🌿 Vaidya: Holistic Healthcare Microservices Platform

**🚀 Empowering Wellness Through Scalable, Modular Spring Boot Microservices**

---

![Build Status](https://img.shields.io/badge/build-passing-brightgreen) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F) ![License](https://img.shields.io/badge/license-MIT-blue) ![Deployed](https://img.shields.io/badge/deployed-Railway-brightgreen)

## 🌟 Introduction

Vaidya is a cutting-edge healthcare management system built on **Spring Boot** with a microservices architecture. Named after the Sanskrit word for "physician" (वैद्य), Vaidya streamlines patient-doctor interactions through five vibrant, interconnected services: user authentication, appointment scheduling, prescription management, user administration, and slot allocation. 🎉 It’s designed for scalability, security, and ease of use, leveraging RESTful APIs to deliver a seamless healthcare experience.


## 🛠️ Architecture Overview

Vaidya’s microservices communicate via REST APIs, orchestrated through a lightweight API Gateway. Dive into the high-level architecture below:

```mermaid
graph TD
  A[User] -->|Request| B((API Gateway))
  B --> C[Login & Register Service]
  B --> D[Appointment Service]
  B --> E[Prescription Service]
  B --> F[User Management System]
  B --> G[Slot Service]
  C -->|Data| H[Database Cluster]
  classDef default fill:#f9f,stroke:#333,stroke-width:2px;
```
  








## 🚀 Features

### 1. 🔐 Login & Register Service
- Secure JWT-based authentication with HashiCorp Vault. 🛡️
- User registration with role assignment (Patient, Doctor, Admin). 👤

### 2. 📅 Appointment Service
- Book, modify, or cancel appointments effortlessly. 🗓️
- Syncs with Slot Service for real-time availability. ⚡
- Track appointment history for seamless management. 📜

### 3. 💊 Prescription Service
- Doctors create prescriptions and log vitals (e.g., BP, fever, weight). 🩺
- Viewable prescription history for patients and doctors. 📊
- Full CRUD support for prescription data. ✍️

### 4. 👨‍💼 User Management System (UMS)
- Admin portal to manage all users with ease. 🛠️
- Edit, delete, or view user details with role-based access. 🔐
- Centralized control over platform access. 🌐

### 5. ⏰ Slot Service
- Create and manage appointment slots dynamically. 🕒
- Real-time CRUD operations for slot availability. 🔄
- Seamless integration with Appointment Service. 🔗

## 🗄️ Database Schema

![Vaidya Database Schema](https://i.imgur.com/SnDdEYC.jpeg)

🌈 *Explore the structured foundation of Vaidya’s data management with this colorful schema visualization.*


## 🛠️ Tech Stack

- **Backend**: Spring Boot 3.x, Spring Data JPA, Spring Security 🛠️
- **Database**: MySQL 💾
- **API**: RESTful with JSON payloads 📡
- **Authentication**: JWT (JSON Web Tokens) using HashiCorp Vault 🔑
- **Build Tool**: Maven 🛡️
- **Deployment**: Railway 🚀
- **Monitoring**: Prometheus & Grafana 📈
- **Health Checks**: Spring Boot Actuator 🩺

## 📋 Prerequisites

- Java 17+ ☕
- Maven 3.3.3+ 🛠️
- MySQL 💾
- IDE (e.g., IntelliJ IDEA, Eclipse) 💻
- Postman (for API testing) 🚀
- Prometheus & Grafana (for monitoring) 📊
- Spring Boot Actuator (for health checks) 🩺


## 🛠️ Installation

Follow these steps to set up Vaidya locally:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/TeamVaidya/vaidya.git
   cd vaidya
   ```
2. **Set up the Database:**
     * Create a MySql database:
        ```bash
        CREATE DATABASE vaidya_db;
        ```
    * Update `application.properties` in each microservice with your database credentials:
        
        ```bash
        spring.datasource.url=jdbc:mysql://localhost:3306/vaidya_db
        spring.datasource.username=root
        spring.datasource.password=yourpassword
        spring.jpa.hibernate.ddl-auto=update
        ```
3. **Build and run each microservice:** 
   
    ```bash
    cd login-service
    mvn clean install
    mvn spring-boot:run
    ```
    Repeat the above commands for `appointment-service`, `prescription-service`, `ums-service`, and `slot-service`. 🔄







## 🧪 Usage

### Register a User (Doctor):
* Register a user:
    ```json
    POST /doctor/register
    
    {
        "fullName": "Dr. John Doe",
        "userEmail": "13jerry02@gmail.com",
        "specialization": "Cardiologist",
        "qualification": "MBBS, MD",
        "experience": 10,
        "address": "123 Street, City",
        "gender": "Male",
        "phoneNumber": "9876543210",
        "password": "securepassword",
        "diseases": "Heart-related",
        "clinicName": "City Hospital",
        "openTime": "09:00:00",
        "closeTime": "18:00:00",
    
        "roleId": 2,
        "aadharNo": "123456789012"
    }
    ```

* Book Appointment:

    ```json
    Post /api/patients/post

    {
        "patientName": "Priyanshu BIrla",
        "phoneNumber": "9811472334",
        "email": "13jeery02@gmail.com",
        "aadharNo": 987656788765,
        "age": 30,
        "dateTime": "2024-09-17T01:00:00",
        "address": "123 Main Street, City, Country",
        "roleId": 1,
        "doctor": {
            "userId": 1  // Replace with actual doctor ID
        },
        "slot": {
            "slotId": 2 // Replace with actual slot ID
        }
    }
    ```

* Prescription 

    ```json
    POST api/prescriptions/post

    {
        "fever": 101.2,
        "weight": 70.5,
        "bp": "120/80",
        "sugar": 110,
        "test": ["Blood Test", "X-Ray"],
        "medicine": ["Paracetamol", "Aspirin"],
        "history": ["Asthma"],
        "user": {"userId": 5},
        "slot": {"slotId": 2},
        "patient": {"patientId": 10}
    }
    ```

* Slots creation

    ```json
   POST api/slots/create
   
    {
        "startTime": "09:00:00",
        "endTime": "09:30:00",
        "slotRange": "30 minutes",
        "userId": 5,
        "date": "2024-02-25"
    }
    ```


* Filter 
    ```
    Query params:
       /users/filter?roleId=1&fullName=Raju
    ```
 ✨__(See `/docs/api` for full endpoint details.)__


 





## 📋 API Documentation

### 🌐 Key Endpoints
- `POST /doctor/login` - Authenticate users 🗝️
- `POST /api/slots/create` - Create appointment slots ⏰
- `POST /api/prescriptions/post` - Add prescriptions 💊
- `POST /api/patients/post` - Book appointments 📅
- `POST /doctor/register` - Register new users 👤
- `GET /users` - Admin fetch all users (authenticated) 👨‍💼

🔗 *Detailed Swagger/OpenAPI documentation is available [here](https://swaggervaidya-production.up.railway.app/webjars/swagger-ui/index.html).*


## 📧 Contact/Support

- **Email**: 📧 team.vaiydainfo@gmail.com
- **Issues**: Report bugs or suggest features [here](https://github.com/TeamVaidya/vaidya/issues) 🐞


## 🌈 Why Vaidya?

Vaidya isn’t just a healthcare platform—it’s a symphony of innovation blending traditional wisdom with modern tech. 🌿 Its modular design, vibrant microservices, and colorful ecosystem of tools (Spring Boot, JWT, Railway) ensure scalability, security, and a delightful user experience. Join us in revolutionizing healthcare! 🚀


## 🚀 Future Scope

- Integrate real-time notifications with WebSocket. 🔔
- Develop a patient portal with React. 🌟
- Deploy with Kubernetes for high availability. 🛡️
- Enhance monitoring with AI-driven insights. 🤖
- Integration with Telemedicine.💊
