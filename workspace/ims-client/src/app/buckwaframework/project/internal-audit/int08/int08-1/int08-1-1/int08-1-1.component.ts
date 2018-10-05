import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
declare var $: any;
@Component({
  selector: "int08-1-1",
  templateUrl: "./int08-1-1.component.html",
  styleUrls: ["./int08-1-1.component.css"]
})
export class Int0811Component implements OnInit {
  showData: boolean = false;
  data: String[];
  budgetYear: any;
  riskList: any[];
  datatable: any;
  isSearch: any = false;
  riskYear: any;
  wsRiskList: any[];
  showForm: any;
  dataTableF1: any;
  riskAssRiskWsHdr: any;
  condition: any;
  riskHrdId: any;
  dataTableF2: any;
  dataTableF3: any;
  active: any;
  riskType: any;
  buttonFullYear: any;
  pageMapping: any[] = [];
  riskDataList: RiskData[];
  riskAssRiskWsHdrList: any;
  columnList: any[];
  percentList: any[];
  // BreadCrumb
breadcrumb: BreadCrumb[];
  constructor(
    private router: Router,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "การประเมินความเสี่ยง", route: "#" },
      { label: "ประเมินความเสี่ยงโครงการยุทธศาสตร์ของกรมสรรพสามิต", route: "#" },
    ];
   }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");

    this.budgetYear = this.route.snapshot.queryParams["budgetYear"];
    this.riskType = this.route.snapshot.queryParams["riskType"];

    if (this.budgetYear == null || this.budgetYear == undefined) {
      this.budgetYear == 'xxxx';
    }
    $('#year').calendar({
      type: 'year',
      text: TextDateTH,
      formatter: formatter('ป'),
      onChange: (date) => {
        //console.log(date.getFullYear())
        this.changeYear(date.getFullYear() + 543);
      }
    });
    this.active = "Y";


    this.showForm = '0';
    this.wsRiskList = ["ปัจจัยเสี่ยงงบประมาณที่ใช้ดำเนินงานโครงการ", "ปัจจัยเสี่ยงประสิทธิภาพในการดำเนินงานโครงการ"];
    this.pageMapping = ["/int08/1/5", "/int08/1/8"];
    this.initDatatable();

    if (this.budgetYear != null && this.budgetYear != undefined && this.budgetYear != '') {
      this.buttonFullYear = true;
    } else {
      this.buttonFullYear = false;
    };

  }


  uploadData() {
    //this.showData = true;

  }

  changeYear(year) {
    this.budgetYear = year;
    //console.log(this.budgetYear);
    const URL = "combobox/controller/findByBudgetYear";
    this.ajax.post(URL, { budgetYear: this.budgetYear }, res => {

      this.riskList = res.json();
      //console.log(this.riskList);


    });
  }


  createBudgetYear() {
    this.budgetYear = $('#budgetYear').val().trim();
    if (this.budgetYear != null && this.budgetYear != undefined && this.budgetYear != '') {
      //console.log(this.budgetYear);
      const URL = "ia/int08/createBudgetYear";

      this.ajax.post(URL, { budgetYear: this.budgetYear, active: 'Y' }, res => {


        this.router.navigate(["/int08/1/4"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }, errRes => {
        var message = errRes.json();
        this.messageBarService.errorModal(message.messageTh);

      });
    } else {
      this.messageBarService.errorModal('กรุณาเลือก ปีงบประมาณ');
    }
  }

  searchDataTable() {
    this.riskType = $('#riskType').val();
    this.budgetYear = $('#budgetYear').val().trim();
    //console.log("riskType :" + this.riskType);

    if (this.budgetYear != null && this.budgetYear != undefined && this.budgetYear != '') {
      this.isSearch = true;
      this.initDatatable();
      this.buttonFullYear = true;
    } else {
      this.messageBarService.errorModal('กรุณาเลือก ปีงบประมาณ');
    }
  }


  initDatatable(): void {
    if (this.datatable != null) {
      this.datatable.destroy();
    }
    // const URL = AjaxService.CONTEXT_PATH + "ia/int08/searchRiskAssRiskWsHdr";
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/searchRisk";
    //console.log(URL);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: true,
      ajax: {
        type: "POST",
        url: URL,
        data: { riskHdrName: this.riskType, budgetYear: this.budgetYear, active: this.active }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "riskHdrName" },
        { data: "riskHrdPaperName" },
        { data: "budgetYear" },
        {
          render: function (data, type, row, meta) {
            //console.log("data :", row.createdDate)
            if (row.createdDate != null && row.createdDate != undefined && row.createdDate != '') {
              var dateTime = new Date(row.createdDate).toLocaleString("th-TH");
              return dateTime.split(' ')[0];
            } else {
              return row.createdDate;
            }
          },
          className: "center"
        },
        { data: "createdBy" },
        {
          data: "riskHdrId",
          render: function () {
            return '<button type="button" class="ui mini button  primary dtl"><i class="table icon"></i> รายละเอียด</button>'
              + '<button type="button" class="ui mini button  primary export"><i class="print icon "></i> Export</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [0, 3, 4, 5, 6], className: "center aligned" },
        { targets: [1, 2], className: "left aligned" }
      ],
      rowCallback: (row, data, index) => {
        $("td > .dtl", row).bind("click", () => {
          var indexPage = this.wsRiskList.indexOf(data.riskHdrName);
          if (indexPage >= 0) {
            this.riskHrdId = data.riskHrdId;
            if ("/int08/1/5" == this.pageMapping[indexPage]) {
              this.renderForm1(data.riskHrdId);
            } else {
              this.renderForm3(data.riskHrdId);
            }

          } else {
            this.riskHrdId = data.riskHrdId;
            this.renderForm2(data.riskHrdId);
          }


        })
        $("td > .export", row).bind("click", () => {


        });
      }
    });
  }

  renderForm3(riskHrdId) {

    //console.log("riskHrdId", riskHrdId);
    const URLHRD = "ia/int08/findRiskById";
    this.ajax.post(URLHRD, { riskHrdId: riskHrdId }, res => {
      this.riskAssRiskWsHdr = res.json();
      this.showForm = 3;
      //console.log("riskAssRiskWsHdr", this.riskAssRiskWsHdr);
      const URL = "ia/condition/findConditionByParentId";
      this.ajax.post(URL, { parentId: riskHrdId, riskType: 'MAIN', page: 'int08-1-8' }, res => {
        this.condition = res.json();
        //console.log("condition", this.condition);


        this.initDatatableF3();
      });

    });

  }

  renderForm1(riskHrdId) {

    //console.log("riskHrdId", riskHrdId);
    const URLHRD = "ia/int08/findRiskById";
    this.ajax.post(URLHRD, { riskHrdId: riskHrdId }, res => {
      this.riskAssRiskWsHdr = res.json();
      this.showForm = 1;
      //console.log("riskAssRiskWsHdr", this.riskAssRiskWsHdr);
      const URL = "ia/condition/findConditionByParentId";
      this.ajax.post(URL, { parentId: riskHrdId, riskType: 'MAIN', page: 'int08-1-5' }, res => {
        this.condition = res.json();
        //console.log("condition", this.condition);


        this.initDatatableF1();
      });

    });

  }

  initDatatableF1(): void {
    if (this.dataTableF1 != null) {
      this.dataTableF1.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/dataTableWebService1";
    //console.log(URL);
    this.dataTableF1 = $("#dataTableF1").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      scrollY: true,
      scrollX: true,
      scrollCollapse: true,
      paging: true,
      ajax: {
        type: "POST",
        url: URL,
        data: { riskHrdId: this.riskHrdId }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "projectBase" },
        { data: "budget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "localBudget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "otherMoney", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "approveBudget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "sumMonth", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "rl" },
        { data: "valueTranslation" }

      ], createdRow: function (row, data, dataIndex) {
        //console.log("row");
        //console.log("data", data.valueTranslation);
        //console.log("dataIndex", dataIndex);
        if (data.color == 'แดง') {
          $(row).find('td:eq(8)').addClass('bg-c-red');
          $(row).find('td:eq(7)').addClass('bg-c-red');
        } else if (data.color == 'เขียว') {
          $(row).find('td:eq(8)').addClass('bg-c-green');
          $(row).find('td:eq(7)').addClass('bg-c-green');
        } else if (data.color == 'เหลือง') {
          $(row).find('td:eq(8)').addClass('bg-c-yellow');
          $(row).find('td:eq(7)').addClass('bg-c-yellow');
        }

      },
      columnDefs: [
        { targets: [0, 2, 7], className: "center aligned" },
        { targets: [3, 4, 5, 6], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }
  initDatatableF3(): void {
    if (this.dataTableF3 != null) {
      this.dataTableF3.destroy();
    }
    let url = "ia/int08/queryRiskAssPerDtlByHrdId";
    this.ajax.post(url, { riskHrdId: this.riskHrdId }, res => {
      // this.riskDataList
      var jsonObjList = res.json();
      this.riskDataList = [];
      for (let index = 0; index < jsonObjList.length; index++) {
        var element = jsonObjList[index];
        var riskData = new RiskData();
        riskData.riskOtherDtlId = element.riskOtherDtlId;
        riskData.riskHrdId = this.riskHrdId;
        riskData.departmentName = element.departmentName;
        riskData.projectBase = element.projectBase;
        riskData.riskCost = element.riskCost;
        riskData.rl = element.rl;
        riskData.valueTranslation = element.valueTranslation;
        riskData.color = element.color;
        riskData.isDeleted = 'N';
        this.riskDataList.push(riskData);

      }

      this.dataTableF3 = $("#dataTableF3").DataTable({
        lengthChange: false,
        searching: false,
        ordering: false,
        pageLength: 10,
        processing: true,
        serverSide: false,
        paging: true,
        data: this.riskDataList,
        columns: [
          {
            render: function (data, type, row, meta) {
              return meta.row + meta.settings._iDisplayStart + 1;
            },
            className: "center"
          },
          { data: "projectBase" },
          { data: "departmentName" },
          { data: "riskCost" },
          { data: "rl" },
          { data: "valueTranslation" }
        ],
        columnDefs: [
          { targets: [0, 2, 4, 5], className: "center aligned" },
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
 
        }

      });
    }, errRes => {

    });
    // const URL = AjaxService.CONTEXT_PATH + "ia/int08/findRiskAssOtherDtlByHeaderId";

  }



  initDatatableF2(): void {
    if (this.dataTableF2 != null) {
      this.dataTableF2.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/findRiskAssOtherDtlByHeaderId";
    //console.log(URL);
    this.dataTableF2 = $("#dataTableF2").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      scrollY: true,
      scrollX: true,
      scrollCollapse: true,
      paging: true,
      ajax: {
        type: "POST",
        url: URL,
        data: { riskHrdId: this.riskHrdId }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "projectBase" },
        { data: "departmentName" },
        { data: "riskCost" },
        { data: "rl" },
        { data: "valueTranslation" }

      ], createdRow: function (row, data, dataIndex) {
        //console.log("row");
        console.log("data", data);
        //console.log("dataIndex", dataIndex);
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
      columnDefs: [
        { targets: [0, 2, 4, 5], className: "center aligned" },
        { targets: [3], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }

  renderForm2(riskHrdId) {
    //console.log("riskHrdId", riskHrdId);
    const URLHRD = "ia/int08/findRiskById";
    this.ajax.post(URLHRD, { riskHrdId: riskHrdId }, res => {
      this.riskAssRiskWsHdr = res.json();
      this.showForm = 2;
      //console.log("riskAssRiskWsHdr", this.riskAssRiskWsHdr);
      const URL = "ia/condition/findConditionByParentId";
      this.ajax.post(URL, { parentId: riskHrdId, riskType: 'OTHER', page: 'int08-1-6' }, res => {
        this.condition = res.json();
        //console.log("condition", this.condition);
        this.initDatatableF2();
      });
    });
  }

  renderForm4() {
    //console.log("budgetYear", this.budgetYear);
    this.showForm = 4;
    const URL = "ia/condition/findConditionByParentId";
    this.ajax.post(URL, { parentId: this.budgetYear, riskType: 'ALL', page: 'int08-1-7' }, res => {
      this.condition = res.json();
      //console.log("condition", this.condition);
      this.queryColumnNameListAndSetColumn();
    });

  }

  initDatatable4(): void {
    var url = 'ia/int08/searchFullRiskByBudgetYear';
    var hrmlTr = '';
    this.ajax.post(url, { budgetYear: this.budgetYear, riskHrdNameList: this.columnList }, res => {

      res.json().forEach(element => {
        //console.log(element);
        hrmlTr += "<tr style='text-align: center !important'>";
        hrmlTr += "<td>" + element.id + "</td>";
        hrmlTr += "<td style='text-align: left !important'>" + element.projectBase + "</td>";
        hrmlTr += "<td style='text-align: center !important'>" + element.departmentName + "</td>";
        element.rl.forEach(rl => {
          hrmlTr += "<td style='text-align: right !important' >" + rl + "</td>";
        });
        hrmlTr += "<td style='text-align: right !important'>" + element.sumRiskCost + "</td>";
        hrmlTr += "<td style='text-align: center !important'  class='" + this.getStyeClassByColor(element.color) + "'>" + element.rlAll + "</td>";
        hrmlTr += "<td style='text-align: center !important'  class='" + this.getStyeClassByColor(element.color) + "'>" + element.valueTranslation + "</td>";
        hrmlTr += "</tr>";
      });

      $("#tbody").html(hrmlTr);
    }, errRes => {
      //console.log(errRes);
    });
  }

  queryColumnNameListAndSetColumn() {
    var url = "ia/int08/findByBudgetYear";

    this.ajax.post(url, { budgetYear: this.budgetYear }, res => {
      //console.log(riskAssRiskWsHdr);
      var riskAssRiskWsHdr = res.json();
      this.riskAssRiskWsHdrList = res.json();
      this.columnList = [];
      this.percentList = [];
      for (let i = 0; i < riskAssRiskWsHdr.length; i++) {
        const element = riskAssRiskWsHdr[i];
        this.columnList.push(element.riskHdrName);
        this.percentList.push(0);
      }

      var trHTML = '<tr><th rowspan="2" style="text-align: center !important">ลำดับ</th> <th rowspan="2" style="text-align: center !important">โครงการตามยุทธศาสตร์</th><th rowspan="2" style="text-align: center !important">หน่วยงาน</th>';
      this.columnList.forEach(element => {
        //console.log(element);
        trHTML += '<th rowspan="2" style="text-align: center !important;">' + element + '</th>';
      });
      trHTML += '<th rowspan="2" style="text-align: center !important;" >รวม</th><th colspan="2" style="text-align: center !important">ประเมินความเสี่ยง</th></tr><tr><th style="text-align: center !important; border-left: 1px solid rgba(34,36,38,.1) !important">RL</th><th style="text-align: center !important">แปลค่า</th></tr>';
      $("#trColumn").html(trHTML);
      this.initDatatable4();
    }, errRes => {
      //console.log(errRes);
    });
  }

  getStyeClassByColor(color) {
    if (color == 'แดง') {
      return 'bg-c-red';
    } else if (color == 'เขียว') {
      return 'bg-c-green';
    } else if (color == 'เหลือง') {
      return 'bg-c-yellow';
    }
  }

  clearDataForm1() {
    this.showForm = 0;
  }

  clearDataForm2() {
    this.showForm = 0;
  }

  clearDataForm3() {
    this.showForm = 0;
  }

  changebudgetYear = event => {
    //console.log(event)
  }

  clearData() {
    this.budgetYear = "";
    this.buttonFullYear = false;
    this.initDatatable();
    this.showForm = 0;
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

