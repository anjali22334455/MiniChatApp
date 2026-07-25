# 💬 Mini Chat App

A lightweight **real-time client-server chat application** built using **Java** for the backend and **HTML, CSS, and JavaScript** for the frontend. The application enables multiple users to communicate instantly through a web interface using **Server-Sent Events (SSE)** for real-time message broadcasting. This project demonstrates the fundamentals of client-server communication, event-driven programming, and real-time web applications without relying on external frameworks.

---

## ✨ Features

- 💬 Real-Time Group Chat
- ☕ Java-Based Backend Server
- 🌐 Interactive Web-Based User Interface
- 📡 Live Message Broadcasting using Server-Sent Events (SSE)
- 👥 Supports Multiple Connected Clients
- ⚡ Instant Message Delivery
- 🔄 Cross-Origin Resource Sharing (CORS) Support
- 🚀 Lightweight and Easy to Understand
- 📚 Beginner-Friendly Networking Project

---

## 🛠 Tech Stack

### Backend

- Java
- Java HTTP Server
- Server-Sent Events (SSE)

### Frontend

- HTML5
- CSS3
- JavaScript

### Communication Protocol

- HTTP
- Server-Sent Events (SSE)

---

## ⚙️ How It Works

1. The Java server starts and listens for incoming client connections on **port 8080**.
2. The frontend is served locally using an HTTP server on **port 8081**.
3. Each browser connects to the backend using **Server-Sent Events (SSE)** through the `EventSource` API.
4. Users type and send messages through the web interface.
5. The Java server receives each message and broadcasts it to all connected clients.
6. Every connected browser instantly receives new messages without refreshing the page.

---

## 📂 Project Structure

```text
MiniChatApp
│
├── Frontend
│   ├── index.html
│   ├── script.js
│   └── style.css
│
├── Server
│   ├── ChatServer.java
│   ├── ChatServer.class
│   └── ChatServer$ClientHandler.class
│
└── README.md
```

---

## 🚀 Installation

### Clone the Repository

```bash
git clone https://github.com/anjali22334455/MiniChatApp.git
```

Move into the project directory.

```bash
cd MiniChatApp
```

---

## ▶️ Run the Project

### Step 1: Compile the Java Server

```bash
cd Server
javac ChatServer.java
```

### Step 2: Start the Java Server

```bash
java ChatServer
```

The server will start on:

```text
http://localhost:8080
```

---

### Step 3: Start the Frontend

Open another terminal.

```bash
cd Frontend
```

Start a local HTTP server.

```bash
npx http-server -p 8081
```

If you don't have **http-server** installed:

```bash
npm install -g http-server
```

---

### Step 4: Open the Application

Open your browser and visit:

```text
http://localhost:8081
```

Open multiple browser tabs or different browsers to simulate multiple users chatting simultaneously.

---

## 🎯 Learning Outcomes

Through this project, you can learn:

- Client-Server Architecture
- Java HTTP Server Programming
- Server-Sent Events (SSE)
- Real-Time Communication
- Event-Driven Programming
- Frontend and Backend Integration
- Cross-Origin Resource Sharing (CORS)

---

## 🚀 Future Enhancements

- 🔐 User Authentication
- 👤 User Profiles
- 😊 Emoji Support
- 📂 File Sharing
- 💬 Private Messaging
- 🟢 Online User List
- 💾 Persistent Chat History
- 🌙 Dark Mode
- 📱 Responsive Mobile Interface

---

## 👨‍💻 Author

**Anjali Jaiswal**

- 💼 GitHub: https://github.com/anjali22334455
- 💼 LinkedIn: *Add your LinkedIn profile link here*
- 📧 Email: *Add your email address here*

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the repository.
2. Create a new branch.

```bash
git checkout -b feature-name
```

3. Commit your changes.

```bash
git commit -m "Add new feature"
```

4. Push your branch.

```bash
git push origin feature-name
```

5. Open a Pull Request.

---

## 📄 License

This project is developed for educational and learning purposes.

---

## ⭐ Support

If you found this project helpful, please consider giving it a **⭐ Star** on GitHub.

Your support motivates me to build more open-source projects and continue learning.
