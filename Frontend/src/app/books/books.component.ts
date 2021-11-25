import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { first } from 'rxjs/operators';
import { BookService } from '@app/_services/book.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '@app/_models/book';

@Component({
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.less']
})

export class BooksComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  books: Book[] = [];
  emailDropdown = false;


  // We use this trigger because fetching the list of persons can be quite long,
  // thus we ensure the data is fetched before rendering
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private bookService: BookService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10
    };
    this.bookService.getAllFiltered()
      .pipe(first())
      .subscribe(books => {
        this.books = books;
        this.dtTrigger.next();
      });
  }

  deleteBook(id: string) {
    // const book = this.books.find(x => x.id === id);
    // book.isDeleting = true;
    this.bookService.delete(id)
      .pipe(first())
      .subscribe(() => {
        this.books = this.books.filter(x => x.id !== id);
      });
  }
}
