import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

// services
import { MessageBarService } from '../../../common/services/message-bar.service';
import { MessageService } from '../../../common/services/message.service';
import { AjaxService } from '../../../common/services/ajax.service';

// models
import { Dropdown } from '../../../common/models/dropdown';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'page-message',
    templateUrl: 'message.html'
})
export class MessagePage implements OnInit  {

    messageTypes: Dropdown[];
    messageDt: any;
    editMessageMd: any;
    checkboxes; Array;

    constructor(
        private messageService: MessageService,
        private messageBarService: MessageBarService,
        private router: Router,
        private ajaxService: AjaxService
    ) {
        console.log('do constructor message page');
    }

    ngOnInit(): void {
        console.log('do init message page');

        this.messageTypes = new Array();

        let dd = new Dropdown();
        dd.value = 'I';
        dd.name = 'Info';
        dd.text = 'Info';
        this.messageTypes.push(dd);

        dd = new Dropdown();
        dd.value = 'E';
        dd.name = 'Error';
        dd.text = 'Error';
        this.messageTypes.push(dd);

        dd = new Dropdown();
        dd.value = 'W';
        dd.name = 'Waring';
        dd.text = 'Waring';
        this.messageTypes.push(dd);

        dd = new Dropdown();
        dd.value = 'C';
        dd.name = 'Confirm';
        dd.text = 'Confirm';
        this.messageTypes.push(dd);
    }

    ngAfterViewInit() {
        this.initDatatable();
    }

    delete(): void {
        // checkboxes = document.getElementsByName('foo');
        // for(var i=0, n=checkboxes.length;i<n;i++) {
        //   checkboxes[i].checked = source.checked;
        // }
        console.log('Delete !' );
        this.checkboxes  = document.getElementsByName('checkMessageID');
        for(var i=0, n=this.checkboxes.length;i<n;i++) {
            if(this.checkboxes[i].checked){
                console.log(this.checkboxes[i].defaultValue);
                this.messageService.delete(this.checkboxes[i].defaultValue)
            }
          }
          this.messageDt.ajax.reload();

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
                "url": AjaxService.CONTEXT_PATH + this.messageService.url+"/search"
            },
            "columns": [
                {
                    "data": "messageId",
                    "render": function(data, type, full, meta) {
                        return '<div class="ui checkbox"><input name="checkMessageID" value="'+data+'" type="checkbox"><label></label></div>';
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
            "rowCallback": (row, data, index) => {
                $('td > .edit', row).bind('click', () => {
                    // console.log("data ",data);
                    this.router.navigate(['/edit-message', data.messageId]);
                });
            }
        });
    }
}