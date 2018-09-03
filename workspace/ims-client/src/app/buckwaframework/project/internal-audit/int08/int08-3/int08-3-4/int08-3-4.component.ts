import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";



declare var jQuery: any;
declare var $: any;
@Component({
  selector: "int08-3-4",
  templateUrl: "./int08-3-4.component.html",
  styleUrls: ["./int08-3-4.component.css"]
})
export class Int0834Component implements OnInit {
  riskAssRiskWsHdr: any;
  id: any;
  riskHrdPaperName: any;
  budgetYear: any;
  userCheck: any;
  exciseSetor: any = '';
  exciseArea: any = '';
  exciseBranch: any = '';
  riskCost: any = '';
  datatable: any;
  riskData: RiskData;
  riskDataList: RiskData[] = [];
  dataTableList: RiskData[] = [];
  riskHrdData: RiskHrdData;
  isConditionShow: any;

  fileExel: File[];


  constructor(private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private messageBarService: MessageBarService
  ) {
    this.fileExel = new Array<File>(); // initial file array
  }
  uu
  ngOnInit() {
    //$(".ui.dropdown").dropdown();
    $('.menu .item').tab()
    this.riskHrdData = new RiskHrdData();
    this.id = this.route.snapshot.queryParams["id"];
    this.findRiskById();

    this.isConditionShow = false;
    // $("#ConditionRL").modal("show");
    //$("#ConditionRL").modal("hide");

  }

  ngAfterViewInit() {


  }

  getConditionShow() {
    return this.isConditionShow;
  }



  findRiskById() {
    let url = "ia/int083/findRiskById"
    this.ajax.post(url, { riskHrdId: this.id }, res => {

      this.riskAssRiskWsHdr = res.json();
      console.log(this.riskAssRiskWsHdr);
      this.riskHrdPaperName = this.riskAssRiskWsHdr.riskHrdPaperName;
      this.budgetYear = this.riskAssRiskWsHdr.budgetYear;
      this.userCheck = this.riskAssRiskWsHdr.userCheck;
      this.riskHrdData.riskHrdId = this.riskAssRiskWsHdr.riskHrdId;
      this.riskHrdData.riskHrdPaperName = this.riskHrdPaperName;
      this.riskHrdData.userCheck = this.userCheck;
      this.riskHrdData.budgetYear = this.budgetYear;
      url = "ia/int083/findRiskOtherDtlByRiskHrdId";
      this.ajax.post(url, { riskHrdId: this.id }, res => {
        // this.riskDataList
        var jsonObjList = res.json();
        for (let index = 0; index < jsonObjList.length; index++) {
          var element = jsonObjList[index];
          var riskData = new RiskData();
          riskData.riskOtherDtlId = element.riskOtherDtlId;
          riskData.riskHrdId = element.riskHrdId;
          riskData.exciseArea = element.exciseArea;
          riskData.exciseBranch = element.exciseBranch;
          riskData.exciseSetor = element.exciseSetor;
          riskData.riskCost = element.riskCost;
          riskData.isDeleted = 'N';
          this.riskDataList.push(riskData);

        }
        this.initDatatable();
      }, errRes => {
        console.log("66666", errRes);
      });
    });
  }

  onUpload = (event: any) => {
    event.preventDefault();


    const form = $("#upload-form")[0];
    let formBody = new FormData(form);

    let url = "ia/int083/excelINT083";
    this.ajax.upload(
      url,
      formBody,
      res => {
        console.log(res.json());

        res.json().forEach(element => {
          element.isDeleted = 'N';

          element.riskHrdId = this.id;
          this.riskDataList.push(element);

        });
        this.initDatatable();

      }
    );
  };

