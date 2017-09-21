import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'page-login',
    templateUrl: 'login.html',
    styles:[
        `
        body > .grid {
            height: 100%;
        }
        .image {
            margin-top: -100px;
        }
        .column {
            max-width: 450px;
        }
        `
    ]
})
export class LoginPage {

    constructor(private router: Router) {

    }

    goToHome(): void {
        this.router.navigate(['home']);
    }

}