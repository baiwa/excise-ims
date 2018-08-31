/**
 * CREATED BY: KimJaeHa
 * DATE: 30 AUG 2018 15:35
 */
import { Component, Input } from '@angular/core';

@Component({
    selector: "Accordion",
    templateUrl: "./accordion.component.html",
    styleUrls: ["./accordion.component.css"]
})
export class AccordionComponent {

    @Input() list: Accordion[] = [];

    constructor() {
        // TODO
    }

}

class Accordion {
    title?: string;
    content?: string;
}