import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { AuthService } from '../../../common/services/auth.service';
import { User } from '../../../common/models/user';

@Component({
    selector: 'page-login',
    templateUrl: 'login.html',
    styleUrls: ['login.css']
})
export class LoginPage implements OnInit {

    public loginForm: FormGroup;
    public loginInvalid: boolean = false;
    public ngLoading = { "loading": false };

    constructor(public authService: AuthService) {

    }

    ngOnInit() {
        this.loginForm = new FormGroup({
            username: new FormControl(''),
            password: new FormControl('')
        });
    }

    login() {
        this.ngLoading = { "loading": true };
        this.authService.login(this.loginForm.value as User)
            .then(ok => {
                this.loginInvalid = false;
                this.ngLoading = { "loading": false };
            })
            .catch(error => {
                this.loginInvalid = true;
                this.ngLoading = { "loading": false };
            });
    }

    onKey(event: KeyboardEvent) {
        let keyCode = event.code || event.keyCode;
        if(keyCode === "Enter" || keyCode === 13){
            this.login();
        }
    }

}