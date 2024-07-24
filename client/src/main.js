import './style.css';

import { StudentFormView } from './StudentFormView';
import { StudentsView } from './StudentsView';
import { PaginatorView } from './PaginatorView';

import { StudentsPageModel } from './StudentsPageModel';

document.addEventListener('DOMContentLoaded', async () => {
  const studentsList = document.getElementById('students-list');

  const studentsPageModel = new StudentsPageModel();

  new StudentFormView(studentForm, async (student) => {
    await studentsPageModel.addStudent(student);
  });

  const studentsView = new StudentsView(studentsList, async (studentId) => {
    await studentsPageModel.deleteStudent(studentId);
  });

  const paginatorView = new PaginatorView(paginator, async (pageNumber) => {
    await studentsPageModel.loadStudents(pageNumber);
  });

  studentsPageModel
    .getStudents()
    .subscribe((students) => studentsView.setStudents(students));

  studentsPageModel.getTotalCount().subscribe((studentsCount) => {
    const pagesCount = Math.ceil(
      studentsCount / studentsPageModel.getPageSize().get()
    );
    paginatorView.setPagesCount(pagesCount);
  });

  studentsPageModel.getPageSize().subscribe((pageSize) => {
    const pagesCount = Math.ceil(
      studentsPageModel.getTotalCount().get() / pageSize
    );
    paginatorView.setPagesCount(pagesCount);
  });

  studentsPageModel.getPageNumber().subscribe((pageNumber) => {
    paginatorView.setPageNumber(pageNumber);
  });

  const errorElement = document.getElementById('studentsListError');
  studentsPageModel.getError().subscribe((error) => {
    if (error) {
      const errorTextElement = document.createElement('p');
      errorTextElement.classList.add('paper');
      errorTextElement.classList.add('error');
      errorTextElement.textContent = error;
      errorElement.appendChild(errorTextElement);
    } else {
      errorElement.replaceChildren();
    }
  });

  await studentsPageModel.init();
});
