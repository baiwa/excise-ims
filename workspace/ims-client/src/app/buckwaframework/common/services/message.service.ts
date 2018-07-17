import { Injectable } from "@angular/core";
import { Headers, Http } from "@angular/http";

import { Message } from "../../common/models/message";

import "rxjs/add/operator/toPromise";

@Injectable()
export class MessageService {
  readonly url = "preferences/message";
  private headers = new Headers({ "Content-Type": "application/json" });

  constructor(private http: Http) {}

  create(message: Message): Promise<Message> {
    return this.http
      .post(this.url, message, { headers: this.headers })
      .toPromise()
      .then(res => JSON.parse(res["_body"]).data as Message)
      .catch(this.handleError);
  }

  delete(message: string): Promise<Message> {
    return this.http
      .delete(this.url + "/" + message, { headers: this.headers })
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  update(message: Message): Promise<Message> {
    return this.http
      .put(this.url, message, { headers: this.headers })
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  read(message: Message): Promise<Message> {
    return this.http
      .get(this.url + "/" + message.messageId, { headers: this.headers })
      .toPromise()
      .then(res => JSON.parse(res["_body"]).data as Message)

      .catch(this.handleError);
  }
  private handleError(error: any): Promise<any> {
    console.error("An error occurred", error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
