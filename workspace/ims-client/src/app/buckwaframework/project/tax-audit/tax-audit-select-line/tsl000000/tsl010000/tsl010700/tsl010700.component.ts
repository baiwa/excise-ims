import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { AjaxService } from 'services/ajax.service';
import { Router, ActivatedRoute } from '@angular/router';
import { IaService } from 'services/ia.service';
import { AuthService } from 'services/auth.service';
import { MessageBarService } from 'services/index';
import * as moment from 'moment';
import { TextDateTH } from 'helpers/index';
declare var $: any;

@Component({
  selector: 'app-tsl010700',
  templateUrl: './tsl010700.component.html',
  styleUrls: ['./tsl010700.component.css']
})
export class Tsl010700Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การคัดเลือกราย', route: '#' },
    { label: 'ตารางผลการคัดเลือกรายประจำปี', route: '#' },
    { label: 'ตรวจสอบข้อมูลด้านภาษีสรรพาสามิต', route: '#' },
  ];

  searchFlag: string = "FALSE";
  entrepreneurTable: any;
  getRawTable: any;
  payRawTable: any;
  dataRecord: any;
  obj: dataTax;
  dateCalendar: string = "";
  id: any;

  constructor(
    private dataService: IaService,
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private message: MessageBarService,
    private ajax: AjaxService,
  ) {
    this.dataRecord = this.dataService.getData();
    console.log("dataRecord", this.dataRecord);
    this.dateCalendar = this.route.snapshot.queryParams['dateCalendar'];
    this.searchFlag = this.route.snapshot.queryParams['searchFlag'];
    this.obj = new dataTax();

    //let date = moment(new Date()).locale('th');
    //console.log(date)
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('tsl010700').then(user => {
      this.obj.officer = user.fullName;
    });
   
  }



  onReport() {
    this.obj.exciseArea =   $("#exciseArea").val();
    this.obj.exciseSubArea =   $("#exciseSubArea").val();
    this.obj.exciseId =   $("#exciseId").val();
    this.obj.companyName =   $("#companyName").val();
    this.obj.product =   $("#product").val();
    this.obj.riskTypeDesc =   $("#riskTypeDesc").val();
    this.obj.dateCalendar =   $("#dateCalendar").val();
    this.obj.companyAddress =   $("#companyAddress").val();
    // if(this.obj.resultGetRaw==="1"){
    //   this.obj.resultGetRawValue="";
    // }
    
    console.log(this.obj);

     var form = document.createElement("form");
     form.method = "POST";
     form.action = AjaxService.CONTEXT_PATH + "exciseTax/report/pdf/tax/checkExciseTax";
     form.style.display = "none";    
     var jsonInput = document.createElement("input");
     jsonInput.name = "json";
     jsonInput.value = JSON.stringify(this.obj);
     form.appendChild(jsonInput);
     document.body.appendChild(form);
     form.submit();
     
    // const URL = "exciseTax/report/updateFlag";
    // this.message.comfirm(confirm => {
    //   if (confirm) {
    //     const URL = "exciseTax/report/updateFlag";
    //     this.ajax.post(URL, parseInt(this.dataRecord.taYearPlanId) || 0, response => {
    //       this.message.successModal(response.json().messageTh);
    //       this.router.navigate(["/tax-audit-select-line/tsl0106-00"]);
    //     }).catch(err => {
    //       this.message.errorModal("ไม่สามารถทำการบันทึกได้");
    //       console.error(err);
    //     });
    //   }
    // }, "ยืนยันการบันทึกข้อมูล");

  }

  ngAfterViewInit() {
    this.initGetRawDatatable();
    this.initPayRawDatatable();
  }

  initGetRawDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "taxAudit/checkDisplay/search";
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
            "exciseId": this.dataRecord.exciseId,
            "dateCalendar": this.dateCalendar,
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
    const URL = AjaxService.CONTEXT_PATH + "taxAudit/checkDisplay/search";
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
            "exciseId": this.dataRecord.exciseId,
            "dateCalendar": this.dateCalendar,
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

  onClickBack() {
    this.router.navigate(["/tax-audit-select-line/tsl0106-00"], {
      queryParams: {}
    });
  }



}

class dataTax {
  exciseArea: string;
  exciseSubArea: string;
  exciseId: string;
  companyName: string;
  product: string;
  riskTypeDesc: string;
  dateCalendar: string;
  companyAddress: string;
  //user login
  officer: string;
  
  //radio1
  resultGetRaw:string;
  resultGetRawValue:string;
  resultGetRawBox:string;
  //radio2
  resultPayRaw:string;
  resultPayRawValue:string;
  resultPayRawBox:string;
  //radio3
  // buyInvoiceBox:string;
  // buyInvoiceNum:string;
  // buyInvoiceValue:string;
  // buyInvoice:string;
  //radio4
  // taxInvoiceBox:string;
  // taxInvoiceNumNum:string
  // taxInvoiceValue:string;
  // taxInvoiceNum:string;

  

} 