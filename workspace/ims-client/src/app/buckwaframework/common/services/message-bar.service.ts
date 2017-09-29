import { Injectable } from '@angular/core';

// components
import { MessageBarComponent } from '../components/message-bar.component';

@Injectable()
export class MessageBarService {

    private messageBar: MessageBarComponent;

    add(messageBar: MessageBarComponent): void {
        this.messageBar = messageBar;
    }

    show() {
        this.messageBar.show();
    }

}