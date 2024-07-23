import { Observable } from './observable';

import { addStudent, getStudents, deleteStudent } from './api';

export class StudentsPageModel {
  constructor() {
    this.students = new Observable([]);
    this.totalCount = new Observable(0);
    this.pageSize = new Observable(10);
    this.pageNumber = new Observable(1);
  }

  async init() {
    await this.loadStudents(1);
  }

  async loadStudents(pageNumber) {
    const students = await getStudents({
      pageSize: this.pageSize.get(),
      pageNumber: pageNumber,
    });

    this.pageNumber.set(pageNumber);
    this.students.set(students.items);
    this.totalCount.set(students.totalCount);
  }

  async addStudent(student) {
    await addStudent(student);
    await this.loadStudents(1);
  }

  async deleteStudent(id) {
    await deleteStudent(id);
    await this.loadStudents(this.pageNumber.get());
  }

  getStudents() {
    return this.students;
  }

  getTotalCount() {
    return this.totalCount;
  }

  getPageSize() {
    return this.pageSize;
  }

  getPageNumber() {
    return this.pageNumber;
  }
}
