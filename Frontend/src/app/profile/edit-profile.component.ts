import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgForm } from '@angular/forms';
import { first } from 'rxjs/operators';
import { User } from '@app/_models/user';

import { AccountService, AlertService } from '@app/_services';

@Component({
  templateUrl: 'edit-profile.component.html',
  styleUrls: ['edit-profile.component.less']
})

export class EditProfileComponent implements OnInit {
  form: FormGroup;
  id: string;
  isAddMode: boolean;
  loading = false;
  submitted = false;
  userId: number;
  userRole: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
    private alertService: AlertService
  ) { }

  ngOnInit() {
    this.userRole = this.route.snapshot.params['role'];
    this.id = this.route.snapshot.params['id'];
    this.isAddMode = !this.id;
    this.userId = this.accountService.userId;

    this.form = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      age: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern('^((\\+91-?)|0)?[0-9]{9}$')]],
      role: ['', Validators.required],
    });

    if (!this.isAddMode) {
      this.accountService.getById(this.id)
        .pipe(first())
        .subscribe(x => {
          this.f.firstName.setValue(x.firstName);
          this.f.lastName.setValue(x.lastName);
          this.f.age.setValue(x.age);
          this.f.phone.setValue(x.phone);
          this.f.email.setValue(x.email);
          this.f.role.setValue(x.role);
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

    this.updateUser();
  }

  private updateUser() {
    this.accountService.editUserFromProfile(this.id, this.form.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Update successful', { keepAfterRouteChange: true });
          this.router.navigate(['../../../profile/' + this.id, { relativeTo: this.id }]);
        },
        error => {
          this.alertService.error(error.error.errorMessage);
          this.loading = false;
        });
  }
}
