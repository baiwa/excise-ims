import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';


declare var jQuery: any;
declare var $: any;

@Component({
  selector: "app-int08-2-1",
  templateUrl: "./int08-2-1.component.html",
  styleUrls: ["./int08-2-1.component.css"]
})
export class Int0821Component implements OnInit {

  formatter: any;

  riskAssInfHdr: any;
  riskAssInfHdrId : any;
  riskAssInfHdrName: any;
  riskInfPaperName: any;
  budgetYear: any;


  infRiskList: any[];
  datatable: any;


  riskList: any[];
  isSearch: any = false;

  openForm1: any;
  openForm2: any;
  dataTableF1: any;
  dataTableF2: any;

  condition: any;

  showData: boolean = false;
  constructor(
    private router: Router,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
    private _location: Location
  ) { }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    
    this.budgetYear = this.route.snapshot.queryParams["budgetYear"];
    if (this.budgetYear == null || this.budgetYear == undefined) {
      this.budgetYear == 'xxxx';
    }

    $('#year').calendar({
      type: 'year',
      text: TextDateTH,
      formatter: formatter('ป'),
      onChange: (date) => {
        console.log(date.getFullYear())
        this.changeYear(date.getFullYear() + 543);
      }
    });
    this.openForm1 = false;
    this.openForm2 = false;
    this.infRiskList = ["ปัจจัยเสี่ยงจำนวนครั้งการใช้งานไม่ได้ของระบบ"];
    this.initDatatable();
  }

  ngAfterViewInit() {

  }

  changeYear(year) {
    this.budgetYear = year;
    console.log(this.budgetYear);
    const URL = "combobox/controller/findByRiskInfName";
    this.ajax.post(URL, { budgetYear: this.budgetYear }, res => {

      this.riskList = res.json();
      console.log(this.riskList);
    });
  }



  createBudgetYear() {
    this.budgetYear = $('#budgetYear').val().trim();
    console.log(this.budgetYear);
    if (this.budgetYear == null || this.budgetYear == undefined || this.budgetYear == "") {
      this.messageBarService.errorModal("กรุณาเลือก ปีงบประมาณ ในการสร้างปัจจัยเสี่ยง");
    } else {
      const URL = "ia/int082/createBudgetYear";

      this.ajax.post(URL, { budgetYear: this.budgetYear }, res => {
        this.router.navigate(["/int08/2/2"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }, errRes => {
        var message = errRes.json();
        this.messageBarService.errorModal(message.messageTh);

      });
    }
  }

  searchDataTable() {
    this.budgetYear = $('#budgetYear').val().trim();
    if (this.budgetYear != null && this.budgetYear != undefined && this.budgetYear != '') {
      this.isSearch = true;
      this.initDatatable();
    } else {
      this.messageBarService.errorModal('กรุณาเลือก ปีงบประมาณ ในการค้นหาข้อมูล');
    }
  }

  initDatatable(): void {
    if (this.datatable != null || this.datatable != undefined) {
      this.datatable.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int082/searchRiskInfHdr";
    console.log(URL);
    console.log(this.budgetYear);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      scrollY:true,
      scrollX: true,
      scrollCollapse: true,
      paging: true,
      ajax: {
        type: "POST",
        url: URL,
        data: { budgetYear: this.budgetYear }
      },
      columns: [

        // {
        //   render: function (data, type, row, meta) {
        //     return `<input type="checkbox" >`;
        //   },
        //   className: "center"
        // },
        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },

        { data: "riskInfPaperName" },
        { data: "riskAssInfHdrName" },
        { data: "budgetYear" },
        { data: "createdDate" },
        { data: "createdBy" },
        {
          data: "riskAssInfHdrId",
          render: function () {
            return '<button type="button" class="ui mini button primary dtl" (click)="ExportOtherDtl()" ><i class="table icon"></i> รายละเอียด</button>'
              + '<button type="button" class="ui mini button primary export"><i class="print icon"></i> Export</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [0, 3, 4], className: "center aligned" },
        { targets: [1,2], className: "left aligned" }
      ],
      rowCallback: (row, data, index) => {
        $("td > .dtl", row).bind("click", () => {
         
          if (this.infRiskList.indexOf(data.riskAssInfHdrName) >= 0) {
            this.riskAssInfHdrId = data.riskAssInfHdrId;
            this.renderForm1(data.riskAssInfHdrId);
          } else {
            this.riskAssInfHdrId = data.riskAssInfHdrId;
            this.renderForm2(data.riskAssInfHdrId);
          }
        })

        $("td > .export", row).bind("click", () => {

          if (this.infRiskList.indexOf(data.riskAssInfHdrName) >= 0) {
            this.riskAssInfHdrId = data.riskAssInfHdrId;
            const URL = "ia/int082/exportInfWebService?riskAssInfHdrId=" + this.riskAssInfHdrId;
            console.log("id", this.riskAssInfHdrId);
            this.ajax.download(URL);
            
          } else {
            this.riskAssInfHdrId = data.riskAssInfHdrId;
            const URL = "ia/int082/exportInfOtherDtl?riskAssInfHdrId=" + this.riskAssInfHdrId;
            console.log("id", this.riskAssInfHdrId);
            this.ajax.download(URL);
          }

        });

      }
    });
  }

  renderForm1(riskAssInfHdrId) {

    console.log("riskAssInfHdrId", riskAssInfHdrId);
    const URLHRD = "ia/int082/findRiskById";
    this.ajax.post(URLHRD, { riskAssInfHdrId: riskAssInfHdrId }, res => {
      this.riskAssInfHdr = res.json();
      this.openForm1 = true;
      this.openForm2 = false;
      console.log("riskAssInfHdr", this.riskAssInfHdr);
      const URL = "ia/condition/findConditionByParentId";
      this.ajax.post(URL, { parentId: riskAssInfHdrId, riskType: 'MAIN', page: 'int08-2-3' }, res => {
        this.condition = res.json();
        console.log("condition", this.condition);

        this.initDatatableF1();
      });

    });

  }

  initDatatableF1(): void {
    if (this.dataTableF1 != null) {
      this.dataTableF1.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int082/dataTableWebService";
    console.log(URL);
    this.dataTableF1 = $("#dataTableF1").DataTable({
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
      ajax: {
        type: "POST",
        url: URL,
        data: { riskAssInfHdrId: this.riskAssInfHdrId }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "infName" },
        { data: "jan", render: $.fn.dataTable.render.number(',') },
        { data: "feb", render: $.fn.dataTable.render.number(',') },
        { data: "mar", render: $.fn.dataTable.render.number(',') },
        { data: "april", render: $.fn.dataTable.render.number(',') },
        { data: "may", render: $.fn.dataTable.render.number(',') },
        { data: "jun", render: $.fn.dataTable.render.number(',') },
        { data: "jul", render: $.fn.dataTable.render.number(',') },
        { data: "aug", render: $.fn.dataTable.render.number(',') },
        { data: "sep", render: $.fn.dataTable.render.number(',') },
        { data: "oct", render: $.fn.dataTable.render.number(',') },
        { data: "nov", render: $.fn.dataTable.render.number(',') },
        { data: "dec", render: $.fn.dataTable.render.number(',') },
        { data: "total", render: $.fn.dataTable.render.number(',') },
        { data: "rl" },
        { data: "valueTranslation" }

      ], 
      createdRow: function (row, data, dataIndex) {
        console.log("row");
        console.log("data", data.valueTranslation);
        console.log("dataIndex", dataIndex);
        if (data.color == 'แดง') {
          $(row).find('td:eq(15)').addClass('red');
          $(row).find('td:eq(16)').addClass('red');
        } else if (data.color == 'เขียว') {
          $(row).find('td:eq(15)').addClass('green');
          $(row).find('td:eq(16)').addClass('green');
        } else if (data.color == 'เหลือง') {
          $(row).find('td:eq(15)').addClass('yellow');
          $(row).find('td:eq(16)').addClass('yellow');
        }

      },
      columnDefs: [
        { targets: [0,15,16], className: "center aligned" },
        { targets: [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }


  renderForm2(riskAssInfHdrId) {
    console.log("riskAssInfHdrId", riskAssInfHdrId);
    const URLHRD = "ia/int082/findRiskById";
    this.ajax.post(URLHRD, { riskAssInfHdrId: riskAssInfHdrId }, res => {
      this.riskAssInfHdr = res.json();
      this.openForm1 = false;
      this.openForm2 = true;
      console.log("riskAssInfHdr", this.riskAssInfHdr);
      const URL = "ia/condition/findConditionByParentId";
      this.ajax.post(URL, { parentId: riskAssInfHdrId, riskType: 'OTHER', page: 'int08-2-4' }, res => {
        this.condition = res.json();
        console.log("condition", this.condition);
        this.initDatatableF2();
      });
    });
  }

  initDatatableF2(): void {
    if (this.dataTableF1 != null) {
      this.dataTableF2.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int082/findRiskAssInfOtherDtlByHeaderId";
    console.log(URL);
    this.dataTableF2 = $("#dataTableF2").DataTable({
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
      ajax: {
        type: "POST",
        url: URL,
        data: { riskAssInfHdrId: this.riskAssInfHdrId }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },

        { data: "infName" },
        { data: "riskCost", className: "right" },
        { data: "rl", className: "center" },
        { data: "valueTranslation", className: "center" },
        

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
      columnDefs: [
        { targets: [0,4], className: "center aligned" },
        { targets: [2,3], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }

  clearDataForm1() {
    this.openForm1 = false;
  }

  clearDataForm2() {
    this.openForm2 = false;
  }

  clearData(){
    this.budgetYear = "";
    this.initDatatable();
    this.openForm1 = false;
    this.openForm2 = false;
  }

  ExportWebService() {
    const URL = "ia/int082/exportInfWebService?riskAssInfHdrId=" + this.riskAssInfHdrId;
    console.log("id", this.riskAssInfHdrId);
    this.ajax.download(URL);

  }


  ExportOtherDtl() {
    const URL = "ia/int082/exportInfOtherDtl?riskAssInfHdrId=" + this.riskAssInfHdrId;
    console.log("id", this.riskAssInfHdrId);
    this.ajax.download(URL);
 
  }


}
