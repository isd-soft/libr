import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { LayoutComponent } from './layout.component';
import { AddEditComponent } from './add-edit.component';
import {DataTablesModule} from "angular-datatables";
import { NgForm } from '@angular/forms';
import {BooksRoutingModule} from '@app/books/books-routing.module';
import {BooksComponent} from '@app/books/books.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    BooksRoutingModule,
    DataTablesModule
  ],
  declarations: [
    LayoutComponent,
    BooksComponent,
    AddEditComponent
  ]
})
export class BooksModule { }
