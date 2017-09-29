import { Component } from '@angular/core';
import { AuthService } from '../../../common/services/auth.service';

@Component({
    selector: 'page-login',
    templateUrl: 'login.html',
    styleUrls:['login.css']
})
export class LoginPage {

    constructor(
        public authService: AuthService) {

    }

    login() {
        this.authService.login();
    }

}