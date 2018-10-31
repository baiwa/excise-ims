import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter ,stringToDate} from "../../../../common/helper/datepicker";
import { AjaxService, MessageBarService, AuthService } from "../../../../common/services";
import { TravelCostHeader } from "../../../../common/models";
import { Router, ActivatedRoute } from "@angular/router";
import { TravelCostDetail } from "app/buckwaframework/common/models/travelcostdetail";
import { IaService } from 'app/buckwaframework/common/services/ia.service';
import { BreadCrumb } from 'models/index';


declare var $: any;
@Component({
  selector: "app-cop09-1",
  templateUrl: "./cop09-1.component.html",
  styleUrls: ["./cop09-1.component.css"]
})
export class Cop091Component implements OnInit {

  searchFlag: String;
  breadcrumb: BreadCrumb[];

  totalAsPlanNumber:Number=0;
  totalAsPlanSuccess:Number=0;
  totalAsPlanWait:Number=0;
  totalOutsidePlanNumber:Number=0;
  totalOutsidePlanSuccess:Number=0;
  totalOutsidePlanWait:Number=0;   

  constructor(
    private message: MessageBarService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private iaService: IaService,
    private msg: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจปฏิบัติการ", route: "#" },
      { label: "สรุปผลการตรวจปฏิบัติการ", route: "#" }
    ];
    
   }
  calenda = function () {
   
    $("#date").calendar({
      type: "year",
      text: TextDateTH,
      formatter: formatter("ป")
    });

  }

  clickSearch = function () {
    if ($("#fiscalYear").val()=="") {
      this.message.alert("กรุณาระบุ แผนการตรวจปฏิบัติการประจำปีงบประมาณ");
      return false;
    }

    $("#searchFlag").val("TRUE");
    $('#tableData').DataTable().ajax.reload();
    $('#tableData2').DataTable().ajax.reload();
  }

  dataTable = function(){
    var table = $('#tableData').DataTable({
      "lengthChange":true,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,      
      "ajax" : {
        "url" : '/ims-webapp/api/cop/cop091/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : $("#searchFlag").val(),
            "fiscalYear" : $("#fiscalYear").val()
          }));
        },  
      },
      "columns": [
        {
          "data": "id",
          "className": "ui center aligned",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          "data": "entrepreneurNo","className": "ui center aligned"
        }, {
          "data": "entrepreneurName"
        }, {
          "data": "entrepreneurLoca"
        }, {
          "data": "checkDate","className": "ui center aligned"
        }, {
          "data": "actionPlan","className": "ui center aligned",
          "render": function (data, type, row, meta) {
            let s = '';
            if(data=='1871'){
              s = 'ตามแผนปฏิบัติการ';
            }else{
              s = 'นอกแผนปฏิบัติการ';
            }
            return s;
          }
        }, {
          "data": "status","className": "ui center aligned",
          "render": function (data, type, row, meta) {
            let s = '';
            if(data=='1874'){
              s = 'เสร็จสิ้น';
            }else{
              s = 'รอการดำเนินการ';
            }
            return s;
          }
        }
      ]
    });
  }

  dataTable2 = function(){
    var table2 = $('#tableData2').DataTable({
      "lengthChange":true,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,      
      "ajax" : {
        "url" : '/ims-webapp/api/cop/cop091/list2',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : $("#searchFlag").val(),
            "fiscalYear" : $("#fiscalYear").val()
          }));
        },  
      },
      "columns": [
        {
          "data": "id",
          "className": "ui center aligned",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          "data": "entrepreneurNo","className": "ui center aligned"
        }, {
          "data": "entrepreneurName"
        }, {
          "data": "entrepreneurLoca"
        }, {
          "data": "checkDate","className": "ui center aligned"
        }, {
          "data": "actionPlan","className": "ui center aligned",
          "render": function (data, type, row, meta) {
            let s = '';
            if(data=='1871'){
              s = 'ตามแผนปฏิบัติการ';
            }else{
              s = 'นอกแผนปฏิบัติการ';
            }
            return s;
          }
        }, {
          "data": "status","className": "ui center aligned",
          "render": function (data, type, row, meta) {
            let s = '';
            if(data=='1874'){
              s = 'เสร็จสิ้น';
            }else{
              s = 'รอการดำเนินการ';
            }
            return s;
          }
        }
      ]
    });    
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('COP-09100');
    
    this.dataTable();
    this.dataTable2();
    this.calenda(); 

    // if(this.route.snapshot.queryParams["fiscalYear"]){
    //   $("#fiscalYear").val(this.route.snapshot.queryParams["fiscalYear"]);
    //   this.clickSearch();
    // }
    
  }

}
