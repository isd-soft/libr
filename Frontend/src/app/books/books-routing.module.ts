import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from './layout.component';
import {BooksComponent} from './books.component';
import {AddEditComponent} from './add-edit.component';

const routes: Routes = [
  {
      path: '', component: LayoutComponent,
    children: [
      { path: '', component: BooksComponent },
      { path: 'add', component: AddEditComponent },
      { path: 'edit/:id', component: AddEditComponent }
      ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  // CommonModule,
  exports: [RouterModule]
})
export class BooksRoutingModule { }
