import { Component } from '@angular/core';

import { AccountService } from './_services';
import { User } from './_models';

@Component({
    selector: 'app',
    templateUrl: 'app.component.html'
})

export class AppComponent {
    user: User;
    userId: number;
    roleOfUser: string;

    constructor(private accountService: AccountService) {
        // this.statusOfUser = accountService
        this.accountService.user.subscribe(x => this.user = x);
        // if (this.user !== null) {
        //   this.userId = accountService.userId;
        // }
    }

    logout() {
        this.accountService.logout();
    }
}
