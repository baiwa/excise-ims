import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location, NgIf } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";
import { log } from "util";

declare var $: any;
declare var $: any;

@Component({
  selector: 'app-int08-2-5',
  templateUrl: './int08-2-5.component.html',
  styleUrls: ['./int08-2-5.component.css']
})
export class Int0825Component implements OnInit {
  
  budgetYear: any;
  
  
  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute
  ) { 
    
  }

  ngOnInit() {
    this.budgetYear = this.route.snapshot.queryParams["budgetYear"];

  }
  ngAfterViewInit() {
  }

  cancelFlow() {
    this.messageBarService.comfirm(foo => {
      // let msg = "";
      if (foo) {
        this.router.navigate(["/int08/2/2"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }
    }, "คุณต้องการยกเลิกการทำงานใช่หรือไม่ ? ");
  }

}

