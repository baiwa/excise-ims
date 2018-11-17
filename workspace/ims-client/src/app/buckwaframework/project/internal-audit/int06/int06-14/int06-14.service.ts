import { Observable } from "rxjs/Observable";
import { ComboBox } from "models/combobox";
import { AjaxService } from "services/ajax.service";
import { Injectable } from "@angular/core";

import { Router } from "@angular/router";
import { MessageBarService } from "services/message-bar.service";

const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
declare var $: any;
@Injectable()
export class Int0614Service {
  dataTable: any;
  constructor(
    private ajax: AjaxService,
    private msg: MessageBarService,
    private router: Router
  ) {}

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

  clear = () => {
    $(".office").dropdown("restore defaults");
  };

  superdataTable = () => {
    this.dataTable = $("#dataTable").DataTableTh({});
  };
}
