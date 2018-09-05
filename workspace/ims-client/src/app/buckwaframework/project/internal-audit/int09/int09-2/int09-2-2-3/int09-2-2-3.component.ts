import { AjaxService } from './../../../../../common/services/ajax.service';
import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';
import { Router ,ActivatedRoute} from '@angular/router';


declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-2-3',
  templateUrl: './int09-2-2-3.component.html'
})
export class Int09223Component implements OnInit {

  allowanceDate;
  allowanceCost;
  rentDate;
  rentCost;
  travelCost;
  
  typeDropdown : any;
  trainingCaseDropdown: any;
  withdrawTypeDropdown: any;
  trainingRoomTypeDropdown: any;
  gradeDropdown : any;
  dataDropdown : any;

  idProcessHead : any;
  stateAgencyNameHead : any;
  thosePickedHead : any;
  fiscalYearInHead : any;
  
  constructor( private ajax:AjaxService,private route:ActivatedRoute) {
    this.allowanceDate = 0;
    this.allowanceCost = 0;
    this.rentDate = 0;
    this.rentCost = 0;
    this.travelCost = 0;
  }

  setValue = ()=>{
  let data = this.route.snapshot.queryParams["fiscalYear"].split("/");
  let m = parseInt(data[1])-1;
  this.idProcessHead = this.route.snapshot.queryParams["idProcess"];
   this.stateAgencyNameHead = this.route.snapshot.queryParams["stateAgencyName"];
   this.thosePickedHead = this.route.snapshot.queryParams["thosePicked"];
   this.fiscalYearInHead = data[0]+" "+TextDateTH.months[m]+" "+data[2];
  }

  calenda = function () {
    $("#dateOfApplication").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateOfWithdrawal").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#departureDate").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#returnDate").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }
  clickCheckAll = event => {
    if (event.target.checked) {
      var node = $('#tableData').DataTable().rows().nodes();
      $.each(node, function (index, value) {
        $(this).find('input').prop('checked', true);
      });
    } else {
      var node = $('#tableData').DataTable().rows().nodes();
      $.each(node, function (index, value) {
        $(this).find('input').prop('checked', false);
      });
    }
  }

  typeDropdownList =() =>{

    this.dataDropdown = {
      lovIdMaster : "306"
    }
    const URL =  "ia/int09223/listDropdown";
  
     this.ajax.post(URL, this.dataDropdown, res => {   
       this.typeDropdown = res.json();
    });
  }
  typeDropdownOnChange = event => {

    let id = $("#type").val();
    this.dataDropdown = {
      lovIdMaster : id
    }
     const URL =  "ia/int09223/listDropdown";
  
     this.ajax.post(URL, this.dataDropdown, res => {     
       console.log(res.json());
       this.gradeDropdown = res.json();
    });

  }
  trainingCaseDropdownList =() =>{

    this.dataDropdown = {
      lovIdMaster : "337"
    }
    const URL =  "ia/int09223/listDropdown";
  
     this.ajax.post(URL, this.dataDropdown, res => {   
       this.trainingCaseDropdown = res.json();
    });
  }

  withdrawTypeDropdownList =() =>{

    this.dataDropdown = {
      lovIdMaster : "336"
    }
    const URL =  "ia/int09223/listDropdown";
  
     this.ajax.post(URL, this.dataDropdown, res => {   
       this.withdrawTypeDropdown = res.json();
    });
  }
  

  trainingRoomTypeDropdownList = event => {
    let id = $("#withdrawType").val();
    console.log("withdrawType : ",$("#withdrawType").val());
    this.dataDropdown = {
      lovIdMaster : id
    }
     const URL =  "ia/int09223/listDropdown2";
  
     this.ajax.post(URL, this.dataDropdown, res => {     
       console.log(res.json());
       this.trainingRoomTypeDropdown = res.json();
    });
  }


  dataTable = function () {
    var table = $('#tableData').DataTable({
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int09213/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "idProcess" : $("#idProcess").val(),
            "searchFlag" : $("#searchFlag").val()
          }));
        },  
      },

      "columns": [
        {
          "data": "workSheetDetailId",
          "render": function (data, type, row, meta) {
            return '<input type="checkbox">';
          },
          "className": "ui center aligned"
        }, {
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
        }, {
          "data": "allowanceCost",
          "className": "ui center aligned"
        }, {
          "data": "rentDate",
          "className": "ui center aligned"
        }, {
          "data": "rentCost",
          "className": "ui center aligned"
        }, {
          "data": "travelCost",
          "className": "ui center aligned"
        }, {
          "data": "otherCost",
          "className": "ui center aligned"
        },
        {
          "data": "sumCost",
          "clasName": "ui center aligned"
        }, {
          "data": "note",
          "className": "ui center aligned"
        }, {
          "data": "note",
          "render": function (data, type, row) {
            var btn = '<button type="button" class="mini ui yellow button btn-edit">แก้ไข</button>';
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
      $("#name").val(data.name);
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
    this.setValue();
  }
  ngAfterViewInit() {
   
    this.dataTable();
    $('.ui.dropdown').dropdown();
    this.typeDropdownList();
    this.trainingCaseDropdownList();
    this.withdrawTypeDropdownList();
    this.calenda();

  }

}
