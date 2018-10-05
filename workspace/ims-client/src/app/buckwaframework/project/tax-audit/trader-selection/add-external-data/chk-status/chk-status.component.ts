import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { ExciseService } from "../../../../../common/services/excise.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-chk-status',
  templateUrl: './chk-status.component.html',
  styleUrls: ['./chk-status.component.css']
})
export class ChkStatusComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
    private ex: ExciseService,
    private router: Router,
    private ajax: AjaxService
  ) {

  }

  ngOnInit() {


  }

}
