import { Component } from '@angular/core';

// services
import { MessageBarService } from '../../../common/services/message-bar.service';

// models
import { Message } from '../../../common/models/message';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'page-home',
    templateUrl: 'home.html'
})
export class HomePage {

    constructor(private messageBarService: MessageBarService) {

    }

    showMessage(type: string): void {
        let message = new Message();

        switch(type) { 
            case 'S': { 
                message.messageCode = 'MSG_0001';
                message.messageEn = 'Success message';
                message.messageType = 'S';
                break; 
            } 
            case 'E': { 
                message.messageCode = 'MSG_0001';
                message.messageEn = 'Error message';
                message.messageType = 'E';
                break; 
            }
            case 'I': { 
                message.messageCode = 'MSG_0001';
                message.messageEn = 'Info message';
                message.messageType = 'I';
                break; 
            } 
            default: { 
                message.messageCode = 'MSG_0001';
                message.messageEn = 'Default message';
                break; 
            } 
        }
        this.messageBarService.show(message);
    }

    ngAfterViewInit() {
        $('.dropdown').dropdown();
    }
}