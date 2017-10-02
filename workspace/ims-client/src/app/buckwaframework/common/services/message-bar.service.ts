import { Injectable } from '@angular/core';

// components
import { MessageBarComponent } from '../components/message-bar.component';

// models
import { Message } from '../models/message';

@Injectable()
export class MessageBarService {

    private messageBar: MessageBarComponent;

    constructor() {
        console.log('Do init service message bar');
    }

    add(messageBar: MessageBarComponent): void {
        this.messageBar = messageBar;
    }

    show(message: Message): void {
        this.messageBar.show(message);
    }

    hide(): void {
        this.messageBar.hide();
    }
}