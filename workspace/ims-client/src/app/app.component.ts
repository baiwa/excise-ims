import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

// services
import { AuthService } from './buckwaframework/common/services/auth.service';
import { TranslateService } from './buckwaframework/common/services/translate.service';

// models
import { User } from './buckwaframework/common/models/user';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    
    user: User;

    constructor(
        private authService: AuthService,
        private router: Router,
        private translateService: TranslateService) { }

    ngOnInit(): void {
        this.translateService.use('th');
        this.user = this.authService.getUser();
    }

    logout() {
        this.authService.logout();
    }

    ngAfterViewInit() {
        $('.dropdown').dropdown();
    }

    changeToEnglish() {
        this.translateService.use('en');
    }

    changeToThai() {
        this.translateService.use('th');
    }
}
