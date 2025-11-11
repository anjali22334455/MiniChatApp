package Server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {
    private static final List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(" Chat server started on http://localhost:" + port);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private BufferedWriter out;
        private String username;
        private boolean registered = false;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String request = in.readLine();
                if (request == null) return;

                if (request.startsWith("GET /events")) {
                    handleSSE();
                } else if (request.startsWith("POST /send")) {
                    handleMessage();
                } else if (request.startsWith("OPTIONS")) {
                    sendResponse(out, "204 No Content", "text/plain", "");
                } else {
                    sendResponse(out, "404 Not Found", "text/plain", "Not Found");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clients.remove(this);
                try { socket.close(); } catch (IOException ignored) {}
            }
        }

        private void handleSSE() throws IOException {
            out.write("HTTP/1.1 200 OK\r\n");
            out.write("Content-Type: text/event-stream\r\n");
            out.write("Cache-Control: no-cache\r\n");
            out.write("Connection: keep-alive\r\n");
            out.write("Access-Control-Allow-Origin: *\r\n\r\n");
            out.flush();

            clients.add(this);
            System.out.println(" Client connected for events (total: " + clients.size() + ")");

            try {
                while (true) {
                    Thread.sleep(15000);
                    out.write("data: [ping]\n\n");
                    out.flush();
                }
            } catch (Exception e) {
                clients.remove(this);
            }
        }

        private void handleMessage() throws IOException {
            String line;
            int contentLength = 0;

            while (!(line = in.readLine()).isEmpty()) {
                if (line.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }
            }

            char[] body = new char[contentLength];
            in.read(body, 0, contentLength);
            String msg = new String(body).trim();

            if (!registered) {
                username = msg.split(":")[0].trim();
                registered = true;
            } else {
                String sender = msg.split(":")[0].trim();
                if (!sender.equals(username)) {
                    sendResponse(out, "403 Forbidden", "text/plain", "Name mismatch");
                    return;
                }
            }

            System.out.println(" " + msg);
            broadcast(msg);
            sendResponse(out, "200 OK", "text/plain", "Sent");
        }

        private void broadcast(String message) {
            for (ClientHandler client : clients) {
                try {
                    client.out.write("data: " + message + "\n\n");
                    client.out.flush();
                } catch (IOException e) {
                    clients.remove(client);
                }
            }
        }

        private void sendResponse(BufferedWriter out, String status, String type, String body) throws IOException {
            out.write("HTTP/1.1 " + status + "\r\n");
            out.write("Content-Type: " + type + "\r\n");
            out.write("Access-Control-Allow-Origin: *\r\n");
            out.write("Access-Control-Allow-Methods: GET, POST, OPTIONS\r\n");
            out.write("Access-Control-Allow-Headers: Content-Type\r\n");
            out.write("Content-Length: " + body.length() + "\r\n");
            out.write("Connection: close\r\n\r\n");
            out.write(body);
            out.flush();
        }
    }
}