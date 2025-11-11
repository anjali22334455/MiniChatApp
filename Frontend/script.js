const SERVER_URL = "http://127.0.0.1:8080";
const chatBox = document.getElementById("chat-box");
const usernameInput = document.getElementById("username");
const messageInput = document.getElementById("message");
const sendBtn = document.getElementById("send-btn");
const joinBtn = document.getElementById("join-btn");
const msgArea = document.getElementById("msg-area");
const joinArea = document.getElementById("join-area");

let username = null;

const eventSource = new EventSource(`${SERVER_URL}/events`);

eventSource.onmessage = (event) => {
  if (!event.data || event.data.startsWith("[ping]") || event.data.startsWith(": ping")) return;

  const msgDiv = document.createElement("div");
  msgDiv.classList.add("chat-message");

  const [sender, ...rest] = event.data.split(":");
  const text = rest.join(":").trim();
  const time = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

  if (sender.trim() === username) msgDiv.classList.add("my-message");
  else msgDiv.classList.add("other-message");

  msgDiv.textContent = `${sender.trim()}: ${text}  (${time})`;
  chatBox.appendChild(msgDiv);
  chatBox.scrollTop = chatBox.scrollHeight;
};

joinBtn.onclick = () => {
  const name = usernameInput.value.trim();
  if (!name) return alert("Enter your name first!");
  username = name;
  joinArea.style.display = "none";
  msgArea.style.display = "flex";
};

sendBtn.onclick = sendMessage;
messageInput.addEventListener("keypress", e => {
  if (e.key === "Enter") sendMessage();
});

function sendMessage() {
  const text = messageInput.value.trim();
  if (!text || !username) return;

  fetch(`${SERVER_URL}/send`, {
    method: "POST",
    headers: { "Content-Type": "text/plain" },
    body: `${username}: ${text}`
  });

  messageInput.value = "";
}