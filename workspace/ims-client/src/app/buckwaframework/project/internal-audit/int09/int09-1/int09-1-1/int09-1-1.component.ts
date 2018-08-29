import { Component, OnInit, AfterViewInit } from "@angular/core";
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
export class Int0911Component implements OnInit {

  searchFlag: String;
  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private travelService: TravelService
  ) { }

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
          "data": "createdBy"
        }, {
          "data": "createdDate","className": "ui center aligned"
        }, {
          "data": "createdBy"
        }, {
          "data": "createdBy"
        }, {
          "data": "createdBy"
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
          "data": "createdBy"
        }, {
          "data": "createdBy"
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
  }

  ngOnInit() {
    this.dataTable();
    this.dataTable2();
  }
  
}
