import { MessageBarService } from './../../../../../common/services/message-bar.service';
import { AjaxService } from './../../../../../common/services/ajax.service';
import { ExciseService } from './../../../../../common/services/excise.service';
import { TextDateTH, formatter, stringToDate } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { DecimalFormat } from '../../../../../common/helper';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-1-3',
  templateUrl: './int09-2-1-3.component.html'
})
export class Int09213Component implements OnInit {

  table: any;
  allowanceDate;
  allowanceCost;
  rentDate;
  rentCost;
  travelCost;
  otherCost;
  sumCost;
  hesderTxt;
  hideA: boolean = false;
  typeDropdown: any;
  levelDropdown: any;
  typeWithdrawal: any;
  dataDropdown: any;
  travelCostDetail: Detail[];


  constructor(
    private exciseService: ExciseService,
    private ajax: AjaxService,
    private message: MessageBarService
  ) {
    this.allowanceDate = 0;
    this.allowanceCost = 0;
    this.rentDate = 0;
    this.rentCost = 0;
    this.travelCost = 0;
    this.otherCost = 0;
    this.sumCost = 0;
    this.hesderTxt = {
      startDateInput: "",
      endDateInput: "",
      sector: "",
      area: "",
      branch: "",
      department: ""
    };
    this.travelCostDetail = [];
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

  typeRoom = () => {
    var id = $("#typeWithdrawal").val();
    if (id == 0 || id == 2 || id == 3) {
      $("#typeRoomLabel").show();
      $("#typeRoomValue").show(function () {
        $(".ui.dropdown").dropdown();
      });
    } else {
      $("#typeRoomValue").val("");
      $("#typeRoomLabel").hide();
      $("#typeRoomValue").hide();
    }

  }

  caseDropdownChange = (e) => {
    $("#type").dropdown('restore defaults');

    if (e.target.value == "306") {
      $("#lebeltypeWithdrawal").show();
      $("#selecttypeWithdrawal").show();
      $("#typeRoomLabel").show();
      $("#typeRoomValue").show();
      this.setDataypeWithdrawal(e.target.value);
    } else {
      $("#typeWithdrawal").dropdown('restore defaults');
      $("#lebeltypeWithdrawal").hide();
      $("#selecttypeWithdrawal").hide();

      $("#typeRoom").dropdown('restore defaults');
      $("#typeRoomLabel").hide();
      $("#typeRoomValue").hide();
    }


    this.dataDropdown = { lovIdMaster: e.target.value }
    const URL = "ia/int09213/listDropdown";
    this.ajax.post(URL, JSON.stringify(this.dataDropdown), res => {
      console.log("Response : ", res.json());
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
    if (data == "306") {
      _value = data;
      _label = "ปกติ";
    }

    let dataJson = [
      {
        label: _label,
        value: _value
      }, {
        label: "การฝึกอบรมประเภท ก",
        value: "337"
      }, {
        label: "การฝึกอบรมประเภท ข",
        value: "338"
      }
    ]

    this.typeWithdrawal = dataJson;
  }

  DF(what) {
    const df = new DecimalFormat("###,###.00");
    return df.format(what);
  }
  dataTable = () => {
    this.table = $('#tableData').DataTable({
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
          "data": "name",
          "render": (data, type, row) => {
            return row.prefix + " " + row.name + " " + row.lastName;
          }
        }, {
          "data": "position",
          "className": "ui center aligned"
        }, {
          "data": "allowanceDateDesc",
          "className": "ui center aligned"
        }, {
          "data": "allowanceCost",
          "className": "ui center aligned"
        }, {
          "data": "rentDate",
          "className": "ui center aligned"
        }, {
          "data": "rentCost",
          "className": "ui center aligned",
          "render": (data, type, row) => {
            return this.DF(data);
          }
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
            return '<button type="button" class="ui yellow button btn-edit"><i class="edit icon"></i></button>';

          },
          "className": "ui center aligned"
        }
      ],
      "footerCallback": (row, data, start, end, display) => {

        let allowanceDate = 0;
        let allowanceCost = 0
        let rentDate = 0
        let rentCost = 0
        let travelCost = 0
        let otherCost = 0
        let sumCost = 0;

        data.map(val => {
          allowanceDate += val.allowanceDate;
          allowanceCost += val.allowanceCost;
          rentDate += val.rentDate;
          rentCost += val.rentCost;
          travelCost += val.travelCost;
          otherCost += val.otherCost;
          sumCost += val.sumCost;
        });
        $(row)[0].childNodes[1].innerHTML = allowanceDate;
        $(row)[0].childNodes[2].innerHTML = allowanceCost;
        $(row)[0].childNodes[3].innerHTML = rentDate;
        $(row)[0].childNodes[4].innerHTML = rentCost;
        $(row)[0].childNodes[5].innerHTML = travelCost;
        $(row)[0].childNodes[6].innerHTML = otherCost;
        $(row)[0].childNodes[7].innerHTML = sumCost;
      },
    });

    this.table.clear().draw();
    this.table.rows.add(this.travelCostDetail); // Add new data
    this.table.columns.adjust().draw(); // Redraw the DataTable

    //button edit
    this.table.on('click', 'tbody tr button.btn-edit', (e) => {
      var closestRow = $(e.target).closest('tr');
      var data = this.table.row(closestRow).data();
      console.log(data);

      //set date on form
      $("#idEdit").val(data.workSheetDetailId);
      $("#prefix").val(data.prefixForm);
      $("#prefix").dropdown('set selected');

      $("#name").val(data.nameForm);
      $("#last").val(data.lastForm);
      $("#position").val(data.positionForm);

      $("#case").val(data.caseForm);
      $("#case").dropdown('set selected');
      $("#type").val(data.typeForm);
      $("#type").dropdown('set selected');
      $("#level").val(data.levelForm);
      $("#level").dropdown('set selected');
     
      $("#appoveDate").val(data.appoveDateDataForm);
      $("#withdrawDate").val(data.withdrawDateDataForm);
      $("#goFrom").val(data.goFromForm);
      $("#food").val(data.foodForm);
      $("#startGoDate").val(data.startGoDateDataForm);
      $("#endGoDate").val(data.endGoDateDataForm);
      $("#numberLive").val(data.numberLiveForm);
      $("#typeWithdrawal").val(data.typeWithdrawalForm);
      $("#typeRoom").val(data.typeRoomForm);
      $("#note").val(data.note);
      $("#editFlag").val("edit");

    });

  }

