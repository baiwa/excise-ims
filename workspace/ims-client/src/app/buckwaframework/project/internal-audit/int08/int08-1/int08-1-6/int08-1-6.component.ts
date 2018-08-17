import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";



declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-int08-1-6',
  templateUrl: './int08-1-6.component.html',
  styleUrls: ['./int08-1-6.component.css']
})
export class Int0816Component implements OnInit {

  constructor(private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private messageBarService: MessageBarService
  ) { }

  ngOnInit() {

  }

}
