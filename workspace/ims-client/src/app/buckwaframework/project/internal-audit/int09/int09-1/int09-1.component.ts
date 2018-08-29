import { Component, OnInit } from "@angular/core";
import { TextDateTH,formatter} from "../../../../common/helper/datepicker";
import { AjaxService, MessageBarService } from "../../../../common/services";
import { TravelCostHeader } from "../../../../common/models";
import { Router,ActivatedRoute } from "@angular/router";
import { TravelCostDetail } from "app/buckwaframework/common/models/travelcostdetail";
import { IaService } from 'app/buckwaframework/common/services/ia.service';

declare var $: any;
@Component({
  selector: "app-int09-1",
  templateUrl: "./int09-1.component.html",
  styleUrls: ["./int09-1.component.css"]
})
export class Int091Component implements OnInit {

  searchFlag: String;

  constructor(
    private message:MessageBarService,
    private ajax : AjaxService,
    private route: ActivatedRoute,
    private router: Router,
    private iaService: IaService,
    private msg: MessageBarService
  ) {}
  calenda = function () {
    $("#date").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#date2").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#date3").calendar({
      maxDate: new Date(),
      type: "year",
      text: TextDateTH,
      formatter: formatter("ป")
    });
    $("#date4").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#date5").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#date6").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

  clickSearch = function(){
    $("#searchFlag").val("TRUE");
    $('#tableData').DataTable().ajax.reload();
  }
  
  clickClear = function(){
    $("#searchFlag").val("FALSE");
    $('#tableData').DataTable().ajax.reload();
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
        "url" : '/ims-webapp/api/ia/int091/list',
        "contentType": "application/json",
        "type" : "POST",
        "data" : (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag" : $("#searchFlag").val()
          }));
        },  
      },
      "columns": [
        {
          "data": "id",
          "className": "ui center aligned",
          "render": function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          "data": "createdDate",
          "className": "ui center aligned"
        }, {
          "data": "createdBy"
        }, {
          "data": "fiscalYear",
          "className": "ui center aligned",
          "render": function (data, type, row, meta) {
            return row.fiscalYear.split("/")[2];
          }
        }, {
          "data": "createdDate"
        }, {
          "data": "createdDate"
        }, {
          "data": "createdDate"
        }, {
          "data": "createdBy"
        }, {
          "data": "createdBy"
        },{
          "data": "id",
          "className": "ui center aligned",
          "render" : function(data,type,row){
            var btn = '';
            btn +='<button class="mini ui primary button btn-edit">รายละเอียด</button>';
            btn +='<button class="mini ui red button btn-edit">ยกเลิก</button>';
              return btn;
          }
        }
      ]
    });

    //button edit>
    table.on('click', 'tbody tr button.btn-edit', ()=> {
			var closestRow = $(this).closest('tr');
      var data = table.row(closestRow).data();   
      this.router.navigate(['/int09/1/1']);
      console.log(data);
     
    });
    
    
  
  }


  ngOnInit() {
    this.dataTable();
    this.calenda();
  }

}
