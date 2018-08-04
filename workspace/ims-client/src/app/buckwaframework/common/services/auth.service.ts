import { Injectable } from "@angular/core";
import { Headers, Http } from "@angular/http";
import { Router } from "@angular/router";

import { Observable } from "rxjs";
import { Subject } from "rxjs/Subject";
import { AjaxService } from "./ajax.service";

// model
import { User } from "../models/user";

@Injectable()
export class AuthService {
  readonly LOGIN_URL = "security/login";
  private headers = new Headers({
    "Content-Type": "application/x-www-form-urlencoded"
  });

  private userSubject = new Subject<User>();
  private user: User = new User();
  private isLoggedIn: boolean = false;
  private intertalAuditPageList = [];
  // store the URL so we can redirect after logging in
  redirectUrl: string;
  private authenPages: String[];
  constructor(
    private http: Http,
    private ajaxService: AjaxService,
    private router: Router
  ) { }

  login(userBean: User): Promise<any> {
    let body = `username=${userBean.username}&password=${userBean.password}`;

    let p = new Promise((resolve, reject) => {
      this.ajaxService.post(
        this.LOGIN_URL,
        body,
        res => {
          this.isLoggedIn = true;
          // this.user.username = 'admin';
          // this.userSubject.next(this.user);
          this.router.navigate(["/home"]);
          resolve("OK");
        },
        (resp: Response) => {
          this.isLoggedIn = false;
          reject("FAIL");
        },
        AjaxService.FORM_HEADER
      );
    });

    return p;
  }

  logout(): void {
    this.isLoggedIn = false;
    this.user = new User();
    this.ajaxService.delete(this.LOGIN_URL, (res: Response) => {
      this.router.navigate(["/login"]);
    });
  }



  authState(): Observable<User> {
    return this.userSubject.asObservable();
  }

  private handleError(error: any): Promise<any> {
    console.error("An error occurred", error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  getLogin(): boolean {
    return this.isLoggedIn;
  }

  getUser(): User {
    return this.user;
  }

  renderByPage(pages) {
    //console.log(this.user);
    var countInpage = 0;
    if (this.authenPages != null && this.authenPages != undefined && this.authenPages.length > 0) {
      var pageList = pages.split(',');
      pageList.forEach(element => {
        if (this.authenPages.indexOf(element) >= 0) {
          countInpage++;
        }
      });
    } else {
      return this.isLoggedIn;
    }
    //console.log(this.isLoggedIn && countInpage > 0);
    return this.isLoggedIn && countInpage > 0;
  }



  getUserProfile(): Promise<boolean> {
    const usrProfile = "access-control/user-profile";
    let p = new Promise<boolean>((resolve, reject) => {
      //check session Ajax
      this.ajaxService.get(
        usrProfile,
        (res: Response) => {
          let body: any = res.json();
          this.user.username = body.data.username;
          this.isLoggedIn = true;
          this.authenPages = body.data.exciseBaseControl;
          //console.log(body.data);
          resolve(true);
        },
        (resError: Response) => {
          resolve(true);
          //Back Login
          this.isLoggedIn = false;
          this.router.navigate(["/login"]);
        }
      );
    });

    return p;
  }
}
