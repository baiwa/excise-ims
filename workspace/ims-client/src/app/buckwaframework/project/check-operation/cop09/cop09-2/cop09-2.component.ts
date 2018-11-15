import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { AjaxService } from 'services/ajax.service';
import { Router, ActivatedRoute } from '@angular/router';
import { IaService } from 'services/ia.service';
import { AuthService } from 'services/auth.service';
import { MessageBarService } from 'services/index';

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
  obj: data;
  dateCalendar: string = "";
  id: any;
  idUpdate:any=0;

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
    this.obj = new data();
  }

  ngOnInit() {

    this.idUpdate = this.route.snapshot.queryParams["id"];
    this.authService.reRenderVersionProgram('cop092').then(user => {
      this.obj.officer = user.fullName;
      console.log(this.obj.officer);
    });
  }



  onReport() {
    this.message.comfirm(confirm => {
      if (confirm) {
        const URL = "cop/cop092/updateFlag";
        this.ajax.post(URL, {"id":this.idUpdate}, response => {
          this.message.successModal(response.json().messageTh);
          this.router.navigate(["/cop09/1"]);
        }).catch(err => {
          this.message.errorModal("ไม่สามารถทำการบันทึกได้");
          console.error(err);
        });
      }
    }, "ยืนยันการบันทึกข้อมูล");

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
    this.router.navigate(["cop09/1"], {
      queryParams: {}
    });
  }



}

class data {
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
  buyInvoiceBox:string;
  buyInvoiceNum:string;
  buyInvoiceValue:string;
  buyInvoice:string;
  //radio4
  taxInvoiceBox:string;
  taxInvoiceNumNum:string
  taxInvoiceValue:string;
  taxInvoiceNum:string;

  

} 