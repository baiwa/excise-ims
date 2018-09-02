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

    constructor() { }

    ngOnDestroy() { }

    ngOnInit() {
        $("#calendar").calendar({
            type: "year",
            text: TextDateTH,
            formatter: formatter("year")
        });
    }

    search() {
        this.searchValue.emit("step1");
    }

}
