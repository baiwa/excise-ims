import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-1-3',
  templateUrl: './int09-2-1-3.component.html'
})
export class Int09213Component implements OnInit {

  constructor() { }

  calenda = function () {
    $("#appoveDate").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#withdrawDate").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#startGoDate").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#endGoDate").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
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
    var table = $('#tableData').DataTable({
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,      
      "ajax" : {
        "url" : '/ims-webapp/api/ia/int09213/list',
        "contentType": "application/json",
        "type" : "GET",
      },
          
      "columns": [
        {
          "data": "workSheetDetailId",
           "render": function (data, type, row, meta) {
            return '<input type="checkbox">';
          },
          "className": "ui center aligned" 
        },{
          "data": "workSheetDetailId",
           "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          "className": "ui center aligned" 
        }, {
          "data": "name"
        }, {
          "data": "position",
          "className": "ui center aligned"
        }, {
          "data": "allowanceDate",
          "className": "ui center aligned"
        },{
          "data": "allowanceCost",
          "className": "ui center aligned"
        },{
          "data": "rentDate",
          "className": "ui center aligned"
        },{
          "data": "rentCost",
          "className": "ui center aligned"
        },{
          "data": "travelCost",
          "className": "ui center aligned"
        },{
          "data": "otherCost",
          "className": "ui center aligned"
        },
        {
          "data": "sumCost",
          "clasName": "ui center aligned"
        },{
          "data": "note",
          "className": "ui center aligned"
        }, {
          "data": "note",
          "render" : function(data,type,row){
            var btn = '<a class="btn-edit">รายละเอียด</a>';
              return btn;
          },
          "className": "ui center aligned"
        }
      ]
    });

    //button edit
     table.on('click', 'tbody tr a.btn-edit', function () {
			var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();      
      console.log(data);
     
    }); 
  
  }

  ngOnInit() {
  }
  ngAfterViewInit() {
    this.dataTable();
    $('.ui.dropdown').dropdown();

    this.calenda();
    
  }

}
