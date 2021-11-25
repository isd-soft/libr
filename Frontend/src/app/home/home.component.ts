import { Component, OnInit } from '@angular/core';

import { BookService } from '@app/_services/book.service';
import { first } from 'rxjs/operators';
import { AccountService, AlertService } from '@app/_services';
import { Book } from '@app/_models/book';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup } from '@angular/forms';


@Component({
  templateUrl: 'home.component.html',
  styleUrls: ['style.css']
})

export class HomeComponent implements OnInit {

  constructor(private bookService: BookService,
    private accountService: AccountService,
    private alertService: AlertService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    // this.userId = localStorage.getItem('user').id;
    this.userRole = accountService.getRole();
  }
  books: Book[] = [];
  booksData: Book[] = [];
  categories: any;
  vote: number;
  countOfAllCategories: number;
  form: FormGroup;
  id: string;
  orderStatus = '';
  orderCategory = 'All';
  booksCount = 0;
  userId: string;
  userRole: string;
  isAddMode: boolean;
  loading = false;
  submitted = false;

  condition = false;


  ngOnInit() {
    this.userId = JSON.parse(localStorage.getItem('user')).id;
    this.fetchBooks();

    this.fetchAllCategories();
  }

  fetchAllCategories() {
    this.bookService.getAllCategories()
      .pipe(first())
      .subscribe(categoriesData => {
        this.categories = categoriesData;
        this.countOfAllCategories = 0;
        for (const category of this.categories) {
          this.countOfAllCategories += category.count;
        }
      });
  }

  showData(data: string) {
    this.books = [];
    this.condition = false;
    this.bookService.findInGoogleApi(data)
      .subscribe(dataResponse => {
        for (let i = 0; i < dataResponse["items"].length; i++) {
          if (i === 5) { break; }
          const b = dataResponse["items"][i].volumeInfo;
          b.apiId = dataResponse["items"][i].id;
          this.books.push(b);
        }
        this.condition = true;
      });

  }

  public saveBook(book: Book) {
    $('#confirmationRequestModal-' + book.apiId)["modal"]('hide');
    this.clearArray();
    book.userId = Number.parseInt(this.userId);
    book.isManuallyAdded = false;
    this.bookService.save(book)
      .pipe(first())
      .subscribe(
        data => {
          this.fetchBooks();
          this.alertService.success('Book added successfully', { keepAfterRouteChange: false });
        },
        error => {
          this.alertService.error(error.error.errorMessage);
          this.loading = false;
        });
  }

  public status(bookId, bookStatus) {
    this.bookService.updateStatus(this.userId, bookId, bookStatus)
      .subscribe(data => {
        this.fetchBooks();
        if (bookStatus === 'REQUESTED') {
          this.alertService.success('Book requested successfully', { keepAfterRouteChange: false });
        }
      },
        error => {
          this.alertService.error(error.error.errorMessage);
          this.loading = false;
        });
  }

  filterCategoryValue(category) {
    this.orderCategory = category;
  }

  filterStatusValue(status) {
    this.orderStatus = status;
  }

  public upVote(bookId) {
    this.bookService.vote(this.userId, bookId, 1)
      .subscribe(data => {
        this.fetchBooks();
      },
        error => {
          this.alertService.error(error.error.errorMessage);
          this.loading = false;
        });
  }



  private fetchBooks() {
    this.bookService.getAllFiltered()
      .pipe(first())
      .subscribe(booksD => {
        this.booksData = booksD;
        this.booksCount = 0;
        for (const book of booksD) {
          if (book.status === 'IN_LIBRARY') {
            this.booksCount++;
          }
        }

        // this.dtTrigger.next();
      });
  }

  public downVote(bookId) {
    this.bookService.vote(this.userId, bookId, -1)
      .subscribe(data => {
        this.fetchBooks();
      },
        error => {
          this.alertService.error(error.error.errorMessage);
          this.loading = false;
        });
  }

  addBookReaction(bookId, reaction) {
    this.bookService.addBookReaction(this.userId, bookId, reaction)
      .subscribe(data => {
        this.fetchBooks();
      },
        error => {
          this.alertService.error(error.error.errorMessage);
          this.loading = false;
        });
  }

  clearArray() {
    document.getElementById('myInput')["value"] = '';
    this.condition = false;
  }

  orderByCategory(categories) {
    for (const category of categories) {
      if (this.orderCategory.toString() === category.toString()) {
        return true;
      }
      else {
        return false;
      }
    }
  }
}
