import { Component, OnInit } from '@angular/core';

// services
import { MessageBarService } from '../services/message-bar.service';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'message-bar',
    template: `
    <div [ngClass]="{'hidden': !isVisible}" class="ui success message">
        <i (click)="hide()" class="close icon"></i>
        <div class="header">
            Successfully
        </div>
        <p>The message was saved.</p>
    </div>
    `,
    styles: ['.ui.message { margin-bottom: 14px; }']
})
export class MessageBarComponent implements OnInit {

    private isVisible: boolean = false;

    constructor(private messageBarService: MessageBarService) {
        
    }

    ngOnInit(): void {
        this.messageBarService.add(this);
    }

    show(): void {
        this.isVisible = true;
    }

    hide(): void {
        this.isVisible = false;
    }
}