import { Component, Input, OnDestroy, AfterViewInit } from "@angular/core";

declare var $: any;
@Component({
    selector: 'ui-modal',
    templateUrl: './modal.component.html',
    styleUrls: ['./modal.component.css']
})
export class ModalComponent implements AfterViewInit, OnDestroy{

    @Input() success: boolean; // change button in modal type: alert
    @Input() type: string; // custom confirm alert
    @Input() size: string; // mini tiny small large
    @Input() title: string; // title of header
    @Input() id: string; // id of modal

    constructor() {
        // TODO
    }

    ngAfterViewInit(): void {
        $(`#${this.id}`).modal({ autofocus: false });
        console.log("Run this");
    }

    ngOnDestroy(): void {
        $(`#${this.id}`).remove();
    }

    chkType(_case): boolean { // check type of modal
        return this.type === _case;
    }

}