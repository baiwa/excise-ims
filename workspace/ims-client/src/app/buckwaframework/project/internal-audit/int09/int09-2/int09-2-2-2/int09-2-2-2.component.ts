import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IaService, MessageBarService } from 'app/buckwaframework/common/services';
import { AjaxService } from 'app/buckwaframework/common/services';

declare var jQuery: any;
declare var $: any;


@Component({
  selector: 'app-int09-2-2-2',
  templateUrl: './int09-2-2-2.component.html'
})
export class Int09222Component implements OnInit {

  constructor(private router: Router,
  private message:MessageBarService,
  private messageBarService: MessageBarService,
  private ajax: AjaxService) {}
 
  calenda = function () {
    $("#fiscalYear").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

  clickSave = () => {

    if($("#stateAgencyName").val()==""){
      this.messageBarService.alert("ชื่อส่วนราชการ","กรุณาระบุ");
      return false;
    }
    if($("#thosePicked").val()==""){
      this.messageBarService.alert("ผู้เบิก","กรุณาระบุ");
      return false;
    }
    if($("#fiscalYearIn").val()==""){
      this.messageBarService.alert("วันที่","กรุณาระบุ");
      return false;
    }
    const URL = "ia/int09222/save";
    let data = {"createdDate" : new Date(),
                "createdBy" : "Game",
                "stateAgencyName" :$("#stateAgencyName").val(),
                "thosePicked" : $("#thosePicked").val(),
                "fiscalYear" : $("#fiscalYearIn").val()}
    this.message.comfirm((res) => {
    if(res){
      this.ajax.post(URL,data, res => {
        const dataJ = res.json();
        if (dataJ.errorMessage.messageType == "C") {
          this.message.successModal(dataJ.errorMessage.messageTh); 
          this.router.navigate(['/int09/2-2-3'],{
            queryParams: {
              idProcess:dataJ.idProcess,
              stateAgencyName :$("#stateAgencyName").val(),
              thosePicked : $("#thosePicked").val(),
              fiscalYear : $("#fiscalYearIn").val()}
            });
        } else {
          this.message.errorModal(dataJ.errorMessage.messageTh);
        }
      });
     }
    },"บันทึก"); 
  }

  clickback = () => {
    this.router.navigate(['/int09/2-2-1']);
  }

  ngOnInit() {
  }

  ngAfterViewInit(){
    this.calenda();
  }

}
