#  Client–Server Chatting Program

A simple real-time chatting application built using **Java** (for the backend) and **HTML, CSS, JavaScript** (for the frontend).

---

##  Features
- Real-time group chat functionality.
- Java-based server using **Server-Sent Events (SSE)**.
- Web frontend for easy chatting between multiple clients.
- No external frameworks — lightweight and educational.
- CORS handled for cross-origin communication.

---

## Tech Stack
**Backend:** Java  
**Frontend:** HTML, CSS, JavaScript  
**Protocol:** HTTP with Server-Sent Events (SSE)  

---

##  How It Works
1. The server runs on port **8080**.
2. The frontend (served on port **8081**) connects using `EventSource` for live updates.
3. Each user (browser tab) can send and receive messages in real time.
4. The server broadcasts all messages to connected clients.

---

##  How to Run
### Step 1: Start the Java Server

cd Server
javac ChatServer.java
java Server.ChatServer

### Step 2: Start the Frontend
cd Frontend
npx http-server -p 8081

