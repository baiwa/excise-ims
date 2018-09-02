import { Component, OnInit, AfterViewInit, OnDestroy } from "@angular/core";
import {
  TravelCostHeader,
  TravelCostDetail,
  Contract
} from "../../../../../common/models";
import { AjaxService, MessageBarService } from "../../../../../common/services";
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
declare var $: any;
@Component({
  selector: "app-int09-1-1",
  templateUrl: "./int09-1-1.component.html",
  styleUrls: ["./int09-1-1.component.css"]
})
export class Int0911Component implements OnInit, OnDestroy {

  searchFlag: String;
  documentTypeList:any;

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private travelService: TravelService
  ) { }

  getDocumentTypeList (){
    this.documentTypeList = [
      {value:1,label:"ประมาณการค่าใช้จ่าย"},
      {value:2,label:"สัญญาการยืมเงิน"},
      {value:3,label:"หลักฐานการจ่ายเงิน"},
      {value:4,label:"ใบเบิกค่าใช้จ่ายในการเดินทางไปราชการ"},
      {value:5,label:"บันทึกข้อความ"}]
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
        "url" : '/ims-webapp/api/ia/int091/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : "TRUE"
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
          "data": "createdDate",
          "className": "ui center aligned"
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "นายนพรัช  โพนทอง";
          }
        }, {
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "บันทึกข้อความ";
          }
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "ขออนุมัติเดินทางไปราชการ";
          }
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "อนุมัติ";
          }
        },{
          "data": "id",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var btn = '';
            btn +='<button class="mini ui primary button btn-edit">ดาวน์โหลด</button>';
            btn +='<button class="mini ui red button btn-edit">ยกเลิก</button>';
              return btn;
          }
        }
      ]
    });

    //button edit>
    table.on('click', 'tbody tr button.btn-edit', ()=> {
			var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();   
      this.router.navigate(['/int09/2-2-3']);
      console.log(data);
     
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
        "url" : '/ims-webapp/api/ia/int091/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : "TRUE"
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
          "data": "createdDate",
          "className": "ui center aligned"
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "นายนพรัช  โพนทอง";
          }
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "ใบเสร็จรับเงินค่าที่พักโรงแรมห้าดาว";
          }
        }, {
          "data": "createdBy",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var s = '15';
              return s;
          }
        },{
          "data": "id",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var btn = '';
            btn +='<button class="mini ui primary button btn-edit">ดาวน์โหลด</button>';
            btn +='<button class="mini ui red button btn-edit">ยกเลิก</button>';
              return btn;
          }
        }
      ]
    });

    //button edit>
    table2.on('click', 'tbody tr button.btn-edit', ()=> {
			var closestRow = $(this).closest('tr');
      var data = table2.row(closestRow).data();   
      this.router.navigate(['/int09/2-2-3']);
      console.log(data);
     
    });
    
    
  
  }
  modalAddDocument (){
    $('#modalAddDocument').modal('show');
  }
  addDocument (){
   console.log("Add Document : True");
   $('#modalAddDocument').modal('hide');
    if($('#documentType').val()==1){
      this.router.navigate(['/int09/1/1/1']);
    }else if($('#documentType').val()==2){
      this.router.navigate(['/int09/1/1/2']);
    }else if($('#documentType').val()==3){
      this.router.navigate(['/int09/1/1/3']);
    }else if($('#documentType').val()==4){
      this.router.navigate(['/int09/1/1/4']);
    }else if($('#documentType').val()==5){
      this.router.navigate(['/int09/1/1/5']);
    }
   
  }
  clickBack(){
    this.router.navigate(['/int09/1']);
  }

  ngOnDestroy() {
    $('#modalAddDocument').remove();
  }
  
  ngOnInit() {
    this.getDocumentTypeList();
    this.dataTable();
    this.dataTable2();
  }
  
}
