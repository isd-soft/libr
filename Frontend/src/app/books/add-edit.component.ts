import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '@app/_services';
import { BookService } from '@app/_services/book.service';
import { AccountService } from '@app/_services';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-add-edit',
  templateUrl: './add-edit.component.html',
})
export class AddEditComponent implements OnInit {
  form: FormGroup;
  id: string;
  isAddMode: boolean;
  loading = false;
  submitted = false;
  userId: number;
  authors: Array<string>
  categories: Array<string>

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private bookService: BookService,
    private accountService: AccountService,
    private alertService: AlertService
  ) { }

  ngOnInit() {
    this.userId = this.accountService.userId;
    this.id = this.route.snapshot.params['id'];
    this.isAddMode = !this.id;

    this.form = this.formBuilder.group({
      title: ['', Validators.required],
      authors: ['', Validators.required],
      publisher: ['', Validators.required],
      publishedDate: ['', Validators.required],
      description: ['', Validators.required],
      industryIdentifiers: ['', Validators.required],
      pageCount: ['', Validators.required],
      categories: ['', Validators.required],
      averageRating: ['', Validators.required],
      ratingsCount: ['', Validators.required],
      maturityRating: ['', Validators.required],
      imageLinks: ['', Validators.required],
      language: ['', Validators.required],
      previewLink: ['', Validators.required]
    });

    if (!this.isAddMode) {
      this.bookService.getById(this.id)
        .pipe(first())
        .subscribe(x => {
          this.authors = x.authors;
          this.categories = x.categories;
          this.f.title.setValue(x.title);
          this.f.authors.setValue(x.authors);
          this.f.publisher.setValue(x.publisher);
          this.f.publishedDate.setValue(x.publishedDate);
          this.f.description.setValue(x.description);
          this.f.industryIdentifiers.setValue(x.industryIdentifiers);
          this.f.pageCount.setValue(x.pageCount);
          this.f.categories.setValue(x.categories);
          this.f.averageRating.setValue(x.averageRating);
          this.f.ratingsCount.setValue(x.ratingsCount);
          this.f.maturityRating.setValue(x.maturityRating);
          this.f.imageLinks.setValue(x.imageLinks);
          this.f.language.setValue(x.language);
          this.f.previewLink.setValue(x.previewLink);
          this.f.id.setValue(this.userId);
        });
    }
  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    if (this.isAddMode) {
      this.createBook();
    } else {
      this.updateBook();
    }
  }

  private createBook() {
    this.form.value.authors = this.form.value.authors.split(',');
    this.form.value.categories = this.form.value.categories.split(',');
    this.form.value.industryIdentifiers = [{ 'isbn': this.form.value.industryIdentifiers.split(',')[0] }];
    this.form.value.imageLinks = { 'thumbnail': this.form.value.imageLinks.split(',')[0] };
    this.bookService.register(this.form.value, this.userId, true)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Book added successfully', { keepAfterRouteChange: true });
          this.router.navigate(['.', { relativeTo: this.route }]);
        },
        error => {
          this.alertService.error(error.error.errorMessage);
          this.loading = false;
        });
  }

  private updateBook() {
    if (!(this.form.value.authors == this.authors)) {
      this.form.value.authors = this.form.value.authors.split(',');
    }
    if (!(this.form.value.categories == this.categories)) {
      this.form.value.categories = this.form.value.categories.split(',');
    }

    this.bookService.update(this.id, this.form.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Update successful', { keepAfterRouteChange: true });
          this.router.navigate(['..', { relativeTo: this.route }]);
        },
        error => {
          this.alertService.error(error.error);
          this.loading = false;
        });
  }

}
