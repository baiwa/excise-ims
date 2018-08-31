import { Component, Input } from "@angular/core";

@Component({
    selector: 'Modal',
    templateUrl: './modal.component.html',
    styleUrls: ['./modal.component.css']
})
export class ModalComponent {

    @Input() success: boolean = false; // change button in modal type: alert
    @Input() type: string = "alert"; // custom confirm alert
    @Input() size: string; // mini tiny small large
    @Input() title: string; // title of header
    @Input() id: string; // id of modal

    constructor() {
        // TODO
    }

    chkType(_case): boolean { // check type of modal
        return this.type === _case;
    }

}