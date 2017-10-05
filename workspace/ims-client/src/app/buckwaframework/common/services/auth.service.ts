import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';

// model
import { User } from '../models/user';

@Injectable()
export class AuthService {

    readonly url = 'api/security/login';
    private headers = new Headers({'Content-Type': 'application/x-www-form-urlencoded'});

    private userSubject = new Subject<User>();
    private user;
    isLoggedIn = false;

    // store the URL so we can redirect after logging in
    redirectUrl: string;

    constructor(private http: Http) {
        // load messages
    }

    callLogin(): Promise<any> {
        let username = 'user';
        let password = 'password';

        let body = `username=${username}&password=${password}`;
        return this.http.post(this.url, body, {headers: this.headers})
            .toPromise()
            .then(res => JSON.parse(res['_body']).data)
            .catch(this.handleError);
    }

    login() : void {
        this.callLogin();

        this.isLoggedIn = true;

        this.user = new User();
        this.user.username = 'admin';
        this.userSubject.next(this.user);
    }

    logout(): void {
        this.isLoggedIn = false;
        
        this.userSubject.next(null);
    }

    authState(): Observable<User> {
        return this.userSubject.asObservable();
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}