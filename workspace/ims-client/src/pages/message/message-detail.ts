import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

import { MessageService } from '../../services/message.service';

import { Message } from '../../model/message';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'page-message-detail',
    templateUrl: 'message-detail.html'
})
export class MessageDetailPage implements OnInit {

    title: string;
    message: Message;
    messageForm: any;

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
        if (!this.messageForm.form('validate form')) return;

        this.messageService
            .create(this.message)
            .then((m)=> {
                console.log('m')
                console.log(m)
                this.router.navigate(['/message-detail', m.messageId]);
            });
    }

    back(): void {
        this.location.back();
    }

    ngAfterViewInit() {
        console.log('ngAfterViewInit')
        this.messageForm = $('#messageForm').form({
            on: 'blur',
            inline : true,
            fields: {
                    messageCode: {
                        identifier  : 'messageCode',
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'Please enter a value'
                                }
                            ]
                        },
                    messageType: {
                        identifier  : 'messageType',
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'Please enter a value'
                                }
                            ]
                        },
                    messageEn: {
                        identifier  : 'messageEn',
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'Please enter a value'
                                }
                            ]
                        },
                    messageTh: {
                        identifier  : 'messageTh',
                            rules: [
                                {
                                    type   : 'empty',
                                    prompt : 'Please enter a value'
                                }
                            ]
                        }
                    }
            });
    }
}