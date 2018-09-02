import { Component, OnInit, AfterViewInit } from "@angular/core";
import {
  TravelCostHeader,
  TravelCostDetail,
  Contract
} from "../../../../../../common/models";
import { AjaxService, MessageBarService } from "../../../../../../common/services";
import { Prices } from "../../../../../../common/helper/travel";
import { Router, ActivatedRoute } from "@angular/router";
import {
  digit,
  numberWithCommas,
  TextDateTH,
  formatter,
  DecimalFormat
} from "../../../../../../common/helper";
import { TravelService } from "../../../../../../common/services/travel.service";
declare var $: any;
@Component({
  selector: "app-int09-1-1-1",
  templateUrl: "./int09-1-1-1.component.html",
  styleUrls: ["./int09-1-1-1.component.css"]
})
export class Int09111Component implements OnInit, AfterViewInit {


  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private travelService: TravelService
  ) {  }
  calenda = function () {
    $("#date").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#date2").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
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
          "render": function (data, type, row, meta) {
            return '<input type="checkbox">';
          },
          "className": "ui center aligned"
        },{
          "data": "id",
          "className": "ui center aligned",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "นายนพรัช  โพนทอง";
          }
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "นักวิชาการตรวจสอบภายในชำนาญการพิเศษ";
          }
        }, {
          "data": "createdDate",
          "className": "ui right aligned",
          "render": function (data, type, row, meta) {
            return "12";
          }
        }, {
          "data": "createdBy",
          "className": "ui right aligned",
          "render": function (data, type, row, meta) {
            return "12,000";
          }
        }, {
          "data": "createdDate",
          "className": "ui right aligned",
          "render": function (data, type, row, meta) {
            return "12";
          }
        }, {
          "data": "createdBy",
          "className": "ui right aligned",
          "render": function (data, type, row, meta) {
            return "12,000";
          }
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "1,000";
          }
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "1,000";
          }
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return "26,000";
          }
        }, {
          "data": "createdBy",
          "render": function (data, type, row, meta) {
            return " ";
          }
        },{
          "data": "id",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var btn = '';
            btn +='<button class="mini ui primary button btn-edit">แก้ไข</button>';
              return btn;
          }
        }
      ]
    });

    //button edit>
    table.on('click', 'tbody tr button.btn-edit', ()=> {
			var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();  
      this.modalAdd();
      console.log(data);
     
    });
  }
  modalAdd (){
    $('#modalAdd').modal('show');
  }
  saveData (){
    console.log("Save Data : True");
     $('modalAdd').modal('hide');
   }

   modalAddHead (){
    $('#modalAddHead').modal('show');
  }
  saveHead (){
    console.log("Save Head : True");
     $('#modalAddHead').modal('hide');
   }
  //  hideModal (){
  //   $('#modalAddHead').modal('hide');
  //   $('#modalAdd').modal('hide');
  //  }

   clickCheckAll = event=>{
    if (event.target.checked) {
      var node =  $('#tableData').DataTable().rows().nodes();
       $.each(node, function (index, value) {
        $(this).find('input').prop('checked',true);
       });
    }else{
      var node =  $('#tableData').DataTable().rows().nodes();
       $.each(node, function (index, value) {
        $(this).find('input').prop('checked',false); 
       });
    }
  }
  
  clickBack(){
    this.router.navigate(['/int09/1/1']);
  }


  ngOnInit() {
    this.dataTable();
    this.calenda();
    // this.hideModal();
  }
  

  ngAfterViewInit() {

  }

 
  
}