  onSubmit = (e) => {
    //not reload page
    e.preventDefault();


    //json data
    let data = {
      headerId: e.target.headerId.value,
      idEdit: e.target.idEdit.value,
      editFlag: e.target.editFlag.value,
      prefix: e.target.prefix.value,
      name: e.target.name.value,
      last: e.target.last.value,
      position: e.target.position.value,
      caseType : e.target.case.value,
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
      travelCost: e.target.travelCost.value,
      otherCost: e.target.otherCost.value,
      days: this.momentDiff(e.target.startGoDateData.value, e.target.endGoDateData.value)
    }


    //post data
    const URL = "ia/int09213/addData";
    this.ajax.post(URL, JSON.stringify(data), res => {
      //success 
      if ($("#editFlag").val() == 'edit') {
        const index = this.travelCostDetail.findIndex(obj => obj.workSheetDetailId == res.json().workSheetDetailId);
        console.log("id : ", res.json().workSheetDetailId);
        console.log("Index : ", index);
        this.travelCostDetail[index] = res.json();
      } else {
        this.travelCostDetail.push(res.json());
      }

      console.log("Push array : ", this.travelCostDetail);
      // $('#tableData').DataTable().ajax.reload();
      this.table.clear().draw();
      this.table.rows.add(this.travelCostDetail); // Add new data
      this.table.columns.adjust().draw(); // Redraw the DataTable
      this.message.successModal("ทำรายสำเร็จ", "แจ้งเตือน");

      //form reset
      $("#editFlag").val("");
      $("#prefix").dropdown('restore defaults');
      $("#name").val("")
      $("#last").val("")
      $("#position").val("")
      $("#case").dropdown('restore defaults');
      $("#type").dropdown('restore defaults');
      $("#level").dropdown('restore defaults');
      $("#appoveDate").val("")
      $("#withdrawDate").val("")
      $("#goFrom").dropdown('restore defaults');
      $("#food").dropdown('restore defaults');
      $("#startGoDateData").val("")
      $("#endGoDateData").val("")
      $("#numberLive").val("")
      $("#travelCost").val("")
      $("#otherCost").val("")
      $("#note").val("")
    });


  }

  momentDiff = (start, end) => {

    //cal date
    let _start = stringToDate(start);
    let _end = stringToDate(end);
    let momentStart = moment(_start);
    let momentEnd = moment(_end);

    return momentEnd.diff(momentStart, 'hours');
  }

  ngOnInit() {

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

class Detail {
  allowanceCost: any;
  allowanceDate: any;
  allowanceDateDesc: any;
  category: any;
  createdBy: any;
  createdDate: any;
  degree: any;
  headerId: any;
  isDeleted: any;
  lastName: any;
  name: any;
  note: any;
  numberLive: any;
  otherCost: any;
  position: any;
  prefix: any;
  rentCost: any;
  rentDate: any;
  sumCost: any;
  travelCost: any;
  updatedBy: any;
  updatedDate: any;
  version: any;
  workSheetDetailId: any;
}
