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

        this.authService.authState().subscribe((user) => {
            this.user = user;
            if (user) {
                let redirect = this.authService.redirectUrl ? this.authService.redirectUrl : '/home';
                console.log('redirect to ' + redirect)
                this.router.navigate([redirect]);
            } else {
                console.log('redirect to login')
                this.router.navigate(['/login']);
            }
        });
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
