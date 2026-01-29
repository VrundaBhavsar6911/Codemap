
---

## 1. Research & Understanding (Real Police Workflow)

### 1.1 Patrolling

Patrolling is a routine police activity where officers move within an assigned beat/area to:

* Maintain law and order
* Deter crime through visible presence
* Respond quickly to incidents
* Collect on-ground intelligence

**Characteristics:**

* Continuous / recurring
* Area-based (beats)
* Officers report status periodically

### 1.2 Bandobast / Nakabandi

Bandobast or Nakabandi is a planned, time-bound police operation conducted for:

* VIP movement
* Festivals, protests, elections
* Crime control checkpoints

**Characteristics:**

* Event-driven and temporary
* Requires planning & approval
* Fixed locations and time windows
* Higher supervision and audit

### 1.3 Operational Roles in Real Life

* **Planner (Station Officer / PI):** Plans patrol routes or bandobast points
* **Executing Officers:** Field officers carrying out the task
* **Supervisor (DSP / Control Room):** Monitors execution and handles escalations

---

## 2. Actor & Role Design

### 2.1 Roles

| Role       | Responsibilities                                               |
| ---------- | -------------------------------------------------------------- |
| OFFICER    | Execute patrol / bandobast, share live location, update status |
| PLANNER    | Create plans, assign officers, generate reports                |
| SUPERVISOR | Monitor live operations, receive alerts, close operations      |

### 2.2 Authentication & Authorization

* JWT-based authentication
* Role-based access control (RBAC)

**Permissions:**

* Only PLANNER can create/assign operations
* OFFICER can only view assigned tasks
* SUPERVISOR can monitor all active operations

---

## 3. System Design (Core Evaluation)

### 3.1 High-Level Architecture

```
[ Mobile App ]
      |
   REST / WS
      |
[ API Gateway ]
      |
-------------------------------
| Auth Service                |
| Operations Service          |
| Tracking Service            |
| Notification Service        |
-------------------------------
      |
[ PostgreSQL ]   [ Redis ]
```

### 3.2 Service Boundaries

#### Auth Service

* Login
* JWT issuance
* Role validation

#### Operations Service

* Create patrol / bandobast
* Assign officers
* Close operations
* Generate PDF

#### Tracking Service

* Receive live GPS updates
* Publish location via WebSocket

#### Notification Service

* Alerts (officer offline, deviation, panic)
* Uses Redis Pub/Sub

### 3.3 Redis Usage

* Caching active operations
* Storing latest officer location
* Pub/Sub for alerts

### 3.4 Real-time Communication

* WebSocket for:

  * Live officer location
  * Operation status updates

---

## 4. Data Design (Schema Overview)

### 4.1 Users

```
User
- id
- name
- role
- badge_number
- station_id
- status
```

### 4.2 Patrol

```
Patrol
- id
- area
- start_time
- end_time
- planner_id
- status
```

### 4.3 Bandobast

```
Bandobast
- id
- event_name
- location
- start_time
- end_time
- risk_level
- status
```

### 4.4 Assignment

```
Assignment
- id
- operation_id
- officer_id
- role
- assigned_at
```

### 4.5 Location Tracking

```
OfficerLocation
- officer_id
- latitude
- longitude
- timestamp
```

### 4.6 Alerts

```
Alert
- id
- type
- message
- severity
- created_at
```

---

## 5. API Design (Sample)

### Authentication

```
POST /auth/login
```

### Patrol Planning

```
POST /patrols
GET  /patrols/{id}
POST /patrols/{id}/assign
```

### Bandobast

```
POST /bandobast
POST /bandobast/{id}/assign
```

### Live Tracking

```
POST /tracking/location
WS  /ws/locations
```

---

## 6. Implementation Scope (Chosen)

### Implemented End-to-End Flow

* Patrol creation
* Officer assignment
* Live location update
* Supervisor monitoring

### Skipped (Explained)

* Complex GIS routing
* External SMS/WhatsApp integration

---

## 7. Docker & Run Instructions

```
docker-compose up --build
```

Services:

* app (Spring Boot)
* postgres
* redis

---

## 8. Trade-offs

* Monolith with modular design chosen over full microservices for simplicity
* Redis used for speed instead of heavy DB writes
* WebSocket preferred over polling for real-time monitoring

---

## 9. Postman & Docs

* Postman collection included in repo
* Swagger enabled at `/swagger-ui.html`

---

## 10. Explanation Video

* 5–10 min walkthrough:

  * Domain understanding
  * Architecture
  * API demo

---

## 11. Conclusion

This system models **real police workflows**, focusing on planning, execution, monitoring, and auditability rather than just CRUD features. The design prioritizes scalability, clarity, and real-time visibility.


com.copmap
 ├── CopMapApplication.java
 ├── config
 │    ├── SecurityConfig.java
 │    ├── RedisConfig.java
 │    └── WebSocketConfig.java
 ├── auth
 │    ├── AuthController.java
 │    ├── AuthService.java
 │    └── JwtUtil.java
 ├── user
 │    ├── User.java
 │    ├── Role.java
 │    ├── UserRepository.java
 │    └── UserService.java
 ├── patrol
 │    ├── Patrol.java
 │    ├── PatrolController.java
 │    ├── PatrolService.java
 │    └── PatrolRepository.java
 ├── assignment
 │    ├── Assignment.java
 │    └── AssignmentRepository.java
 ├── tracking
 │    ├── OfficerLocation.java
 │    ├── TrackingController.java
 │    └── TrackingService.java
 ├── notification
 │    └── AlertService.java
 └── common
      ├── ApiResponse.java
      └── BaseEntity.java

