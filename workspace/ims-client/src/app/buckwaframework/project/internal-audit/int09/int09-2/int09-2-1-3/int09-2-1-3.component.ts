import { MessageBarService } from './../../../../../common/services/message-bar.service';
import { AjaxService } from './../../../../../common/services/ajax.service';
import { ExciseService } from './../../../../../common/services/excise.service';
import { TextDateTH, formatter, stringToDate } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

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
  hesderTxt;
  hideA: boolean = false;
  typeDropdown: any;
  levelDropdown: any;
  typeWithdrawal: any;
  dataDropdown: any;


  constructor(
    private exciseService: ExciseService,
    private ajax: AjaxService,
    private message : MessageBarService
  ) {
    this.allowanceDate = 0;
    this.allowanceCost = 0;
    this.rentDate = 0;
    this.rentCost = 0;
    this.travelCost = 0;
    this.hesderTxt = {
      startDateInput: "",
      endDateInput: "",
      sector: "",
      area: "",
      branch: "",
      department: ""
    };
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
      formatter: formatter("วดปเวลา")
    });
    $("#endGoDate").calendar({
      maxDate: new Date(),
      type: "datetime",
      formatter: formatter("วดปเวลา")
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

  typeRoom =  () => {
    var id = $("#typeWithdrawal").val();
    if (id == 0 || id == 2 || id == 3) {
      $("#typeRoomLabel").show();
      $("#typeRoomValue").show(function () {
        $(".ui.dropdown").dropdown();
      });
    } else {
      $("#typeRoomValue").va("");
      $("#typeRoomLabel").hide();
      $("#typeRoomValue").hide();
    }

  }

  caseDropdownChange = (e) => {
    $("#type").dropdown('restore defaults');

    if(e.target.value=="306"){
      $("#lebeltypeWithdrawal").show();
      $("#selecttypeWithdrawal").show();
      $("#typeRoomLabel").show();
      $("#typeRoomValue").show();
      this.setDataypeWithdrawal(e.target.value);
    }else{
      $("#typeWithdrawal").dropdown('restore defaults');      
      $("#lebeltypeWithdrawal").hide();
      $("#selecttypeWithdrawal").hide();

      $("#typeRoom").dropdown('restore defaults');  
      $("#typeRoomLabel").hide();
      $("#typeRoomValue").hide();
    }
    

    this.dataDropdown = { lovIdMaster:e.target.value }
    const URL = "ia/int09213/listDropdown";
    this.ajax.post(URL, JSON.stringify(this.dataDropdown), res => {
      console.log("Response : ",res.json());
      this.typeDropdown = res.json();
    });
  }

  typeDropdownChange = (e) => {
    $("#level").dropdown('restore defaults');
    let id = e.target.value;
    this.dataDropdown = {
      lovIdMaster: id
    }
    const URL = "ia/int09213/listDropdown";

    this.ajax.post(URL, this.dataDropdown, res => {
      console.log(res.json());
      this.levelDropdown = res.json();
    });

  }

  setDataypeWithdrawal = (data) => {
    let _value = "";
    let _label = "";
    if(data=="306"){
      _value = data;
      _label = "ปกติ";
    }

    let dataJson = [
      {
        label : _label,
        value : _value
      },{
        label : "การฝึกอบรมประเภท ก",
        value : "337"
      },{
        label : "การฝึกอบรมประเภท ข",
        value : "338"
      }
    ]
    
    this.typeWithdrawal = dataJson;
  }

  setSession = function () {
    $.ajax({
      url: "/ims-webapp/api/ia/int09213/setSession",
      contentType: "application/json",
      type: "GET",
      data: function (d) {
        return JSON.stringify($.extend({}, d, {
        }));
      },
      success: function (data) {
        console.log("set session seccess.");
      }
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
        "type": "GET",
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

  onSubmit = (e) => {
    //not reload page
    e.preventDefault();

    //validate form
    let prefix = e.target.prefix.value
    if (prefix == "1") prefix = "นาย";
    if (prefix == "2") prefix = "นางสาว";
    if (prefix == "3") prefix = "นาง";
    
    //json data
    let data = {
      prefix: prefix,
      name: e.target.name.value,
      last: e.target.last.value,
      position: e.target.position.value,
      type: e.target.type.value,
      level: e.target.level.value,
      appoveDateData: e.target.appoveDateData.value,
      withdrawDateData: e.target.withdrawDateData.value,
      goFrom: e.target.goFrom.value,
      food: e.target.food.value,
      startGoDateData: e.target.startGoDateData.value,
      endGoDateData: e.target.endGoDateData.value,
      numberLive: e.target.numberLive.value,
      typeWithdrawal: e.target.typeWithdrawal.value,
      typeRoom: e.target.typeRoom.value,
      note: e.target.note.value,
      days : this.momentDiff(e.target.startGoDateData.value,e.target.endGoDateData.value)
    }
    console.log(JSON.stringify(data));
    //post data
    const URL = "ia/int09213/addData";
    this.ajax.post(URL,JSON.stringify(data), res => {
      console.log(res.json());
      $('#tableData').DataTable().ajax.reload();
      this.message.successModal("ทำรายสำเร็จ","แจ้งเตือน");
    });


  }

  momentDiff = (start,end) => {

      let _start = stringToDate(start);
      let _end = stringToDate(end);
      let momentStart = moment(_start);
      let momentEnd = moment(_end);

      return momentEnd.diff(momentStart, 'days',true);

  } 

  ngOnInit() {
    this.setSession();
    this.hesderTxt = this.exciseService.getData() != undefined && this.exciseService.getData();
  }
  ngAfterViewInit() {
    
    $("#lebeltypeWithdrawal").hide();
    $("#selecttypeWithdrawal").hide();
    
    $("#typeRoomLabel").hide();
    $("#typeRoomValue").hide();
    this.dataTable();
    $('.ui.dropdown').dropdown();
    this.calenda();

  }

}
