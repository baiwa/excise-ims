import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { MessageBarService } from './../../../../../common/services/message-bar.service';
import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute} from '@angular/router';
import { AjaxService } from 'app/buckwaframework/common/services';
import { IaService } from 'app/buckwaframework/common/services/ia.service';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-2-1',
  templateUrl: './int09-2-2-1.component.html'
})
export class Int09221Component implements OnInit {
  documentTypeList : any;
  constructor(
    private message:MessageBarService,
    private ajax : AjaxService,
    private route: ActivatedRoute,
    private router: Router,
    private iaService: IaService
  ) { }

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
    $("#checkAllSap").prop('checked', false);
  }
  
  clickClear = function(){
    $("#money").val("");  
    $("#searchFlag").val("FALSE");

    $("#documentType").prop('selectedIndex',0);    
    $("#documentType").dropdown('clear');
    
    $("#checkAllSap").prop('checked', false);
    $('#tableData').DataTable().ajax.reload();
  }

  clickDelete = () => {
    if($('#tableData').DataTable().rows().count() == 0 ){
      this.message.alert("ไม่มีข้อมูล")
      return false
    }

    if (!$('input[type="checkbox"]').is(':checked')) {
      this.message.alert("กรุณาเลือกรายการที่ต้องการลบ")
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
    const URL = "ia/int09221/deleteList";
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
    },"ลบรายการที่เลือก");
    $("#checkAllSap").prop('checked', false);
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

  deparmentDropdown = () =>{

    const URL = "ia/int09221/documentTypeDropdown";
    this.ajax.get(URL, res => {
      this.documentTypeList= res.json();
    });
  }

  dataTable = function(){
    $("#doctype").val() == 0 ? "":$("doctype").val();
    var table = $('#tableData').DataTable({
      "lengthChange":true,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,      
      "ajax" : {
        "url" : '/ims-webapp/api/ia/int09221/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "documentType" : $("#documentType").val(),
            "year" : $("#year1").val(),
            "searchFlag" : $("#searchFlag").val()
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
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          "className": "ui center aligned"
        }, {
          "render": function (data, type, row, meta) {
            return row.createdDate;
            // return date;
          },
          "className": "ui center aligned"
        }, {
          "data": "createdBy"
        }, {
          "data": "documentType",
          "render": function (data, type, row, meta) {
            let docType = "";
           if(data==1){
            docType = "หลักฐานการจ่ายเงินค่าใช้จ่ายในการเดินทางไปราชการ";
           }else if(data==2){
            docType = "ใบเบิกค่าใช้จ่ายในการเดินทางไปราชการ";
           }else if(data==3){
            docType = "เอกสารหลักฐานแนบ";
           }
            return docType;
          }
        }, {
          "data": "thosePicked"
        }, {
          "render": function (data, type, row, meta) {
            return row.fiscalYear.split("/")[2];
          },
          "className": "ui center aligned"
        },{
          "data": "id",
          "render" : function(data,type,row){
            var btn = '<button class="ui mini primary button btn-edit">รายละเอียด</button>';
              return btn;
          },
          "className": "ui center aligned"
        }
      ]
    });

    //button edit>
    table.on('click', 'tbody tr button.btn-edit', ()=> {
			var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();   
      this.router.navigate(['/int09/2-2-3']);
      console.log(data);
     
    });
    
    
  
  }

  // setValue = () => {
   
  //   const data = this.iaService.getData();
  //   console.log(data);
  //   if(data){
  //     $("#searchFlag").val(data.searchFlag);
  //     $("#year").val(data.year);
  //     $("#documentType").val(data.documentType);
  //   }
  // }

  clickCreate = () => {
    
    // let docType = $("#documentType").val();
    // let year1 = $("#year1").val();
    // this.iaService.setData({searchFlag:"TRUE"});
    this.router.navigate(['/int09/2-2-2']);
  }

  ngOnInit() { 
    this.deparmentDropdown();
  }
  ngAfterViewInit() {
    $('.ui.dropdown').dropdown();
    // this.setValue();
    this.calenda();
    this.dataTable();
   

    
  }
}
