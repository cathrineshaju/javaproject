const API_URL = '/api/tasks';

async function fetchTasks() {
  const res = await fetch(API_URL);
  const tasks = await res.json();
  render(tasks);
}

async function addTask() {
  const input = document.getElementById('taskInput');
  const desc = input.value.trim();
  if (!desc) return;
  await fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ description: desc })
  });
  input.value = '';
  fetchTasks();
}

async function toggleComplete(id) {
  await fetch(`${API_URL}/${id}`, { method: 'PUT' });
  fetchTasks();
}

async function deleteTask(id) {
  await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
  fetchTasks();
}

function render(tasks) {
  const list = document.getElementById('taskList');
  list.innerHTML = '';
  tasks.forEach(t => {
    const li = document.createElement('li');
    li.className = 'task';
    li.innerHTML = `
      <span class="${t.completed ? 'completed' : ''}"
            onclick="toggleComplete(${t.id})">
        ${t.description}
      </span>
      <button onclick="deleteTask(${t.id})">Delete</button>
    `;
    list.appendChild(li);
  });
}

document.getElementById('addBtn').addEventListener('click', addTask);
fetchTasks();
