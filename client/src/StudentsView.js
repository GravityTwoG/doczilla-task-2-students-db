export class StudentsView {
  constructor(parent, onStudentDelete) {
    this.parent = parent;
    this.onStudentDelete = onStudentDelete;

    this.parent.addEventListener('dblclick', this.#onDeleteButtonClick);
  }

  destroy() {
    this.parent.replaceChildren();
    this.parent.removeEventListener('dblclick', this.#onDeleteButtonClick);
  }

  #onDeleteButtonClick = async (event) => {
    try {
      const deleteButton = event.target.closest('.student__delete-button');
      if (!deleteButton) {
        return;
      }

      const studentId = deleteButton.dataset.studentId;
      if (!studentId) {
        return;
      }

      await this.onStudentDelete(studentId);
    } catch (error) {
      console.error(error);
      alert('An error occurred while deleting the student: ' + error.message);
    }
  };

  setStudents(students) {
    this.students = students;

    this.parent.replaceChildren();

    this.students.forEach((student) => {
      const studentElement = this.#createStudentElement(student);
      this.parent.appendChild(studentElement);
    });
  }

  #createStudentElement(student) {
    const studentElement = document.createElement('div');
    studentElement.classList.add('student');
    studentElement.classList.add('paper');

    const idElement = this.#createColumn('ID', student.id);
    studentElement.appendChild(idElement);

    const nameElement = this.#createColumn('Name', student.name);
    studentElement.appendChild(nameElement);

    const lastNameElement = this.#createColumn('Last Name', student.lastName);
    studentElement.appendChild(lastNameElement);

    const patronymicElement = this.#createColumn(
      'Patronymic',
      student.patronymic
    );
    studentElement.appendChild(patronymicElement);

    const birthDateElement = this.#createColumn(
      'Birth Date',
      student.birthDate
    );
    studentElement.appendChild(birthDateElement);

    const groupElement = this.#createColumn('Group', student.group);
    studentElement.appendChild(groupElement);

    const deleteButton = document.createElement('button');
    deleteButton.classList.add('student__delete-button');
    deleteButton.textContent = 'Delete';
    deleteButton.dataset.studentId = student.id;
    studentElement.appendChild(deleteButton);

    return studentElement;
  }

  #createColumn(label, value) {
    const columnElement = document.createElement('div');
    columnElement.classList.add('student__column');

    const labelElement = document.createElement('p');
    labelElement.classList.add('student__column__label');
    labelElement.textContent = label;
    columnElement.appendChild(labelElement);

    const valueElement = document.createElement('p');
    valueElement.classList.add('student__column__value');
    valueElement.textContent = value;
    columnElement.appendChild(valueElement);

    return columnElement;
  }
}
