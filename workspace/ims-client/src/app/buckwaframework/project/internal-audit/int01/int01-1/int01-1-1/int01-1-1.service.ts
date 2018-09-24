import { Injectable } from "@angular/core";
import { AjaxService } from "../../../../../common/services";
import { ComboBox } from "../../../../../common/models";
import { Observable } from "rxjs";
import { digit } from "../../../../../common/helper";
import { toDateLocale, formatter, TextDateTH } from "../../../../../common/helper/datepicker";
import { NgForm } from "@angular/forms";

const URL = {
    DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};

declare var $: any;

@Injectable()
export class Int0111Service {

    combobox1: ComboBox[] = [];
    combobox2: ComboBox[] = [];
    combobox3: ComboBox[] = [];

    constructor(private ajax: AjaxService) {
        // TODO
    }

    dropdown = (type: string, combo: string, id?: number): Promise<any> => {
        const DATA = { type: type, lovIdMaster: id || null };
        return this.ajax.post(URL.DROPDOWN, DATA,
            res => { this[combo] = res.json(); }
        );
    }

    findById = (id: number, combo: string) => {
        return this[combo].find( obj => obj.lovId == id);
    }

    pullComboBox = (type: string, combo: string, id?: number): Observable<ComboBox[]> => {
        return new Observable<ComboBox[]>(obs => {
            this.dropdown(type, combo, id).then(
                () => obs.next(this[combo])
            );
        });
    }

    calendar = (fromId: string, toId: string, form: NgForm): void => {
        $(`#${fromId}`).calendar({
            endCalendar: $(`#${toId}`),
            maxDate: new Date(),
            type: "date",
            text: TextDateTH,
            formatter: formatter(),
            onChange: async (date) => {
                let day = date.getDate();
                let _month = await toDateLocale(date)[0].split("/")[1];
                let _year = await toDateLocale(date)[0].split("/")[2];
                form.controls.startDate.setValue(digit(day) + "/" + digit(_month) + "/" + _year.toString());
            }
        });
        $(`#${toId}`).calendar({
            startCalendar: $(`#${fromId}`),
            maxDate: new Date(),
            type: "date",
            text: TextDateTH,
            formatter: formatter(),
            onChange: async (date) => {
                let day = date.getDate();
                let _month = await toDateLocale(date)[0].split("/")[1];
                let _year = await toDateLocale(date)[0].split("/")[2];
                form.controls.endDate.setValue(digit(day) + "/" + digit(_month) + "/" + _year.toString());
            }
        });
    }

    reset(a, b): void {
        $(".ui.dropdown.search").dropdown("restore defaults");
        $(`#${a}`).calendar('refresh');
        $(`#${b}`).calendar('refresh');
    }

}