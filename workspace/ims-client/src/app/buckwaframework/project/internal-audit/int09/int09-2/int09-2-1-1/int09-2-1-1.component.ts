import { MessageBarService } from './../../../../../common/services/message-bar.service';
import { AjaxService } from './../../../../../common/services/ajax.service';
import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-int09-2-1-1',
  templateUrl: './int09-2-1-1.component.html'
})
export class Int09211Component implements OnInit {

  deparmentList : any;
  constructor(
    private message: MessageBarService,
    private ajax : AjaxService
  ) { }


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
  clickClear = ()  =>{   
    $("#year1").val("");
    $("#searchFlag").val("FALSE");
    $("#department").dropdown("restore defaults");
    
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

  clickDelete = () => {
    if($('#tableData').DataTable().rows().count() == 0 ){
      this.message.alert("ไม่มีข้อมูล")
      return false
    }

    if (!$('input[type="checkbox"]').is(':checked')) {
      this.message.alert("กรุณาเลือกรายการ")
      return false
    }

    var dataList = [];
		var node =  $('#tableData').DataTable().rows().nodes();
		$.each(node, function (index, value) {
			if ($(this).find('input[type=checkbox]').is(':checked')) {
		    	var data =  $('#tableData').DataTable().rows().data()[index];
          dataList.push(parseInt(data.id));
		    }
    });
    this.message.comfirm((res) => {
      if(res){
        const URL = "ia/int09211/delete";
    this.ajax.post(URL,dataList, res => {
      const msg = res.json();
      if (msg.messageType == "C") {
        this.message.successModal(msg.messageTh);
      } else {
        this.message.errorModal(msg.messageTh);
      }
      $('#tableData').DataTable().ajax.reload();
    });
      }
      
    },"ลบรายการ");
  }

  deparmentDropdown = () =>{

    const URL = "ia/int09211/departmentDropdown";
    this.ajax.get(URL, res => {
      this.deparmentList = res.json();
    });
  }

  dataTable = () => {
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
        "data": (d) => {
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
          "data": "startDate",
          "className": "ui center aligned",         
        }, {
          "data": "createdBy",
          "className": "ui center aligned"
        }, {
          "data": "createdBy",
          "render": function (data, type, row) {
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
    $(".ui .dropdown").dropdown();
    this.dataTable();
    this.calenda();
    this.deparmentDropdown();
  }

}
