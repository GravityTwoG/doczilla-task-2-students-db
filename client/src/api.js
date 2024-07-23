const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function addStudent(student) {
  const response = await fetch(`${API_BASE_URL}/students`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(student),
  });

  return response.json();
}

export async function getStudents(query) {
  const response = await fetch(
    `${API_BASE_URL}/students?pageSize=${query.pageSize}&pageNumber=${query.pageNumber}`
  );

  return response.json();
}

export async function deleteStudent(id) {
  const response = await fetch(`${API_BASE_URL}/students/${id}`, {
    method: 'DELETE',
  });

  return response.status;
}
