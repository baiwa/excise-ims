import { Component } from '@angular/core';

// services
import { MessageBarService } from '../../../common/services/message-bar.service';

// models
import { Message } from '../../../common/models/message';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'select-form',
    templateUrl: 'select-form.component.html',
    styleUrls: ['select-form.component.css']
})
export class SelectFormComponent {

    private showSubMenuMat: boolean = false;
    private showSubMenuIns: boolean = false;

    constructor(private messageBarService: MessageBarService) {

    }

    ngAfterViewInit() {
        // $('.dropdown').dropdown();
    }

    clickMenuMat() {
        this.showSubMenuMat = true;
        this.showSubMenuIns = false;
    }

    clickMenuIns() {
        this.showSubMenuMat = false;
        this.showSubMenuIns = true;
    }
}