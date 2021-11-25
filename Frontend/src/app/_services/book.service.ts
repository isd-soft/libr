import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

import { environment } from '@environments/environment';
import { Book } from '@app/_models/book';
import { map } from "rxjs/operators";
import { Categories } from '@app/_models/Categories';

@Injectable({ providedIn: 'root' })
export class BookService {
  private bookSubject: BehaviorSubject<Book>;
  public book: Observable<Book>;
  private categorySubject: BehaviorSubject<Categories>;
  public categories: Observable<Categories>;
  userId: number;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    this.userId = JSON.parse(localStorage.getItem('user'));
    this.categorySubject = new BehaviorSubject<Categories>(JSON.parse(localStorage.getItem('category')));
    this.categories = this.categorySubject.asObservable();
    this.bookSubject = new BehaviorSubject<Book>(JSON.parse(localStorage.getItem('book')));
    this.book = this.bookSubject.asObservable();
  }

  public get bookValue(): Book {
    return this.bookSubject.value;
  }

  findInGoogleApi(keyword: string) {
    return this.http.get(`https://www.googleapis.com/books/v1/volumes?q=` + keyword);
  }

  save(book: Book) {
    return this.http.post(`${environment.apiUrl}/books`, book);
  }

  getAllDashboard() {
    return this.http.get(`${environment.apiUrl}/dashboard`);
  }

  getById(id: string) {
    return this.http.get<Book>(`${environment.apiUrl}/books/${id}`);
  }

  getAll() {
    return this.http.get<Book[]>(`${environment.apiUrl}/books`);
  }

  getAllFiltered() {
    return this.http.get<Book[]>(`${environment.apiUrl}/books/filtered`);
  }

  getAllCategories() {
    return this.http.get(`${environment.apiUrl}/books/categories`);
  }

  delete(id: string) {
    return this.http.delete(`${environment.apiUrl}/books/${id}`)
      .pipe(map(x => {
        return x;
      }));
  }

  register(book: Book, userId: number, isManuallyAdded: boolean) {
    book.userId = userId;
    book.isManuallyAdded = isManuallyAdded;
    return this.http.post(`${environment.apiUrl}/books`, book);
  }

  vote(userId: string, bookId: number, vote: number) {
    return this.http.post(`${environment.apiUrl}/vote`, { "userId": userId, "bookId": bookId, "vote": vote });
  }

  getStatusInfo(status, bookId) {
    return this.http.get(`${environment.apiUrl}/book-actions/${status}/book/${bookId}`);
  }

  addBookReaction(userId, bookId, reaction) {
    return this.http.post(`${environment.apiUrl}/book-reaction`,
      { "userId": userId, "bookId": bookId, "type": reaction });
  }

  addCommentReaction(userId, commentId, reaction) {
    return this.http.post(`${environment.apiUrl}/comment-reaction`,
      { "userId": userId, "commentId": commentId, "type": reaction });
  }

  comment(comment, userId, bookId) {
    return this.http.post(`${environment.apiUrl}/comments/`, { "userId": userId, "bookId": bookId, "comment": comment });
  }

  updateStatus(userId, bookId, status) {
    return this.http.post(`${environment.apiUrl}/book-actions/update-status`, { "bookId": bookId, "userId": userId, "newStatus": status });
  }

  getBookActionByStatus(status) {
    return this.http.get(`${environment.apiUrl}/book-actions/${status}`);
  }

  update(id, params) {
    return this.http.put(`${environment.apiUrl}/books/${id}`, params);
  }

  getActuatorInfo() {
    return this.http.get(`${environment.apiUrl}/actuator/info`);
  }

  getActuatorHealth() {
    return this.http.get(`${environment.apiUrl}/actuator/health`);
  }

  getActuatorMetrics(url) {
    return this.http.get(`${environment.apiUrl}/actuator/metrics/${url}`);
  }
}
