import { Component, OnInit, OnDestroy, Output, EventEmitter } from "@angular/core";
import { TextDateTH, formatter } from "../../../../../common/helper";

declare var $: any;

@Component({
    selector: "app-int02-m4-1",
    templateUrl: "./int02-m4-1.component.html",
    styleUrls: ["./int02-m4-1.component.css"]
})
export class Int02M41Component implements OnInit, OnDestroy {

    @Output() searchValue = new EventEmitter<any>();
    @Output() year = new EventEmitter<string>();
    _year: string = "";

    constructor() { }

    ngOnDestroy() { }

    ngOnInit() {
        $("#calendar").calendar({
            type: "year",
            maxDate: new Date(),
            text: TextDateTH,
            formatter: formatter("year"),
            onChange: (date) => {
                const _date = new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()));
                this._year = _date.toLocaleString('th-TH', { timeZone: 'UTC' }).split(" ")[0].split("/")[2];
            }
        });
    }

    search() {
        this.year.emit(this._year);
        this.searchValue.emit("step1");
    }

}
