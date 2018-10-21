import { Component, OnInit, AfterViewInit, ViewChild } from "@angular/core";
import { AjaxService, MessageBarService, AuthService } from 'app/buckwaframework/common/services';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { Utils } from "helpers/utils";
import { BreadCrumb } from 'models/breadcrumb';

declare var $: any;
@Component({
  selector: 'app-int06-10',
  templateUrl: './int06-10.component.html',
  styleUrls: ['./int06-10.component.css']
})
export class Int0610Component implements OnInit, AfterViewInit {

  activityList: any;
  budgetList : any;
  breadcrumb: BreadCrumb[]



  constructor(
    private authService: AuthService,
    private ajax: AjaxService,
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ทะเบียนคุมการรับจ่ายเงิน", route: "#" },
      { label: "ค้นหารายการขอเบิก", route: "#" },
    ];
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06100');
    this.calenda();
    this.activity();
    this.budge();
    this.dataTable();
  }
  ngAfterViewInit() {
    $(".ui.dropdown.ai").dropdown().css('width', '100%');
  }

  onSearch = () => {
    $("#searchFlag").val("TRUE");
    $("#dataTable").DataTable().ajax.reload();
  }

  onClear = () => {
    $("#budget").val("");
    $("#activity").val("");
    $("#dateForm").val("");
    $("#dateTo").val("");
    $("#searchFlag").val("FALSE");
    $("#dataTable").DataTable().ajax.reload();
  }

  activity = () => {
    let url = "ia/int0610/activity"
    this.ajax.get(url, res => {
      this.activityList = res.json();
    })    
  }

  budge = () => {
    let url = "ia/int0610/budge"
    this.ajax.get(url, res => {
      this.budgetList = res.json();
    })  
  }

  calenda = () => {
    $("#dateForm").calendar({    
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
     formatter: formatter()
    });
    $("#dateTo").calendar({    
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

  dataTable = () => {
    const table = $("#dataTable").DataTable({
      "serverSide": true,
      "searching": false,
      "processing": true,
      "ordering": false,
      "scrollX": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int0610/findAll',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
            "budget": $("#budget").val(),
            "activity": $("#activity").val(),
            "dateForm": $("#dateForm").val(),
            "dateTo": $("#dateTo").val(),
            "searchFlag": $("#searchFlag").val(),
          }));
        },
      },

      "columns": [
        {
          "data": "dateOfPay",
          "render": function (data, type, row, meta) {
          return meta.row + meta.settings._iDisplayStart + 1;
        },
          "className": "ui center aligned"
        },{
          "data": "refnum",
        "className": "ui center aligned"
        },{
          "data": "withdrawaldate",
        "className": "ui center aligned"
        },{
          "data": "budgettype",
        "className": "ui center aligned"
        },{
          "data": "activities",
        "className": "ui center aligned"
        },{
          "data": "budgetname",
        "className": "ui center aligned"
        },{
          "data": "categoryname",
        "className": "ui center aligned"
        },{
          "data": "listname",
        "className": "ui center aligned"
        },{
          "data": "itemdesc",
        "className": "ui center aligned"
        },{
          "data": "withdrawalamount",
        "className": "ui right aligned",
        "render": (data) => {
          return Utils.moneyFormat(data);
        }
        },{
          "data": "withholdingtax",
        "className": "ui right aligned",
        "render": (data) => {
          return Utils.moneyFormat(data);
        }
      },{
        "data": "socialsecurity",
      "className": "ui right aligned",
      "render": (data) => {
        return Utils.moneyFormat(data);
      }
      },{
        "data": "anotheramount",
      "className": "ui right aligned",
      "render": (data) => {
        return Utils.moneyFormat(data);
      }
      },{
        "data": "receivedamount",
      "className": "ui right aligned",
      "render": (data) => {
        return Utils.moneyFormat(data);
      }
      },{
        "data": "withdrawaldocnum",
      "className": "ui center aligned"
      },{
        "data": "paymentdocnum",
      "className": "ui center aligned"
      },{
        "data": "note",
      "className": "ui center aligned"
      }
      ]
    });
  }

}
