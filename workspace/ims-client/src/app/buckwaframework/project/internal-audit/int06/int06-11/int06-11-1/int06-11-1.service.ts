import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "services/message-bar.service";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { resolve } from "url";
import { reject } from "q";

const URL = {
  SAVE: "ia/int061101/save",
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId",
  AUTH_LOGIN: "ia/int061101/getAuthLogin"
  // AUTH_LOGIN: "/restful/versionProgramByPageCode"
};

@Injectable()
export class Int061101Service {
  constructor(
    private ajax: AjaxService,
    private msg: MessageBarService,
    private router: Router
  ) {}

  getUserLogin = () => {
    return new Promise<any>((resolve, reject) => {
      this.ajax.post(URL.AUTH_LOGIN, {}, res => {
        resolve(res.json());
      }),
        error => {
          reject(error);
        };
    });
  };

  dropdown = (type: string, id?: number): Observable<any> => {
    const DATA = { type: type, lovIdMaster: id || null };
    return new Observable<ComboBox[]>(obs => {
      this.ajax
        .post(URL.DROPDOWN, DATA, res => {
          this[type] = res.json();
        })
        .then(() => {
          obs.next(this[type]);
        });
    });
  };

  save(formData: any) {
    this.ajax.post(URL.SAVE, formData, res => {
      console.log(res.json());
    });
  }
}
