import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { MessageService } from '../../services/message.service';

import { Message } from '../../model/message';

@Component({
    selector: 'page-message-detail',
    templateUrl: 'message-detail.html'
})
export class MessageDetailPage implements OnInit {

    title: string;
    message: Message;

    constructor(
        private messageService: MessageService,
        private location: Location,
        private router: Router,
        private route: ActivatedRoute
    ) {
        
    }

    ngOnInit(): void {
        let id = this.route.snapshot.params['id'];
        console.log('id' + id)
        if (id) {
            this.title = 'Edit Message';
            this.message = new Message();
        } else {
            this.title = 'Add Message';
            this.message = new Message();
        }
    }

    save(): void {
        this.messageService.create(this.message)
            .then((m)=> {
                console.log('m')
                console.log(m)
                this.router.navigate(['/message-detail', m.messageId]);
            });
    }

    back(): void {
        this.location.back();
    }
}