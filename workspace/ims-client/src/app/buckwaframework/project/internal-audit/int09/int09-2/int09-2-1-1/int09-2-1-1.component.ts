import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-int09-2-1-1',
  templateUrl: './int09-2-1-1.component.html'
})
export class Int09211Component implements OnInit {

  constructor() { }


   calenda = function () {
    $("#year").calendar({
      maxDate: new Date(),
      type: "year",
      text: TextDateTH,
      formatter: formatter("ป")
    });
  }
  clickSearch = function () {
    $("#searchFlag").val("TRUE");
    $('#tableData').DataTable().ajax.reload();
  }
  clickClear = function () {
    $("#year1").val("");
    $("#department").val("");
    $("#searchFlag").val("FALSE");
    this.officeDropdown();
    $('#tableData').DataTable().ajax.reload();
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

  officeDropdown = function () {
    $.ajax({
      url: "/ims-webapp/api/ia/int09211/departmentDropdown", 
      contentType: "application/json",
      type: "GET",
      data: function (d) {
        return JSON.stringify($.extend({}, d, {}));
      },
      success: function (data) {
        var str = "<option value='0'>หน่วยงาน</option>";
        data.map( obj => {
          str +="<option value='"+obj.value+"'>"+obj.label+"</option>";          
        });

        $("#department").html(str).dropdown();
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
        "url": '/ims-webapp/api/ia/int09211/list',
        "contentType": "application/json",
        "type": "POST",
        "data": function (d) {
          return JSON.stringify($.extend({}, d, {
            "year": $("#year1").val(),
            "department": $("#department").val(),
            "searchFlag": $("#searchFlag").val()
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
        }, {
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
          "render": function (data, type, row) {
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
    this.calenda();
    this.officeDropdown();
  }

}
