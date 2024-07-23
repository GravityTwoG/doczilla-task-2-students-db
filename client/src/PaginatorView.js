export class PaginatorView {
  constructor(parent, onPageChange) {
    this.parent = parent;
    this.onPageChange = onPageChange;
    this.scope = 3;

    this.parent.addEventListener('click', this.#onPageClick.bind(this));
  }

  destroy() {
    this.parent.replaceChildren();
  }

  setPages(pagesCount, currentPageNumber) {
    const pages = [];

    let min = Math.max(1, currentPageNumber - this.scope);
    let max = Math.min(pagesCount, currentPageNumber + this.scope);
    if (max < this.scope * 2 + 1) {
      max = Math.min(pagesCount, this.scope * 2 + 1);
    } else if (min > pagesCount - this.scope * 2) {
      min = Math.max(1, pagesCount - this.scope * 2);
    }

    for (let i = min; i <= max; i++) {
      pages.push({
        number: i,
        isCurrent: i === currentPageNumber,
      });
    }

    this.parent.replaceChildren();

    pages.forEach((page) => {
      const pageElement = document.createElement('li');
      pageElement.classList.add('paginator__page');
      if (page.isCurrent) {
        pageElement.classList.add('is-current');
      }
      pageElement.textContent = page.number;
      pageElement.dataset.pageNumber = page.number;
      this.parent.appendChild(pageElement);
    });
  }

  #onPageClick(event) {
    const pageNumber = parseInt(event.target.dataset.pageNumber);
    if (!pageNumber) {
      return;
    }

    this.onPageChange(pageNumber);
  }
}
