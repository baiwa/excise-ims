import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { AjaxService } from 'services/ajax.service';
import { Router, ActivatedRoute } from '@angular/router';
import { IaService } from 'services/ia.service';
import { AuthService } from 'services/auth.service';
import { MessageBarService } from 'services/index';
import { Cop9, Cop9Service } from '../cop9.service';
import { UserDetail } from 'models/index';
import { ThYearToEnYear } from 'helpers/index';
import * as moment from 'moment';

declare var $: any;

@Component({
  selector: 'app-cop09-2',
  templateUrl: './cop09-2.component.html',
  styleUrls: ['./cop09-2.component.css']
})
export class Cop092Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจปฏิบัติการ", route: "#" },
    { label: "ตรวจสอบข้อมูล", route: "#" },
    { label: 'ตรวจสอบข้อมูลด้านภาษีสรรพาสามิต', route: '#' },
  ];

  searchFlag: string = "FALSE";
  entrepreneurTable: any;
  getRawTable: any;
  payRawTable: any;
  dataRecord: any;
  obj: data = new data();
  dateCalendar: string = "";
  dateCalendarEn: string = "";
  dateMonth: Date = new Date();
  id: any;
  idUpdate: any = 0;

  dataList: Cop9;

  user: UserDetail;

  constructor(
    private dataService: IaService,
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private message: MessageBarService,
    private ajax: AjaxService,
    private cop9Service: Cop9Service
  ) {
    this.dataRecord = this.dataService.getData();
    console.log("dataRecord", this.dataRecord);
   // console.log("id", this.dataRecord.id);
    this.dateCalendar = this.route.snapshot.queryParams['dateCalendar'];
    this.searchFlag = this.route.snapshot.queryParams['searchFlag'];
    this.dateMonth = new Date();
    this.dateMonth.setFullYear(parseInt(ThYearToEnYear(this.dateCalendar.split("/")[1])));
    this.dateMonth.setMonth(parseInt(this.dateCalendar.split("/")[0]) - 1);
    this.dateCalendarEn = moment(this.dateMonth).format('DD/MM/YYYY');

  }

  async ngOnInit() {
    this.idUpdate = this.route.snapshot.queryParams["id"];
    this.user = await this.authService.reRenderVersionProgram('cop092');


  }


  // onChangeRadio(name: string, e) {
  //   const result = ["ข้อมูลถูกต้อง", "ข้อมูลไม่ถูกต้อง"]
  //   this.obj[name] = result[parseInt(e.target.value)];
  // }

  onReport() {
    this.obj.fiscalYearId = this.dataRecord.id;
    this.obj.exciseArea =   $("#exciseArea").val();
    this.obj.exciseSubArea =   $("#exciseSubArea").val();
    this.obj.exciseId =   $("#exciseId").val();
    this.obj.companyName =   $("#companyName").val();
    this.obj.product =   $("#product").val();
    //this.obj.riskTypeDesc =   $("#riskTypeDesc").val();
    this.obj.dateCalendar =   $("#dateCalendar").val();
    this.obj.companyAddress =   $("#companyAddress").val();

    //this.obj.resultGetRaw =   $("#resultGetRaw").val();
    // this.obj.resultPayRaw =   $("#resultPayRaw").val();
    // this.obj.receiptInvoiceRaw =   $("#receiptInvoiceRaw").val();
    // this.obj.payInvoiceRaw =   $("#payInvoiceRaw").val();


    if (this.user.fullName != null && this.user.fullName != undefined && this.user.fullName != '') {
      this.obj.officer = this.user.fullName;
    }else{
      this.obj.officer = "";
    }


    console.log(this.obj);
    
    this.message.comfirm(confirm => {
      if (confirm) {
        const URL = "cop/cop092/updateFlag";
        this.ajax.post(URL, { "id": this.idUpdate }, response => {
          this.saveDataReport();
          this.exportPdf();
          this.message.successModal(response.json().msg.messageTh);
          this.router.navigate(["/cop09/1"]);
        }).catch(err => {
          this.message.errorModal("ไม่สามารถทำการบันทึกได้");
          console.error(err);
        });
      }
    }, "ยืนยันการบันทึกข้อมูล");

  }


  exportPdf() {
    var form = document.createElement("form");
    form.method = "POST";
    form.action = AjaxService.CONTEXT_PATH + "exciseOperation/report/pdf/operation/checkExciseOperation";
    // form.action = AjaxService.CONTEXT_PATH + "exciseTax/report/pdf/tax/checkExciseTax";
    form.style.display = "none";
    form.target = "_blank"    
    var jsonInput = document.createElement("input");
    jsonInput.name = "json";
    jsonInput.value = JSON.stringify(this.obj);
    form.appendChild(jsonInput);
    document.body.appendChild(form);
    form.submit();
  }


  saveDataReport() {
      const URL = "cop/cop092/saveReport";

      this.ajax.post(URL, 
        { fiscalYearId: this.obj.fiscalYearId, 
          exciseArea: this.obj.exciseArea, 
          exciseSubArea: this.obj.exciseSubArea, 
          exciseId: this.obj.exciseId, 
          companyName:this.obj.companyName,
          product:this.obj.product,
          dateCalendar:this.obj.dateCalendar,
          companyAddress:this.obj.companyAddress,
          resultGetRaw:this.obj.resultGetRaw,
          resultGetBox:this.obj.resultGetRawBox,
          resultPayRaw:this.obj.resultPayRaw,
          resultPayBox:this.obj.resultPayRawBox,
          receiptInvoiceRaw:this.obj.receiptInvoiceRaw,
          receiptInvoiceBox:this.obj.receiptInvoiceBox,
          payInvoiceRaw:this.obj.payInvoiceRaw,
          payInvoiceBox:this.obj.payInvoiceBox,
          officer:this.obj.officer
        }, res => { 
      });

  }


  ngAfterViewInit() {
    this.initGetRawDatatable();
    this.initPayRawDatatable();
    this.initGetProductDatatable();
    this.initPayProductDatatable();
  }

  initGetRawDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "cop/cop092/budget/list";
    this.getRawTable = $("#tableGetRawMaterial").DataTableTh({
      serverSide: true,
      searching: false,
      processing: true,
      ordering: false,
      scrollX: true,
      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            "excise": this.dataRecord.exciseId,
            "monthBuget": this.dateCalendarEn,
            "searchFlag": this.searchFlag
          }));
        }
      },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "list",
          className: "ui left aligned"
        }, {
          data: "unit",
          className: "ui left aligned"
        }, {
          data: "stock",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "receive",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "receiveTotal",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }
      ],
    });
  };

  initPayRawDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "cop/cop092/budget/list";
    this.payRawTable = $("#tablePayRawMaterial").DataTableTh({
      serverSide: true,
      searching: false,
      processing: true,
      ordering: false,
      scrollX: true,
      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            "excise": this.dataRecord.exciseId,
            "monthBuget": this.dateCalendarEn,
            "searchFlag": this.searchFlag
          }));
        }
      },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "list",
          className: "ui left aligned"
        }, {
          data: "unit",
          className: "ui left aligned"
        }, {
          data: "productInline",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "productOutline",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "corrupt",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "other",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }
      ],
    });
  };

  initGetProductDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "cop/cop092/product/list";
    this.getRawTable = $("#tableReceiveProduct").DataTableTh({
      serverSide: true,
      searching: false,
      processing: true,
      ordering: false,
      scrollX: true,
      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            "excise": this.dataRecord.exciseId,
            "monthBuget": this.dateCalendarEn,
            "searchFlag": this.searchFlag
          }));
        }
      },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "list",
          className: "ui left aligned"
        }, {
          data: "unit",
          className: "ui left aligned"
        }, {
          data: "stock",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        },
        {
          data: "getPro",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        },
        {
          data: "receive",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        },
        {
          data: "other",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "receiveTotal",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }
      ],
    });
  };

  initPayProductDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "cop/cop092/product/list";
    this.payRawTable = $("#tablePayProduct").DataTableTh({
      serverSide: true,
      searching: false,
      processing: true,
      ordering: false,
      scrollX: true,
      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            "excise": this.dataRecord.exciseId,
            "monthBuget": this.dateCalendarEn,
            "searchFlag": this.searchFlag
          }));
        }
      },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "list",
          className: "ui left aligned"
        }, {
          data: "unit",
          className: "ui left aligned"
        }, {
          data: "domDist",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "foreignSale",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "inHouseUse",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "warehouse",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "corrupt",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "other1",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "total",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }
      ],
    });
  };

  onClickBack() {
    this.router.navigate(["cop09/1"], {
      queryParams: {}
    });
  }

}

class data {
  fiscalYearId:any;
  exciseArea: string;
  exciseSubArea: string;
  exciseId: string;
  companyName: string;
  product: string;
  //riskTypeDesc: string;
  dateCalendar: string;
  companyAddress: string;
  //user login
  officer: string;
  
  //radio1
  resultGetRaw:string;
  resultGetRawBox:string;
  //radio2
  resultPayRaw:string;
  resultPayRawBox:string;
  //radio3
  receiptInvoiceBox:string;
  receiptInvoiceRaw:string;
  //radio4
  payInvoiceBox:string;
  payInvoiceRaw:string;
} 