import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AlertComponent} from './_components';
import {HomeComponent} from './home';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ProfileComponent } from './profile/profile.component';

import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {DataTablesModule} from 'angular-datatables';
import { InsideBookComponent } from './inside-book/inside-book.component';
import {ChangePasswordComponent} from '@app/users/change-password.component';
import {EditProfileComponent} from "@app/profile/edit-profile.component";
import {NgApexchartsModule} from 'ng-apexcharts';;
import { DashboardComponent } from './dashboard/dashboard.component'

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgbModule,
    MatCardModule,
    NoopAnimationsModule,
    MatFormFieldModule,
    DataTablesModule,
    NgApexchartsModule
  ],

  declarations: [
    AppComponent,
    AlertComponent,
    HomeComponent,
    ChangePasswordComponent,
    InsideBookComponent,
    ProfileComponent,
    EditProfileComponent,
    DashboardComponent,
    ],

  bootstrap: [AppComponent],
  exports: []
})

export class AppModule {
}
