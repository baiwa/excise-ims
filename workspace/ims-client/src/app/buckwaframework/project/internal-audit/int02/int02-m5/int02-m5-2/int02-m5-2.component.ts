import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router } from "@angular/router";

declare var $: any;

@Component({
    selector: "app-int02-m5-2",
    templateUrl: "./int02-m5-2.component.html",
    styleUrls: ["./int02-m5-2.component.css"]
})
export class Int02M52Component implements OnInit, OnDestroy {

    list: any;
    datatable: any;

    constructor(private router: Router) {
        this.list = [];
        for (let i = 1; i < 99; i++) {
            this.list.push(i);
        }
    }

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        this.datatable = $("#datatable").DataTable({
            engthChange: false,
            searching: false,
            select: true,
            ordering: true,
            pageLength: 5,
            lengthMenu: [5, 10, 25, 50, 75, 100],
            // processing: true,
            // serverSide: true,
            paging: true,
            pagingType: "full_numbers"
        });
    }

    search(): void {
        this.router.navigate(['/int02/m5/2/1']);
    }

}
