import './style.css';

import { addStudent, getStudents, deleteStudent } from './api';

import { StudentFormView } from './StudentFormView';
import { StudentsView } from './StudentsView';
import { PaginatorView } from './PaginatorView';

class StudentsPageModel {
  constructor() {
    this.students = [];
    this.totalCount = 0;
    this.pageSize = 10;
    this.pageNumber = 1;
  }

  async init() {
    await this.loadStudents(1);
  }

  async loadStudents(pageNumber) {
    this.pageNumber = pageNumber;
    const students = await getStudents({
      pageSize: this.pageSize,
      pageNumber: this.pageNumber,
    });
    this.students = students.items;
    this.totalCount = students.totalCount;
  }

  async addStudent(student) {
    await addStudent(student);

    await this.loadStudents(1);
  }

  async deleteStudent(id) {
    await deleteStudent(id);

    await this.loadStudents(this.pageNumber);
  }

  getStudents() {
    return this.students;
  }

  getPagesCount() {
    return Math.ceil(this.totalCount / this.pageSize);
  }
}

document.addEventListener('DOMContentLoaded', async () => {
  const studentsList = document.getElementById('students-list');

  const studentsPageModel = new StudentsPageModel();
  await studentsPageModel.init();

  const studentFormView = new StudentFormView(studentForm, async (student) => {
    await studentsPageModel.addStudent(student);
    studentsView.setStudents(studentsPageModel.getStudents());
  });

  const studentsView = new StudentsView(studentsList, async (studentId) => {
    await studentsPageModel.deleteStudent(studentId);
    studentsView.setStudents(studentsPageModel.getStudents());
  });

  studentsView.setStudents(studentsPageModel.getStudents());

  const paginatorView = new PaginatorView(paginator, async (pageNumber) => {
    await studentsPageModel.loadStudents(pageNumber);
    studentsView.setStudents(studentsPageModel.getStudents());
    paginatorView.setPages(studentsPageModel.getPagesCount(), pageNumber);
  });

  paginatorView.setPages(studentsPageModel.getPagesCount(), 1);
});
