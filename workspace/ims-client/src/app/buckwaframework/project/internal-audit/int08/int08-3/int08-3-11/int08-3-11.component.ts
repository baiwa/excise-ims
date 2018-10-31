import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { AuthService } from "services/auth.service";
import { RiskAssRiskWsHdr } from "models/RiskAssRiskWsHdr";
import { BreadCrumb } from "models/breadcrumb";



declare var jQuery: any;
declare var $: any;
const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
@Component({
  selector: 'app-int08-3-11',
  templateUrl: './int08-3-11.component.html',
  styleUrls: ['./int08-3-11.component.css']
})
export class Int08311Component implements OnInit {
  riskAssRiskWsHdr: RiskAssRiskWsHdr;
  id: any;

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
  breadcrumb: BreadCrumb[];

  fileExel: File[];
  exciseSetorList: any[] = [];
  exciseAreaList: any[] = [];
  exciseBranchList: any[] = [];
  departmentName: any;
  titleList: any[] = [];
  constructor(private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private ajax: AjaxService,
    private messageBarService: MessageBarService
  ) {
    this.fileExel = new Array<File>(); // initial file array
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "การประเมินความเสี่ยง", route: "#" },
      { label: "ประเมินความเสี่ยงสำนักงานสรรพสามิตภาคพื้นที่", route: "#" },
      { label: "รายละเอียดปัจจัยเสี่ยงการส่งเงินเกิน 3 วัน" , route: "#" },
    ];
  }

  ngOnInit() {
    this.riskAssRiskWsHdr = new RiskAssRiskWsHdr();
    this.riskAssRiskWsHdr.checkPosition = 'ผู้อำนวยการกลุ่มตรวจสอบภายใน';
    this.authService.reRenderVersionProgram('INT-08311');
    //$(".ui.dropdown").dropdown();
    $('.menu .item').tab();
    //console.log('ngOnInit');
    this.riskHrdData = new RiskHrdData();
    this.id = this.route.snapshot.queryParams["id"];
    this.findRiskById();
    this.isConditionShow = false;
    let url = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(url, { type: 'SECTOR_VALUE' }, res => {
      this.exciseSetorList = res.json();
      console.log("getDropByTypeAndParentId", this.exciseSetorList);
    });
    this.ajax.post(URL.DROPDOWN, { type: 'TITLE' }, res => {
      this.titleList = res.json();

    });

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
      //console.log(this.riskAssRiskWsHdr);
      this.riskAssRiskWsHdr.checkPosition = 'ผู้อำนวยการกลุ่มตรวจสอบภายใน';
      url = "ia/int083/findRiskOver3Day";
      this.ajax.post(url, { riskHrdId: this.id }, res => {
        // this.riskDataList
        var jsonObjList = res.json();
        for (let index = 0; index < jsonObjList.length; index++) {
          var element = jsonObjList[index];
          var riskData = new RiskData();
          riskData.riskOtherDtlId = element.riskOtherDtlId;
          riskData.riskHrdId = this.id;
          riskData.departmentName = element.departmentName;
          riskData.color = element.color;
          riskData.riskCost = element.riskCost;
          riskData.rl = element.rl;
          riskData.valueTranslation = element.valueTranslation;
          riskData.isDeleted = 'N';
          console.log(riskData);
          this.riskDataList.push(riskData);
        }
        this.initDatatable();
      }, errRes => {

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

        res.json().forEach(element => {
          element.isDeleted = 'N';
          element.riskOtherDtlId = null;
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
        //console.log(element);
      }
    });

    this.datatable = $("#dataTable").DataTableTh({
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
        { data: "departmentName" },
        { data: "riskCost", className: "right" },
        { data: "rl", className: "center" },
        { data: "valueTranslation", className: "center" }
        // {
        //   data: "riskHdrId",
        //   render: function () {
        //     return '<button type="button" class="ui mini button red del"><i class="trash alternate icon"></i> ลบ </button>';
        //   }
        // }
      ],
      rowCallback: (row, data, index) => {

        $("td > .del", row).bind("click", () => {
          //console.log(index);
          if (this.dataTableList[index].riskOtherDtlId != null && this.dataTableList[index].riskOtherDtlId != undefined && this.dataTableList[index].riskOtherDtlId != '') {
            for (let i = 0; i < this.riskDataList.length; i++) {
              const element = this.riskDataList[i];
              if (element.riskOtherDtlId != null && element.riskOtherDtlId != undefined && element.riskOtherDtlId != '') {
                if (element.riskOtherDtlId == this.dataTableList[index].riskOtherDtlId && element.departmentName == this.dataTableList[index].departmentName) {
                  this.riskDataList[i].isDeleted = 'Y';
                }

              }
            }
          } else {
            for (let i = 0; i < this.riskDataList.length; i++) {
              const element = this.riskDataList[i];
              if (element.departmentName == this.dataTableList[index].departmentName) {
                this.riskDataList.splice(i, 1);
              }
            }
          }
          this.initDatatable();
        });
      }, createdRow: function (row, data, dataIndex) {
        console.log("row");
        console.log("data", data);
        console.log("dataIndex", dataIndex);
        if (data.color == 'แดง') {
          $(row).find('td:eq(3)').addClass('bg-c-red');
          $(row).find('td:eq(4)').addClass('bg-c-red');
        } else if (data.color == 'เขียว') {
          $(row).find('td:eq(3)').addClass('bg-c-green');
          $(row).find('td:eq(4)').addClass('bg-c-green');
        } else if (data.color == 'เหลือง') {
          $(row).find('td:eq(3)').addClass('bg-c-yellow');
          $(row).find('td:eq(4)').addClass('bg-c-yellow');
        }

      }


    });
  }

  addObjRiskDtl() {
    var msgMessage = '';


    if (this.departmentName == null || this.departmentName == undefined || this.departmentName == '') {
      this.messageBarService.errorModal("กรุณากรอก \"ส่วนงานที่ต้องการประเมินความเสียง\" ");
      return;
    }


    if (this.riskCost == null || this.riskCost == undefined || this.riskCost == '') {
      this.messageBarService.errorModal("กรุณากรอก \"ค่าความเสี่ยง\" ");
      return;
    }



    let riskData = new RiskData();
    riskData.departmentName = 'สพพ. ' + this.departmentName;

    riskData.riskCost = this.riskCost;
    riskData.isDeleted = 'N';
    riskData.riskHrdId = this.riskAssRiskWsHdr.riskHrdId;

    this.riskDataList.push(riskData);
    //console.log("riskDataList", this.riskDataList);
    this.initDatatable();
    this.exciseArea = '';
    this.exciseBranch = '';
    this.exciseSetor = '';
    this.riskCost = '';
  }

  saveRiskAssRiskWsDtl(): void {
    ////console.log("saveRiskAssRiskWsDtl", this.riskHrdPaperName);
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

    if (this.riskAssRiskWsHdr.checkLastName == null || this.riskAssRiskWsHdr.checkLastName == undefined || this.riskAssRiskWsHdr.checkLastName == "") {
      msgMessage = "กรุณากรอก \"นามสกุลผู้ตรวจ\" ";
    }
    if (this.riskAssRiskWsHdr.checkUserName == null || this.riskAssRiskWsHdr.checkUserName == undefined || this.riskAssRiskWsHdr.checkUserName == "") {
      msgMessage = "กรุณากรอก \"ชื่อผู้ตรวจ\" ";
    }
    if (this.riskAssRiskWsHdr.checkUserTitle == null || this.riskAssRiskWsHdr.checkUserTitle == undefined || this.riskAssRiskWsHdr.checkUserTitle == "") {
      msgMessage = "กรุณากรอก \"คำนำหน้าชื่อผู้ตรวจ\" ";
    }
    if (this.riskAssRiskWsHdr.createPosition == null || this.riskAssRiskWsHdr.createPosition == undefined || this.riskAssRiskWsHdr.createPosition == "") {
      msgMessage = "กรุณากรอก \"ตำแหน่งผู้จัดทำ\" ";
    }
    if (this.riskAssRiskWsHdr.createLastName == null || this.riskAssRiskWsHdr.createLastName == undefined || this.riskAssRiskWsHdr.createLastName == "") {
      msgMessage = "กรุณากรอก \"นามสกุลผู้จัดทำ\" ";
    }
    if (this.riskAssRiskWsHdr.createUserName == null || this.riskAssRiskWsHdr.createUserName == undefined || this.riskAssRiskWsHdr.createUserName == "") {
      msgMessage = "กรุณากรอก \"ชื่อผู้จัดทำ\" ";
    }
    if (this.riskAssRiskWsHdr.createUserTitle == null || this.riskAssRiskWsHdr.createUserTitle == undefined || this.riskAssRiskWsHdr.createUserTitle == "") {
      msgMessage = "กรุณากรอก \"คำนำหน้าชื่อผู้จัดทำ\" ";
    }
    if (this.riskAssRiskWsHdr.riskHrdPaperName == null || this.riskAssRiskWsHdr.riskHrdPaperName == undefined || this.riskAssRiskWsHdr.riskHrdPaperName == "") {
      msgMessage = "กรุณากรอก \"ชื่อกระดาษทำการ\" ";
    }

    if (msgMessage == "") {
      var url = "ia/int083/saveRiskAssOther";
      var urlDtl = "ia/int083/saveRiskAssExcOv3dDtl";
      this.riskAssRiskWsHdr.riskType = 'MAIN';
      this.ajax.post(url, this.riskAssRiskWsHdr, res => {
        var message = res.json();
        if (message.messageType == 'E') {
          this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
        } else {


          this.ajax.post(urlDtl, { riskAssExcOv3dDtlList: this.riskDataList }, res => {
            var message = res.json();
            this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
            this.router.navigate(["/int08/3/2"], {
              queryParams: { budgetYear: this.riskAssRiskWsHdr.budgetYear }
            });
            //console.log(message);
          }, errRes => {
            var message = errRes.json();
            this.messageBarService.errorModal(message.messageTh);

            //console.log(message);
          });




        }

      }, errRes => {
        var message = errRes.json();
        ////console.log(message);
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
        this.router.navigate(["/int08/3/2"], {
          queryParams: { budgetYear: this.riskAssRiskWsHdr.budgetYear }
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

  sectorChange() {
    console.log(this.exciseSetor);
    this.departmentName = $("#exciseSetor option:selected").text();
    //console.log($('#exciseSetor').val());
    let url = "combobox/controller/getDropByTypeAndParentId";
    this.exciseBranchList = [];
    this.ajax.post(url, { type: 'SECTOR_VALUE', lovIdMaster: this.exciseSetor }, res => {

      this.exciseAreaList = res.json();
      console.log(this.exciseAreaList);
    });
  }
  branchChange() {
    this.departmentName = $("#exciseBranch option:selected").text();
  }


  areaChange() {
    console.log(this.exciseArea);
    this.departmentName = $("#exciseArea option:selected").text();
    //console.log($('#exciseSetor').val());
    let url = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(url, { type: 'SECTOR_VALUE', lovIdMaster: this.exciseArea }, res => {

      this.exciseBranchList = res.json();
      console.log(this.exciseBranchList);
    });
  }
}

class RiskData {
  departmentName: any = '';
  color: any = '';
  riskHrdId: any = 0;
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