import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { LayoutComponent } from './layout.component';
import { ListComponent } from './list.component';
import { AddEditComponent } from './add-edit.component';
import {DataTablesModule} from 'angular-datatables';
import { NgForm } from '@angular/forms';
import {EditProfileComponent} from '@app/profile/edit-profile.component';

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        UsersRoutingModule,
        DataTablesModule
    ],
    declarations: [
        LayoutComponent,
        ListComponent,
        AddEditComponent,
    ]
})
export class UsersModule { }
