import { Component, Input, OnDestroy } from "@angular/core";

declare var $: any;
@Component({
    selector: 'ui-modal',
    templateUrl: './modal.component.html',
    styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnDestroy{

    @Input() success: boolean; // change button in modal type: alert
    @Input() type: string; // custom confirm alert
    @Input() size: string; // mini tiny small large
    @Input() title: string; // title of header
    @Input() id: string; // id of modal

    constructor() {
        // TODO
    }

    ngOnDestroy(): void {
        $(`#${this.id}`).remove();
    }

    chkType(_case): boolean { // check type of modal
        return this.type === _case;
    }

}