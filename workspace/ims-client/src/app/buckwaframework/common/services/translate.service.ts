import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Message } from '../models/message';

@Injectable()
export class TranslateService {

    private currentLang: string;
    readonly url = 'api/preferences/message';
    private headers = new Headers({'Content-Type': 'application/json'});
    private messages: Message[] = new Array();

    constructor(private http: Http) {
        // load messages
        console.log('Load messages');
        this.getMessages().then(messages => this.messages = messages);
    }

    
    getMessages(): Promise<any> {
        return this.http.get(this.url)
            .toPromise()
            .then(res => JSON.parse(res['_body']).data)
            .catch(this.handleError);
    }

    public use(lang: string): void {
        this.currentLang = lang;
    }

    private translate(key: string): string {
        let translation = key;

        console.log(this.messages[key]);

        if (this.messages[key]) {
            switch(this.currentLang.toUpperCase()) { 
                case 'TH': { 
                    translation = this.messages[key].messageTh;
                    break; 
                }
                default: { 
                    translation = this.messages[key].messageEn;
                    break; 
                } 
            }
        }

        return translation;
    }
    
    public instant(key: string) {
        return this.translate(key); 
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}