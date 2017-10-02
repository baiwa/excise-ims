import { Component, OnInit } from '@angular/core';

// services
import { MessageBarService } from '../../../common/services/message-bar.service';
import { MessageService } from '../../../common/services/message.service';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'page-message',
    templateUrl: 'message.html'
})
export class MessagePage implements OnInit  {

    messageDt: any;
    editMessageMd: any;

    constructor(
        private messageService: MessageService,
        private messageBarService: MessageBarService
    ) {
        console.log('do constructor message page');
    }

    ngOnInit(): void {
        console.log('do init message page');
    }

    ngAfterViewInit() {
        this.initDatatable();
    }

    initDatatable(): void {
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
                "url": this.messageService.url
            },
            "columns": [
                {
                    "data": "messageId",
                    "render": function() {
                        return '<div class="ui checkbox"><input type="checkbox"><label></label></div>';
                    }
                },
                { "data": "messageCode" },
                { "data": "messageEn" }, 
                { "data": "messageTh" },
                { "data": "messageType" },
                {
                    "data": "messageId",
                    "render": function() {
                        return '<button type="button" class="ui mini button edit"><i class="pencil icon"></i> Edit</button>';
                    }
                }
            ],
            "columnDefs": [
                { targets: [0, 5], className: "center aligned"}
            ],
            "rowCallback": function(row, data, index) {
                $('td > .edit', row).bind('click', () => {
                    this.showEditMessageMd(data);
                });
            }
        });
    }
}