# Enda Project 🚀

A full-stack web application built with Spring Boot backend and Angular frontend for managing receipts, supervisors, and zones.

## Project Structure 🗂️

```
enda-project/
├── enda-back/          # Spring Boot REST API
├── enda-front/         # Angular frontend application  
├── uploads/            # File storage directory
└── docker-compose.yaml # Docker configuration
```

## Technologies Used 🛠️

### Backend (enda-back) ⚙️
- **Java 17+** ☕
- **Spring Boot 3.x** 🌱
- **Spring Mail** ✉️ - Email functionality
- **Spring Data JPA** 🗄️ - Database operations
- **Maven** 📦 - Dependency management
- **Jakarta Mail** 📧 - Email services

### Frontend (enda-front) 🎨
- **Angular 15+** 🅰️
- **TypeScript** 📝
- **SCSS** 🎨 - Styling
- **Angular CLI** ⚡ - Development tools

## Features ✨

- Receipt management (RecuController, RecuService) 🧾
- Supervisor management (SuperviseurController) 👨‍💼
- Zone management (ZoneController) 🗺️
- Access Point management (APController) 📡
- Email notifications with JavaMailSender 📬
- File upload functionality 📁
- RESTful API architecture 🔗

## Prerequisites 📋

- Java 17 or higher ☕
- Node.js 16+ and npm 🟩
- Maven 3.6+ 📦
- Docker and Docker Compose (optional) 🐳

## Getting Started 🏁

### Backend Setup ⚙️

1. Navigate to the backend directory:
   ```bash
   cd enda-back
   ```

2. Install dependencies and build:
   ```bash
   ./mvnw clean install
   ```

3. Configure database settings in `src/main/resources/application.yaml`

4. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

The backend will start on `http://localhost:8080` 🌐

### Frontend Setup 🎨

1. Navigate to the frontend directory:
   ```bash
   cd enda-front
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   ng serve
   ```

The frontend will start on `http://localhost:4200` 🌐

### Docker Setup (Optional) 🐳

Run the entire application using Docker:

```bash
docker-compose up -d
```

## Configuration ⚙️

### Backend Configuration
- Main config: `enda-back/src/main/resources/application.yaml`
- Development config: `enda-back/src/main/resources/application-dev.yaml`
- Mail configuration: Check `MailConfig.java` in config package

### Frontend Configuration
- Angular config: `enda-front/angular.json`
- TypeScript config: `enda-front/tsconfig.json`

## API Endpoints 🔗

The backend provides RESTful APIs for:
- `/api/recu` - Receipt management 🧾
- `/api/superviseur` - Supervisor management 👨‍💼  
- `/api/zone` - Zone management 🗺️
- `/api/ap` - Access Point management 📡

## Models 🧩

### Frontend Models
- `ap.model.ts` - Access Point model 📡
- `recu.model.ts` - Receipt model 🧾
- `superviseur.model.ts` - Supervisor model 👨‍💼
- `zone.model.ts` - Zone model 🗺️

### Backend Entities
Located in `enda-back/src/main/java/com/ray/enda/model/`

## Services 🛎️

### Frontend Services
- `recu.service.ts` - Receipt service for API communication 🔗

### Backend Services
Located in `enda-back/src/main/java/com/ray/enda/service/`

## Development 🧑‍💻

### Backend Development
- Use your preferred IDE (IntelliJ IDEA recommended) 💡
- Follow Spring Boot conventions 🌱
- Add new controllers in the controller package
- Add new services in the service package

### Frontend Development
- Use Angular CLI for generating components: `ng generate component component-name` ⚡
- Follow Angular style guide 🅰️
- Add new services: `ng generate service service-name`

## Testing 🧪

### Backend Tests
```bash
cd enda-back
./mvnw test
```

### Frontend Tests
```bash
cd enda-front
ng test
```

## Build for Production 🏗️

### Backend
```bash
cd enda-back
./mvnw clean package
```

### Frontend
```bash
cd enda-front
ng build --prod
```

## Contributing 🤝

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License 📄

This project is licensed under the Apache License 2.0 - see the Spring framework license for details.

## Contact 📬

- GitHub: [@Raydineri](https://github.com/Raydineri)
- Project Repository: [https://github.com/Raydineri/Enda-project.git](https://github.com/Raydineri/Enda-project.git)
