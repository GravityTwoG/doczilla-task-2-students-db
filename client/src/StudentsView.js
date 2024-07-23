export class StudentsView {
  constructor(parent, onStudentDelete) {
    this.parent = parent;
    this.onStudentDelete = onStudentDelete;

    this.parent.addEventListener('click', this.#onDeleteButtonClick);
  }

  destroy() {
    this.parent.replaceChildren();
    this.parent.removeEventListener('click', this.#onDeleteButtonClick);
  }

  #onDeleteButtonClick = (event) => {
    const deleteButton = event.target.closest('.student__delete-button');
    if (!deleteButton) {
      return;
    }

    const studentId = deleteButton.dataset.studentId;
    if (!studentId) {
      return;
    }

    this.onStudentDelete(studentId);
  };

  setStudents(students) {
    this.students = students;

    this.parent.replaceChildren();

    this.students.forEach((student) => {
      const studentElement = this.createStudentElement(student);
      this.parent.appendChild(studentElement);
    });
  }

  createStudentElement(student) {
    const studentElement = document.createElement('div');
    studentElement.classList.add('student');
    studentElement.classList.add('paper');

    const firstNameElement = document.createElement('div');
    firstNameElement.classList.add('student__name');
    firstNameElement.textContent = student.name;
    studentElement.appendChild(firstNameElement);

    const lastNameElement = document.createElement('div');
    lastNameElement.classList.add('student__last-name');
    lastNameElement.textContent = student.lastName;
    studentElement.appendChild(lastNameElement);

    const patronymicElement = document.createElement('div');
    patronymicElement.classList.add('student__patronymic');
    patronymicElement.textContent = student.patronymic;
    studentElement.appendChild(patronymicElement);

    const birthDateElement = document.createElement('div');
    birthDateElement.classList.add('student__birth-date');
    birthDateElement.textContent = student.birthDate;
    studentElement.appendChild(birthDateElement);

    const groupElement = document.createElement('div');
    groupElement.classList.add('student__group');
    groupElement.textContent = student.group;
    studentElement.appendChild(groupElement);

    const deleteButton = document.createElement('button');
    deleteButton.classList.add('student__delete-button');
    deleteButton.textContent = 'Delete';
    deleteButton.dataset.studentId = student.id;
    studentElement.appendChild(deleteButton);

    return studentElement;
  }
}
