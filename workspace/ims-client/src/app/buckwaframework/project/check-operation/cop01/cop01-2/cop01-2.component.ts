import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { TextDateTH, digit, formatter } from "../../../../common/helper/datepicker";
import { AjaxService } from "../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../common/services/message-bar.service";
declare var $: any;
@Component({
  selector: 'app-cop01-2',
  templateUrl: './cop01-2.component.html',
  styleUrls: ['./cop01-2.component.css']
})
export class Cop012Component implements OnInit {
  year: any;
  formatter1: any;
  planStart:any;
  
  constructor(private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private messageBarService: MessageBarService
    
  ) {
    this.formatter1 = formatter('ดป');
   }

  ngOnInit() {

    $("#calendar").calendar({

      type: "month",
      text: TextDateTH,
      formatter: this.formatter1
    });
  }
  search() {
    this.planStart =  $("#planStart").val();
    console.log(this.planStart);
    this.router.navigate(["/cop01/3"], {
      queryParams: { planStart: this.planStart }
    });
  }
  

}
