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
    styleUrls : ['select-form.component.css']
})
export class SelectFormComponent {

    constructor(private messageBarService: MessageBarService) {

    }



    ngAfterViewInit() {
        // $('.dropdown').dropdown();
    }
    
}