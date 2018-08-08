import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-1-3',
  templateUrl: './int09-2-1-3.component.html'
})
export class Int09213Component implements OnInit {

  allowanceDate;
  allowanceCost;
  rentDate;
  rentCost;
  travelCost;

  hideA: boolean = false;

  constructor() { 
    this.allowanceDate = 0;
    this.allowanceCost = 0;
    this.rentDate = 0;
    this.rentCost = 0;
    this.travelCost = 0;
  }

  typeRoom = function() {
    var id = $("#typeWithdrawal").val();
  //  e.target.value == 1 || e.target.value == "" ?  this.hideA = false :  this.hideA = true;
   if( id==0 || id==2 || id==3 ){
    $("#typeRoomLabel").show();
    $("#typeRoomValue").show(function(){
      $(".ui.dropdown").dropdown();
    });
   }else{
    $("#typeRoomLabel").hide();
    $("#typeRoomValue").hide();
   }
   
  }

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
      type: "datetime",
      text: TextDateTH,
      formatter : formatter("วดปเวลา")
    });
    $("#endGoDate").calendar({
      maxDate: new Date(),
      type: "datetime",
      formatter : formatter("วดปเวลา")
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
            var btn = '<button type="button" class="ui yellow button btn-edit">แก้ไข</button>';
              return btn;
          },
          "className": "ui center aligned"
        }
      ],
      "footerCallback": (row, data, start, end, display) => {
        console.log($(row));
        data.map(val => {
          this.allowanceDate += val.allowanceDate;
          this.allowanceCost += val.allowanceCost;
          this.rentDate += val.rentDate;
          this.rentCost += val.rentCost;
          this.travelCost += val.travelCost;
        });
        $(row)[0].childNodes[1].innerHTML = this.allowanceDate;
        $(row)[0].childNodes[2].innerHTML = this.allowanceCost;
        $(row)[0].childNodes[3].innerHTML = this.rentDate;
        $(row)[0].childNodes[4].innerHTML = this.rentCost;
        $(row)[0].childNodes[5].innerHTML = this.travelCost;        
      },
    });

    //button edit
     table.on('click', 'tbody tr button.btn-edit', function () {
			var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();      
      console.log(data);

      //set date on form
      $("#idEdit").val(data.workSheetDetailId);      
      $("#prefix").val(data.workSheetDetailId);
      $("#name").val(data.name );
      $("#last").val(data.lastName);
      $("#position").val(data.position);
      $("#type").val(data.category);
      $("#level").val(data.degree);
      $("#appoveDate").val(data.workSheetDetailId);
      $("#withdrawDate").val(data.workSheetDetailId);
      $("#goFrom").val(data.workSheetDetailId);
      $("#food").val(data.workSheetDetailId);
      $("#startGoDate").val(data.workSheetDetailId);
      $("#endGoDate").val(data.workSheetDetailId);
      $("#numberLive").val(data.workSheetDetailId);
      $("#typeWithdrawal").val(data.workSheetDetailId);
      $("#typeRoom").val(data.workSheetDetailId);
      $("#note").val(data.workSheetDetailId);


     
    }); 
  
  }

  ngOnInit() {
  }
  ngAfterViewInit() {
    $("#typeRoomLabel").hide();
    $("#typeRoomValue").hide();
    this.dataTable();
    $('.ui.dropdown').dropdown();

    this.calenda();
    
  }

}
