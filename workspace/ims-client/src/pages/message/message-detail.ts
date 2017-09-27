import { Component } from '@angular/core';

@Component({
    selector: 'page-message-detail',
    templateUrl: 'message-detail.html'
})
export class MessageDetailPage {
    title : string;

    constructor() {
        this.title = 'Add Message';
    }

    save(): void {
        console.log('Test');
    }
}