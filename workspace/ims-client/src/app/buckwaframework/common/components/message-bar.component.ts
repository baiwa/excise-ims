import { Component, OnInit } from '@angular/core';

// services
import { MessageBarService } from '../services/message-bar.service';

// models
import { Message } from '../models/message';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'message-bar',
    template: `
    <div *ngIf="message" [ngClass]="{'hidden': !isVisible, 'success': isSuccess, 'info': isInfo, 'negative': isError}" class="ui message">
        <i (click)="hide()" class="close icon"></i>
        <div class="header">
            {{title}}
        </div>
        <p>{{message.messageCode}} : {{message.messageEn}}</p>
    </div>
    `,
    styles: ['.ui.message { margin-bottom: 14px; }']
})
export class MessageBarComponent implements OnInit {

    private title: string = 'Message';
    private isVisible: boolean = false;
    
    private isSuccess: boolean = false;
    private isError: boolean = false;
    private isInfo: boolean = false;

    private message: Message;

    constructor(private messageBarService: MessageBarService) {
        
    }

    ngOnInit(): void {
        this.messageBarService.add(this);
    }

    show(message: Message): void {
        // reset
        this.isSuccess = false;
        this.isInfo = false;
        this.isError = false;

        this.message = message;
        this.isVisible = true;

        switch(message.messageType.toUpperCase()) { 
            case 'S': { 
                this.isSuccess = true;
                this.title = 'Successfully';
                break; 
            } 
            case 'E': { 
                this.isError = true;
                this.title = 'Error';
                break; 
            }
            case 'I': { 
                this.isInfo = true;
                this.title = 'Info';
                break; 
            } 
            default: { 
                this.title = 'Message';
                break; 
            } 
        }
    }

    hide(): void {
        this.isVisible = false;
    }
}