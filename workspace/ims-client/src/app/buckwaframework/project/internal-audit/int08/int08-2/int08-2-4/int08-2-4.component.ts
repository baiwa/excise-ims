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
  selector: 'app-int08-2-4',
  templateUrl: './int08-2-4.component.html',
  styleUrls: ['./int08-2-4.component.css']
})
export class Int0824Component implements OnInit {
  
  riskAssRiskInfHdr: any;
  
  id: any;
  riskInfPaperName: any;
  budgetYear: any;
  userCheck: any;

  infName: any = '';
  riskCost: any = '';

  datatable: any;


  //Data
  riskOtherData: RiskOtherData;
  riskOtherDataList: RiskOtherData[] = [];
  dataTableList: RiskOtherData[] = [];

  riskInfHrdData: RiskInfHrdData;

  fileExel: File[];

  isConditionShow: any;

  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute
  ) {
    this.fileExel = new Array<File>(); // initial file array
   }

  ngOnInit() {
    $('.menu .item').tab();
    
    this.riskInfHrdData = new RiskInfHrdData();
    this.id = this.route.snapshot.queryParams["id"];
    this.findRiskById();

    this.isConditionShow = false;
    
  }
  ngAfterViewInit() {
   
  }

  findRiskById() {
    let url = "ia/int082/findRiskById"
    this.ajax.post(url, { riskAssInfHdrId: this.id }, res => {
      
      this.riskAssRiskInfHdr = res.json();
      console.log("ดาต้านะจ๊ะ",this.riskAssRiskInfHdr);
      this.riskInfPaperName = this.riskAssRiskInfHdr.riskInfPaperName;
      this.budgetYear = this.riskAssRiskInfHdr.budgetYear;
      this.userCheck = this.riskAssRiskInfHdr.userCheck;
  
      this.riskInfHrdData.riskAssInfHdrId = this.riskAssRiskInfHdr.riskAssInfHdrId;
      this.riskInfHrdData.riskInfPaperName = this.riskInfPaperName;
      this.riskInfHrdData.userCheck = this.userCheck;
      this.riskInfHrdData.budgetYear = this.budgetYear;
      
      url = "ia/int082/findRiskAssInfOtherDtlByRiskHrdId";
      this.ajax.post(url, { riskInfHrdId: this.id }, res => {
        // this.riskDataList
        var jsonObjList = res.json();
        console.log("สำเร็จ888", jsonObjList);
        for (let index = 0; index < jsonObjList.length; index++) {
          var element = jsonObjList[index];
          var riskOtherData = new RiskOtherData();
          riskOtherData.riskAssInfOtherId = element.riskAssInfOtherId;
          riskOtherData.riskInfHrdId = element.riskInfHrdId;
          riskOtherData.infName = element.infName;
          riskOtherData.riskCost = element.riskCost;
          riskOtherData.rl = element.rl;
          riskOtherData.valueTranslation = element.valueTranslation;
          riskOtherData.color = element.color;
          riskOtherData.isDeleted = 'N';
          this.riskOtherDataList.push(riskOtherData);
        
        }
        this.initDatatable();
      }, errRes => {
        console.log("เกิดข้อผิดพลาด", errRes);
      });
    });
  }

  onUpload = (event: any) => {
    event.preventDefault();

    const form = $("#upload-form")[0];
    let formBody = new FormData(form);

    let url = "ia/int082/excelINT082";
    this.ajax.upload(
      url,
      formBody,
      res => {
        console.log(res.json());

        res.json().forEach(element => {
          
          element.isDeleted = 'N';

          element.riskInfHrdId = this.id;
          this.riskOtherDataList.push(element);
  
        });
        this.initDatatable();

      }
    );
  };

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
      scrollY:true,
      scrollX: true,
      scrollCollapse: true,
      paging: true,
      data: this.dataTableList,
      columns: [
        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "infName" },
        { data: "riskCost" },
        { data: "rl" },
        { data: "valueTranslation"},
        {
          data: "riskAssInfHdrId",
          render: function () {
            return '<button type="button" class="ui mini button  del"><i class="trash alternate icon"></i> ลบ </button>';
          }
        }
      ],
      columnDefs: [
        { targets: [0,3,4,5], className: "center aligned" },
        { targets: [2], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ],

      createdRow: function (row, data, dataIndex) {
        console.log("row");
        console.log("data", data.color);
        console.log("dataIndex", dataIndex);
        if (data.color == 'แดง') {
          $(row).find('td:eq(3)').addClass('red');
          $(row).find('td:eq(4)').addClass('red');
        } else if (data.color == 'เขียว') {
          $(row).find('td:eq(3)').addClass('green');
          $(row).find('td:eq(4)').addClass('green');
        } else if (data.color == 'เหลือง') {
          $(row).find('td:eq(3)').addClass('yellow');
          $(row).find('td:eq(4)').addClass('yellow');
        }

      },
      rowCallback: (row, data, index) => {

        $("td > .del", row).bind("click", () => {
          console.log(index);
          if (this.dataTableList[index].riskAssInfOtherId != null && this.dataTableList[index].riskAssInfOtherId != undefined && this.dataTableList[index].riskAssInfOtherId != '') {
            for (let i = 0; i < this.riskOtherDataList.length; i++) {
              const element = this.riskOtherDataList[i];
              if (element.riskAssInfOtherId != null && element.riskAssInfOtherId != undefined && element.riskAssInfOtherId != '') {
                if (element.riskAssInfOtherId == this.dataTableList[index].riskAssInfOtherId && element.infName == this.dataTableList[index].infName) {
                  this.riskOtherDataList[i].isDeleted = 'Y';
                }

              }
            }
          } else {
            for (let i = 0; i < this.riskOtherDataList.length; i++) {
              const element = this.riskOtherDataList[i];
              if (element.infName == this.dataTableList[index].infName) {
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
    if (this.infName == null || this.infName == undefined || this.infName == '') {
      this.messageBarService.errorModal("กรุณากรอก \"ชื่อโครงการ\" ");
      return;
    }
    if (this.riskCost == null || this.riskCost == undefined || this.riskCost == '') {
      this.messageBarService.errorModal("กรุณากรอก \"ค่าความเสี่ยง\" ");
      return;
    }

    let riskOtherData = new RiskOtherData();
    riskOtherData.infName = this.infName;
    riskOtherData.riskCost = this.riskCost;
    riskOtherData.isDeleted = 'N';
    riskOtherData.riskInfHrdId = this.riskAssRiskInfHdr.riskAssInfHdrId;

    this.riskOtherDataList.push(riskOtherData);
    console.log("riskOtherDataList ลงตาราง แต่ยังไม่ลง table", this.riskOtherDataList);
    this.initDatatable();
    this.infName = '';
    this.riskCost = '';
  }

  saveRiskAssInfOtherDtl(): void {
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
      var url = "ia/int082/saveRiskInfPaperName";
      var urlDtl = "ia/int082/saveRiskAssInfOther";
      this.riskAssRiskInfHdr.riskType = 'OTHER';
      
      this.ajax.post(url, this.riskAssRiskInfHdr, res => {
        var message = res.json();
        //console.log(message.messageType);
        if (message.messageType == 'E') {
          this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
        } else {

          this.ajax.post(urlDtl, { riskAssInfOtherDtlList: this.riskOtherDataList }, res => {
            var message = res.json();
            console.log(message);
          }, errRes => {
            var message = errRes.json();
            console.log(message);
          });

          
          this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
          this.router.navigate(["/int08/2/2"], {
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


  getConditionShow() {
    return this.isConditionShow;
  }

  modalConditionRL() {
    this.isConditionShow = true;
  }

  closeConditionRL() {
    this.isConditionShow = false;
  }

  ExportOtherDtl() {
    const URL = "ia/int082/exportInfOtherDtl?riskAssInfHdrId=" + this.id;
    console.log("id", this.id);
    this.ajax.download(URL);
 
  }

}


class RiskOtherData {
  riskAssInfOtherId: any = 0;
  riskInfHrdId: any = 0;
  infName: any = '';
  riskCost: any = '';
  rl: any = '';
  valueTranslation: any = '';
  color: any = '';
  isDeleted: any = '';

}

class RiskInfHrdData {
  riskAssInfHdrId: any = '';
  riskInfPaperName: any = '';
  budgetYear: any = '';
  userCheck: any = '';
}

class File {
  [x: string]: any;
  name: string;
  type: string;
  value: any;
}
