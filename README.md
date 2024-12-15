cat > README.md << EOF
# **TV Shows Web Application**

This project is a full-stack application that allows users to list and view details of TV shows. It is split into the following components:

- **Backend**: A Java-based REST API that fetches and processes TV show details using the TVMaze API.
- **Frontend**: A React-based user interface to display TV shows and their details.
- **Docker**: Both backend and frontend are containerized and managed using \`docker-compose\`.

---

## **Table of Contents**

1. [Project Overview](#project-overview)
2. [Technologies Used](#technologies-used)
3. [Setup and Running the Application](#setup-and-running-the-application)
4. [API Endpoints Documentation](#api-endpoints-documentation)
5. [Assumptions and Decisions](#assumptions-and-decisions)
6. [Notes](#notes)

---

## **Project Overview**

The TV Shows Web Application provides the following features:

1. Consumes a \`tvtitles.txt\` file to process TV show titles and fetch details from the [TVMaze API](http://api.tvmaze.com/).
2. Lists all TV shows retrieved via the API.
3. Displays detailed information about individual TV shows.

---

## **Technologies Used**

### **Backend**
- Java 8+
- Spring Boot
- H2 in-memory database (optional for data persistence)

### **Frontend**
- React
- CSS Framework (e.g., Material-UI or Bootstrap)

### **Other Tools**
- Docker
- Docker Compose

---

## **Setup and Running the Application**

### **Prerequisites**
1. Install [Docker](https://docs.docker.com/get-docker/) and [Docker Compose](https://docs.docker.com/compose/install/) on your system.
2. For local development, ensure Java 8+ and Node.js are installed.

### **Steps to Run the Application**

#### **Using Docker**
1. Clone the main repository:

   git clone https://github.com/amarbalu/TVMaze.git
   cd TVMaze


2. Clone the frontend repository:

   rmdir tvshow-frontend
   
   git clone https://github.com/amarbalu/tvshow-frontend.git


4. Clone the backend repository:

   rmdir tvshow-backend
   
   git clone https://github.com/amarbalu/tvshow-backend.git


5. Start the application:

   docker-compose up --build


6. Access the application:
   - **Frontend**: [http://localhost:3000](http://localhost:3000)
   - **Backend**: [http://localhost:8080](http://localhost:8080)

---

#### **Local Development (Without Docker)**

1. **Backend**:
   - Clone the backend repository:

     cd tvshow-backend
     
     ./mvnw spring-boot:run

   - Backend will be accessible at \`http://localhost:8080\`.

2. **Frontend**:
   - Clone the frontend repository:

     cd tvshow-frontend
     
     npm install
     
     npm start

   - Frontend will be accessible at \`http://localhost:3000\`.

---

## **API Endpoints Documentation**

| Method | Endpoint          | Description                            |
| ------ | ----------------- | -------------------------------------- |
| GET    | \`/api/shows\`      | Fetch the list of TV shows             |
| POST   | \`/api/shows\`      | Add TV show titles from \`tvtitles.txt\` |
| GET    | \`/api/shows/{id}\` | Fetch details of a specific TV show    |

### **Sample API Usage**

#### **1. Fetch All TV Shows**
**Request**:
\`\`\`bash
GET /api/shows
\`\`\`

**Response**:
\`\`\`json
[
  {
    "id": 1,
    "name": "Breaking Bad",
    "genre": "Drama",
    "network": "AMC",
    "status": "Ended",
    "schedule": {
      "time": "21:00",
      "days": ["Sunday"]
    },
    "summary": "A high school chemistry teacher turned methamphetamine producer."
  }
]
\`\`\`

---

## **Assumptions and Decisions**

1. **Data Source**: TV show titles are processed from the \`tvtitles.txt\` file.
2. **Data Persistence**: Backend uses an in-memory database (H2) for simplicity.
3. **UI Design**: Frontend uses a responsive CSS framework for improved design.
4. **Docker Ports**:
   - Backend: \`8080\`
   - Frontend: \`3000\`
5. **TVMaze API Integration**:
   - \`/singlesearch/shows?q={title}\` is used to fetch show details.

---

## **Notes**

1. The application demonstrates a minimal setup for listing and displaying TV shows.
2. Future enhancements may include:
   - Persistent database (e.g., MySQL/PostgreSQL).
   - Authentication and role-based access control.
   - Advanced UI features such as search and filters.
3. Ensure CORS is configured correctly in the backend for local frontend testing.

---

## **Author**

- **Name**: Amar Balu
- **GitHub**: [https://github.com/amarbalu](https://github.com/amarbalu)

---

## **License**

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
EOF
