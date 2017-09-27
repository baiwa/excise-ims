import { Component } from '@angular/core';
import { Message } from '../../model/message';
import { MessageService } from '../../services/message.service';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'page-message',
    templateUrl: 'message.html'
})
export class MessagePage {

    messageDt: any;
    editMessageMd: any;

    constructor(private messageService: MessageService) {
        console.log(messageService.url);

    }

    showEditMessageMd(message: Message): void {

    }

    ngAfterViewInit() {
        this.initDatatable();
    }

    initDatatable(): void {
        // console.log(messageService.url)
        this.messageDt = $('#messageDt').DataTable({
            "lengthChange": false,
            "searching": false,
            "ordering": false,
            "pageLength": 10,
            "processing": true,
            "serverSide": true,
            "paging": true,
            "pagingType": "full_numbers",
            "ajax": {
                "type": "GET",
                // "url": "http://localhost:8084/webpoc/api/preferences/message",
                "url": this.messageService.url
            },
            "columns": [
                { "data": "messageCode" },
                { "data": "messageEn" }, 
                { "data": "messageTh" },
                { "data": "messageType" },
                {
                    "data": "messageId",
                    "render": function() {
                        return '<button type="button" class="ui button edit">Edit</button>';
                    }
                }
            ],
            "rowCallback": function(row, data, index) {
                $('td > .edit', row).bind('click', () => {
                    this.showEditMessageMd(data);
                });
            }
        });
    }
}