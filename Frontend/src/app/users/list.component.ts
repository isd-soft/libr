import { Component, OnDestroy, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { AccountService } from '@app/_services';
import { Subject } from 'rxjs';
import { User } from '@app/_models';
import { ActivatedRoute, Router } from '@angular/router';


@Component({ templateUrl: 'list.component.html' })
export class ListComponent implements OnDestroy, OnInit {
  dtOptions: DataTables.Settings = {};
  users: User[] = [];
  userRole: string;

  // We use this trigger because fetching the list of persons can be quite long,
  // thus we ensure the data is fetched before rendering
  dtTrigger: Subject<any> = new Subject<any>();

  constructor(private accountService: AccountService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 20
    };
    this.accountService.getAll()
      .pipe(first())
      .subscribe(users => {
        this.users = users;
        this.dtTrigger.next();
      });
    this.userRole = this.accountService.getRole();
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  deleteUser(id: string) {
    const user = this.users.find(x => x.id === id);
    // user.isDeleting = true;
    this.accountService.delete(id)
      .pipe(first())
      .subscribe(() => {
        this.users = this.users.filter(x => x.id !== id);
      });
  }

  navigateToProfile(id: string) {
    this.router.navigate(["/profile/" + id], { relativeTo: this.activatedRoute });
  }

  public sendEmail(id: string, type: string) {

    this.accountService.sendEmail(id, type);
  }
}
