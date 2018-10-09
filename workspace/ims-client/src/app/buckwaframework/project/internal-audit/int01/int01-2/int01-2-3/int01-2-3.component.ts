import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
declare var $: any;
@Component({
  selector: 'int01-2-3',
  templateUrl: './int01-2-3.component.html',
  styleUrls: ['./int01-2-3.component.css']
})
export class Int0123Component implements OnInit {
  datatable: any;
  offCode: any;
  startDate: any;
  endDate: any;
  dataTableList: any[];

  constructor(private router: Router,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService) {

  }

  ngOnInit() {
    this.offCode = this.route.snapshot.queryParams["offCode"];
    this.startDate = this.route.snapshot.queryParams["startDate"];
    this.endDate = this.route.snapshot.queryParams["endDate"];
    this.ajax.post("ia/int012/searchLicense", {
      offCode: this.offCode, startDate: this.startDate, endDate: this.endDate
    }, res => {
      this.dataTableList = res.json();
      console.log(this.dataTableList);

    });

  }

}
