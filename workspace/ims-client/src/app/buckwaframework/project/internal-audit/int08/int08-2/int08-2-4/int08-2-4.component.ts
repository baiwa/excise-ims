import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location, NgIf } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";

declare var $: any;
declare var $: any;

@Component({
  selector: 'app-int08-2-4',
  templateUrl: './int08-2-4.component.html',
  styleUrls: ['./int08-2-4.component.css']
})
export class Int0824Component implements OnInit {
  showData: boolean = false;

  riskAssRiskInfHdr: any;
  
  id: any;
  riskInfPaperName: any;
  budgetYear: any;
  userCheck: any;

  riskAssInfOtherName: any = '';
  riskCost: any = '';

  datatable: any;

  //Data: Data;
  riskOtherDataList: RiskOtherData[] = [];
  dataTableList: RiskOtherData[] = [];

  riskInfHrdData: RiskInfHrdData;

  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    $('.menu .item').tab();
    
    this.riskInfHrdData = new RiskInfHrdData();
    this.id = this.route.snapshot.queryParams["id"];
    this.findRiskById();
  }
  ngAfterViewInit() {
   
  }

  findRiskById() {
    let url = "ia/int08/findRiskById"
    this.ajax.post(url, { riskAssInfHdrId: this.id }, res => {

      this.riskAssRiskInfHdr = res.json();
      console.log(this.riskAssRiskInfHdr);
      this.riskInfPaperName = this.riskAssRiskInfHdr.riskInfPaperName;
      this.budgetYear = this.riskAssRiskInfHdr.budgetYear;
      this.userCheck = this.riskAssRiskInfHdr.userCheck;
      this.riskInfHrdData.riskAssInfHdrId = this.riskAssRiskInfHdr.riskAssInfHdrId;
      this.riskInfHrdData.riskInfPaperName = this.riskInfPaperName;
      this.riskInfHrdData.userCheck = this.userCheck;
      this.riskInfHrdData.budgetYear = this.budgetYear;
      
      url = "ia/int08/findRiskOtherDtlByRiskHrdId";
      this.ajax.post(url, { riskAssInfOtherId: this.id }, res => {
        // this.riskDataList
        var jsonObjList = res.json();
        console.log("สำเร็จ", jsonObjList);
        for (let index = 0; index < jsonObjList.length; index++) {
          var element = jsonObjList[index];
          var riskOtherData = new RiskOtherData();
          riskOtherData.riskAssInfOtherId = element.riskOtherDtlId;
          riskOtherData.riskInfHrdId = element.riskHrdId;
          riskOtherData.riskAssInfOtherName = element.projectBase;
          riskOtherData.riskCost = element.riskCost;
          riskOtherData.isDeleted = 'N';
          this.riskOtherDataList.push(riskOtherData);

        }
        this.initDatatable();
      }, errRes => {
        console.log("เกิดข้อผิดพลาด", errRes);
      });
    });
  }

  
  initDatatable(): void {

    if (this.datatable != null || this.datatable != undefined) {
      this.datatable.destroy();
    }
    this.dataTableList = [];
    this.riskOtherDataList.forEach(element => {
      if (element.isDeleted == 'N') {
        this.dataTableList.push(element);
        console.log(element);
      }
    });

    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: false,
      data: this.dataTableList,
      columns: [
        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "riskAssInfOtherName" },
        { data: "riskCost", className: "right" },
        { data: "rl", className: "center" },
        { data: "valueTranslation", className: "center" },
        {
          data: "riskAssInfHdrId",
          render: function () {
            return '<button type="button" class="ui mini button del"><i class="pencil icon"></i> ลบ </button>';
          }
        }
      ],
      rowCallback: (row, data, index) => {

        $("td > .del", row).bind("click", () => {
          console.log(index);
          if (this.dataTableList[index].riskAssInfOtherId != null && this.dataTableList[index].riskAssInfOtherId != undefined && this.dataTableList[index].riskAssInfOtherId != '') {
            for (let i = 0; i < this.riskOtherDataList.length; i++) {
              const element = this.riskOtherDataList[i];
              if (element.riskAssInfOtherId != null && element.riskAssInfOtherId != undefined && element.riskAssInfOtherId != '') {
                if (element.riskAssInfOtherId == this.dataTableList[index].riskAssInfOtherId && element.riskAssInfOtherName == this.dataTableList[index].riskAssInfOtherName) {
                  this.riskOtherDataList[i].isDeleted = 'Y';
                }

              }
            }
          } else {
            for (let i = 0; i < this.riskOtherDataList.length; i++) {
              const element = this.riskOtherDataList[i];
              if (element.riskAssInfOtherName == this.dataTableList[index].riskAssInfOtherName) {
                this.riskOtherDataList.splice(i, 1);
              }
            }
          }
          this.initDatatable();
        });
      }

    });
  }

  addObjRiskDtl() {
    var msgMessage = '';
    if (this.riskAssInfOtherName == null || this.riskAssInfOtherName == undefined || this.riskAssInfOtherName == '') {
      this.messageBarService.errorModal("กรุณากรอก \"ชื่อโครงการ\" ");
      return;
    }
    if (this.riskCost == null || this.riskCost == undefined || this.riskCost == '') {
      this.messageBarService.errorModal("กรุณากรอก \"ค่าความเสี่ยง\" ");
      return;
    }

    let riskOtherData = new RiskOtherData();
    riskOtherData.riskAssInfOtherName = this.riskAssInfOtherName;
    riskOtherData.riskCost = this.riskCost;
    riskOtherData.isDeleted = 'N';
    riskOtherData.riskInfHrdId = this.riskAssRiskInfHdr.riskInfHrdId;

    this.riskOtherDataList.push(riskOtherData);
    console.log("riskOtherDataList", this.riskOtherDataList);
    this.initDatatable();
    this.riskAssInfOtherName = '';
    this.riskCost = '';
  }

  saveRiskAssRiskWsDtl(): void {
    //console.log("saveRiskAssRiskWsDtl", this.riskHrdPaperName);
    var msgMessage = "";
    var countInfOther = 0;
    this.riskOtherDataList.forEach(element => {
      if (element.isDeleted == 'N') {
        countInfOther++;
      }
    });
    if (countInfOther == 0) {
      this.messageBarService.errorModal("กรุณาเพิ่มความ ระบบสารสนเทศ อย่างน้อยหนึ่งโครงการ");
      return;
    }
    this.riskAssRiskInfHdr.riskInfPaperName = this.riskInfPaperName;
    this.riskAssRiskInfHdr.userCheck = this.userCheck;
    this.riskAssRiskInfHdr.budgetYear = this.budgetYear;
    //console.log(this.riskHrdData)


    if (this.userCheck == null || this.userCheck == undefined || this.userCheck == "") {
      msgMessage = "กรุณากรอก \"ผู้ตรวจ\" ";
    }

    if (this.riskInfPaperName == null || this.riskInfPaperName == undefined || this.riskInfPaperName == "") {
      msgMessage = "กรุณากรอก \"ชื่อกระดาษทำการ\" ";
    }

    if (msgMessage == "") {
      var url = "ia/int08/saveRiskAssOther";
      var urlDtl = "ia/int08/saveRiskAssDtlOther";
      this.ajax.post(url, this.riskAssRiskInfHdr, res => {
        var message = res.json();
        //console.log(message.messageType);
        if (message.messageType == 'E') {
          this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
        } else {

          for (let index = 0; index < this.riskOtherDataList.length; index++) {
            var dtl = this.riskOtherDataList[index];
            this.ajax.post(urlDtl, { riskAssInfOtherId: dtl.riskAssInfOtherId, riskInfHrdId: dtl.riskInfHrdId, riskAssInfOtherName: dtl.riskAssInfOtherName, isDeleted: dtl.isDeleted, riskCost: dtl.riskCost }, res => {
              var message = res.json();
              console.log(message);
            }, errRes => {
              var message = errRes.json();
              console.log(message);
            });

          }
          this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
          this.router.navigate(["/int08/1/4"], {
            queryParams: { budgetYear: this.budgetYear }
          });

        }

      }, errRes => {
        var message = errRes.json();
        //console.log(message);
        this.messageBarService.errorModal(message.messageTh);

      });
    } else {
      this.messageBarService.errorModal(msgMessage);
    }

  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

  popupEditData() {
   
  }

  closePopupEdit() {
   
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


class RiskOtherData {
  riskAssInfOtherId: any = 0;
  riskInfHrdId: any = 0;
  riskAssInfOtherName: any = '';
  riskCost: any = '';
  rl: any = '';
  valueTranslation: any = '';
  isDeleted: any = '';
}

class RiskInfHrdData {
  riskAssInfHdrId: any = '';
  riskInfPaperName: any = '';
  budgetYear: any = '';
  userCheck: any = '';
}