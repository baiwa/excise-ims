import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';

// model
import { User } from '../models/user';

@Injectable()
export class AuthService {

    private userSubject = new Subject<User>();
    private user;
    isLoggedIn = false;

    // store the URL so we can redirect after logging in
    redirectUrl: string;

    login() : void {
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
}