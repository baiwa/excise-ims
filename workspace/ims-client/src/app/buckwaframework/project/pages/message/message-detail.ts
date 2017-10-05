import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';

// services
import { MessageBarService } from '../../../common/services/message-bar.service';
import { MessageService } from '../../../common/services/message.service';

// models
import { Message } from '../../../common/models/message';

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
    statusPage: Boolean;

    constructor(
        private messageService: MessageService,
        private location: Location,
        private router: Router,
        private route: ActivatedRoute,
        private messageBarService: MessageBarService
    ) {
        
    }

    ngOnInit(): void {
        let id = this.route.snapshot.params['id'];
        console.log('id' + id)
        if (id) {
            this.title = 'Edit Message';
            this.message = new Message();
            this.statusPage = false;
            this.message.messageId = id;
            this.getMessageById();
        } else {
            this.title = 'Add Message';
            this.message = new Message();
            this.statusPage = true;
        }
    }

    getMessageById(): void {
        this.messageService.read(this.message).then(respone => {
            console.log(respone);
            this.message = respone;
        })
        

    }



    save(): void {
        if (!this.messageForm.form('validate form')) return;
        if(this.statusPage){
        this.messageService
            .create(this.message)
            .then((m)=> {
                let msg = new Message();
                msg.messageCode = 'MSG_0001';
                msg.messageEn = 'The message was saved.';
                msg.messageTh = 'message ถูกบันทึกแล้ว';
                msg.messageType = 'S';
                this.messageBarService.show(msg);
                this.router.navigate(['/message']);
            });
        }else{
            this.messageService
            .update(this.message)
            .then((m)=> {
                let msg = new Message();
                msg.messageCode = 'MSG_0001';
                msg.messageEn = 'The message was saved.';
                msg.messageTh = 'message ถูกบันทึกแล้ว';
                msg.messageType = 'S';
                this.messageBarService.show(msg);
                this.router.navigate(['/message']);
            });
        }
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