import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { AjaxService } from "services/ajax.service";

const URL = {
  VALUE1: "ia/int0806/getValue1",
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};

@Injectable()
export class Int0806Service {
  constructor(private ajax: AjaxService) {}

  LovGetValue1 = (type: string, id?: number): Observable<any> => {
    const DATA = { type: type, lovIdMaster: id || null };
    return new Observable<ComboBox[]>(obs => {
      this.ajax
        .post(URL.VALUE1, DATA, res => {
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

  pullComboBox = (
    type: string,
    combo: string,
    id?: number
  ): Observable<ComboBox[]> => {
    return new Observable<ComboBox[]>(obs => {
      this.dropdown(type, combo, id).then(() => obs.next(this[combo]));
    });
  };

  dropdown = (type: string, combo: string, id?: number): Promise<any> => {
    const DATA = { type: type, lovIdMaster: id || null };
    return this.ajax.post(URL.DROPDOWN, DATA, res => {
      this[combo] = res.json();
    });
  };
}
