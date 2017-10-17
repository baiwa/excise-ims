import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Router } from '@angular/router';

import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';
import { AjaxService } from './ajax.service';

// model
import { User } from '../models/user';

@Injectable()
export class AuthService {

    readonly LOGIN_URL = 'api/security/login';
    private headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });

    private userSubject = new Subject<User>();
    private user: User = new User();
    private isLoggedIn: boolean = false;

    // store the URL so we can redirect after logging in
    redirectUrl: string;

    constructor(private http: Http, private ajaxService: AjaxService, private router: Router, ) {

    }



    login(userBean: User): Promise<any> {
        let body = `username=${userBean.username}&password=${userBean.password}`;

        let p = new Promise((resolve, reject) => {
            this.ajaxService.Post(this.LOGIN_URL, body, (res) => {
                this.isLoggedIn = true;
                // this.user.username = 'admin';
                // this.userSubject.next(this.user);
                this.router.navigate(['/home']);
                resolve("OK");
            }, (resp: Response) => {
                this.isLoggedIn = false;
                console.log(resp.status);
                reject("FAIL");
            }, AjaxService.FORM_HEADER);
        });

        return p;
    }

    logout(): void {
        this.isLoggedIn = false;
        this.user = new User();
        this.ajaxService.DELETE(this.LOGIN_URL, (res: Response) => {
            console.log("Logout Success...");
            this.router.navigate(['/login']);
        });
    }

    authState(): Observable<User> {
        return this.userSubject.asObservable();
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getLogin(): boolean {
        return this.isLoggedIn;
    }

    getUser(): User {
        return this.user;
    }

    getUserProfile(): Promise<boolean> {
        const usrProfile = "api/user/getUserProfile";
        let p = new Promise<boolean>((resolve, reject) => {
            //check session Ajax
            // console.log("check session Ajax");
            this.ajaxService.GET(usrProfile, (res: Response) => {
                let body: any = res.json();
                this.user.username = body.username;
                this.isLoggedIn = true;
                resolve(true);
            }, (resError: Response) => {
                resolve(true);
                //Back Login
                this.isLoggedIn = false;
                this.router.navigate(['/login']);
            });

        });

        return p;
    }
}