import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { AjaxService } from "services/ajax.service";

const URL = {
  DEFUALT: "combobox/controller/getDropByTypeAndParentId"
};

@Injectable()
export class ComboBoxService {
  constructor(private ajax: AjaxService) {}

  Lov = (type: string, id?: number): Observable<any> => {
    const DATA = { type: type, lovIdMaster: id || null };
    return new Observable<ComboBox[]>(obs => {
      this.ajax
        .post(URL.DEFUALT, DATA, res => {
          this[type] = res.json();
        })
        .then(() => {
          obs.next(this[type]);
        })
        .catch(() => {
          console.log("Fail");
        }),
        error => {
          console.log(error);
        };
    });
  };
}