  initDatatable(): void {

    if (this.datatable != null || this.datatable != undefined) {
      this.datatable.destroy();
    }
    this.dataTableList = [];
    this.riskDataList.forEach(element => {
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
        { data: "หน่วยงาน" },
        { data: "riskCost", className: "right" },
        { data: "rl", className: "center" },
        { data: "valueTranslation", className: "center" },
        {
          data: "riskHdrId",
          render: function () {
            return '<button type="button" class="ui mini button del"><i class="pencil icon"></i> ลบ </button>';
          }
        }
      ],
      rowCallback: (row, data, index) => {

        $("td > .del", row).bind("click", () => {
          console.log(index);
          if (this.dataTableList[index].riskOtherDtlId != null && this.dataTableList[index].riskOtherDtlId != undefined && this.dataTableList[index].riskOtherDtlId != '') {
            for (let i = 0; i < this.riskDataList.length; i++) {
              const element = this.riskDataList[i];
              if (element.riskOtherDtlId != null && element.riskOtherDtlId != undefined && element.riskOtherDtlId != '') {
                if (element.riskOtherDtlId == this.dataTableList[index].riskOtherDtlId && element.exciseArea == this.dataTableList[index].exciseArea && element.exciseBranch == this.dataTableList[index].exciseBranch && element.exciseSetor == this.dataTableList[index].exciseSetor) {
                  this.riskDataList[i].isDeleted = 'Y';
                }

              }
            }
          } else {
            for (let i = 0; i < this.riskDataList.length; i++) {
              const element = this.riskDataList[i];
              if (element.exciseArea == this.dataTableList[index].exciseArea && element.exciseBranch == this.dataTableList[index].exciseBranch && element.exciseSetor == this.dataTableList[index].exciseSetor) {
                this.riskDataList.splice(i, 1);
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
    if (this.exciseSetor == null || this.exciseSetor == undefined || this.exciseSetor == '') {
      this.messageBarService.errorModal("กรุณากรอก \"สรรพสามิตภาค\" ");
      return;
    }
    if (this.exciseArea == null || this.exciseArea == undefined || this.exciseArea == '') {
      this.messageBarService.errorModal("กรุณากรอก \" สรรพสามิตพื้นที่\" ");
      return;
    }
    if (this.exciseBranch == null || this.exciseBranch == undefined || this.exciseBranch == '') {
      this.messageBarService.errorModal("กรุณากรอก \"สรรพสามิตสาขา\" ");
      return;
    }
    if (this.riskCost == null || this.riskCost == undefined || this.riskCost == '') {
      this.messageBarService.errorModal("กรุณากรอก \"ค่าความเสี่ยง\" ");
      return;
    }
    let riskData = new RiskData();
    riskData.exciseArea = this.exciseArea;
    riskData.exciseBranch = this.exciseBranch;
    riskData.exciseSetor = this.exciseSetor;
    riskData.riskCost = this.riskCost;
    riskData.isDeleted = 'N';
    riskData.riskHrdId = this.riskAssRiskWsHdr.riskHrdId;

    this.riskDataList.push(riskData);
    console.log("riskDataList", this.riskDataList);
    this.initDatatable();
    this.exciseArea = '';
    this.exciseBranch = '';
    this.exciseSetor = '';
    this.riskCost = '';
  }

  saveRiskAssRiskWsDtl(): void {
    //console.log("saveRiskAssRiskWsDtl", this.riskHrdPaperName);
    var msgMessage = "";
    var countProject = 0;
    this.riskDataList.forEach(element => {
      if (element.isDeleted == 'N') {
        countProject++;
      }
    });
    if (countProject == 0) {
      this.messageBarService.errorModal("กรุณาเพิ่มความ โครงการตามยุทธศาสตร์ อย่างน้อยหนึ่งโครงการ");
      return;
    }
    this.riskAssRiskWsHdr.riskHrdPaperName = this.riskHrdPaperName;
    this.riskAssRiskWsHdr.userCheck = this.userCheck;
    this.riskAssRiskWsHdr.budgetYear = this.budgetYear;
    //console.log(this.riskHrdData)


    if (this.userCheck == null || this.userCheck == undefined || this.userCheck == "") {
      msgMessage = "กรุณากรอก \"ผู้ตรวจ\" ";
    }

    if (this.riskHrdPaperName == null || this.riskHrdPaperName == undefined || this.riskHrdPaperName == "") {
      msgMessage = "กรุณากรอก \"ชื่อกระดาษทำการ\" ";
    }

    if (msgMessage == "") {
      var url = "ia/int08/saveRiskAssOther";
      var urlDtl = "ia/int08/saveRiskAssDtlOther";
      this.riskAssRiskWsHdr.riskType = 'OTHER';
      this.ajax.post(url, this.riskAssRiskWsHdr, res => {
        var message = res.json();
        //console.log(message.messageType);
        if (message.messageType == 'E') {
          this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
        } else {


          this.ajax.post(urlDtl, { riskAssOtherDtlList: this.riskDataList }, res => {
            var message = res.json();
            console.log(message);
          }, errRes => {
            var message = errRes.json();
            console.log(message);
          });


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
  onChangeUpload = (event: any) => {
    if (event.target.files && event.target.files.length > 0) {
      let reader = new FileReader();

      reader.onload = (e: any) => {
        const f = {
          name: event.target.files[0].name,
          type: event.target.files[0].type,
          value: e.target.result
        };
        this.fileExel = [f];
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  };
  cancelFlow() {
    this.messageBarService.comfirm(foo => {
      // let msg = "";
      if (foo) {
        this.router.navigate(["/int08/1/4"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }
    }, "คุณต้องการยกเลิกการทำงานใช่หรือไม่ ? ");
  }
  modalConditionRL() {
    this.isConditionShow = true;
  }

  closeConditionRL(e) {
    this.isConditionShow = false;
  }

}

class RiskData {
  exciseArea: any = '';
  exciseSetor: any = '';
  riskHrdId: any = 0;
  exciseBranch: any = '';
  riskCost: any = '';
  rl: any = '';
  valueTranslation: any = '';
  riskOtherDtlId: any = 0;
  isDeleted: any = '';
}

class RiskHrdData {
  riskHrdId: any = '';
  riskHrdPaperName: any = '';
  budgetYear: any = '';
  userCheck: any = '';

}


class File {
  [x: string]: any;
  name: string;
  type: string;
  value: any;
}

class Data {
  companyName: any = "";
  startDate: any = "";
  endDate: any = "";
  analysNumber: any = "";
  startDateSplit: any = "";
  endDateSplit: any = "";
}