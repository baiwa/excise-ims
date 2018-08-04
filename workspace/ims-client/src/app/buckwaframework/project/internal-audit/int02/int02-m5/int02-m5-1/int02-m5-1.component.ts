import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router } from "@angular/router";

declare var $: any;

@Component({
    selector: "app-int02-m5-1",
    templateUrl: "./int02-m5-1.component.html",
    styleUrls: ["./int02-m5-1.component.css"]
})
export class Int02M51Component implements OnInit, OnDestroy {

    constructor(private router: Router) {
    }

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
    }

    create(): void {
        this.router.navigate(['/int02/m5/1/1']);
    }

}
