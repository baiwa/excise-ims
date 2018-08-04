import { Component, OnInit } from "@angular/core";

declare var $: any;

@Component({
    selector: "app-int02-m5-1-1",
    templateUrl: "./int02-m5-1-1.component.html",
    styleUrls: ["./int02-m5-1-1.component.css"]
})
export class Int02M511Component implements OnInit {

    datatable: any;

    constructor() { }

    ngOnInit() { }

    save() {
        alert("บันทึกละ เอาไงต่อ");
    }

}