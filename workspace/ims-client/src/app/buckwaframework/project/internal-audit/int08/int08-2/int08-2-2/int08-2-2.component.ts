import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int08-2-2',
  templateUrl: './int08-2-2.component.html',
  styleUrls: ['./int08-2-2.component.css']
})
export class Int0822Component implements OnInit {
  
  riskHdrName: any;
  datatable: any;

  
  constructor() { }

  ngOnInit() {
  }

}
