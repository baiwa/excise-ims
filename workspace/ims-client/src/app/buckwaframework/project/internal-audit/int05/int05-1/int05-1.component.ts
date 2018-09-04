import { TextDateTH, formatter } from './../../../../common/helper/datepicker';
import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../common/services";
declare var $: any;
@Component({
  selector: "int05-1",
  templateUrl: "./int05-1.component.html",
  styleUrls: ["./int05-1.component.css"]
})
export class Int051Component implements OnInit {
  sector: any;
  area: any;
  branch: any;
  showData: boolean = true;
  $form : any;
  constructor(private ajax: AjaxService) {
   }


  ngOnInit() {
    this.$form = $("#formSearch");
    $(".ui.dropdown").dropdown();
    this.sectorDropdown();
    this.calenda();
    this.table();
  }
  ngAfterViewInit() {
    $(".ui.dropdown").dropdown();
  }
  sectorDropdown = () => {
    const URL = "ia/int0511/sector";
    this.ajax.get(URL, res => {
      this.sector = res.json();
    });
  }
  sectorOnchange = (e) => {
    const URL = "ia/int0511/area";
    let params = e.target.value;
    this.ajax.post(URL, params, res => {

      console.log("Id : ", res.json());
      this.area = res.json();
    });
  }
  areaOnchange = (e) => {
    const URL = "ia/int0511/area";
    let params = e.target.value;
    this.ajax.post(URL, params, res => {

      console.log("Id : ", res.json());
      this.branch = res.json();
    });
  }

  onClear =()=>{
    this.$form.form("clear");
    $("#srachFlag").val("FALSE");
    $("#dataTable").DataTable().ajax.reload();
  }

  onSubmit =()=>{
    $("#searchFlag").val("TRUE");
    $("#dataTable").DataTable().ajax.reload();
  }

  calenda = () => {
    $("#dateF").calendar({
      maxDate: new Date(),
      endCalendar: $("#dateTo"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateT").calendar({
      maxDate: new Date(),
      startCalendar: $("#dateForm"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

  table =()=>{
    var table = $("#dataTable").DataTable({
      "serverSide": true,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int0511/findAll',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
          "sector" : $("#sector").val(),
          "area" : $("#area").val(),           
          "branch" : $("#branch").val(),
          "dateForm" : $("#dateForm").val(),
          "dateTo" : $("#dateTo").val(),
          "searchFlag" : $("#searchFlag").val()
          }));
        },
      },

      "columns": [
        {
          "data": "dateOfPay",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          "className": "ui center aligned"
        }, {
          "data": "dateOfPay"
        }, {
          "data": "status",
          "className": "ui center aligned",         
        }, {
          "data": "departmentName",
          "className": "ui center aligned"
        }, {
          "data": "bookNumberWithdrawStamp",          
          "className": "ui center aligned"
        }, {
          "data": "dateWithdrawStamp",          
          "className": "ui center aligned"
        }, {
          "data": "bookNumberDeliverStamp",          
          "className": "ui center aligned"
        }, {
          "data": "dateDeliverStamp",          
          "className": "ui center aligned"
        }, {
          "data": "fivePartNumber",          
          "className": "ui center aligned"
        }, {
          "data": "createdDate",          
          "className": "ui center aligned"
        }, {
          "data": "stampCheckDate",          
          "className": "ui center aligned"
        }, {
          "data": "stampChecker",          
          "className": "ui center aligned"
        }, {
          "data": "stampBrand",          
          "className": "ui center aligned"
        }, {
          "data": "numberOfBook",          
          "className": "ui center aligned"
        }, {
          "data": "numberOfStamp",          
          "className": "ui center aligned"
        }, {
          "data": "valueOfStampPrinted",          
          "className": "ui center aligned"
        }, {
          "data": "sumOfValue",          
          "className": "ui center aligned"
        }, {
          "data": "serialNumber",          
          "className": "ui center aligned"
        }, {
          "data": "note",          
          "className": "ui center aligned"
        }, {
          "data": "note",      
          "render": function(data, type, row){
            var btn='';
             btn += '<button class="ui mini blue button btn-detail">รายละเอียด</button>';
             btn += '<button class="ui mini yellow button btn-edit">แก้ไข</button>';
             btn += '<button class="ui mini red button btn-delete">ลบ</button>';
            return btn;
          },
          "className": "ui center aligned"
        }
      ]
    });
    table.on('click', 'tbody tr button.btn-detail', function () {
      var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();
      console.log(data);

    }); 
    table.on('click', 'tbody tr button.btn-edit', function () {
      var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();
      console.log(data);
      $('#modal-edit').modal('show');

    }); 
    table.on('click', 'tbody tr button.btn-delete', function () {
      var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();
      console.log(data);

    }); 
  }
}

