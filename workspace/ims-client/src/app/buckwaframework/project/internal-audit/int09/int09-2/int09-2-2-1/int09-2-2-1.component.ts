import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { MessageBarService } from './../../../../../common/services/message-bar.service';
import { Component, OnInit } from '@angular/core';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-2-1',
  templateUrl: './int09-2-2-1.component.html'
})
export class Int09221Component implements OnInit {

  constructor(private message:MessageBarService) { }

  calenda = function () {
    $("#year").calendar({
      maxDate: new Date(),
      type: "year",
      text: TextDateTH,
      formatter: formatter("ป")
    });
  }

  clickSearch = function(){
    $("#searchFlag").val("TRUE");
    $('#tableData').DataTable().ajax.reload();
  }
  
  clickClear = function(){
    $("#money").val("");  
    $("#searchFlag").val("FALSE");

    $("#department").prop('selectedIndex',0);    
    $("#department").dropdown('clear');
    
    $('#tableData').DataTable().ajax.reload();
  }
  clickDelete = () => {
    if($('#tableData').DataTable().rows().count() == 0 ){
      this.message.alert("ไม่มีข้อมูล")
      return false
    }
    this.message.comfirm((res) => {
      if(res){
        console.log("top");
      }
      
    },"ลบรายการ");
  }


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

  dataTable = function(){
    $("#doctype").val() == 0 ? "":$("doctype").val();
    var table = $('#tableData').DataTable({
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,      
      "ajax" : {
        "url" : '/ims-webapp/api/ia/int09211/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" :function(d) {
          return JSON.stringify($.extend({}, d, {
            "money" : $("#money").val(),
            "department" : $("#department").val(),
            "searchFlag" : $("#searchFlag").val()
          }));
        },  
      },
          
      "columns": [
        {
          "data": "department",
          "render": function (data, type, row, meta) {
            return '<input type="checkbox">';
          },
          "className": "ui center aligned"
        },{
          "data": "department",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          "className": "ui center aligned"
        }, {
          "data": "department"
        }, {
          "data": "updatedDate",
          "className": "ui center aligned"
        }, {
          "data": "createdBy",
          "className": "ui center aligned"
        }, {
          "data": "createdBy",
          "render" : function(data,type,row){
            var btn = '<button class="ui mini primary button btn-edit">รายละเอียด</button>';
              return btn;
          },
          "className": "ui center aligned"
        }
      ]
    });

    //button edit
    table.on('click', 'tbody tr button.btn-edit', function () {
			var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();      
      console.log(data);
     
    });
  
  }

  ngOnInit() {
  }
  ngAfterViewInit() {
    $('.ui.dropdown').dropdown();
    this.calenda();
    this.dataTable();

    
  }
}
