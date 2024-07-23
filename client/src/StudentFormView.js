export class StudentFormView {
  constructor(parent, onStudentAdd) {
    this.parent = parent;
    this.onStudentAdd = onStudentAdd;

    this.parent.addEventListener('submit', this.#onSubmit);
  }

  destroy() {
    this.parent.replaceChildren();
    this.parent.removeEventListener('submit', this.#onSubmit);
  }

  #onSubmit = async (event) => {
    try {
      event.preventDefault();

      if (this.isSubmitting) {
        return;
      }

      this.isSubmitting = true;

      const student = {
        name: this.parent.querySelector('#name').value,
        lastName: this.parent.querySelector('#lastName').value,
        patronymic: this.parent.querySelector('#patronymic').value,
        birthDate: this.parent.querySelector('#birthDate').value,
        group: this.parent.querySelector('#group').value,
      };

      await this.onStudentAdd(student);
      this.isSubmitting = false;
      this.parent.reset();
    } catch (error) {
      console.error(error);
      alert('An error occurred while adding the student');
    } finally {
      this.isSubmitting = false;
    }
  };
}
