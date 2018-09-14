import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { forEach } from "@angular/router/src/utils/collection";
import { TextDateTH, digit } from "../../../../../common/helper/datepicker";
import { DecimalFormat } from "../../../../../common/helper";


declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-int08-1-6',
  templateUrl: './int08-1-6.component.html',
  styleUrls: ['./int08-1-6.component.css']
})
export class Int0816Component implements OnInit {
  riskAssRiskWsHdr: any;
  id: any;
  riskHrdPaperName: any;
  budgetYear: any;
  userCheck: any;
  projectBase: any = '';
  departmentName: any = '';
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
    let url = "ia/int08/findRiskById"
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

      url = "ia/int08/findRiskOtherDtlByRiskHrdId";
      this.ajax.post(url, { riskHrdId: this.id }, res => {
        // this.riskDataList
        var jsonObjList = res.json();
        for (let index = 0; index < jsonObjList.length; index++) {
          var element = jsonObjList[index];
          var riskData = new RiskData();
          riskData.riskOtherDtlId = element.riskOtherDtlId;
          riskData.riskHrdId = element.riskHrdId;
          riskData.departmentName = element.departmentName;
          riskData.projectBase = element.projectBase;
          riskData.riskCost = element.riskCost;
          riskData.rl=element.rl;
          riskData.valueTranslation=element.valueTranslation;
          riskData.color=element.color;
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

    let url = "ia/int08/excelINT081";
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
      paging: true,
      data: this.dataTableList,
      columns: [
        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "projectBase" },
        { data: "departmentName"},
        { data: "riskCost"},
        { data: "rl" },
        { data: "valueTranslation" },
        {
          data: "riskHdrId",
          render: function () {
            return '<button type="button" class="ui mini button del"><i class="trash alternate icon"></i> ลบ </button>';
          }
        }
      ],
      columnDefs: [
        { targets: [0,2,4,5,6], className: "center aligned" },
        { targets: [3], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ],
      createdRow: function (row, data, dataIndex) {
        console.log("row");
        console.log("data", data.color);
        console.log("dataIndex", dataIndex);
        if (data.color == 'แดง') {
          $(row).find('td:eq(4)').addClass('bg-c-red');
          $(row).find('td:eq(5)').addClass('bg-c-red');
        } else if (data.color == 'เขียว') {
          $(row).find('td:eq(4)').addClass('bg-c-green');
          $(row).find('td:eq(5)').addClass('bg-c-green');
        } else if (data.color == 'เหลือง') {
          $(row).find('td:eq(4)').addClass('bg-c-yellow');
          $(row).find('td:eq(5)').addClass('bg-c-yellow');
        }

      },
      rowCallback: (row, data, index) => {

        $("td > .del", row).bind("click", () => {
          console.log(index);
          if (this.dataTableList[index].riskOtherDtlId != null && this.dataTableList[index].riskOtherDtlId != undefined && this.dataTableList[index].riskOtherDtlId != '') {
            for (let i = 0; i < this.riskDataList.length; i++) {
              const element = this.riskDataList[i];
              if (element.riskOtherDtlId != null && element.riskOtherDtlId != undefined && element.riskOtherDtlId != '') {
                if (element.riskOtherDtlId == this.dataTableList[index].riskOtherDtlId && element.projectBase == this.dataTableList[index].projectBase && element.departmentName == this.dataTableList[index].departmentName) {
                  this.riskDataList[i].isDeleted = 'Y';
                }

              }
            }
          } else {
            for (let i = 0; i < this.riskDataList.length; i++) {
              const element = this.riskDataList[i];
              if (element.projectBase == this.dataTableList[index].projectBase && element.departmentName == this.dataTableList[index].departmentName) {
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
    if (this.projectBase == null || this.projectBase == undefined || this.projectBase == '') {
      this.messageBarService.errorModal("กรุณากรอก \"ชื่อโครงการ\" ");
      return;
    }
    if (this.departmentName == null || this.departmentName == undefined || this.departmentName == '') {
      this.messageBarService.errorModal("กรุณากรอก \"ชื่อหน่วยงาน\" ");
      return;
    }
    if (this.riskCost == null || this.riskCost == undefined || this.riskCost == '') {
      this.messageBarService.errorModal("กรุณากรอก \"ค่าความเสี่ยง\" ");
      return;
    }
    let riskData = new RiskData();
    riskData.projectBase = this.projectBase;
    riskData.departmentName = this.departmentName;
    riskData.riskCost = this.riskCost;
    riskData.isDeleted = 'N';
    riskData.riskHrdId = this.riskAssRiskWsHdr.riskHrdId;

    this.riskDataList.push(riskData);
    console.log("riskDataList", this.riskDataList);
    this.initDatatable();
    this.projectBase = '';
    this.departmentName = '';
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

  ExportOtherDtl() {
    const URL = "ia/int08/exportWsOtherDtl?riskHrdId=" + this.id;
    console.log("id", this.id);
    this.ajax.download(URL);
 
  }

}

class RiskData {
  projectBase: any = '';
  riskHrdId: any = 0;
  departmentName: any = '';
  riskCost: any = '';
  rl: any = '';
  valueTranslation: any = '';
  color: any = '';
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

