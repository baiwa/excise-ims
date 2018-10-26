import { Component, OnInit, AfterViewInit, OnDestroy } from "@angular/core";
import {
  TravelCostHeader,
  TravelCostDetail,
  Contract
} from "../../../../../common/models";
import { AjaxService, MessageBarService, AuthService } from "../../../../../common/services";
import { Prices } from "../../../../../common/helper/travel";
import { Router, ActivatedRoute } from "@angular/router";
import {
  digit,
  numberWithCommas,
  TextDateTH,
  formatter,
  DecimalFormat
} from "../../../../../common/helper";
import { TravelService } from "../../../../../common/services/travel.service";
import { Headers } from "@angular/http/src/headers";
import { File } from "app/buckwaframework/common/models/file";
import { BreadCrumb } from 'models/index';

declare var $: any;
@Component({
  selector: "app-cop07-1-1",
  templateUrl: "./cop07-1-1.component.html",
  styleUrls: ["./cop07-1-1.component.css"]
})
export class Cop0711Component implements OnInit, OnDestroy {

  searchFlag: String;
  fileUpload:File[];

  id:any;
  fiscalYear:any;
  asPlanNumber:any;
  outsidePlanNumber:any;

  breadcrumb: BreadCrumb[];


  constructor(
    private message: MessageBarService,
    private ajax: AjaxService,
    private router: Router,
    private authService: AuthService,
    private route: ActivatedRoute,
    private travelService: TravelService
  ) {
    this.breadcrumb = [
      { label: "ตรวจปฏิบัติการ", route: "#" },
      { label: "แผนการตรวจปฏิบัติการประจำปีงบประมาณ", route: "#" },
      { label: "รายละเอียดแผนปฏิบัติการ", route: "#" }
 
    ];

    this.fileUpload = new Array<File>(); // initial file array
  }

  
  ngOnInit() {
    this.authService.reRenderVersionProgram('COP-07110');

    this.id = this.route.snapshot.queryParams["id"];
    this.fiscalYear = this.route.snapshot.queryParams["fiscalYear"];
    this.asPlanNumber = this.route.snapshot.queryParams["asPlanNumber"];
    this.outsidePlanNumber = this.route.snapshot.queryParams["outsidePlanNumber"];

    this.getHead();
    this.dataTable();
    this.dataTable2();
  }

  ngOnDestroy() {
    $('#modalAddDocument').remove();
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
        "url" : '/ims-webapp/api/ia/int0911/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : "TRUE",
            "idProcess" : this.idProcess
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
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdDate","className": "ui center aligned"
        },{
          "data": "id",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var btn = '';
            btn +='<button class="mini ui red button btn-delete"><i class="trash alternate icon"></i>ลบ</button>';
              return btn;
          }
        }
      ]
    });

    //button delete>
    table.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table.row(closestRow).data();
     
      const URL = "ia/int0911/delete";
      this.message.comfirm((res) => {
        if(res){
      
      this.ajax.post(URL, {id:data.id}, res => {        
        const msg = res.json();
      if (msg.messageType == "C") {
        this.message.successModal(msg.messageTh);
      } else {
        this.message.errorModal(msg.messageTh);
      }
      $("#searchFlag").val("TRUE");
      $('#tableData').DataTable().ajax.reload();
      });
     }
    },"ลบรายการ");
      
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
        "url" : '/ims-webapp/api/ia/int0911/list2',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : "TRUE",
            "idProcess" : this.idProcess
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
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdDate","className": "ui center aligned"
        },{
          "data": "id",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var btn = '';
            btn +='<button class="mini ui red button btn-delete"><i class="trash alternate icon"></i>ลบ</button>';
              return btn;
          }
        }
      ]
    });

    //button delete>
    table2.on('click', 'tbody tr button.btn-delete', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = table2.row(closestRow).data();
     
      const URL = "ia/int0911/deleteT2";
      this.message.comfirm((res) => {
        if(res){
      
      this.ajax.post(URL, {id:data.id}, res => {        
        const msg = res.json();
      if (msg.messageType == "C") {
        this.message.successModal(msg.messageTh);
      } else {
        this.message.errorModal(msg.messageTh);
      }
      $("#searchFlag").val("TRUE");
      $('#tableData2').DataTable().ajax.reload();
      });
     }
    },"ลบรายการ");
      
    });
    
  }
  modalAddDocument (){
    $('#modalAddDocument').modal('show');
  }

  clickBack(){
    this.router.navigate(['/cop07/1']);
  }

  getHead = () =>{
  
 
  }

}