import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
@Injectable()
export class AjaxService {

    public static JSON_HEADER = new Headers({ 'Content-Type': 'application/json' });
    public static FORM_HEADER = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });

    public static CONTEXT_PATH: string = "/ims-webapp/";
    public static isDebug: boolean = true;

    constructor(private http: Http) {

    }


    Post(url: string, body: any, success: any, error?: any, header?: Headers) {
        if (AjaxService.isDebug) {
            console.log("URL : ", AjaxService.CONTEXT_PATH + url);
            console.log("Params : ", body);
        }
        let httpHeader = AjaxService.JSON_HEADER;
        if (header) {
            httpHeader = AjaxService.FORM_HEADER
        }
        let errorFn = this.handleError;
        if (error) {
            errorFn = error;
        }
        return this.http
            .post(AjaxService.CONTEXT_PATH + url, body, { headers: httpHeader })
            .toPromise()
            .then(success)
            .catch(errorFn);
    }

    GET(url: string, success: any, error?: any) {
        let errorFn = this.handleError;
        if (error) {
            errorFn = error;
        }
        return this.http.get(AjaxService.CONTEXT_PATH + url)
            .toPromise()
            .then(success)
            .catch(errorFn);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    DELETE(url: string, success: any, error?: any){
        let errorFn = this.handleError;
        if (error) {
            errorFn = error;
        }
        return this.http.delete(AjaxService.CONTEXT_PATH + url)
            .toPromise()
            .then(success)
            .catch(errorFn);
    }


}