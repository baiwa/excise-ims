import { Component } from "@angular/core";
import { BreadCrumb } from "models/breadcrumb";

@Component({
    selector: 'ui-breadcrumb',
    templateUrl: './breadcrump.html',
    styleUrls: ['./breadcrump.css']
})
export class BreadCrump {

    breadcrumb1: BreadCrumb[];

    constructor() {
        // TODO
        this.breadcrumb1 = [
            { label: 'Active', route: '#' },
        ];
    }
}