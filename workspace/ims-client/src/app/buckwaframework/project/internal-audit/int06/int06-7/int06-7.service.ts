import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AjaxService } from "services/ajax.service";
import { ComboBox } from "models/combobox";

const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};

declare var $: any;

@Injectable()
export class Int067Service {
  combobox1: ComboBox[] = [];
  combobox2: ComboBox[] = [];
  combobox3: ComboBox[] = [];

  constructor(private ajax: AjaxService) {
    // TODO
  }

  //   pullComboBox = () => {
  //     const DATA = { type: "SECTOR_VALUE" };
  //     this.ajax.post(URL.DROPDOWN, DATA, res => {
  //       let comboList = res.json();
  //       console.log(comboList);
  //     });
  //   };

  dropdown = (type: string, combo: string, id?: number): Promise<any> => {
    const DATA = { type: type, lovIdMaster: id || null };
    return this.ajax.post(URL.DROPDOWN, DATA, res => {
      this[combo] = res.json();
    });
  };

  findById = (id: number, combo: string) => {
    return this[combo].find(obj => obj.lovId == id);
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
}
