import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

// services
import { MessageBarService } from '../../../common/services/message-bar.service';

// models
import { Message } from '../../../common/models/message';
import { AjaxService } from '../../../common/services';
import { TextDateTH } from '../../../common/helper';

declare var jQuery: any;
declare var $: any;

@Component({
    selector: 'select-form',
    templateUrl: 'select-form.component.html',
    styleUrls: ['select-form.component.css']
})
export class SelectFormComponent implements OnInit {
  ngOnInit(): void {
    throw new Error("Method not implemented.");
  }
    
}
