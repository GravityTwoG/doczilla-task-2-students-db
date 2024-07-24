const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function addStudent(student) {
  const response = await fetch(`${API_BASE_URL}/students`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(student),
  });

  await checkError(response);

  return response.json();
}

export async function getStudents(query) {
  const response = await fetch(
    `${API_BASE_URL}/students?pageSize=${query.pageSize}&pageNumber=${query.pageNumber}`
  );

  await checkError(response);

  return response.json();
}

export async function deleteStudent(id) {
  const response = await fetch(`${API_BASE_URL}/students/${id}`, {
    method: 'DELETE',
  });

  await checkError(response);

  return response.status;
}

async function checkError(response) {
  if (!response.ok) {
    const isJson = response.headers
      .get('Content-Type')
      .includes('application/json');

    if (isJson) {
      const error = await response.json();
      throw new Error(error.error);
    }

    throw new Error(response.statusText);
  }
}
