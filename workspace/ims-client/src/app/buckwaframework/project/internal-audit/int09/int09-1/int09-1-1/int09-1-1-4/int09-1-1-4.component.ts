import { Component, OnInit, AfterViewInit } from "@angular/core";
import {
  TravelCostHeader,
  TravelCostDetail,
  Contract
} from "../../../../../../common/models";
import { AjaxService, MessageBarService } from "../../../../../../common/services";
import { Prices } from "../../../../../../common/helper/travel";
import { Router, ActivatedRoute } from "@angular/router";
import {
  digit,
  numberWithCommas,
  TextDateTH,
  formatter,
  DecimalFormat
} from "../../../../../../common/helper";
import { TravelService } from "../../../../../../common/services/travel.service";
declare var $: any;
@Component({
  selector: "app-int09-1-1-4",
  templateUrl: "./int09-1-1-4.component.html",
  styleUrls: ["./int09-1-1-4.component.css"]
})
export class Int09114Component implements OnInit, AfterViewInit {

  idProcess:any;
  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private travelService: TravelService
  ) {}
  calenda = function () {
    $("#date").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#date2").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

  clickBack(){
    this.router.navigate(['/int09/1/1'], {
      queryParams: {idProcess:this.idProcess}
    });
  }


  ngOnInit() {
    this.idProcess = this.route.snapshot.queryParams["idProcess"];
    console.log("idProcess : ",this.idProcess);
    this.calenda();
    // this.hideModal();
  }
  save(){	

    $('#modalAddHead').modal('hide');
    const URL = "ia/int09114/save";
    this.ajax.post(URL, { 
      idProcess:this.idProcess
    }, res => {
      const msg = res.json();
      
    if (msg.messageType == "C") {
      this.msg.successModal(msg.messageTh);
      this.clickBack();
    } else {
      this.msg.errorModal(msg.messageTh);
    }
    });
  }

  ngAfterViewInit() {

  }

 
  
}
