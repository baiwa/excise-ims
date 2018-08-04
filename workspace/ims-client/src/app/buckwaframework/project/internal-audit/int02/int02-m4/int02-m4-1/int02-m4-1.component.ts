import { Component, OnInit, OnDestroy, Output, EventEmitter } from "@angular/core";

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

    ngOnInit() { }

    search() {
        this.searchValue.emit("step1");
    }

}
